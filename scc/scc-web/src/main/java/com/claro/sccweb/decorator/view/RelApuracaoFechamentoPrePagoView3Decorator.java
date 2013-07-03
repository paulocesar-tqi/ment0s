package com.claro.sccweb.decorator.view;

import javax.servlet.jsp.PageContext;

import org.displaytag.decorator.TableDecorator;
import org.displaytag.decorator.TotalTableDecorator;
import org.displaytag.model.TableModel;

import com.claro.cobillingweb.persistence.view.RelatorioApuracaoFechamentoPrePagoView;

public class RelApuracaoFechamentoPrePagoView3Decorator extends TableDecorator {

	private double vlrLiquidoApurado = 0;
	
	public RelatorioApuracaoFechamentoPrePagoView getRows(){
		
		RelatorioApuracaoFechamentoPrePagoView entity = (RelatorioApuracaoFechamentoPrePagoView) getCurrentRowObject();
		return entity;
	}
	
	public Double getVlrLiquidoApuradoSumarizado(){
		
		Double valor = 0.0;
		if(getRows().getCdEotHolding().equals("00")){
			this.vlrLiquidoApurado += getRows().getValorApuradoLiquido().doubleValue();
		}
		return valor;
		
	}
	
	public double getVlrLiquidoApurado() {
		return vlrLiquidoApurado;
	}

	public void setVlrLiquidoApurado(double vlrLiquidoApurado) {
		this.vlrLiquidoApurado = vlrLiquidoApurado;
	}

	
	
	
	
	

}
