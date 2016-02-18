///**
// * 
// */
//package fr.emac.gipsi.gsi.truptil.graph;
//
//import java.net.UnknownHostException;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import javax.xml.bind.JAXBContext;
//import javax.xml.bind.JAXBException;
//import javax.xml.bind.Marshaller;
//import javax.xml.bind.Unmarshaller;
//
//import org.jfree.chart.JFreeChart;
//import org.jfree.chart.plot.Plot;
//import org.vaadin.addon.JFreeChartWrapper;
//
//import com.vaadin.ui.Layout;
//import com.vaadin.ui.VerticalLayout;
//
//
//
///**
// * @author truptil
// *
// */
//public abstract class AbstractMiseGraph {
//
////	protected static Layout layout;
////	protected static Layout mainLayout;
//	protected static ArrayList<AbstractMiseGraph> listGraph;
////	protected MenuBar optionMenu;
//	private Unmarshaller unmarshaller;
//	private Marshaller marshaller;
////	private DateField fieldDateFrom;
////	private DateField fieldDateTo;
//	/**
//	 * 
//	 */
//	public AbstractMiseGraph() {
//		JAXBContext context;
//		try {
//			context = JAXBContext.newInstance("fr.enstimac.mise.element");		
//			unmarshaller = context.createUnmarshaller();
//			marshaller = context.createMarshaller();
//		} catch (JAXBException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
////		mainLayout = new VerticalLayout();
////		mainLayout.setSizeFull();
////		layout=new VerticalLayout();
////		layout.setSizeFull(); 
////		
////		mainLayout.addComponent(MenuBarCrisis.getInstance().getOptionMenu());
////		mainLayout.addComponent(layout);		
////		mainLayout.setSizeFull();
////		((VerticalLayout)mainLayout).setExpandRatio(layout, 1.0f);
//
//	}
//
//
//
//
//
////	public void addChart(Plot elt){
////		JFreeChart c = new JFreeChart(elt);
////		layout.addComponent(new JFreeChartWrapper(c));
////	}
////	public void addChart(JFreeChart elt){
////		layout.addComponent(new JFreeChartWrapper(elt));
////		
////	}
//
//	public abstract JFreeChart createChart(Object dataset);
//
//	public abstract Object createDataSet();
//
////	public Date getDateFrom(){
////		return MenuBarCrisis.getInstance().getFieldDateFrom().getValue();
////	}
////
////	public Date getDateTo(){
////		return MenuBarCrisis.getInstance().getFieldDateTo().getValue();
////	}
////
////	public ArrayList<IStoredEvent> getListEventByDate(Date from, Date to){
////		ArrayList<IStoredEvent> _return = new ArrayList<IStoredEvent>();
////		try {
////			List<StoredReceivedEvent> recEvent = DBManager.getInstance().getDatastore().find(StoredReceivedEvent.class).asList();
////			List<StoredSendEvent> sendEvent = DBManager.getInstance().getDatastore().find(StoredSendEvent.class).asList();
////
////			if(from!=null){
////				for(IStoredEvent event : recEvent){
////					System.out.println( "from = "+from);
////					System.out.println("to = "+ to);
////					System.out.println(" event.getDate() = "+ event.getDate());
////					if(event.getDate().after(from) && event.getDate().before(to)){
////						_return.add(event);
////					}
////				}
////				for(IStoredEvent event : sendEvent){
////					System.out.println( "from = "+from);
////					System.out.println("to = "+ to);
////					System.out.println(" event.getDate() = "+ event.getDate());
////					if(event.getDate().after(from) && event.getDate().before(to)){
////						_return.add(event);
////					}
////				}
////			}else{
////				for(IStoredEvent event : recEvent){
////
////					_return.add(event);
////				}
////				for(IStoredEvent event : sendEvent){
////					_return.add(event);
////				}
////			}
////
////
////		} catch (UnknownHostException e) {
////			e.printStackTrace();
////		}
////
////
////
////		return _return;
////
////	}
//
//
////	/**
////	 * @return the layout
////	 */
////	public Layout getLayout() {
////
////		if(layout.getComponentCount()>0){
////			layout.removeAllComponents();
////		}
////		for(AbstractMiseGraph amg :getListGraph()){
////			
////		addChart(amg.createChart(amg.createDataSet()));
////		}
////
////		return mainLayout;
////	}
//	
//	
//	public void addChartToLayout(){
//		getListGraph().add(this);
//	}
//
//	/**
//	 * @return the unmarshaller
//	 */
//	public Unmarshaller getUnmarshaller() {
//		return unmarshaller;
//	}
//
//	/**
//	 * @return the marshaller
//	 */
//	public Marshaller getMarshaller() {
//		return marshaller;
//	}
//
//
//
//
//
//	/**
//	 * @return the listGraph
//	 */
//	public static ArrayList<AbstractMiseGraph> getListGraph() {
//		if(listGraph==null){
//			listGraph= new ArrayList<AbstractMiseGraph>();
//		}
//		return listGraph;
//	}
//
//}
