/**
 * 
 */
package com.claro.cobillingweb.persistence.view;

import java.io.Serializable;
import java.util.Date;

/**
 * @author vagner.souza
 *
 */
public class SccQuestionamentoAnaliseChamadaView implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8642014841364934395L;
	
	private Long sqCdrQuestionamento;
	private Long sqQuestionamento;
	private String cdEotClaro;
	private String cdEotLd;
	private Long nuCrd;
	private Long sqRemessaQuestionamento;
	private Long sqRetornoQuestionamento;
	private Long nuRegistroArquivo;
	private String nuMsisdnOrigem;
	private Date dtChamada;
	private Long hrInicioChamada;
	private String nuTelefoneDestino;
	private Double miDuracaoTarifada;
	private Double vlLiquidoChamada;
	private Double vlBrutoChamada;
	private Integer nuReclamacao;
	private String cdMotivoRejeicao;
	private Date dtEvento;
	private Date dtReferencia;
	private Date dtRetorno;
	private Date dtPrazoQuestionamento;
	private Date dtLimiteQuestionamento;
	private Date dtPrazoConfirmacao;
	private Date dtLimiteConfirmacao;
	private Double vlPotencialClaro;
	private Double vlPotencialLd;
	private String cdConfirmacaoQuestionamento;
	private Double vlApuradoPenalidade;
	private Double vlAcertoInclusao;
	private Date dtCarga;
	private String inResultadoAnalise;
	private String inTipoRegsitro;
	
	
	public Long getSqCdrQuestionamento() {
		return sqCdrQuestionamento;
	}
	public void setSqCdrQuestionamento(Long sqCdrQuestionamento) {
		this.sqCdrQuestionamento = sqCdrQuestionamento;
	}
	public Long getSqQuestionamento() {
		return sqQuestionamento;
	}
	public void setSqQuestionamento(Long sqQuestionamento) {
		this.sqQuestionamento = sqQuestionamento;
	}
	public String getCdEotClaro() {
		return cdEotClaro;
	}
	public void setCdEotClaro(String cdEotClaro) {
		this.cdEotClaro = cdEotClaro;
	}
	public String getCdEotLd() {
		return cdEotLd;
	}
	public void setCdEotLd(String cdEotLd) {
		this.cdEotLd = cdEotLd;
	}
	public Long getNuCrd() {
		return nuCrd;
	}
	public void setNuCrd(Long nuCrd) {
		this.nuCrd = nuCrd;
	}
	public Long getSqRemessaQuestionamento() {
		return sqRemessaQuestionamento;
	}
	public void setSqRemessaQuestionamento(Long sqRemessaQuestionamento) {
		this.sqRemessaQuestionamento = sqRemessaQuestionamento;
	}
	public Long getSqRetornoQuestionamento() {
		return sqRetornoQuestionamento;
	}
	public void setSqRetornoQuestionamento(Long sqRetornoQuestionamento) {
		this.sqRetornoQuestionamento = sqRetornoQuestionamento;
	}
	public Long getNuRegistroArquivo() {
		return nuRegistroArquivo;
	}
	public void setNuRegistroArquivo(Long nuRegistroArquivo) {
		this.nuRegistroArquivo = nuRegistroArquivo;
	}

	public String getNuMsisdnOrigem() {
		return nuMsisdnOrigem;
	}
	public void setNuMsisdnOrigem(String nuMsisdnOrigem) {
		this.nuMsisdnOrigem = nuMsisdnOrigem;
	}
	public Date getDtChamada() {
		return dtChamada;
	}
	public void setDtChamada(Date dtChamada) {
		this.dtChamada = dtChamada;
	}
	public Long getHrInicioChamada() {
		return hrInicioChamada;
	}
	public void setHrInicioChamada(Long hrInicioChamada) {
		this.hrInicioChamada = hrInicioChamada;
	}
	public String getNuTelefoneDestino() {
		return nuTelefoneDestino;
	}
	public void setNuTelefoneDestino(String nuTelefoneDestino) {
		this.nuTelefoneDestino = nuTelefoneDestino;
	}
	public Double getMiDuracaoTarifada() {
		return miDuracaoTarifada;
	}
	public void setMiDuracaoTarifada(Double miDuracaoTarifada) {
		this.miDuracaoTarifada = miDuracaoTarifada;
	}
	public Double getVlLiquidoChamada() {
		return vlLiquidoChamada;
	}
	public void setVlLiquidoChamada(Double vlLiquidoChamada) {
		this.vlLiquidoChamada = vlLiquidoChamada;
	}
	public Double getVlBrutoChamada() {
		return vlBrutoChamada;
	}
	public void setVlBrutoChamada(Double vlBrutoChamada) {
		this.vlBrutoChamada = vlBrutoChamada;
	}
	public Integer getNuReclamacao() {
		return nuReclamacao;
	}
	public void setNuReclamacao(Integer nuReclamacao) {
		this.nuReclamacao = nuReclamacao;
	}
	public String getCdMotivoRejeicao() {
		return cdMotivoRejeicao;
	}
	public void setCdMotivoRejeicao(String cdMotivoRejeicao) {
		this.cdMotivoRejeicao = cdMotivoRejeicao;
	}
	public Date getDtEvento() {
		return dtEvento;
	}
	public void setDtEvento(Date dtEvento) {
		this.dtEvento = dtEvento;
	}
	public Date getDtReferencia() {
		return dtReferencia;
	}
	public void setDtReferencia(Date dtReferencia) {
		this.dtReferencia = dtReferencia;
	}
	public Date getDtRetorno() {
		return dtRetorno;
	}
	public void setDtRetorno(Date dtRetorno) {
		this.dtRetorno = dtRetorno;
	}
	public Date getDtPrazoQuestionamento() {
		return dtPrazoQuestionamento;
	}
	public void setDtPrazoQuestionamento(Date dtPrazoQuestionamento) {
		this.dtPrazoQuestionamento = dtPrazoQuestionamento;
	}
	public Date getDtLimiteQuestionamento() {
		return dtLimiteQuestionamento;
	}
	public void setDtLimiteQuestionamento(Date dtLimiteQuestionamento) {
		this.dtLimiteQuestionamento = dtLimiteQuestionamento;
	}
	public Date getDtPrazoConfirmacao() {
		return dtPrazoConfirmacao;
	}
	public void setDtPrazoConfirmacao(Date dtPrazoConfirmacao) {
		this.dtPrazoConfirmacao = dtPrazoConfirmacao;
	}
	public Date getDtLimiteConfirmacao() {
		return dtLimiteConfirmacao;
	}
	public void setDtLimiteConfirmacao(Date dtLimiteConfirmacao) {
		this.dtLimiteConfirmacao = dtLimiteConfirmacao;
	}
	public Double getVlPotencialClaro() {
		return vlPotencialClaro;
	}
	public void setVlPotencialClaro(Double vlPotencialClaro) {
		this.vlPotencialClaro = vlPotencialClaro;
	}
	public Double getVlPotencialLd() {
		return vlPotencialLd;
	}
	public void setVlPotencialLd(Double vlPotencialLd) {
		this.vlPotencialLd = vlPotencialLd;
	}
	public String getCdConfirmacaoQuestionamento() {
		return cdConfirmacaoQuestionamento;
	}
	public void setCdConfirmacaoQuestionamento(String cdConfirmacaoQuestionamento) {
		this.cdConfirmacaoQuestionamento = cdConfirmacaoQuestionamento;
	}
	public Double getVlApuradoPenalidade() {
		return vlApuradoPenalidade;
	}
	public void setVlApuradoPenalidade(Double vlApuradoPenalidade) {
		this.vlApuradoPenalidade = vlApuradoPenalidade;
	}
	public Double getVlAcertoInclusao() {
		return vlAcertoInclusao;
	}
	public void setVlAcertoInclusao(Double vlAcertoInclusao) {
		this.vlAcertoInclusao = vlAcertoInclusao;
	}
	public Date getDtCarga() {
		return dtCarga;
	}
	public void setDtCarga(Date dtCarga) {
		this.dtCarga = dtCarga;
	}
	public String getInResultadoAnalise() {
		return inResultadoAnalise;
	}
	public void setInResultadoAnalise(String inResultadoAnalise) {
		this.inResultadoAnalise = inResultadoAnalise;
	}
	public String getInTipoRegsitro() {
		return inTipoRegsitro;
	}
	public void setInTipoRegsitro(String inTipoRegsitro) {
		this.inTipoRegsitro = inTipoRegsitro;
	}
	

}
