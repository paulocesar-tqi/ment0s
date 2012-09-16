package com.claro.cobillingweb.persistence.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.IndexColumn;

/**
 * Contratos pós-pago.
 *
 */

@Entity
@Table(name="SCC_CONTRATO_COBL")
@SequenceGenerator(name="scc_contrato_cobl_sq01" , sequenceName="scc_contrato_cobl_sq01")
public class SccContratoCobl implements Serializable {

	private static final long serialVersionUID = 1L;
	private long cdContratoCobilling;
	private String cdUsuarioManut;
	private String dsContratoCobilling;
	private Date dtAtualizacao;
	private Date dtCriacao;
	private Date dtFimVigencia;
	private Date dtInicioVigencia;
	private List<SccContratoAcordado> sccContratoAcordados;
	private List<SccProdutoContratado> prod;

    public SccContratoCobl() { }
    
	@Id
	@Column(name="CD_CONTRATO_COBILLING")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="scc_contrato_cobl_sq01")
	public long getCdContratoCobilling() {
		return this.cdContratoCobilling;
	}
	
	public void setCdContratoCobilling(long cdContratoCobilling) {
		this.cdContratoCobilling = cdContratoCobilling;
	}
	
	@Column(name="CD_USUARIO_MANUT")
	public String getCdUsuarioManut() {
		return this.cdUsuarioManut;
	}
	
	public void setCdUsuarioManut(String cdUsuarioManut) {
		this.cdUsuarioManut = cdUsuarioManut;
	}
	
	@Column(name="DS_CONTRATO_COBILLING")
	public String getDsContratoCobilling() {
		return this.dsContratoCobilling;
	}
	
	public void setDsContratoCobilling(String dsContratoCobilling) {
		this.dsContratoCobilling = dsContratoCobilling;
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
	@Column(name="DT_FIM_VIGENCIA")
	public Date getDtFimVigencia() {
		return this.dtFimVigencia;
	}
    
	public void setDtFimVigencia(Date dtFimVigencia) {
		this.dtFimVigencia = dtFimVigencia;
	}
	
    @Temporal( TemporalType.DATE)
	@Column(name="DT_INICIO_VIGENCIA")
	public Date getDtInicioVigencia() {
		return this.dtInicioVigencia;
	}
    
	public void setDtInicioVigencia(Date dtInicioVigencia) {
		this.dtInicioVigencia = dtInicioVigencia;
	}
	
	//bi-directional many-to-one association to SccContratoAcordado
	@OneToMany(fetch=FetchType.LAZY)
	@IndexColumn(name="CD_CONTRATO_COBILLING")
	public List<SccContratoAcordado> getSccContratoAcordados() {
		return this.sccContratoAcordados;
	}
	
	public void setSccContratoAcordados(List<SccContratoAcordado> sccContratoAcordados) {
		this.sccContratoAcordados = sccContratoAcordados;
	}
	
	//bi-directional many-to-one association to SccProdutoContratado
	@OneToMany(fetch=FetchType.LAZY)
	@IndexColumn(name="CD_CONTRATO_COBILLING")
	public List<SccProdutoContratado> getProd() {
		return this.prod;
	}
	
	public void setProd(List<SccProdutoContratado> prod) {
		this.prod = prod;
	}
	
}
