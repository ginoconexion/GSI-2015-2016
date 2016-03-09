use utf8;
package DB::Covoiturage::Result::LieuFrance;

# Created by DBIx::Class::Schema::Loader
# DO NOT MODIFY THE FIRST PART OF THIS FILE

=head1 NAME

DB::Covoiturage::Result::LieuFrance

=cut

use strict;
use warnings;

use base 'DBIx::Class::Core';

=head1 COMPONENTS LOADED

=over 4

=item * L<DBIx::Class::FromValidators>

=item * L<DBIx::Class::InflateColumn::DateTime>

=back

=cut

__PACKAGE__->load_components("FromValidators", "InflateColumn::DateTime");

=head1 TABLE: C<lieu_france>

=cut

__PACKAGE__->table("lieu_france");

=head1 ACCESSORS

=head2 id

  data_type: 'integer'
  is_auto_increment: 1
  is_nullable: 0

=head2 nom

  data_type: 'varchar'
  is_nullable: 1
  size: 50

=head2 latitude

  data_type: 'float'
  is_nullable: 1

=head2 longitude

  data_type: 'float'
  is_nullable: 1

=cut

__PACKAGE__->add_columns(
  "id",
  { data_type => "integer", is_auto_increment => 1, is_nullable => 0 },
  "nom",
  { data_type => "varchar", is_nullable => 1, size => 50 },
  "latitude",
  { data_type => "float", is_nullable => 1 },
  "longitude",
  { data_type => "float", is_nullable => 1 },
);

=head1 PRIMARY KEY

=over 4

=item * L</id>

=back

=cut

__PACKAGE__->set_primary_key("id");

=head1 RELATIONS

=head2 covoiturage_france_id_lieu_destinations

Type: has_many

Related object: L<DB::Covoiturage::Result::CovoiturageFrance>

=cut

__PACKAGE__->has_many(
  "covoiturage_france_id_lieu_destinations",
  "DB::Covoiturage::Result::CovoiturageFrance",
  { "foreign.id_lieu_destination" => "self.id" },
  { cascade_copy => 0, cascade_delete => 0 },
);

=head2 covoiturage_france_id_lieus_depart

Type: has_many

Related object: L<DB::Covoiturage::Result::CovoiturageFrance>

=cut

__PACKAGE__->has_many(
  "covoiturage_france_id_lieus_depart",
  "DB::Covoiturage::Result::CovoiturageFrance",
  { "foreign.id_lieu_depart" => "self.id" },
  { cascade_copy => 0, cascade_delete => 0 },
);

=head2 passe_pars

Type: has_many

Related object: L<DB::Covoiturage::Result::PassePar>

=cut

__PACKAGE__->has_many(
  "passe_pars",
  "DB::Covoiturage::Result::PassePar",
  { "foreign.id_lieu" => "self.id" },
  { cascade_copy => 0, cascade_delete => 0 },
);

=head2 id_covoiturages

Type: many_to_many

Composing rels: L</passe_pars> -> id_covoiturage

=cut

__PACKAGE__->many_to_many("id_covoiturages", "passe_pars", "id_covoiturage");


# Created by DBIx::Class::Schema::Loader v0.07043 @ 2016-01-27 14:39:16
# DO NOT MODIFY THIS OR ANYTHING ABOVE! md5sum:Xfdqv6RkkWHpZFDm6G6nHQ

sub profil {
	return {
		required => [qw(nom latitude longitude )],
		constraint_methods => {
			latitude => qr/^([-]?[0-9]*\.[0-9]+|[0-9]+)+$/,
			longitude => qr/^([-]?[0-9]*\.[0-9]+|[0-9]+)+$/,
		},
		msgs => {
			format => '%s',
			prefix => '',
			missing => 'Valeur obligatoire',
			invalid => 'Valeur incorrecte',
			constraints => {
				latitude => 'La latitude n\' a pas le bon format',
				longitude => 'La longitude n\' a pas le bon format',
			}
		}
	}
}

# You can replace this text with custom code or comments, and it will be preserved on regeneration
1;
