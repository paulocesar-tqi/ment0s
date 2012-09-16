package com.claro.sccweb.controller.graficos.evolucao;

import java.awt.Color;
import java.awt.GradientPaint;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.claro.sccweb.service.data.GrupoCDR;
import com.claro.sccweb.service.data.GrupoStatusConstants;
import com.claro.sccweb.service.data.PeriodoCDR;

public class EvolucaoEncaminhadoReciclar extends AbstractView {

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private static final String title = "Evolução Encaminhado e A Reciclar";
    private static final String serie1Name = "% Encaminhado";
    private static final String serie2Name = "% A Reciclar";
    private static final String domainAxis = "Mês Ref - Relatório SCC ";
    private static final GradientPaint gp0 = new GradientPaint(
			0.0f, 0.0f, Color.RED, 
			0.0f, 0.0f, Color.BLACK
			);
    private static final GradientPaint gp1 = new GradientPaint(
            						0.0f, 0.0f, Color.BLUE, 
            						0.0f, 0.0f, Color.BLACK
        							);
    private static final int defaultX = 800;
    private static final int defaultY = 400;
	
	 
	protected void renderMergedOutputModel(Map<String, Object> model,HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		List<PeriodoCDR> periodos = (List<PeriodoCDR>)request.getSession().getAttribute(BaseFormController.DISPLAY_TAG_SPACE_1);				
		List<GrupoCDR> dados1 = new ArrayList<GrupoCDR>();
		List<GrupoCDR> dados2 = new ArrayList<GrupoCDR>();
		int x = defaultX;
    	int y = defaultY;
		for (int i=0;i<periodos.size();i++)
			{
			PeriodoCDR periodo = periodos.get(i);
			for (int c=0;c<periodo.getCdrs().size();c++)
				{
				if (periodo.getCdrs().get(c).getGrupoStatus().equals(GrupoStatusConstants.GRUPO_CDR_ENCAMINHADO))
					{
					periodo.getCdrs().get(c).setMesAno(periodo.getMesAno());
					dados1.add(periodo.getCdrs().get(c));
					}
				else if (periodo.getCdrs().get(c).getGrupoStatus().equals(GrupoStatusConstants.GRUPO_CDR_A_RECICLAR))
					{
					periodo.getCdrs().get(c).setMesAno(periodo.getMesAno());
					dados2.add(periodo.getCdrs().get(c));
					}
				}
			}
		response.setContentType("image/png");
        ServletOutputStream out = response.getOutputStream();
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        
        	if (dados1.size() > 10)  {
        		x = dados1.size() * 80;
        	}
        	
        	else if (dados2.size() > 10)  {
        		x = dados2.size() * 80;
        	}
        	
        	
            for (int cont=0; cont<dados1.size(); cont++) {
            	GrupoCDR grupo = dados1.get(cont);
            	dataset.addValue((grupo.getPercentual()), serie1Name, grupo.getMesAno());
            }
            
            for (int cont=0; cont<dados2.size(); cont++) {
            	GrupoCDR grupo = dados2.get(cont);
            	dataset.addValue((grupo.getPercentual()), serie2Name, grupo.getMesAno());
            }
            
            
            JFreeChart chart = ChartFactory.createBarChart(
            		title,
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
				renderer.setSeriesPaint(1, gp1);
	    		ChartUtilities.writeChartAsPNG(out, chart, x, y);
            }
		
	}

	
	
}
