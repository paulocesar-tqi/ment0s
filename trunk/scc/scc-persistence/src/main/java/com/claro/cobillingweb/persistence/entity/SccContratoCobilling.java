package com.claro.cobillingweb.persistence.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="SCC_CONTRATO_COBILLING")
public class SccContratoCobilling extends FwjBaseEntidade {
	
	private static final long serialVersionUID = 1L;
	private SccContratoCobillingPK id;
	private String cdUsuarioManut;
	private String dsCriterioCusto;
	private String dsPeriodoRepasse;
	private Date dtAtualizacao;
	private Date dtCriacao;
	private Date dtInicioContrato;
	private String cdTipoContrato;
	private Date dtFimContrato;
	private String flRepassaCpmf;
	private String flRepassaIcms;
	private Double peCofins;
	private Double pePis;
	private Double peRetencao;
	private Double vlAssociadoCriterioCusto;
	private Double vlCpmf;
	private Double vlCriterioCustoLiquido;
	private Double vlIss;
	
    public SccContratoCobilling() { }
    
	@EmbeddedId
	public SccContratoCobillingPK getId() {
		return this.id;
	}
	public void setId(SccContratoCobillingPK id) {
		this.id = id;
	}
	
	@Column(name="CD_USUARIO_MANUT")
	public String getCdUsuarioManut() {
		return this.cdUsuarioManut;
	}
	public void setCdUsuarioManut(String cdUsuarioManut) {
		this.cdUsuarioManut = cdUsuarioManut;
	}
	
	@Column(name="DS_CRITERIO_CUSTO")
	public String getDsCriterioCusto() {
		return this.dsCriterioCusto;
	}
	public void setDsCriterioCusto(String dsCriterioCusto) {
		this.dsCriterioCusto = dsCriterioCusto;
	}
	
	@Column(name="DS_PERIODO_REPASSE")
	public String getDsPeriodoRepasse() {
		return this.dsPeriodoRepasse;
	}
	public void setDsPeriodoRepasse(String dsPeriodoRepasse) {
		this.dsPeriodoRepasse = dsPeriodoRepasse;
	}
	
    @Temporal( TemporalType.DATE)
	@Column(name="DT_ATUALIZACAO")
	public Date getDtAtualizacao() {
		return this.dtAtualizacao;
	}
	public void setDtAtualizacao(Date dtAtualizacao) {
		this.dtAtualizacao = dtAtualizacao;
	}
	
    @Temporal( TemporalType.DATE)
	@Column(name="DT_CRIACAO")
	public Date getDtCriacao() {
		return this.dtCriacao;
	}
	public void setDtCriacao(Date dtCriacao) {
		this.dtCriacao = dtCriacao;
	}
	
	@Temporal( TemporalType.DATE)
	@Column(name="DT_INICIO_CONTRATO",updatable=false,insertable=false)
	public Date getDtInicioContrato() {
		return this.dtInicioContrato;
	}
	public void setDtInicioContrato(Date dtInicioContrato) {
		this.dtInicioContrato = dtInicioContrato;
	}
	
	@Column(name="CD_TIPO_CONTRATO",updatable=false,insertable=false)
	public String getCdTipoContrato() {
		return this.cdTipoContrato;
	}
	public void setCdTipoContrato(String cdTipoContrato) {
		this.cdTipoContrato = cdTipoContrato;
	}
	
    @Temporal( TemporalType.DATE)
	@Column(name="DT_FIM_CONTRATO")
	public Date getDtFimContrato() {
		return this.dtFimContrato;
	}
	public void setDtFimContrato(Date dtFimContrato) {
		this.dtFimContrato = dtFimContrato;
	}
	
	@Column(name="FL_REPASSA_CPMF")
	public String getFlRepassaCpmf() {
		return this.flRepassaCpmf;
	}
	public void setFlRepassaCpmf(String flRepassaCpmf) {
		this.flRepassaCpmf = flRepassaCpmf;
	}
	
	@Column(name="FL_REPASSA_ICMS")
	public String getFlRepassaIcms() {
		return this.flRepassaIcms;
	}
	public void setFlRepassaIcms(String flRepassaIcms) {
		this.flRepassaIcms = flRepassaIcms;
	}
	
	@Column(name="PE_COFINS")
	public Double getPeCofins() {
		return this.peCofins;
	}
	public void setPeCofins(Double peCofins) {
		this.peCofins = peCofins;
	}
	
	@Column(name="PE_PIS")
	public Double getPePis() {
		return this.pePis;
	}
	public void setPePis(Double pePis) {
		this.pePis = pePis;
	}
	
	@Column(name="PE_RETENCAO")
	public Double getPeRetencao() {
		return this.peRetencao;
	}
	public void setPeRetencao(Double peRetencao) {
		this.peRetencao = peRetencao;
	}
	
	@Column(name="VL_ASSOCIADO_CRITERIO_CUSTO")
	public Double getVlAssociadoCriterioCusto() {
		return this.vlAssociadoCriterioCusto;
	}
	public void setVlAssociadoCriterioCusto(Double vlAssociadoCriterioCusto) {
		this.vlAssociadoCriterioCusto = vlAssociadoCriterioCusto;
	}
	
	@Column(name="VL_CPMF")
	public Double getVlCpmf() {
		return this.vlCpmf;
	}
	public void setVlCpmf(Double vlCpmf) {
		this.vlCpmf = vlCpmf;
	}
	
	@Column(name="VL_CRITERIO_CUSTO_LIQUIDO")
	public Double getVlCriterioCustoLiquido() {
		return this.vlCriterioCustoLiquido;
	}
	public void setVlCriterioCustoLiquido(Double vlCriterioCustoLiquido) {
		this.vlCriterioCustoLiquido = vlCriterioCustoLiquido;
	}
	
	@Column(name="VL_ISS")
	public Double getVlIss() {
		return this.vlIss;
	}
	public void setVlIss(Double vlIss) {
		this.vlIss = vlIss;
	}
	
}
