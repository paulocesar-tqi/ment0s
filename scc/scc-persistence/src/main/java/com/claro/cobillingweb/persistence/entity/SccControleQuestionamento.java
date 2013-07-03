package com.claro.cobillingweb.persistence.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Entity;



@Entity
@Table(name="SCC_CONTROLE_QUESTIONAMENTO")
public class SccControleQuestionamento extends FwjBaseEntidade implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4470425554697197239L;

	@Id
	@SequenceGenerator(name="SCC_CONTROLE_QUESTIONAMENTO_SQQUESTIONAMENTO_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SCC_CONTROLE_QUESTIONAMENTO_SQQUESTIONAMENTO_GENERATOR")
	@Column(name="SQ_QUESTIONAMENTO")
	private long sqQuestionamento;

	@Column(name="CD_EOT_LD")
	private String cdEotLd;

	@Column(name="CD_IDENTIFICACAO_CARTA_CLARO")
	private String cdIdentificacaoCartaClaro;

	@Column(name="CD_IDENTIFICACAO_CARTA_LD")
	private String cdIdentificacaoCartaLd;

	@Column(name="CD_PROCESSO")
	private String cdProcesso;

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
	@Column(name="DT_EVENTO")
	private Date dtEvento;

    @Temporal( TemporalType.DATE)
	@Column(name="DT_PROTOCOLO_CLARO")
	private Date dtProtocoloClaro;

    @Temporal( TemporalType.DATE)
	@Column(name="DT_PROTOCOLO_LD")
	private Date dtProtocoloLd;

	@Column(name="NO_RESPONSAVEL_CLARO")
	private String noResponsavelClaro;

	@Column(name="NO_RESPONSAVEL_LD")
	private String noResponsavelLd;

	@Column(name="TX_ANALISE")
	private String txAnalise;

	@Column(name="TX_ARQUIVOS")
	private String txArquivos;

	@Column(name="TX_COMENTARIO_CLARO")
	private String txComentarioClaro;

	@Column(name="TX_COMENTARIO_LD")
	private String txComentarioLd;

	@Column(name="TX_CORRECAO")
	private String txCorrecao;

	@Column(name="TX_MOTIVOS_REJEICAO")
	private String txMotivosRejeicao;

	//bi-directional many-to-one association to SccStatusQuestionamento
    @ManyToOne
	@JoinColumn(name="CD_STATUS_QUESTIONAMENTO")
	private SccStatusQuestionamento sccStatusQuestionamento;

	//bi-directional many-to-one association to SccResultadoQuestionamento
	@OneToMany(mappedBy="sccControleQuestionamento")
	private Set<SccResultadoQuestionamento> sccResultadoQuestionamentos;

    public SccControleQuestionamento() {
    }

	public long getSqQuestionamento() {
		return this.sqQuestionamento;
	}

	public void setSqQuestionamento(long sqQuestionamento) {
		this.sqQuestionamento = sqQuestionamento;
	}

	public String getCdEotLd() {
		return this.cdEotLd;
	}

	public void setCdEotLd(String cdEotLd) {
		this.cdEotLd = cdEotLd;
	}

	public String getCdIdentificacaoCartaClaro() {
		return this.cdIdentificacaoCartaClaro;
	}

	public void setCdIdentificacaoCartaClaro(String cdIdentificacaoCartaClaro) {
		this.cdIdentificacaoCartaClaro = cdIdentificacaoCartaClaro;
	}

	public String getCdIdentificacaoCartaLd() {
		return this.cdIdentificacaoCartaLd;
	}

	public void setCdIdentificacaoCartaLd(String cdIdentificacaoCartaLd) {
		this.cdIdentificacaoCartaLd = cdIdentificacaoCartaLd;
	}

	public String getCdProcesso() {
		return this.cdProcesso;
	}

	public void setCdProcesso(String cdProcesso) {
		this.cdProcesso = cdProcesso;
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

	public Date getDtEvento() {
		return this.dtEvento;
	}

	public void setDtEvento(Date dtEvento) {
		this.dtEvento = dtEvento;
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

	public String getNoResponsavelClaro() {
		return this.noResponsavelClaro;
	}

	public void setNoResponsavelClaro(String noResponsavelClaro) {
		this.noResponsavelClaro = noResponsavelClaro;
	}

	public String getNoResponsavelLd() {
		return this.noResponsavelLd;
	}

	public void setNoResponsavelLd(String noResponsavelLd) {
		this.noResponsavelLd = noResponsavelLd;
	}

	public String getTxAnalise() {
		return this.txAnalise;
	}

	public void setTxAnalise(String txAnalise) {
		this.txAnalise = txAnalise;
	}

	public String getTxArquivos() {
		return this.txArquivos;
	}

	public void setTxArquivos(String txArquivos) {
		this.txArquivos = txArquivos;
	}

	public String getTxComentarioClaro() {
		return this.txComentarioClaro;
	}

	public void setTxComentarioClaro(String txComentarioClaro) {
		this.txComentarioClaro = txComentarioClaro;
	}

	public String getTxComentarioLd() {
		return this.txComentarioLd;
	}

	public void setTxComentarioLd(String txComentarioLd) {
		this.txComentarioLd = txComentarioLd;
	}

	public String getTxCorrecao() {
		return this.txCorrecao;
	}

	public void setTxCorrecao(String txCorrecao) {
		this.txCorrecao = txCorrecao;
	}

	public String getTxMotivosRejeicao() {
		return this.txMotivosRejeicao;
	}

	public void setTxMotivosRejeicao(String txMotivosRejeicao) {
		this.txMotivosRejeicao = txMotivosRejeicao;
	}

	public SccStatusQuestionamento getSccStatusQuestionamento() {
		return this.sccStatusQuestionamento;
	}

	public void setSccStatusQuestionamento(SccStatusQuestionamento sccStatusQuestionamento) {
		this.sccStatusQuestionamento = sccStatusQuestionamento;
	}
	
	public Set<SccResultadoQuestionamento> getSccResultadoQuestionamentos() {
		return this.sccResultadoQuestionamentos;
	}

	public void setSccResultadoQuestionamentos(Set<SccResultadoQuestionamento> sccResultadoQuestionamentos) {
		this.sccResultadoQuestionamentos = sccResultadoQuestionamentos;
	}
	

}
