package com.claro.cobillingweb.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the SCC_CONTESTACAO_DETALHE_PRE database table.
 * 
 */
@Entity
@Table(name="SCC_CONTESTACAO_DETALHE_PRE")
public class SccContestacaoDetalhePre implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SccContestacaoDetalhePrePK id;

	@Column(name="CD_ANALISTA_INPUT_REPASSE")
	private String cdAnalistaInputRepasse;

    @Temporal( TemporalType.DATE)
	@Column(name="DT_LIBERACAO_REPASSE")
	private Date dtLiberacaoRepasse;

	@Column(name="PC_CALC_CHAMADAS_COM_DEFEITO")
	private BigDecimal pcCalcChamadasComDefeito;

	@Column(name="PC_CALC_TOTAL_PERDA")
	private BigDecimal pcCalcTotalPerda;

	@Column(name="PC_P1_CALC_PERDA_ERRO_TARIFA")
	private BigDecimal pcP1CalcPerdaErroTarifa;

	@Column(name="PC_P2_CALC_PERDA_NAO_REG_PLAT")
	private BigDecimal pcP2CalcPerdaNaoRegPlat;

	@Column(name="PC_P3_CALC_PERDA_DEFEITO")
	private BigDecimal pcP3CalcPerdaDefeito;

	@Column(name="PC_P4_CALC_PERDA_OUT_DEFEITO")
	private BigDecimal pcP4CalcPerdaOutDefeito;

	@Column(name="PC_PERDA_CONTRATUAL")
	private BigDecimal pcPerdaContratual;

	@Column(name="PC_RATEIO")
	private BigDecimal pcRateio;

	@Column(name="PC_X1_CALC_PERDA_FRAUDE")
	private BigDecimal pcX1CalcPerdaFraude;

	@Column(name="QT_CALC_CHAMADAS_COM_DEFEITO")
	private BigDecimal qtCalcChamadasComDefeito;

	@Column(name="QT_CALC_CHAMADAS_CONCILIADAS")
	private BigDecimal qtCalcChamadasConciliadas;

	@Column(name="QT_CALC_CHAMADAS_PERDIDAS")
	private BigDecimal qtCalcChamadasPerdidas;

	@Column(name="QT_CHAMADAS_ARQ_BATIMENTO")
	private BigDecimal qtChamadasArqBatimento;

	@Column(name="QT_CHAMADAS_ARQUIVO_LD")
	private BigDecimal qtChamadasArquivoLd;

	@Column(name="QT_CHAMADAS_REPASSE")
	private BigDecimal qtChamadasRepasse;

	@Column(name="QT_MINUTOS_ARRED_ARQ_BATIMENTO")
	private BigDecimal qtMinutosArredArqBatimento;

	@Column(name="QT_MINUTOS_ARRED_ARQUIVO_LD")
	private BigDecimal qtMinutosArredArquivoLd;

	@Column(name="QT_MINUTOS_CONCILIADOS")
	private BigDecimal qtMinutosConciliados;

	@Column(name="QT_MINUTOS_PERDIDOS")
	private BigDecimal qtMinutosPerdidos;

	@Column(name="QT_MINUTOS_REAIS_ARQ_BATIMENTO")
	private BigDecimal qtMinutosReaisArqBatimento;

	@Column(name="QT_MINUTOS_REAIS_ARQUIVO_LD")
	private BigDecimal qtMinutosReaisArquivoLd;

	@Column(name="QT_MINUTOS_REPASSE")
	private BigDecimal qtMinutosRepasse;

	@Column(name="QT_P1_CHAMADAS_ERRO_TARIFA")
	private BigDecimal qtP1ChamadasErroTarifa;

	@Column(name="QT_P1_MIN_CHAMADAS_ERRO_TARIFA")
	private BigDecimal qtP1MinChamadasErroTarifa;

	@Column(name="QT_P2_CHAMADAS_NAO_RG_PLAT")
	private BigDecimal qtP2ChamadasNaoRgPlat;

	@Column(name="QT_P2_MIN_CHAMADAS_NAO_RG_PLAT")
	private BigDecimal qtP2MinChamadasNaoRgPlat;

	@Column(name="QT_P3_CHAMADAS_DEFEITO")
	private BigDecimal qtP3ChamadasDefeito;

	@Column(name="QT_P3_MIN_CHAMADAS_DEFEITO")
	private BigDecimal qtP3MinChamadasDefeito;

	@Column(name="QT_P4_CHAMADAS_OUT_DEFEITO")
	private BigDecimal qtP4ChamadasOutDefeito;

	@Column(name="QT_P4_MIN_CHAMADAS_OUT_DEFEITO")
	private BigDecimal qtP4MinChamadasOutDefeito;

	@Column(name="QT_X1_CHAMADAS_FRAUDE")
	private BigDecimal qtX1ChamadasFraude;

	@Column(name="QT_X1_MIN_CHAMADAS_FRAUDE")
	private BigDecimal qtX1MinChamadasFraude;

	@Column(name="SQ_PRE_FECHAMENTO")
	private BigDecimal sqPreFechamento;

	@Column(name="VL_ACERTO_CONCILIACAO_CLARO")
	private BigDecimal vlAcertoConciliacaoClaro;

	@Column(name="VL_ACERTO_CONCILIACAO_LD")
	private BigDecimal vlAcertoConciliacaoLd;

	@Column(name="VL_ACORDADO")
	private BigDecimal vlAcordado;

	@Column(name="VL_APURADO_REPASSE")
	private BigDecimal vlApuradoRepasse;

	@Column(name="VL_BRUTO_ARQ_BATIMENTO")
	private BigDecimal vlBrutoArqBatimento;

	@Column(name="VL_BRUTO_ARQUIVO_LD")
	private BigDecimal vlBrutoArquivoLd;

	@Column(name="VL_BRUTO_REPASSE")
	private BigDecimal vlBrutoRepasse;

	@Column(name="VL_CALC_CHAMADAS_COM_DEFEITO")
	private BigDecimal vlCalcChamadasComDefeito;

	@Column(name="VL_CALC_CHAMADAS_CONCILIADAS")
	private BigDecimal vlCalcChamadasConciliadas;

	@Column(name="VL_CALC_CHAMADAS_PERDIDAS")
	private BigDecimal vlCalcChamadasPerdidas;

	@Column(name="VL_CALC_TOTAL_PERDA")
	private BigDecimal vlCalcTotalPerda;

	@Column(name="VL_CORRECAO_MONETARIA")
	private BigDecimal vlCorrecaoMonetaria;

	@Column(name="VL_DIFERENCA_VL_DEFEITO")
	private BigDecimal vlDiferencaVlDefeito;

	@Column(name="VL_DIFERENCA_VL_PLEITO")
	private BigDecimal vlDiferencaVlPleito;

	@Column(name="VL_DIFERENCA_VL_PROPOSTO")
	private BigDecimal vlDiferencaVlProposto;

	@Column(name="VL_P1_CALC_PERDA_ERRO_TARIFA")
	private BigDecimal vlP1CalcPerdaErroTarifa;

	@Column(name="VL_P1_CHAMADAS_ERRO_TARIFA")
	private BigDecimal vlP1ChamadasErroTarifa;

	@Column(name="VL_P1_CHAMADAS_RETARIFADAS")
	private BigDecimal vlP1ChamadasRetarifadas;

	@Column(name="VL_P2_CALC_PERDA_NAO_REG_PLAT")
	private BigDecimal vlP2CalcPerdaNaoRegPlat;

	@Column(name="VL_P2_CHAMADAS_NAO_RG_PLAT")
	private BigDecimal vlP2ChamadasNaoRgPlat;

	@Column(name="VL_P2_PENALIDADE_NAO_REG_PLAT")
	private BigDecimal vlP2PenalidadeNaoRegPlat;

	@Column(name="VL_P3_CALC_PERDA_DEFEITO")
	private BigDecimal vlP3CalcPerdaDefeito;

	@Column(name="VL_P3_CHAMADAS_DEFEITO")
	private BigDecimal vlP3ChamadasDefeito;

	@Column(name="VL_P3_PENALIDADE_DEFEITO")
	private BigDecimal vlP3PenalidadeDefeito;

	@Column(name="VL_P4_CALC_PERDA_OUT_DEFEITO")
	private BigDecimal vlP4CalcPerdaOutDefeito;

	@Column(name="VL_P4_CHAMADAS_OUT_DEFEITO")
	private BigDecimal vlP4ChamadasOutDefeito;

	@Column(name="VL_P4_PENALIDADE_OUT_DEFEITO")
	private BigDecimal vlP4PenalidadeOutDefeito;

	@Column(name="VL_PENALIDADE_MIN_PERD_CLARO")
	private BigDecimal vlPenalidadeMinPerdClaro;

	@Column(name="VL_PLEITO")
	private BigDecimal vlPleito;

	@Column(name="VL_PROPOSTO")
	private BigDecimal vlProposto;

	@Column(name="VL_PROVISAO_CONTESTACAO")
	private BigDecimal vlProvisaoContestacao;

	@Column(name="VL_X1_CALC_PERDA_FRAUDE")
	private BigDecimal vlX1CalcPerdaFraude;

	@Column(name="VL_X1_CHAMADAS_FRAUDE")
	private BigDecimal vlX1ChamadasFraude;

	@Column(name="VL_X1_PENALIDADE_FRAUDE")
	private BigDecimal vlX1PenalidadeFraude;

	//bi-directional many-to-one association to SccContestacaoPrePago
    @ManyToOne
	@JoinColumn(name="SQ_CONTESTACAO_PRE_PAGO", insertable=false, updatable=false)
	private SccContestacaoPrePago sccContestacaoPrePago;

	//bi-directional many-to-one association to SccOperadora
    @ManyToOne
	@JoinColumn(name="CD_EOT_CLARO", insertable=false, updatable=false)
	private SccOperadora sccOperadora;

    public SccContestacaoDetalhePre() {
    }

	public SccContestacaoDetalhePrePK getId() {
		return this.id;
	}

	public void setId(SccContestacaoDetalhePrePK id) {
		this.id = id;
	}
	
	public String getCdAnalistaInputRepasse() {
		return this.cdAnalistaInputRepasse;
	}

	public void setCdAnalistaInputRepasse(String cdAnalistaInputRepasse) {
		this.cdAnalistaInputRepasse = cdAnalistaInputRepasse;
	}

	public Date getDtLiberacaoRepasse() {
		return this.dtLiberacaoRepasse;
	}

	public void setDtLiberacaoRepasse(Date dtLiberacaoRepasse) {
		this.dtLiberacaoRepasse = dtLiberacaoRepasse;
	}

	public BigDecimal getPcCalcChamadasComDefeito() {
		return this.pcCalcChamadasComDefeito;
	}

	public void setPcCalcChamadasComDefeito(BigDecimal pcCalcChamadasComDefeito) {
		this.pcCalcChamadasComDefeito = pcCalcChamadasComDefeito;
	}

	public BigDecimal getPcCalcTotalPerda() {
		return this.pcCalcTotalPerda;
	}

	public void setPcCalcTotalPerda(BigDecimal pcCalcTotalPerda) {
		this.pcCalcTotalPerda = pcCalcTotalPerda;
	}

	public BigDecimal getPcP1CalcPerdaErroTarifa() {
		return this.pcP1CalcPerdaErroTarifa;
	}

	public void setPcP1CalcPerdaErroTarifa(BigDecimal pcP1CalcPerdaErroTarifa) {
		this.pcP1CalcPerdaErroTarifa = pcP1CalcPerdaErroTarifa;
	}

	public BigDecimal getPcP2CalcPerdaNaoRegPlat() {
		return this.pcP2CalcPerdaNaoRegPlat;
	}

	public void setPcP2CalcPerdaNaoRegPlat(BigDecimal pcP2CalcPerdaNaoRegPlat) {
		this.pcP2CalcPerdaNaoRegPlat = pcP2CalcPerdaNaoRegPlat;
	}

	public BigDecimal getPcP3CalcPerdaDefeito() {
		return this.pcP3CalcPerdaDefeito;
	}

	public void setPcP3CalcPerdaDefeito(BigDecimal pcP3CalcPerdaDefeito) {
		this.pcP3CalcPerdaDefeito = pcP3CalcPerdaDefeito;
	}

	public BigDecimal getPcP4CalcPerdaOutDefeito() {
		return this.pcP4CalcPerdaOutDefeito;
	}

	public void setPcP4CalcPerdaOutDefeito(BigDecimal pcP4CalcPerdaOutDefeito) {
		this.pcP4CalcPerdaOutDefeito = pcP4CalcPerdaOutDefeito;
	}

	public BigDecimal getPcPerdaContratual() {
		return this.pcPerdaContratual;
	}

	public void setPcPerdaContratual(BigDecimal pcPerdaContratual) {
		this.pcPerdaContratual = pcPerdaContratual;
	}

	public BigDecimal getPcRateio() {
		return this.pcRateio;
	}

	public void setPcRateio(BigDecimal pcRateio) {
		this.pcRateio = pcRateio;
	}

	public BigDecimal getPcX1CalcPerdaFraude() {
		return this.pcX1CalcPerdaFraude;
	}

	public void setPcX1CalcPerdaFraude(BigDecimal pcX1CalcPerdaFraude) {
		this.pcX1CalcPerdaFraude = pcX1CalcPerdaFraude;
	}

	public BigDecimal getQtCalcChamadasComDefeito() {
		return this.qtCalcChamadasComDefeito;
	}

	public void setQtCalcChamadasComDefeito(BigDecimal qtCalcChamadasComDefeito) {
		this.qtCalcChamadasComDefeito = qtCalcChamadasComDefeito;
	}

	public BigDecimal getQtCalcChamadasConciliadas() {
		return this.qtCalcChamadasConciliadas;
	}

	public void setQtCalcChamadasConciliadas(BigDecimal qtCalcChamadasConciliadas) {
		this.qtCalcChamadasConciliadas = qtCalcChamadasConciliadas;
	}

	public BigDecimal getQtCalcChamadasPerdidas() {
		return this.qtCalcChamadasPerdidas;
	}

	public void setQtCalcChamadasPerdidas(BigDecimal qtCalcChamadasPerdidas) {
		this.qtCalcChamadasPerdidas = qtCalcChamadasPerdidas;
	}

	public BigDecimal getQtChamadasArqBatimento() {
		return this.qtChamadasArqBatimento;
	}

	public void setQtChamadasArqBatimento(BigDecimal qtChamadasArqBatimento) {
		this.qtChamadasArqBatimento = qtChamadasArqBatimento;
	}

	public BigDecimal getQtChamadasArquivoLd() {
		return this.qtChamadasArquivoLd;
	}

	public void setQtChamadasArquivoLd(BigDecimal qtChamadasArquivoLd) {
		this.qtChamadasArquivoLd = qtChamadasArquivoLd;
	}

	public BigDecimal getQtChamadasRepasse() {
		return this.qtChamadasRepasse;
	}

	public void setQtChamadasRepasse(BigDecimal qtChamadasRepasse) {
		this.qtChamadasRepasse = qtChamadasRepasse;
	}

	public BigDecimal getQtMinutosArredArqBatimento() {
		return this.qtMinutosArredArqBatimento;
	}

	public void setQtMinutosArredArqBatimento(BigDecimal qtMinutosArredArqBatimento) {
		this.qtMinutosArredArqBatimento = qtMinutosArredArqBatimento;
	}

	public BigDecimal getQtMinutosArredArquivoLd() {
		return this.qtMinutosArredArquivoLd;
	}

	public void setQtMinutosArredArquivoLd(BigDecimal qtMinutosArredArquivoLd) {
		this.qtMinutosArredArquivoLd = qtMinutosArredArquivoLd;
	}

	public BigDecimal getQtMinutosConciliados() {
		return this.qtMinutosConciliados;
	}

	public void setQtMinutosConciliados(BigDecimal qtMinutosConciliados) {
		this.qtMinutosConciliados = qtMinutosConciliados;
	}

	public BigDecimal getQtMinutosPerdidos() {
		return this.qtMinutosPerdidos;
	}

	public void setQtMinutosPerdidos(BigDecimal qtMinutosPerdidos) {
		this.qtMinutosPerdidos = qtMinutosPerdidos;
	}

	public BigDecimal getQtMinutosReaisArqBatimento() {
		return this.qtMinutosReaisArqBatimento;
	}

	public void setQtMinutosReaisArqBatimento(BigDecimal qtMinutosReaisArqBatimento) {
		this.qtMinutosReaisArqBatimento = qtMinutosReaisArqBatimento;
	}

	public BigDecimal getQtMinutosReaisArquivoLd() {
		return this.qtMinutosReaisArquivoLd;
	}

	public void setQtMinutosReaisArquivoLd(BigDecimal qtMinutosReaisArquivoLd) {
		this.qtMinutosReaisArquivoLd = qtMinutosReaisArquivoLd;
	}

	public BigDecimal getQtMinutosRepasse() {
		return this.qtMinutosRepasse;
	}

	public void setQtMinutosRepasse(BigDecimal qtMinutosRepasse) {
		this.qtMinutosRepasse = qtMinutosRepasse;
	}

	public BigDecimal getQtP1ChamadasErroTarifa() {
		return this.qtP1ChamadasErroTarifa;
	}

	public void setQtP1ChamadasErroTarifa(BigDecimal qtP1ChamadasErroTarifa) {
		this.qtP1ChamadasErroTarifa = qtP1ChamadasErroTarifa;
	}

	public BigDecimal getQtP1MinChamadasErroTarifa() {
		return this.qtP1MinChamadasErroTarifa;
	}

	public void setQtP1MinChamadasErroTarifa(BigDecimal qtP1MinChamadasErroTarifa) {
		this.qtP1MinChamadasErroTarifa = qtP1MinChamadasErroTarifa;
	}

	public BigDecimal getQtP2ChamadasNaoRgPlat() {
		return this.qtP2ChamadasNaoRgPlat;
	}

	public void setQtP2ChamadasNaoRgPlat(BigDecimal qtP2ChamadasNaoRgPlat) {
		this.qtP2ChamadasNaoRgPlat = qtP2ChamadasNaoRgPlat;
	}

	public BigDecimal getQtP2MinChamadasNaoRgPlat() {
		return this.qtP2MinChamadasNaoRgPlat;
	}

	public void setQtP2MinChamadasNaoRgPlat(BigDecimal qtP2MinChamadasNaoRgPlat) {
		this.qtP2MinChamadasNaoRgPlat = qtP2MinChamadasNaoRgPlat;
	}

	public BigDecimal getQtP3ChamadasDefeito() {
		return this.qtP3ChamadasDefeito;
	}

	public void setQtP3ChamadasDefeito(BigDecimal qtP3ChamadasDefeito) {
		this.qtP3ChamadasDefeito = qtP3ChamadasDefeito;
	}

	public BigDecimal getQtP3MinChamadasDefeito() {
		return this.qtP3MinChamadasDefeito;
	}

	public void setQtP3MinChamadasDefeito(BigDecimal qtP3MinChamadasDefeito) {
		this.qtP3MinChamadasDefeito = qtP3MinChamadasDefeito;
	}

	public BigDecimal getQtP4ChamadasOutDefeito() {
		return this.qtP4ChamadasOutDefeito;
	}

	public void setQtP4ChamadasOutDefeito(BigDecimal qtP4ChamadasOutDefeito) {
		this.qtP4ChamadasOutDefeito = qtP4ChamadasOutDefeito;
	}

	public BigDecimal getQtP4MinChamadasOutDefeito() {
		return this.qtP4MinChamadasOutDefeito;
	}

	public void setQtP4MinChamadasOutDefeito(BigDecimal qtP4MinChamadasOutDefeito) {
		this.qtP4MinChamadasOutDefeito = qtP4MinChamadasOutDefeito;
	}

	public BigDecimal getQtX1ChamadasFraude() {
		return this.qtX1ChamadasFraude;
	}

	public void setQtX1ChamadasFraude(BigDecimal qtX1ChamadasFraude) {
		this.qtX1ChamadasFraude = qtX1ChamadasFraude;
	}

	public BigDecimal getQtX1MinChamadasFraude() {
		return this.qtX1MinChamadasFraude;
	}

	public void setQtX1MinChamadasFraude(BigDecimal qtX1MinChamadasFraude) {
		this.qtX1MinChamadasFraude = qtX1MinChamadasFraude;
	}

	public BigDecimal getSqPreFechamento() {
		return this.sqPreFechamento;
	}

	public void setSqPreFechamento(BigDecimal sqPreFechamento) {
		this.sqPreFechamento = sqPreFechamento;
	}

	public BigDecimal getVlAcertoConciliacaoClaro() {
		return this.vlAcertoConciliacaoClaro;
	}

	public void setVlAcertoConciliacaoClaro(BigDecimal vlAcertoConciliacaoClaro) {
		this.vlAcertoConciliacaoClaro = vlAcertoConciliacaoClaro;
	}

	public BigDecimal getVlAcertoConciliacaoLd() {
		return this.vlAcertoConciliacaoLd;
	}

	public void setVlAcertoConciliacaoLd(BigDecimal vlAcertoConciliacaoLd) {
		this.vlAcertoConciliacaoLd = vlAcertoConciliacaoLd;
	}

	public BigDecimal getVlAcordado() {
		return this.vlAcordado;
	}

	public void setVlAcordado(BigDecimal vlAcordado) {
		this.vlAcordado = vlAcordado;
	}

	public BigDecimal getVlApuradoRepasse() {
		return this.vlApuradoRepasse;
	}

	public void setVlApuradoRepasse(BigDecimal vlApuradoRepasse) {
		this.vlApuradoRepasse = vlApuradoRepasse;
	}

	public BigDecimal getVlBrutoArqBatimento() {
		return this.vlBrutoArqBatimento;
	}

	public void setVlBrutoArqBatimento(BigDecimal vlBrutoArqBatimento) {
		this.vlBrutoArqBatimento = vlBrutoArqBatimento;
	}

	public BigDecimal getVlBrutoArquivoLd() {
		return this.vlBrutoArquivoLd;
	}

	public void setVlBrutoArquivoLd(BigDecimal vlBrutoArquivoLd) {
		this.vlBrutoArquivoLd = vlBrutoArquivoLd;
	}

	public BigDecimal getVlBrutoRepasse() {
		return this.vlBrutoRepasse;
	}

	public void setVlBrutoRepasse(BigDecimal vlBrutoRepasse) {
		this.vlBrutoRepasse = vlBrutoRepasse;
	}

	public BigDecimal getVlCalcChamadasComDefeito() {
		return this.vlCalcChamadasComDefeito;
	}

	public void setVlCalcChamadasComDefeito(BigDecimal vlCalcChamadasComDefeito) {
		this.vlCalcChamadasComDefeito = vlCalcChamadasComDefeito;
	}

	public BigDecimal getVlCalcChamadasConciliadas() {
		return this.vlCalcChamadasConciliadas;
	}

	public void setVlCalcChamadasConciliadas(BigDecimal vlCalcChamadasConciliadas) {
		this.vlCalcChamadasConciliadas = vlCalcChamadasConciliadas;
	}

	public BigDecimal getVlCalcChamadasPerdidas() {
		return this.vlCalcChamadasPerdidas;
	}

	public void setVlCalcChamadasPerdidas(BigDecimal vlCalcChamadasPerdidas) {
		this.vlCalcChamadasPerdidas = vlCalcChamadasPerdidas;
	}

	public BigDecimal getVlCalcTotalPerda() {
		return this.vlCalcTotalPerda;
	}

	public void setVlCalcTotalPerda(BigDecimal vlCalcTotalPerda) {
		this.vlCalcTotalPerda = vlCalcTotalPerda;
	}

	public BigDecimal getVlCorrecaoMonetaria() {
		return this.vlCorrecaoMonetaria;
	}

	public void setVlCorrecaoMonetaria(BigDecimal vlCorrecaoMonetaria) {
		this.vlCorrecaoMonetaria = vlCorrecaoMonetaria;
	}

	public BigDecimal getVlDiferencaVlDefeito() {
		return this.vlDiferencaVlDefeito;
	}

	public void setVlDiferencaVlDefeito(BigDecimal vlDiferencaVlDefeito) {
		this.vlDiferencaVlDefeito = vlDiferencaVlDefeito;
	}

	public BigDecimal getVlDiferencaVlPleito() {
		return this.vlDiferencaVlPleito;
	}

	public void setVlDiferencaVlPleito(BigDecimal vlDiferencaVlPleito) {
		this.vlDiferencaVlPleito = vlDiferencaVlPleito;
	}

	public BigDecimal getVlDiferencaVlProposto() {
		return this.vlDiferencaVlProposto;
	}

	public void setVlDiferencaVlProposto(BigDecimal vlDiferencaVlProposto) {
		this.vlDiferencaVlProposto = vlDiferencaVlProposto;
	}

	public BigDecimal getVlP1CalcPerdaErroTarifa() {
		return this.vlP1CalcPerdaErroTarifa;
	}

	public void setVlP1CalcPerdaErroTarifa(BigDecimal vlP1CalcPerdaErroTarifa) {
		this.vlP1CalcPerdaErroTarifa = vlP1CalcPerdaErroTarifa;
	}

	public BigDecimal getVlP1ChamadasErroTarifa() {
		return this.vlP1ChamadasErroTarifa;
	}

	public void setVlP1ChamadasErroTarifa(BigDecimal vlP1ChamadasErroTarifa) {
		this.vlP1ChamadasErroTarifa = vlP1ChamadasErroTarifa;
	}

	public BigDecimal getVlP1ChamadasRetarifadas() {
		return this.vlP1ChamadasRetarifadas;
	}

	public void setVlP1ChamadasRetarifadas(BigDecimal vlP1ChamadasRetarifadas) {
		this.vlP1ChamadasRetarifadas = vlP1ChamadasRetarifadas;
	}

	public BigDecimal getVlP2CalcPerdaNaoRegPlat() {
		return this.vlP2CalcPerdaNaoRegPlat;
	}

	public void setVlP2CalcPerdaNaoRegPlat(BigDecimal vlP2CalcPerdaNaoRegPlat) {
		this.vlP2CalcPerdaNaoRegPlat = vlP2CalcPerdaNaoRegPlat;
	}

	public BigDecimal getVlP2ChamadasNaoRgPlat() {
		return this.vlP2ChamadasNaoRgPlat;
	}

	public void setVlP2ChamadasNaoRgPlat(BigDecimal vlP2ChamadasNaoRgPlat) {
		this.vlP2ChamadasNaoRgPlat = vlP2ChamadasNaoRgPlat;
	}

	public BigDecimal getVlP2PenalidadeNaoRegPlat() {
		return this.vlP2PenalidadeNaoRegPlat;
	}

	public void setVlP2PenalidadeNaoRegPlat(BigDecimal vlP2PenalidadeNaoRegPlat) {
		this.vlP2PenalidadeNaoRegPlat = vlP2PenalidadeNaoRegPlat;
	}

	public BigDecimal getVlP3CalcPerdaDefeito() {
		return this.vlP3CalcPerdaDefeito;
	}

	public void setVlP3CalcPerdaDefeito(BigDecimal vlP3CalcPerdaDefeito) {
		this.vlP3CalcPerdaDefeito = vlP3CalcPerdaDefeito;
	}

	public BigDecimal getVlP3ChamadasDefeito() {
		return this.vlP3ChamadasDefeito;
	}

	public void setVlP3ChamadasDefeito(BigDecimal vlP3ChamadasDefeito) {
		this.vlP3ChamadasDefeito = vlP3ChamadasDefeito;
	}

	public BigDecimal getVlP3PenalidadeDefeito() {
		return this.vlP3PenalidadeDefeito;
	}

	public void setVlP3PenalidadeDefeito(BigDecimal vlP3PenalidadeDefeito) {
		this.vlP3PenalidadeDefeito = vlP3PenalidadeDefeito;
	}

	public BigDecimal getVlP4CalcPerdaOutDefeito() {
		return this.vlP4CalcPerdaOutDefeito;
	}

	public void setVlP4CalcPerdaOutDefeito(BigDecimal vlP4CalcPerdaOutDefeito) {
		this.vlP4CalcPerdaOutDefeito = vlP4CalcPerdaOutDefeito;
	}

	public BigDecimal getVlP4ChamadasOutDefeito() {
		return this.vlP4ChamadasOutDefeito;
	}

	public void setVlP4ChamadasOutDefeito(BigDecimal vlP4ChamadasOutDefeito) {
		this.vlP4ChamadasOutDefeito = vlP4ChamadasOutDefeito;
	}

	public BigDecimal getVlP4PenalidadeOutDefeito() {
		return this.vlP4PenalidadeOutDefeito;
	}

	public void setVlP4PenalidadeOutDefeito(BigDecimal vlP4PenalidadeOutDefeito) {
		this.vlP4PenalidadeOutDefeito = vlP4PenalidadeOutDefeito;
	}

	public BigDecimal getVlPenalidadeMinPerdClaro() {
		return this.vlPenalidadeMinPerdClaro;
	}

	public void setVlPenalidadeMinPerdClaro(BigDecimal vlPenalidadeMinPerdClaro) {
		this.vlPenalidadeMinPerdClaro = vlPenalidadeMinPerdClaro;
	}

	public BigDecimal getVlPleito() {
		return this.vlPleito;
	}

	public void setVlPleito(BigDecimal vlPleito) {
		this.vlPleito = vlPleito;
	}

	public BigDecimal getVlProposto() {
		return this.vlProposto;
	}

	public void setVlProposto(BigDecimal vlProposto) {
		this.vlProposto = vlProposto;
	}

	public BigDecimal getVlProvisaoContestacao() {
		return this.vlProvisaoContestacao;
	}

	public void setVlProvisaoContestacao(BigDecimal vlProvisaoContestacao) {
		this.vlProvisaoContestacao = vlProvisaoContestacao;
	}

	public BigDecimal getVlX1CalcPerdaFraude() {
		return this.vlX1CalcPerdaFraude;
	}

	public void setVlX1CalcPerdaFraude(BigDecimal vlX1CalcPerdaFraude) {
		this.vlX1CalcPerdaFraude = vlX1CalcPerdaFraude;
	}

	public BigDecimal getVlX1ChamadasFraude() {
		return this.vlX1ChamadasFraude;
	}

	public void setVlX1ChamadasFraude(BigDecimal vlX1ChamadasFraude) {
		this.vlX1ChamadasFraude = vlX1ChamadasFraude;
	}

	public BigDecimal getVlX1PenalidadeFraude() {
		return this.vlX1PenalidadeFraude;
	}

	public void setVlX1PenalidadeFraude(BigDecimal vlX1PenalidadeFraude) {
		this.vlX1PenalidadeFraude = vlX1PenalidadeFraude;
	}

	public SccContestacaoPrePago getSccContestacaoPrePago() {
		return this.sccContestacaoPrePago;
	}

	public void setSccContestacaoPrePago(SccContestacaoPrePago sccContestacaoPrePago) {
		this.sccContestacaoPrePago = sccContestacaoPrePago;
	}
	
	public SccOperadora getSccOperadora() {
		return this.sccOperadora;
	}

	public void setSccOperadora(SccOperadora sccOperadora) {
		this.sccOperadora = sccOperadora;
	}
	
}