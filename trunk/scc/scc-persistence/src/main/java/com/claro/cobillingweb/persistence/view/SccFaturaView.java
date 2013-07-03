/**
 * 
 */
package com.claro.cobillingweb.persistence.view;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author 93046251
 *
 */
public class SccFaturaView implements Serializable {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1378694094457260502L;
	
	private String eotClaro;
	private String csp;
	private String operadoraLD;
	private String uf;
	private String cicloMesAno;
	private String numeroFatura;
	private Timestamp dataEmissao;
	private Timestamp dataVencimento;
	private Timestamp dataVencimentoOriginal;
	private Double valorOriginal;
	private Double valor;
	private Double valorICMS;
	private String status;
	private String situacaoEvento;
	private Long aging;
	private Double ajuste;
	private String dscStatus;
	private Long numeroNotaFiscal;
	private Long numeroAging;
	private String serie;
	private String subSerie;
	private Double totalCreditos;
	private Double totalAjustes;
	private Double valorOfertasLD;
	private Double valorDescontosLD;
	private Double valorCreditosLD;
	private Double valorPago;
	private Long quantidadeEventos;
	private Double juros;
	private Double multas;
	private Integer cdCiclo;
	private Integer mmCiclo;
	private Integer aaCiclo;
	
	
	
	
	public String getEotClaro() {
		return eotClaro;
	}
	public void setEotClaro(String eotClaro) {
		this.eotClaro = eotClaro;
	}
	public String getCsp() {
		return csp;
	}
	public void setCsp(String csp) {
		this.csp = csp;
	}
	public String getOperadoraLD() {
		return operadoraLD;
	}
	public void setOperadoraLD(String operadoraLD) {
		this.operadoraLD = operadoraLD;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public String getCicloMesAno() {
		return cicloMesAno;
	}
	public void setCicloMesAno(String cicloMesAno) {
		this.cicloMesAno = cicloMesAno;
	}
	public String getNumeroFatura() {
		return numeroFatura;
	}
	public void setNumeroFatura(String numeroFatura) {
		this.numeroFatura = numeroFatura;
	}
	public Timestamp getDataEmissao() {
		return dataEmissao;
	}
	public void setDataEmissao(Timestamp dataEmissao) {
		this.dataEmissao = dataEmissao;
	}
	public Timestamp getDataVencimento() {
		return dataVencimento;
	}
	public void setDataVencimento(Timestamp dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	public Timestamp getDataVencimentoOriginal() {
		return dataVencimentoOriginal;
	}
	public void setDataVencimentoOriginal(Timestamp dataVencimentoOriginal) {
		this.dataVencimentoOriginal = dataVencimentoOriginal;
	}
	public Double getValorOriginal() {
		return valorOriginal;
	}
	public void setValorOriginal(Double valorOriginal) {
		this.valorOriginal = valorOriginal;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public Double getValorICMS() {
		return valorICMS;
	}
	public void setValorICMS(Double valorICMS) {
		this.valorICMS = valorICMS;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSituacaoEvento() {
		return situacaoEvento;
	}
	public void setSituacaoEvento(String situacaoEvento) {
		this.situacaoEvento = situacaoEvento;
	}
	public Long getAging() {
		return aging;
	}
	public void setAging(Long aging) {
		this.aging = aging;
	}
	public Double getAjuste() {
		return ajuste;
	}
	public void setAjuste(Double ajuste) {
		this.ajuste = ajuste;
	}
	public String getDscStatus() {
		return dscStatus;
	}
	public void setDscStatus(String dscStatus) {
		this.dscStatus = dscStatus;
	}
	public Long getNumeroNotaFiscal() {
		return numeroNotaFiscal;
	}
	public void setNumeroNotaFiscal(Long numeroNotaFiscal) {
		this.numeroNotaFiscal = numeroNotaFiscal;
	}
	public Long getNumeroAging() {
		return numeroAging;
	}
	public void setNumeroAging(Long numeroAging) {
		this.numeroAging = numeroAging;
	}
	public String getSerie() {
		return serie;
	}
	public void setSerie(String serie) {
		this.serie = serie;
	}
	public String getSubSerie() {
		return subSerie;
	}
	public void setSubSerie(String subSerie) {
		this.subSerie = subSerie;
	}
	public Double getTotalCreditos() {
		return totalCreditos;
	}
	public void setTotalCreditos(Double totalCreditos) {
		this.totalCreditos = totalCreditos;
	}
	public Double getTotalAjustes() {
		return totalAjustes;
	}
	public void setTotalAjustes(Double totalAjustes) {
		this.totalAjustes = totalAjustes;
	}
	public Double getValorOfertasLD() {
		return valorOfertasLD;
	}
	public void setValorOfertasLD(Double valorOfertasLD) {
		this.valorOfertasLD = valorOfertasLD;
	}
	public Double getValorDescontosLD() {
		return valorDescontosLD;
	}
	public void setValorDescontosLD(Double valorDescontosLD) {
		this.valorDescontosLD = valorDescontosLD;
	}
	public Double getValorCreditosLD() {
		return valorCreditosLD;
	}
	public void setValorCreditosLD(Double valorCreditosLD) {
		this.valorCreditosLD = valorCreditosLD;
	}
	public Double getValorPago() {
		return valorPago;
	}
	public void setValorPago(Double valorPago) {
		this.valorPago = valorPago;
	}
	public Long getQuantidadeEventos() {
		return quantidadeEventos;
	}
	public void setQuantidadeEventos(Long quantidadeEventos) {
		this.quantidadeEventos = quantidadeEventos;
	}
	public Double getJuros() {
		return juros;
	}
	public void setJuros(Double juros) {
		this.juros = juros;
	}
	public Double getMultas() {
		return multas;
	}
	public void setMultas(Double multas) {
		this.multas = multas;
	}
	public Integer getCdCiclo() {
		return cdCiclo;
	}
	public void setCdCiclo(Integer cdCiclo) {
		this.cdCiclo = cdCiclo;
	}
	public Integer getMmCiclo() {
		return mmCiclo;
	}
	public void setMmCiclo(Integer mmCiclo) {
		this.mmCiclo = mmCiclo;
	}
	public Integer getAaCiclo() {
		return aaCiclo;
	}
	public void setAaCiclo(Integer aaCiclo) {
		this.aaCiclo = aaCiclo;
	}
	
	
	


}
