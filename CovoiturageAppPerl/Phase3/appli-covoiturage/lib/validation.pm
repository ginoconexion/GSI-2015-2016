package validation;
use strict;
use warnings;
use Dancer ':syntax';
use Data::Dumper;
use Data::Dumper::HTML 'dumper_html';
use Dancer::Plugin::DBIC;
use LWP::UserAgent;
use JSON;
use URI;

# --- chargement classes DBIx::Class du schéma covoiturage
my $schema = schema('covoiturage');

my @places = (1, 2, 3, 4);

sub getAlbiToulouseCoordinates($$){
	my ($donnees, $type) = @_;
	my $albi = getLieuOpenstreetmap("Albi");
	my $toulouse = getLieuOpenstreetmap("Toulouse");
	$donnees->{"annonce"}->{"id_lieu_depart"} = $albi;
	$donnees->{"annonce"}->{"id_lieu_destination"} = $toulouse;
	$donnees->{"annonce"}->{"prix"} = "5";
}

sub getLieuOpenstreetmap($){
	my ($nom) = @_;
	my $lieu = 0;
	my $ua = LWP::UserAgent->new;
	$ua->agent("Emacovoit(pgiraultmatz\@gmail.com)/0.1 ");
	my $uri = URI->new('http://nominatim.openstreetmap.org/search');
	$uri->query_form(
	 	countrycodes => 'fr',
	 	format => 'json',
	 	polygon => 0,
	 	addressdetails => 1,
	 	q => $nom,
	 	'&'
	 );
	 my $req = HTTP::Request->new(GET => $uri);
	 $req->content_type('application/x-www-form-urlencoded');
	 my $res = $ua->request($req);
	 
	 if ($res->is_success){
	 	my $json_text = $res->content;
		my $json = JSON->new->allow_nonref;
		my @perl_scalar = $json->utf8(1)->decode($json_text);
		
		my $index;
		# on parcourt les n premières entrées
	 	for ($index = 0; $index <= 15; $index++) {
	 		
	 		my $entry = $perl_scalar[0][$index];
	 		# si l'entrée est de type ville
	 		if (exists($entry->{type}) and ($entry->{type} eq "city" or $entry->{type} eq "administrative")) {
	 			# on récupére ou crée le lieu
	 			#messages::information(dumper_html($perl_scalar[0][$index]));
	 			$lieu = getOrCreateLieu1($entry->{address}->{city}, $entry->{lat}, $entry->{lon});
	 			last;
	 		}
	 	};
	 }
	 # si le service ne répond pas
	 else {
	 	$lieu = 0;
	 }
	 return $lieu;
};

sub getOrCreateLieu1($$$) {
	my ($nom, $latitude, $longitude) = @_;
	my $lieu = $schema->resultset('LieuFrance')->find({ nom => $nom, });
	# si le lieu existe
	if ($lieu){
		
	}
	# s'il n'existe pas on le crée
	else {
		my $profil = $schema->class('LieuFrance')->profil;
		my $coordonnees = {
			'nom' => $nom,
			'latitude' => $latitude,
			'longitude' => $longitude,
		};
		my $lieu = 0;
		my ($validation) = Data::FormValidator->check($coordonnees, $profil);
		$lieu = $schema->resultset('LieuFrance')->create_from_fv($validation)
	}
	return $lieu;
}

sub getListeEtapes($$){
	my ($params, $messages) = @_;
	my @etapes = ();
	for my $key (sort(keys($params))) {
		#messages::information(dumper_html($key));
		if ($key =~ m/etape\[[0-9]+\]/ ){
			my $nom = $params->{$key};
			#messages::information($nom);
			my $numero = substr($key, 6, 1);
			my $etape = getLieuOpenstreetmap($nom);
			if ($etape != 0){
				push @etapes, $etape;
			}
			else {
				$messages->{"etapes"}->{$numero} = "Veuillez saisir une adresse";
			}
		}
	}
	return @etapes;
}

sub validerCovoiturageFrance($$$){
	#validation dans le cas de covoit albi-toulouse ou covoit france
	my ($params,$request, $type) = @_;
	my $id_type_covoiturage = $type->id;
	my $profil = $schema->class('CovoiturageFrance')->profil;
	my ($results) = Data::FormValidator->check($params, $profil);
	my $messages = $results->msgs;
	
	my @tailles = $schema->resultset('TailleBagage')->search({})->all();
	my $lieuDestination = getLieuOpenstreetmap(params->{'destination.nom'});
	my $lieuDepart = getLieuOpenstreetmap(params->{'depart.nom'});
	my @etapes = getListeEtapes($params, $messages);
	my $erreursEtapes = 1;
	if (exists($messages->{"etapes"})) {
		messages::information("erreurs etapes");
		$erreursEtapes = 0;
	}
	
	#messages::information(dumper_html($results));
	if ($results->has_invalid or $results->has_missing or $lieuDestination == 0 or $lieuDepart == 0 or $erreursEtapes == 0) {
		
		my $reaffichage = $request->Vars;
		my $donnees = {
			places => \@places,
			tailles => \@tailles,
			url_validation => uri_for("/annonce/publier/$id_type_covoiturage/"),
			annonce => $reaffichage,
			msgs => $messages,
		};
		if (defined param('id_annonce')) {
			my $id_annonce = param('id_annonce');
			$donnees->{"url_validation"} = uri_for("/annonce/modifier/$id_type_covoiturage/$id_annonce/");
		}
		$donnees->{"annonce"}->{"etapes"} = \@etapes;
		$donnees->{"annonce"}->{"id_type_covoiturage"} = $type;
		$donnees->{"annonce"}->{"id_lieu_depart"} = $lieuDepart;
		$donnees->{"annonce"}->{"id_lieu_destination"} = $lieuDestination;
		my $template = "annonce/publier";
		
		$template ="$template/publierFrance";
		template $template, $donnees
	}
	else {
		
		# actions réalisées qu'il s'agisse de modification ou publication
		my $dt = createDateTime($results->valid('date'), $results->valid('heure'));
		$params->{'date_depart'} = $dt;
		$params->{'id_lieu_depart'} = $lieuDepart->id;
		$params->{'id_lieu_destination'} = $lieuDestination->id;
		my $annonce;
		
		# si le param id_annonce de la route utilisée n'est pas nul, on modifie une annonce existante
		if (defined param('id_annonce')) {
			my $id_annonce = param('id_annonce');
			$annonce = $schema->resultset('CovoiturageFrance')->find($id_annonce);
			firewall::isAuthorOrAdmin($annonce);
			my ($results) = Data::FormValidator->check($params, $profil);
			$annonce->update_from_fv($results);
			for my $passePar ($annonce->passe_pars){
				warning Dumper($passePar);
				$passePar->delete;
			};
			messages::succes("Annonce modifiée avec succes");
		}
		# sinon publication d'une nouvelle annonce
		else {
			
			my $date_publication = DateTime->now->set_time_zone( 'Europe/Paris' );
			my $user = session 'user';
			$params->{'date_publication'} = $date_publication;
			$params->{'id_utilisateur'} = $user->id;
			$params->{'id_type_covoiturage'} = $type->id;
			my ($results) = Data::FormValidator->check($params, $profil);
			$annonce = $schema->resultset('CovoiturageFrance')->create_from_fv($results);
			messages::succes("Annonce publiee avec succes");
		}
		
		# on insére la liste des étapes
		my $profilEtape = $schema->class('PassePar')->profil;
		for my $etape (@etapes){
			#warning Dumper($etape);
			my $paramsEtape = {};
				my $etape_id = $etape->id;
				$paramsEtape->{'id_covoiturage'} = $annonce->id;
				$paramsEtape->{'id_lieu'} = $etape_id;
				my ($resultsEtape) = Data::FormValidator->check($paramsEtape, $profilEtape);
				#messages::information(dumper_html($resultsEtape));
				$schema->resultset('PassePar')->create_from_fv($resultsEtape);
		}
		# redirige en requête post
		my $id_annonce = $annonce->id;
		my $route = "/annonce/$id_type_covoiturage/$id_annonce/";
		redirect $route;
	}
}

sub validerCovoiturageAlbi($$$){
	#validation dans le cas de covoit albi
	my ($params,$request, $type) = @_;
	my $id_type_covoiturage = $type->id;
	
	my @lieux = $schema->resultset('LieuAlbi')->search({})->all();
	my $profil = $schema->class('CovoiturageAlbi')->profil;
	my ($results) = Data::FormValidator->check($params, $profil);
	
	my $lieuDepart = 0;
	my $lieuDestination = 0;
	
	# on récupère le lieu de départ et de destination s'ils ont été fournis
	if ($results->valid('id_lieu_depart')){
		my $id_lieu_depart = $results->valid('id_lieu_depart');
		$lieuDepart = $schema->resultset('LieuAlbi')->find($id_lieu_depart);
	}
	if ($results->valid('id_lieu_destination')){
		my $id_lieu_destination = $results->valid('id_lieu_destination');
		$lieuDestination = $schema->resultset('LieuAlbi')->find($id_lieu_destination);
	}
	
	#messages::information(dumper_html($results) );
	if ($results->has_invalid or $results->has_missing or $lieuDepart == 0 or $lieuDestination == 0){
		my $reaffichage = $request->Vars;
		my $msg = $results->msgs;
		
		my $donnees = {
			places => \@places,
			lieux => \@lieux,
			url_validation => uri_for("/annonce/publier/$id_type_covoiturage/"),
			annonce => $reaffichage,
			msgs => $msg,
		};
		if (defined param('id_annonce')){
			my $id_annonce = param('id_annonce');
			$donnees->{"url_validation"} = uri_for("/annonce/modifier/$id_type_covoiturage/$id_annonce/");
		}
		
		$donnees->{"annonce"}->{"id_lieu_depart"} = $lieuDepart;
		$donnees->{"annonce"}->{"id_lieu_destination"} = $lieuDestination;
		template 'annonce/publier/publierAlbi', $donnees;
	}
	else {
		my $dt = createDateTime($results->valid('date'), $results->valid('heure'));
		$params->{'date_depart'} = $dt;
		my $id_annonce;
		
		if(defined param('id_annonce')){
			$id_annonce = param('id_annonce');
			my $annonce = $schema->resultset('CovoiturageAlbi')->find($id_annonce);
			firewall::isAuthorOrAdmin($annonce);
			my ($results) = Data::FormValidator->check($params, $profil);
			$annonce->update_from_fv($results);
			messages::succes("Annonce modifiée avec succes");
		}
		else {
			my $date_publication = DateTime->now->set_time_zone( 'Europe/Paris' );
			my $user = session 'user';
			
			$params->{'date_publication'} = $date_publication;
			$params->{'id_utilisateur'} = $user->id;
			$params->{'id_type_covoiturage'} = $type->id;
			
			my ($results) = Data::FormValidator->check($params, $profil);
			my $covoiturage = $schema->resultset('CovoiturageAlbi')->create_from_fv($results);
			$id_annonce = $covoiturage->id;
			messages::succes("Annonce publiee avec succes");
			
		}
		# redirige en requête post
		my $route = "/annonce/$id_type_covoiturage/$id_annonce/";
		redirect $route;
	}
}

sub createDate($){
	my ($date) = @_;
	
	my $d = DateTime->new(
		year => substr($date, 6, 4),
		month => substr($date, 3, 2),
		day => substr($date, 0, 2),
	);
	return $d;
}

sub createDateTime($$){
	my ($date, $clock) = @_;
	
	my $dt = DateTime->new(
			year => substr($date, 6, 4),
			month => substr($date, 3, 2),
			day => substr($date, 0, 2),
			hour => substr($clock, 0, 2),
			minute => substr($clock, 3, 2),
		);
	return $dt;
}

sub validerLieuAlbi() {
	
	my $profil = $schema->class('LieuAlbi')->profil;
	my @listeLieux = $schema->resultset('LieuAlbi')->search({})->all();
	my $params = request->params;
	my ($results) = Data::FormValidator->check($params, $profil);
	my $template = "";
	
	if ($results->has_invalid or $results->has_missing){
		my $vars = request->Vars;
		my $msgs = $results->msgs;
		
		my $donnees = {
			lieu => $vars,
			msgs => $msgs,
		};
		
		if (defined param('id')){
			my $id = param('id');
			$donnees->{"url_validation"} = uri_for("/admin/albi/lieu/modifier/$id/");
			$template = "lieuAlbi/modifier";
		}
		else {
			$template = "lieuAlbi/liste";
			$donnees->{"lieux"} = \@listeLieux;
			$donnees->{"url_validation"} => uri_for('/admin/albi/lieu/liste/'),
		}
	}
	else {
		if (defined param('id')){
			my $id = param('id');
			my $lieu = $schema->resultset('LieuAlbi')->find($id);
			$lieu->update_from_fv($results);
		}
		else {
			$schema->resultset('LieuAlbi')->create_from_fv($results);
		}
		my $route = "/admin/albi/lieu/liste/";
		redirect $route;
	}
}
