package com.claro.sccweb.decorator.rownum.entity;

import com.claro.cobillingweb.persistence.entity.SccAssinaturaPrePago;
import com.claro.sccweb.decorator.rownum.RownumDecorator;

public class SccAssinaturaPrePagoDecorator extends RownumDecorator<SccAssinaturaPrePago> {

	public SccAssinaturaPrePagoDecorator(SccAssinaturaPrePago entity, int rownum) {
		super(entity, rownum);
	}

	@Override
	protected boolean isDeleteEnabled() {
		return false;
	}

	public String getDtReferencia() {
		return formataDate(getRow().getDtInicioFranquia());
	}

	public String getOperadoraClaro() {
		return getRow().getOperadoraClaro().getDsOperadora();
	}

	public String getOperadoraLD() {
		return getRow().getOperadoraLD().getDsOperadora();
	}

	public String getTerminal() {
		return getRow().getNuTelefone();
	}

	public String getStatus() {
		return getRow().getStatusAssinatura().getDsDominio();
	}

	public String getDescricaoPacote() {
		String des = "-";
		if (getRow().getPacotePrepago() != null) {
			des = getRow().getPacotePrepago().getNoPacotePrepago();
		
			des += " " + formataInteger(getRow().getQtMinutosAdquiridos().intValue()) + " min";
		}
		return des;
	}

	public String getProduto() {
		if (getRow().getProdutoPrepago() != null) {
			return getRow().getProdutoPrepago().getCdProdutoPrepago() + " - "
				+ getRow().getProdutoPrepago().getNoProdutoPrepago();
		} else {
			return "-";
		}
	}

	public String getQtdChamadas() {
		return (getRow().getQtdCdr() != null ? getRow().getQtdCdr() : "") + "";
	}

	public String getQtdPacotes() {
		return (getRow().getSqArquivo() != null ? getRow().getSqArquivo() : "") + "";
	}

	public String getQtdConsumida() {
		return formataDouble(getRow().getQtTarifaFranquia());
	}

	public String getDuracaoReal() {
		return (getRow().getHrDuracaoReal() != null ? getRow().getHrDuracaoReal() : "") + "";
	}

	public String getVlBruto() {
		return formataDouble(getRow().getVlBrutoPacote() != null ? getRow().getVlBrutoPacote().doubleValue() : null);
	}
}
