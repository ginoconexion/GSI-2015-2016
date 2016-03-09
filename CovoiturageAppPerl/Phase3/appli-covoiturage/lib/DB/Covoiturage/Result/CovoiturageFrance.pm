use utf8;
package DB::Covoiturage::Result::CovoiturageFrance;

# Created by DBIx::Class::Schema::Loader
# DO NOT MODIFY THE FIRST PART OF THIS FILE

=head1 NAME

DB::Covoiturage::Result::CovoiturageFrance

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

=head1 TABLE: C<covoiturage_france>

=cut

__PACKAGE__->table("covoiturage_france");

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

=head2 prix

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

=head2 id_taille_baguage

  data_type: 'integer'
  is_foreign_key: 1
  is_nullable: 1

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
  "prix",
  { data_type => "integer", is_nullable => 1 },
  "description",
  { data_type => "longtext", is_nullable => 1 },
  "id_lieu_depart",
  { data_type => "integer", is_foreign_key => 1, is_nullable => 1 },
  "id_lieu_destination",
  { data_type => "integer", is_foreign_key => 1, is_nullable => 1 },
  "id_utilisateur",
  { data_type => "varchar", is_foreign_key => 1, is_nullable => 1, size => 50 },
  "id_taille_baguage",
  { data_type => "integer", is_foreign_key => 1, is_nullable => 1 },
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

Related object: L<DB::Covoiturage::Result::LieuFrance>

=cut

__PACKAGE__->belongs_to(
  "id_lieu_depart",
  "DB::Covoiturage::Result::LieuFrance",
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

Related object: L<DB::Covoiturage::Result::LieuFrance>

=cut

__PACKAGE__->belongs_to(
  "id_lieu_destination",
  "DB::Covoiturage::Result::LieuFrance",
  { id => "id_lieu_destination" },
  {
    is_deferrable => 1,
    join_type     => "LEFT",
    on_delete     => "NO ACTION",
    on_update     => "NO ACTION",
  },
);

=head2 id_taille_baguage

Type: belongs_to

Related object: L<DB::Covoiturage::Result::TailleBagage>

=cut

__PACKAGE__->belongs_to(
  "id_taille_baguage",
  "DB::Covoiturage::Result::TailleBagage",
  { id => "id_taille_baguage" },
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

=head2 passe_pars

Type: has_many

Related object: L<DB::Covoiturage::Result::PassePar>

=cut

__PACKAGE__->has_many(
  "passe_pars",
  "DB::Covoiturage::Result::PassePar",
  { "foreign.id_covoiturage" => "self.id" },
  { cascade_copy => 0, cascade_delete => 0 },
);

=head2 sauvegardes

Type: has_many

Related object: L<DB::Covoiturage::Result::Sauvegarde>

=cut

__PACKAGE__->has_many(
  "sauvegardes",
  "DB::Covoiturage::Result::Sauvegarde",
  { "foreign.id_covoiturage" => "self.id" },
  { cascade_copy => 0, cascade_delete => 0 },
);

=head2 id_lieus

Type: many_to_many

Composing rels: L</passe_pars> -> id_lieu

=cut

__PACKAGE__->many_to_many("id_lieus", "passe_pars", "id_lieu");

=head2 id_utilisateurs

Type: many_to_many

Composing rels: L</sauvegardes> -> id_utilisateur

=cut

__PACKAGE__->many_to_many("id_utilisateurs", "sauvegardes", "id_utilisateur");


# Created by DBIx::Class::Schema::Loader v0.07043 @ 2016-02-01 21:12:56
# DO NOT MODIFY THIS OR ANYTHING ABOVE! md5sum:iO6/VQLIWIwmN2lcpOnjvw


use Data::FormValidator;
use Data::FormValidator::Constraints::DateTime qw(:all);
use Dancer::Plugin::DBIC;

sub profil {
	return {
		optional => [qw(prix description date_publication date_depart id_lieu_depart id_lieu_destination id_utilisateur id_type_covoiturage)],
		required => [qw(date heure nombre_place id_taille_baguage depart.nom destination.nom)],
		constraint_methods => {
			date => after_today('%d-%m-%Y'),
			heure => qr/^[0-9]{2}[:][0-9]{2}$/,
			prix => qr/^\d+$/,
			nombre_place => qr/^\d+$/,
			id_taille_baguage => verifier_existence('id_taille_baguage', 'TailleBagage'),
			id_type_covoiturage => qr/^\d+$/,
			'destination.nom' => verifier_lieux_different({ fields => [qw/depart.nom/]})
		},
		msgs => {
			format => '%s',
			prefix => '',
			missing => 'Valeur obligatoire',
			invalid => 'Valeur incorrecte',
			constraints => {
				date => 'Le format de la date n\'est pas valide',
				heure => 'Le format de l\'heure n\'est pas valide',
				prix => 'Le format du prix n\'est pas valide',
				nombre_place => 'Le nombre de place n\'est pas valide',
				id_taille_baguage => 'La taille des baguages n\'est pas valide',
				'destination.nom' => "La destination doit être différente du lieu de départ",
			}
		}
	}
}

sub profil_date {
	return {
		required => [qw(date)],
		constraint_methods => {
			date => after_today('%d-%m-%Y'),
		},
		msgs => {
			format => '%s',
			prefix => '',
			missing => 'Valeur obligatoire',
			invalid => 'Valeur incorrecte',
			constraints => {
				date => 'Le format de la date n\'est pas valide',
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
};

sub verifier_lieux_different {
	my ($attrs) = @_;
	my ($champ_nom_lieu_depart) = @{ $attrs->{fields} } if $attrs->{fields};
	return sub  {
		my $dfv = shift;
		$dfv->name_this('destination.nom');
		my $nom_lieu_destination = $dfv->get_current_constraint_value();
		my $schema = schema('covoiturage');
		my $data = $dfv->get_filtered_data;
		my $nom_lieu_depart = $data->{$champ_nom_lieu_depart};
			return ($nom_lieu_destination ne $nom_lieu_depart);
	}
}


# You can replace this text with custom code or comments, and it will be preserved on regeneration
1;
