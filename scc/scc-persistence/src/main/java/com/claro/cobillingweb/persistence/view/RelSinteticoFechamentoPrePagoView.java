package com.claro.cobillingweb.persistence.view;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.apache.commons.lang.StringUtils;


/**
 * Relatório sintético de repasse pré-pago.
 *
 */
public class RelSinteticoFechamentoPrePagoView {
	
	public static NumberFormat decimalFormat = new DecimalFormat("#.##");

	private String cdEOTHolding;
	private String cdEOTClaro;
	private String operadoraClaro;
	private String cdEOTLD;	
	private String uf;
	private String trafego;
	private String cnAssinante;
	private String cnOrigem;
	private String cdEotOrigem;
	private String tipo;
	private String periodo;
	private Long chamadas;
	private Double minutos;
	private Double icmsRepassar;
	private Double valorRepassar;
	private Double icmsNaoRepassado;
	private Double valorBruto;
	private Double valorPis;
	private Double valorCofins;
	
	private Double valorLiquido;
	private Double pisCofins;
	

	
	public String getOperadoraClaro() {
		return operadoraClaro;
	}
	
    public String getOperadoraClaroSemUF() {
    	String ret = this.operadoraClaro;
    	if (this.operadoraClaro != null) {
    		int i = this.operadoraClaro.indexOf("-");
    		if (i>=0) {
    			ret = this.operadoraClaro.substring(0, i).trim();
    		}
    	}
    	return ret;
    }
	
	public void setOperadoraClaro(String operadoraClaro) {
		this.operadoraClaro = operadoraClaro;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public String getTrafego() {
		return trafego;
	}
	public void setTrafego(String trafego) {
		this.trafego = trafego;
	}
	public String getCnAssinante() {
		return cnAssinante;
	}
	public void setCnAssinante(String cnAssinante) {
		this.cnAssinante = cnAssinante;
	}
	public String getCnOrigem() {
		return cnOrigem;
	}
	public void setCnOrigem(String cnOrigem) {
		this.cnOrigem = cnOrigem;
	}
	public String getCdEotOrigem() {
		return cdEotOrigem;
	}
	public void setCdEotOrigem(String cdEotOrigem) {
		this.cdEotOrigem = cdEotOrigem;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getPeriodo() {
		return periodo;
	}
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	public Long getChamadas() {
		return chamadas;
	}
	public void setChamadas(Long chamadas) {
		this.chamadas = chamadas;
	}
	public Double getMinutos() {
		return minutos;
	}
	public void setMinutos(Double minutos) {
		this.minutos = minutos;
	}
	
	public Double getIcmsRepassar() {
		return icmsRepassar;
	}
	public void setIcmsRepassar(Double icmsRepassar) {
		this.icmsRepassar = icmsRepassar;
	}
	public Double getValorRepassar() {
		return valorRepassar;
	}
	public void setValorRepassar(Double valorRepassar) {
		this.valorRepassar = valorRepassar;
	}
	public Double getIcmsNaoRepassado() {
		return icmsNaoRepassado;
	}
	public void setIcmsNaoRepassado(Double icmsNaoRepassado) {
		this.icmsNaoRepassado = icmsNaoRepassado;
	}
	public Double getValorBruto() {
		return valorBruto;
	}
	public void setValorBruto(Double valorBruto) {
		this.valorBruto = valorBruto;
	}
	public String getCdEOTHolding() {
		return cdEOTHolding;
	}
	public void setCdEOTHolding(String cdEOTHolding) {
		this.cdEOTHolding = cdEOTHolding;
	}
	
	
	public String getCdEOTLD() {
		return cdEOTLD;
	}
	public void setCdEOTLD(String cdEOTLD) {
		this.cdEOTLD = cdEOTLD;
	}
	public Double getValorPis() {
		return valorPis;
	}
	public void setValorPis(Double valorPis) {
		this.valorPis = valorPis;
	}
	public Double getValorCofins() {
		return valorCofins;
	}
	public void setValorCofins(Double valorCofins) {
		this.valorCofins = valorCofins;
	}
	public String getCdEOTClaro() {
		return cdEOTClaro;
	}
	public void setCdEOTClaro(String cdEOTClaro) {
		this.cdEOTClaro = cdEOTClaro;
	}
	public Double getValorLiquido() {
		return valorLiquido;
	}	
	public void setValorLiquido(Double valorLiquido) {
		
		String retorno = decimalFormat.format(zeroIfNull(this.valorBruto)
				- zeroIfNull(this.icmsRepassar)
				- zeroIfNull(this.valorPis)
				- zeroIfNull(this.valorCofins)
				- zeroIfNull(this.icmsNaoRepassado));
			
		if(StringUtils.isNotEmpty(retorno)){
			this.valorLiquido = new Double(retorno);
		}
		
		this.valorLiquido = valorLiquido;
	}
	
	public void setPureValorLiquido(Double valorLiquido) {		
		this.valorLiquido = valorLiquido;
	}
	
	
	public Double getPisCofins() {
		return pisCofins;
	}

	
	public void setPisCofins(Double pisCofins) {
		
		String value = decimalFormat.format(zeroIfNull(this.valorPis)
				+ zeroIfNull(this.valorCofins));
		
		if(StringUtils.isNotEmpty(value)){
			this.pisCofins = new Double(value);
		}
		this.pisCofins = pisCofins;
	}	
	
	public void setPurePisCofins(Double pisCofins) {
		this.pisCofins = pisCofins;
	}	

	//Para Excel
	public String getMinutosString()
	{
		if (getMinutos() == null)
			return "0,0";
		else
			return decimalFormat.format(getMinutos());
	}
	
	/*item.setVlLiquido(item.getVlBruto()-item.getVlIcmsADescontar()-item.getVlIcmsRepassar()-item.getVlPis()-item.getVlCofins());*/
	public String getValorLiquidoString()
	{
		return decimalFormat.format(zeroIfNull(getValorBruto())-zeroIfNull(getIcmsRepassar())-zeroIfNull(getValorPis())-zeroIfNull(getValorCofins())-zeroIfNull(getIcmsNaoRepassado()));
	}
	
	public String getPisCofinsString()
	{
		return decimalFormat.format(zeroIfNull(getValorPis())+zeroIfNull(getValorCofins()));
	}
	
	public String getIcmsRepassarString()
	{
		return decimalFormat.format(zeroIfNull(getIcmsRepassar()));
	}
	
	/*public String getValorRepassarString()
	{
		return decimalFormat.format(zeroIfNull(getValorRepassar()));
	}*/
	
	public String getValorRepassarString() {
		return decimalFormat.format(zeroIfNull(getValorBruto())
				- zeroIfNull(getIcmsNaoRepassado()));
	}
	
	
	public String getIcmsNaoRepassadoString()
	{
		return decimalFormat.format(zeroIfNull(getIcmsNaoRepassado()));
	}
	
	public String getValorBrutoString()
	{
		return decimalFormat.format(getValorBruto());
	}
	
	protected Double zeroIfNull(Double value) {
		if (value == null) {
			return 0.0;
		}
		return value;
	}
	
	protected Long zeroIfNull(Long value) {
		if (value == null) {
			return 0L;
		}
		return value;
	}
	
	public RelSinteticoFechamentoPrePagoView copy() {
		RelSinteticoFechamentoPrePagoView ret = new RelSinteticoFechamentoPrePagoView();
		
		ret.setCdEOTHolding(getCdEOTHolding());
		ret.setCdEOTClaro(getCdEOTClaro());
		ret.setOperadoraClaro(getOperadoraClaro());
		ret.setCdEOTLD(getCdEOTLD());
		ret.setUf(getUf());
		ret.setTrafego(getTrafego());
		ret.setCnAssinante(getCnAssinante());
		ret.setCnOrigem(getCnOrigem());
		ret.setCdEotOrigem(getCdEotOrigem());
		ret.setTipo(getTipo());
		ret.setPeriodo(getPeriodo());
		ret.setChamadas(getChamadas());
		ret.setMinutos(getMinutos());
		ret.setIcmsRepassar(getIcmsRepassar());
		ret.setValorRepassar(getValorRepassar());
		ret.setIcmsNaoRepassado(getIcmsNaoRepassado());
		ret.setValorBruto(getValorBruto());
		ret.setValorPis(getValorPis());
		ret.setValorCofins(getValorCofins());
		
		ret.setPureValorLiquido(getValorLiquido());
		ret.setPurePisCofins(getPisCofins());
		
		return ret;
	}
	
	public void clear() {
		setChamadas(0L);
		setMinutos(0D);
		setValorPis(0D);
		setValorCofins(0D);
		setIcmsRepassar(0D);
		setValorRepassar(0D);
		setIcmsNaoRepassado(0D);
		setValorBruto(0D);
	}
	
	public void summarize(RelSinteticoFechamentoPrePagoView linha) {

		this.chamadas = this.chamadas + zeroIfNull(linha.getChamadas());
		this.minutos = this.minutos + zeroIfNull(linha.getMinutos());
		this.valorPis = this.valorPis + zeroIfNull(linha.getValorPis());
		this.valorCofins = this.valorCofins +zeroIfNull(linha.getValorCofins());
		this.icmsRepassar = this.icmsRepassar + zeroIfNull(linha.getIcmsRepassar());
		this.valorRepassar = this.valorRepassar + zeroIfNull(linha.getValorRepassar());
		this.icmsNaoRepassado = this.icmsNaoRepassado + zeroIfNull(linha.getIcmsNaoRepassado());
		this.valorBruto = this.valorBruto + zeroIfNull(linha.getValorBruto());
	}
}

