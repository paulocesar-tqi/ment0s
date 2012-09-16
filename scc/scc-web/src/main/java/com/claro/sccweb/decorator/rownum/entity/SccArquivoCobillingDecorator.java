package com.claro.sccweb.decorator.rownum.entity;

import com.claro.cobillingweb.persistence.entity.SccArquivoCobilling;
import com.claro.sccweb.decorator.rownum.RownumDecorator;

public class SccArquivoCobillingDecorator extends RownumDecorator<SccArquivoCobilling>{
	
	private SccArquivoCobilling arquivoProtocolo;
	
	public SccArquivoCobillingDecorator(SccArquivoCobilling entity, int rownum) {
		super(entity, rownum);
	}
	
	public SccArquivoCobillingDecorator(SccArquivoCobilling entity, SccArquivoCobilling arquivoProtocolo ,int rownum) {
		super(entity, rownum);
		this.arquivoProtocolo = arquivoProtocolo;
	}
	
	public SccArquivoCobilling getArquivoProtocolo() {
		return arquivoProtocolo;
	}
	
	public String getSeqArquivo() {
		return getRow().getSqArquivo().toString();
	}
	
	public String getDataConnect() {
		return formataDate(getRow().getDtConnect());
	}
	
	public String getDataReferencia() {
		return formataDate(getRow().getDtProcExterna());
	}
	
	public String getDataProcClaro() {
		return formataDate(getRow().getDtProcClaro());
	}
	
	public String getDataProcPPC() {
		return formataDate(getRow().getDtProcExterna());		
	}
	
	public String getOperadoraClaro() {
		return getRow().getOperadoraClaro().getDsOperadora();
	}
	
	public String getOperadoraExterna() {
		return getRow().getOperadoraExterna().getDsOperadora();
	}
	
	public String getNomeArquivo() {
		return formataString(getRow().getNoArquivo());
	}
	
	public String getNomeArquivoLink() {
		return "<a href='#' onClick='download("+getRownum()+")'> "+getRow().getNoArquivo()+" </a>";		
	}
	
	public String getNoComposto() {
		StringBuffer sb = new StringBuffer();
		if (getRow().getTipoArquivo().getCdTipoBatimento().equals("RET") || getRow().getTipoArquivo().getCdTipoBatimento().equals("QRT") || getRow().getTipoArquivo().getCdTipoBatimento().equals("COB")) {
			sb.append(formataString(getRow().getNoArquivo()));
			sb.append("</br>");
			if (this.arquivoProtocolo != null) {
				sb.append(formataString(arquivoProtocolo.getNoArquivo()));
				sb.append("</br>");
			}
			sb.append(formataString(getRow().getNoArquivoTrans()));
		} else if (getRow().getTipoArquivo().getCdTipoBatimento().equals("REM") || getRow().getTipoArquivo().getCdTipoBatimento().equals("FIS") || getRow().getTipoArquivo().getCdTipoBatimento().equals("QRM")) {
			sb.append(formataString(getRow().getNoArquivoIfc()));
			sb.append("</br>");
			if (this.arquivoProtocolo != null) {
				sb.append(formataString(arquivoProtocolo.getNoArquivo()));
				sb.append("</br>");
			}
			sb.append(formataString(getRow().getNoArquivo()));
		}
		return sb.toString();
	}
	/*
	public String getNoComposto() {
		StringBuffer sb = new StringBuffer();
		sb.append(formataString(getRow().getNoArquivoIfc()));
		sb.append("</br>");
		if (this.arquivoProtocolo != null) {
			sb.append(formataString(arquivoProtocolo.getNoArquivo()));
			sb.append("</br>");
		}
		sb.append(formataString(getRow().getNoArquivo()));		
		return sb.toString();
	}
	*/
	public String getMinutosTarifados() {
		return formataInteger(getRow().getQtDuracaoTarifada());
	}
	
	public String getQtCDR() {
		return formataInteger(getRow().getQtRegistros());
	}
	
	public String getQtDuracaoReal() {
		return formataInteger(getRow().getQtDuracaoReal());
	}
	
	public String getQtDuracaoTarifada() {
		return formataInteger(getRow().getQtDuracaoTarifada());
	}
	
	public String getValorLiquido() {
		return formataDouble(getRow().getVlLiquidoArquivo());
	}
	
	public String getValorBruto() {
		return formataDouble(getRow().getVlBrutoArquivo());
	}
	
	public String getStatus() {
		return getRow().getCdStatusArquivo().getDsStatusArquivo();
	}
	
	public String getDataInicioTrafego() {
		return formataDate(getRow().getDtInicioTrafego());
	}
	
	public String getDataFinalTrafego() {
		return formataDate(getRow().getDtFimTrafego());
	}
	
	public String getSucesso() {
		return "<a href='#' onClick='visualizarOK("+getRownum()+")'> Ciclo </a>";
	}
	
	public String getCdCiclo() {
		return exibeInteger(getRow().getCdCiclo());
	}
	
	public String getAaCiclo() {
		return exibeInteger(getRow().getAaCiclo());
	}
	
	public String getMmCiclo() {
		return exibeInteger(getRow().getMmCiclo());
	}
	
	public String getErro() {
		return "<a href='#' onClick='visualizarNOK("+getRownum()+")'> Motivo </a>";
	}
	
	public String getStatusProtocolo() {
		if ((getRow().getCdErroProtocolo() == null) || (getRow().getCdErroProtocolo().equalsIgnoreCase("OK"))) {
			return "ARQUIVO OK";
		} else {
			return "ARQUIVO NÃO OK";
		}
	}
	
	protected boolean isDeleteEnabled() {
		return false;
	}
	
}
