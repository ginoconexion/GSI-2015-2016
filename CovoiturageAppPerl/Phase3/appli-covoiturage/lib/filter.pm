package filter;
use strict;
use warnings;
use Dancer ':syntax';
use Dancer::Plugin::DBIC;
use Data::Dumper;
use Data::Dumper::HTML 'dumper_html';

# --- chargement classes DBIx::Class du schéma covoiturage
my $schema = schema('covoiturage');

sub filterByDepartAndDestination($$$$){
	my ($lieuDepart, $lieuDestination, $donnees, $date) = @_;
	$donnees->{"annonce"}->{"id_lieu_depart"} = $lieuDepart;
	$donnees->{"annonce"}->{"id_lieu_destination"} = $lieuDestination;
	
	my @lieux = $schema->resultset('LieuFrance')->search()->all();
	my @lieuxDepart = ();
	my @annonces = ();
	
	for my $lieu (@lieux){
		my $distance = calcul::calculerDistance($lieuDepart, $lieu);
		if ($distance < 50){
			push @lieuxDepart, $lieu;
		}
	}
	my @annoncesDepart = ();
	for my $lieu (@lieuxDepart){
		# on pousse les covoiturages liés
		for my $annonce ($lieu->covoiturage_france_id_lieus_depart){
			push @annoncesDepart, $annonce;
		}
		
		# on pousse les étapes
		for my $annonce ($lieu->id_covoiturages) {
			push @annoncesDepart, $annonce;
		}
	}
	for my $annonce (@annoncesDepart){
		# vérification si le lieu de destination de l'annonce est proche du lieu souhaité
		my $lieu = $annonce->id_lieu_destination;
		my $distance = calcul::calculerDistance($lieuDestination, $lieu);
		
		if ($distance < 30){
			push @annonces, $annonce;
		}
		
		# pour chacune des étapes du covoiturage je verifie si l'une est proche du lieu de destination
		for my $etape ($annonce->id_lieus) {
			$distance = calcul::calculerDistance($lieuDestination, $etape);
			if ($distance < 30){
				push @annonces, $annonce;
			}
		}
	}
	# si une date a été fournie
	if (defined $date && $date ne ""){
		@annonces = filterByDate($date, @annonces);
	}
	return @annonces;
}

sub filterByLieuDepart($$$) {
	
	my ($lieuDepart, $donnees, $date ) = @_;
	my @annonces = ();
	my @lieux = $schema->resultset('LieuFrance')->search()->all();
	
	my @lieuxDepart = ();
	for my $lieu (@lieux) {
		my $distance = calcul::calculerDistance($lieuDepart, $lieu);
		if ($distance < 30){
			push @lieuxDepart, $lieu;
		}
	}
	for my $lieu (@lieuxDepart) {
		# on pousse les covoiturages liés
		for my $annonce ($lieu->covoiturage_france_id_lieus_depart){
			push @annonces, $annonce;
		}
		# on pousse les étapes
		for my $annonce ($lieu->id_covoiturages) {
			push @annonces, $annonce;
		}
	}
	
	# si une date a été fournie
	if (defined $date && $date ne ""){
		@annonces = filterByDate($date, @annonces);
	}
	$donnees->{"annonce"}->{"id_lieu_depart"} = $lieuDepart;
	return @annonces;
}
sub filterByLieuDestination($$$) {
	
	my ($lieuDestination, $donnees, $date ) = @_;
	my @annonces = ();
	my @lieux = $schema->resultset('LieuFrance')->search()->all();
	
	my @lieuxDestination = ();
	for my $lieu (@lieux) {
		my $distance = calcul::calculerDistance($lieuDestination, $lieu);
		if ($distance < 30){
			push @lieuxDestination, $lieu;
		}
	}
	for my $lieu (@lieuxDestination) {
		# on pousse les covoiturages liés
		for my $annonce ($lieu->covoiturage_france_id_lieu_destinations){
			push @annonces, $annonce;
		}
		# on pousse les étapes
		for my $annonce ($lieu->id_covoiturages) {
			push @annonces, $annonce;
		}
	}
	
	# si une date a été fournie
	if (defined $date && $date ne ""){
		@annonces = filterByDate($date, @annonces);
	}
	$donnees->{"annonce"}->{"id_lieu_destination"} = $lieuDestination;
	return @annonces;
}

sub filterByDate(){
	my ($date, @annonces1) = @_;
	my @annonces = ();
	for my $annonce (@annonces1){
		my $dateDepart = $annonce->date_depart->strftime('%d-%m-%Y');
		if ($dateDepart eq $date){
			push @annonces, $annonce;
		}
	}
	return @annonces;
}


return true;