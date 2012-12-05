package com.claro.cobillingweb.persistence.view;

import java.util.Date;

/**
 * View para a query que monta o relatório de Resumo de Apuração
 * para repasse de pré-pago. 
 *
 */
/*Devido ao prazo muito curto , a query será usada da forma original sem análise 
 * de performance.
 * Os campos valorXX são derivados do SQL original. Analisar e renomear esses campos 
 * fica fora do escopo devido ao prazo.
 * */
public class RelApuracaoFechamentoPrePagoView {

	private String cdEOTLD;
	private Date dtInicialFechamento;
	private Date dtFinalFechamento;
	private String cdEOTClaro;
	private Date dtFechamento;
	private String cdStatusFechamento;
	private Long sqPedido;
	private Long qtCdrs;
	private Double valor10;
	private Double valor11;
	private Double valor12;
	private Double valor13;
	private Double valor14;
	private Double valor15;
	private Double valor16;
	private Long qtCdrsOm;
	private Double valor18;
	private Double valor19;
	private Double valor20;
	private Double valor21;
	private Double valor22;
	private Double valor23;
	private Double valor24;
	private Double valor25;
	private Double valor26;
	private Double valor27;
	private Double valor28;
	private Double valor29;
	private Long qtCdrs226;
	private Double valor31;
	private Double valor32;
	private Double valor33;
	private Double valor34;
	private Double valor35;
	private Double valor36;
	private Double valor37;
	private Double valor38;
	private Double valor39;
	private String repasseCPFM;
	private String repassaICMS;
	private Double valor42;
	private Double valor43;
	private Double valor44;
	private String dsOperadora;
	private String criterioCusto;
	private String ufClaro;

	public String getCdEOTLD() {
		return cdEOTLD;
	}
	public void setCdEOTLD(String cdEOTLD) {
		this.cdEOTLD = cdEOTLD;
	}
	public Date getDtInicialFechamento() {
		return dtInicialFechamento;
	}
	public void setDtInicialFechamento(Date dtInicialFechamento) {
		this.dtInicialFechamento = dtInicialFechamento;
	}
	public Date getDtFinalFechamento() {
		return dtFinalFechamento;
	}
	public void setDtFinalFechamento(Date dtFinalFechamento) {
		this.dtFinalFechamento = dtFinalFechamento;
	}
	public String getCdEOTClaro() {
		return cdEOTClaro;
	}
	public void setCdEOTClaro(String cdEOTClaro) {
		this.cdEOTClaro = cdEOTClaro;
	}
	public Date getDtFechamento() {
		return dtFechamento;
	}
	public void setDtFechamento(Date dtFechamento) {
		this.dtFechamento = dtFechamento;
	}
	public String getCdStatusFechamento() {
		return cdStatusFechamento;
	}
	public void setCdStatusFechamento(String cdStatusFechamento) {
		this.cdStatusFechamento = cdStatusFechamento;
	}
	public Long getSqPedido() {
		return sqPedido;
	}
	public void setSqPedido(Long sqPedido) {
		this.sqPedido = sqPedido;
	}
	public Long getQtCdrs() {
		return qtCdrs;
	}
	public void setQtCdrs(Long qtCdrs) {
		this.qtCdrs = qtCdrs;
	}
	public Double getValor10() {
		return valor10;
	}
	public void setValor10(Double valor10) {
		this.valor10 = valor10;
	}
	public Double getValor11() {
		return valor11;
	}
	public void setValor11(Double valor11) {
		this.valor11 = valor11;
	}
	public Double getValor12() {
		return valor12;
	}
	public void setValor12(Double valor12) {
		this.valor12 = valor12;
	}
	public Double getValor13() {
		return valor13;
	}
	public void setValor13(Double valor13) {
		this.valor13 = valor13;
	}
	public Double getValor14() {
		return valor14;
	}
	public void setValor14(Double valor14) {
		this.valor14 = valor14;
	}
	public Double getValor15() {
		return valor15;
	}
	public void setValor15(Double valor15) {
		this.valor15 = valor15;
	}
	public Double getValor16() {
		return valor16;
	}
	public void setValor16(Double valor16) {
		this.valor16 = valor16;
	}
	public Long getQtCdrsOm() {
		return qtCdrsOm;
	}
	public void setQtCdrsOm(Long qtCdrsOm) {
		this.qtCdrsOm = qtCdrsOm;
	}
	public Double getValor18() {
		return valor18;
	}
	public void setValor18(Double valor18) {
		this.valor18 = valor18;
	}
	public Double getValor19() {
		return valor19;
	}
	public void setValor19(Double valor19) {
		this.valor19 = valor19;
	}
	public Double getValor20() {
		return valor20;
	}
	public void setValor20(Double valor20) {
		this.valor20 = valor20;
	}
	public Double getValor21() {
		return valor21;
	}
	public void setValor21(Double valor21) {
		this.valor21 = valor21;
	}
	public Double getValor22() {
		return valor22;
	}
	public void setValor22(Double valor22) {
		this.valor22 = valor22;
	}
	public Double getValor23() {
		return valor23;
	}
	public void setValor23(Double valor23) {
		this.valor23 = valor23;
	}
	public Double getValor24() {
		return valor24;
	}
	public void setValor24(Double valor24) {
		this.valor24 = valor24;
	}
	public Double getValor25() {
		return valor25;
	}
	public void setValor25(Double valor25) {
		this.valor25 = valor25;
	}
	public Double getValor26() {
		return valor26;
	}
	public void setValor26(Double valor26) {
		this.valor26 = valor26;
	}
	public Double getValor27() {
		return valor27;
	}
	public void setValor27(Double valor27) {
		this.valor27 = valor27;
	}
	public Double getValor28() {
		return valor28;
	}
	public void setValor28(Double valor28) {
		this.valor28 = valor28;
	}
	public Double getValor29() {
		return valor29;
	}
	public void setValor29(Double valor29) {
		this.valor29 = valor29;
	}
	public Long getQtCdrs226() {
		return qtCdrs226;
	}
	public void setQtCdrs226(Long qtCdrs226) {
		this.qtCdrs226 = qtCdrs226;
	}
	public Double getValor31() {
		return valor31;
	}
	public void setValor31(Double valor31) {
		this.valor31 = valor31;
	}
	public Double getValor32() {
		return valor32;
	}
	public void setValor32(Double valor32) {
		this.valor32 = valor32;
	}
	public Double getValor33() {
		return valor33;
	}
	public void setValor33(Double valor33) {
		this.valor33 = valor33;
	}
	public Double getValor34() {
		return valor34;
	}
	public void setValor34(Double valor34) {
		this.valor34 = valor34;
	}
	public Double getValor35() {
		return valor35;
	}
	public void setValor35(Double valor35) {
		this.valor35 = valor35;
	}
	public Double getValor36() {
		return valor36;
	}
	public void setValor36(Double valor36) {
		this.valor36 = valor36;
	}
	public Double getValor37() {
		return valor37;
	}
	public void setValor37(Double valor37) {
		this.valor37 = valor37;
	}
	public Double getValor38() {
		return valor38;
	}
	public void setValor38(Double valor38) {
		this.valor38 = valor38;
	}
	public Double getValor39() {
		return valor39;
	}
	public void setValor39(Double valor39) {
		this.valor39 = valor39;
	}
	public String getRepasseCPFM() {
		return repasseCPFM;
	}
	public void setRepasseCPFM(String repasseCPFM) {
		this.repasseCPFM = repasseCPFM;
	}
	public String getRepassaICMS() {
		return repassaICMS;
	}
	public void setRepassaICMS(String repassaICMS) {
		this.repassaICMS = repassaICMS;
	}
	public Double getValor42() {
		return valor42;
	}
	public void setValor42(Double valor42) {
		this.valor42 = valor42;
	}
	public Double getValor43() {
		return valor43;
	}
	public void setValor43(Double valor43) {
		this.valor43 = valor43;
	}
	public Double getValor44() {
		return valor44;
	}
	public void setValor44(Double valor44) {
		this.valor44 = valor44;
	}
	public String getDsOperadora() {
		return dsOperadora;
	}
	public void setDsOperadora(String dsOperadora) {
		this.dsOperadora = dsOperadora;
	}
	public String getCriterioCusto() {
		return criterioCusto;
	}
	public void setCriterioCusto(String criterioCusto) {
		this.criterioCusto = criterioCusto;
	}
	public String getUfClaro() {
		return ufClaro;
	}
	public void setUfClaro(String ufClaro) {
		this.ufClaro = ufClaro;
	}
	
	
	
	   
      
}
