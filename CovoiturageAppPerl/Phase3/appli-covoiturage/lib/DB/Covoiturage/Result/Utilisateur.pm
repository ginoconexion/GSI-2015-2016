use utf8;
package DB::Covoiturage::Result::Utilisateur;

# Created by DBIx::Class::Schema::Loader
# DO NOT MODIFY THE FIRST PART OF THIS FILE

=head1 NAME

DB::Covoiturage::Result::Utilisateur

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

=head1 TABLE: C<utilisateur>

=cut

__PACKAGE__->table("utilisateur");

=head1 ACCESSORS

=head2 id

  data_type: 'varchar'
  is_nullable: 0
  size: 50

=head2 nom

  data_type: 'varchar'
  is_nullable: 1
  size: 45

=head2 prenom

  data_type: 'varchar'
  is_nullable: 1
  size: 45

=head2 mail

  data_type: 'varchar'
  is_nullable: 1
  size: 45

=head2 telephone

  data_type: 'varchar'
  is_nullable: 1
  size: 45

=head2 type

  data_type: 'varchar'
  is_nullable: 1
  size: 45

=head2 admin

  data_type: 'integer'
  default_value: 0
  is_nullable: 1

=cut

__PACKAGE__->add_columns(
  "id",
  { data_type => "varchar", is_nullable => 0, size => 50 },
  "nom",
  { data_type => "varchar", is_nullable => 1, size => 45 },
  "prenom",
  { data_type => "varchar", is_nullable => 1, size => 45 },
  "mail",
  { data_type => "varchar", is_nullable => 1, size => 45 },
  "telephone",
  { data_type => "varchar", is_nullable => 1, size => 45 },
  "type",
  { data_type => "varchar", is_nullable => 1, size => 45 },
  "admin",
  { data_type => "integer", default_value => 0, is_nullable => 1 },
);

=head1 PRIMARY KEY

=over 4

=item * L</id>

=back

=cut

__PACKAGE__->set_primary_key("id");

=head1 RELATIONS

=head2 covoiturage_albis

Type: has_many

Related object: L<DB::Covoiturage::Result::CovoiturageAlbi>

=cut

__PACKAGE__->has_many(
  "covoiturage_albis",
  "DB::Covoiturage::Result::CovoiturageAlbi",
  { "foreign.id_utilisateur" => "self.id" },
  { cascade_copy => 0, cascade_delete => 0 },
);

=head2 covoiturage_frances

Type: has_many

Related object: L<DB::Covoiturage::Result::CovoiturageFrance>

=cut

__PACKAGE__->has_many(
  "covoiturage_frances",
  "DB::Covoiturage::Result::CovoiturageFrance",
  { "foreign.id_utilisateur" => "self.id" },
  { cascade_copy => 0, cascade_delete => 0 },
);

=head2 sauvegardes

Type: has_many

Related object: L<DB::Covoiturage::Result::Sauvegarde>

=cut

__PACKAGE__->has_many(
  "sauvegardes",
  "DB::Covoiturage::Result::Sauvegarde",
  { "foreign.id_utilisateur" => "self.id" },
  { cascade_copy => 0, cascade_delete => 0 },
);

=head2 id_covoiturages

Type: many_to_many

Composing rels: L</sauvegardes> -> id_covoiturage

=cut

__PACKAGE__->many_to_many("id_covoiturages", "sauvegardes", "id_covoiturage");


# Created by DBIx::Class::Schema::Loader v0.07043 @ 2016-02-03 09:56:23
# DO NOT MODIFY THIS OR ANYTHING ABOVE! md5sum:CbLRzr4uE5NiNa5q1yGutg

sub profil {
	return {
		optional => [qw(id prenom nom type telephone)],
		required => [qw(mail)],
		constraint_methods => {
			mail => qr/^[a-z0-9._-]+@[a-z0-9._-]{2,}\.[a-z]{2,4}$/,
			telephone => qr/^[0-9]{2}[0-9]{2}[0-9]{2}[0-9]{2}[0-9]{2}$/,
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
