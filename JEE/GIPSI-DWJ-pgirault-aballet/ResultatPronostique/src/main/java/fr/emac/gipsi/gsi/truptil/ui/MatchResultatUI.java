/**
 * 
 */
package fr.emac.gipsi.gsi.truptil.ui;

import java.util.Locale;

import com.google.common.eventbus.Subscribe;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.Page;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Component;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;

import fr.emac.gipsi.gsi.truptil.element.Joueur;
import fr.emac.gipsi.gsi.truptil.event.DashboardEvent.UserLoggedOutEvent;
import fr.emac.gipsi.gsi.truptil.event.DashboardEvent.UserLoginRequestedEvent;
import fr.emac.gipsi.gsi.truptil.event.DashboardEventBus;
import fr.emac.gipsi.gsi.truptil.views.LoginView;
import fr.emac.gipsi.gsi.truptil.views.MainView;

/**
 * @author truptil
 *
 */
@Theme("mytheme")
@Widgetset("fr.emac.gipsi.gsi.truptil.MyAppWidgetset")
public class MatchResultatUI extends UI {

	/**
	 * 
	 */
	public MatchResultatUI() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param content
	 */
	public MatchResultatUI(Component content) {
		super(content);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.vaadin.ui.UI#init(com.vaadin.server.VaadinRequest)
	 */
	@Override
	protected void init(VaadinRequest request) {
		setLocale(Locale.FRANCE);
		DashboardEventBus.register(this);
		Responsive.makeResponsive(this);
		
		addStyleName(ValoTheme.UI_WITH_MENU);
		setSizeFull();
	//	setSizeUndefined();
		
		Joueur user = (Joueur) VaadinSession.getCurrent().getAttribute(
				Joueur.class.getName());
		if(user !=null){
		System.out.println("user " + user.toString());
		}
		if (user != null) {
			
			// Authenticated user
			setContent(new MainView());
			removeStyleName("loginview");
	//		getNavigator().navigateTo(getNavigator().getState());
		} else {
			setContent(new LoginView());
			addStyleName("loginview");
		}

		
		
//		updateContent();
//		setContent(new MainView());
	}
	
	private void updateContent() {
		Joueur user = (Joueur) VaadinSession.getCurrent().getAttribute(
				Joueur.class.getName());
		if(user !=null){
		System.out.println("user " + user.toString());
		}
		if (user != null) {
			
			// Authenticated user
			setContent(new MainView());
			removeStyleName("loginview");
	//		getNavigator().navigateTo(getNavigator().getState());
		} else {
			setContent(new LoginView());
			addStyleName("loginview");
		}
	}

	private final DashboardEventBus dashboardEventbus = new DashboardEventBus();

	
	
	@Subscribe
	public void userLoginRequested(final UserLoginRequestedEvent event) {
		System.out.println("event subscribe");
		Joueur user = (Joueur)VaadinSession.getCurrent().getAttribute(Joueur.class.getName());
		updateContent();
	}
	
	  @Subscribe
	    public void userLoggedOut(final UserLoggedOutEvent event) {
	        // When the user logs out, current VaadinSession gets closed and the
	        // page gets reloaded on the login screen. Do notice the this doesn't
	        // invalidate the current HttpSession.
	        VaadinSession.getCurrent().close();
	        Page.getCurrent().reload();
	    }


		public static DashboardEventBus getDashboardEventbus() {
			return ((MatchResultatUI) getCurrent()).dashboardEventbus;
		}

		
}
