package com.claro.cobillingweb.persistence.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.annotations.JoinColumnsOrFormulas;
import org.hibernate.annotations.JoinFormula;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "SCC_ASSINATURA_PREPAGO")
public class SccAssinaturaPrePago implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SccAssinaturaPrePagoPK id;
	private Long sqArquivo;
	private SccOperadora operadoraLD;
	private SccOperadora operadoraClaro;
	private String cdTipoEvento;
	private SccPreDominio statusAssinatura;
	private String nuTelefone;
	private Date dtInicioFranquia;
	private Integer hrInicioFranquia;
	private Double qtMinutosSaldoInicial;
	private Double qtMinutosAdquiridos;
	private BigDecimal vlBrutoPacote;
	private BigDecimal vlDescontoConcedido;
	private SccPacotePrepago pacotePrepago;
	private Date dtCarga;
	private SccPreFechamentoAssinatura preFechamentoAssinatura;
	private SccProdutoPrepago produtoPrepago;
	private Double qtTarifaFranquia;
	private Integer hrDuracaoReal;
	private Long qtdCdr;

	@EmbeddedId
	public SccAssinaturaPrePagoPK getId() {
		return id;
	}

	public void setId(SccAssinaturaPrePagoPK id) {
		this.id = id;
	}

	@Column(name = "SQ_ARQUIVO")
	public Long getSqArquivo() {
		return sqArquivo;
	}

	public void setSqArquivo(Long sqArquivo) {
		this.sqArquivo = sqArquivo;
	}

	@ManyToOne
	@JoinColumn(name = "CD_EOT_LD")
	public SccOperadora getOperadoraLD() {
		return operadoraLD;
	}

	public void setOperadoraLD(SccOperadora operadoraLD) {
		this.operadoraLD = operadoraLD;
	}

	@ManyToOne
	@JoinColumn(name = "CD_EOT_CLARO")
	public SccOperadora getOperadoraClaro() {
		return operadoraClaro;
	}

	public void setOperadoraClaro(SccOperadora operadoraClaro) {
		this.operadoraClaro = operadoraClaro;
	}

	@Column(name = "CD_TIPO_EVENTO")
	public String getCdTipoEvento() {
		return cdTipoEvento;
	}

	public void setCdTipoEvento(String cdTipoEvento) {
		this.cdTipoEvento = cdTipoEvento;
	}

	@ManyToOne
	@JoinColumnsOrFormulas(value = { @JoinColumnOrFormula(column = @JoinColumn(name = "CD_STATUS_ASSINATURA", referencedColumnName="CD_DOMINIO")),
			@JoinColumnOrFormula(formula = @JoinFormula(referencedColumnName = "TP_DOMINIO", value = "'STASS'")) })
	public SccPreDominio getStatusAssinatura() {
		return statusAssinatura;
	}

	public void setStatusAssinatura(SccPreDominio statusAssinatura) {
		this.statusAssinatura = statusAssinatura;
	}

	@Column(name = "NU_TELEFONE")
	public String getNuTelefone() {
		return nuTelefone;
	}

	public void setNuTelefone(String nuTelefone) {
		this.nuTelefone = nuTelefone;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DT_INICIO_FRANQUIA")
	public Date getDtInicioFranquia() {
		return dtInicioFranquia;
	}

	public void setDtInicioFranquia(Date dtInicioFranquia) {
		this.dtInicioFranquia = dtInicioFranquia;
	}

	@Column(name = "HR_INICIO_FRANQUIA")
	public Integer getHrInicioFranquia() {
		return hrInicioFranquia;
	}

	public void setHrInicioFranquia(Integer hrInicioFranquia) {
		this.hrInicioFranquia = hrInicioFranquia;
	}

	@Column(name = "QT_MINUTOS_SALDO_INICIAL")
	public Double getQtMinutosSaldoInicial() {
		return qtMinutosSaldoInicial;
	}

	public void setQtMinutosSaldoInicial(Double qtMinutosSaldoInicial) {
		this.qtMinutosSaldoInicial = qtMinutosSaldoInicial;
	}

	@Column(name = "QT_MINUTOS_ADQUIRIDOS")
	public Double getQtMinutosAdquiridos() {
		return qtMinutosAdquiridos;
	}

	public void setQtMinutosAdquiridos(Double qtMinutosAdquiridos) {
		this.qtMinutosAdquiridos = qtMinutosAdquiridos;
	}

	@Column(name = "VL_BRUTO_PACOTE")
	public BigDecimal getVlBrutoPacote() {
		return vlBrutoPacote;
	}

	public void setVlBrutoPacote(BigDecimal vlBrutoPacote) {
		this.vlBrutoPacote = vlBrutoPacote;
	}

	@Column(name = "VL_DESCONTO_CONCEDIDO")
	public BigDecimal getVlDescontoConcedido() {
		return vlDescontoConcedido;
	}

	public void setVlDescontoConcedido(BigDecimal vlDescontoConcedido) {
		this.vlDescontoConcedido = vlDescontoConcedido;
	}

	@ManyToOne
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name = "CD_PACOTE_PREPAGO")
	public SccPacotePrepago getPacotePrepago() {
		return pacotePrepago;
	}

	public void setPacotePrepago(SccPacotePrepago pacotePrepago) {
		this.pacotePrepago = pacotePrepago;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DT_CARGA")
	public Date getDtCarga() {
		return dtCarga;
	}

	public void setDtCarga(Date dtCarga) {
		this.dtCarga = dtCarga;
	}

	@ManyToOne
	@JoinColumn(name = "SQ_PRE_FECHAMENTO_ASSINATURA")
	public SccPreFechamentoAssinatura getPreFechamentoAssinatura() {
		return preFechamentoAssinatura;
	}

	public void setPreFechamentoAssinatura(SccPreFechamentoAssinatura preFechamentoAssinatura) {
		this.preFechamentoAssinatura = preFechamentoAssinatura;
	}

	@ManyToOne
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name = "CD_PRODUTO_PREPAGO")
	public SccProdutoPrepago getProdutoPrepago() {
		return produtoPrepago;
	}

	public void setProdutoPrepago(SccProdutoPrepago produtoPrepago) {
		this.produtoPrepago = produtoPrepago;
	}

	@Column(name = "QT_TARIF_FRANQUIA")
	public Double getQtTarifaFranquia() {
		return qtTarifaFranquia;
	}

	public void setQtTarifaFranquia(Double qtTarifaFranquia) {
		this.qtTarifaFranquia = qtTarifaFranquia;
	}

	@Column(name = "HR_DURACAO_REAL")
	public Integer getHrDuracaoReal() {
		return hrDuracaoReal;
	}

	public void setHrDuracaoReal(Integer hrDuracaoReal) {
		this.hrDuracaoReal = hrDuracaoReal;
	}

	@Column(name = "QTD_CDRS")
	public Long getQtdCdr() {
		return qtdCdr;
	}

	public void setQtdCdr(Long qtdCdr) {
		this.qtdCdr = qtdCdr;
	}

}
