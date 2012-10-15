/**
 * 
 */
package com.claro.sccweb.decorator.rownum.entity;

import com.claro.cobillingweb.persistence.view.SccContingenciaFiscalView;
import com.claro.sccweb.decorator.rownum.RownumDecorator;

/**
 * @author 92031709
 *
 */
public class SccContingenciaFiscalViewDecorator extends RownumDecorator<SccContingenciaFiscalView> {
	

	private SccContingenciaFiscalView entity;

	private String cdCSP;
	private String nomeEmpresa;
	private String sgUF;
	private String valorFaturado;
	private String valorBaseCalculo;
	private String valorICMS;
	private String valorInsencao;
	private String nfInicial;
	private String nfFinal;
	private String razaoSocial;
	private String numeroCNPJ;
	private String inscricaoEstadual;
	private String serieNF;

	public SccContingenciaFiscalViewDecorator(SccContingenciaFiscalView entity,
			int rownum) {
		super(entity, rownum);
		formatarCampos(entity);
		
	}

	@Override
	protected boolean isDeleteEnabled() {
		return false;
	}

	
	private void formatarCampos(SccContingenciaFiscalView entity){

		this.setCdCSP(entity.getCdCSP());
		this.setNomeEmpresa(entity.getNomeEmpresa());
		this.setSgUF(entity.getSgUF());
		this.setValorFaturado(formataDouble(entity.getValorFaturado()));
		this.setValorBaseCalculo(formataDouble(entity.getValorBaseCalculo()));
		this.setValorICMS(formataDouble(entity.getValorICMS()));
		this.setValorInsencao(formataDouble(entity.getValorInsencao()));
		this.setNfInicial(formataInteger(entity.getNfInicial()));
		this.setNfFinal(formataInteger(entity.getNfFinal()));
		this.setRazaoSocial(entity.getRazaoSocial());
		this.setNumeroCNPJ(entity.getNumeroCNPJ());
		this.setInscricaoEstadual(entity.getInscricaoEstadual());
		this.setSerieNF(entity.getSerieNF());
		
	}

	public SccContingenciaFiscalView getEntity() {
		return entity;
	}

	public void setEntity(SccContingenciaFiscalView entity) {
		this.entity = entity;
	}

	public String getCdCSP() {
		return cdCSP;
	}

	public void setCdCSP(String cdCSP) {
		this.cdCSP = cdCSP;
	}

	public String getNomeEmpresa() {
		return nomeEmpresa;
	}

	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}

	public String getSgUF() {
		return sgUF;
	}

	public void setSgUF(String sgUF) {
		this.sgUF = sgUF;
	}

	public String getValorFaturado() {
		return valorFaturado;
	}

	public void setValorFaturado(String valorFaturado) {
		this.valorFaturado = valorFaturado;
	}

	public String getValorBaseCalculo() {
		return valorBaseCalculo;
	}

	public void setValorBaseCalculo(String valorBaseCalculo) {
		this.valorBaseCalculo = valorBaseCalculo;
	}

	public String getValorICMS() {
		return valorICMS;
	}

	public void setValorICMS(String valorICMS) {
		this.valorICMS = valorICMS;
	}

	public String getValorInsencao() {
		return valorInsencao;
	}

	public void setValorInsencao(String valorInsencao) {
		this.valorInsencao = valorInsencao;
	}

	public String getNfInicial() {
		return nfInicial;
	}

	public void setNfInicial(String nfInicial) {
		this.nfInicial = nfInicial;
	}

	public String getNfFinal() {
		return nfFinal;
	}

	public void setNfFinal(String nfFinal) {
		this.nfFinal = nfFinal;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getNumeroCNPJ() {
		return numeroCNPJ;
	}

	public void setNumeroCNPJ(String numeroCNPJ) {
		this.numeroCNPJ = numeroCNPJ;
	}

	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}

	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}

	public String getSerieNF() {
		return serieNF;
	}

	public void setSerieNF(String serieNF) {
		this.serieNF = serieNF;
	}

}
