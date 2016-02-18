/**
 * 
 */
package fr.emac.gipsi.gsi.truptil.views;

import java.net.UnknownHostException;

import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.Position;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import fr.emac.gipsi.gsi.truptil.db.DBManager;
import fr.emac.gipsi.gsi.truptil.element.Joueur;
import fr.emac.gipsi.gsi.truptil.event.DashboardEvent.UserLoginRequestedEvent;
import fr.emac.gipsi.gsi.truptil.event.DashboardEventBus;


/**
 * @author truptil
 *
 */
public class LoginView extends VerticalLayout {

	final VerticalLayout loginPanel= new VerticalLayout();

	public LoginView() {
		setSizeFull();

		Component loginForm = buildLoginForm();
		addComponent(loginForm);
		setComponentAlignment(loginForm, Alignment.MIDDLE_CENTER);

		Notification notification = new Notification(
				"Bienvenue dans l'outil de pari euro2016 ");
		notification
		.setDescription("<span>Cette application est <b>Maison</b></span>");
		notification.setHtmlContentAllowed(true);
		notification.setStyleName("tray dark small closable login-help");
		notification.setPosition(Position.BOTTOM_CENTER);
		notification.setDelayMsec(20000);
		notification.show(Page.getCurrent());
	}

	private Component buildLoginForm() {
	//	VerticalLayout loginPanel = new VerticalLayout();
		loginPanel.setSizeUndefined();
		loginPanel.setSpacing(true);
		Responsive.makeResponsive(loginPanel);
		loginPanel.addStyleName("login-panel");

		loginPanel.addComponent(buildLabels());
		loginPanel.addComponent(buildFields());
		//     loginPanel.addComponent(new CheckBox("Remember me", true));
		return loginPanel;
	}

	private Component buildFields() {
		HorizontalLayout fields = new HorizontalLayout();
		fields.setSpacing(true);
		fields.addStyleName("fields");

		final TextField username = new TextField("Login");
		username.setIcon(FontAwesome.USER);
		username.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);

		final PasswordField password = new PasswordField("Password");
		password.setIcon(FontAwesome.LOCK);
		password.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);

		final Button signin = new Button("Sign In");
		signin.addStyleName(ValoTheme.BUTTON_PRIMARY);
		signin.setClickShortcut(KeyCode.ENTER);
		signin.focus();

		fields.addComponents(username, password, signin);
		fields.setComponentAlignment(signin, Alignment.BOTTOM_LEFT);

		signin.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(final ClickEvent event) {
				if (username.getValue() != null
						&& !username.getValue().equals("")
						&& password.getValue() != null
						&& !password.getValue().equals("")) {
					signin.removeClickShortcut();
					try {
						int flag=0;
						for(Joueur user:DBManager.getInstance().getDatastore().find(Joueur.class).asList()){
							if(username.getValue().equals(user.getLogin()) && password.getValue().equals(user.getPwd())){
								flag=1;
								VaadinSession.getCurrent().setAttribute(Joueur.class.getName(), user);					 
								 DashboardEventBus.post(new UserLoginRequestedEvent(username
					                        .getValue(), password.getValue()));
							}

						}
						if (flag==1){
							signin.setClickShortcut(KeyCode.ENTER);
							//	    buildMainView();
						}else{
							// Add new error message
							Label error = new Label(
									"Mauvais nom d'utilisateur ou mot de passe.",
									ContentMode.HTML);
							error.addStyleName("error");
							error.setSizeUndefined();
							error.addStyleName("light");
							// Add animation
							error.addStyleName("v-animate-reveal");
							loginPanel.addComponent(error);
							username.focus();
						}
					} catch (UnknownHostException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		return fields;
	}

	private Component buildLabels() {
		CssLayout labels = new CssLayout();
		labels.addStyleName("labels");

		Label welcome = new Label("Welcome");
		welcome.setSizeUndefined();
		welcome.addStyleName(ValoTheme.LABEL_H4);
		welcome.addStyleName(ValoTheme.LABEL_COLORED);
		labels.addComponent(welcome);

		Label title = new Label("QuickTickets Dashboard");
		title.setSizeUndefined();
		title.addStyleName(ValoTheme.LABEL_H3);
		title.addStyleName(ValoTheme.LABEL_LIGHT);
		labels.addComponent(title);
		return labels;
	}
}