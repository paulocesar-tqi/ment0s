/**
 * 
 */
package com.claro.cobillingweb.persistence.view;

import java.util.Date;

/**
 * @author 93046251
 *
 */
public class SccBatimentoInterfaceView {
	
	private String nomeArquivo;
	private Long operadoraLD;
	private String operadoraClaro;
	private Date dataMovimentacao;
	private Date dataTransferencia;
	private Long quantidadeRegistrosMobile;
	private Date dataProcessamento;
	private Long quantidadeRegistrosScc;
	
	
	public String getNomeArquivo() {
		return nomeArquivo;
	}
	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}
	public Long getOperadoraLD() {
		return operadoraLD;
	}
	public void setOperadoraLD(Long operadoraLD) {
		this.operadoraLD = operadoraLD;
	}
	public String getOperadoraClaro() {
		return operadoraClaro;
	}
	public void setOperadoraClaro(String operadoraClaro) {
		this.operadoraClaro = operadoraClaro;
	}
	public Date getDataMovimentacao() {
		return dataMovimentacao;
	}
	public void setDataMovimentacao(Date dataMovimentacao) {
		this.dataMovimentacao = dataMovimentacao;
	}
	public Date getDataTransferencia() {
		return dataTransferencia;
	}
	public void setDataTransferencia(Date dataTransferencia) {
		this.dataTransferencia = dataTransferencia;
	}
	public Long getQuantidadeRegistrosMobile() {
		return quantidadeRegistrosMobile;
	}
	public void setQuantidadeRegistrosMobile(Long quantidadeRegistrosMobile) {
		this.quantidadeRegistrosMobile = quantidadeRegistrosMobile;
	}
	public Date getDataProcessamento() {
		return dataProcessamento;
	}
	public void setDataProcessamento(Date dataProcessamento) {
		this.dataProcessamento = dataProcessamento;
	}
	public Long getQuantidadeRegistrosScc() {
		return quantidadeRegistrosScc;
	}
	public void setQuantidadeRegistrosScc(Long quantidadeRegistrosScc) {
		this.quantidadeRegistrosScc = quantidadeRegistrosScc;
	}		

}
