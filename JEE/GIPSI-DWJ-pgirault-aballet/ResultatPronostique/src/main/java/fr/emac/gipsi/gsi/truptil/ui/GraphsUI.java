///**
// * 
// */
//package fr.emac.gipsi.gsi.truptil.ui;
//
//import java.net.UnknownHostException;
//import java.util.ArrayList;
//import java.util.HashMap;
//
//import javax.swing.JLabel;
//
//import org.jfree.chart.JFreeChart;
//import org.jfree.chart.plot.Plot;
//import org.vaadin.addon.JFreeChartWrapper;
//
//import com.vaadin.data.Item;
//import com.vaadin.data.util.IndexedContainer;
//import com.vaadin.server.VaadinRequest;
//import com.vaadin.ui.Component;
//import com.vaadin.ui.ComponentContainer;
//import com.vaadin.ui.Layout;
//import com.vaadin.ui.Table;
//import com.vaadin.ui.TextField;
//import com.vaadin.ui.UI;
//import com.vaadin.ui.VerticalLayout;
//
//
///**
// * @author truptil
// *
// */
//public class GraphsUI extends UI {
//
//	//	private ArrayList<AbstractMiseGraph> listgraph;
//
//
//	@Override
//	protected void init(VaadinRequest request) {
//
//		VerticalLayout mainLayout = new VerticalLayout();
//		mainLayout.setSizeFull();
//		VerticalLayout layout = new VerticalLayout();
//		//		layout.setSizeFull(); 
//
//	//	mainLayout.addComponent(MenuBarCrisis.getInstance().getOptionMenu());
//		mainLayout.addComponent(layout);		
//		mainLayout.setSizeFull();
//		((VerticalLayout)mainLayout).setExpandRatio(layout, 1.0f);
//
//		//		BarChart3D bc = new BarChart3D();
//		//		TimeSeriesMise tsm = new TimeSeriesMise("number of event by time", "number of event", "time");
//		//		PieChartMise pcm = new PieChartMise();
//		//		MultiplePieChartMise mpcm = new MultiplePieChartMise();
//		//		LineChartTpsReponse lctr = new LineChartTpsReponse();
//
//		//		getListGraph().add(bc);
//		//		getListGraph().add(tsm);
//		//		getListGraph().add(pcm);
//		//		getListGraph().add(mpcm);
//		//		getListGraph().add(lctr);
//
//		getLayout(layout);
//
//
//
//		//	layout.setExpandRatio(layout, 1.0f);
//		if(layout.getComponentCount()>0){
//			for(int i=0;i<layout.getComponentCount();i++){
//				layout.getComponent(i).setSizeFull();
//			}}
//
//		setContent(mainLayout);
//	}
//
//
//	public void addChart(Plot elt, ComponentContainer layout){
//		JFreeChart c = new JFreeChart(elt);
//		layout.addComponent(new JFreeChartWrapper(c));
//	}
//	public void addChart(JFreeChart elt, ComponentContainer layout){
//		layout.addComponent(new JFreeChartWrapper(elt));
//
//	}
//
//	/**
//	 * @param layout 
//	 * @return the layout
//	 */
//	public Layout getLayout(Layout layout) {
//
//		if(layout.getComponentCount()>0){
//			layout.removeAllComponents();
//		}
//		createTableResult(layout);
////		for(AbstractMiseGraph amg :getListGraph()){
////
////			addChart(amg.createChart(amg.createDataSet()),layout);
////		}
//
//		return layout;
//	}
//
//
//	private void createTableResult(Layout layout) {
//
//		int nbEventSend=0;
//		int nbEventReceive=0;
////		try {
////			nbEventSend = DBManager.getInstance().getDatastore().find(StoredSendEvent.class).asList().size();
////			nbEventReceive = DBManager.getInstance().getDatastore().find(StoredReceivedEvent.class).asList().size();
////		} catch (UnknownHostException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
//
//		layout.addComponent(new TextField("number event sent : ", Integer.toString(nbEventSend)));
//		layout.addComponent(new TextField("number event received : ", Integer.toString(nbEventReceive)));
//
////		HashMap<StoredReceivedEvent, Double> datasMeasure =null;
////		HashMap<StoredReceivedEvent, Double> datasActivity =null;
////		for(AbstractMiseGraph amg :getListGraph()){
////			if(amg instanceof LineChartTpsReponse){
////				amg.createDataSet();
////				datasMeasure = ((LineChartTpsReponse)amg).getDonneesMeasure();
////				datasActivity = ((LineChartTpsReponse)amg).getDonneesActivity();
////			}
////		}
//
////		if(datasActivity!=null && datasMeasure!=null){
////			if(datasActivity.size()>0 && datasMeasure.size()>0){
////				double tpsMax=0;
////				double tpsMin=100000000.0;
////				double tpsMoyen =0;
////				double nb =0;
////				HashMap<StoredReceivedEvent, Double> datas = new HashMap<StoredReceivedEvent, Double>();
////						datas.putAll(datasActivity);
////				datas.putAll(datasMeasure);
////				for(Double d : datas.values()){
////					if(d>tpsMax){
////						tpsMax=d;
////					}
////					if(d<tpsMin){
////						tpsMin=d;
////					}
////
////					nb++;
////					tpsMoyen=tpsMoyen+d;
////				}
////				tpsMoyen=tpsMoyen/nb;		
////
////				layout.addComponent(new TextField("maximum transfert time ", Double.toString(tpsMax)));
////				layout.addComponent(new TextField("minimum transfert time ", Double.toString(tpsMin)));
////				layout.addComponent(new TextField("average transfert time ", Double.toString(tpsMoyen)));
////				//			layout.addComponent(createTable(datas));
////			}
////		}
////		if(datasActivity!=null){
////			if(datasActivity.size()>0){
////				double tpsMax=0;
////				double tpsMin=100000000.0;
////				double tpsMoyen =0;
////				double nb =0;
////				for(Double d : datasActivity.values()){
////					if(d>tpsMax){
////						tpsMax=d;
////					}
////					if(d<tpsMin){
////						tpsMin=d;
////					}
////
////					nb++;
////					tpsMoyen=tpsMoyen+d;
////				}
////				tpsMoyen=tpsMoyen/nb;		
////
////				layout.addComponent(new TextField("maximum transfert time for ActivityStatusEvent ", Double.toString(tpsMax)));
////				layout.addComponent(new TextField("minimum transfert time for ActivityStatusEvent", Double.toString(tpsMin)));
////				layout.addComponent(new TextField("average transfert time for ActivityStatusEvent ", Double.toString(tpsMoyen)));
////				//			layout.addComponent(createTable(datas));
////			}
////		}
////		if(datasMeasure!=null){
////			if(datasMeasure.size()>0){
////				double tpsMax=0;
////				double tpsMin=100000000.0;
////				double tpsMoyen =0;
////				double nb =0;
////				for(Double d : datasMeasure.values()){
////					if(d>tpsMax){
////						tpsMax=d;
////					}
////					if(d<tpsMin){
////						tpsMin=d;
////					}
////
////					nb++;
////					tpsMoyen=tpsMoyen+d;
////				}
////				tpsMoyen=tpsMoyen/nb;		
////
////				layout.addComponent(new TextField("maximum transfert time for MeasureEvent ", Double.toString(tpsMax)));
////				layout.addComponent(new TextField("minimum transfert time for MeasureEvent", Double.toString(tpsMin)));
////				layout.addComponent(new TextField("average transfert time for MeasureEvent ", Double.toString(tpsMoyen)));
////				//			layout.addComponent(createTable(datas));
////			}
////		}
//	}
//
//
//	private Component createTable(HashMap<Double, Double> datas) {
//		Table table = new Table();
//		IndexedContainer cont= new IndexedContainer();
//		cont.addContainerProperty("NumEvent", Integer.class, 0.0);
//		cont.addContainerProperty("TimeTransfert", Double.class, 0.0);
//		table.setContainerDataSource(cont);
//
//		if( datas!=null){
//			int i=0;
//			for(double d :datas.values()){
//				Item item =table.addItem(i+1);
//				item.getItemProperty("NumEvent").setValue(i);
//				item.getItemProperty("TimeTransfert").setValue(d);
//			}}
//		table.setSizeFull();
//		return table;
//	}
//
//
////	private ArrayList<AbstractMiseGraph> getListGraph() {	
////
////		return MenuBarCrisis.getInstance().getListGraphs();
////	}
//
//
//
//	//		
//	//         VerticalLayout layout = new VerticalLayout();
//	//        layout.setMargin(true);
//	//        setContent(layout);
//	//	
//	//        if(layout.getComponentCount()>0){
//	//			layout.removeAllComponents();
//	//		}
//	//			
//	//		BarChart3D bc = new BarChart3D();
//	////		layout.addComponent(bc.getLayout());
//	//	//	bc.getLayout().setSizeFull();
//	////		
//	//		
//	//		
//	//		TimeSeriesMise tsm = new TimeSeriesMise("number of event by time", "number of event", "time");
//	//	
//	//
//	//		bc.addChartToLayout();
//	//		tsm.addChartToLayout();
//	//		
//	//		
//	//		System.out.println("tsm : " + tsm.getDonnees());
//	//		layout.addComponent(tsm.getLayout());
//	////		tsm.getLayout().setSizeFull();
//	//		
//	//		
//	//		
//	//		layout.setSizeFull(); 
//	//	}
//
//}
