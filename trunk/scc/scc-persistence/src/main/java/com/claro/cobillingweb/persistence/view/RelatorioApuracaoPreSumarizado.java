package com.claro.cobillingweb.persistence.view;


public class RelatorioApuracaoPreSumarizado {
	
	public RelatorioApuracaoPreSumarizado(){
		
	}
	
	public RelatorioApuracaoPreSumarizado(RelatorioApuracaoFechamentoPrePagoView entity) {
		
		this.entity = entity;
		this.operadoraClaro = entity.getDsOperadora();
		this.dsOperadora = entity.getDsOperadora();
		this.descricao = entity.getDsOperadora();
		this.cdEotLd = entity.getCdEotLd();
		this.cdEotHolding = entity.getCdEotHolding();
		this.uf = entity.getUf();
		this.valorApuradoLiquido = entity.getValorApuradoLiquidoStr();
		this.pisCofins = entity.getPisCofinsStr();
		this.valorIcmsRepassar = entity.getValorIcmsRepassarStr();
		this.valorIcmsNaoRepassado = entity.getValorIcmsNaoRepassadoStr();
		this.valorRepassar = entity.getValorRepassarStr();
		this.servicoPrestadoLiquido = entity.getServicoPrestadoLiquidoStr();
		this.pisCofinsServicePrestado = entity.getpisCofinsServicePrestadoStr();
		this.iss = entity.getIssStr();
		this.valorBrutoServico = entity.getValorBrutoServicoStr();
		this.creditosAutorizados = entity.getCreditosAutorizadosStr();
		this.creditos226 = entity.getCreditos226Str();
		this.penalidadesMinutosPerdidos = entity.getPenalidadeMinutosPerdidosStr();
		this.totalMultasJuros = entity.getTotalMultasJurosStr();
		this.totalAcertosConciliacoes = entity.getTotalAcertosConciliacoesStr();
		this.cpmfDescontar = entity.getCpmDescontarStr();
		this.icmsDescontar = entity.getIcmsDescontarStr();
		this.icmsRepassar = entity.getIcmsRepassarStr();
		this.valorFinalRepassar = entity.getValorFinalRepassarStr();
		this.valorNotaFiscal = entity.getValorNotaFiscalStr();
		this.destaqueIcms = entity.getDestaqueIcmsStr();
		
	}

	private RelatorioApuracaoFechamentoPrePagoView entity;
	private String operadoraClaro;
	private String dsOperadora;
	private String cdEotLd;
	private String cdEotHolding;
	private String uf;
	private String valorApuradoLiquido;
	private String pisCofins;
	private String valorIcmsRepassar;
	private String valorIcmsNaoRepassado;
	private String valorRepassar;
	private String servicoPrestadoLiquido;
	private String pisCofinsServicePrestado;
	private String iss;
	private String valorBrutoServico;
	private String creditosAutorizados;
	private String creditos226;
	private String penalidadesMinutosPerdidos;
	private String totalMultasJuros;
	private String totalAcertosConciliacoes;
	private String cpmfDescontar;
	private String icmsDescontar;
	private String icmsRepassar;
	private String valorFinalRepassar;
	private String valorNotaFiscal;
	private String destaqueIcms;
	
	private String dsOperadoraClaro;
	private String dsOperadoraHolding;
	private String dsOperadoraLd;
	
	private RelatorioApuracaoFechamentoPrePagoView total;
	
	private String descricao;
	
	private String totalValorApuradoLiquido;
	
	
	
	public String getCampoChamadas(){
	
		return null;
	}
	public String getCampoMinutos(){
		
		return this.getPenalidadesMinutosPerdidos();
	}
	
	public String getCampoValorBruto(){
		
		return this.getValorBrutoServico();
	}
	
	public String getCampoIcmsNaoRepassado(){
		
		return this.getValorIcmsNaoRepassado();
		
	}
	
	public String getCampoIcmsRepassar(){
		
		return this.getIcmsRepassar();
		
	}
	
	public String getCampoPisCofins(){
		
		return this.getPisCofins();
		
	}
	
	public String getCampoIss(){
		
		return this.getIss();
	}
	
	public String getCampoValorLiquido(){
		
		return this.getValorApuradoLiquido();
	}
	
	public String getCampoValorRepassar(){
		
		return this.getValorRepassar();
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

	public String getValorApuradoLiquido() {
		return valorApuradoLiquido;
	}

	public void setValorApuradoLiquido(String valorApuradoLiquido) {
		this.valorApuradoLiquido = valorApuradoLiquido;
	}

	public String getPisCofins() {
		return pisCofins;
	}

	public void setPisCofins(String pisCofins) {
		this.pisCofins = pisCofins;
	}

	public String getValorIcmsRepassar() {
		return valorIcmsRepassar;
	}

	public void setValorIcmsRepassar(String valorIcmsRepassar) {
		this.valorIcmsRepassar = valorIcmsRepassar;
	}

	public String getValorIcmsNaoRepassado() {
		return valorIcmsNaoRepassado;
	}

	public void setValorIcmsNaoRepassado(String valorIcmsNaoRepassado) {
		this.valorIcmsNaoRepassado = valorIcmsNaoRepassado;
	}

	public String getValorRepassar() {
		return valorRepassar;
	}

	public void setValorRepassar(String valorRepassar) {
		this.valorRepassar = valorRepassar;
	}

	public String getServicoPrestadoLiquido() {
		return servicoPrestadoLiquido;
	}

	public void setServicoPrestadoLiquido(String servicoPrestadoLiquido) {
		this.servicoPrestadoLiquido = servicoPrestadoLiquido;
	}

	public String getPisCofinsServicePrestado() {
		return pisCofinsServicePrestado;
	}

	public void setPisCofinsServicePrestado(String pisCofinsServicePrestado) {
		this.pisCofinsServicePrestado = pisCofinsServicePrestado;
	}

	public String getIss() {
		return iss;
	}

	public void setIss(String iss) {
		this.iss = iss;
	}

	public String getValorBrutoServico() {
		return valorBrutoServico;
	}

	public void setValorBrutoServico(String valorBrutoServico) {
		this.valorBrutoServico = valorBrutoServico;
	}

	public String getCreditosAutorizados() {
		return creditosAutorizados;
	}

	public void setCreditosAutorizados(String creditosAutorizados) {
		this.creditosAutorizados = creditosAutorizados;
	}

	public String getCreditos226() {
		return creditos226;
	}

	public void setCreditos226(String creditos226) {
		this.creditos226 = creditos226;
	}

	public String getPenalidadesMinutosPerdidos() {
		return penalidadesMinutosPerdidos;
	}

	public void setPenalidadesMinutosPerdidos(String penalidadesMinutosPerdidos) {
		this.penalidadesMinutosPerdidos = penalidadesMinutosPerdidos;
	}

	public String getTotalMultasJuros() {
		return totalMultasJuros;
	}

	public void setTotalMultasJuros(String totalMultasJuros) {
		this.totalMultasJuros = totalMultasJuros;
	}

	public String getTotalAcertosConciliacoes() {
		return totalAcertosConciliacoes;
	}

	public void setTotalAcertosConciliacoes(String totalAcertosConciliacoes) {
		this.totalAcertosConciliacoes = totalAcertosConciliacoes;
	}

	public String getCpmfDescontar() {
		return cpmfDescontar;
	}

	public void setCpmfDescontar(String cpmfDescontar) {
		this.cpmfDescontar = cpmfDescontar;
	}

	public String getIcmsDescontar() {
		return icmsDescontar;
	}

	public void setIcmsDescontar(String icmsDescontar) {
		this.icmsDescontar = icmsDescontar;
	}

	public String getIcmsRepassar() {
		return icmsRepassar;
	}

	public void setIcmsRepassar(String icmsRepassar) {
		this.icmsRepassar = icmsRepassar;
	}

	public String getValorFinalRepassar() {
		return valorFinalRepassar;
	}

	public void setValorFinalRepassar(String valorFinalRepassar) {
		this.valorFinalRepassar = valorFinalRepassar;
	}

	public String getValorNotaFiscal() {
		return valorNotaFiscal;
	}

	public void setValorNotaFiscal(String valorNotaFiscal) {
		this.valorNotaFiscal = valorNotaFiscal;
	}

	public String getDestaqueIcms() {
		return destaqueIcms;
	}

	public void setDestaqueIcms(String destaqueIcms) {
		this.destaqueIcms = destaqueIcms;
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

	public RelatorioApuracaoFechamentoPrePagoView getTotal() {
		return total;
	}

	public void setTotal(RelatorioApuracaoFechamentoPrePagoView total) {
		this.total = total;
	}

	public RelatorioApuracaoFechamentoPrePagoView getEntity() {
		return entity;
	}

	public void setEntity(RelatorioApuracaoFechamentoPrePagoView entity) {
		this.entity = entity;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getTotalValorApuradoLiquido() {
		return totalValorApuradoLiquido;
	}
	public void setTotalValorApuradoLiquido(String totalValorApuradoLiquido) {
		this.totalValorApuradoLiquido = totalValorApuradoLiquido;
	}
}
