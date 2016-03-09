package messages;
use strict;
use warnings;
use Dancer ':syntax';

# messages::information("un message info");     # bleu
# messages::avertissement("un message warning");# jaune
# messages::danger("un message danger");        # rouge
# messages::succes("un message success");       # vert

hook before => sub {
    var messages => [] if not defined var 'messages';
};

sub information {
    my $message = shift;
    var messages => [] if not defined var 'messages';
    push @{var 'messages'}, { classe => 'info', texte => $message };
}

sub succes {
    my $message = shift;
    var messages => [] if not defined var 'messages';
    push @{var 'messages'}, { classe => 'success', texte => $message };
}

sub avertissement {
    my $message = shift;
    var messages => [] if not defined var 'messages';
    push @{var 'messages'}, { classe => 'warning', texte => $message };
}

sub danger {
    my $message = shift;
    var messages => [] if not defined var 'messages';
    push @{var 'messages'}, { classe => 'danger', texte => $message };
}

hook before_template => sub {
    my $tokens = shift;
    $tokens->{messages} = var 'messages';
};

true;

__END__
