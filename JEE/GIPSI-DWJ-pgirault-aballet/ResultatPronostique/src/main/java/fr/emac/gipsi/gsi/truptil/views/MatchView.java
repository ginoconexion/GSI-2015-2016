/**
 * 
 */
package fr.emac.gipsi.gsi.truptil.views;

import java.io.File;
import java.net.UnknownHostException;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import fr.emac.gipsi.gsi.truptil.db.DBManager;
import fr.emac.gipsi.gsi.truptil.element.ResultatMatch;
import fr.emac.gipsi.gsi.truptil.equipe.Equipe;
import fr.emac.gipsi.gsi.truptil.equipe.ListeEquipe;
import fr.emac.gipsi.gsi.truptil.extract.Competition;
import fr.emac.gipsi.gsi.truptil.extract.Match;
import fr.emac.gipsi.gsi.truptil.graph.ColumChart;
import fr.emac.gipsi.gsi.truptil.util.GestionMatch;

/**
 * @author truptil
 *
 */
public class MatchView extends HorizontalLayout implements View{

	private VerticalLayout vl;

	/**
	 * 
	 */
	public MatchView() {
	}

	@Override
	public void enter(ViewChangeEvent event) {
		
		 vl = new VerticalLayout();
		vl.setSizeFull();
		setSizeFull();
		vl.addStyleName("dashboard-view");
		
		//setPrimaryStyleName("valo-natural-page-scrolling");
//		removeAllComponents();
		//setSizeUndefined();//		setSizeFull();
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

				vl.addComponent(createLayoutMatch(m));

			}


		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		addComponent(vl);
	
//		setSizeUndefined();

	}

	private Component createLayoutMatch(final Match m) {

			HorizontalLayout hl = new HorizontalLayout();

		HorizontalLayout hl2 = new HorizontalLayout();

		try {

			Equipe eq1 = GestionMatch.findEquipe( m.getEquipe1());
			Equipe eq2 = GestionMatch.findEquipe( m.getEquipe2());
			
			List<ResultatMatch> rms = DBManager.getInstance().getDatastore().find(ResultatMatch.class).asList();
			ResultatMatch rr = null;
			for( ResultatMatch rm : rms){
				if(rm.getIdMatch().equals(m.getId())){
					rr=rm;
				}
			}
			
			hl2.addComponent(new Image("", new ThemeResource(
					"img"+eq1.getFlag())));
			hl2.addComponent( new Label(eq1.getId()));
			hl2.addComponent(new Label(" "));
			hl2.addComponent(new Label(Integer.toString(rr.getScore1())));
			hl2.addComponent(new Label(" - "));
			hl2.addComponent(new Label(Integer.toString(rr.getScore2())));
			hl2.addComponent(new Label(" "));
			hl2.addComponent(new Label(eq2.getId()));
			hl2.addComponent(new Image("", new ThemeResource(
					"img"+eq2.getFlag())));

			final ResultatMatch frr =rr;
			Button edit = new Button("see result");
			edit.addClickListener(new ClickListener() {
				
				@Override
				public void buttonClick(ClickEvent event) {
					removeAllComponents();
					addComponent(vl);
					addComponent(getGraphMatch(frr));
				}
			});
			hl2.addComponent(edit);
			
			for(int i=0;i< hl2.getComponentCount();i++){
				hl2.setComponentAlignment(hl2.getComponent(i), Alignment.MIDDLE_CENTER);
				
			}
			
			
		} catch (JAXBException | UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		HorizontalLayout hl3 = getGraphMatch(m);

//		hl.setWidth(100, Unit.PERCENTAGE);
//		hl.setHeight(200, Unit.PIXELS);
		hl.setSizeFull();
		hl2.setSizeFull();
//		hl3.setSizeFull();
		hl.addComponent(hl2);
//		hl.addComponent(hl3);
		hl.setComponentAlignment(hl2, Alignment.MIDDLE_CENTER);

		return hl;
	}

	

	private HorizontalLayout getGraphMatch(ResultatMatch m) {

		HorizontalLayout hl = new HorizontalLayout();
		 ColumChart cc = new ColumChart();
		 VerticalLayout graph = cc.createColumChart(m);
		 graph.setSizeFull();
		 hl.addComponent(graph);
		 hl.setSizeFull();
		
		return hl;
	}

}
