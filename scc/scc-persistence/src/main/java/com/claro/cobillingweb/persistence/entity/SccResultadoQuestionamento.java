package com.claro.cobillingweb.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the SCC_RESULTADO_QUESTIONAMENTO database table.
 * 
 */
@Entity
@Table(name="SCC_RESULTADO_QUESTIONAMENTO")
public class SccResultadoQuestionamento implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SccResultadoQuestionamentoPK id;

	@Column(name="CD_USUARIO_MANUT")
	private String cdUsuarioManut;

    @Temporal( TemporalType.DATE)
	@Column(name="DT_ANALISE")
	private Date dtAnalise;

    @Temporal( TemporalType.DATE)
	@Column(name="DT_ATUALIZACAO")
	private Date dtAtualizacao;

    @Temporal( TemporalType.DATE)
	@Column(name="DT_CORRECAO")
	private Date dtCorrecao;

    @Temporal( TemporalType.DATE)
	@Column(name="DT_CRIACAO")
	private Date dtCriacao;

    @Temporal( TemporalType.DATE)
	@Column(name="DT_PROTOCOLO_CLARO")
	private Date dtProtocoloClaro;

    @Temporal( TemporalType.DATE)
	@Column(name="DT_PROTOCOLO_LD")
	private Date dtProtocoloLd;

    @Temporal( TemporalType.DATE)
	@Column(name="DT_REPASSE")
	private Date dtRepasse;

	@Column(name="MI_TARIFADOS")
	private BigDecimal miTarifados;

	@Column(name="PE_PROCEDENTE")
	private BigDecimal peProcedente;

	@Column(name="PE_SEM_ANALISE")
	private BigDecimal peSemAnalise;

	@Column(name="QT_CDRS")
	private BigDecimal qtCdrs;

	@Column(name="VL_PENALIDADE_CLARO")
	private BigDecimal vlPenalidadeClaro;

	@Column(name="VL_PENALIDADE_LD")
	private BigDecimal vlPenalidadeLd;

	@Column(name="VL_POTENCIAL_CLARO")
	private BigDecimal vlPotencialClaro;

	@Column(name="VL_POTENCIAL_LD")
	private BigDecimal vlPotencialLd;

	//bi-directional many-to-one association to SccControleQuestionamento
    @ManyToOne
	@JoinColumn(name="SQ_QUESTIONAMENTO")
	private SccControleQuestionamento sccControleQuestionamento;

    public SccResultadoQuestionamento() {
    }

	public SccResultadoQuestionamentoPK getId() {
		return this.id;
	}

	public void setId(SccResultadoQuestionamentoPK id) {
		this.id = id;
	}
	
	public String getCdUsuarioManut() {
		return this.cdUsuarioManut;
	}

	public void setCdUsuarioManut(String cdUsuarioManut) {
		this.cdUsuarioManut = cdUsuarioManut;
	}

	public Date getDtAnalise() {
		return this.dtAnalise;
	}

	public void setDtAnalise(Date dtAnalise) {
		this.dtAnalise = dtAnalise;
	}

	public Date getDtAtualizacao() {
		return this.dtAtualizacao;
	}

	public void setDtAtualizacao(Date dtAtualizacao) {
		this.dtAtualizacao = dtAtualizacao;
	}

	public Date getDtCorrecao() {
		return this.dtCorrecao;
	}

	public void setDtCorrecao(Date dtCorrecao) {
		this.dtCorrecao = dtCorrecao;
	}

	public Date getDtCriacao() {
		return this.dtCriacao;
	}

	public void setDtCriacao(Date dtCriacao) {
		this.dtCriacao = dtCriacao;
	}

	public Date getDtProtocoloClaro() {
		return this.dtProtocoloClaro;
	}

	public void setDtProtocoloClaro(Date dtProtocoloClaro) {
		this.dtProtocoloClaro = dtProtocoloClaro;
	}

	public Date getDtProtocoloLd() {
		return this.dtProtocoloLd;
	}

	public void setDtProtocoloLd(Date dtProtocoloLd) {
		this.dtProtocoloLd = dtProtocoloLd;
	}

	public Date getDtRepasse() {
		return this.dtRepasse;
	}

	public void setDtRepasse(Date dtRepasse) {
		this.dtRepasse = dtRepasse;
	}

	public BigDecimal getMiTarifados() {
		return this.miTarifados;
	}

	public void setMiTarifados(BigDecimal miTarifados) {
		this.miTarifados = miTarifados;
	}

	public BigDecimal getPeProcedente() {
		return this.peProcedente;
	}

	public void setPeProcedente(BigDecimal peProcedente) {
		this.peProcedente = peProcedente;
	}

	public BigDecimal getPeSemAnalise() {
		return this.peSemAnalise;
	}

	public void setPeSemAnalise(BigDecimal peSemAnalise) {
		this.peSemAnalise = peSemAnalise;
	}

	public BigDecimal getQtCdrs() {
		return this.qtCdrs;
	}

	public void setQtCdrs(BigDecimal qtCdrs) {
		this.qtCdrs = qtCdrs;
	}

	public BigDecimal getVlPenalidadeClaro() {
		return this.vlPenalidadeClaro;
	}

	public void setVlPenalidadeClaro(BigDecimal vlPenalidadeClaro) {
		this.vlPenalidadeClaro = vlPenalidadeClaro;
	}

	public BigDecimal getVlPenalidadeLd() {
		return this.vlPenalidadeLd;
	}

	public void setVlPenalidadeLd(BigDecimal vlPenalidadeLd) {
		this.vlPenalidadeLd = vlPenalidadeLd;
	}

	public BigDecimal getVlPotencialClaro() {
		return this.vlPotencialClaro;
	}

	public void setVlPotencialClaro(BigDecimal vlPotencialClaro) {
		this.vlPotencialClaro = vlPotencialClaro;
	}

	public BigDecimal getVlPotencialLd() {
		return this.vlPotencialLd;
	}

	public void setVlPotencialLd(BigDecimal vlPotencialLd) {
		this.vlPotencialLd = vlPotencialLd;
	}

	public SccControleQuestionamento getSccControleQuestionamento() {
		return this.sccControleQuestionamento;
	}

	public void setSccControleQuestionamento(SccControleQuestionamento sccControleQuestionamento) {
		this.sccControleQuestionamento = sccControleQuestionamento;
	}
	
}