/**
 * 
 */
package com.claro.sccweb.controller.graficos.distribuicao;

import java.awt.Color;
import java.awt.GradientPaint;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StackedBarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.claro.sccweb.controller.BaseFormController;
import com.lowagie.text.Font;

/**
 * @author vagner.souza
 *
 */
//@Controller
//@RequestMapping(value="/user/cdr/distribuicao/grafico")
public class SccDistribuicaoGrafico {
	
	private static final Long ALOCADO = 500L;
	private static final Long ENCAIMINHADO = 501L;
	private static final Long FATURADO = 502L;
	private static final String TITULO = "Relatório de Distribuição de CDRs";

	
	 //@RequestMapping("/graficoBarras.png")
	  public void renderChart(String variation, OutputStream stream,  HttpServletRequest request, HttpServletResponse response) throws Exception {
	    boolean rotate = "rotate".equals(variation); // add ?variation=rotate to the URL to rotate the chart
	    List<ItemGraficoDistribuicao> tabela = (List<ItemGraficoDistribuicao>)request.getSession().getAttribute(BaseFormController.DISPLAY_TAG_SPACE_2);
	    JFreeChart chart = generateChart(rotate, tabela);
	    ChartUtilities.writeChartAsPNG(stream, chart, 750, 400);
	  }
	 
	  private JFreeChart generateChart(boolean rotate, List<ItemGraficoDistribuicao> tabela){
		CategoryDataset data = getDataset(tabela);
	    return ChartFactory.createBarChart(TITULO, // title
	        "Percentual",  // x-axis label
	        "Ciclo",  // y-axis label
	        data,
	        rotate ? PlotOrientation.HORIZONTAL : PlotOrientation.VERTICAL,
	        true,      // legend displayed
	        true,      // tooltips displayed
	        false );   // no URLs*/
	  }
	  
	  private static JFreeChart createChart(DefaultCategoryDataset dataset){
	        ValueAxis valueAxis = new NumberAxis("Percentual");
	 
	        CategoryAxis categoryAxis = new CategoryAxis("Ciclo");
	        categoryAxis.setLowerMargin(0.01d); // indent first bar
	        categoryAxis.setUpperMargin(0.01d); // indent last bar
	        categoryAxis.setCategoryMargin(0.1d); // space between categories
	 
	        BarRenderer renderer = new StackedBarRenderer();
	        renderer.setItemMargin(0d); // remove space between items
	        CategoryPlot categoryPlot = new CategoryPlot(dataset, categoryAxis, valueAxis, renderer);
	 
	        // change the colours of the bars
/*
	        GradientPaint gradient1 =
	                new GradientPaint( 0.0f, 0.0f, new Color(51, 104, 155, 196),
	                        0.0f, 0.0f, new Color(51, 104, 155, 64) );
	        GradientPaint gradient2 =
	                new GradientPaint( 0.0f, 0.0f, new Color(0, 140, 208, 196),
	                        0.0f, 0.0f, new Color(0, 140, 208, 64) );
*/	        
	        categoryPlot.getRenderer(0).setSeriesPaint(0, Color.BLUE);
	        categoryPlot.getRenderer(0).setSeriesPaint(1, Color.RED);
	        categoryPlot.getRenderer(0).setSeriesPaint(2, Color.GREEN);
	 
	        // bring it all together
	        return new JFreeChart(TITULO, categoryPlot);
	        
	    }
	  
		 private CategoryDataset getDataset(List<ItemGraficoDistribuicao> lstItemGrafico ) {

		        final String series1 = "Enc";
		        final String series2 = "AL";
		        final String series3 = "FAT";
				 
				final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

				for (ItemGraficoDistribuicao entidade : lstItemGrafico) {
					
					if(entidade.getCdStatusCdr().equals(ENCAIMINHADO)){
						dataset.addValue(entidade.getValorEncaminhado(), series1, entidade.getDescricao());
					}
					if(entidade.getCdStatusCdr().equals(ALOCADO)){
						dataset.addValue(entidade.getValorAlocado(), series2, entidade.getDescricao());
					}
					
					if(entidade.getCdStatusCdr().equals(FATURADO)){
						dataset.addValue(entidade.getValorFaturado(), series3, entidade.getDescricao());
					}

					
				}

			        return dataset;

			 }
	  

}
