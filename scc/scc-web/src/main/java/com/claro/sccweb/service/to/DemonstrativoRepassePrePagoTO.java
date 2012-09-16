package com.claro.sccweb.service.to;

import java.util.Date;


/**
 * TO utilizado como argumento para geração de demonstrativo de repasse para pré-pago.
 *
 */
public class DemonstrativoRepassePrePagoTO {

	private String cdEOTClaro;
	private String cdEOTLD;
	private String cdEOTHolding;
	private String cdProdutoPrepago;
	private Date dtInicial;
	private Date dtFinal;
	
	
	
	
	public String getCdEOTClaro() {
		return cdEOTClaro;
	}
	public void setCdEOTClaro(String cdEOTClaro) {
		this.cdEOTClaro = cdEOTClaro;
	}
	public String getCdEOTLD() {
		return cdEOTLD;
	}
	public void setCdEOTLD(String cdEOTLD) {
		this.cdEOTLD = cdEOTLD;
	}
	public String getCdProdutoPrepago() {
		return cdProdutoPrepago;
	}
	public void setCdProdutoPrepago(String cdProdutoPrepago) {
		this.cdProdutoPrepago = cdProdutoPrepago;
	}
	public Date getDtInicial() {
		return dtInicial;
	}
	public void setDtInicial(Date dtInicial) {
		this.dtInicial = dtInicial;
	}
	public String getCdEOTHolding() {
		return cdEOTHolding;
	}
	public void setCdEOTHolding(String cdEOTHolding) {
		this.cdEOTHolding = cdEOTHolding;
	}
	public Date getDtFinal() {
		return dtFinal;
	}
	public void setDtFinal(Date dtFinal) {
		this.dtFinal = dtFinal;
	}
	
	
	
}
