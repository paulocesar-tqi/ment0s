/**
 * 
 */
package com.claro.sccweb.controller.graficos.distribuicao;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.claro.sccweb.controller.BaseFormController;

/**
 * @author vagner.souza
 *
 */
//@Controller
//@RequestMapping(value="/user/cdr/distribuicao/grafico")
public class GeradorGrafico {
	
	private static final Long ALOCADO = 500L;
	private static final Long ENCAIMINHADO = 501L;
	private static final Long FATURADO = 502L;
	private static final String TITULO = "Relatório de Distribuição de CDRs";

	
	 public GeradorGrafico() {

	 }
	 
	 /**

	     * Gera um gráfico de barras 3D Vertical

	     * @param tituloGrafico String, o titulo do gráfico

	     * @param tituloEixoX String, o titulo do eixo X

	     * @param tituloEixoY String, o titulo do eixo Y

	     * @param arrayValores ArrayList, a lista com os valores para o gráfico

	     * @return BufferedImage, a imagem do Gráfico gerada

	     *

	     * A classe DefaultCategoryDataset recebe os valores que irão gerar o gráfico

	     * DefaultCategoryDataset.addValue(Number, Comparable, Comparable)

	     * DefaultCategoryDataset.addValue(Double, Comparable, Comparable)

	     */


	@SuppressWarnings("unchecked")
	//@RequestMapping("/graficoBarras.png")
	public static BufferedImage gerarGraficoBarraVertical3D(String tituloGrafico, String tituloEixoX, String tituloEixoY, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		List<ItemGraficoDistribuicao> tabela = (List<ItemGraficoDistribuicao>) request
				.getSession().getAttribute(
						BaseFormController.DISPLAY_TAG_SPACE_2);

		BufferedImage buf = null;

		try {

			DefaultCategoryDataset defaultCategoryDataset = new DefaultCategoryDataset();

			Iterator iterator = tabela.iterator();

			while (iterator.hasNext()) {

				ItemGraficoDistribuicao modelo = (ItemGraficoDistribuicao) iterator
						.next();
				
				if(modelo.getCdStatusCdr().equals(ALOCADO)){
					defaultCategoryDataset.addValue(modelo.getValorAlocado(), modelo.getCdStatusCdr(), modelo.getDescricao());
				}
				if(modelo.getCdStatusCdr().equals(ENCAIMINHADO)){
					defaultCategoryDataset.addValue(modelo.getValorEncaminhado(), modelo.getCdStatusCdr(), modelo.getDescricao());
				}
				
				if(modelo.getCdStatusCdr().equals(FATURADO)){
					defaultCategoryDataset.addValue(modelo.getValorFaturado(), modelo.getCdStatusCdr(), modelo.getDescricao());
				}
				

			}

			JFreeChart chart = ChartFactory.createBarChart3D(tituloGrafico,
					tituloEixoX,

					tituloEixoY, defaultCategoryDataset,
					PlotOrientation.VERTICAL,

					true, false, false);

			chart.setBorderVisible(true);

			chart.setBorderPaint(Color.black);

			buf = chart.createBufferedImage(400, 250);

		} catch (Exception e) {

			throw new Exception(e);

		}

		return buf;

	}

}
