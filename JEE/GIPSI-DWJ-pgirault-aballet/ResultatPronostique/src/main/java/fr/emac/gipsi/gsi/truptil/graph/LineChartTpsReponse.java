///**
// * 
// */
//package fr.emac.gipsi.gsi.truptil.graph;
//
//import java.awt.BasicStroke;
//import java.awt.Color;
//import java.awt.Font;
//import java.io.StringReader;
//import java.net.UnknownHostException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.concurrent.TimeUnit;
//
//import javax.xml.bind.JAXBContext;
//import javax.xml.bind.JAXBException;
//import javax.xml.bind.Marshaller;
//import javax.xml.bind.Unmarshaller;
//import javax.xml.datatype.XMLGregorianCalendar;
//
//import org.jfree.chart.ChartFactory;
//import org.jfree.chart.JFreeChart;
//import org.jfree.chart.axis.NumberAxis;
//import org.jfree.chart.axis.PeriodAxis;
//import org.jfree.chart.axis.PeriodAxisLabelInfo;
//import org.jfree.chart.axis.ValueAxis;
//import org.jfree.chart.plot.PlotOrientation;
//import org.jfree.chart.plot.ValueMarker;
//import org.jfree.chart.plot.XYPlot;
//import org.jfree.chart.renderer.xy.XYItemRenderer;
//import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
//import org.jfree.data.time.Hour;
//import org.jfree.data.time.Millisecond;
//import org.jfree.data.time.Minute;
//import org.jfree.data.time.RegularTimePeriod;
//import org.jfree.data.time.Second;
//import org.jfree.data.time.TimeSeries;
//import org.jfree.data.time.TimeSeriesCollection;
//import org.jfree.data.time.TimeSeriesDataItem;
//import org.jfree.data.xy.XYDataset;
//import org.jfree.data.xy.XYSeries;
//import org.jfree.data.xy.XYSeriesCollection;
//import org.jfree.ui.LengthAdjustmentType;
//import org.jfree.ui.RectangleAnchor;
//import org.jfree.ui.TextAnchor;
//
//import fr.emac.gipsi.gsi.truptil.element.Joueur;
//
//
///**
// * @author truptil
// *
// */
//public class LineChartTpsReponse extends AbstractMiseGraph {
//
//	/**
//	 * 
//	 */
//	public LineChartTpsReponse() {
//		
//		
//		JAXBContext context;
//		try {
////			context = JAXBContext.newInstance("fr.enstimac.mise.element");		
////			unmarshall =  context.createUnmarshaller();
////			marshall = context.createMarshaller();
//		} catch (JAXBException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
////		createDonnees();
//	}
//
//	private void createDonnees(Joueur j) {
//
////		try {
//
//			
//			
//	//				MeasureEvent a = (MeasureEvent) unmarshall.unmarshal(evt);
////					System.out.println("a.getTimestamp() " + a.getTimestamp());
////					System.out.println("a.getTimestampReceived " + a.getTimeReceived()) ;
////
////					long timeReceveid = a.getTimeReceived().toGregorianCalendar().getTimeInMillis();
////					long timeSend = a.getTimestamp().toGregorianCalendar().getTimeInMillis();
//
//					//					System.out.println("timeReceveid : "+ timeReceveid);
//					//					System.out.println("timeSend : "+timeSend);
//					//					
////					double diffMilli = timeReceveid - timeSend;
////
////					double diifSec = diffMilli/1000;
////					double diffMin = diifSec/60;
//
//					//					System.out.println("miliseconds : " + diffMilli );
//					//					System.out.println("seconds : " + diifSec );
//					//					System.out.println("min : " + diffMin );
//
//
//					//donnees.put(numEvent, diifSec);
////					numEvent++;
////				}
//			}
//
////		} catch (UnknownHostException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		} catch (JAXBException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
//
//
//
//
//		//		try {
//		//			List<StoredSendEvent> listSend = DBManager.getInstance().getDatastore().find(StoredSendEvent.class).asList();
//		//			List<StoredReceivedEvent> listRecu = DBManager.getInstance().getDatastore().find(StoredReceivedEvent.class).asList();
//		//
//		//			for(StoredSendEvent sse : listSend){
//		//
//		//				for(StoredReceivedEvent sre : listRecu){
//		//					//				System.out.println("sse.getId().equals(sre.getId() " + sse.getId() + " " + sre.getId());
//		//					if(sse.getType().equals(sre.getType())){
//		//						if(eventEquals(sse,sre,sse.getType())){
//		////							System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$eventReceive");
//		////							System.out.println(sre.getDate() +"    "+sse.getDate());
//		//							//				System.out.println(	getDateDiff(sre.getDate(),sse.getDate(),TimeUnit.MILLISECONDS));
//		//							//	Long ldiff = new Long(getDateDiff(sre.getDate(),sse.getDate(),TimeUnit.MILLISECONDS));
//		//
//		//							Long ldiff2 = sre.getDateMillis() - sse.getDateMillis();
//		//
//		//							double diff2 = ldiff2.doubleValue();
//		//							
//		//							if(diff2<0){
//		//								diff2=-diff2;
//		//							}
//		//
//		//							//	double diff = ldiff.doubleValue();
//		////							System.out.println("diff " + diff2);
//		//							donnees.put(numEvent, diff2);
//		//							numEvent++;
//		//						}
//		//					}}
//		//			}
//		//
//		//
//		//
//		//		} catch (UnknownHostException e) {
//		//			// TODO Auto-generated catch block
//		//			e.printStackTrace();
//		//		}
//
////	}
//
//	private boolean eventEquals(StoredSendEvent sse, StoredReceivedEvent sre, EventType eventType) {
//
//		//sse.getEventId()
//		//		System.out.println( "sse.getEventId() = " + sse.getEventId() + " sre.getEventId() = " +sre.getEventId() );
//		if (sse.getEventId()!=null && sre.getEventId() !=null){
//			String in =sse.getEventId();
//			String out =sre.getEventId();
//
//			String[] in2 = in.split("-");
//			String[] out2 = out.split("-");
//
//			if(in2.length>2 && out2.length>2){
//				if(in2[in2.length-1].equals(out2[out2.length-1]) && in2[in2.length-2].equals(out2[out2.length-2])){
//					return true;
//				}
//			}else{
//				System.out.println("erreur");
//				return false;
//			}
//		}else{
//			return false;
//		}
//
//		return false;
//		//		if(sse.getEvent().equals(sre.getEvent())){
//		//			return true;
//		//		}
//		//		
//		//		
//		//		return false;
//	}
//
//	/* (non-Javadoc)
//	 * @see fr.enstimac.mise.component.graphs.AbstractMiseGraph#createChart(java.lang.Object)
//	 */
//	@Override
//	public JFreeChart createChart(Object dataset) {
//		//		JFreeChart chart = ChartFactory.createXYLineChart(
//		//				"Transfert Time",      // chart title
//		//				"event",                      // x axis label
//		//				"time",                      // y axis label
//		//				(XYDataset) dataset,                  // data
//		//				PlotOrientation.VERTICAL,
//		//				true,                     // include legend
//		//				true,                     // tooltips
//		//				false                     // urls
//		//				);
//
//		// NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...
//		//		chart.setBackgroundPaint(Color.white);
//
//		//	           final StandardLegend legend = (StandardLegend) chart.getLegend();
//		//      legend.setDisplaySeriesShapes(true);
//
//		// get a reference to the plot for further customisation...
//		////		XYPlot plot = chart.getXYPlot();
//		//		plot.setBackgroundPaint(Color.lightGray);
//		//		//    plot.setAxisOffset(new Spacer(Spacer.ABSOLUTE, 5.0, 5.0, 5.0, 5.0));
//		//		plot.setDomainGridlinePaint(Color.white);
//		//		plot.setRangeGridlinePaint(Color.white);
//		//
//		//		XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
//		//		renderer.setSeriesLinesVisible(0, true);
//		//		renderer.setSeriesShapesVisible(0, true);
//		//		plot.setRenderer(renderer);
//		//
//		//		// change the auto tick unit selection to integer units only...
//		//		final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
//		//		rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
//		//		// OPTIONAL CUSTOMISATION COMPLETED.
//
//
//		JFreeChart jfreechart=ChartFactory.createXYLineChart("Transfert Time", "Time", "seconds", (XYDataset) dataset,PlotOrientation.VERTICAL , false,true,false);
//
//		XYPlot xyplot = (XYPlot)jfreechart.getPlot();
//		xyplot.setDomainGridlinePaint(Color.lightGray);
//		xyplot.setDomainGridlineStroke(new BasicStroke(1.0F));
//		xyplot.setRangeGridlinePaint(Color.lightGray);
//		xyplot.setRangeGridlineStroke(new BasicStroke(1.0F));
//		xyplot.setRangeTickBandPaint(new Color(240, 240, 240));
//		PeriodAxis periodaxis = new PeriodAxis(null, new Minute(0,10, 22, 11, 2013), new Minute(0,11, 22, 11, 2013));
//		PeriodAxisLabelInfo aperiodaxislabelinfo[] = new PeriodAxisLabelInfo[2];
//		aperiodaxislabelinfo[0] = new PeriodAxisLabelInfo(org.jfree.data.time.Second.class, new SimpleDateFormat("ss"));
//		aperiodaxislabelinfo[1] = new PeriodAxisLabelInfo(org.jfree.data.time.Minute.class, new SimpleDateFormat("hh:mm"));
//		periodaxis.setLabelInfo(aperiodaxislabelinfo);
//		xyplot.setDomainAxis(periodaxis);
//		ValueAxis valueaxis = xyplot.getRangeAxis();
//		valueaxis.setRange(-10, 100);
//		XYItemRenderer xyitemrenderer = xyplot.getRenderer();
//		xyitemrenderer.setSeriesPaint(0, Color.RED);
//		xyitemrenderer.setSeriesStroke(0, new BasicStroke(2.0F));
//
//		return jfreechart;
//	}
//
//	/* (non-Javadoc)
//	 * @see fr.enstimac.mise.component.graphs.AbstractMiseGraph#createDataSet()
//	 */
//	@Override
//	public Object createDataSet() {
//
//		//		XYSeries series = new XYSeries("transfertTime");
//		//		for(double i =0.0; i<numEvent;i++){
//		//			series.add(i, donnees.get(i));
//		//		}
//		//		XYSeriesCollection dataset = new XYSeriesCollection();
//		//		dataset.addSeries(series);
//		//
//		//		return dataset;
//
//		TimeSeriesCollection timeseriescollection = new TimeSeriesCollection();
//		TimeSeries timeseries = new TimeSeries("Radiation", org.jfree.data.time.Millisecond.class);
//
//		ArrayList<XMLGregorianCalendar> deja = new ArrayList<XMLGregorianCalendar>();
//		try {
//			JAXBContext context = JAXBContext.newInstance("fr.enstimac.mise.element");		
//			Unmarshaller unmarshaller = context.createUnmarshaller();
//
//
//			List<StoredReceivedEvent> listEvent = DBManager.getInstance().getDatastore().find(StoredReceivedEvent.class).asList();
//			for(StoredReceivedEvent sre : listEvent){
//				if(sre.getType().equals(EventType.MeasureEvent)){
//					StringReader evt = new StringReader((String) sre.getEvent());
//					MeasureEvent a = (MeasureEvent) unmarshaller.unmarshal(evt);
//					if(!deja.contains(a.getTimestamp())){
//						deja.add(a.getTimestamp());						
//	//					System.out.println( " localisation : "+a.getSituation().getLocalisation() + " time : " +a.getTimestamp() +" unit :"+a.getUnit() + " value ="+a.getValue());
//						RegularTimePeriod period = new Millisecond(a.getTimestamp().getMillisecond(),a.getTimestamp().getSecond(), a.getTimestamp().getMinute(), a.getTimestamp().getHour(), a.getTimestamp().getDay(), a.getTimestamp().getMonth(), a.getTimestamp().getYear());
//								
//								//Second(a.getTimestamp().getSecond(), a.getTimestamp().getMinute(), a.getTimestamp().getHour(), a.getTimestamp().getDay(), a.getTimestamp().getMonth(), a.getTimestamp().getYear());
//
//						long timeReceveid = a.getTimeReceived().toGregorianCalendar().getTimeInMillis();
//						long timeSend = a.getTimestamp().toGregorianCalendar().getTimeInMillis();								
//						double diffMilli = timeReceveid - timeSend;
//
//						double diifSec = diffMilli/1000;
//						double diffMin = diifSec/60;
//
//						
//	//					System.out.println(" localisation : "+a.getSituation().getLocalisation() + "diifSec =" +diifSec +"     diffHeureServer = "+diffHeureServer);
//						
////						System.out.println("diifSec Avant :::::   " +diifSec);
////						if(diifSec<0){
////							diifSec=-diifSec - diffHeureServer;
////						}else{
////							//decalage de temps entre servers
////							diifSec = diifSec - diffHeureServer;
////						
////						
////						}
////						
//
//						if(diifSec<0){
//							diifSec=diifSec;
//						}
//			
////						System.out.println("diifSec Apres :::::   " +diifSec);
//						
//	//					donneesMeasure.put(sre, diifSec);
//						
//						timeseries.add(new TimeSeriesDataItem(period, diifSec));
//						//timeseries.add(new Hour(0, 30, 6, 2005), 45.299999999999997D);
//					}
//				}else if(sre.getType().equals(EventType.ActivityStatus)){
//					StringReader evt = new StringReader((String) sre.getEvent());
//		//			ActivityStatusEvent a = (ActivityStatusEvent) unmarshaller.unmarshal(evt);
//					if(!deja.contains(a.getTimestamp())){
//						deja.add(a.getTimestamp());						
////						RegularTimePeriod period = new Second(a.getTimestamp().getSecond(), a.getTimestamp().getMinute(), a.getTimestamp().getHour(), a.getTimestamp().getDay(), a.getTimestamp().getMonth(), a.getTimestamp().getYear());
//						RegularTimePeriod period = new Millisecond(a.getTimestamp().getMillisecond(),a.getTimestamp().getSecond(), a.getTimestamp().getMinute(), a.getTimestamp().getHour(), a.getTimestamp().getDay(), a.getTimestamp().getMonth(), a.getTimestamp().getYear());
//						
//						long timeReceveid = a.getTimeReceived().toGregorianCalendar().getTimeInMillis();
//						long timeSend = a.getTimestamp().toGregorianCalendar().getTimeInMillis();								
//						double diffMilli = timeReceveid - timeSend;
//
//						double diifSec = diffMilli/1000;
//						double diffMin = diifSec/60;
//
////						if(diifSec<0){
////							diifSec=-diifSec-diffHeureServer;
////						}else{
////							//decalage de temps entre servers
////							diifSec = diifSec - diffHeureServer;
////						
////						}
//
//						if(diifSec<0){
//							diifSec=diifSec;
//						}
//			
//						donneesActivity.put(sre, diifSec);
//
//						timeseries.add(new TimeSeriesDataItem(period, diifSec));
//
//					}
//				}
//			}
//			System.out.println("nb item = " + timeseries.getItemCount());
//		} catch (UnknownHostException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (JAXBException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//
//		timeseriescollection.addSeries(timeseries);
//		return timeseriescollection;
//
//
//	}
//
//	public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
//		long diffInMillies = date1.getTime() - date2.getTime();
//		return timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
//	}
//
//	/**
//	 * @return the donneesMeasure
//	 */
//	public HashMap<StoredReceivedEvent, Double> getDonneesMeasure() {
//		return donneesMeasure;
//	}
//
//	/**
//	 * @return the donneesActivity
//	 */
//	public HashMap<StoredReceivedEvent, Double> getDonneesActivity() {
//		return donneesActivity;
//	}
//
//
//	
//}
