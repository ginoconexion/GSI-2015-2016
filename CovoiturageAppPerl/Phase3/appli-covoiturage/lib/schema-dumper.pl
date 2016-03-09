use strict;
use warnings;
use DBIx::Class::Schema::Loader qw/ make_schema_at /;

my $classe_base     = 'DB::Covoiturage';
my $repertoire_base = 'C:\Users\PGM\workspace\PerlCovoiturage\Phase3\appli-covoiturage\lib';

my $dsn = 'dbi:mysql:gsi16gn:sgbd-eleves.mines-albi.fr:3306';
#my $dsn = 'dbi:mysql:covoiturage:localhost';

my $user = 'gsi16gn';
#my $user = 'test';

my $password = 'gd9sy2';
#my $password = 'test';


make_schema_at(
	$classe_base,
	{
		relationships  => 1,
		components     => [qw/FromValidators InflateColumn::DateTime/],
		debug          => 1,
		dump_directory => $repertoire_base,
	},
	[ $dsn, $user, $password, ],
);

__END__
