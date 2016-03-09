package myapp;

use Dancer ':syntax';
use CGI::Carp qw(fatalsToBrowser);
use Dancer::Plugin::DBIC;
use Data::Dumper;
use Data::Dumper::HTML 'dumper_html';
use DateTime;
use DateTime::Format::Strptime;
use Data::FormValidator;
use Data::FormValidator::Constraints::DateTime qw(:all);
use POSIX qw/ceil/;

use firewall;
use messages;
use user;
use calcul;
use filter;
use validation;
use annonce;
use sauvegarde;
use admin;

prefix undef;

# ligne à décommenter si on n'execute pas l'appli avec un serveur standalone
Dancer::Request->new(env => \%ENV);

# --- chargement classes DBIx::Class du schéma covoiturage
my $schema = schema('covoiturage');

hook before => sub {
	
	# code qui doit être executé sur toute l'appli
	# si la variable de session user n'existe, on va chercher en base de donnée l'user de login -> request->user() donné l'ENV et LDAP
	# pour l'instant nous fixons la variable à "pgirault" pour executer l'application en standalone
	
	
	if (not session('user')) {
    	
    	# si via serveur apache
    	my $login = request->user();
    	
    	# si execution en standalone
    	#my $login = "mloisel";
    	
    	my $user = $schema->resultset('Utilisateur')->find($login);
    	
    	if (defined $user){
    		session user => $user;
    	}
    	else {
    		my $utilisateur = user::register($login);
    		# redirect ...
    		redirect "/utilisateur/profil/";
    	}
    }
};

# --- appelé juste avant la génération du HTML par le template
#   transmission de données complémentaires (messages, menus, ...) au template
hook before_template => sub {
	
    my $tokens = shift;
    $tokens->{uri_base} = uri_for('/');
    $tokens->{path_info} = request->path_info;
	$tokens->{login} = request->user();
	
	$tokens->{routes} = {
		'accueil' => '',
		'publier' => 'annonce/publier/',
		'rechercher' => {
			'albiToulouse' => 'annonce/rechercher/albi-toulouse/',
			'albi' => 'annonce/rechercher/albi/',
			'france' => 'annonce/rechercher/france/',
		}, 
	};
};

get '/' => sub {
    forward '/accueil/';
};

get '/erreur/' => sub {
	template 'template/error404';
};

get '/a-propos/' => sub {
	template 'a_propos';
};

any ['get', 'post'] => '/accueil/' => sub {
	my $user = session('user');
	my $donnees = ();
	if ($user->admin == 1){
		$donnees->{"nombre_annonce_albi"} = $schema->resultset('CovoiturageAlbi')->search()->count();
		$donnees->{"nombre_annonce_france"}= $schema->resultset('CovoiturageFrance')->search()->count();
		
		#my @rs = $schema->resultset('CovoiturageFrance')->search(undef, { select => { count => '*'}});
#		my @rs = $schema->resultset('CovoiturageFrance')->search(undef,
#			{ select => [
#      		'id_utilisateur',
#      		{ count => '*' },
#		    ]},
#		    { group_by => 'id_utilisateur'},
#			 );
#			 
#		for my $var (@rs){
#			messages::information(dumper_html($var));
#		}
#		
#		#my $sth = database->prepare("select max(nb) as nb_annonces, id_utilisateur from (select count(*) as nb, id_utilisateur from covoiturage_france group by id_utilisateur) as t;")
#		#$sth->execute();
#		#my $results = $sth->fetchrow_hashref;
#		#$donnees->{"utilisateur_le_plus_actif"} = ()
		
	};
	
    template 'home', $donnees;
};


# --- route par défaut: déclenchée si URL non reconnue
any qr{.*} => sub {
    status 'not_found';
    template 'route_non_trouvee', { path => request->path };
};

true;
