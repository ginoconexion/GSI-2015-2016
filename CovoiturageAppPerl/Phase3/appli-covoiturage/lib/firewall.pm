package firewall;
use strict;
use warnings;
use Dancer ':syntax';

use Data::Dumper;

my $message = "Vous n'avez pas les droit pour accéder à cette page";

sub isAdmin {
	my $utilisateur = session('user');
	if ($utilisateur->admin == 0){
		redirectHome();
	}
};

sub isAuthor($) {
	my ($annonce) = @_;
	my $utilisateur = session('user');
	if ($annonce->id_utilisateur->id ne $utilisateur->id){
		redirectHome();
	}
}

sub isAuthorOrAdmin($){
	my ($annonce) = @_;
	my $utilisateur = session('user');
	
	if ($annonce->id_utilisateur->id ne $utilisateur->id && $utilisateur->admin == 0){
		redirectHome();
	}
}

sub redirectHome {
	messages::danger($message);
	forward '/accueil/';
}

return true;