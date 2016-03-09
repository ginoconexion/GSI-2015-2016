use utf8;
package DB::Covoiturage::Result::PassePar;

# Created by DBIx::Class::Schema::Loader
# DO NOT MODIFY THE FIRST PART OF THIS FILE

=head1 NAME

DB::Covoiturage::Result::PassePar

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

=head1 TABLE: C<passe_par>

=cut

__PACKAGE__->table("passe_par");

=head1 ACCESSORS

=head2 id_covoiturage

  data_type: 'integer'
  is_foreign_key: 1
  is_nullable: 0

=head2 id_lieu

  data_type: 'integer'
  is_foreign_key: 1
  is_nullable: 0

=cut

__PACKAGE__->add_columns(
  "id_covoiturage",
  { data_type => "integer", is_foreign_key => 1, is_nullable => 0 },
  "id_lieu",
  { data_type => "integer", is_foreign_key => 1, is_nullable => 0 },
);

=head1 PRIMARY KEY

=over 4

=item * L</id_covoiturage>

=item * L</id_lieu>

=back

=cut

__PACKAGE__->set_primary_key("id_covoiturage", "id_lieu");

=head1 RELATIONS

=head2 id_covoiturage

Type: belongs_to

Related object: L<DB::Covoiturage::Result::CovoiturageFrance>

=cut

__PACKAGE__->belongs_to(
  "id_covoiturage",
  "DB::Covoiturage::Result::CovoiturageFrance",
  { id => "id_covoiturage" },
  { is_deferrable => 1, on_delete => "NO ACTION", on_update => "NO ACTION" },
);

=head2 id_lieu

Type: belongs_to

Related object: L<DB::Covoiturage::Result::LieuFrance>

=cut

__PACKAGE__->belongs_to(
  "id_lieu",
  "DB::Covoiturage::Result::LieuFrance",
  { id => "id_lieu" },
  { is_deferrable => 1, on_delete => "NO ACTION", on_update => "NO ACTION" },
);


# Created by DBIx::Class::Schema::Loader v0.07043 @ 2016-01-27 14:39:16
# DO NOT MODIFY THIS OR ANYTHING ABOVE! md5sum:oL6l+XrUl2m+Oii2SsjFXg



sub profil {
	return {
		required => [qw(id_covoiturage id_lieu)],
		constraint_methods => {
			id_covoiturage => qr/^[0-9]+$/,
			id_lieu => qr/^[0-9]+$/,
		},
		msgs => {
			format => '%s',
			prefix => '',
			missing => 'Valeur obligatoire',
			invalid => 'Valeur incorrecte',
			constraints => {
			}
		}
	}
}

# You can replace this text with custom code or comments, and it will be preserved on regeneration
1;
