package user;
use strict;
use warnings;
use Dancer ':syntax';

use Net::LDAP;
use Data::Dumper;
use Data::Dumper::HTML 'dumper_html';
use Unicode::String qw(utf8 latin1 utf16);
use Dancer::Plugin::DBIC;

# --- chargement classes DBIx::Class du schéma covoiturage
my $schema = schema('covoiturage');

prefix '/utilisateur';

get '/deconnexion/' => sub {
	# à voir comment faire
	session user => "";
#	session->delete('user');
	redirect '/accueil/';
};

get '/profil/' => sub {
	my $user = session('user');
	my $id = $user->id;
	$user = $schema->resultset('Utilisateur')->find($id);
	
	template 'utilisateur/vue', {
		'utilisateur' => $user,
		'url_validation' => uri_for("/utilisateur/profil/"),
	};
};

post '/profil/' => sub {
	my $params = request->params;
	my $profil = $schema->class('Utilisateur')->profil;
	my ($results) = Data::FormValidator->check($params, $profil);
	my $user = session('user');
	
	if ($results->has_missing or $results->has_invalid) {
		my $reaffichage = request->Vars;
		my $msg = $results->msgs;
		my $donnees = {
			utilisateur => $reaffichage,
			url_validation => uri_for("/utilisateur/profil/"),
			msgs => $msg,
		};
		$donnees->{"utilisateur"}->{"type"} = $user->type;
		template 'utilisateur/vue', $donnees;
	}
	else {
		my $id = $user->id;
		$user = $schema->resultset('Utilisateur')->find($id);
		$user->update_from_fv($results);
		messages::succes("Profil modifié avec succes");
		# redirige en requête post
		forward '/accueil/';
	}
};


get '/liste/' => sub {
	my @utilisateurs = $schema->resultset('Utilisateur')->search()->all();
	template 'utilisateur/liste', { 'utilisateurs' => \@utilisateurs };
};


sub register($) {
	my $login = shift;
	warning Dumper($login);
	
	my $ldap = Net::LDAP->new("ldap.mines-albi.fr");
	$ldap->bind();
	my $msg = $ldap->search(
	base => "ou=People,dc=enstimac,dc=fr",
	filter => "(uid=$login)"
	);
	
	
	my @entries = $msg->entries;
	my $first = shift(@entries);
	my $prenom = $first->get_value('givenName');
	my $nom = $first->get_value('sn');
	my $mail = $first->get_value('mail');
	# valeur pouvant être nul si l'élève est un enseignant ou autre dans ce cas l'utilisateur pourra remplir lui même cette valeur
	my $type = $first->get_value('statutEleve');
	
	$prenom = ucfirst lc $prenom;
	$nom = ucfirst lc $nom;
	
	my $params = {
		'id' => $login,
		'prenom' => $prenom,
		'nom' => $nom,
		'mail' => $mail,
		'telephone' => "",
		'type' => $type,
	};
	
	my $profil = $schema->class('Utilisateur')->profil;
	my $results = Data::FormValidator->check($params, $profil);
	messages::information(dumper_html($results));
	my $utilisateur = $schema->resultset('Utilisateur')->create_from_fv($results);
	return $utilisateur;
};

return 1;