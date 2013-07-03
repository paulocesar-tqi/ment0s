/**
 * 
 */
package com.claro.cobillingweb.persistence.view;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import com.claro.cobillingweb.persistence.entity.SccDisputaDetalhePrePago;

/**
 * @author vagner.souza
 *
 */
public class SccDisputaPrePagoView implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2096320651243968306L;

	private Long sqDisputaPrePago;

	private String cdAnalistaInputAcaoJudic;

	private String cdAnalistaInputAcordo;

	private String cdAnalistaInputCartaClaro;

	private String cdAnalistaInputCartaLd;

	private String cdAnalistaInputRa;

	private String cdAnalistaInputRepasse;

	private String cdIdentificacaoAtaAcordo;

	private String cdIdentificacaoCartaClaro;

	private String cdIdentificacaoCartaLd;

	private String cdStatusDisputa;

	private String cdTipoDisputa;

	private String dsPleito;

	private String dsRespostaCartaClaro;

	private Date dtAssinaturaAcordo;

	private Date dtDisputa;

	private Date dtInputAcordo;

	private Date dtInputCartaClaro;

	private Date dtInputCartaLd;

	private Date dtInputRa;

	private Date dtLiberacaoRepasse;

	private Date dtNotificacaoAcaoJudicial;

	private Date dtPrazoDisputa;

	private Date dtPrazoResposta;

	private Date dtPrevTerminoAcaoJudicial;

	private Date dtPrevTerminoRaAnatel;

	private Date dtProgramadaRepasse;

	private Date dtProtocoloCartaClaro;

	private Date dtProtocoloLd;

	private Date dtRaAnatel;

	private Date dtRepasseDisputa;

	private Date dtRepasseScc;

	private String fgAcaoJudicial;

	private String fgDisputaForaDoPrazo;

	private String fgDisputaSemResposta;

	private String fgRaAnatel;

	private String fgRepasseLiberado;

	private String inRiscoDisputa;

	private String noResponsavelAcordoClaro;

	private String noResponsavelAcordoLd;

	private String noResponsavelCartaLd;

	private String txAvaliacaoJuridica;

	private String txAvaliacaoOperacional;

	private String txAvaliacaoRegulatoria;

	private String txComentarioAcaoJudicial;

	private String txComentarioAnalise;

	private String txComentarioConciliacao;

	private String txComentarioRaAnatel;

	private String txComentarioRepasse;

	private Double vlAcertoConciliacaoClaro;

	private Double vlAcertoConciliacaoLd;

	private Double vlAcordado;

	private Double vlDiferencaVlPleito;

	private Double vlDiferencaVlProposto;

	private Double vlPleito;

	private Double vlPropostoCartaClaro;

	private Double vlProvisaoDisputa;

	private Set<SccDisputaDetalhePrePago> sccDisputaDetalhePrePagos;

	private String  cdEotLd;
	
	private Long qtdDetalhes;
	
	private Long qtdRepasses;

	public Long getSqDisputaPrePago() {
		return sqDisputaPrePago;
	}

	public void setSqDisputaPrePago(Long sqDisputaPrePago) {
		this.sqDisputaPrePago = sqDisputaPrePago;
	}

	public String getCdAnalistaInputAcaoJudic() {
		return cdAnalistaInputAcaoJudic;
	}

	public void setCdAnalistaInputAcaoJudic(String cdAnalistaInputAcaoJudic) {
		this.cdAnalistaInputAcaoJudic = cdAnalistaInputAcaoJudic;
	}

	public String getCdAnalistaInputAcordo() {
		return cdAnalistaInputAcordo;
	}

	public void setCdAnalistaInputAcordo(String cdAnalistaInputAcordo) {
		this.cdAnalistaInputAcordo = cdAnalistaInputAcordo;
	}

	public String getCdAnalistaInputCartaClaro() {
		return cdAnalistaInputCartaClaro;
	}

	public void setCdAnalistaInputCartaClaro(String cdAnalistaInputCartaClaro) {
		this.cdAnalistaInputCartaClaro = cdAnalistaInputCartaClaro;
	}

	public String getCdAnalistaInputCartaLd() {
		return cdAnalistaInputCartaLd;
	}

	public void setCdAnalistaInputCartaLd(String cdAnalistaInputCartaLd) {
		this.cdAnalistaInputCartaLd = cdAnalistaInputCartaLd;
	}

	public String getCdAnalistaInputRa() {
		return cdAnalistaInputRa;
	}

	public void setCdAnalistaInputRa(String cdAnalistaInputRa) {
		this.cdAnalistaInputRa = cdAnalistaInputRa;
	}

	public String getCdAnalistaInputRepasse() {
		return cdAnalistaInputRepasse;
	}

	public void setCdAnalistaInputRepasse(String cdAnalistaInputRepasse) {
		this.cdAnalistaInputRepasse = cdAnalistaInputRepasse;
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

	public String getCdStatusDisputa() {
		return cdStatusDisputa;
	}

	public void setCdStatusDisputa(String cdStatusDisputa) {
		this.cdStatusDisputa = cdStatusDisputa;
	}

	public String getCdTipoDisputa() {
		return cdTipoDisputa;
	}

	public void setCdTipoDisputa(String cdTipoDisputa) {
		this.cdTipoDisputa = cdTipoDisputa;
	}

	public String getDsPleito() {
		return dsPleito;
	}

	public void setDsPleito(String dsPleito) {
		this.dsPleito = dsPleito;
	}

	public String getDsRespostaCartaClaro() {
		return dsRespostaCartaClaro;
	}

	public void setDsRespostaCartaClaro(String dsRespostaCartaClaro) {
		this.dsRespostaCartaClaro = dsRespostaCartaClaro;
	}

	public Date getDtAssinaturaAcordo() {
		return dtAssinaturaAcordo;
	}

	public void setDtAssinaturaAcordo(Date dtAssinaturaAcordo) {
		this.dtAssinaturaAcordo = dtAssinaturaAcordo;
	}

	public Date getDtDisputa() {
		return dtDisputa;
	}

	public void setDtDisputa(Date dtDisputa) {
		this.dtDisputa = dtDisputa;
	}

	public Date getDtInputAcordo() {
		return dtInputAcordo;
	}

	public void setDtInputAcordo(Date dtInputAcordo) {
		this.dtInputAcordo = dtInputAcordo;
	}

	public Date getDtInputCartaClaro() {
		return dtInputCartaClaro;
	}

	public void setDtInputCartaClaro(Date dtInputCartaClaro) {
		this.dtInputCartaClaro = dtInputCartaClaro;
	}

	public Date getDtInputCartaLd() {
		return dtInputCartaLd;
	}

	public void setDtInputCartaLd(Date dtInputCartaLd) {
		this.dtInputCartaLd = dtInputCartaLd;
	}

	public Date getDtInputRa() {
		return dtInputRa;
	}

	public void setDtInputRa(Date dtInputRa) {
		this.dtInputRa = dtInputRa;
	}

	public Date getDtLiberacaoRepasse() {
		return dtLiberacaoRepasse;
	}

	public void setDtLiberacaoRepasse(Date dtLiberacaoRepasse) {
		this.dtLiberacaoRepasse = dtLiberacaoRepasse;
	}

	public Date getDtNotificacaoAcaoJudicial() {
		return dtNotificacaoAcaoJudicial;
	}

	public void setDtNotificacaoAcaoJudicial(Date dtNotificacaoAcaoJudicial) {
		this.dtNotificacaoAcaoJudicial = dtNotificacaoAcaoJudicial;
	}

	public Date getDtPrazoDisputa() {
		return dtPrazoDisputa;
	}

	public void setDtPrazoDisputa(Date dtPrazoDisputa) {
		this.dtPrazoDisputa = dtPrazoDisputa;
	}

	public Date getDtPrazoResposta() {
		return dtPrazoResposta;
	}

	public void setDtPrazoResposta(Date dtPrazoResposta) {
		this.dtPrazoResposta = dtPrazoResposta;
	}

	public Date getDtPrevTerminoAcaoJudicial() {
		return dtPrevTerminoAcaoJudicial;
	}

	public void setDtPrevTerminoAcaoJudicial(Date dtPrevTerminoAcaoJudicial) {
		this.dtPrevTerminoAcaoJudicial = dtPrevTerminoAcaoJudicial;
	}

	public Date getDtPrevTerminoRaAnatel() {
		return dtPrevTerminoRaAnatel;
	}

	public void setDtPrevTerminoRaAnatel(Date dtPrevTerminoRaAnatel) {
		this.dtPrevTerminoRaAnatel = dtPrevTerminoRaAnatel;
	}

	public Date getDtProgramadaRepasse() {
		return dtProgramadaRepasse;
	}

	public void setDtProgramadaRepasse(Date dtProgramadaRepasse) {
		this.dtProgramadaRepasse = dtProgramadaRepasse;
	}

	public Date getDtProtocoloCartaClaro() {
		return dtProtocoloCartaClaro;
	}

	public void setDtProtocoloCartaClaro(Date dtProtocoloCartaClaro) {
		this.dtProtocoloCartaClaro = dtProtocoloCartaClaro;
	}

	public Date getDtProtocoloLd() {
		return dtProtocoloLd;
	}

	public void setDtProtocoloLd(Date dtProtocoloLd) {
		this.dtProtocoloLd = dtProtocoloLd;
	}

	public Date getDtRaAnatel() {
		return dtRaAnatel;
	}

	public void setDtRaAnatel(Date dtRaAnatel) {
		this.dtRaAnatel = dtRaAnatel;
	}

	public Date getDtRepasseDisputa() {
		return dtRepasseDisputa;
	}

	public void setDtRepasseDisputa(Date dtRepasseDisputa) {
		this.dtRepasseDisputa = dtRepasseDisputa;
	}

	public Date getDtRepasseScc() {
		return dtRepasseScc;
	}

	public void setDtRepasseScc(Date dtRepasseScc) {
		this.dtRepasseScc = dtRepasseScc;
	}

	public String getFgAcaoJudicial() {
		return fgAcaoJudicial;
	}

	public void setFgAcaoJudicial(String fgAcaoJudicial) {
		this.fgAcaoJudicial = fgAcaoJudicial;
	}

	public String getFgDisputaForaDoPrazo() {
		return fgDisputaForaDoPrazo;
	}

	public void setFgDisputaForaDoPrazo(String fgDisputaForaDoPrazo) {
		this.fgDisputaForaDoPrazo = fgDisputaForaDoPrazo;
	}

	public String getFgDisputaSemResposta() {
		return fgDisputaSemResposta;
	}

	public void setFgDisputaSemResposta(String fgDisputaSemResposta) {
		this.fgDisputaSemResposta = fgDisputaSemResposta;
	}

	public String getFgRaAnatel() {
		return fgRaAnatel;
	}

	public void setFgRaAnatel(String fgRaAnatel) {
		this.fgRaAnatel = fgRaAnatel;
	}

	public String getFgRepasseLiberado() {
		return fgRepasseLiberado;
	}

	public void setFgRepasseLiberado(String fgRepasseLiberado) {
		this.fgRepasseLiberado = fgRepasseLiberado;
	}

	public String getInRiscoDisputa() {
		return inRiscoDisputa;
	}

	public void setInRiscoDisputa(String inRiscoDisputa) {
		this.inRiscoDisputa = inRiscoDisputa;
	}

	public String getNoResponsavelAcordoClaro() {
		return noResponsavelAcordoClaro;
	}

	public void setNoResponsavelAcordoClaro(String noResponsavelAcordoClaro) {
		this.noResponsavelAcordoClaro = noResponsavelAcordoClaro;
	}

	public String getNoResponsavelAcordoLd() {
		return noResponsavelAcordoLd;
	}

	public void setNoResponsavelAcordoLd(String noResponsavelAcordoLd) {
		this.noResponsavelAcordoLd = noResponsavelAcordoLd;
	}

	public String getNoResponsavelCartaLd() {
		return noResponsavelCartaLd;
	}

	public void setNoResponsavelCartaLd(String noResponsavelCartaLd) {
		this.noResponsavelCartaLd = noResponsavelCartaLd;
	}

	public String getTxAvaliacaoJuridica() {
		return txAvaliacaoJuridica;
	}

	public void setTxAvaliacaoJuridica(String txAvaliacaoJuridica) {
		this.txAvaliacaoJuridica = txAvaliacaoJuridica;
	}

	public String getTxAvaliacaoOperacional() {
		return txAvaliacaoOperacional;
	}

	public void setTxAvaliacaoOperacional(String txAvaliacaoOperacional) {
		this.txAvaliacaoOperacional = txAvaliacaoOperacional;
	}

	public String getTxAvaliacaoRegulatoria() {
		return txAvaliacaoRegulatoria;
	}

	public void setTxAvaliacaoRegulatoria(String txAvaliacaoRegulatoria) {
		this.txAvaliacaoRegulatoria = txAvaliacaoRegulatoria;
	}

	public String getTxComentarioAcaoJudicial() {
		return txComentarioAcaoJudicial;
	}

	public void setTxComentarioAcaoJudicial(String txComentarioAcaoJudicial) {
		this.txComentarioAcaoJudicial = txComentarioAcaoJudicial;
	}

	public String getTxComentarioAnalise() {
		return txComentarioAnalise;
	}

	public void setTxComentarioAnalise(String txComentarioAnalise) {
		this.txComentarioAnalise = txComentarioAnalise;
	}

	public String getTxComentarioConciliacao() {
		return txComentarioConciliacao;
	}

	public void setTxComentarioConciliacao(String txComentarioConciliacao) {
		this.txComentarioConciliacao = txComentarioConciliacao;
	}

	public String getTxComentarioRaAnatel() {
		return txComentarioRaAnatel;
	}

	public void setTxComentarioRaAnatel(String txComentarioRaAnatel) {
		this.txComentarioRaAnatel = txComentarioRaAnatel;
	}

	public String getTxComentarioRepasse() {
		return txComentarioRepasse;
	}

	public void setTxComentarioRepasse(String txComentarioRepasse) {
		this.txComentarioRepasse = txComentarioRepasse;
	}

	public Double getVlAcertoConciliacaoClaro() {
		return vlAcertoConciliacaoClaro;
	}

	public void setVlAcertoConciliacaoClaro(Double vlAcertoConciliacaoClaro) {
		this.vlAcertoConciliacaoClaro = vlAcertoConciliacaoClaro;
	}

	public Double getVlAcertoConciliacaoLd() {
		return vlAcertoConciliacaoLd;
	}

	public void setVlAcertoConciliacaoLd(Double vlAcertoConciliacaoLd) {
		this.vlAcertoConciliacaoLd = vlAcertoConciliacaoLd;
	}

	public Double getVlAcordado() {
		return vlAcordado;
	}

	public void setVlAcordado(Double vlAcordado) {
		this.vlAcordado = vlAcordado;
	}

	public Double getVlDiferencaVlPleito() {
		return vlDiferencaVlPleito;
	}

	public void setVlDiferencaVlPleito(Double vlDiferencaVlPleito) {
		this.vlDiferencaVlPleito = vlDiferencaVlPleito;
	}

	public Double getVlDiferencaVlProposto() {
		return vlDiferencaVlProposto;
	}

	public void setVlDiferencaVlProposto(Double vlDiferencaVlProposto) {
		this.vlDiferencaVlProposto = vlDiferencaVlProposto;
	}

	public Double getVlPleito() {
		return vlPleito;
	}

	public void setVlPleito(Double vlPleito) {
		this.vlPleito = vlPleito;
	}

	public Double getVlPropostoCartaClaro() {
		return vlPropostoCartaClaro;
	}

	public void setVlPropostoCartaClaro(Double vlPropostoCartaClaro) {
		this.vlPropostoCartaClaro = vlPropostoCartaClaro;
	}

	public Double getVlProvisaoDisputa() {
		return vlProvisaoDisputa;
	}

	public void setVlProvisaoDisputa(Double vlProvisaoDisputa) {
		this.vlProvisaoDisputa = vlProvisaoDisputa;
	}

	public Set<SccDisputaDetalhePrePago> getSccDisputaDetalhePrePagos() {
		return sccDisputaDetalhePrePagos;
	}

	public void setSccDisputaDetalhePrePagos(
			Set<SccDisputaDetalhePrePago> sccDisputaDetalhePrePagos) {
		this.sccDisputaDetalhePrePagos = sccDisputaDetalhePrePagos;
	}

	public String getCdEotLd() {
		return cdEotLd;
	}

	public void setCdEotLd(String cdEotLd) {
		this.cdEotLd = cdEotLd;
	}

	public Long getQtdDetalhes() {
		return qtdDetalhes;
	}

	public void setQtdDetalhes(Long qtdDetalhes) {
		this.qtdDetalhes = qtdDetalhes;
	}

	public Long getQtdRepasses() {
		return qtdRepasses;
	}

	public void setQtdRepasses(Long qtdRepasses) {
		this.qtdRepasses = qtdRepasses;
	}
	
}
