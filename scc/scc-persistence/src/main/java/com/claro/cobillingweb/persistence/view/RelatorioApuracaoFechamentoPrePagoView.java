package com.claro.cobillingweb.persistence.view;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;


public class RelatorioApuracaoFechamentoPrePagoView {
	
	protected static NumberFormat decimalFormat = new DecimalFormat("#.##");
	protected static NumberFormat integerFormat = new DecimalFormat("#.##");
	static {
    	Locale locale = new Locale("pt","BR");
    	decimalFormat = NumberFormat.getInstance(locale);
    	decimalFormat.setMinimumFractionDigits(2);
    	decimalFormat.setMaximumFractionDigits(2);
    	integerFormat = NumberFormat.getInstance(locale);
    	integerFormat.setMinimumFractionDigits(0);
    	integerFormat.setMaximumFractionDigits(0);
    }

	
	public RelatorioApuracaoFechamentoPrePagoView(){
		
	}
	
	
	public RelatorioApuracaoFechamentoPrePagoView(
			RelApuracaoFechamentoPrePagoView entity) {
		
		this.entity = entity;
		
		this.operadoraClaro = getOperadoraClaro(entity);
		this.dsOperadora = getDsOperadora(entity);
		this.cdEotLd = getCdeotLd(entity);
		this.cdEotHolding = getCdEotHolding(entity);
		this.valorApuradoLiquido = getValorApuradoLiquido(entity);
		this.pisCofins = getPisCofins(entity);
		this.valorIcmsRepassar = getValorIcmsRepassar(entity);
		this.valorIcmsNaoRepassado = getValorIcmsNaoRepassado(entity);
		this.valorRepassar = getValorRepassar(entity);
		this.servicoPrestadoLiquido = getServicoPrestadoLiquido(entity);
		this.pisCofinsServicePrestado = getPisCofinsServicePrestado(entity);
		this.iss = getIss(entity);
		this.valorBrutoServico = getValorBrutoServico(entity);
		this.creditosAutorizados = getCreditosAutorizados(entity);
		this.creditos226 = getCreditos226(entity);
		this.penalidadesMinutosPerdidos = getPenalidadesMinutosPerdidos(entity);
		this.totalMultasJuros = getTotalMultasJuros(entity);
		this.totalAcertosConciliacoes = getTotalAcertosConciliacoes(entity);
		this.cpmfDescontar = getCpmfDescontar2(entity);
		this.icmsDescontar = getIcmsRepassar(entity); //getIcmsDescontar(entity);
		this.icmsRepassar =  getIcmsRepassar2(entity);   // solicitado para retirar getIcmsRepassar(entity);
		this.valorFinalRepassar = getValorFinalRepassar(entity);
		this.valorNotaFiscal = getValorNotaFiscal(entity);
		this.destaqueIcms = getDestaqueIcms(entity);
		this.dsOperadoraHolding = getDsOperadoraHolding(entity);
		
		
	}
	
	private RelApuracaoFechamentoPrePagoView entity;
	private String operadoraClaro;
	private String dsOperadora;
	private String cdEotLd;
	private String cdEotHolding;
	private String uf;
	private Double valorApuradoLiquido;
	private Double pisCofins;
	private Double valorIcmsRepassar;
	private Double valorIcmsNaoRepassado;
	private Double valorRepassar;
	private Double servicoPrestadoLiquido;
	private Double pisCofinsServicePrestado;
	private Double iss;
	private Double valorBrutoServico;
	private Double creditosAutorizados;
	private Double creditos226;
	private Double penalidadesMinutosPerdidos;
	private Double totalMultasJuros;
	private Double totalAcertosConciliacoes;
	private Double cpmfDescontar;
	private Double icmsDescontar;
	private Double icmsRepassar;
	private Double valorFinalRepassar;
	private Double valorNotaFiscal;
	private Double destaqueIcms;
	
	private String dsOperadoraClaro;
	private String dsOperadoraHolding;
	private String dsOperadoraLd;
	
	private RelatorioApuracaoFechamentoPrePagoView total;
	
	public void addValorApuradoLiquido(double valorApuradoLiquido){
		this.valorApuradoLiquido += valorApuradoLiquido;
	}
	
	public void addValorRepassar(double valorRepassar){
		this.valorRepassar += valorRepassar;
	}
	
	public RelApuracaoFechamentoPrePagoView getEntity() {
		return entity;
	}
	public void setEntity(RelApuracaoFechamentoPrePagoView entity) {
		this.entity = entity;
	}
	public String getOperadoraClaro() {
		return operadoraClaro;
	}
	public void setOperadoraClaro(String operadoraClaro) {
		this.operadoraClaro = operadoraClaro;
	}
	
	public String getDsOperadora() {
		return dsOperadora;
	}
	public void setDsOperadora(String dsOperadora) {
		this.dsOperadora = dsOperadora;
	}
	public String getCdEotLd() {
		return cdEotLd;
	}
	public void setCdEotLd(String cdEotLd) {
		this.cdEotLd = cdEotLd;
	}
	public String getCdEotHolding() {
		return cdEotHolding;
	}
	public void setCdEotHolding(String cdEotHolding) {
		this.cdEotHolding = cdEotHolding;
	}
	
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public Double getValorApuradoLiquido() {
		return valorApuradoLiquido;
	}
	public void setValorApuradoLiquido(Double valorApuradoLiquido) {
		this.valorApuradoLiquido = valorApuradoLiquido;
	}
	public Double getPisCofins() {
		return pisCofins;
	}
	public void setPisCofins(Double pisCofins) {
		this.pisCofins = pisCofins;
	}
	public Double getValorIcmsRepassar() {
		return valorIcmsRepassar;
	}
	public void setValorIcmsRepassar(Double valorIcmsRepassar) {
		this.valorIcmsRepassar = valorIcmsRepassar;
	}
	public Double getValorIcmsNaoRepassado() {
		return valorIcmsNaoRepassado;
	}
	public void setValorIcmsNaoRepassado(Double valorIcmsNaoRepassado) {
		this.valorIcmsNaoRepassado = valorIcmsNaoRepassado;
	}
	public Double getValorRepassar() {
		return valorRepassar;
	}
	public void setValorRepassar(Double valorRepassar) {
		this.valorRepassar = valorRepassar;
	}
	public Double getServicoPrestadoLiquido() {
		return servicoPrestadoLiquido;
	}
	public void setServicoPrestadoLiquido(Double servicoPrestadoLiquido) {
		this.servicoPrestadoLiquido = servicoPrestadoLiquido;
	}
	public Double getPisCofinsServicePrestado() {
		return pisCofinsServicePrestado;
	}
	public void setPisCofinsServicePrestado(Double pisCofinsServicePrestado) {
		this.pisCofinsServicePrestado = pisCofinsServicePrestado;
	}
	public Double getIss() {
		return iss;
	}
	public void setIss(Double iss) {
		this.iss = iss;
	}
	public Double getValorBrutoServico() {
		return valorBrutoServico;
	}
	public void setValorBrutoServico(Double valorBrutoServico) {
		this.valorBrutoServico = valorBrutoServico;
	}
	public Double getCreditosAutorizados() {
		return creditosAutorizados;
	}
	public void setCreditosAutorizados(Double creditosAutorizados) {
		this.creditosAutorizados = creditosAutorizados;
	}
	public Double getCreditos226() {
		return creditos226;
	}
	public void setCreditos226(Double creditos226) {
		this.creditos226 = creditos226;
	}
	public Double getPenalidadesMinutosPerdidos() {
		return penalidadesMinutosPerdidos;
	}
	public void setPenalidadesMinutosPerdidos(Double penalidadesMinutosPerdidos) {
		this.penalidadesMinutosPerdidos = penalidadesMinutosPerdidos;
	}
	public Double getTotalMultasJuros() {
		return totalMultasJuros;
	}
	public void setTotalMultasJuros(Double totalMultasJuros) {
		this.totalMultasJuros = totalMultasJuros;
	}
	public Double getTotalAcertosConciliacoes() {
		return totalAcertosConciliacoes;
	}
	public void setTotalAcertosConciliacoes(Double totalAcertosConciliacoes) {
		this.totalAcertosConciliacoes = totalAcertosConciliacoes;
	}
	public Double getCpmfDescontar() {
		return cpmfDescontar;
	}
	public void setCpmfDescontar(Double cpmfDescontar) {
		this.cpmfDescontar = cpmfDescontar;
	}
	public Double getIcmsDescontar() {
		return icmsDescontar;
	}
	public void setIcmsDescontar(Double icmsDescontar) {
		this.icmsDescontar = icmsDescontar;
	}
	public Double getIcmsRepassar() {
		return icmsRepassar;
	}
	public void setIcmsRepassar(Double icmsRepassar) {
		this.icmsRepassar = icmsRepassar;
	}
	public Double getValorFinalRepassar() {
		return valorFinalRepassar;
	}
	public void setValorFinalRepassar(Double valorFinalRepassar) {
		this.valorFinalRepassar = valorFinalRepassar;
	}
	public Double getValorNotaFiscal() {
		return valorNotaFiscal;
	}
	public void setValorNotaFiscal(Double valorNotaFiscal) {
		this.valorNotaFiscal = valorNotaFiscal;
	}
	public Double getDestaqueIcms() {
		return destaqueIcms;
	}
	public void setDestaqueIcms(Double destaqueIcms) {
		this.destaqueIcms = destaqueIcms;
	}
	
	
	public String getValorApuradoLiquidoStr(){
		String value = "";
		if(this.valorApuradoLiquido != null){
			value = decimalFormat.format(this.getValorApuradoLiquido());
		}
		return value;
	}
	
	public String getPisCofinsStr(){
		String value = "";
		if(this.getPisCofins() != null){
			value = decimalFormat.format(this.getPisCofins());
		}
		return value;
	}
	

	public String getValorIcmsRepassarStr(){
		String value = "";
		if(this.getValorIcmsRepassar() != null){
			value = decimalFormat.format(this.getValorIcmsRepassar());
		}
		return value;
	}
	
	public String getValorIcmsNaoRepassadoStr(){
		String value ="";
		if(this.getValorIcmsNaoRepassado()!= null){
			value = decimalFormat.format(this.getValorIcmsNaoRepassado());
		}
		
		return value;
	}
	
	public String getValorRepassarStr(){
		
		String value = "";
		if(this.getValorRepassar() != null){
			value = decimalFormat.format(this.getValorRepassar());
		}
		
		return value; 
		
	}
	
	public String getServicoPrestadoLiquidoStr(){
		String value = "";
		if(this.getServicoPrestadoLiquido() != null){
			value = decimalFormat.format(this.getServicoPrestadoLiquido());
		}
		return value;
	}
	
	public String getpisCofinsServicePrestadoStr(){
		
		String value = "";
		if(this.getPisCofinsServicePrestado() != null){
			value = decimalFormat.format(this.getPisCofinsServicePrestado());
		}
		return value;
	}
	
	public String getIssStr(){
		String value = "";
		if(this.getIss() != null){
			value = decimalFormat.format(this.getIss());
		}
		return value;
	}
	
	public String getValorBrutoServicoStr(){
		
		String value = "";
		if(this.getValorBrutoServico() != null){
			value = decimalFormat.format(this.getValorBrutoServico());
		}
		return value;
	}
	
	public String getCreditosAutorizadosStr(){ 
		
		return decimalFormat.format(this.getCreditosAutorizados());
		
	}
	
	public String getCreditos226Str(){
		return decimalFormat.format(this.getCreditos226());
	}
	
	public String getPenalidadeMinutosPerdidosStr(){
		return decimalFormat.format(this.getPenalidadesMinutosPerdidos());
	}
	
	public String getTotalMultasJurosStr(){
		return decimalFormat.format(this.getTotalMultasJuros());
	}
	
	public String getTotalAcertosConciliacoesStr(){
		return decimalFormat.format(this.getTotalAcertosConciliacoes());
	}
	
	public String getCpmDescontarStr(){
		return decimalFormat.format(this.getCpmfDescontar());
	}
	
	public String getIcmsDescontarStr(){
		return decimalFormat.format(this.getIcmsDescontar());
	}
	
	public String getIcmsRepassarStr(){
		return decimalFormat.format(this.getIcmsRepassar());
	}
	
	public String getValorFinalRepassarStr(){
		return decimalFormat.format(this.getValorFinalRepassar());
	}
	
	public String getValorNotaFiscalStr(){
		return decimalFormat.format(this.getValorNotaFiscal());
	}
	
	public String getDestaqueIcmsStr(){
		return decimalFormat.format(this.getDestaqueIcms());
	}
	
	public RelatorioApuracaoFechamentoPrePagoView getTotal() {
		return total;
	}


	public void setTotal(RelatorioApuracaoFechamentoPrePagoView total) {
		this.total = total;
	}


	private String getOperadoraClaro(RelApuracaoFechamentoPrePagoView entity){
		
		return entity.getCdEOTClaro();
	}


	private String getDsOperadoraHolding(RelApuracaoFechamentoPrePagoView entity){
		
		return entity.getDsOperadoraHolding();
	}

	
	
	
	private Double getValorApuradoLiquido(RelApuracaoFechamentoPrePagoView entity){
		
		Double vlrLiquidoApurado =  zeroIfNull(entity.getValor11())+zeroIfNull(entity.getValor19())-(zeroIfNull(entity.getValor15())
						+zeroIfNull(entity.getValor16())+zeroIfNull(entity.getValor23())+zeroIfNull(entity.getValor24()))
				-(zeroIfNull(entity.getValor14())+zeroIfNull(entity.getValor22()));
				
		return vlrLiquidoApurado;
		
	}
	
	private Double getPisCofins(RelApuracaoFechamentoPrePagoView entity){
		
		Double valorPisCofins = zeroIfNull(entity.getValor15())+zeroIfNull(entity.getValor16())+zeroIfNull(entity.getValor23())+zeroIfNull(entity.getValor24());
		return valorPisCofins;
	}
	
	private Double getValorIcmsRepassar(RelApuracaoFechamentoPrePagoView entity) {	
		Double valorIcmsRepassar = 0.0;
		if (isFlagTrue(entity.getRepassaICMS())){
			valorIcmsRepassar = zeroIfNull(entity.getValor14())+zeroIfNull(entity.getValor22());
		}
		return valorIcmsRepassar;
	}
	
	private Double getValorIcmsNaoRepassado(RelApuracaoFechamentoPrePagoView entity) {
		Double valor = 0.0;
		if (!isFlagTrue(entity.getRepassaICMS()))	{
			valor = zeroIfNull(entity.getValor14())+zeroIfNull(entity.getValor22());
		}
		 
		return valor;
	}
	
	private Double getValorRepassar(RelApuracaoFechamentoPrePagoView entity) {	
		
		Double valorRepassar =  (getValorLiquidoApurado(entity) + getValorPisCofins(entity) + getValorIcmsRepassar(entity));
		return valorRepassar;
	}
	
	private Double getValorLiquidoApurado(RelApuracaoFechamentoPrePagoView entity){
		
		Double vlrLiquidoApurado =  zeroIfNull(entity.getValor11())+zeroIfNull(entity.getValor19())-(zeroIfNull(entity.getValor15())
						+zeroIfNull(entity.getValor16())+zeroIfNull(entity.getValor23())+zeroIfNull(entity.getValor24()))
				-(zeroIfNull(entity.getValor14())+zeroIfNull(entity.getValor22()));
				
		return vlrLiquidoApurado;
		
	}
	
	private Double getValorPisCofins(RelApuracaoFechamentoPrePagoView entity){
		
		Double valorPisCofins = zeroIfNull(entity.getValor15())+zeroIfNull(entity.getValor16())+zeroIfNull(entity.getValor23())+zeroIfNull(entity.getValor24());
		return valorPisCofins;
	}

	
	private Double getServicoPrestadoLiquido(RelApuracaoFechamentoPrePagoView entity) {
		
		Double valor = 0.0;
		valor = zeroIfNull(entity.getValor25()) -(zeroIfNull(entity.getValor26())+zeroIfNull(entity.getValor27())+zeroIfNull(entity.getValor28()));
		
		return valor;
	}

	private Double getPisCofinsServicePrestado(RelApuracaoFechamentoPrePagoView entity) {
		Double valor = zeroIfNull(entity.getValor26())+zeroIfNull(entity.getValor27());
		return valor;
	}

	private Double getIss(RelApuracaoFechamentoPrePagoView entity) {
		return zeroIfNull(entity.getValor28());
	}

	private Double getValorServicePrestadoBruto(RelApuracaoFechamentoPrePagoView entity){
		
		Double valorServicePrestadoBruto = zeroIfNull(entity.getValor25());
		return valorServicePrestadoBruto;
	}


	private Double getValorBrutoServico(RelApuracaoFechamentoPrePagoView entity) {
		
		return getValorServicePrestadoBruto(entity);
	}
	
	private Double getValorCreditosAutorizados(RelApuracaoFechamentoPrePagoView entity){
		
		Double valorCreditosAutorizados = zeroIfNull(entity.getValor29());
		return valorCreditosAutorizados;
	}


	private Double getCreditosAutorizados(RelApuracaoFechamentoPrePagoView entity) {
		return getValorCreditosAutorizados(entity);		
	}
	
	private Double getValorCreditos226(RelApuracaoFechamentoPrePagoView entity){
		
		Double valorCreditos226 = null;
		if (isFlagTrue(entity.getRepassaICMS())){
			valorCreditos226 = zeroIfNull(entity.getValor32());
		}
		else{
			valorCreditos226 = zeroIfNull(entity.getValor32())-zeroIfNull(entity.getValor44());
		}

		return valorCreditos226;
		
	}


	private Double getCreditos226(RelApuracaoFechamentoPrePagoView entity) {		
		return getValorCreditos226(entity);
	}
	
	private Double getPenalidadesMinutosPerdidos(RelApuracaoFechamentoPrePagoView entity) {		
		return getValorPenalidadeMinutosPerdidos(entity);
	}

	private Double getValorPenalidadeMinutosPerdidos(RelApuracaoFechamentoPrePagoView entity){
		
		Double valorPenalidadeMinutosPerdidos = zeroIfNull(entity.getValor33()); 
		return valorPenalidadeMinutosPerdidos;
	}


	private Double getTotalMultasJuros(RelApuracaoFechamentoPrePagoView entity) {		
		return getValorMultas(entity);
	}
	
	
	private Double getValorMultas(RelApuracaoFechamentoPrePagoView entity){
		Double valorMultas = zeroIfNull(entity.getValor35())+zeroIfNull(entity.getValor34());
		return valorMultas;
	}
	
	private Double getTotalAcertosConciliacoes(RelApuracaoFechamentoPrePagoView entity) {		
		return getValorAcertos(entity);
	}
	
	private Double getValorAcertos(RelApuracaoFechamentoPrePagoView entity){
		
		Double valorAcertos = zeroIfNull(entity.getValor37())+zeroIfNull(entity.getValor36());
		return valorAcertos;
	}

	private Double getCpmfDescontar2(RelApuracaoFechamentoPrePagoView entity) {
		return 0.0;
	}

	
	private Double getIcmsRepassar2(RelApuracaoFechamentoPrePagoView entity) {
		
		Double valor = 0.0;
		if(isFlagTrue(entity.getRepassaICMS())){
			valor = entity.getValor38();
		}
		return valor;
	}
	
	private Double getIcmsRepassar(RelApuracaoFechamentoPrePagoView entity) {
		Double valor = 0.0;
		if (isFlagTrue(entity.getRepassaICMS())){
			valor = zeroIfNull(entity.getValor14())+zeroIfNull(entity.getValor22());
		}
		return valor;
	}

	
	private Double getValorFinalRepassar(RelApuracaoFechamentoPrePagoView entity) {
		return (getValorLiquidoApurado(entity) +getValorPisCofins(entity) - getValorServicePrestadoBruto(entity) - getValorCreditosAutorizados(entity) - getValorCreditos226(entity) + getValorPenalidadeMinutosPerdidos(entity) +
				getValorMultas(entity) + getValorAcertos(entity) - getCpmfDescontar2(entity) + getIcmsRepassar2(entity));
	}
	
	
	
	private Double getValorNotaFiscal(RelApuracaoFechamentoPrePagoView entity) {		
		return (getValorLiquidoApurado(entity) + getValorPisCofins(entity) + getValorIcmsRepassar(entity)) - (getValorCreditos226(entity) -getValorAcertos(entity));
	}

	private String getCdEotHolding(RelApuracaoFechamentoPrePagoView entity){
		String value = "";
		if(StringUtils.isNotEmpty(entity.getCdEotHolding())){
			value = entity.getCdEotHolding();
		}
		
		return value;
	}
	
	private Double getDestaqueIcms(RelApuracaoFechamentoPrePagoView entity) {
		Double valorRepassar = (getValorLiquidoApurado(entity) + getValorPisCofins(entity) +  getValorIcmsRepassar(entity));
		if (valorRepassar == null) {
			return 0.0;
		}
		Double valor = 0.0;
		if (isFlagTrue(entity.getRepassaICMS())){
			valor = zeroIfNull(entity.getValor14())+zeroIfNull(entity.getValor22());
		}
		return (valor - (getValorCreditos226(entity) * (valor/valorRepassar)));
	}

	
	private Double getIcmsDescontar(RelApuracaoFechamentoPrePagoView entity) {
		return 0.0;
	}
	
	
	public String getDsOperadora(RelApuracaoFechamentoPrePagoView entity){
		
		String value = "";
		if(StringUtils.isNotEmpty(entity.getUfClaro())){
			value = entity.getUfClaro()+ " - " + entity.getDsOperadora() ;
		}else{
			
			value = entity.getDsOperadora(); 
		}
		
		return value;
	}
	
	private String getCdeotLd(RelApuracaoFechamentoPrePagoView entity){
		return entity.getCdEOTLD();
	}

	
	private boolean isFlagTrue(String value) {
		return ((value != null) && (value.equalsIgnoreCase("S")));
	}

	
	private Double zeroIfNull(Double value) {
		if (value == null) {
			return 0.0;
		}
		return value;
	}


	public String getDsOperadoraClaro() {
		return dsOperadoraClaro;
	}


	public void setDsOperadoraClaro(String dsOperadoraClaro) {
		this.dsOperadoraClaro = dsOperadoraClaro;
	}


	public String getDsOperadoraHolding() {
		return dsOperadoraHolding;
	}


	public void setDsOperadoraHolding(String dsOperadoraHolding) {
		this.dsOperadoraHolding = dsOperadoraHolding;
	}


	public String getDsOperadoraLd() {
		return dsOperadoraLd;
	}


	public void setDsOperadoraLd(String dsOperadoraLd) {
		this.dsOperadoraLd = dsOperadoraLd;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dsOperadoraHolding == null) ? 0 : dsOperadoraHolding.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RelatorioApuracaoFechamentoPrePagoView other = (RelatorioApuracaoFechamentoPrePagoView) obj;
		if (dsOperadoraHolding == null) {
			if (other.dsOperadoraHolding != null)
				return false;
		} else if (!dsOperadoraHolding.equals(other.dsOperadoraHolding))
			return false;
		return true;
	}


	
	

	
}
