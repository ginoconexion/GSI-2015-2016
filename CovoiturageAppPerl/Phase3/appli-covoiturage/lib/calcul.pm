package calcul;
use POSIX;
use Math::Trig;
use Math::Round;

sub calculerDistance($$) {
	my ($lieuDepart, $lieuDestination) = @_;
	
	my $latDepart = $lieuDepart->latitude;
	my $longDepart = $lieuDepart->longitude;
	my $latDestination = $lieuDestination->latitude;
	my $longDestination = $lieuDestination->longitude;
	
	
	my $e=(3.14159265358979*$latDepart/180); 
	my $f=(3.14159265358979*$longDepart/180); 
	my $g=(3.14159265358979*$latDestination/180);
	my $h=(3.14159265358979*$longDestination/180);
	my $i=(cos($e)*cos($g)*cos($f)*cos($h)+cos($e)*sin($f)*cos($g)*sin($h)+sin($e)*sin($g));
	my $j= acos($i);
	
	# valeur en km
	$number = 6371*$j;
	$number = nearest(0.1,$number);
	return $number;
}

return true;