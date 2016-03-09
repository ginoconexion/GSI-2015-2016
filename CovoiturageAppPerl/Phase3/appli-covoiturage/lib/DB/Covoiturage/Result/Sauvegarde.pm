use utf8;
package DB::Covoiturage::Result::Sauvegarde;

# Created by DBIx::Class::Schema::Loader
# DO NOT MODIFY THE FIRST PART OF THIS FILE

=head1 NAME

DB::Covoiturage::Result::Sauvegarde

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

=head1 TABLE: C<sauvegarde>

=cut

__PACKAGE__->table("sauvegarde");

=head1 ACCESSORS

=head2 id_covoiturage

  data_type: 'integer'
  is_foreign_key: 1
  is_nullable: 0

=head2 id_utilisateur

  data_type: 'varchar'
  default_value: (empty string)
  is_foreign_key: 1
  is_nullable: 0
  size: 50

=cut

__PACKAGE__->add_columns(
  "id_covoiturage",
  { data_type => "integer", is_foreign_key => 1, is_nullable => 0 },
  "id_utilisateur",
  {
    data_type => "varchar",
    default_value => "",
    is_foreign_key => 1,
    is_nullable => 0,
    size => 50,
  },
);

=head1 PRIMARY KEY

=over 4

=item * L</id_covoiturage>

=item * L</id_utilisateur>

=back

=cut

__PACKAGE__->set_primary_key("id_covoiturage", "id_utilisateur");

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

=head2 id_utilisateur

Type: belongs_to

Related object: L<DB::Covoiturage::Result::Utilisateur>

=cut

__PACKAGE__->belongs_to(
  "id_utilisateur",
  "DB::Covoiturage::Result::Utilisateur",
  { id => "id_utilisateur" },
  { is_deferrable => 1, on_delete => "NO ACTION", on_update => "NO ACTION" },
);


# Created by DBIx::Class::Schema::Loader v0.07043 @ 2016-02-01 21:12:56
# DO NOT MODIFY THIS OR ANYTHING ABOVE! md5sum:qBYI+cS9aolnhBm2ZmPe4A

use Dancer::Plugin::DBIC;

sub profil {
	return {
		optional => [qw(id_covoiturage id_utilisateur)],
		required => [qw(id_covoiturage id_utilisateur)],
		constraint_methods => {
			id_utilisateur => verifier_existence('id_utilisateur', 'Utilisateur'),
			id_covoiturage => verifier_existence('id_covoiturage', 'CovoiturageFrance'),
		}
	}
}
	
sub verifier_existence {
	my ($nom, $table) = @_;
	return sub {
		my $dfv = shift;
		$dfv->name_this($nom);
		my $id = $dfv->get_current_constraint_value();
		
		my $schema = schema('covoiturage');
		my $objet = $schema->resultset($table)->find($id);
		return (defined $objet);
	}	
};

# You can replace this text with custom code or comments, and it will be preserved on regeneration
1;
