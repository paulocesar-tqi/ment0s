package com.claro.cobillingweb.persistence.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="SCC_PAGAMENTO_REPASSE")
public class SccPagamentoRepasse {

	private static final long serialVersionUID = 1L;
	private SccPagamentoRepassePK id;
	private String cdDocumentoSap;
	private String cdFormaPagamento;
	private String cdStatusPagamento;
	private String cdUsuarioManut;
	private Date dtAtualizacao;
	private Date dtBasePagamento;
	private Date dtCriacao;
	private Date dtEmissaoNf;
	private Date dtEntregaRepasse;
	private Date dtPagamentoRepasse;
	private Date dtPagamentoSap;
	private Date dtVencimentoNf;
	private String fgContabAutomatica;
	private Long nuNf;
	private Long qtDiasAtraso;
	private String sgUf;
	private Long sqArquivoRemessaSap;
	private Long sqArquivoRetornoSap;
	private String txObservacao;
	private Double vlBrutoNf;
	private Double vlPagamentoRepasse;
	private Double vlPagamentoSap;
	private Double vlRepasse;

    public SccPagamentoRepasse() {
    }


	@EmbeddedId
	public SccPagamentoRepassePK getId() {
		return this.id;
	}

	public void setId(SccPagamentoRepassePK id) {
		this.id = id;
	}
	

	@Column(name="CD_DOCUMENTO_SAP")
	public String getCdDocumentoSap() {
		return this.cdDocumentoSap;
	}

	public void setCdDocumentoSap(String cdDocumentoSap) {
		this.cdDocumentoSap = cdDocumentoSap;
	}


	@Column(name="CD_FORMA_PAGAMENTO")
	public String getCdFormaPagamento() {
		return this.cdFormaPagamento;
	}

	public void setCdFormaPagamento(String cdFormaPagamento) {
		this.cdFormaPagamento = cdFormaPagamento;
	}


	@Column(name="CD_STATUS_PAGAMENTO")
	public String getCdStatusPagamento() {
		return this.cdStatusPagamento;
	}

	public void setCdStatusPagamento(String cdStatusPagamento) {
		this.cdStatusPagamento = cdStatusPagamento;
	}


	@Column(name="CD_USUARIO_MANUT")
	public String getCdUsuarioManut() {
		return this.cdUsuarioManut;
	}

	public void setCdUsuarioManut(String cdUsuarioManut) {
		this.cdUsuarioManut = cdUsuarioManut;
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
	@Column(name="DT_BASE_PAGAMENTO")
	public Date getDtBasePagamento() {
		return this.dtBasePagamento;
	}

	public void setDtBasePagamento(Date dtBasePagamento) {
		this.dtBasePagamento = dtBasePagamento;
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
	@Column(name="DT_EMISSAO_NF")
	public Date getDtEmissaoNf() {
		return this.dtEmissaoNf;
	}

	public void setDtEmissaoNf(Date dtEmissaoNf) {
		this.dtEmissaoNf = dtEmissaoNf;
	}


    @Temporal( TemporalType.DATE)
	@Column(name="DT_ENTREGA_REPASSE")
	public Date getDtEntregaRepasse() {
		return this.dtEntregaRepasse;
	}

	public void setDtEntregaRepasse(Date dtEntregaRepasse) {
		this.dtEntregaRepasse = dtEntregaRepasse;
	}


    @Temporal( TemporalType.DATE)
	@Column(name="DT_PAGAMENTO_REPASSE")
	public Date getDtPagamentoRepasse() {
		return this.dtPagamentoRepasse;
	}

	public void setDtPagamentoRepasse(Date dtPagamentoRepasse) {
		this.dtPagamentoRepasse = dtPagamentoRepasse;
	}


    @Temporal( TemporalType.DATE)
	@Column(name="DT_PAGAMENTO_SAP")
	public Date getDtPagamentoSap() {
		return this.dtPagamentoSap;
	}

	public void setDtPagamentoSap(Date dtPagamentoSap) {
		this.dtPagamentoSap = dtPagamentoSap;
	}


    @Temporal( TemporalType.DATE)
	@Column(name="DT_VENCIMENTO_NF")
	public Date getDtVencimentoNf() {
		return this.dtVencimentoNf;
	}

	public void setDtVencimentoNf(Date dtVencimentoNf) {
		this.dtVencimentoNf = dtVencimentoNf;
	}


	@Column(name="FG_CONTAB_AUTOMATICA")
	public String getFgContabAutomatica() {
		return this.fgContabAutomatica;
	}

	public void setFgContabAutomatica(String fgContabAutomatica) {
		this.fgContabAutomatica = fgContabAutomatica;
	}


	@Column(name="NU_NF")
	public Long getNuNf() {
		return this.nuNf;
	}

	public void setNuNf(Long nuNf) {
		this.nuNf = nuNf;
	}


	@Column(name="QT_DIAS_ATRASO")
	public Long getQtDiasAtraso() {
		return this.qtDiasAtraso;
	}

	public void setQtDiasAtraso(Long qtDiasAtraso) {
		this.qtDiasAtraso = qtDiasAtraso;
	}


	@Column(name="SG_UF")
	public String getSgUf() {
		return this.sgUf;
	}

	public void setSgUf(String sgUf) {
		this.sgUf = sgUf;
	}


	@Column(name="SQ_ARQUIVO_REMESSA_SAP")
	public Long getSqArquivoRemessaSap() {
		return this.sqArquivoRemessaSap;
	}

	public void setSqArquivoRemessaSap(Long sqArquivoRemessaSap) {
		this.sqArquivoRemessaSap = sqArquivoRemessaSap;
	}


	@Column(name="SQ_ARQUIVO_RETORNO_SAP")
	public Long getSqArquivoRetornoSap() {
		return this.sqArquivoRetornoSap;
	}

	public void setSqArquivoRetornoSap(Long sqArquivoRetornoSap) {
		this.sqArquivoRetornoSap = sqArquivoRetornoSap;
	}


	@Column(name="TX_OBSERVACAO")
	public String getTxObservacao() {
		return this.txObservacao;
	}

	public void setTxObservacao(String txObservacao) {
		this.txObservacao = txObservacao;
	}


	@Column(name="VL_BRUTO_NF")
	public Double getVlBrutoNf() {
		return this.vlBrutoNf;
	}

	public void setVlBrutoNf(Double vlBrutoNf) {
		this.vlBrutoNf = vlBrutoNf;
	}


	@Column(name="VL_PAGAMENTO_REPASSE")
	public Double getVlPagamentoRepasse() {
		return this.vlPagamentoRepasse;
	}

	public void setVlPagamentoRepasse(Double vlPagamentoRepasse) {
		this.vlPagamentoRepasse = vlPagamentoRepasse;
	}


	@Column(name="VL_PAGAMENTO_SAP")
	public Double getVlPagamentoSap() {
		return this.vlPagamentoSap;
	}

	public void setVlPagamentoSap(Double vlPagamentoSap) {
		this.vlPagamentoSap = vlPagamentoSap;
	}


	@Column(name="VL_REPASSE")
	public Double getVlRepasse() {
		return this.vlRepasse;
	}

	public void setVlRepasse(Double vlRepasse) {
		this.vlRepasse = vlRepasse;
	}
}
