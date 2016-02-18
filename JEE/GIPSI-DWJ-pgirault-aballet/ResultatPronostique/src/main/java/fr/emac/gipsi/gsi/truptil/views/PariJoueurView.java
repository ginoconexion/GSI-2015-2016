/**
 * 
 */
package fr.emac.gipsi.gsi.truptil.views;

import java.io.File;
import java.net.UnknownHostException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import fr.emac.gipsi.gsi.truptil.db.DBManager;
import fr.emac.gipsi.gsi.truptil.element.Joueur;
import fr.emac.gipsi.gsi.truptil.element.Pronostic;
import fr.emac.gipsi.gsi.truptil.equipe.Equipe;
import fr.emac.gipsi.gsi.truptil.equipe.ListeEquipe;
import fr.emac.gipsi.gsi.truptil.extract.Competition;
import fr.emac.gipsi.gsi.truptil.extract.Match;

/**
 * @author truptil
 *
 */
public class PariJoueurView extends VerticalLayout implements View {

	/**
	 * 
	 */
	public PariJoueurView() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param children
	 */
	public PariJoueurView(Component... children) {
		super(children);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.vaadin.navigator.View#enter(com.vaadin.navigator.ViewChangeListener.ViewChangeEvent)
	 */
	@Override
	public void enter(ViewChangeEvent event) {
		removeAllComponents();
		setSizeFull();
		int numPoule =0;
		for(DashboardViewType dwt : DashboardViewType.values()){
			//	System.out.println("dwt = "+dwt.getViewName() + " event =" + event.getViewName());
			if(dwt.getViewName().equals(event.getViewName())){
				numPoule = dwt.getNum()-1;
			}
		}

		//System.out.println(numPoule);

		try {
			Competition comp = DBManager.getInstance().getDatastore().find(Competition.class).asList().get(0);

			for(Match m : comp.getGroupe().get(numPoule).getMatch()){

				addComponent(createLayoutMatch(m));

			}


		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
	
	
	private Component createLayoutMatch(final Match m) {

		File f = new File(VaadinService.getCurrent().getBaseDirectory()+"/equipe16.xml");
		HorizontalLayout hl = new HorizontalLayout();

		HorizontalLayout hl2 = new HorizontalLayout();

		try {
			Unmarshaller unm = JAXBContext.newInstance("fr.emac.gipsi.gsi.truptil.equipe").createUnmarshaller();

			ListeEquipe le = (ListeEquipe) unm.unmarshal(f);

			Equipe eq1 = findEquipe(le, m.getEquipe1());
			Equipe eq2 = findEquipe(le, m.getEquipe2());
		
			final TextField score1 =  new TextField();
			final TextField score2 =  new TextField();
			Button but = new Button("save");
			but.addClickListener(new ClickListener() {
				
				@Override
				public void buttonClick(ClickEvent event) {
					Joueur user = (Joueur)VaadinSession.getCurrent().getAttribute(Joueur.class.getName());
					try {
						DBManager.getInstance().getDatastore().save(new Pronostic(score1.getValue(), score2.getValue(), m.getId(), user.getLogin()));
					} catch (UnknownHostException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			});
			
			hl2.addComponent(new Image("", new ThemeResource(
					"img"+eq1.getFlag())));
			hl2.addComponent(new Label(eq1.getId()));
			hl2.addComponent(new Label(" "));
			hl2.addComponent(score1);
			hl2.addComponent(new Label(" - "));
			hl2.addComponent(score2);
			hl2.addComponent(new Label(" "));
			hl2.addComponent(new Label(eq2.getId()));
			hl2.addComponent(new Image("", new ThemeResource(
					"img"+eq2.getFlag())));
			hl2.addComponent(but);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		hl.addComponent(hl2);
//		hl.addComponent(getGraphMatch(m));
		hl.setSizeFull();
		hl2.setSizeFull();
		

		return hl;
	}

	private Equipe findEquipe(ListeEquipe le, String equipe1) {

			for(Equipe eq : le.getEquipe()){
		//		System.out.println(eq.getPosition().getNumGroupe() + " " + equipe1.subSequence(0, 1));
				if(eq.getPosition().getNumGroupe().equals(Integer.parseInt(equipe1.substring(0, 1)))){
		//			System.out.println(eq.getPosition().getNumEquipe() + " " + equipe1.substring(1, 2));
					
					if(eq.getPosition().getNumEquipe().equals(Integer.parseInt(equipe1.substring(1, 2)))){
					//	System.out.println(" equipe = " +eq.getId());
						return eq;
				}
				}
			}
		
		return null;
	}

}
