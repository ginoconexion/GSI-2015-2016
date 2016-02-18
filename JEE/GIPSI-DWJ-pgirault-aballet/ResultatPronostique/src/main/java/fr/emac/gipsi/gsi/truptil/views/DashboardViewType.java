package fr.emac.gipsi.gsi.truptil.views;

import com.vaadin.navigator.View;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Resource;

public enum DashboardViewType {
	Match1("resultat_poule_A", MatchView.class, FontAwesome.HOME,1, true), 
	Match2("resultat_poule_B", MatchView.class, FontAwesome.HOME,2, true), 
	Match3("resultat_poule_C", MatchView.class, FontAwesome.HOME,3, true), 
	Match4("resultat_poule_D", MatchView.class, FontAwesome.HOME,4, true), 
	Match5("resultat_poule_E", MatchView.class, FontAwesome.HOME,5, true), 
	Match6("resultat_poule_F", MatchView.class, FontAwesome.ARCHIVE,6, true), 
	User("resultat_joueur", JoueurView.class, FontAwesome.BELL,0, true),
//	PariA("pari_joueur_A", PariJoueurView.class, FontAwesome.APPLE,1, true),
//	PariB("pari_joueur_B", PariJoueurView.class, FontAwesome.APPLE,2, true),
//	PariC("pari_joueur_C", PariJoueurView.class, FontAwesome.APPLE,3, true),
//	PariD("pari_joueur_D", PariJoueurView.class, FontAwesome.APPLE,4, true),
//	PariE("pari_joueur_E", PariJoueurView.class, FontAwesome.APPLE,5, true),
//	PariF("pari_joueur_F", PariJoueurView.class, FontAwesome.APPLE,6, true)
            ;

    private final String viewName;
    private final Class<? extends View> viewClass;
    private final Resource icon;
    private final int num;
    private final boolean stateful;

    private DashboardViewType(final String viewName,
            final Class<? extends View> viewClass, final Resource icon, final int num,
            final boolean stateful) {
    	this.num=num;
        this.viewName = viewName;
        this.viewClass = viewClass;
        this.icon = icon;
        this.stateful = stateful;
    }

    public boolean isStateful() {
        return stateful;
    }

    public String getViewName() {
        return viewName;
    }

    public Class<? extends View> getViewClass() {
        return viewClass;
    }

    public Resource getIcon() {
        return icon;
    }

    
    
    public static DashboardViewType getByViewName(final String viewName) {
        DashboardViewType result = null;
        for (DashboardViewType viewType : values()) {
            if (viewType.getViewName().equals(viewName)) {
                result = viewType;
                break;
            }
        }
        return result;
    }

	/**
	 * @return the num
	 */
	public int getNum() {
		return num;
	}

}
