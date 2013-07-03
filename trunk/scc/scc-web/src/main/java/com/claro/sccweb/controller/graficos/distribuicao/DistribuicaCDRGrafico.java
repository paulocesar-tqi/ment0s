package com.claro.sccweb.controller.graficos.distribuicao;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.AbstractView;

import com.claro.sccweb.controller.BaseFormController;
//import com.eteks.java2d.PJABufferedImage;
import com.claro.sccweb.form.DistribuicaoCDRForm;
import com.claro.sccweb.form.SccDisputaForm;


/**
 * Aproveitamento do código de Leandro Yung & 13006152 Renato Caires de Oliveira
 * sds.systechnet@gmail.com .
 * 
 */
//@Controller
//@RequestMapping(value="/user/cdr/distribuicao/grafico")
public class DistribuicaCDRGrafico extends AbstractView {

	 	private final static int DEFAULT_WIDTH = 550;
	    private final static int DEFAULT_HEIGHT = 350;
	    private final static String title = "Relatório de Distribuição de CDRs";
	    private static final  NumberFormat nf;
	    private final SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
	    
	    static {
	    	Locale locale = new Locale("pt","BR");
	    	
	        nf = NumberFormat.getPercentInstance(locale);
	        nf.setMinimumFractionDigits(2);
	        nf.setMaximumFractionDigits(2);
	    }
	
	//@RequestMapping("/graficoBarras.png")	 
	public void renderMergedOutputModel(Map<String, Object> model,HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//DistribuicaoCDRForm form = (DistribuicaoCDRForm) model.get("filtro");
		//List<ItemGraficoDistribuicao> tabela = (List<ItemGraficoDistribuicao>) form.getLstItemGrafico();
		 List<ItemGraficoDistribuicao> tabela = (List<ItemGraficoDistribuicao>)request.getSession().getAttribute(BaseFormController.DISPLAY_TAG_SPACE_2);		
		 ServletOutputStream out = response.getOutputStream();
		 BufferedImage bImg = createChart(tabela, DEFAULT_WIDTH, DEFAULT_HEIGHT);
		 ImageIO.write(bImg, "png", out);
	}

	
	public  BufferedImage createChart(List<ItemGraficoDistribuicao> billingItem,int prefWidth, int prefHeight) {
        System.setProperty ("java.awt.headless", "true");
        
        BufferedImage bImg = null;
        Graphics2D g2d = null;
        Rectangle2D r = null;
        GradientPaint color = null;
        int size;
        int width, height;
        int xpos, ypos;
        int barWidth, barHeight;
        
        if (billingItem!=null && billingItem.size()>0) {
            size = billingItem.size();
            width = size * 100 + 20 > prefWidth ? size*100 + 20 : prefWidth;
            barWidth = (int)((width-20) / size / 2) -5;
            height = prefHeight < DEFAULT_HEIGHT ? DEFAULT_HEIGHT : prefHeight;
            barHeight = height - 110;
            
            try {
            	bImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            } catch (Exception e) {
            	
            } finally {
            	if (bImg==null) {
            		//System.setProperty ("awt.toolkit" , "com.eteks.awt.PJAToolkit");
                    System.setProperty ("java2d.font.usePlatformFont", "false"); 
                    System.setProperty ("java.awt.fonts", "./fonts");
                    //System.setProperty ("java.awt.graphicsenv", "com.eteks.java2d.PJAGraphicsEnvironment");
                    
                	//bImg = new PJABufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            	}
            }
            Font font1 = new Font("", Font.PLAIN, 10);
            Font font2 = new Font("", Font.PLAIN, 9);
            
            g2d = (Graphics2D)bImg.createGraphics();
            g2d.setPaint(Color.WHITE);
            r = new Rectangle2D.Double(0, 0, width, height);
            g2d.draw(r);
            g2d.fill(r);
            
            g2d.setPaint(Color.BLACK);
            r = new Rectangle2D.Double(width-185, 5, 180, 30);
            g2d.draw(r);
            
            g2d.setFont(new Font("", Font.BOLD, 12));
            
            color = getBlueColor(width-180, 10, 20, 20);
            g2d.setPaint(color);
            r = new Rectangle2D.Double(width-180, 10, 20, 20);
            g2d.draw(r);
            g2d.fill(r);
            g2d.setPaint(Color.BLACK);
            g2d.drawString("ENC", width-150, 25);
            
            color = getRedColor(width-115, 10, 20, 20);
            g2d.setPaint(color);
            r = new Rectangle2D.Double(width-115, 10, 20, 20);
            g2d.draw(r);
            g2d.fill(r);
            g2d.setPaint(Color.BLACK);
            g2d.drawString("AL", width-85, 25);
            
            color = getGreenColor(width-60, 10, 20, 20);
            g2d.setPaint(color);
            r = new Rectangle2D.Double(width-60, 10, 20, 20);
            g2d.draw(r);
            g2d.fill(r);
            g2d.setPaint(Color.BLACK);
            g2d.drawString("FA", width-30, 25);
            
            g2d.setPaint(Color.RED);
            g2d.setFont(new Font("", Font.BOLD, 12));
            g2d.drawString(title, 10, 15);
            
            // Draw Scale Axis
            g2d.setPaint(Color.GRAY);
            drawBar(g2d, 10, height-50-barHeight, width-10, 1);
            drawBar(g2d, 10, height-50-(int)(barHeight/2), width-10, 1);
            
            g2d.setPaint(Color.LIGHT_GRAY);
            drawBar(g2d, 10, height-50-(int)(barHeight/4), width-10, 1);
            drawBar(g2d, 10, height-50-(int)(3*barHeight/4), width-10, 1);
            
            g2d.setPaint(Color.BLACK);
            drawBar(g2d, 10, height-50, width-10, 1);
            drawBar(g2d, 10, height-50-barHeight, 1, barHeight+38);
            drawBar(g2d, 10, height-12, width-10, 1);
            drawBar(g2d, 6, height-50-barHeight, 8, 1);
            drawBar(g2d, 6, height-50-(int)(barHeight/2), 8, 1);
            drawBar(g2d, 6, height-50, 8, 1);
            drawBar(g2d, 8, height-50-(int)(barHeight/4), 4, 1);
            drawBar(g2d, 8, height-50-(int)(3*barHeight/4), 4, 1);
            
            g2d.setFont(new Font("", Font.PLAIN, 9));
            g2d.drawString("100", 0, height-52-barHeight);
            g2d.drawString("50", 0, height-40-(int)(barHeight/2));
            g2d.drawString("0", 0, height-40);
            g2d.drawString("Ciclos", width-40, height);
            
            g2d.setPaint(Color.BLACK);
            g2d.setFont(font1);
            g2d.drawString("SCC Sistema de Controle de Cobilling", 10, 25);
            g2d.drawString("% de CDRs Faturáveis = Encaminhado + AL + FA", 
                    10, 38);
            g2d.drawString("Criado em: " + sd.format(Calendar.getInstance().getTime()), width-180, 45);
            
            for (int cont=0; cont<billingItem.size(); cont++) {
            	ItemGraficoDistribuicao item = billingItem.get(cont);
                if (item.getMmCiclo() ==0 && item.getAaCiclo()==0) {
                    ypos = (height - 50) - (int)
                        (item.getValorEncaminhado() * barHeight);
                    xpos = cont * (2*barWidth + 10) + 25;
                    g2d.setPaint(Color.BLACK);
                    g2d.setFont(font1);
                    g2d.drawString(item.getDescricao(), xpos+(barWidth/3), 
                            height-20);
                    drawBar(g2d, xpos+(barWidth/2)+25, height-14, 1, 8);
                    g2d.setFont(font2);
                    g2d.drawString(item.getValorEncaminhado().toString(), xpos-3, height-35);
                    g2d.drawString(item.getValorAlocado().toString(), xpos+barWidth*2/3+5, height-35);
                    g2d.drawString(item.getValorFaturado().toString(), xpos+barWidth*5/3-5,
                            height-35);
                    
                    color = getBlueColor(xpos, ypos, barWidth*2/3, barHeight);
                    g2d.setPaint(color);
                    drawBar(g2d, xpos, ypos, barWidth*2/3, (int)
                            (item.getValorEncaminhado() * barHeight));
                    
                    xpos += barWidth*2/3;
                    ypos = (height - 50) - (int)
                        (item.getValorAlocado() * barHeight);
                    color = getRedColor(xpos, ypos, barWidth*2/3, barHeight);
                    g2d.setPaint(color);
                    drawBar(g2d, xpos, ypos, barWidth*2/3, (int)
                            (item.getValorAlocado() * barHeight));
                    
                    xpos += barWidth*2/3;
                    ypos = (height - 50) - (int)
                        (item.getValorFaturado() * barHeight);
                    color = getGreenColor(xpos, ypos, barWidth*2/3, barHeight);
                    g2d.setPaint(color);
                    drawBar(g2d, xpos, ypos, barWidth*2/3, (int)
                            (item.getValorFaturado() * barHeight));
                    
                } else {
                    ypos = (height - 50) - (int)
                        (item.getValorAlocado() * barHeight);
                    xpos = cont * (2*barWidth + 10) + 25;
                    
                    g2d.setPaint(Color.LIGHT_GRAY);
                    drawBar(g2d, xpos-5, height-50-barHeight, 1, barHeight);
                    
                    g2d.setPaint(Color.BLACK);
                    g2d.setFont(font1);
                    g2d.drawString(item.getDescricao(), xpos+(barWidth/2)+10, 
                            height-20);
                    drawBar(g2d, xpos+(barWidth/2)+25, height-14, 1, 8);
                    g2d.setFont(font2);
                    g2d.drawString(item.getValorAlocado().toString(), xpos+3, height-35);
                    g2d.drawString(item.getValorFaturado().toString(), xpos+barWidth,
                            height-35);
                    
                    color = getRedColor(xpos, ypos, barWidth, barHeight);
                    g2d.setPaint(color);
                    drawBar(g2d, xpos, ypos, barWidth, (int)
                            (item.getValorAlocado() * barHeight));
                    
                    xpos = xpos + barWidth;
                    ypos = (height - 50) - (int)
                        (item.getValorFaturado() * barHeight);
                    
                    color = getGreenColor(xpos, ypos, barWidth, barHeight);
                    g2d.setPaint(color);
                    drawBar(g2d, xpos, ypos, barWidth, (int)
                            (item.getValorFaturado() * barHeight));
                }
            }
        } else {
            
//            bImg = new PJABufferedImage(prefWidth, prefHeight, 
//                    BufferedImage.TYPE_BYTE_INDEXED);
            g2d = bImg.createGraphics();
            
            g2d.setPaint(Color.WHITE);
            r = new Rectangle2D.Double(0, 0, prefWidth, prefHeight);
            g2d.draw(r);
            g2d.fill(r);
            
            g2d.setPaint(Color.BLACK);
            g2d.setFont(new Font("", Font.BOLD, 32));
            g2d.drawString("No Data Found", 50, 100);
        }
        
        return bImg;
    }
    
    private static void drawBar(Graphics2D g2d, int x, int y, int width, 
            int height) {
        Rectangle2D r = new Rectangle2D.Double(x, y, width, height);
        g2d.draw(r);
        g2d.fill(r);
    }
    
    private static GradientPaint getRedColor(int x, int y, int width, 
            int height) {
        GradientPaint redtowhite = new GradientPaint(x-5, y-5, 
                Color.RED, x+width+50, y+10, Color.BLACK);
        return redtowhite;
    }
    
    private static GradientPaint getGreenColor(int x, int y, int width, 
            int height) {
        GradientPaint redtowhite = new GradientPaint(x-5, y-5, 
                Color.GREEN, x+width+50, y+10, Color.BLACK);
        return redtowhite;
    }
    
    private static GradientPaint getBlueColor(int x, int y, int width, 
            int height) {
        GradientPaint redtowhite = new GradientPaint(x-5, y-5, 
                Color.BLUE, x+width+50, y+10, Color.BLACK);
        return redtowhite;
    }
	
	
	
	
}
