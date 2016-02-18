/**
 * 
 */
package fr.emac.gipsi.gsi.truptil.views;

import java.net.UnknownHostException;
import java.util.Iterator;

import at.downdrown.vaadinaddons.highchartsapi.exceptions.HighChartsException;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import fr.emac.gipsi.gsi.truptil.element.Joueur;
import fr.emac.gipsi.gsi.truptil.event.DashboardEventBus;
import fr.emac.gipsi.gsi.truptil.graph.ColumChart;
import fr.emac.gipsi.gsi.truptil.graph.LineChart;

/**
 * @author truptil
 *
 */
public class JoueurView extends Panel implements View{

	private VerticalLayout root;
	private CssLayout dashboardPanels;

	@Override
	public void enter(ViewChangeEvent event) {

		addStyleName(ValoTheme.PANEL_BORDERLESS);
		setSizeFull();
		DashboardEventBus.register(this);

		root = new VerticalLayout();
		root.setSizeFull();
		root.setMargin(true);
		root.addStyleName("dashboard-view");
		setContent(root);
		Responsive.makeResponsive(root);

		Component content = buildContent();
		root.addComponent(content);
		root.setExpandRatio(content, 1);

	}

	private Component buildContent() {
		dashboardPanels = new CssLayout();
		dashboardPanels.addStyleName("dashboard-panels");
		Responsive.makeResponsive(dashboardPanels);

		dashboardPanels.addComponent(buildNBPronostics());
		dashboardPanels.addComponent(buildNBPointsByGame());
		dashboardPanels.addComponent(buildTop10Gamers());
		dashboardPanels.addComponent(buildNextGame());

		return dashboardPanels;
	}

	private Component buildNextGame() {
		// TODO Auto-generated method stub
		return buildNBPronostics();
	}

	private Component buildTop10Gamers() {

		Table t = new Table("classement joueur") ;
		t.setSizeFull();
		t=createContentTable(t);
		
		return createContentWrapper(t);
	}

	private Table createContentTable(Table t) {
		// TODO Auto-generated method stub
		return t;
	}

	private Component buildNBPointsByGame() {
		 LineChart cc = new LineChart();
		 VerticalLayout graph=null;
		 try {
			graph = cc.createColumChart((Joueur)VaadinSession.getCurrent().getAttribute(Joueur.class.getName()));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (HighChartsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return createContentWrapper(graph);
	}

	private Component buildNBPronostics() {

		HorizontalLayout hl = new HorizontalLayout();
		 ColumChart cc = new ColumChart();
		 VerticalLayout graph = cc.createColumChart((Joueur)VaadinSession.getCurrent().getAttribute(Joueur.class.getName()));
		 graph.setSizeFull();
		 hl.addComponent(graph);
		 hl.setSizeFull();
		Component toto = createContentWrapper(hl);
		return toto;
	}

	  private Component createContentWrapper(final Component content) {
	        final CssLayout slot = new CssLayout();
	        slot.setWidth("100%");
	        slot.addStyleName("dashboard-panel-slot");

	        CssLayout card = new CssLayout();
	        card.setWidth("100%");
	        card.addStyleName(ValoTheme.LAYOUT_CARD);

	        HorizontalLayout toolbar = new HorizontalLayout();
	        toolbar.addStyleName("dashboard-panel-toolbar");
	        toolbar.setWidth("100%");

	        Label caption = new Label(content.getCaption());
	        caption.addStyleName(ValoTheme.LABEL_H4);
	        caption.addStyleName(ValoTheme.LABEL_COLORED);
	        caption.addStyleName(ValoTheme.LABEL_NO_MARGIN);
	        content.setCaption(null);

	        MenuBar tools = new MenuBar();
	        tools.addStyleName(ValoTheme.MENUBAR_BORDERLESS);
	        MenuItem max = tools.addItem("", FontAwesome.EXPAND, new Command() {

	            @Override
	            public void menuSelected(final MenuItem selectedItem) {
	                if (!slot.getStyleName().contains("max")) {
	                    selectedItem.setIcon(FontAwesome.COMPRESS);
	                    toggleMaximized(slot, true);
	                } else {
	                    slot.removeStyleName("max");
	                    selectedItem.setIcon(FontAwesome.EXPAND);
	                    toggleMaximized(slot, false);
	                }
	            }
	        });
	        max.setStyleName("icon-only");
	        MenuItem root = tools.addItem("", FontAwesome.COG, null);
	        root.addItem("Configure", new Command() {
	            @Override
	            public void menuSelected(final MenuItem selectedItem) {
	                Notification.show("Not implemented in this demo");
	            }
	        });
	        root.addSeparator();
	        root.addItem("Close", new Command() {
	            @Override
	            public void menuSelected(final MenuItem selectedItem) {
	                Notification.show("Not implemented in this demo");
	            }
	        });

	        toolbar.addComponents(caption, tools);
	        toolbar.setExpandRatio(caption, 1);
	        toolbar.setComponentAlignment(caption, Alignment.MIDDLE_LEFT);
	        
	        card.addComponents(toolbar, content);
	        slot.addComponent(card);
	        
	        return slot;
	    }
	
	  private void toggleMaximized(final Component panel, final boolean maximized) {
	        for (Iterator<Component> it = root.iterator(); it.hasNext();) {
	            it.next().setVisible(!maximized);
	        }
	        dashboardPanels.setVisible(true);

	        for (Iterator<Component> it = dashboardPanels.iterator(); it.hasNext();) {
	            Component c = it.next();
	            c.setVisible(!maximized);
	        }

	        if (maximized) {
	            panel.setVisible(true);
	            panel.addStyleName("max");
	        } else {
	            panel.removeStyleName("max");
	        }
	    }

}
