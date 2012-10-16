package com.claro.cobillingweb.persistence.entity;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;


/**
 * The persistent class for the SCC_CDR_COBILLING database table.
 * 
 */
@NamedQueries({
	@NamedQuery(name="SccCdrCobilling.QUERY_BY_STATUS_AND_ARQUIVO" , query="SELECT t from SccCdrCobilling t where t.statusCdr.cdStatusCdr = :statusCdr AND t.sqArquivoRemessa = :seqArquivo")
		})
@Entity
@Table(name="SCC_CDR_COBILLING")
public class SccCdrCobilling implements Serializable {
	private static final long serialVersionUID = 1L;
	private SccCdrCobillingPK id;
	private Long aaCiclo;
	private String cdAreaRoaming;
	private String cdCharge;
	private Long cdCiclo;
	private Long cdComponenteProduto;
	private String cdCsp;
	private String cdDefeito;
	private String cdEotDestino;
	private String cdEotOrigem;
	private String cdEotRoaming;
	private String cdErroReciclagem;
	private String cdLocalidadeDestino;
	private String cdLocalidadeOrigem;
	private SccMotivoRejeicao cdMotivoRejeicao;
	private SccNaturezaCobranca cdNaturezaCobranca;
	private String cdPeriodoChamada;
	private SccStatusCdr statusCdr;
	private String cdSubStatusCdr;
	private String cdTipoChamada;
	private String cdsPaisDestino;
	private Date dtCarga;
	private Date dtEmissaoFatura;
	private Date dtEvento;
	private Date dtRepasse;
	private Date dtStatusCdr;
	private Date dtVencimentoFatura;
	private Long hrDuracaoReal;
	private String inChamadaRepassada;
	private String inDestinoChamada;
	private Double miDuracaoTarifada;
	private Long mmCiclo;
	private Long nuCdr;
	private String nuFatura;
	private Long nuNf;
	private Long nuReclamacao;
	private String nuSerieNf;
	private Long qtMinutosFranquia;
	private Long sqArquivoOriginal;
	private Long sqArquivoRemessa;
	private SccArquivoCobilling arquivoRetorno;
	private SccArquivoCobilling arquivoRemessa;
	private Long sqCredito;
	private Long sqRegistroArquivo;
	private Long vlAjuste;
	private Double vlBrutoChamada;
	private Long vlCofins;
	private Long vlFaturado;
	private Long vlFaturadoLiquido;
	private Long vlIcms;
	private Double vlLiquidoChamada;
	private Long vlPis;

    public SccCdrCobilling() {
    }


	@EmbeddedId
	public SccCdrCobillingPK getId() {
		return this.id;
	}

	public void setId(SccCdrCobillingPK id) {
		this.id = id;
	}
	

	@Column(name="AA_CICLO")
	public Long getAaCiclo() {
		return this.aaCiclo;
	}

	public void setAaCiclo(Long aaCiclo) {
		this.aaCiclo = aaCiclo;
	}


	@Column(name="CD_AREA_ROAMING")
	public String getCdAreaRoaming() {
		return this.cdAreaRoaming;
	}

	public void setCdAreaRoaming(String cdAreaRoaming) {
		this.cdAreaRoaming = cdAreaRoaming;
	}


	@Column(name="CD_CHARGE")
	public String getCdCharge() {
		return this.cdCharge;
	}

	public void setCdCharge(String cdCharge) {
		this.cdCharge = cdCharge;
	}


	@Column(name="CD_CICLO")
	public Long getCdCiclo() {
		return this.cdCiclo;
	}

	public void setCdCiclo(Long cdCiclo) {
		this.cdCiclo = cdCiclo;
	}


	@Column(name="CD_COMPONENTE_PRODUTO")
	public Long getCdComponenteProduto() {
		return this.cdComponenteProduto;
	}

	public void setCdComponenteProduto(Long cdComponenteProduto) {
		this.cdComponenteProduto = cdComponenteProduto;
	}


	@Column(name="CD_CSP")
	public String getCdCsp() {
		return this.cdCsp;
	}

	public void setCdCsp(String cdCsp) {
		this.cdCsp = cdCsp;
	}


	@Column(name="CD_DEFEITO")
	public String getCdDefeito() {
		return this.cdDefeito;
	}

	public void setCdDefeito(String cdDefeito) {
		this.cdDefeito = cdDefeito;
	}


	@Column(name="CD_EOT_DESTINO")
	public String getCdEotDestino() {
		return this.cdEotDestino;
	}

	public void setCdEotDestino(String cdEotDestino) {
		this.cdEotDestino = cdEotDestino;
	}


	@Column(name="CD_EOT_ORIGEM")
	public String getCdEotOrigem() {
		return this.cdEotOrigem;
	}

	public void setCdEotOrigem(String cdEotOrigem) {
		this.cdEotOrigem = cdEotOrigem;
	}


	@Column(name="CD_EOT_ROAMING")
	public String getCdEotRoaming() {
		return this.cdEotRoaming;
	}

	public void setCdEotRoaming(String cdEotRoaming) {
		this.cdEotRoaming = cdEotRoaming;
	}


	@Column(name="CD_ERRO_RECICLAGEM")
	public String getCdErroReciclagem() {
		return this.cdErroReciclagem;
	}

	public void setCdErroReciclagem(String cdErroReciclagem) {
		this.cdErroReciclagem = cdErroReciclagem;
	}


	@Column(name="CD_LOCALIDADE_DESTINO")
	public String getCdLocalidadeDestino() {
		return this.cdLocalidadeDestino;
	}

	public void setCdLocalidadeDestino(String cdLocalidadeDestino) {
		this.cdLocalidadeDestino = cdLocalidadeDestino;
	}


	@Column(name="CD_LOCALIDADE_ORIGEM")
	public String getCdLocalidadeOrigem() {
		return this.cdLocalidadeOrigem;
	}

	public void setCdLocalidadeOrigem(String cdLocalidadeOrigem) {
		this.cdLocalidadeOrigem = cdLocalidadeOrigem;
	}




	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CD_MOTIVO_REJEICAO")
	public SccMotivoRejeicao getCdMotivoRejeicao() {
		return cdMotivoRejeicao;
	}


	public void setCdMotivoRejeicao(SccMotivoRejeicao cdMotivoRejeicao) {
		this.cdMotivoRejeicao = cdMotivoRejeicao;
	}


	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CD_NATUREZA_COBRANCA")
	public SccNaturezaCobranca getCdNaturezaCobranca() {
		return cdNaturezaCobranca;
	}


	public void setCdNaturezaCobranca(SccNaturezaCobranca cdNaturezaCobranca) {
		this.cdNaturezaCobranca = cdNaturezaCobranca;
	}


	@Column(name="CD_PERIODO_CHAMADA")
	public String getCdPeriodoChamada() {
		return this.cdPeriodoChamada;
	}

	public void setCdPeriodoChamada(String cdPeriodoChamada) {
		this.cdPeriodoChamada = cdPeriodoChamada;
	}

	

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CD_STATUS_CDR")
	public SccStatusCdr getStatusCdr() {
		return statusCdr;
	}


	public void setStatusCdr(SccStatusCdr statusCdr) {
		this.statusCdr = statusCdr;
	}


	@Column(name="CD_SUB_STATUS_CDR")
	public String getCdSubStatusCdr() {
		return this.cdSubStatusCdr;
	}

	public void setCdSubStatusCdr(String cdSubStatusCdr) {
		this.cdSubStatusCdr = cdSubStatusCdr;
	}


	@Column(name="CD_TIPO_CHAMADA")
	public String getCdTipoChamada() {
		return this.cdTipoChamada;
	}

	public void setCdTipoChamada(String cdTipoChamada) {
		this.cdTipoChamada = cdTipoChamada;
	}


	@Column(name="CDS_PAIS_DESTINO")
	public String getCdsPaisDestino() {
		return this.cdsPaisDestino;
	}

	public void setCdsPaisDestino(String cdsPaisDestino) {
		this.cdsPaisDestino = cdsPaisDestino;
	}


    @Temporal( TemporalType.DATE)
	@Column(name="DT_CARGA")
	public Date getDtCarga() {
		return this.dtCarga;
	}

	public void setDtCarga(Date dtCarga) {
		this.dtCarga = dtCarga;
	}


    @Temporal( TemporalType.DATE)
	@Column(name="DT_EMISSAO_FATURA")
	public Date getDtEmissaoFatura() {
		return this.dtEmissaoFatura;
	}

	public void setDtEmissaoFatura(Date dtEmissaoFatura) {
		this.dtEmissaoFatura = dtEmissaoFatura;
	}


    @Temporal( TemporalType.DATE)
	@Column(name="DT_EVENTO")
	public Date getDtEvento() {
		return this.dtEvento;
	}

	public void setDtEvento(Date dtEvento) {
		this.dtEvento = dtEvento;
	}


    @Temporal( TemporalType.DATE)
	@Column(name="DT_REPASSE")
	public Date getDtRepasse() {
		return this.dtRepasse;
	}

	public void setDtRepasse(Date dtRepasse) {
		this.dtRepasse = dtRepasse;
	}


    @Temporal( TemporalType.DATE)
	@Column(name="DT_STATUS_CDR")
	public Date getDtStatusCdr() {
		return this.dtStatusCdr;
	}

	public void setDtStatusCdr(Date dtStatusCdr) {
		this.dtStatusCdr = dtStatusCdr;
	}


    @Temporal( TemporalType.DATE)
	@Column(name="DT_VENCIMENTO_FATURA")
	public Date getDtVencimentoFatura() {
		return this.dtVencimentoFatura;
	}

	public void setDtVencimentoFatura(Date dtVencimentoFatura) {
		this.dtVencimentoFatura = dtVencimentoFatura;
	}


	@Column(name="HR_DURACAO_REAL")
	public Long getHrDuracaoReal() {
		return this.hrDuracaoReal;
	}

	public void setHrDuracaoReal(Long hrDuracaoReal) {
		this.hrDuracaoReal = hrDuracaoReal;
	}


	@Column(name="IN_CHAMADA_REPASSADA")
	public String getInChamadaRepassada() {
		return this.inChamadaRepassada;
	}

	public void setInChamadaRepassada(String inChamadaRepassada) {
		this.inChamadaRepassada = inChamadaRepassada;
	}


	@Column(name="IN_DESTINO_CHAMADA")
	public String getInDestinoChamada() {
		return this.inDestinoChamada;
	}

	public void setInDestinoChamada(String inDestinoChamada) {
		this.inDestinoChamada = inDestinoChamada;
	}


	@Column(name="MI_DURACAO_TARIFADA")
	public Double getMiDuracaoTarifada() {
		return this.miDuracaoTarifada;
	}

	public void setMiDuracaoTarifada(Double miDuracaoTarifada) {
		this.miDuracaoTarifada = miDuracaoTarifada;
	}


	@Column(name="MM_CICLO")
	public Long getMmCiclo() {
		return this.mmCiclo;
	}

	public void setMmCiclo(Long mmCiclo) {
		this.mmCiclo = mmCiclo;
	}


	@Column(name="NU_CDR")
	public Long getNuCdr() {
		return this.nuCdr;
	}

	public void setNuCdr(Long nuCdr) {
		this.nuCdr = nuCdr;
	}


	@Column(name="NU_FATURA")
	public String getNuFatura() {
		return this.nuFatura;
	}

	public void setNuFatura(String nuFatura) {
		this.nuFatura = nuFatura;
	}


	@Column(name="NU_NF")
	public Long getNuNf() {
		return this.nuNf;
	}

	public void setNuNf(Long nuNf) {
		this.nuNf = nuNf;
	}


	@Column(name="NU_RECLAMACAO")
	public Long getNuReclamacao() {
		return this.nuReclamacao;
	}

	public void setNuReclamacao(Long nuReclamacao) {
		this.nuReclamacao = nuReclamacao;
	}


	@Column(name="NU_SERIE_NF")
	public String getNuSerieNf() {
		return this.nuSerieNf;
	}

	public void setNuSerieNf(String nuSerieNf) {
		this.nuSerieNf = nuSerieNf;
	}


	@Column(name="QT_MINUTOS_FRANQUIA")
	public Long getQtMinutosFranquia() {
		return this.qtMinutosFranquia;
	}

	public void setQtMinutosFranquia(Long qtMinutosFranquia) {
		this.qtMinutosFranquia = qtMinutosFranquia;
	}


	@Column(name="SQ_ARQUIVO_ORIGINAL")
	public Long getSqArquivoOriginal() {
		return this.sqArquivoOriginal;
	}

	public void setSqArquivoOriginal(Long sqArquivoOriginal) {
		this.sqArquivoOriginal = sqArquivoOriginal;
	}


	@Column(name="SQ_ARQUIVO_REMESSA")
	public Long getSqArquivoRemessa() {
		return this.sqArquivoRemessa;
	}

	public void setSqArquivoRemessa(Long sqArquivoRemessa) {
		this.sqArquivoRemessa = sqArquivoRemessa;
	}



	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="SQ_ARQUIVO_RETORNO")
	public SccArquivoCobilling getArquivoRetorno() {
		return arquivoRetorno;
	}


	public void setArquivoRetorno(SccArquivoCobilling arquivoRetorno) {
		this.arquivoRetorno = arquivoRetorno;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="SQ_ARQUIVO_REMESSA",updatable=false,insertable=false)
	public SccArquivoCobilling getArquivoRemessa() {
		return arquivoRemessa;
	}


	public void setArquivoRemessa(SccArquivoCobilling arquivoRemessa) {
		this.arquivoRemessa = arquivoRemessa;
	}


	@Column(name="SQ_CREDITO")
	public Long getSqCredito() {
		return this.sqCredito;
	}

	public void setSqCredito(Long sqCredito) {
		this.sqCredito = sqCredito;
	}


	@Column(name="SQ_REGISTRO_ARQUIVO")
	public Long getSqRegistroArquivo() {
		return this.sqRegistroArquivo;
	}

	public void setSqRegistroArquivo(Long sqRegistroArquivo) {
		this.sqRegistroArquivo = sqRegistroArquivo;
	}


	@Column(name="VL_AJUSTE")
	public Long getVlAjuste() {
		return this.vlAjuste;
	}

	public void setVlAjuste(Long vlAjuste) {
		this.vlAjuste = vlAjuste;
	}


	@Column(name="VL_BRUTO_CHAMADA")
	public Double getVlBrutoChamada() {
		return this.vlBrutoChamada;
	}

	public void setVlBrutoChamada(Double vlBrutoChamada) {
		this.vlBrutoChamada = vlBrutoChamada;
	}


	@Column(name="VL_COFINS")
	public Long getVlCofins() {
		return this.vlCofins;
	}

	public void setVlCofins(Long vlCofins) {
		this.vlCofins = vlCofins;
	}


	@Column(name="VL_FATURADO")
	public Long getVlFaturado() {
		return this.vlFaturado;
	}

	public void setVlFaturado(Long vlFaturado) {
		this.vlFaturado = vlFaturado;
	}


	@Column(name="VL_FATURADO_LIQUIDO")
	public Long getVlFaturadoLiquido() {
		return this.vlFaturadoLiquido;
	}

	public void setVlFaturadoLiquido(Long vlFaturadoLiquido) {
		this.vlFaturadoLiquido = vlFaturadoLiquido;
	}


	@Column(name="VL_ICMS")
	public Long getVlIcms() {
		return this.vlIcms;
	}

	public void setVlIcms(Long vlIcms) {
		this.vlIcms = vlIcms;
	}


	@Column(name="VL_LIQUIDO_CHAMADA")
	public Double getVlLiquidoChamada() {
		return this.vlLiquidoChamada;
	}

	public void setVlLiquidoChamada(Double vlLiquidoChamada) {
		this.vlLiquidoChamada = vlLiquidoChamada;
	}


	@Column(name="VL_PIS")
	public Long getVlPis() {
		return this.vlPis;
	}

	public void setVlPis(Long vlPis) {
		this.vlPis = vlPis;
	}

}