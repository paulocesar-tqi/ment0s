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
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * @author vagner.souza
 *
 */
/**
 * The persistent class for the SCC_DISPUTA_PRE_PAGO database table.
 * 
 */
@Entity
@Table(name = "SCC_DISPUTA_PRE_PAGO")
public class SccDisputaPrePago implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5987218698108017265L;

	@Id
	@SequenceGenerator(name = "SCC_DISPUTA_PRE_PAGO_SQDISPUTAPREPAGO_GENERATOR")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SCC_DISPUTA_PRE_PAGO_SQDISPUTAPREPAGO_GENERATOR")
	@Column(name = "SQ_DISPUTA_PRE_PAGO")
	private Long sqDisputaPrePago;

	@Column(name = "CD_ANALISTA_INPUT_ACAO_JUDIC")
	private String cdAnalistaInputAcaoJudic;

	@Column(name = "CD_ANALISTA_INPUT_ACORDO")
	private String cdAnalistaInputAcordo;

	@Column(name = "CD_ANALISTA_INPUT_CARTA_CLARO")
	private String cdAnalistaInputCartaClaro;

	@Column(name = "CD_ANALISTA_INPUT_CARTA_LD")
	private String cdAnalistaInputCartaLd;

	@Column(name = "CD_ANALISTA_INPUT_RA")
	private String cdAnalistaInputRa;

	@Column(name = "CD_ANALISTA_INPUT_REPASSE")
	private String cdAnalistaInputRepasse;

	@Column(name = "CD_IDENTIFICACAO_ATA_ACORDO")
	private String cdIdentificacaoAtaAcordo;

	@Column(name = "CD_IDENTIFICACAO_CARTA_CLARO")
	private String cdIdentificacaoCartaClaro;

	@Column(name = "CD_IDENTIFICACAO_CARTA_LD")
	private String cdIdentificacaoCartaLd;

	@Column(name = "CD_STATUS_DISPUTA")
	private String cdStatusDisputa;

	@Column(name = "CD_TIPO_DISPUTA")
	private String cdTipoDisputa;

	@Column(name = "DS_PLEITO")
	private String dsPleito;

	@Column(name = "DS_RESPOSTA_CARTA_CLARO")
	private String dsRespostaCartaClaro;

	@Temporal(TemporalType.DATE)
	@Column(name = "DT_ASSINATURA_ACORDO")
	private Date dtAssinaturaAcordo;

	@Temporal(TemporalType.DATE)
	@Column(name = "DT_DISPUTA")
	private Date dtDisputa;

	@Temporal(TemporalType.DATE)
	@Column(name = "DT_INPUT_ACORDO")
	private Date dtInputAcordo;

	@Temporal(TemporalType.DATE)
	@Column(name = "DT_INPUT_CARTA_CLARO")
	private Date dtInputCartaClaro;

	@Temporal(TemporalType.DATE)
	@Column(name = "DT_INPUT_CARTA_LD")
	private Date dtInputCartaLd;

	@Temporal(TemporalType.DATE)
	@Column(name = "DT_INPUT_RA")
	private Date dtInputRa;

	@Temporal(TemporalType.DATE)
	@Column(name = "DT_LIBERACAO_REPASSE")
	private Date dtLiberacaoRepasse;

	@Temporal(TemporalType.DATE)
	@Column(name = "DT_NOTIFICACAO_ACAO_JUDICIAL")
	private Date dtNotificacaoAcaoJudicial;

	@Temporal(TemporalType.DATE)
	@Column(name = "DT_PRAZO_DISPUTA")
	private Date dtPrazoDisputa;

	@Temporal(TemporalType.DATE)
	@Column(name = "DT_PRAZO_RESPOSTA")
	private Date dtPrazoResposta;

	@Temporal(TemporalType.DATE)
	@Column(name = "DT_PREV_TERMINO_ACAO_JUDICIAL")
	private Date dtPrevTerminoAcaoJudicial;

	@Temporal(TemporalType.DATE)
	@Column(name = "DT_PREV_TERMINO_RA_ANATEL")
	private Date dtPrevTerminoRaAnatel;

	@Temporal(TemporalType.DATE)
	@Column(name = "DT_PROGRAMADA_REPASSE")
	private Date dtProgramadaRepasse;

	@Temporal(TemporalType.DATE)
	@Column(name = "DT_PROTOCOLO_CARTA_CLARO")
	private Date dtProtocoloCartaClaro;

	@Temporal(TemporalType.DATE)
	@Column(name = "DT_PROTOCOLO_LD")
	private Date dtProtocoloLd;

	@Temporal(TemporalType.DATE)
	@Column(name = "DT_RA_ANATEL")
	private Date dtRaAnatel;

	@Temporal(TemporalType.DATE)
	@Column(name = "DT_REPASSE_DISPUTA")
	private Date dtRepasseDisputa;

	@Temporal(TemporalType.DATE)
	@Column(name = "DT_REPASSE_SCC")
	private Date dtRepasseScc;

	@Column(name = "FG_ACAO_JUDICIAL")
	private String fgAcaoJudicial;

	@Column(name = "FG_DISPUTA_FORA_DO_PRAZO")
	private String fgDisputaForaDoPrazo;

	@Column(name = "FG_DISPUTA_SEM_RESPOSTA")
	private String fgDisputaSemResposta;

	@Column(name = "FG_RA_ANATEL")
	private String fgRaAnatel;

	@Column(name = "FG_REPASSE_LIBERADO")
	private String fgRepasseLiberado;

	@Column(name = "IN_RISCO_DISPUTA")
	private String inRiscoDisputa;

	@Column(name = "NO_RESPONSAVEL_ACORDO_CLARO")
	private String noResponsavelAcordoClaro;

	@Column(name = "NO_RESPONSAVEL_ACORDO_LD")
	private String noResponsavelAcordoLd;

	@Column(name = "NO_RESPONSAVEL_CARTA_LD")
	private String noResponsavelCartaLd;

	@Column(name = "TX_AVALIACAO_JURIDICA")
	private String txAvaliacaoJuridica;

	@Column(name = "TX_AVALIACAO_OPERACIONAL")
	private String txAvaliacaoOperacional;

	@Column(name = "TX_AVALIACAO_REGULATORIA")
	private String txAvaliacaoRegulatoria;

	@Column(name = "TX_COMENTARIO_ACAO_JUDICIAL")
	private String txComentarioAcaoJudicial;

	@Column(name = "TX_COMENTARIO_ANALISE")
	private String txComentarioAnalise;

	@Column(name = "TX_COMENTARIO_CONCILIACAO")
	private String txComentarioConciliacao;

	@Column(name = "TX_COMENTARIO_RA_ANATEL")
	private String txComentarioRaAnatel;

	@Column(name = "TX_COMENTARIO_REPASSE")
	private String txComentarioRepasse;

	@Column(name = "VL_ACERTO_CONCILIACAO_CLARO")
	private Double vlAcertoConciliacaoClaro;

	@Column(name = "VL_ACERTO_CONCILIACAO_LD")
	private Double vlAcertoConciliacaoLd;

	@Column(name = "VL_ACORDADO")
	private Double vlAcordado;

	@Column(name = "VL_DIFERENCA_VL_PLEITO")
	private Double vlDiferencaVlPleito;

	@Column(name = "VL_DIFERENCA_VL_PROPOSTO")
	private Double vlDiferencaVlProposto;

	@Column(name = "VL_PLEITO")
	private Double vlPleito;

	@Column(name = "VL_PROPOSTO_CARTA_CLARO")
	private Double vlPropostoCartaClaro;

	@Column(name = "VL_PROVISAO_DISPUTA")
	private Double vlProvisaoDisputa;

	// bi-directional many-to-one association to SccDisputaDetalhePrePago
	@JsonIgnore
	@OneToMany(mappedBy = "sccDisputaPrePago", fetch=FetchType.LAZY)
	private Set<SccDisputaDetalhePrePago> sccDisputaDetalhePrePagos;

	// bi-directional many-to-one association to SccOperadora
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "CD_EOT_LD")
	private SccOperadora sccOperadora;
	
	@Transient
	private Date dtInputCartaLdTra;

	public SccDisputaPrePago() {
	}

	public Long getSqDisputaPrePago() {
		return this.sqDisputaPrePago;
	}

	public void setSqDisputaPrePago(Long sqDisputaPrePago) {
		this.sqDisputaPrePago = sqDisputaPrePago;
	}

	public String getCdAnalistaInputAcaoJudic() {
		return this.cdAnalistaInputAcaoJudic;
	}

	public void setCdAnalistaInputAcaoJudic(String cdAnalistaInputAcaoJudic) {
		this.cdAnalistaInputAcaoJudic = cdAnalistaInputAcaoJudic;
	}

	public String getCdAnalistaInputAcordo() {
		return this.cdAnalistaInputAcordo;
	}

	public void setCdAnalistaInputAcordo(String cdAnalistaInputAcordo) {
		this.cdAnalistaInputAcordo = cdAnalistaInputAcordo;
	}

	public String getCdAnalistaInputCartaClaro() {
		return this.cdAnalistaInputCartaClaro;
	}

	public void setCdAnalistaInputCartaClaro(String cdAnalistaInputCartaClaro) {
		this.cdAnalistaInputCartaClaro = cdAnalistaInputCartaClaro;
	}

	public String getCdAnalistaInputCartaLd() {
		return this.cdAnalistaInputCartaLd;
	}

	public void setCdAnalistaInputCartaLd(String cdAnalistaInputCartaLd) {
		this.cdAnalistaInputCartaLd = cdAnalistaInputCartaLd;
	}

	public String getCdAnalistaInputRa() {
		return this.cdAnalistaInputRa;
	}

	public void setCdAnalistaInputRa(String cdAnalistaInputRa) {
		this.cdAnalistaInputRa = cdAnalistaInputRa;
	}

	public String getCdAnalistaInputRepasse() {
		return this.cdAnalistaInputRepasse;
	}

	public void setCdAnalistaInputRepasse(String cdAnalistaInputRepasse) {
		this.cdAnalistaInputRepasse = cdAnalistaInputRepasse;
	}

	public String getCdIdentificacaoAtaAcordo() {
		return this.cdIdentificacaoAtaAcordo;
	}

	public void setCdIdentificacaoAtaAcordo(String cdIdentificacaoAtaAcordo) {
		this.cdIdentificacaoAtaAcordo = cdIdentificacaoAtaAcordo;
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

	public String getCdStatusDisputa() {
		return this.cdStatusDisputa;
	}

	public void setCdStatusDisputa(String cdStatusDisputa) {
		this.cdStatusDisputa = cdStatusDisputa;
	}

	public String getCdTipoDisputa() {
		return this.cdTipoDisputa;
	}

	public void setCdTipoDisputa(String cdTipoDisputa) {
		this.cdTipoDisputa = cdTipoDisputa;
	}

	public String getDsPleito() {
		return this.dsPleito;
	}

	public void setDsPleito(String dsPleito) {
		this.dsPleito = dsPleito;
	}

	public String getDsRespostaCartaClaro() {
		return this.dsRespostaCartaClaro;
	}

	public void setDsRespostaCartaClaro(String dsRespostaCartaClaro) {
		this.dsRespostaCartaClaro = dsRespostaCartaClaro;
	}

	public Date getDtAssinaturaAcordo() {
		return this.dtAssinaturaAcordo;
	}

	public void setDtAssinaturaAcordo(Date dtAssinaturaAcordo) {
		this.dtAssinaturaAcordo = dtAssinaturaAcordo;
	}

	public Date getDtDisputa() {
		return this.dtDisputa;
	}

	public void setDtDisputa(Date dtDisputa) {
		this.dtDisputa = dtDisputa;
	}

	public Date getDtInputAcordo() {
		return this.dtInputAcordo;
	}

	public void setDtInputAcordo(Date dtInputAcordo) {
		this.dtInputAcordo = dtInputAcordo;
	}

	public Date getDtInputCartaClaro() {
		return this.dtInputCartaClaro;
	}

	public void setDtInputCartaClaro(Date dtInputCartaClaro) {
		this.dtInputCartaClaro = dtInputCartaClaro;
	}

	public Date getDtInputCartaLd() {
		return this.dtInputCartaLd;
	}

	public void setDtInputCartaLd(Date dtInputCartaLd) {
		this.setDtInputCartaLdTra(dtInputCartaLd);
		this.dtInputCartaLd = dtInputCartaLd;
	}

	public Date getDtInputRa() {
		return this.dtInputRa;
	}

	public void setDtInputRa(Date dtInputRa) {
		this.dtInputRa = dtInputRa;
	}

	public Date getDtLiberacaoRepasse() {
		return this.dtLiberacaoRepasse;
	}

	public void setDtLiberacaoRepasse(Date dtLiberacaoRepasse) {
		this.dtLiberacaoRepasse = dtLiberacaoRepasse;
	}

	public Date getDtNotificacaoAcaoJudicial() {
		return this.dtNotificacaoAcaoJudicial;
	}

	public void setDtNotificacaoAcaoJudicial(Date dtNotificacaoAcaoJudicial) {
		this.dtNotificacaoAcaoJudicial = dtNotificacaoAcaoJudicial;
	}

	public Date getDtPrazoDisputa() {
		return this.dtPrazoDisputa;
	}

	public void setDtPrazoDisputa(Date dtPrazoDisputa) {
		this.dtPrazoDisputa = dtPrazoDisputa;
	}

	public Date getDtPrazoResposta() {
		return this.dtPrazoResposta;
	}

	public void setDtPrazoResposta(Date dtPrazoResposta) {
		this.dtPrazoResposta = dtPrazoResposta;
	}

	public Date getDtPrevTerminoAcaoJudicial() {
		return this.dtPrevTerminoAcaoJudicial;
	}

	public void setDtPrevTerminoAcaoJudicial(Date dtPrevTerminoAcaoJudicial) {
		this.dtPrevTerminoAcaoJudicial = dtPrevTerminoAcaoJudicial;
	}

	public Date getDtPrevTerminoRaAnatel() {
		return this.dtPrevTerminoRaAnatel;
	}

	public void setDtPrevTerminoRaAnatel(Date dtPrevTerminoRaAnatel) {
		this.dtPrevTerminoRaAnatel = dtPrevTerminoRaAnatel;
	}

	public Date getDtProgramadaRepasse() {
		return this.dtProgramadaRepasse;
	}

	public void setDtProgramadaRepasse(Date dtProgramadaRepasse) {
		this.dtProgramadaRepasse = dtProgramadaRepasse;
	}

	public Date getDtProtocoloCartaClaro() {
		return this.dtProtocoloCartaClaro;
	}

	public void setDtProtocoloCartaClaro(Date dtProtocoloCartaClaro) {
		this.dtProtocoloCartaClaro = dtProtocoloCartaClaro;
	}

	public Date getDtProtocoloLd() {
		return this.dtProtocoloLd;
	}

	public void setDtProtocoloLd(Date dtProtocoloLd) {
		this.dtProtocoloLd = dtProtocoloLd;
	}

	public Date getDtRaAnatel() {
		return this.dtRaAnatel;
	}

	public void setDtRaAnatel(Date dtRaAnatel) {
		this.dtRaAnatel = dtRaAnatel;
	}

	public Date getDtRepasseDisputa() {
		return this.dtRepasseDisputa;
	}

	public void setDtRepasseDisputa(Date dtRepasseDisputa) {
		this.dtRepasseDisputa = dtRepasseDisputa;
	}

	public Date getDtRepasseScc() {
		return this.dtRepasseScc;
	}

	public void setDtRepasseScc(Date dtRepasseScc) {
		this.dtRepasseScc = dtRepasseScc;
	}

	public String getFgAcaoJudicial() {
		return this.fgAcaoJudicial;
	}

	public void setFgAcaoJudicial(String fgAcaoJudicial) {
		this.fgAcaoJudicial = fgAcaoJudicial;
	}

	public String getFgDisputaForaDoPrazo() {
		return this.fgDisputaForaDoPrazo;
	}

	public void setFgDisputaForaDoPrazo(String fgDisputaForaDoPrazo) {
		this.fgDisputaForaDoPrazo = fgDisputaForaDoPrazo;
	}

	public String getFgDisputaSemResposta() {
		return this.fgDisputaSemResposta;
	}

	public void setFgDisputaSemResposta(String fgDisputaSemResposta) {
		this.fgDisputaSemResposta = fgDisputaSemResposta;
	}

	public String getFgRaAnatel() {
		return this.fgRaAnatel;
	}

	public void setFgRaAnatel(String fgRaAnatel) {
		this.fgRaAnatel = fgRaAnatel;
	}

	public String getFgRepasseLiberado() {
		return this.fgRepasseLiberado;
	}

	public void setFgRepasseLiberado(String fgRepasseLiberado) {
		this.fgRepasseLiberado = fgRepasseLiberado;
	}

	public String getInRiscoDisputa() {
		return this.inRiscoDisputa;
	}

	public void setInRiscoDisputa(String inRiscoDisputa) {
		this.inRiscoDisputa = inRiscoDisputa;
	}

	public String getNoResponsavelAcordoClaro() {
		return this.noResponsavelAcordoClaro;
	}

	public void setNoResponsavelAcordoClaro(String noResponsavelAcordoClaro) {
		this.noResponsavelAcordoClaro = noResponsavelAcordoClaro;
	}

	public String getNoResponsavelAcordoLd() {
		return this.noResponsavelAcordoLd;
	}

	public void setNoResponsavelAcordoLd(String noResponsavelAcordoLd) {
		this.noResponsavelAcordoLd = noResponsavelAcordoLd;
	}

	public String getNoResponsavelCartaLd() {
		return this.noResponsavelCartaLd;
	}

	public void setNoResponsavelCartaLd(String noResponsavelCartaLd) {
		this.noResponsavelCartaLd = noResponsavelCartaLd;
	}

	public String getTxAvaliacaoJuridica() {
		return this.txAvaliacaoJuridica;
	}

	public void setTxAvaliacaoJuridica(String txAvaliacaoJuridica) {
		this.txAvaliacaoJuridica = txAvaliacaoJuridica;
	}

	public String getTxAvaliacaoOperacional() {
		return this.txAvaliacaoOperacional;
	}

	public void setTxAvaliacaoOperacional(String txAvaliacaoOperacional) {
		this.txAvaliacaoOperacional = txAvaliacaoOperacional;
	}

	public String getTxAvaliacaoRegulatoria() {
		return this.txAvaliacaoRegulatoria;
	}

	public void setTxAvaliacaoRegulatoria(String txAvaliacaoRegulatoria) {
		this.txAvaliacaoRegulatoria = txAvaliacaoRegulatoria;
	}

	public String getTxComentarioAcaoJudicial() {
		return this.txComentarioAcaoJudicial;
	}

	public void setTxComentarioAcaoJudicial(String txComentarioAcaoJudicial) {
		this.txComentarioAcaoJudicial = txComentarioAcaoJudicial;
	}

	public String getTxComentarioAnalise() {
		return this.txComentarioAnalise;
	}

	public void setTxComentarioAnalise(String txComentarioAnalise) {
		this.txComentarioAnalise = txComentarioAnalise;
	}

	public String getTxComentarioConciliacao() {
		return this.txComentarioConciliacao;
	}

	public void setTxComentarioConciliacao(String txComentarioConciliacao) {
		this.txComentarioConciliacao = txComentarioConciliacao;
	}

	public String getTxComentarioRaAnatel() {
		return this.txComentarioRaAnatel;
	}

	public void setTxComentarioRaAnatel(String txComentarioRaAnatel) {
		this.txComentarioRaAnatel = txComentarioRaAnatel;
	}

	public String getTxComentarioRepasse() {
		return this.txComentarioRepasse;
	}

	public void setTxComentarioRepasse(String txComentarioRepasse) {
		this.txComentarioRepasse = txComentarioRepasse;
	}

	public Double getVlAcertoConciliacaoClaro() {
		return this.vlAcertoConciliacaoClaro;
	}

	public void setVlAcertoConciliacaoClaro(Double vlAcertoConciliacaoClaro) {
		this.vlAcertoConciliacaoClaro = vlAcertoConciliacaoClaro;
	}

	public Double getVlAcertoConciliacaoLd() {
		return this.vlAcertoConciliacaoLd;
	}

	public void setVlAcertoConciliacaoLd(Double vlAcertoConciliacaoLd) {
		this.vlAcertoConciliacaoLd = vlAcertoConciliacaoLd;
	}

	public Double getVlAcordado() {
		return this.vlAcordado;
	}

	public void setVlAcordado(Double vlAcordado) {
		this.vlAcordado = vlAcordado;
	}

	public Double getVlDiferencaVlPleito() {
		return this.vlDiferencaVlPleito;
	}

	public void setVlDiferencaVlPleito(Double vlDiferencaVlPleito) {
		this.vlDiferencaVlPleito = vlDiferencaVlPleito;
	}

	public Double getVlDiferencaVlProposto() {
		return this.vlDiferencaVlProposto;
	}

	public void setVlDiferencaVlProposto(Double vlDiferencaVlProposto) {
		this.vlDiferencaVlProposto = vlDiferencaVlProposto;
	}

	public Double getVlPleito() {
		return this.vlPleito;
	}

	public void setVlPleito(Double vlPleito) {
		this.vlPleito = vlPleito;
	}

	public Double getVlPropostoCartaClaro() {
		return this.vlPropostoCartaClaro;
	}

	public void setVlPropostoCartaClaro(Double vlPropostoCartaClaro) {
		this.vlPropostoCartaClaro = vlPropostoCartaClaro;
	}

	public Double getVlProvisaoDisputa() {
		return this.vlProvisaoDisputa;
	}

	public void setVlProvisaoDisputa(Double vlProvisaoDisputa) {
		this.vlProvisaoDisputa = vlProvisaoDisputa;
	}

	public Set<SccDisputaDetalhePrePago> getSccDisputaDetalhePrePagos() {
		return this.sccDisputaDetalhePrePagos;
	}

	public void setSccDisputaDetalhePrePagos(
			Set<SccDisputaDetalhePrePago> sccDisputaDetalhePrePagos) {
		this.sccDisputaDetalhePrePagos = sccDisputaDetalhePrePagos;
	}

	public SccOperadora getSccOperadora() {
		return this.sccOperadora;
	}

	public void setSccOperadora(SccOperadora sccOperadora) {
		this.sccOperadora = sccOperadora;
	}

	public Date getDtInputCartaLdTra() {
		return dtInputCartaLdTra;
	}

	public void setDtInputCartaLdTra(Date dtInputCartaLdTra) {
		this.dtInputCartaLdTra = dtInputCartaLdTra;
	}

	
	

}
