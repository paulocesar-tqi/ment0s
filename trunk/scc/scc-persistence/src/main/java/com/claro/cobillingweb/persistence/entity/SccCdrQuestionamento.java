/**
 * 
 */
package com.claro.cobillingweb.persistence.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author vagner.souza
 *
 */
@Entity
@Table(name="SCC_CDR_QUESTIONAMENTO")
@SequenceGenerator(name="SCC_CDR_QUESTIONAMENTO_SQCDRQUESTIONAMENTO_GENERATOR", sequenceName="SCC_CDR_QUESTIONAMENTO_SQCDRQUESTIONAMENTO_GENERATOR" )
public class SccCdrQuestionamento implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7109481100830536332L;
	

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SCC_CDR_QUESTIONAMENTO_SQCDRQUESTIONAMENTO_GENERATOR")
	@Column(name="SQ_CDR_QUESTIONAMENTO")
	private Long sqCdrQuestionamento;

	@Column(name="CD_AREA_VISITADA")
	private String cdAreaVisitada;

	@Column(name="CD_CONFIRMACAO_QUESTIONAMENTO")
	private String cdConfirmacaoQuestionamento;

	@Column(name="CD_EOT_CLARO")
	private String cdEotClaro;

	@Column(name="CD_EOT_LD")
	private String cdEotLd;

	@Column(name="CD_MOTIVO_EVENTO")
	private String cdMotivoEvento;

	@Column(name="CD_MOTIVO_REJEICAO")
	private String cdMotivoRejeicao;

	@Column(name="CD_NATUREZA_COBRANCA")
	private BigDecimal cdNaturezaCobranca;

	@Column(name="CD_PAIS_DESTINO")
	private String cdPaisDestino;

	@Column(name="CD_REFATURAMENTO")
	private String cdRefaturamento;

	@Column(name="CD_STATUS_ANALISE")
	private String cdStatusAnalise;

	@Column(name="CD_TIPO_CHAMADA")
	private String cdTipoChamada;

    @Temporal( TemporalType.DATE)
	@Column(name="DT_CARGA")
	private Date dtCarga;

    @Temporal( TemporalType.DATE)
	@Column(name="DT_CHAMADA")
	private Date dtChamada;

    @Temporal( TemporalType.DATE)
	@Column(name="DT_EVENTO")
	private Date dtEvento;

    @Temporal( TemporalType.DATE)
	@Column(name="DT_LIMITE_CONFIRMACAO")
	private Date dtLimiteConfirmacao;

    @Temporal( TemporalType.DATE)
	@Column(name="DT_LIMITE_QUESTIONAMENTO")
	private Date dtLimiteQuestionamento;

    @Temporal( TemporalType.DATE)
	@Column(name="DT_PRAZO_CONFIRMACAO")
	private Date dtPrazoConfirmacao;

    @Temporal( TemporalType.DATE)
	@Column(name="DT_PRAZO_QUESTIONAMENTO")
	private Date dtPrazoQuestionamento;

    @Temporal( TemporalType.DATE)
	@Column(name="DT_REFERENCIA")
	private Date dtReferencia;

    @Temporal( TemporalType.DATE)
	@Column(name="DT_RETORNO")
	private Date dtRetorno;

	@Column(name="HR_INICIO_CHAMADA")
	private Long hrInicioChamada;

	@Column(name="IN_DESTINO_CHAMADA")
	private String inDestinoChamada;

	@Column(name="IN_RESULTADO_ANALISE")
	private String inResultadoAnalise;

	@Column(name="IN_TIPO_REGISTRO")
	private String inTipoRegistro;

	@Column(name="MI_DURACAO_TARIFADA")
	private Double miDuracaoTarifada;

	@Column(name="NU_CDR")
	private Long nuCdr;

	@Column(name="NU_MSISDN_ORIGEM")
	private String nuMsisdnOrigem;

	@Column(name="NU_RECLAMACAO")
	private Integer nuReclamacao;

	@Column(name="NU_REGISTRO_ARQUIVO")
	private Long nuRegistroArquivo;

	@Column(name="NU_TELEFONE_DESTINO")
	private String nuTelefoneDestino;

	@Column(name="SQ_ARQUIVO_DUPLICIDADE")
	private BigDecimal sqArquivoDuplicidade;

	@Column(name="SQ_ARQUIVO_RETORNO")
	private BigDecimal sqArquivoRetorno;

	@Column(name="SQ_QUESTIONAMENTO")
	private Long sqQuestionamento;

	@Column(name="SQ_REMESSA_QUESTIONAMENTO")
	private Long sqRemessaQuestionamento;

	@Column(name="SQ_RETORNO_QUESTIONAMENTO")
	private Long sqRetornoQuestionamento;

	@Column(name="VL_ACERTO_INCLUSAO")
	private Double vlAcertoInclusao;

	@Column(name="VL_APURADO_PENALIDADE")
	private Double vlApuradoPenalidade;

	@Column(name="VL_BRUTO_CHAMADA")
	private Double vlBrutoChamada;

	@Column(name="VL_LIQUIDO_CHAMADA")
	private Double vlLiquidoChamada;

	@Column(name="VL_POTENCIAL_CLARO")
	private Double vlPotencialClaro;

	@Column(name="VL_POTENCIAL_LD")
	private Double vlPotencialLd;

	public Long getSqCdrQuestionamento() {
		return sqCdrQuestionamento;
	}

	public void setSqCdrQuestionamento(Long sqCdrQuestionamento) {
		this.sqCdrQuestionamento = sqCdrQuestionamento;
	}

	public String getCdAreaVisitada() {
		return cdAreaVisitada;
	}

	public void setCdAreaVisitada(String cdAreaVisitada) {
		this.cdAreaVisitada = cdAreaVisitada;
	}

	public String getCdConfirmacaoQuestionamento() {
		return cdConfirmacaoQuestionamento;
	}

	public void setCdConfirmacaoQuestionamento(String cdConfirmacaoQuestionamento) {
		this.cdConfirmacaoQuestionamento = cdConfirmacaoQuestionamento;
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

	public String getCdMotivoEvento() {
		return cdMotivoEvento;
	}

	public void setCdMotivoEvento(String cdMotivoEvento) {
		this.cdMotivoEvento = cdMotivoEvento;
	}

	public String getCdMotivoRejeicao() {
		return cdMotivoRejeicao;
	}

	public void setCdMotivoRejeicao(String cdMotivoRejeicao) {
		this.cdMotivoRejeicao = cdMotivoRejeicao;
	}

	public BigDecimal getCdNaturezaCobranca() {
		return cdNaturezaCobranca;
	}

	public void setCdNaturezaCobranca(BigDecimal cdNaturezaCobranca) {
		this.cdNaturezaCobranca = cdNaturezaCobranca;
	}

	public String getCdPaisDestino() {
		return cdPaisDestino;
	}

	public void setCdPaisDestino(String cdPaisDestino) {
		this.cdPaisDestino = cdPaisDestino;
	}

	public String getCdRefaturamento() {
		return cdRefaturamento;
	}

	public void setCdRefaturamento(String cdRefaturamento) {
		this.cdRefaturamento = cdRefaturamento;
	}

	public String getCdStatusAnalise() {
		return cdStatusAnalise;
	}

	public void setCdStatusAnalise(String cdStatusAnalise) {
		this.cdStatusAnalise = cdStatusAnalise;
	}

	public String getCdTipoChamada() {
		return cdTipoChamada;
	}

	public void setCdTipoChamada(String cdTipoChamada) {
		this.cdTipoChamada = cdTipoChamada;
	}

	public Date getDtCarga() {
		return dtCarga;
	}

	public void setDtCarga(Date dtCarga) {
		this.dtCarga = dtCarga;
	}

	public Date getDtChamada() {
		return dtChamada;
	}

	public void setDtChamada(Date dtChamada) {
		this.dtChamada = dtChamada;
	}

	public Date getDtEvento() {
		return dtEvento;
	}

	public void setDtEvento(Date dtEvento) {
		this.dtEvento = dtEvento;
	}

	public Date getDtLimiteConfirmacao() {
		return dtLimiteConfirmacao;
	}

	public void setDtLimiteConfirmacao(Date dtLimiteConfirmacao) {
		this.dtLimiteConfirmacao = dtLimiteConfirmacao;
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

	public Date getDtPrazoQuestionamento() {
		return dtPrazoQuestionamento;
	}

	public void setDtPrazoQuestionamento(Date dtPrazoQuestionamento) {
		this.dtPrazoQuestionamento = dtPrazoQuestionamento;
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

	public Long getHrInicioChamada() {
		return hrInicioChamada;
	}

	public void setHrInicioChamada(Long hrInicioChamada) {
		this.hrInicioChamada = hrInicioChamada;
	}

	public String getInDestinoChamada() {
		return inDestinoChamada;
	}

	public void setInDestinoChamada(String inDestinoChamada) {
		this.inDestinoChamada = inDestinoChamada;
	}

	public String getInResultadoAnalise() {
		return inResultadoAnalise;
	}

	public void setInResultadoAnalise(String inResultadoAnalise) {
		this.inResultadoAnalise = inResultadoAnalise;
	}

	public String getInTipoRegistro() {
		return inTipoRegistro;
	}

	public void setInTipoRegistro(String inTipoRegistro) {
		this.inTipoRegistro = inTipoRegistro;
	}

	public Double getMiDuracaoTarifada() {
		return miDuracaoTarifada;
	}

	public void setMiDuracaoTarifada(Double miDuracaoTarifada) {
		this.miDuracaoTarifada = miDuracaoTarifada;
	}

	public Long getNuCdr() {
		return nuCdr;
	}

	public void setNuCdr(Long nuCdr) {
		this.nuCdr = nuCdr;
	}

	public String getNuMsisdnOrigem() {
		return nuMsisdnOrigem;
	}

	public void setNuMsisdnOrigem(String nuMsisdnOrigem) {
		this.nuMsisdnOrigem = nuMsisdnOrigem;
	}

	public Integer getNuReclamacao() {
		return nuReclamacao;
	}

	public void setNuReclamacao(Integer nuReclamacao) {
		this.nuReclamacao = nuReclamacao;
	}

	public Long getNuRegistroArquivo() {
		return nuRegistroArquivo;
	}

	public void setNuRegistroArquivo(Long nuRegistroArquivo) {
		this.nuRegistroArquivo = nuRegistroArquivo;
	}

	public String getNuTelefoneDestino() {
		return nuTelefoneDestino;
	}

	public void setNuTelefoneDestino(String nuTelefoneDestino) {
		this.nuTelefoneDestino = nuTelefoneDestino;
	}

	public BigDecimal getSqArquivoDuplicidade() {
		return sqArquivoDuplicidade;
	}

	public void setSqArquivoDuplicidade(BigDecimal sqArquivoDuplicidade) {
		this.sqArquivoDuplicidade = sqArquivoDuplicidade;
	}

	public BigDecimal getSqArquivoRetorno() {
		return sqArquivoRetorno;
	}

	public void setSqArquivoRetorno(BigDecimal sqArquivoRetorno) {
		this.sqArquivoRetorno = sqArquivoRetorno;
	}

	public Long getSqQuestionamento() {
		return sqQuestionamento;
	}

	public void setSqQuestionamento(Long sqQuestionamento) {
		this.sqQuestionamento = sqQuestionamento;
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

	public Double getVlAcertoInclusao() {
		return vlAcertoInclusao;
	}

	public void setVlAcertoInclusao(Double vlAcertoInclusao) {
		this.vlAcertoInclusao = vlAcertoInclusao;
	}

	public Double getVlApuradoPenalidade() {
		return vlApuradoPenalidade;
	}

	public void setVlApuradoPenalidade(Double vlApuradoPenalidade) {
		this.vlApuradoPenalidade = vlApuradoPenalidade;
	}

	public Double getVlBrutoChamada() {
		return vlBrutoChamada;
	}

	public void setVlBrutoChamada(Double vlBrutoChamada) {
		this.vlBrutoChamada = vlBrutoChamada;
	}

	public Double getVlLiquidoChamada() {
		return vlLiquidoChamada;
	}

	public void setVlLiquidoChamada(Double vlLiquidoChamada) {
		this.vlLiquidoChamada = vlLiquidoChamada;
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

 
}
