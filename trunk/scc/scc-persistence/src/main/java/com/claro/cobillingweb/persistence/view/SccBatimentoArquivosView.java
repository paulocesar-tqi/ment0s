/**
 * 
 */
package com.claro.cobillingweb.persistence.view;

import java.util.Date;

/**
 * @author 93046251
 *
 */
public class SccBatimentoArquivosView {
	
	private Long sqArquivo;

	//colunas CLARO
	private Date dtConnectClaro;
	private Date dtReferenciaClaro;
	private String nomeArquivoClaro;
	private String dnsProtocoloClaro;
	private Double duracaoTarifadaClaro;
	private Double quantidadeClaro;
	private Double valorLiquidoClaro;
	private String erroProtocoloClaro;
	private String descErroProtocoloClaro;

	//colunas LD
	private String nomeArquivoLD;
	private Double duracaoTarifadaLD;
	private Double quantidadeLD;
	private Double valorLiquidoLD;
	private String statusLD;

	//colunas RESULTADO BATIMENTO	
	private String statusBatimento;//???
	private Double duracaoTarifadaBat;
	private Double quantidadeBat;
	private Double valorLiquidoBat;
	public Long getSqArquivo() {
		return sqArquivo;
	}
	public void setSqArquivo(Long sqArquivo) {
		this.sqArquivo = sqArquivo;
	}
	public Date getDtConnectClaro() {
		return dtConnectClaro;
	}
	public void setDtConnectClaro(java.util.Date dtConnectClaro) {
		this.dtConnectClaro = dtConnectClaro;
	}
	public java.util.Date getDtReferenciaClaro() {
		return dtReferenciaClaro;
	}
	public void setDtReferenciaClaro(java.util.Date dtReferenciaClaro) {
		this.dtReferenciaClaro = dtReferenciaClaro;
	}
	public String getNomeArquivoClaro() {
		return nomeArquivoClaro;
	}
	public void setNomeArquivoClaro(String nomeArquivoClaro) {
		this.nomeArquivoClaro = nomeArquivoClaro;
	}
	public String getDnsProtocoloClaro() {
		return dnsProtocoloClaro;
	}
	public void setDnsProtocoloClaro(String dnsProtocoloClaro) {
		this.dnsProtocoloClaro = dnsProtocoloClaro;
	}
	public Double getDuracaoTarifadaClaro() {
		return duracaoTarifadaClaro;
	}
	public void setDuracaoTarifadaClaro(Double duracaoTarifadaClaro) {
		this.duracaoTarifadaClaro = duracaoTarifadaClaro;
	}
	public Double getQuantidadeClaro() {
		return quantidadeClaro;
	}
	public void setQuantidadeClaro(Double quantidadeClaro) {
		this.quantidadeClaro = quantidadeClaro;
	}
	public Double getValorLiquidoClaro() {
		return valorLiquidoClaro;
	}
	public void setValorLiquidoClaro(Double valorLiquidoClaro) {
		this.valorLiquidoClaro = valorLiquidoClaro;
	}
	public String getErroProtocoloClaro() {
		return erroProtocoloClaro;
	}
	public void setErroProtocoloClaro(String erroProtocoloClaro) {
		this.erroProtocoloClaro = erroProtocoloClaro;
	}
	public String getDescErroProtocoloClaro() {
		return descErroProtocoloClaro;
	}
	public void setDescErroProtocoloClaro(String descErroProtocoloClaro) {
		this.descErroProtocoloClaro = descErroProtocoloClaro;
	}
	public String getNomeArquivoLD() {
		return nomeArquivoLD;
	}
	public void setNomeArquivoLD(String nomeArquivoLD) {
		this.nomeArquivoLD = nomeArquivoLD;
	}
	public Double getDuracaoTarifadaLD() {
		return duracaoTarifadaLD;
	}
	public void setDuracaoTarifadaLD(Double duracaoTarifadaLD) {
		this.duracaoTarifadaLD = duracaoTarifadaLD;
	}
	public Double getQuantidadeLD() {
		return quantidadeLD;
	}
	public void setQuantidadeLD(Double quantidadeLD) {
		this.quantidadeLD = quantidadeLD;
	}
	public Double getValorLiquidoLD() {
		return valorLiquidoLD;
	}
	public void setValorLiquidoLD(Double valorLiquidoLD) {
		this.valorLiquidoLD = valorLiquidoLD;
	}
	public String getStatusLD() {
		return statusLD;
	}
	public void setStatusLD(String statusLD) {
		this.statusLD = statusLD;
	}
	public String getStatusBatimento() {
		return statusBatimento;
	}
	public void setStatusBatimento(String statusBatimento) {
		this.statusBatimento = statusBatimento;
	}
	public Double getDuracaoTarifadaBat() {
		return duracaoTarifadaBat;
	}
	public void setDuracaoTarifadaBat(Double duracaoTarifadaBat) {
		this.duracaoTarifadaBat = duracaoTarifadaBat;
	}
	public Double getQuantidadeBat() {
		return quantidadeBat;
	}
	public void setQuantidadeBat(Double quantidadeBat) {
		this.quantidadeBat = quantidadeBat;
	}
	public Double getValorLiquidoBat() {
		return valorLiquidoBat;
	}
	public void setValorLiquidoBat(Double valorLiquidoBat) {
		this.valorLiquidoBat = valorLiquidoBat;
	}

	
	

}
