use utf8;
package DB::Covoiturage::Result::CovoiturageAlbi;

# Created by DBIx::Class::Schema::Loader
# DO NOT MODIFY THE FIRST PART OF THIS FILE

=head1 NAME

DB::Covoiturage::Result::CovoiturageAlbi

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

=head1 TABLE: C<covoiturage_albi>

=cut

__PACKAGE__->table("covoiturage_albi");

=head1 ACCESSORS

=head2 id

  data_type: 'integer'
  is_auto_increment: 1
  is_nullable: 0

=head2 date_publication

  data_type: 'datetime'
  datetime_undef_if_invalid: 1
  is_nullable: 1

=head2 date_depart

  data_type: 'datetime'
  datetime_undef_if_invalid: 1
  is_nullable: 1

=head2 nombre_place

  data_type: 'integer'
  is_nullable: 1

=head2 description

  data_type: 'longtext'
  is_nullable: 1

=head2 id_lieu_depart

  data_type: 'integer'
  is_foreign_key: 1
  is_nullable: 1

=head2 id_lieu_destination

  data_type: 'integer'
  is_foreign_key: 1
  is_nullable: 1

=head2 id_utilisateur

  data_type: 'varchar'
  is_foreign_key: 1
  is_nullable: 1
  size: 50

=head2 id_type_covoiturage

  data_type: 'integer'
  is_foreign_key: 1
  is_nullable: 1

=cut

__PACKAGE__->add_columns(
  "id",
  { data_type => "integer", is_auto_increment => 1, is_nullable => 0 },
  "date_publication",
  {
    data_type => "datetime",
    datetime_undef_if_invalid => 1,
    is_nullable => 1,
  },
  "date_depart",
  {
    data_type => "datetime",
    datetime_undef_if_invalid => 1,
    is_nullable => 1,
  },
  "nombre_place",
  { data_type => "integer", is_nullable => 1 },
  "description",
  { data_type => "longtext", is_nullable => 1 },
  "id_lieu_depart",
  { data_type => "integer", is_foreign_key => 1, is_nullable => 1 },
  "id_lieu_destination",
  { data_type => "integer", is_foreign_key => 1, is_nullable => 1 },
  "id_utilisateur",
  { data_type => "varchar", is_foreign_key => 1, is_nullable => 1, size => 50 },
  "id_type_covoiturage",
  { data_type => "integer", is_foreign_key => 1, is_nullable => 1 },
);

=head1 PRIMARY KEY

=over 4

=item * L</id>

=back

=cut

__PACKAGE__->set_primary_key("id");

=head1 RELATIONS

=head2 id_lieu_depart

Type: belongs_to

Related object: L<DB::Covoiturage::Result::LieuAlbi>

=cut

__PACKAGE__->belongs_to(
  "id_lieu_depart",
  "DB::Covoiturage::Result::LieuAlbi",
  { id => "id_lieu_depart" },
  {
    is_deferrable => 1,
    join_type     => "LEFT",
    on_delete     => "NO ACTION",
    on_update     => "NO ACTION",
  },
);

=head2 id_lieu_destination

Type: belongs_to

Related object: L<DB::Covoiturage::Result::LieuAlbi>

=cut

__PACKAGE__->belongs_to(
  "id_lieu_destination",
  "DB::Covoiturage::Result::LieuAlbi",
  { id => "id_lieu_destination" },
  {
    is_deferrable => 1,
    join_type     => "LEFT",
    on_delete     => "NO ACTION",
    on_update     => "NO ACTION",
  },
);

=head2 id_type_covoiturage

Type: belongs_to

Related object: L<DB::Covoiturage::Result::Type>

=cut

__PACKAGE__->belongs_to(
  "id_type_covoiturage",
  "DB::Covoiturage::Result::Type",
  { id => "id_type_covoiturage" },
  {
    is_deferrable => 1,
    join_type     => "LEFT",
    on_delete     => "NO ACTION",
    on_update     => "NO ACTION",
  },
);

=head2 id_utilisateur

Type: belongs_to

Related object: L<DB::Covoiturage::Result::Utilisateur>

=cut

__PACKAGE__->belongs_to(
  "id_utilisateur",
  "DB::Covoiturage::Result::Utilisateur",
  { id => "id_utilisateur" },
  {
    is_deferrable => 1,
    join_type     => "LEFT",
    on_delete     => "NO ACTION",
    on_update     => "NO ACTION",
  },
);


# Created by DBIx::Class::Schema::Loader v0.07043 @ 2016-01-21 22:42:44
# DO NOT MODIFY THIS OR ANYTHING ABOVE! md5sum:Ki3qRhuNSTrCfh/pQ6Ifqg


use Data::FormValidator;
use Data::FormValidator::Constraints::DateTime qw(:all);
use Dancer::Plugin::DBIC;

sub profil {
	return {
		optional => [qw(description date_publication date_depart id_utilisateur id_type_covoiturage)],
		required => [qw(date heure nombre_place id_lieu_depart id_lieu_destination)],
		constraint_methods => {
			date => after_today('%d-%m-%Y'),
			heure => qr/^[0-9]{2}[:][0-9]{2}$/,
			nombre_place => qr/^\d+$/,
			id_type_covoiturage => verifier_existence('id_type_covoiturage', 'Type'),
			id_lieu_depart => verifier_existence('id_lieu_depart', 'LieuAlbi'),
			id_lieu_destination => verifier_lieux_different({ fields => [qw/id_lieu_depart/]}),
		},
		msgs => {
			format => '%s',
			prefix => '',
			missing => 'Valeur obligatoire',
			invalid => 'Valeur incorrecte',
			constraints => {
				date => 'Le format de la date n\'est pas valide',
				heure => 'Le format de l\'heure n\'est pas valide',
				nombre_place => 'Le nombre de place n\'est pas valide',
			}
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
}

sub verifier_lieux_different {
	my ($attrs) = @_;
	my ($champ_lieu_depart) = @{ $attrs->{fields} } if $attrs->{fields};
	return sub  {
		my $dfv = shift;
		$dfv->name_this('id_lieu_destination');
		
		my $id_lieu_destination = $dfv->get_current_constraint_value();
		my $schema = schema('covoiturage');
		my $lieu_destination = $schema->resultset('LieuAlbi')->find($id_lieu_destination);
		
		if (defined $lieu_destination){
			my $data = $dfv->get_filtered_data;
			my $id_lieu_depart = $data->{$champ_lieu_depart};
			return ($id_lieu_depart != $id_lieu_destination);
		}
		else {
			return 0;
		}
	}
}

# You can replace this text with custom code or comments, and it will be preserved on regeneration
1;
