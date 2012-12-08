/**
 * 
 */
package com.claro.sccweb.decorator.rownum.view;

import com.claro.cobillingweb.persistence.view.DisponibilizacaoPacotePrePagoView;
import com.claro.sccweb.decorator.rownum.RownumDecorator;

/**
 * @author 93046251
 *
 */
public class DisponibilizacaoPacotePrePagoViewDecorator extends RownumDecorator<DisponibilizacaoPacotePrePagoView>{

	public DisponibilizacaoPacotePrePagoViewDecorator(
			DisponibilizacaoPacotePrePagoView entity, int rownum) {
		super(entity, rownum);
	}
	
	@Override
	protected boolean isDeleteEnabled() {
		return false;
	}

	public String getDtReferencia() {
		if (getRow().getDataReferencia() == null)
			return "-";
		return formataDate(getRow().getDataReferencia());
	}

	public String getOperadoraClaro() {
		if (getRow().getOperadoraClaro() == null)
			return "-";
		return getRow().getOperadoraClaro();
	}

	public String getOperadoraLD() {
		if (getRow().getOperadoraLD() == null)
			return "-";
		return getRow().getOperadoraLD();
	}

	public String getTerminal() {
		if (getRow().getTerminal() == null)
			return "-";
		return getRow().getTerminal();
	}

	public String getStatus() {
		if (getRow().getStatus() == null)
			return "-";
		return getRow().getStatus();
	}

	public String getDescricaoPacote() {
		if (getRow().getDescricaoPacote() == null)
			return "-";
		return getRow().getDescricaoPacote();
	}

	public String getQtdPacotes() {
		return (getRow().getQtdPacotes() != null ? getRow().getQtdPacotes() + "" : "-");
	}
	
	public String getProduto() {
		if (getRow().getCdProdutoPrepago() == null)
			return "-";
		return getRow().getCdProdutoPrepago();
	}

	public String getQtdChamadas() {
		return (getRow().getQtdChamadas() != null ? getRow().getQtdChamadas() : "-") + "";
	}

	public String getQtdConsumida() {
		if (getRow().getQtdConsumida() == null)
			return "-";
		return formataDouble(getRow().getQtdConsumida());
	}

	public String getDuracaoReal() {
		return (getRow().getDuracaoReal() != null ? getRow().getDuracaoReal() : "-") + "";
	}

	public String getVlBruto() {
		if (getRow().getValorBruto() == null)
			return "-";
		return formataDouble(getRow().getValorBruto());
	}
	

}
