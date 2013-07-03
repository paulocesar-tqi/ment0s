package com.claro.sccweb.controller.graficos.distribuicao;

import java.awt.Color;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.DatasetRenderingOrder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.Range;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RectangleEdge;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.claro.sccweb.controller.BaseFormController;
@Controller
@RequestMapping(value="/user/cdr/distribuicao/grafico")
public class SccDistribuicaoCDRGrafico {

	/**
	 * 
	 */
	@SuppressWarnings("unused")
	private static final long serialVersionUID = -8473868337932121344L;
	
	private static final Long ALOCADO = 500L;
	private static final Long ENCAIMINHADO = 501L;
	private static final Long FATURADO = 502L;
	private static final String TITULO = "Relatório de Distribuição de CDRs";
	
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/graficoBarras")
	public void renderChart(String variation, OutputStream stream,  HttpServletRequest request, HttpServletResponse response) throws Exception {
	    boolean rotate = "rotate".equals(variation); // add ?variation=rotate to the URL to rotate the chart
	    List<ItemGraficoDistribuicao> tabela = (List<ItemGraficoDistribuicao>)request.getSession().getAttribute(BaseFormController.DISPLAY_TAG_SPACE_2);
	    JFreeChart chart = generateChart(tabela);
	    ChartUtilities.writeChartAsPNG(stream, chart, 750, 400);
	}
	 
	private JFreeChart generateChart(List<ItemGraficoDistribuicao> tabela){
		CategoryDataset data = createDataset1(tabela);
		final JFreeChart chart = ChartFactory.createBarChart(
		            TITULO,  // chart title
		            "Ciclo",         // domain axis label
		            "Percentual",            // range axis label
		            data,           // data
		            PlotOrientation.VERTICAL,
		            true,               // include legend
		            true,
		            false
		        );
		chart.setBackgroundPaint(new Color(0xE2, 0xE4, 0xFF));
		chart.getLegend().setPosition( RectangleEdge.TOP );
		
		final CategoryPlot plot = chart.getCategoryPlot();
	    plot.setDomainAxisLocation(AxisLocation.BOTTOM_OR_LEFT);
	    plot.setRangeAxisLocation(AxisLocation.TOP_OR_LEFT);
	    final CategoryItemRenderer renderer1 = plot.getRenderer();
	    final CategoryAxis axis = plot.getDomainAxis();
	    renderer1.setSeriesPaint(0, Color.BLUE);
	    renderer1.setSeriesPaint(2,new Color(0x22, 0xB1, 0x4C));
	    renderer1.setSeriesPaint(1, Color.RED);
	    renderer1.setItemLabelsVisible(true);
	     
	    axis.setCategoryLabelPositions(
	            CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 8.0)
	        );	    

		NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();  
		rangeAxis.setTickUnit( new NumberTickUnit(5) );
		rangeAxis.setAutoRange(false);  
        Range range= new Range( 0.0, 100.0 );  
        rangeAxis.setRange( range );  
	    
	    
	    //final ValueAxis axis2 = new NumberAxis3D("Secondary");
	    //plot.setRangeAxis(1, axis2);
	       
	    plot.mapDatasetToRangeAxis(1, 1);

	    plot.setDatasetRenderingOrder(DatasetRenderingOrder.REVERSE);
	        // OPTIONAL CUSTOMISATION COMPLETED.

	        // add the chart to a panel...
	    final ChartPanel chartPanel = new ChartPanel(chart);
	    chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
	        //setContentPane(chartPanel);		 
		return chart;
	        
	    }



	
	 private CategoryDataset createDataset1(List<ItemGraficoDistribuicao> lstItemGrafico ) {

        final String series1 = "ENC";
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
