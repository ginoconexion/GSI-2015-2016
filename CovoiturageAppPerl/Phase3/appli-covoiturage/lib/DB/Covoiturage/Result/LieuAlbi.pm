use utf8;
package DB::Covoiturage::Result::LieuAlbi;

# Created by DBIx::Class::Schema::Loader
# DO NOT MODIFY THE FIRST PART OF THIS FILE

=head1 NAME

DB::Covoiturage::Result::LieuAlbi

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

=head1 TABLE: C<lieu_albi>

=cut

__PACKAGE__->table("lieu_albi");

=head1 ACCESSORS

=head2 id

  data_type: 'integer'
  is_auto_increment: 1
  is_nullable: 0

=head2 nom

  data_type: 'varchar'
  is_nullable: 1
  size: 100

=cut

__PACKAGE__->add_columns(
  "id",
  { data_type => "integer", is_auto_increment => 1, is_nullable => 0 },
  "nom",
  { data_type => "varchar", is_nullable => 1, size => 100 },
);

=head1 PRIMARY KEY

=over 4

=item * L</id>

=back

=cut

__PACKAGE__->set_primary_key("id");

=head1 RELATIONS

=head2 covoiturage_albi_id_lieu_destinations

Type: has_many

Related object: L<DB::Covoiturage::Result::CovoiturageAlbi>

=cut

__PACKAGE__->has_many(
  "covoiturage_albi_id_lieu_destinations",
  "DB::Covoiturage::Result::CovoiturageAlbi",
  { "foreign.id_lieu_destination" => "self.id" },
  { cascade_copy => 0, cascade_delete => 0 },
);

=head2 covoiturage_albi_id_lieus_depart

Type: has_many

Related object: L<DB::Covoiturage::Result::CovoiturageAlbi>

=cut

__PACKAGE__->has_many(
  "covoiturage_albi_id_lieus_depart",
  "DB::Covoiturage::Result::CovoiturageAlbi",
  { "foreign.id_lieu_depart" => "self.id" },
  { cascade_copy => 0, cascade_delete => 0 },
);


# Created by DBIx::Class::Schema::Loader v0.07043 @ 2016-01-21 22:42:44
# DO NOT MODIFY THIS OR ANYTHING ABOVE! md5sum:P2MkBDNdYtpGPCP38yG/5A

sub profil {
	return {
		required => [qw( nom )],
		msgs => {
			format => '%s',
			prefix => '',
			missing => 'Valeur obligatoire',
			invalid => 'Valeur incorrecte',
		}
	}
}

# You can replace this text with custom code or comments, and it will be preserved on regeneration
1;
