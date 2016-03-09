package admin;
use strict;
use warnings;
use Dancer ':syntax';

use Data::Dumper;
use Data::Dumper::HTML 'dumper_html';
use Dancer::Plugin::DBIC;

# --- chargement classes DBIx::Class du schéma covoiturage
my $schema = schema('covoiturage');

prefix '/admin';

get '/albi/lieu/liste/' => sub {
	firewall::isAdmin();
	my @listeLieux = $schema->resultset('LieuAlbi')->search({})->all();
	
	template 'lieuAlbi/liste',  {
		lieux => \@listeLieux,
		url_validation => uri_for('/admin/albi/lieu/liste/'),
	}
};

post '/albi/lieu/liste/' => sub  {
	validation::validerLieuAlbi();
};

get '/albi/lieu/modifier/:id/' => sub {
	firewall::isAdmin();
	my $id = param('id');
	my $lieu = $schema->resultset('LieuAlbi')->find($id);
		
	template 'lieuAlbi/modifier',  {
		lieu => $lieu,
		url_validation => uri_for("/admin/albi/lieu/modifier/$id/"),
	}
};

post '/albi/lieu/modifier/:id/' => sub {
	firewall::isAdmin();
	validation::validerLieuAlbi();
};

get '/albi/lieu/supprimer/:id/' => sub {
	firewall::isAdmin();
	my $id = param('id');
	my $lieu = $schema->resultset('LieuAlbi')->find($id);
	
	template 'lieuAlbi/supprimer',  {
		lieu => $lieu,
		url_validation => uri_for("/admin/albi/lieu/supprimer/$id/"),
	}
};

post '/albi/lieu/supprimer/:id/' => sub {
	firewall::isAdmin();
	my $id = param('id');
	my $lieu = $schema->resultset('LieuAlbi')->find($id);
	$lieu->delete();
	my $route = "/admin/albi/lieu/liste/";
	redirect $route;
};


get '/database/clean/' => sub {
	firewall::isAdmin();
	my $where->{'date_depart'} = { '<=', 'date_add(curdate(), interval -1 month)'};
	my @annoncesAlbi = $schema->resultset('CovoiturageAlbi')->search($where);
	my @annoncesFrance = $schema->resultset('CovoiturageAlbi')->search($where);
	
	for my $annonce (@annoncesAlbi){
		$annonce->delete();
	}
	
	for my $annonce (@annoncesFrance){
		#messages::information(dumper_html($annonce->date_depart));
		$annonce->delete();
	}
	
	messages::succes("Les annonces dont la date de départ est vieille de 3 mois ont été supprimés");
	forward '/accueil/';
};

get '/supprimer/:login/' => sub {
	firewall::isAdmin();
	my $login = param('login');
	my $utilisateur = $schema->resultset('Utilisateur')->find($login);
	
	if ($utilisateur->admin == 1) {
		$utilisateur->admin(0);
		$utilisateur->update();
		messages::succes("L'utilisateur n'est plus administrateur")
	}
	else {
		messages::danger("Cet utilisateur n'est pas administrateur")
	}
	# on déconnecte l'utilisateur pour que la variable de session soit remise à jour
	session user => "";
	forward '/utilisateur/liste/';
};
	
get '/ajouter/:login/' => sub {
	firewall::isAdmin();
	my $login = param('login');
	my $utilisateur = $schema->resultset('Utilisateur')->find($login);
	
	if ($utilisateur->admin == 0) {
		$utilisateur->admin(1);
		$utilisateur->update();
		messages::succes("L'utilisateur est désormais administrateur")
	}
	else {
		messages::danger("Cet utilisateur est déjà administrateur")
	}
	# on déconnecte l'utilisateur pour que la variable de session soit remise à jour
	session user => "";
	forward '/utilisateur/liste/';
};

return true;