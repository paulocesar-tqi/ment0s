package com.claro.cobillingweb.persistence.view;

import java.util.Date;

/**
 * @author 92038883
 *
 */

public class SccRelatorioQuestionamentoView{

		private Long sqQuestionamento;
		private String cdEotLD;
		private String cdStatusQuestionamento;
		private Date dtCredito;
		private String dtProtocoloClaro;
		private String dtProtocoloLD;
		private String dtAnalise;
		private String dtCorrecao;
		private String txComentarioClaro;
		private String txComentarioLD;
		private String txAnalise;
		private String txCorrecao;
		private String txMotivosRejeicao;
		private String txArquivos;
		private String cdIdentificacaoCartaClaro;
		private String cdIdentificacaoCartaLD;
		private String noResponsavelClaro;
		private String noResponsavelLD;
		private String cdProcesso;
		private Date criacaoDt;
		private String atualizacaoDt;
		private String usuarioManutCd;
		private Double miDuracaoTarifada;
		private Double vlLiquidoChamada;
		private Double vlBrutoChamada;
		private Double vlPotencialMulta;
		
		
		
		public Long getSqQuestionamento() {
			return sqQuestionamento;
		}
		public void setSqQuestionamento(Long sqQuestionamento) {
			this.sqQuestionamento = sqQuestionamento;
		}
		public String getCdEotLD() {
			return cdEotLD;
		}
		public void setCdEotLD(String cdEotLD) {
			this.cdEotLD = cdEotLD;
		}
		public String getCdStatusQuestionamento() {
			return cdStatusQuestionamento;
		}
		public void setCdStatusQuestionamento(String cdStatusQuestionamento) {
			this.cdStatusQuestionamento = montarStatus(cdStatusQuestionamento);
		}
		
		public Date getDtCredito() {
			return dtCredito;
		}
		public void setDtCredito(Date dtCredito) {
			this.dtCredito = dtCredito;
		}
		public String getDtProtocoloClaro() {
			return dtProtocoloClaro;
		}
		public void setDtProtocoloClaro(String dtProtocoloClaro) {
			if((dtProtocoloClaro) != null){
				this.dtProtocoloClaro = dtProtocoloClaro;
			}
			this.dtProtocoloClaro = null;
		}
		public String getDtProtocoloLD() {
			return dtProtocoloLD;
		}
		public void setDtProtocoloLD(String dtProtocoloLD) {
			this.dtProtocoloLD = dtProtocoloLD;
		}
		public String getDtAnalise() {
			return dtAnalise;
		}
		public void setDtAnalise(String dtAnalise) {
			this.dtAnalise = dtAnalise;
		}
		public String getDtCorrecao() {
			return dtCorrecao;
		}
		public void setDtCorrecao(String dtCorrecao) {
			this.dtCorrecao = dtCorrecao;
		}
		public String getTxComentarioClaro() {
			return txComentarioClaro;
		}
		public void setTxComentarioClaro(String txComentarioClaro) {
			this.txComentarioClaro = txComentarioClaro;
		}
		public String getTxComentarioLD() {
			return txComentarioLD;
		}
		public void setTxComentarioLD(String txComentarioLD) {
			this.txComentarioLD = txComentarioLD;
		}
		public String getTxAnalise() {
			return txAnalise;
		}
		public void setTxAnalise(String txAnalise) {
			this.txAnalise = txAnalise;
		}
		public String getTxCorrecao() {
			return txCorrecao;
		}
		public void setTxCorrecao(String txCorrecao) {
			this.txCorrecao = txCorrecao;
		}
		public String getTxMotivosRejeicao() {
			return txMotivosRejeicao;
		}
		public void setTxMotivosRejeicao(String txMotivosRejeicao) {
			this.txMotivosRejeicao = txMotivosRejeicao;
		}
		public String getTxArquivos() {
			return txArquivos;
		}
		public void setTxArquivos(String txArquivos) {
			this.txArquivos = txArquivos;
		}
		public String getCdIdentificacaoCartaClaro() {
			return cdIdentificacaoCartaClaro;
		}
		public void setCdIdentificacaoCartaClaro(String cdIdentificacaoCartaClaro) {
			this.cdIdentificacaoCartaClaro = cdIdentificacaoCartaClaro;
		}
		public String getCdIdentificacaoCartaLD() {
			return cdIdentificacaoCartaLD;
		}
		public void setCdIdentificacaoCartaLD(String cdIdentificacaoCartaLD) {
			this.cdIdentificacaoCartaLD = cdIdentificacaoCartaLD;
		}
		public String getNoResponsavelClaro() {
			return noResponsavelClaro;
		}
		public void setNoResponsavelClaro(String noResponsavelClaro) {
			this.noResponsavelClaro = noResponsavelClaro;
		}
		public String getNoResponsavelLD() {
			return noResponsavelLD;
		}
		public void setNoResponsavelLD(String noResponsavelLD) {
			this.noResponsavelLD = noResponsavelLD;
		}
		public String getCdProcesso() {
			return cdProcesso;
		}
		public void setCdProcesso(String cdProcesso) {
			this.cdProcesso = cdProcesso;
		}
		public Date getCriacaoDt() {
			return criacaoDt;
		}
		public void setCriacaoDt(Date criacaoDt) {
			this.criacaoDt = criacaoDt;
		}
		public String getAtualizacaoDt() {
			return atualizacaoDt;
		}
		public void setAtualizacaoDt(String atualizacaoDt) {
			this.atualizacaoDt = atualizacaoDt;
		}
		public String getUsuarioManutCd() {
			return usuarioManutCd;
		}
		public void setUsuarioManutCd(String usuarioManutCd) {
			this.usuarioManutCd = usuarioManutCd;
		}
		public Double getMiDuracaoTarifada() {
			return miDuracaoTarifada;
		}
		public void setMiDuracaoTarifada(Double miDuracaoTarifada) {
			this.miDuracaoTarifada = miDuracaoTarifada;
		}
		public Double getVlLiquidoChamada() {
			return vlLiquidoChamada;
		}
		public void setVlLiquidoChamada(Double vlLiquidoChamada) {
			this.vlLiquidoChamada = vlLiquidoChamada;
		}
		public Double getVlBrutoChamada() {
			return vlBrutoChamada;
		}
		public void setVlBrutoChamada(Double vlBrutoChamada) {
			this.vlBrutoChamada = vlBrutoChamada;
		}
		public Double getVlPotencialMulta() {
			return vlPotencialMulta;
		}
		public void setVlPotencialMulta(Double vlPotencialMulta) {
			this.vlPotencialMulta = vlPotencialMulta;
		}

		
		private String montarStatus(String value){
			String retorno = "";
			if(value.equalsIgnoreCase("RGS")){
				retorno = "Registro LD";
			}
			
			if(value.equalsIgnoreCase("PRP")){
				retorno = "Preparação";
			}
			
			if(value.equalsIgnoreCase("ANL")){
				retorno = "Análise";
			}
			
			if(value.equalsIgnoreCase("CRR")){
				retorno = "Correção";
			}
			
			if(value.equalsIgnoreCase("APR")){
				retorno = "Apuração";
			}
			
			if(value.equalsIgnoreCase("CNF")){
				retorno = "Confirmação";
			}
			
			if(value.equalsIgnoreCase("RGC")){
				retorno = "Registro Claro";
			}
			
			if(value.equalsIgnoreCase("INC")){
				retorno = "Inclusão";
			}
			
			if(value.equalsIgnoreCase("RPS")){
				retorno = "Repasse";
			}
			
			return retorno;
		}
		
		
		
		
}
