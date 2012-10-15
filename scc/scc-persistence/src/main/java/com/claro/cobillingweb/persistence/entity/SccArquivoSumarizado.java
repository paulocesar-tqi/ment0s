package com.claro.cobillingweb.persistence.entity;

import java.util.Date;

/**
 * Essa entity não pode ser mapeada pelo Hibernate uma vez que a tabela não possui PK;
 *
 */
public class SccArquivoSumarizado {

	private static final long serialVersionUID = 1L;
	private Long aaCiclo;
	private String cdAreaOrigem;
	private Long cdCiclo;
	private Long cdComponenteProduto;
	private String cdCsp;
	private String cdEotClaro;
	private String cdEotLd;
	private String cdErroReciclagem;
	private String cdMotivoRejeicao;
	private String cdRefaturamento;	
	private Long cdStatusCdr;
	private Date dtCarga;
	private Date dtChamada;
	private Date dtConnect;
	private Date dtEmissaoFatura;
	private Date dtEvento;
	private Date dtProcExterna;
	private Date dtRepasse;
	private Long mmCiclo;
	private Long qtCdrs;
	private Double qtDuracaoReal ;
	private Double qtDuracaoTarifada;
	private Long qtNf;
	private Long sqArquivo;
	private Long sqArquivoOriginal;
	private Double vlBrutoChamada;
	private Double vlFaturado;
	private Double vlFaturadoLiquido;
	private Double vlLiquidoChamada;
	private String dsStatusCdr;
	private String dsProduto;
	private String dsOperadoraClaro;
	private String dsOperadoraLd;
	private String cdSubStatusCdr;

	
	
	
	public Long getAaCiclo() {
		return aaCiclo;
	}
	public void setAaCiclo(Long aaCiclo) {
		this.aaCiclo = aaCiclo;
	}
	public String getCdAreaOrigem() {
		return cdAreaOrigem;
	}
	public void setCdAreaOrigem(String cdAreaOrigem) {
		this.cdAreaOrigem = cdAreaOrigem;
	}
	public Long getCdCiclo() {
		return cdCiclo;
	}
	public void setCdCiclo(Long cdCiclo) {
		this.cdCiclo = cdCiclo;
	}
	public Long getCdComponenteProduto() {
		return cdComponenteProduto;
	}
	public void setCdComponenteProduto(Long cdComponenteProduto) {
		this.cdComponenteProduto = cdComponenteProduto;
	}
	public String getCdCsp() {
		return cdCsp;
	}
	public void setCdCsp(String cdCsp) {
		this.cdCsp = cdCsp;
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
	public String getCdErroReciclagem() {
		return cdErroReciclagem;
	}
	public void setCdErroReciclagem(String cdErroReciclagem) {
		this.cdErroReciclagem = cdErroReciclagem;
	}
	public String getCdMotivoRejeicao() {
		return cdMotivoRejeicao;
	}
	public void setCdMotivoRejeicao(String cdMotivoRejeicao) {
		this.cdMotivoRejeicao = cdMotivoRejeicao;
	}
	public String getCdRefaturamento() {
		return cdRefaturamento;
	}
	public void setCdRefaturamento(String cdRefaturamento) {
		this.cdRefaturamento = cdRefaturamento;
	}
	public Long getCdStatusCdr() {
		return cdStatusCdr;
	}
	public void setCdStatusCdr(Long cdStatusCdr) {
		this.cdStatusCdr = cdStatusCdr;
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
	public Date getDtConnect() {
		return dtConnect;
	}
	public void setDtConnect(Date dtConnect) {
		this.dtConnect = dtConnect;
	}
	public Date getDtEmissaoFatura() {
		return dtEmissaoFatura;
	}
	public void setDtEmissaoFatura(Date dtEmissaoFatura) {
		this.dtEmissaoFatura = dtEmissaoFatura;
	}
	public Date getDtEvento() {
		return dtEvento;
	}
	public void setDtEvento(Date dtEvento) {
		this.dtEvento = dtEvento;
	}
	public Date getDtProcExterna() {
		return dtProcExterna;
	}
	public void setDtProcExterna(Date dtProcExterna) {
		this.dtProcExterna = dtProcExterna;
	}
	public Date getDtRepasse() {
		return dtRepasse;
	}
	public void setDtRepasse(Date dtRepasse) {
		this.dtRepasse = dtRepasse;
	}
	public Long getMmCiclo() {
		return mmCiclo;
	}
	public void setMmCiclo(Long mmCiclo) {
		this.mmCiclo = mmCiclo;
	}
	public Long getQtCdrs() {
		return qtCdrs;
	}
	public void setQtCdrs(Long qtCdrs) {
		this.qtCdrs = qtCdrs;
	}
	public Double getQtDuracaoReal() {
		return qtDuracaoReal;
	}
	public void setQtDuracaoReal(Double qtDuracaoReal) {
		this.qtDuracaoReal = qtDuracaoReal;
	}
	public Double getQtDuracaoTarifada() {
		return qtDuracaoTarifada;
	}
	public void setQtDuracaoTarifada(Double qtDuracaoTarifada) {
		this.qtDuracaoTarifada = qtDuracaoTarifada;
	}
	public Long getQtNf() {
		return qtNf;
	}
	public void setQtNf(Long qtNf) {
		this.qtNf = qtNf;
	}
	public Long getSqArquivo() {
		return sqArquivo;
	}
	public void setSqArquivo(Long sqArquivo) {
		this.sqArquivo = sqArquivo;
	}
	public Long getSqArquivoOriginal() {
		return sqArquivoOriginal;
	}
	public void setSqArquivoOriginal(Long sqArquivoOriginal) {
		this.sqArquivoOriginal = sqArquivoOriginal;
	}
	public Double getVlBrutoChamada() {
		return vlBrutoChamada;
	}
	public void setVlBrutoChamada(Double vlBrutoChamada) {
		this.vlBrutoChamada = vlBrutoChamada;
	}
	public Double getVlFaturado() {
		return vlFaturado;
	}
	public void setVlFaturado(Double vlFaturado) {
		this.vlFaturado = vlFaturado;
	}
	public Double getVlFaturadoLiquido() {
		return vlFaturadoLiquido;
	}
	public void setVlFaturadoLiquido(Double vlFaturadoLiquido) {
		this.vlFaturadoLiquido = vlFaturadoLiquido;
	}
	public Double getVlLiquidoChamada() {
		return vlLiquidoChamada;
	}
	public void setVlLiquidoChamada(Double vlLiquidoChamada) {
		this.vlLiquidoChamada = vlLiquidoChamada;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getDsStatusCdr() {
		return dsStatusCdr;
	}
	public void setDsStatusCdr(String dsStatusCdr) {
		this.dsStatusCdr = dsStatusCdr;
	}
	public String getDsProduto() {
		return dsProduto;
	}
	public void setDsProduto(String dsProduto) {
		this.dsProduto = dsProduto;
	}
	public String getDsOperadoraClaro() {
		return dsOperadoraClaro;
	}
	public void setDsOperadoraClaro(String dsOperadoraClaro) {
		this.dsOperadoraClaro = dsOperadoraClaro;
	}
	public String getDsOperadoraLd() {
		return dsOperadoraLd;
	}
	public void setDsOperadoraLd(String dsOperadoraLd) {
		this.dsOperadoraLd = dsOperadoraLd;
	}
	public String getCdSubStatusCdr() {
		return cdSubStatusCdr;
	}
	public void setCdSubStatusCdr(String cdSubStatusCdr) {
		this.cdSubStatusCdr = cdSubStatusCdr;
	}
	
	
	
	
	
}
