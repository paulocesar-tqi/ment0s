package com.claro.cobillingweb.persistence.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;


/**
 * The persistent class for the SCC_ARQUIVO_COBILLING database table.
 * 
 */
@Entity
@Table(name="SCC_ARQUIVO_COBILLING")
public class SccArquivoCobilling implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long sqArquivo;
	private Integer aaCiclo;
	private Integer cdCiclo;
	private String cdErroProtocolo;
	private String cdProcesso;
	private SccStatusArquivo cdStatusArquivo;
	private Date dtCarga;
	private Date dtConnect;
	private Date dtDsn;
	private Date dtFimTrafego;
	private Date dtInicioTrafego;
	private Date dtProcBilling;
	private Date dtProcClaro;
	private Date dtProcExterna;
	private Date dtStatusArquivo;
	private Integer mmCiclo;
	private String noArquivo;
	private String noArquivoIfc;
	private String noArquivoTrans;
	private String noDiretorioArquivo;
	private Integer nuArquivoIfc;
	private Integer qtDuracaoReal;
	private Integer qtDuracaoTarifada;
	private Integer qtItensNf;
	private Integer qtNfs;
	private Integer qtProdutoNf;
	private Integer qtRegistros;
	private String sgUf;	
	private Long sqArquivoOrigem; 
	private Double vlBaseCalcIcms;
	private Double vlBaseCalcIcmsImpugnado;
	private Double vlBrutoArquivo;
	private Double vlCofins;
	private Double vlIcms;
	private Double vlIcmsImpugnado;
	private Double vlLiquidoArquivo;
	private Double vlPis;
	private Double vlTotalImpugnado;
	private Double vlTotalNf;	
	private SccTipoArquivo tipoArquivo;
	
	private SccOperadora operadoraClaro;
	private SccOperadora operadoraExterna;
	private SccArquivoCobilling arquivoOrigem;
	
    public SccArquivoCobilling() {
    }
    
	@Id
	@Column(name="SQ_ARQUIVO",insertable=false,updatable=false)
	public Long getSqArquivo() {
		return this.sqArquivo;
	}
	
	public void setSqArquivo(Long sqArquivo) {
		this.sqArquivo = sqArquivo;
	}
	
	@Column(name="AA_CICLO")
	public Integer getAaCiclo() {
		return this.aaCiclo;
	}
	
	public void setAaCiclo(Integer aaCiclo) {
		this.aaCiclo = aaCiclo;
	}
	
	@Column(name="CD_CICLO")
	public Integer getCdCiclo() {
		return this.cdCiclo;
	}
	
	public void setCdCiclo(Integer cdCiclo) {
		this.cdCiclo = cdCiclo;
	}
	
	@Column(name="CD_ERRO_PROTOCOLO")
	public String getCdErroProtocolo() {
		return this.cdErroProtocolo;
	}

	public void setCdErroProtocolo(String cdErroProtocolo) {
		this.cdErroProtocolo = cdErroProtocolo;
	}


	@Column(name="CD_PROCESSO")
	public String getCdProcesso() {
		return this.cdProcesso;
	}

	public void setCdProcesso(String cdProcesso) {
		this.cdProcesso = cdProcesso;
	}


	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="CD_STATUS_ARQUIVO")
	public SccStatusArquivo getCdStatusArquivo() {
		return this.cdStatusArquivo;
	}

	public void setCdStatusArquivo(SccStatusArquivo cdStatusArquivo) {
		this.cdStatusArquivo = cdStatusArquivo;
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
	@Column(name="DT_CONNECT")
	public Date getDtConnect() {
		return this.dtConnect;
	}

	public void setDtConnect(Date dtConnect) {
		this.dtConnect = dtConnect;
	}


    @Temporal( TemporalType.DATE)
	@Column(name="DT_DSN")
	public Date getDtDsn() {
		return this.dtDsn;
	}

	public void setDtDsn(Date dtDsn) {
		this.dtDsn = dtDsn;
	}


    @Temporal( TemporalType.DATE)
	@Column(name="DT_FIM_TRAFEGO")
	public Date getDtFimTrafego() {
		return this.dtFimTrafego;
	}

	public void setDtFimTrafego(Date dtFimTrafego) {
		this.dtFimTrafego = dtFimTrafego;
	}


    @Temporal( TemporalType.DATE)
	@Column(name="DT_INICIO_TRAFEGO")
	public Date getDtInicioTrafego() {
		return this.dtInicioTrafego;
	}

	public void setDtInicioTrafego(Date dtInicioTrafego) {
		this.dtInicioTrafego = dtInicioTrafego;
	}


    @Temporal( TemporalType.DATE)
	@Column(name="DT_PROC_BILLING")
	public Date getDtProcBilling() {
		return this.dtProcBilling;
	}

	public void setDtProcBilling(Date dtProcBilling) {
		this.dtProcBilling = dtProcBilling;
	}


    @Temporal( TemporalType.DATE)
	@Column(name="DT_PROC_CLARO")
	public Date getDtProcClaro() {
		return this.dtProcClaro;
	}

	public void setDtProcClaro(Date dtProcClaro) {
		this.dtProcClaro = dtProcClaro;
	}


    
	@Column(name="DT_PROC_EXTERNA")
	public Date getDtProcExterna() {
		return this.dtProcExterna;
	}

	public void setDtProcExterna(Date dtProcExterna) {
		this.dtProcExterna = dtProcExterna;
	}


    @Temporal( TemporalType.DATE)
	@Column(name="DT_STATUS_ARQUIVO")
	public Date getDtStatusArquivo() {
		return this.dtStatusArquivo;
	}

	public void setDtStatusArquivo(Date dtStatusArquivo) {
		this.dtStatusArquivo = dtStatusArquivo;
	}


	@Column(name="MM_CICLO")
	public Integer getMmCiclo() {
		return this.mmCiclo;
	}

	public void setMmCiclo(Integer mmCiclo) {
		this.mmCiclo = mmCiclo;
	}


	@Column(name="NO_ARQUIVO")
	public String getNoArquivo() {
		return this.noArquivo;
	}

	public void setNoArquivo(String noArquivo) {
		this.noArquivo = noArquivo;
	}


	@Column(name="NO_ARQUIVO_IFC")
	public String getNoArquivoIfc() {
		return this.noArquivoIfc;
	}

	public void setNoArquivoIfc(String noArquivoIfc) {
		this.noArquivoIfc = noArquivoIfc;
	}


	@Column(name="NO_ARQUIVO_TRANS")
	public String getNoArquivoTrans() {
		return this.noArquivoTrans;
	}

	public void setNoArquivoTrans(String noArquivoTrans) {
		this.noArquivoTrans = noArquivoTrans;
	}


	@Column(name="NO_DIRETORIO_ARQUIVO")
	public String getNoDiretorioArquivo() {
		return this.noDiretorioArquivo;
	}

	public void setNoDiretorioArquivo(String noDiretorioArquivo) {
		this.noDiretorioArquivo = noDiretorioArquivo;
	}


	@Column(name="NU_ARQUIVO_IFC")
	public Integer getNuArquivoIfc() {
		return this.nuArquivoIfc;
	}

	public void setNuArquivoIfc(Integer nuArquivoIfc) {
		this.nuArquivoIfc = nuArquivoIfc;
	}


	@Column(name="QT_DURACAO_REAL")
	public Integer getQtDuracaoReal() {
		return this.qtDuracaoReal;
	}

	public void setQtDuracaoReal(Integer qtDuracaoReal) {
		this.qtDuracaoReal = qtDuracaoReal;
	}


	@Column(name="QT_DURACAO_TARIFADA")
	public Integer getQtDuracaoTarifada() {
		return this.qtDuracaoTarifada;
	}

	public void setQtDuracaoTarifada(Integer qtDuracaoTarifada) {
		this.qtDuracaoTarifada = qtDuracaoTarifada;
	}


	@Column(name="QT_ITENS_NF")
	public Integer getQtItensNf() {
		return this.qtItensNf;
	}

	public void setQtItensNf(Integer qtItensNf) {
		this.qtItensNf = qtItensNf;
	}


	@Column(name="QT_NFS")
	public Integer getQtNfs() {
		return this.qtNfs;
	}

	public void setQtNfs(Integer qtNfs) {
		this.qtNfs = qtNfs;
	}


	@Column(name="QT_PRODUTO_NF")
	public Integer getQtProdutoNf() {
		return this.qtProdutoNf;
	}

	public void setQtProdutoNf(Integer qtProdutoNf) {
		this.qtProdutoNf = qtProdutoNf;
	}


	@Column(name="QT_REGISTROS")
	public Integer getQtRegistros() {
		return this.qtRegistros;
	}

	public void setQtRegistros(Integer qtRegistros) {
		this.qtRegistros = qtRegistros;
	}


	@Column(name="SG_UF")
	public String getSgUf() {
		return this.sgUf;
	}

	public void setSgUf(String sgUf) {
		this.sgUf = sgUf;
	}


	@Column(name="VL_BASE_CALC_ICMS")
	public Double getVlBaseCalcIcms() {
		return this.vlBaseCalcIcms;
	}

	public void setVlBaseCalcIcms(Double vlBaseCalcIcms) {
		this.vlBaseCalcIcms = vlBaseCalcIcms;
	}


	@Column(name="VL_BASE_CALC_ICMS_IMPUGNADO")
	public Double getVlBaseCalcIcmsImpugnado() {
		return this.vlBaseCalcIcmsImpugnado;
	}

	public void setVlBaseCalcIcmsImpugnado(Double vlBaseCalcIcmsImpugnado) {
		this.vlBaseCalcIcmsImpugnado = vlBaseCalcIcmsImpugnado;
	}


	@Column(name="VL_BRUTO_ARQUIVO")
	public Double getVlBrutoArquivo() {
		return this.vlBrutoArquivo;
	}

	public void setVlBrutoArquivo(Double vlBrutoArquivo) {
		this.vlBrutoArquivo = vlBrutoArquivo;
	}


	@Column(name="VL_COFINS")
	public Double getVlCofins() {
		return this.vlCofins;
	}

	public void setVlCofins(Double vlCofins) {
		this.vlCofins = vlCofins;
	}


	@Column(name="VL_ICMS")
	public Double getVlIcms() {
		return this.vlIcms;
	}

	public void setVlIcms(Double vlIcms) {
		this.vlIcms = vlIcms;
	}


	@Column(name="VL_ICMS_IMPUGNADO")
	public Double getVlIcmsImpugnado() {
		return this.vlIcmsImpugnado;
	}

	public void setVlIcmsImpugnado(Double vlIcmsImpugnado) {
		this.vlIcmsImpugnado = vlIcmsImpugnado;
	}


	@Column(name="VL_LIQUIDO_ARQUIVO")
	public Double getVlLiquidoArquivo() {
		return this.vlLiquidoArquivo;
	}

	public void setVlLiquidoArquivo(Double vlLiquidoArquivo) {
		this.vlLiquidoArquivo = vlLiquidoArquivo;
	}


	@Column(name="VL_PIS")
	public Double getVlPis() {
		return this.vlPis;
	}

	public void setVlPis(Double vlPis) {
		this.vlPis = vlPis;
	}


	@Column(name="VL_TOTAL_IMPUGNADO")
	public Double getVlTotalImpugnado() {
		return this.vlTotalImpugnado;
	}

	public void setVlTotalImpugnado(Double vlTotalImpugnado) {
		this.vlTotalImpugnado = vlTotalImpugnado;
	}


	@Column(name="VL_TOTAL_NF")
	public Double getVlTotalNf() {
		return this.vlTotalNf;
	}

	public void setVlTotalNf(Double vlTotalNf) {
		this.vlTotalNf = vlTotalNf;
	}


	

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="CD_TIPO_ARQUIVO")
	public SccTipoArquivo getTipoArquivo() {
		return tipoArquivo;
	}


	public void setTipoArquivo(SccTipoArquivo tipoArquivo) {
		this.tipoArquivo = tipoArquivo;
	}


	@Column(name="SQ_ARQUIVO_ORIGEM")
	public Long getSqArquivoOrigem() {
		return sqArquivoOrigem;
	}

	public void setSqArquivoOrigem(Long sqArquivoOrigem) {
		this.sqArquivoOrigem = sqArquivoOrigem;
	}
	

	@Transient
	public SccArquivoCobilling getArquivoOrigem() {
		return arquivoOrigem;
	}


	public void setArquivoOrigem(SccArquivoCobilling arquivoOrigem) {
		this.arquivoOrigem = arquivoOrigem;
	}


	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="CD_EOT_CLARO")
	public SccOperadora getOperadoraClaro() {
		return operadoraClaro;
	}


	public void setOperadoraClaro(SccOperadora operadoraClaro) {
		this.operadoraClaro = operadoraClaro;
	}

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="CD_EOT_LD")
	public SccOperadora getOperadoraExterna() {
		return operadoraExterna;
	}


	public void setOperadoraExterna(SccOperadora operadoraExterna) {
		this.operadoraExterna = operadoraExterna;
	}


	

	
	
}