package com.claro.cobillingweb.persistence.entity.external;

import java.util.Date;

public class ViewArquivoPrePago {

	private Long SQ_ARQUIVO;
	private String NO_ARQUIVO;
	private String NO_DIRETORIO_ARQUIVO;
	private String NO_MAQUINA_ARQUIVO;
	private Date DT_MOVIMENTO;
	private String NO_SISTEMA_RESP; 
	private String NO_PROCESSO_RESP; 
	private String IN_STATUS_PROCESSO;    
	private Date DH_PROCESSO;
	private Long SQ_ARQUIVO_CONTROLE_PRE;         			    
	private Integer QT_REGISTROS_ARQUIVO;	 
	private Integer QT_REGISTROS_PROCESSADOS;
	public Long getSQ_ARQUIVO() {
		return SQ_ARQUIVO;
	}
	public void setSQ_ARQUIVO(Long sQ_ARQUIVO) {
		SQ_ARQUIVO = sQ_ARQUIVO;
	}
	public String getNO_ARQUIVO() {
		return NO_ARQUIVO;
	}
	public void setNO_ARQUIVO(String nO_ARQUIVO) {
		NO_ARQUIVO = nO_ARQUIVO;
	}
	public String getNO_DIRETORIO_ARQUIVO() {
		return NO_DIRETORIO_ARQUIVO;
	}
	public void setNO_DIRETORIO_ARQUIVO(String nO_DIRETORIO_ARQUIVO) {
		NO_DIRETORIO_ARQUIVO = nO_DIRETORIO_ARQUIVO;
	}
	public String getNO_MAQUINA_ARQUIVO() {
		return NO_MAQUINA_ARQUIVO;
	}
	public void setNO_MAQUINA_ARQUIVO(String nO_MAQUINA_ARQUIVO) {
		NO_MAQUINA_ARQUIVO = nO_MAQUINA_ARQUIVO;
	}
	public Date getDT_MOVIMENTO() {
		return DT_MOVIMENTO;
	}
	public void setDT_MOVIMENTO(Date dT_MOVIMENTO) {
		DT_MOVIMENTO = dT_MOVIMENTO;
	}
	public String getNO_SISTEMA_RESP() {
		return NO_SISTEMA_RESP;
	}
	public void setNO_SISTEMA_RESP(String nO_SISTEMA_RESP) {
		NO_SISTEMA_RESP = nO_SISTEMA_RESP;
	}
	public String getNO_PROCESSO_RESP() {
		return NO_PROCESSO_RESP;
	}
	public void setNO_PROCESSO_RESP(String nO_PROCESSO_RESP) {
		NO_PROCESSO_RESP = nO_PROCESSO_RESP;
	}
	public String getIN_STATUS_PROCESSO() {
		return IN_STATUS_PROCESSO;
	}
	public void setIN_STATUS_PROCESSO(String iN_STATUS_PROCESSO) {
		IN_STATUS_PROCESSO = iN_STATUS_PROCESSO;
	}
	public Date getDH_PROCESSO() {
		return DH_PROCESSO;
	}
	public void setDH_PROCESSO(Date dH_PROCESSO) {
		DH_PROCESSO = dH_PROCESSO;
	}
	public Long getSQ_ARQUIVO_CONTROLE_PRE() {
		return SQ_ARQUIVO_CONTROLE_PRE;
	}
	public void setSQ_ARQUIVO_CONTROLE_PRE(Long sQ_ARQUIVO_CONTROLE_PRE) {
		SQ_ARQUIVO_CONTROLE_PRE = sQ_ARQUIVO_CONTROLE_PRE;
	}
	public Integer getQT_REGISTROS_ARQUIVO() {
		return QT_REGISTROS_ARQUIVO;
	}
	public void setQT_REGISTROS_ARQUIVO(Integer qT_REGISTROS_ARQUIVO) {
		QT_REGISTROS_ARQUIVO = qT_REGISTROS_ARQUIVO;
	}
	public Integer getQT_REGISTROS_PROCESSADOS() {
		return QT_REGISTROS_PROCESSADOS;
	}
	public void setQT_REGISTROS_PROCESSADOS(Integer qT_REGISTROS_PROCESSADOS) {
		QT_REGISTROS_PROCESSADOS = qT_REGISTROS_PROCESSADOS;
	}
	
	
	
	
	
}
