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
import at.downdrown.vaadinaddons.highchartsapi.model.plotoptions.ColumnChartPlotOptions;
import at.downdrown.vaadinaddons.highchartsapi.model.series.ColumnChartSeries;

import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.VerticalLayout;

import fr.emac.gipsi.gsi.truptil.db.DBManager;
import fr.emac.gipsi.gsi.truptil.element.Joueur;
import fr.emac.gipsi.gsi.truptil.element.Pronostic;
import fr.emac.gipsi.gsi.truptil.element.ResultatMatch;
import fr.emac.gipsi.gsi.truptil.extract.Match;
import fr.emac.gipsi.gsi.truptil.util.GestionMatch;


/**
 * @author truptil
 *
 */
public class ColumChart {

	/**
	 * 
	 */
	public ColumChart() {
		// TODO Auto-generated constructor stub
	}

	public VerticalLayout createColumChart(Joueur j){
		VerticalLayout vl = new VerticalLayout();
		
		 ChartConfiguration columnConfiguration = new ChartConfiguration();
	        columnConfiguration.setTitle("Resultat Pronostique");
	        columnConfiguration.setChartType(ChartType.COLUMN);

	        ColumnChartPlotOptions columnPlotOptions = new ColumnChartPlotOptions();
	        columnConfiguration.setPlotOptions(columnPlotOptions);
	        
	        for(Entry<String, Integer> res : GestionMatch.createNombreBonPronostic(j).entrySet()){
	        	List<HighChartsData> col = new ArrayList<>();
	        	col.add(new DoubleData(res.getValue().doubleValue()));
	        	ColumnChartSeries colColumn = new ColumnChartSeries(res.getKey(), col);
	        	  columnConfiguration.getSeriesList().add(colColumn);
	     	     
	        }
	        
	        try {
	            HighChart columnChart = HighChartFactory.renderChart(columnConfiguration);
	   //         columnChart.setHeight(60, Unit.PERCENTAGE);
	    //        columnChart.setWidth(90, Unit.PERCENTAGE);
	           columnChart.setSizeFull();
	            vl.addComponent(columnChart);
	            vl.setComponentAlignment(columnChart, Alignment.MIDDLE_CENTER);
	            vl.setExpandRatio(columnChart, 1.0f);

	        } catch (HighChartsException e) {
	            e.printStackTrace();
	        }
		
	        vl.setSizeFull();
	        return vl;
	}
	
	public VerticalLayout createColumChart(ResultatMatch m){
		VerticalLayout vl = new VerticalLayout();
		
		 ChartConfiguration columnConfiguration = new ChartConfiguration();
	        columnConfiguration.setTitle("Resultat Pronostique");
	        columnConfiguration.setChartType(ChartType.COLUMN);

	        ColumnChartPlotOptions columnPlotOptions = new ColumnChartPlotOptions();
	        columnConfiguration.setPlotOptions(columnPlotOptions);
	        
	        for(Entry<String, Integer> res : GestionMatch.createNombreBonPronostic(m).entrySet()){
	        	List<HighChartsData> col = new ArrayList<>();
	        	col.add(new DoubleData(res.getValue().doubleValue()));
	        	ColumnChartSeries colColumn = new ColumnChartSeries(res.getKey(), col);
	        	  columnConfiguration.getSeriesList().add(colColumn);
	     	     
	        }
	        
	        try {
	            HighChart columnChart = HighChartFactory.renderChart(columnConfiguration);
	            columnChart.setHeight(60, Unit.PERCENTAGE);
	            columnChart.setWidth(90, Unit.PERCENTAGE);
	          // columnChart.setSizeFull();
	            vl.addComponent(columnChart);
	            vl.setComponentAlignment(columnChart, Alignment.MIDDLE_CENTER);
	            vl.setExpandRatio(columnChart, 1.0f);

	        } catch (HighChartsException e) {
	            e.printStackTrace();
	        }
		
	        vl.setSizeFull();
	        return vl;
		
	}
	

	
}
