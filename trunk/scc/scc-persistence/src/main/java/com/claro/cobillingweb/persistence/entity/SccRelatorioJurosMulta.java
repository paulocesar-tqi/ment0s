package com.claro.cobillingweb.persistence.entity;

import java.util.Date;

/**
 * A tabela SCC_RELATORIO_JUROS_MULTAS n�o possui PK. At� que essa situa��o
 * seja resolvida , essa entidade n�o possuir� annotations de persist�ncia e o DAO
 * ser� todo baseado em SQL puro e n�o Criteria ou HQL.
 *
 */
public class SccRelatorioJurosMulta {

	/**
	 * Propriedade transiente para auxiliar na sele��o de um registro j� que a entidade n�o possui PK.
	 * O rownum ser� populado pelo DAO e ser� relativo � lista de resultados do que a entidade faz parte naquele
	 * momento.
	 */
	private int rowNum;
	
	private SccOperadora operadoraClaro;
	private SccOperadora operadoraLD;
	private Long cdProdutoCobilling;
	private Date dtArrecadacao;
	private Date dtInicialRepasse;
	private Date dtVencimento;
	private String noArquivo;
	private String nuFatura;
	private Long nuQuinzena;
	private String sgUf;
	private Double vlJuros;
	private Double vlMultas;
	
	
	
	public SccOperadora getOperadoraClaro() {
		return operadoraClaro;
	}
	public void setOperadoraClaro(SccOperadora operadoraClaro) {
		this.operadoraClaro = operadoraClaro;
	}
	public SccOperadora getOperadoraLD() {
		return operadoraLD;
	}
	public void setOperadoraLD(SccOperadora operadoraLD) {
		this.operadoraLD = operadoraLD;
	}
	public Long getCdProdutoCobilling() {
		return cdProdutoCobilling;
	}
	public void setCdProdutoCobilling(Long cdProdutoCobilling) {
		this.cdProdutoCobilling = cdProdutoCobilling;
	}
	public Date getDtArrecadacao() {
		return dtArrecadacao;
	}
	public void setDtArrecadacao(Date dtArrecadacao) {
		this.dtArrecadacao = dtArrecadacao;
	}
	public Date getDtInicialRepasse() {
		return dtInicialRepasse;
	}
	public void setDtInicialRepasse(Date dtInicialRepasse) {
		this.dtInicialRepasse = dtInicialRepasse;
	}
	public Date getDtVencimento() {
		return dtVencimento;
	}
	public void setDtVencimento(Date dtVencimento) {
		this.dtVencimento = dtVencimento;
	}
	public String getNoArquivo() {
		return noArquivo;
	}
	public void setNoArquivo(String noArquivo) {
		this.noArquivo = noArquivo;
	}
	public String getNuFatura() {
		return nuFatura;
	}
	public void setNuFatura(String nuFatura) {
		this.nuFatura = nuFatura;
	}
	public Long getNuQuinzena() {
		return nuQuinzena;
	}
	public void setNuQuinzena(Long nuQuinzena) {
		this.nuQuinzena = nuQuinzena;
	}
	public String getSgUf() {
		return sgUf;
	}
	public void setSgUf(String sgUf) {
		this.sgUf = sgUf;
	}
	public Double getVlJuros() {
		return vlJuros;
	}
	public void setVlJuros(Double vlJuros) {
		this.vlJuros = vlJuros;
	}
	public Double getVlMultas() {
		return vlMultas;
	}
	public void setVlMultas(Double vlMultas) {
		this.vlMultas = vlMultas;
	}
	public int getRowNum() {
		return rowNum;
	}
	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}
	
	
	
	
}
