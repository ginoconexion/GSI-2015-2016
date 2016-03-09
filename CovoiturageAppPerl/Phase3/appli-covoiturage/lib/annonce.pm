package annonce;
use Dancer ':syntax';
use CGI::Carp qw(fatalsToBrowser);
use Dancer::Plugin::DBIC;
use Data::Dumper;
use Data::Dumper::HTML 'dumper_html';
use DateTime;
use DateTime::Format::Strptime;
use Data::FormValidator;
use Data::FormValidator::Constraints::DateTime qw(:all);
use POSIX qw/ceil/;
use Dancer::Exception qw(:all);
use strict;
use warnings;

# toutes les routes de ce fichier seront préfixées du préfixe suivant
prefix '/annonce';
 
# --- chargement classes DBIx::Class du schéma covoiturage
my $schema = schema('covoiturage');

my @places = (1, 2, 3, 4);
my @types = $schema->resultset('Type')->search({})->all();


get '/rechercher/:type_code/' => sub {
	my $params = request->params;
	my $code = param('type_code');
	my $type = $schema->resultset('Type')->find({ code => $code, });
	
		my $id_type = $type->id; 
		my $where->{'id_type_covoiturage'} = $id_type;
		$where->{'date_depart'} = { '>=', 'curdate()'};
		my $conditions = {
			join => [ qw/ id_lieu_depart id_lieu_destination /],
			order_by => { -asc => 'date_depart'},
		};
		my @annonces = ();
		my $date;
		my $donnees = {
			url_validation => uri_for("/annonce/rechercher/$code/"),
		};
		if ($code eq 'albi-toulouse' && !exists(params->{'depart.nom'}) && !exists(params->{'destination.nom'})){
			validation::getAlbiToulouseCoordinates($donnees, $type);
		}
		
		if ($code eq 'albi-toulouse' or $code eq 'france') {
			#messages::information('albi-toulouse ou france');
			my $profil = $schema->class('CovoiturageFrance')->profil_date;
			my $lieuDepart = validation::getLieuOpenstreetmap(params->{'depart.nom'});
			my $lieuDestination = validation::getLieuOpenstreetmap(params->{'destination.nom'});
			my $validationDate = Data::FormValidator->check($params, $profil);
			
			if ($validationDate->valid('date')) {
				#messages::information('date valide');
				$date = $validationDate->valid('date');
				$donnees->{"annonce"}->{"date"} = $date;	
				my $date1 = validation::createDate($date);
				$where->{"cast(date_depart as date)"} = "$date1";
			}
			
			# si les deux lieux ont été fourni
			if ($lieuDepart != 0 && $lieuDestination != 0){
				@annonces = filter::filterByDepartAndDestination($lieuDepart, $lieuDestination, $donnees, $date);
			}
			
			# si le lieu de départ a été fourni
			elsif ($lieuDepart != 0) {
				@annonces = filter::filterByLieuDepart($lieuDepart, $donnees, $date);
			}
			# si le lieu de destination a été fourni
			elsif ($lieuDestination != 0) {
				@annonces = filter::filterByLieuDestination($lieuDestination, $donnees, $date);
			}
			# si aucun lieu n'a été fourni
			else {
				@annonces = $schema->resultset('CovoiturageFrance')->search($where, $conditions);
			}
		}
		elsif ($code eq 'albi'){
			#messages::information('albi');
			my @lieux = $schema->resultset('LieuAlbi')->search({})->all();
			$donnees->{"lieux"} = \@lieux;
			my $profil = $schema->class('CovoiturageAlbi')->profil;
			my $validation = Data::FormValidator->check($params, $profil);
			
			if ($validation->valid('id_lieu_depart')){
				my $id_lieu_depart = $validation->valid('id_lieu_depart');
				my $lieu_depart = $schema->resultset('LieuAlbi')->find($id_lieu_depart);
				$donnees->{"annonce"}->{"id_lieu_depart"} = $lieu_depart;
				$where->{"id_lieu_depart.id"} = $lieu_depart->id;
			}
			if ($validation->valid('id_lieu_destination')){
				my $id_lieu_destination = $validation->valid('id_lieu_destination');
				my $lieu_destination = $schema->resultset('LieuAlbi')->find($id_lieu_destination);
				$donnees->{"annonce"}->{"id_lieu_destination"} = $lieu_destination;
				$where->{"id_lieu_destination.id"} = $lieu_destination->id;
			}
			if ($validation->valid('date')){
				#messages::information('date valide');
				my $date = $validation->valid('date');
				$donnees->{"annonce"}->{"date"} = $date;			
				$date = validation::createDate($date);
				$where->{"cast(date_depart as date)"} = "$date";
			}
			@annonces = $schema->resultset('CovoiturageAlbi')->search($where, $conditions);
		}
		$donnees->{"annonce"}->{"id_type_covoiturage"} = $type;
		$donnees->{"annonces"} = \@annonces;
		template 'annonce/rechercher/rechercher', $donnees;
};

get '/publier/' => sub {
	template 'annonce/publier/choixType', {
		types => \@types,
		url_validation => uri_for('/annonce/publier/'),
	}
};

post '/publier/' => sub {
	my @types = $schema->resultset('Type')->search({})->all();
	my $profil = $schema->class('Type')->profil;
	my $params = request->params;
	my ($results) = Data::FormValidator->check($params, $profil);
	
	#warning Dumper($results);
	# si aucun type choisi
	#messages::information(dumper_html($results));
	if ($results->has_invalid or $results->has_missing){
		my $vars = request->Vars;
		my $msgs = $results->msgs;
		template 'annonce/publier/choixType', {
			types => \@types,
			url_validation => uri_for('/annonce/publier/'),
			form => $vars,
			msgs => $msgs,
		}
	}
	# si un type a été choisi
	else {
		my $type = $schema->resultset('Type')->find($results->valid('id_type_covoiturage'));
		my $id = $type->id;
		my $route = "/annonce/publier/$id/";
		#messages::information(dumper_html($route));
		redirect $route;
	}
};

get '/publier/:id_type/' => sub {
	my $id_type_covoiturage = param('id_type');
	my $type = $schema->resultset('Type')->find($id_type_covoiturage);
	#warning Dumper($type);
	
		#messages::information(session 'user');
		my @tailles = $schema->resultset('TailleBagage')->search({})->all();
		my $template = "annonce/publier";
		my $donnees = {
			places => \@places,
			tailles => \@tailles,
			url_validation => uri_for("/annonce/publier/$id_type_covoiturage/"),
		};
		$donnees->{"annonce"}->{"id_type_covoiturage"} = $type;
		
		if ($type->code eq "albi-toulouse") {
			$template ="$template/publierFrance";
			validation::getAlbiToulouseCoordinates($donnees, $type);
		}
		elsif ($type->code eq "france"){
			$template ="$template/publierFrance";
		}
		elsif ($type->code eq "albi"){
			$template ="$template/publierAlbi";
			my @lieux = $schema->resultset('LieuAlbi')->search({})->all();
			$donnees->{"lieux"} = \@lieux;
		}
		# pre remplissage de données pour test
		#$donnees->{"annonce"}->{"date"} = "29-03-2016";
		#$donnees->{"annonce"}->{"heure"} = "10:30";
	
		template $template, $donnees;
};

post '/publier/:id_type/' => sub {
	my $request = request;
	my $params = request->params;
	#warning Dumper($params);
	#messages::information(dumper_html($params));
	
	my $id_type_covoiturage = param('id_type');
	#warning Dumper($id_type_covoiturage);
	my $type = $schema->resultset('Type')->find($id_type_covoiturage);
	
	if ($type->code eq "albi"){
		validation::validerCovoiturageAlbi($params, $request, $type);
	}
	elsif ($type->code eq "albi-toulouse" or $type->code eq "france"){
		validation::validerCovoiturageFrance($params, $request, $type);
	}
};

get '/modifier/:id_type/:id_annonce/' => sub {
	
	my $request = request;
	my $params = $request->params;
	my $id_type = param('id_type');
	my $id_annonce = param('id_annonce');
	my $type = $schema->resultset('Type')->find($id_type);
	
		my $donnees = {
			places => \@places,
			url_validation => uri_for("/annonce/modifier/$id_type/$id_annonce/"),
		};
		
		if ($type->code eq "albi"){
			my @lieux = $schema->resultset('LieuAlbi')->search({})->all();
			my $annonce = $schema->resultset('CovoiturageAlbi')->find($id_annonce);
			firewall::isAuthorOrAdmin($annonce);
			$donnees->{"lieux"} = \@lieux;
			$donnees->{"annonce"} = $annonce;
			template 'annonce/publier/publierAlbi', $donnees;
		}
		elsif ($type->code eq "albi-toulouse" or $type->code eq "france") {
			my $annonce = $schema->resultset('CovoiturageFrance')->find($id_annonce);
			firewall::isAuthorOrAdmin($annonce);
			my @tailles = $schema->resultset('TailleBagage')->search({})->all();
			my $lieu_depart = $annonce->id_lieu_depart();
			my $lieu_destination = $annonce->id_lieu_destination();
			$donnees->{"annonce"} = $annonce;
			$donnees->{"tailles"} = \@tailles;
			template 'annonce/publier/publierFrance', $donnees;
		}
};


post '/modifier/:id_type/:id_annonce/' => sub {
	my $request = request;
	my $params = $request->params;
	my $id_type = param('id_type');
	my $id_annonce = param('id_annonce');
	my $type = $schema->resultset('Type')->find($id_type);
	
	if ($type->code eq "albi"){
		validation::validerCovoiturageAlbi($params, $request, $type);
	}
	elsif ($type->code eq "albi-toulouse" or $type->code eq "france"){
		validation::validerCovoiturageFrance($params, $request, $type);
	}
};

get '/:id_type/:id_annonce/' => sub {
		my $id_type = param('id_type');
		my $id_annonce = param('id_annonce');
		my $type = $schema->resultset('Type')->find($id_type);
		my @tailles = $schema->resultset('TailleBagage')->search({})->all();
		my $user = session('user');
		my $donnees = {
			type => $type,
		};
		
		if ($type->code eq "albi"){
			my @lieux = $schema->resultset('LieuAlbi')->search({})->all();
			my $annonce = $schema->resultset('CovoiturageAlbi')->find($id_annonce);
			$donnees->{"annonce"} = $annonce;
		}
		elsif ($type->code eq "albi-toulouse" or $type->code eq "france") {
			my $annonce = $schema->resultset('CovoiturageFrance')->find($id_annonce);
			$donnees->{"annonce"} = $annonce;
		}
		template 'annonce/vue', $donnees, $user;
};

get '/publiees/' => sub {
	
	# récupération utilisateur courant
	my $user = session('user');
	my $conditions = {
		order_by => { -asc => 'date_depart'},
	};
	
	my @annonces = $schema->resultset('CovoiturageFrance')->search({'id_utilisateur' => $user->id}, $conditions);

	my @annoncesAlbi = $schema->resultset('CovoiturageAlbi')->search({'id_utilisateur' => $user->id}, $conditions);
	
	for my $annonce (@annoncesAlbi){
		push @annonces, $annonce;
	}
	my $donnees = {
		annonces => \@annonces,
		type => "listePubliees",
	};
	template 'annonce/rechercher/listeAnnonces', $donnees;
};


get '/sauvegardees/' => sub { 
	
	my $user = session 'user';
	my $conditions = {
		join => [ qw/ id_covoiturage id_utilisateur/],
		order_by => { -asc => 'date_depart'},
	};
	my @sauvegardes = $schema->resultset('Sauvegarde')->search({'id_utilisateur.id'=> $user->id}, $conditions);
	
	my @annonces = ();
	for my $sauvegarde (@sauvegardes){
		warning Dumper($sauvegarde);
		my $annonce = $sauvegarde->id_covoiturage();
		push @sauvegardes, $annonce;
	};
	
	
	template 'rechercher/listeSauvegarde',  {
		annonces => \@annonces,
	}	
};


get '/supprimer/:id_type/:id_annonce/' => sub {
	
	my $id_type = param('id_type');
	my $id_annonce = param('id_annonce');
	my $type = $schema->resultset('Type')->find($id_type);
	my $annonce;
	
	if ($type->code eq "albi"){
		$annonce = $schema->resultset('CovoiturageAlbi')->find($id_annonce);
		firewall::isAuthorOrAdmin($annonce);		
	}
	else {
		$annonce = $schema->resultset('CovoiturageFrance')->find($id_annonce);
		firewall::isAuthorOrAdmin($annonce);
	}
	
	template 'annonce/supprimer', {
		url_validation => uri_for("/annonce/supprimer/$id_type/$id_annonce/"),
		annonce => $annonce,
	};
};

post '/supprimer/:id_type/:id_annonce/' => sub {
	
	my $id_type = param('id_type');
	my $id_annonce = param('id_annonce');
	my $type = $schema->resultset('Type')->find($id_type);
	my $annonce;
	
	if ($type->code eq "albi"){
		$annonce = $schema->resultset('CovoiturageAlbi')->find($id_annonce);
		firewall::isAuthorOrAdmin($annonce);
	}
	else {
		$annonce = $schema->resultset('CovoiturageFrance')->find($id_annonce);
		firewall::isAuthorOrAdmin($annonce);
	}
	$annonce->delete();
	messages::succes("Annonce supprimée");
	forward '/accueil/'
};	

return true;