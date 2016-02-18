/**
 * 
 */
package fr.emac.gipsi.gsi.truptil.graph;

import com.vaadin.data.util.IndexedContainer;
import com.vaadin.ui.Component;
import com.vaadin.ui.Table;

/**
 * @author truptil
 *
 */
public class TableClassement {

	private int numPoule;
	
	/**
	 * 
	 */
	public TableClassement(int numPoule) {
		this.numPoule=numPoule;
	}
	
	
	public Component createClassementPronostic(){
		Table table = createStructureTable();
		
		return table;
	}
	
	private Table createStructureTable() {
		Table tableau = new Table();
		tableau.setSizeFull();
		tableau.setSelectable(true);
		tableau.setImmediate(true);

		IndexedContainer cont = new IndexedContainer();

		cont.addContainerProperty("groupe", Integer.class, "");
		cont.addContainerProperty("pos", Integer.class, "");
		cont.addContainerProperty("equipe", String.class, "");
		cont.addContainerProperty("point", String.class, "");
		cont.addContainerProperty("victoire", Integer.class, "");
		cont.addContainerProperty("nul", Integer.class, "");
		cont.addContainerProperty("defaite", Integer.class, "");
		cont.addContainerProperty("BP", Integer.class, "");
		cont.addContainerProperty("BC", Integer.class, "");
		cont.addContainerProperty("GA", Integer.class, "");
		
		tableau.setContainerDataSource(cont);

		tableau.setColumnCollapsingAllowed(true);
		tableau.setColumnCollapsed("groupe", true);
		
		return tableau;
	}


	public Component createClassement(){
		Table table = createStructureTable();

		return table;
	}

}
