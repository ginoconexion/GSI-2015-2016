/**
 * 
 */
package fr.emac.gipsi.gsi.truptil.graph;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import at.downdrown.vaadinaddons.highchartsapi.HighChart;
import at.downdrown.vaadinaddons.highchartsapi.HighChartFactory;
import at.downdrown.vaadinaddons.highchartsapi.exceptions.HighChartsException;
import at.downdrown.vaadinaddons.highchartsapi.model.ChartConfiguration;
import at.downdrown.vaadinaddons.highchartsapi.model.ChartType;
import at.downdrown.vaadinaddons.highchartsapi.model.data.HighChartsData;
import at.downdrown.vaadinaddons.highchartsapi.model.data.base.DoubleData;
import at.downdrown.vaadinaddons.highchartsapi.model.plotoptions.BarChartPlotOptions;
import at.downdrown.vaadinaddons.highchartsapi.model.series.BarChartSeries;

import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.VerticalLayout;

import fr.emac.gipsi.gsi.truptil.db.DBManager;
import fr.emac.gipsi.gsi.truptil.element.Pronostic;
import fr.emac.gipsi.gsi.truptil.extract.Match;


/**
 * @author truptil
 *
 */
public class BarChart {

	/**
	 * 
	 */
	public BarChart() {
		// TODO Auto-generated constructor stub
	}

	
	public VerticalLayout createBarChart(Match m){
		VerticalLayout vl = new VerticalLayout();
		
		 ChartConfiguration columnConfiguration = new ChartConfiguration();
	//        columnConfiguration.setTitle("Resultat Pronostique");
	        columnConfiguration.setChartType(ChartType.BAR);

	        BarChartPlotOptions columnPlotOptions = new BarChartPlotOptions();
	        columnConfiguration.setPlotOptions(columnPlotOptions);
	        
	        for(Entry<String, Integer> res : createNombreBonPronostic(m).entrySet()){
	        	List<HighChartsData> col = new ArrayList<>();
	        	col.add(new DoubleData(res.getValue().doubleValue()));
	        	BarChartSeries colColumn = new BarChartSeries(res.getKey(), col);
	        	  columnConfiguration.getSeriesList().add(colColumn);
	     	     
	        }
	        
	        try {
	            HighChart columnChart = HighChartFactory.renderChart(columnConfiguration);
	           // columnChart.setHeight(60, Unit.PERCENTAGE);
	          //  columnChart.setWidth(90, Unit.PERCENTAGE);
	           columnChart.setSizeFull();
	            vl.addComponent(columnChart);
	            vl.setComponentAlignment(columnChart, Alignment.MIDDLE_CENTER);
	            vl.setExpandRatio(columnChart, 1.0f);

	        } catch (HighChartsException e) {
	            e.printStackTrace();
	        }
		
	        vl.setSizeUndefined();
	        return vl;
		
	}
	
	private HashMap<String, Integer> createNombreBonPronostic(Match m) {
		
		HashMap<String, Integer> res = new HashMap<String,Integer>();
		res.put("Nombre exact de But",0);
		res.put("Bon Resultat",0);
		res.put("Mauvais Resultat",0);
		
		try {
			List<Pronostic> prono = DBManager.getInstance().getDatastore().find(Pronostic.class).asList();
			
			for(Pronostic p : prono){
				if(p.getIdMatch().equals(m.getId())){
					if(p.getScore1()== m.getScore1() && p.getScore2() == m.getScore2()){
						res.put("Nombre exact de But", res.get("Nombre exact de But")+1);
					}else if(p.getScore1()-m.getScore1() == p.getScore2()-m.getScore2()){
						res.put("Bon Resultat", res.get("Bon Resultat")+1);
					}else{
						res.put("Mauvais Resultat", res.get("Mauvais Resultat")+1);		
					}
				}
			}
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(res);
		return res;
	}
}
