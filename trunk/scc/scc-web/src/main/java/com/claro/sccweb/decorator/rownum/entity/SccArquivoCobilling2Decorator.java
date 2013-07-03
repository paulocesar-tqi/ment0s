package com.claro.sccweb.decorator.rownum.entity;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.displaytag.decorator.TableDecorator;

import com.claro.cobillingweb.persistence.entity.SccArquivoCobilling;

public class SccArquivoCobilling2Decorator extends TableDecorator{
	
	private SccArquivoCobilling arquivoProtocolo;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
	protected SimpleDateFormat datePeriodoFormat = new SimpleDateFormat("MM/yyyy");
	protected static NumberFormat decimalFormat = new DecimalFormat("#.##");
	protected static NumberFormat decimalFormat34 = new DecimalFormat("###.####");
	protected static NumberFormat integerFormat = new DecimalFormat("#.##");

	
/*	public SccArquivoCobillingDecorator(SccArquivoCobilling entity, int rownum) {
		super(entity, rownum);
	}
	
	public SccArquivoCobillingDecorator(SccArquivoCobilling entity, SccArquivoCobilling arquivoProtocolo ,int rownum) {
		super(entity, rownum);
		this.arquivoProtocolo = arquivoProtocolo;
	}
*/	
	public SccArquivoCobilling getArquivoProtocolo() {
		arquivoProtocolo = (SccArquivoCobilling) getCurrentRowObject();
		return arquivoProtocolo ;
	}
	
	public String getSeqArquivo() {
		return getArquivoProtocolo().getSqArquivo().toString();
	}
	
	public String getDataConnect() {
		return formataDate(getArquivoProtocolo().getDtConnect());
	}
	
	public String getDataReferencia() {
		
		return formataDate(getArquivoProtocolo().getDtProcExterna()); 

	}
	
	public String getDataProcClaro() {
		
		return formataDate(getArquivoProtocolo().getDtProcClaro());
	}
	
	public String getDataProcPPC() {
		return formataDate(getArquivoProtocolo().getDtProcExterna());		
	}
	
	public String getOperadoraClaro() {
		return getArquivoProtocolo().getOperadoraClaro().getDsOperadora();
	}
	
	public String getOperadoraExterna() {
		return getArquivoProtocolo().getOperadoraExterna().getDsOperadora();
	}
	
	public String getNomeArquivo() {
		return formataString(getArquivoProtocolo().getNoArquivo());
	}
	
/*	public String getNomeArquivoLink() {
		return "<a href='#' onClick='download("+getRownum()+")'> "+getRow().getNoArquivo()+" </a>";		
	}
	
	public String getNoComposto() {
		StringBuffer sb = new StringBuffer();
		if(getArquivoProtocolo().getTipoArquivo().getCdTipoBatimento() != null){
			if (getArquivoProtocolo().getTipoArquivo().getCdTipoBatimento().equals("RET") || getArquivoProtocolo().getTipoArquivo().getCdTipoBatimento().equals("QRT") || getArquivoProtocolo().getTipoArquivo().getCdTipoBatimento().equals("COB")) {
				sb.append(formataString(getArquivoProtocolo().getNoArquivo()));
				sb.append("</br>");
				if (this.arquivoProtocolo != null) {
					sb.append(formataString(arquivoProtocolo.getNoArquivo()));
					sb.append("</br>");
				}
				sb.append(formataString(getArquivoProtocolo().getNoArquivoTrans()));
			} else if (getArquivoProtocolo().getTipoArquivo().getCdTipoBatimento().equals("REM") || getArquivoProtocolo().getTipoArquivo().getCdTipoBatimento().equals("FIS") || getArquivoProtocolo().getTipoArquivo().getCdTipoBatimento().equals("QRM")) {
				sb.append(formataString(getArquivoProtocolo().getNoArquivoIfc()));
				sb.append("</br>");
				if (this.arquivoProtocolo != null) {
					sb.append(formataString(arquivoProtocolo.getNoArquivo()));
					sb.append("</br>");
				}
				sb.append(formataString(getArquivoProtocolo().getNoArquivo()));
			}
		}
		return sb.toString();
	}
*/	
	
	public String getNoComposto() {
		StringBuffer sb = new StringBuffer();
		sb.append(formataString(getArquivoProtocolo().getNoArquivoIfc()));
		sb.append("</br>");
		if (this.arquivoProtocolo != null) {
			sb.append(formataString(arquivoProtocolo.getNoArquivo()));
			sb.append("</br>");
		}
		sb.append(formataString(getArquivoProtocolo().getNoArquivo()));		
		return sb.toString();
	}
	
	
	
	public String getMinutosTarifados() {
		return formataInteger(getArquivoProtocolo().getQtDuracaoTarifada());
	}
	
	public String getQtCDR() {
		return formataInteger(getArquivoProtocolo().getQtRegistros());
	}
	
	public String getQtDuracaoReal() {
		return formataInteger(getArquivoProtocolo().getQtDuracaoReal());
	}
	
	public String getQtDuracaoTarifada() {
		return formataInteger(getArquivoProtocolo().getQtDuracaoTarifada());
	}
	
	public String getValorLiquido() {
		return formataDouble(getArquivoProtocolo().getVlLiquidoArquivo(),2);
	}
	
	public String getValorBruto() {
		return formataDouble(getArquivoProtocolo().getVlBrutoArquivo(),2);
	}
	
	public String getStatus() {
		return getArquivoProtocolo().getCdStatusArquivo().getDsStatusArquivo();
	}
	
	public String getDataInicioTrafego() {
		return formataDate(getArquivoProtocolo().getDtInicioTrafego());
	}
	
	public String getDataFinalTrafego() {
		return formataDate(getArquivoProtocolo().getDtFimTrafego());
	}
	
/*	public String getSucesso() {
		return "<a href='#' onClick='visualizarOK("+getRownum()+")'> Ciclo </a>";
	}
*/	
	public String getCdCiclo() {
		return exibeInteger(getArquivoProtocolo().getCdCiclo());
	}
	
	public String getAaCiclo() {
		return exibeInteger(getArquivoProtocolo().getAaCiclo());
	}
	
	public String getMmCiclo() {
		return exibeInteger(getArquivoProtocolo().getMmCiclo());
	}
	
/*	public String getErro() {
		return "<a href='#' onClick='visualizarNOK("+getRownum()+")'> Motivo </a>";
	}
*/	
	public String getStatusProtocolo() {
		if ((getArquivoProtocolo().getCdErroProtocolo() == null) || (getArquivoProtocolo().getCdErroProtocolo().equalsIgnoreCase("OK"))) {
			return "ARQUIVO OK";
		} else {
			return "ARQUIVO NÃO OK";
		}
	}
	
	protected boolean isDeleteEnabled() {
		return false;
	}
	
	protected String formataString(String value) {
		if (value == null) {
			return " ";
		}
		return value;
	}

	protected String exibeInteger(Integer valor) {
		if (valor == null) {
			return "";
		} else {
			return valor.toString();
		}
	}
	
	protected String formataInteger(Integer valor) {
		if (valor == null) {
			return " ";
		}
		return integerFormat.format(valor);
	}
	
	protected String formataDouble(Double valor, int prec) {
		if (valor == null) {
			return " ";
		}
		return decimalFormat.format(trunc(valor,prec));
	}
	
	protected String formataLong(Long valor) {
		if (valor == null) {
			return " ";
		}
		return decimalFormat.format(valor);
	}

	protected double trunc(Double db, int casas) {
		if (db == null) {
			return trunc(0.0000,2);
		}
    	double fator = 1.0;
    	do {
    		fator = fator*10.0;
    		casas--;
    	}
    	while(casas>0);
    	return Math.round(db*fator)/fator;
    }
	
	protected String formataDate(Date valor) {
		if (valor == null) {
			return " ";
		}
		return dateFormat.format(valor);
	}


	
}
