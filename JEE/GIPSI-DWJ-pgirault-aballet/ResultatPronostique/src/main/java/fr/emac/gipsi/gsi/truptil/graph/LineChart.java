/**
 * 
 */
package fr.emac.gipsi.gsi.truptil.graph;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import at.downdrown.vaadinaddons.highchartsapi.Colors;
import at.downdrown.vaadinaddons.highchartsapi.HighChart;
import at.downdrown.vaadinaddons.highchartsapi.HighChartFactory;
import at.downdrown.vaadinaddons.highchartsapi.exceptions.HighChartsException;
import at.downdrown.vaadinaddons.highchartsapi.model.ChartConfiguration;
import at.downdrown.vaadinaddons.highchartsapi.model.ChartType;
import at.downdrown.vaadinaddons.highchartsapi.model.Margin;
import at.downdrown.vaadinaddons.highchartsapi.model.data.HighChartsData;
import at.downdrown.vaadinaddons.highchartsapi.model.data.base.DoubleData;
import at.downdrown.vaadinaddons.highchartsapi.model.plotoptions.LineChartPlotOptions;
import at.downdrown.vaadinaddons.highchartsapi.model.series.LineChartSeries;

import com.vaadin.ui.VerticalLayout;

import fr.emac.gipsi.gsi.truptil.db.DBManager;
import fr.emac.gipsi.gsi.truptil.element.Joueur;
import fr.emac.gipsi.gsi.truptil.element.Pronostic;
import fr.emac.gipsi.gsi.truptil.element.ResultatMatch;

/**
 * @author truptil
 *
 */
public class LineChart {





	public VerticalLayout createColumChart(Joueur j) throws UnknownHostException, HighChartsException{
		VerticalLayout vl = new VerticalLayout();

		//Line Chart
		ChartConfiguration lineConfiguration = new ChartConfiguration();
		lineConfiguration.getxAxis().setLabelsEnabled(true);
		lineConfiguration.getyAxis().setLabelsEnabled(true);
		lineConfiguration.removeBackgroundLines();
		lineConfiguration.setChartType(ChartType.LINE);
		lineConfiguration.setChartMargin(new Margin(50, 20, 170, 20));
		lineConfiguration.setLegendEnabled(false);

		LineChartPlotOptions lineChartPlotOptions = new LineChartPlotOptions();
		lineChartPlotOptions.setDataLabelsFontColor(Colors.LIGHTGRAY);

		lineConfiguration.setPlotOptions(lineChartPlotOptions);

		List<HighChartsData> joueurValues = new ArrayList<>();
		List<Pronostic> prono = DBManager.getInstance().getDatastore().find(Pronostic.class).asList();
		double res =0.0;
		List<ResultatMatch> rms = DBManager.getInstance().getDatastore().find(ResultatMatch.class).asList();
		for( ResultatMatch rm : rms){
			for(Pronostic p : prono){
				if(p.getIdJoueur().equals(j.getLogin())){
					if(rm.getIdMatch().equals(p.getIdMatch())){
						if(p.getScore1()== rm.getScore1() && p.getScore2() == rm.getScore2()){
							res=res+5;
							joueurValues.add(new DoubleData(res));
						}else if(p.getScore1()-rm.getScore1() == p.getScore2()-rm.getScore2()){
							res=res+1;
							joueurValues.add(new DoubleData(res));
						}else{
							res=res+0;
							joueurValues.add(new DoubleData(res));
						}

					}}}}

		LineChartSeries joueurLine = new LineChartSeries("points", joueurValues);
		lineConfiguration.getSeriesList().add(joueurLine);

		HighChart lineChart = HighChartFactory.renderChart(lineConfiguration);
		lineChart.setSizeFull();
		vl.addComponent(lineChart);

		return vl;
	}

}
