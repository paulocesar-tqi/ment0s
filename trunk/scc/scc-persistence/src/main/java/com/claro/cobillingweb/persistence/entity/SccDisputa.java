/**
 * 
 */
package com.claro.cobillingweb.persistence.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * @author vagner.souza
 *
 */

/**
 * The persistent class for the SCC_DISPUTA database table.
 * 
 */

@Entity
@Table(name="SCC_DISPUTA")
public class SccDisputa implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1369399934546280187L;
	
	
	@Id
	@SequenceGenerator(name="SCC_DISPUTA_SQ01" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SCC_DISPUTA_SQ01")
	@Column(name="SQ_DISPUTA")
	private Long sqDisputa;

	@Column(name="CD_IDENTIFICACAO_ATA_ACORDO")
	private String cdIdentificacaoAtaAcordo;

	@Column(name="CD_IDENTIFICACAO_CARTA_CLARO")
	private String cdIdentificacaoCartaClaro;

	@Column(name="CD_IDENTIFICACAO_CARTA_LD")
	private String cdIdentificacaoCartaLd;

	@Column(name="CD_IDENTIFICACAO_TRM_QUITACAO")
	private String cdIdentificacaoTrmQuitacao;

	@Column(name="CD_STATUS_DISPUTA")
	private String cdStatusDisputa;

	@Column(name="CD_USUARIO_MANUT")
	private String cdUsuarioManut;

    @Temporal( TemporalType.DATE)
	@Column(name="DT_ACAO_JUDICIAL")
	private Date dtAcaoJudicial;

    @Temporal( TemporalType.DATE)
	@Column(name="DT_ACORDO")
	private Date dtAcordo;

    @Temporal( TemporalType.DATE)
	@Column(name="DT_ANALISE")
	private Date dtAnalise;

    @Temporal( TemporalType.DATE)
	@Column(name="DT_APROVACAO")
	private Date dtAprovacao;

    @Temporal( TemporalType.DATE)
	@Column(name="DT_ATUALIZACAO")
	private Date dtAtualizacao;

    @Temporal( TemporalType.DATE)
	@Column(name="DT_CONFLITO")
	private Date dtConflito;

    @Temporal( TemporalType.DATE)
	@Column(name="DT_CRIACAO")
	private Date dtCriacao;

    @Temporal( TemporalType.DATE)
	@Column(name="DT_EVENTO")
	private Date dtEvento;

    @Temporal( TemporalType.DATE)
	@Column(name="DT_PAGAMENTO_ACORDO")
	private Date dtPagamentoAcordo;

    @Temporal( TemporalType.DATE)
	@Column(name="DT_PRAZO_CONFLITO")
	private Date dtPrazoConflito;

    @Temporal( TemporalType.DATE)
	@Column(name="DT_PRAZO_CONTESTACAO")
	private Date dtPrazoContestacao;

    @Temporal( TemporalType.DATE)
	@Column(name="DT_PRAZO_RESPOSTA")
	private Date dtPrazoResposta;

    @Temporal( TemporalType.DATE)
	@Column(name="DT_PREVISAO_CONCLUSAO_AJ")
	private Date dtPrevisaoConclusaoAj;

    @Temporal( TemporalType.DATE)
	@Column(name="DT_PREVISAO_CONCLUSAO_RA")
	private Date dtPrevisaoConclusaoRa;

    @Temporal( TemporalType.DATE)
	@Column(name="DT_PROTOCOLO_CLARO")
	private Date dtProtocoloClaro;

    @Temporal( TemporalType.DATE)
	@Column(name="DT_PROTOCOLO_LD")
	private Date dtProtocoloLd;

    @Temporal( TemporalType.DATE)
	@Column(name="DT_PROVISAO")
	private Date dtProvisao;

    @Temporal( TemporalType.DATE)
	@Column(name="DT_RECLAMACAO_ANATEL")
	private Date dtReclamacaoAnatel;

    @Temporal( TemporalType.DATE)
	@Column(name="DT_REPASSE_CONTESTACAO")
	private Date dtRepasseContestacao;

    @Temporal( TemporalType.DATE)
	@Column(name="DT_REPASSE_DISPUTA")
	private Date dtRepasseDisputa;

    @Temporal( TemporalType.DATE)
	@Column(name="DT_TERMINO_DISPUTA")
	private Date dtTerminoDisputa;

    @Temporal( TemporalType.DATE)
	@Column(name="DT_TERMO_QUITACAO")
	private Date dtTermoQuitacao;

	@Column(name="IN_RISCO_DISPUTA")
	private String inRiscoDisputa;

	@Column(name="NO_RESPONSAVEL_APROVACAO")
	private String noResponsavelAprovacao;

	@Column(name="NO_RESPONSAVEL_CLARO")
	private String noResponsavelClaro;

	@Column(name="NO_RESPONSAVEL_LD")
	private String noResponsavelLd;

	@Column(name="QT_PARCELAS_ACORDO")
	private Long qtParcelasAcordo;

	@Column(name="TX_ANALISE")
	private String txAnalise;

	@Column(name="TX_COMENTARIO")
	private String txComentario;

	@Column(name="TX_CONCILIACAO")
	private String txConciliacao;

	@Column(name="TX_CONTESTACAO")
	private String txContestacao;

	@Column(name="TX_RESPOSTA")
	private String txResposta;

	@Column(name="VL_ACERTO_CONCILIACAO")
	private Double vlAcertoConciliacao;

	@Column(name="VL_ACORDO")
	private Double vlAcordo;

	@Column(name="VL_CONTESTADO")
	private Double vlContestado;

	@Column(name="VL_DIFERENCA_CONTESTADO")
	private Double vlDiferencaContestado;

	@Column(name="VL_PROPOSTO")
	private Double vlProposto;

	@Column(name="VL_PROVISAO")
	private Double vlProvisao;

	@Column(name="VL_SALDO_REPASSAR")
	private Double vlSaldoRepassar;

	//bi-directional many-to-one association to SccOperadora
    @ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CD_EOT_LD")
	private SccOperadora sccOperadora;

	//bi-directional many-to-one association to SccDisputaDetalhe
    @JsonIgnore
	@OneToMany(mappedBy="sccDisputa", fetch=FetchType.LAZY)
	private Set<SccDisputaDetalhe> sccDisputaDetalhes;

    public SccDisputa() {
    }

	public Long getSqDisputa() {
		return sqDisputa;
	}

	public void setSqDisputa(Long sqDisputa) {
		this.sqDisputa = sqDisputa;
	}

	public String getCdIdentificacaoAtaAcordo() {
		return cdIdentificacaoAtaAcordo;
	}

	public void setCdIdentificacaoAtaAcordo(String cdIdentificacaoAtaAcordo) {
		this.cdIdentificacaoAtaAcordo = cdIdentificacaoAtaAcordo;
	}

	public String getCdIdentificacaoCartaClaro() {
		return cdIdentificacaoCartaClaro;
	}

	public void setCdIdentificacaoCartaClaro(String cdIdentificacaoCartaClaro) {
		this.cdIdentificacaoCartaClaro = cdIdentificacaoCartaClaro;
	}

	public String getCdIdentificacaoCartaLd() {
		return cdIdentificacaoCartaLd;
	}

	public void setCdIdentificacaoCartaLd(String cdIdentificacaoCartaLd) {
		this.cdIdentificacaoCartaLd = cdIdentificacaoCartaLd;
	}

	public String getCdIdentificacaoTrmQuitacao() {
		return cdIdentificacaoTrmQuitacao;
	}

	public void setCdIdentificacaoTrmQuitacao(String cdIdentificacaoTrmQuitacao) {
		this.cdIdentificacaoTrmQuitacao = cdIdentificacaoTrmQuitacao;
	}

	public String getCdStatusDisputa() {
		return cdStatusDisputa;
	}

	public void setCdStatusDisputa(String cdStatusDisputa) {
		this.cdStatusDisputa = cdStatusDisputa;
	}

	public String getCdUsuarioManut() {
		return cdUsuarioManut;
	}

	public void setCdUsuarioManut(String cdUsuarioManut) {
		this.cdUsuarioManut = cdUsuarioManut;
	}

	public Date getDtAcaoJudicial() {
		return dtAcaoJudicial;
	}

	public void setDtAcaoJudicial(Date dtAcaoJudicial) {
		this.dtAcaoJudicial = dtAcaoJudicial;
	}

	public Date getDtAcordo() {
		return dtAcordo;
	}

	public void setDtAcordo(Date dtAcordo) {
		this.dtAcordo = dtAcordo;
	}

	public Date getDtAnalise() {
		return dtAnalise;
	}

	public void setDtAnalise(Date dtAnalise) {
		this.dtAnalise = dtAnalise;
	}

	public Date getDtAprovacao() {
		return dtAprovacao;
	}

	public void setDtAprovacao(Date dtAprovacao) {
		this.dtAprovacao = dtAprovacao;
	}

	public Date getDtAtualizacao() {
		return dtAtualizacao;
	}

	public void setDtAtualizacao(Date dtAtualizacao) {
		this.dtAtualizacao = dtAtualizacao;
	}

	public Date getDtConflito() {
		return dtConflito;
	}

	public void setDtConflito(Date dtConflito) {
		this.dtConflito = dtConflito;
	}

	public Date getDtCriacao() {
		return dtCriacao;
	}

	public void setDtCriacao(Date dtCriacao) {
		this.dtCriacao = dtCriacao;
	}

	public Date getDtEvento() {
		return dtEvento;
	}

	public void setDtEvento(Date dtEvento) {
		this.dtEvento = dtEvento;
	}

	public Date getDtPagamentoAcordo() {
		return dtPagamentoAcordo;
	}

	public void setDtPagamentoAcordo(Date dtPagamentoAcordo) {
		this.dtPagamentoAcordo = dtPagamentoAcordo;
	}

	public Date getDtPrazoConflito() {
		return dtPrazoConflito;
	}

	public void setDtPrazoConflito(Date dtPrazoConflito) {
		this.dtPrazoConflito = dtPrazoConflito;
	}

	public Date getDtPrazoContestacao() {
		return dtPrazoContestacao;
	}

	public void setDtPrazoContestacao(Date dtPrazoContestacao) {
		this.dtPrazoContestacao = dtPrazoContestacao;
	}

	public Date getDtPrazoResposta() {
		return dtPrazoResposta;
	}

	public void setDtPrazoResposta(Date dtPrazoResposta) {
		this.dtPrazoResposta = dtPrazoResposta;
	}

	public Date getDtPrevisaoConclusaoAj() {
		return dtPrevisaoConclusaoAj;
	}

	public void setDtPrevisaoConclusaoAj(Date dtPrevisaoConclusaoAj) {
		this.dtPrevisaoConclusaoAj = dtPrevisaoConclusaoAj;
	}

	public Date getDtPrevisaoConclusaoRa() {
		return dtPrevisaoConclusaoRa;
	}

	public void setDtPrevisaoConclusaoRa(Date dtPrevisaoConclusaoRa) {
		this.dtPrevisaoConclusaoRa = dtPrevisaoConclusaoRa;
	}

	public Date getDtProtocoloClaro() {
		return dtProtocoloClaro;
	}

	public void setDtProtocoloClaro(Date dtProtocoloClaro) {
		this.dtProtocoloClaro = dtProtocoloClaro;
	}

	public Date getDtProtocoloLd() {
		return dtProtocoloLd;
	}

	public void setDtProtocoloLd(Date dtProtocoloLd) {
		this.dtProtocoloLd = dtProtocoloLd;
	}

	public Date getDtProvisao() {
		return dtProvisao;
	}

	public void setDtProvisao(Date dtProvisao) {
		this.dtProvisao = dtProvisao;
	}

	public Date getDtReclamacaoAnatel() {
		return dtReclamacaoAnatel;
	}

	public void setDtReclamacaoAnatel(Date dtReclamacaoAnatel) {
		this.dtReclamacaoAnatel = dtReclamacaoAnatel;
	}

	public Date getDtRepasseContestacao() {
		return dtRepasseContestacao;
	}

	public void setDtRepasseContestacao(Date dtRepasseContestacao) {
		this.dtRepasseContestacao = dtRepasseContestacao;
	}

	public Date getDtRepasseDisputa() {
		return dtRepasseDisputa;
	}

	public void setDtRepasseDisputa(Date dtRepasseDisputa) {
		this.dtRepasseDisputa = dtRepasseDisputa;
	}

	public Date getDtTerminoDisputa() {
		return dtTerminoDisputa;
	}

	public void setDtTerminoDisputa(Date dtTerminoDisputa) {
		this.dtTerminoDisputa = dtTerminoDisputa;
	}

	public Date getDtTermoQuitacao() {
		return dtTermoQuitacao;
	}

	public void setDtTermoQuitacao(Date dtTermoQuitacao) {
		this.dtTermoQuitacao = dtTermoQuitacao;
	}

	public String getInRiscoDisputa() {
		return inRiscoDisputa;
	}

	public void setInRiscoDisputa(String inRiscoDisputa) {
		this.inRiscoDisputa = inRiscoDisputa;
	}

	public String getNoResponsavelAprovacao() {
		return noResponsavelAprovacao;
	}

	public void setNoResponsavelAprovacao(String noResponsavelAprovacao) {
		this.noResponsavelAprovacao = noResponsavelAprovacao;
	}

	public String getNoResponsavelClaro() {
		return noResponsavelClaro;
	}

	public void setNoResponsavelClaro(String noResponsavelClaro) {
		this.noResponsavelClaro = noResponsavelClaro;
	}

	public String getNoResponsavelLd() {
		return noResponsavelLd;
	}

	public void setNoResponsavelLd(String noResponsavelLd) {
		this.noResponsavelLd = noResponsavelLd;
	}

	public Long getQtParcelasAcordo() {
		return qtParcelasAcordo;
	}

	public void setQtParcelasAcordo(Long qtParcelasAcordo) {
		this.qtParcelasAcordo = qtParcelasAcordo;
	}

	public String getTxAnalise() {
		return txAnalise;
	}

	public void setTxAnalise(String txAnalise) {
		this.txAnalise = txAnalise;
	}

	public String getTxComentario() {
		return txComentario;
	}

	public void setTxComentario(String txComentario) {
		this.txComentario = txComentario;
	}

	public String getTxConciliacao() {
		return txConciliacao;
	}

	public void setTxConciliacao(String txConciliacao) {
		this.txConciliacao = txConciliacao;
	}

	public String getTxContestacao() {
		return txContestacao;
	}

	public void setTxContestacao(String txContestacao) {
		this.txContestacao = txContestacao;
	}

	public String getTxResposta() {
		return txResposta;
	}

	public void setTxResposta(String txResposta) {
		this.txResposta = txResposta;
	}

	public Double getVlAcertoConciliacao() {
		return vlAcertoConciliacao;
	}

	public void setVlAcertoConciliacao(Double vlAcertoConciliacao) {
		this.vlAcertoConciliacao = vlAcertoConciliacao;
	}

	public Double getVlAcordo() {
		return vlAcordo;
	}

	public void setVlAcordo(Double vlAcordo) {
		this.vlAcordo = vlAcordo;
	}

	public Double getVlContestado() {
		return vlContestado;
	}

	public void setVlContestado(Double vlContestado) {
		this.vlContestado = vlContestado;
	}

	public Double getVlDiferencaContestado() {
		return vlDiferencaContestado;
	}

	public void setVlDiferencaContestado(Double vlDiferencaContestado) {
		this.vlDiferencaContestado = vlDiferencaContestado;
	}

	public Double getVlProposto() {
		return vlProposto;
	}

	public void setVlProposto(Double vlProposto) {
		this.vlProposto = vlProposto;
	}

	public Double getVlProvisao() {
		return vlProvisao;
	}

	public void setVlProvisao(Double vlProvisao) {
		this.vlProvisao = vlProvisao;
	}

	public Double getVlSaldoRepassar() {
		return vlSaldoRepassar;
	}

	public void setVlSaldoRepassar(Double vlSaldoRepassar) {
		this.vlSaldoRepassar = vlSaldoRepassar;
	}

	public SccOperadora getSccOperadora() {
		return sccOperadora;
	}

	public void setSccOperadora(SccOperadora sccOperadora) {
		this.sccOperadora = sccOperadora;
	}

	public Set<SccDisputaDetalhe> getSccDisputaDetalhes() {
		return sccDisputaDetalhes;
	}

	public void setSccDisputaDetalhes(Set<SccDisputaDetalhe> sccDisputaDetalhes) {
		this.sccDisputaDetalhes = sccDisputaDetalhes;
	}


}
