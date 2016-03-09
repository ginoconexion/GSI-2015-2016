package sauvegarde;
use strict;
use warnings;
use Dancer ':syntax';

use Data::Dumper;
use Dancer::Plugin::DBIC;

# --- chargement classes DBIx::Class du schéma covoiturage
my $schema = schema('covoiturage');

prefix '/sauvegarder';


get '/liste/' => sub { 
	
	my $user = session 'user';
	my $conditions = {
		join => [ qw/ id_utilisateur id_covoiturage /],
	};
	my @sauvegardes = $schema->resultset('Sauvegarde')->search({'id_utilisateur.id'=> $user->id}, $conditions);

	template 'annonce/rechercher/listeAnnonces.tt',  {
		sauvegardes => \@sauvegardes,
		type => "listeSauvegardes",
	}
};

# route qui enregistre une nouvelle sauvegarde
get '/:id_annonce/' => sub {
	
	my $id_annonce = param('id_annonce');
	my $user = session 'user';
	my $sauvegarde = $schema->resultset('Sauvegarde')->find({'id_utilisateur' => $user->id, 'id_covoiturage' => $id_annonce});
	
	if (defined $sauvegarde){
		messages::danger("Annonce déjà sauvegardée");
		forward '/accueil/';
	}
	else {
		my $annonce = $schema->resultset('CovoiturageFrance')->find($id_annonce);
		my $profil = $schema->class('Sauvegarde')->profil;
		
		my $params = ();
	
		$params->{'id_utilisateur'} = $user->id;
		$params->{'id_covoiturage'} = $annonce->id;
			
		my ($results) = Data::FormValidator->check($params, $profil);
		warning Dumper($user->id);
		warning Dumper($results);
		my $sauvegarde = $schema->resultset('Sauvegarde')->create_from_fv($results);
		messages::succes("Annonce sauvegardée avec succes");
		
	}
	forward '/sauvegarder/liste/';
};


get '/supprimer/:id_annonce/' => sub {	
	my $user = session 'user';
	my $id_annonce = param('id_annonce');
	my $annonce = $schema->resultset('Sauvegarde')->find({'id_utilisateur'=> $user->id, 'id_covoiturage'=> $id_annonce});	
	$annonce->delete();
	messages::succes("Sauvegarde supprimée");
	forward '/sauvegarder/liste/';
	
};