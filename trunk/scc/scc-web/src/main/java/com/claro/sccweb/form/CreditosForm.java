package com.claro.sccweb.form;

import java.util.Date;
import java.util.List;

import com.claro.cobillingweb.persistence.view.RelCreditosPrePagoView;
import com.claro.cobillingweb.persistence.view.RelDetalheCreditoPrePagoView;

public class CreditosForm {

	private String operacao;
	
	private String cdEOTLD;
	
	private String cdEOTClaro;
	
	private String cdTipoCredito;
	
	private String cdStatusCredito;
	
	private Date dataInicial;
	
	 
	
	private Long seqArquivo;
	
	private Long seqCredito;
	
	
	
	private Integer quantidadeResultados = 0;
	/**
	 * Nova página selecionada pelo usuário.
	 */
	private int paginaSelecionada;
	
	private Date dataFinal;
	
	private List<RelCreditosPrePagoView> listCreditosPrePago;
	
	private List<RelDetalheCreditoPrePagoView> listDetalhes;
	
	
		
	public String getOperacao() {
		return operacao;
	}

	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}

	public String getCdEOTLD() {
		return cdEOTLD;
	}

	public void setCdEOTLD(String cdEOTLD) {
		this.cdEOTLD = cdEOTLD;
	}

	public String getCdEOTClaro() {
		return cdEOTClaro;
	}

	public void setCdEOTClaro(String cdEOTClaro) {
		this.cdEOTClaro = cdEOTClaro;
	}

	public String getCdTipoCredito() {
		return cdTipoCredito;
	}

	public void setCdTipoCredito(String cdTipoCredito) {
		this.cdTipoCredito = cdTipoCredito;
	}

	public String getCdStatusCredito() {
		return cdStatusCredito;
	}

	public void setCdStatusCredito(String cdStatusCredito) {
		this.cdStatusCredito = cdStatusCredito;
	}

	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	

	public int getPaginaSelecionada() {
		return paginaSelecionada;
	}

	public void setPaginaSelecionada(int paginaSelecionada) {
		this.paginaSelecionada = paginaSelecionada;
	}

	public Long getSeqArquivo() {
		return seqArquivo;
	}

	public void setSeqArquivo(Long seqArquivo) {
		this.seqArquivo = seqArquivo;
	}

	public Long getSeqCredito() {
		return seqCredito;
	}

	public void setSeqCredito(Long seqCredito) {
		this.seqCredito = seqCredito;
	}

	public Integer getQuantidadeResultados() {
		return quantidadeResultados;
	}

	public void setQuantidadeResultados(Integer quantidadeResultados) {
		this.quantidadeResultados = quantidadeResultados;
	}

	public List<RelCreditosPrePagoView> getListCreditosPrePago() {
		return listCreditosPrePago;
	}

	public void setListCreditosPrePago(
			List<RelCreditosPrePagoView> listCreditosPrePago) {
		this.listCreditosPrePago = listCreditosPrePago;
	}

	public List<RelDetalheCreditoPrePagoView> getListDetalhes() {
		return listDetalhes;
	}

	public void setListDetalhes(List<RelDetalheCreditoPrePagoView> listDetalhes) {
		this.listDetalhes = listDetalhes;
	}

	
}
