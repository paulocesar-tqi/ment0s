package com.claro.sccweb.controller.graficos.evolucao;

import java.awt.Color;
import java.awt.GradientPaint;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.web.servlet.view.AbstractView;

import com.claro.sccweb.controller.BaseFormController;
import com.claro.sccweb.service.data.PeriodoCDR;

public class EvolucaoAceitosView extends AbstractView {

	     private String titulo;
	    private String serieName;	    
	    private static final String domainAxis = "Mês Ref - Relatório SCC ";
	    private static final GradientPaint gp0 = new GradientPaint(
	            						0.0f, 0.0f, Color.RED, 
	            						0.0f, 0.0f, Color.BLACK
	        							);
	    private static final int defaultX = 800;
	    private static final int defaultY = 400;
	    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	    
	     
		protected void renderMergedOutputModel(Map<String, Object> model,HttpServletRequest request, HttpServletResponse response) throws Exception {
			
			List<PeriodoCDR> periodos = (List<PeriodoCDR>)request.getSession().getAttribute(BaseFormController.DISPLAY_TAG_SPACE_1);				
			
			int x = defaultX;
	    	int y = defaultY;
	    	response.setContentType("image/png");
	        ServletOutputStream out = response.getOutputStream();
	        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	    	
	    	for (int i=0;i<periodos.size();i++)
				{
	    		PeriodoCDR periodo = periodos.get(i);
				dataset.addValue((periodo.getAceitos()), serieName, periodo.getMesAno());
				}
			
	        
	        
	        	if (periodos.size() > 10) {
	        		x = periodos.size() * 80;
	        	}
	           
	            JFreeChart chart = ChartFactory.createBarChart(
	    				titulo,
	    				domainAxis + dateFormat.format((new Date())),
	    	            "",
	    	            dataset,
	    	            PlotOrientation.VERTICAL,
	    	            false,
	    	            true,
	    	            false
	    			);
	            if (chart!=null) {
		    		chart.setBackgroundPaint(Color.white);
		    		CategoryPlot plot = chart.getCategoryPlot();
		    		CategoryItemRenderer renderer = plot.getRenderer();
		    		renderer.setSeriesPaint(0, gp0);
		    		ChartUtilities.writeChartAsPNG(out, chart, x, y);
	            }
	        
		}

		public String getTitulo() {
			return titulo;
		}

		public void setTitulo(String titulo) {
			this.titulo = titulo;
		}

		public String getSerieName() {
			return serieName;
		}

		public void setSerieName(String serieName) {
			this.serieName = serieName;
		}

	
	    
	
}
