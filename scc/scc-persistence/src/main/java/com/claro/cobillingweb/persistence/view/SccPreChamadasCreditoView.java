package com.claro.cobillingweb.persistence.view;

import java.util.Date;

/**
 * @author 92038883
 *
 */

public class SccPreChamadasCreditoView {
		
		private String arquivoCredito;
		private String dsOperadoraExterna;
		private String dsOperadoraClaro;
		private String cdCsp;
		private String statusCredito;
		private Date dtCredito;
		private Double valorCredito;
		private Long nuCDR;
		private Integer cdCredito;
		private String nuAssA;
		private String nuAssB;
		private Date dtChamada;
		private Integer inicioChamada;
		private Double duracaoTarifada;
		private Double valorBrutoChamada;
		
		
		public String getArquivoCredito() {
			return arquivoCredito;
		}
		public void setArquivoCredito(String arquivoCredito) {
			this.arquivoCredito = arquivoCredito;
		}
		public String getDsOperadoraExterna() {
			return dsOperadoraExterna;
		}
		public void setDsOperadoraExterna(String dsOperadoraExterna) {
			this.dsOperadoraExterna = dsOperadoraExterna;
		}
		public String getDsOperadoraClaro() {
			return dsOperadoraClaro;
		}
		public void setDsOperadoraClaro(String dsOperadoraClaro) {
			this.dsOperadoraClaro = dsOperadoraClaro;
		}
		public String getCdCsp() {
			return cdCsp;
		}
		public void setCdCsp(String cdCsp) {
			this.cdCsp = cdCsp;
		}
		public String getStatusCredito() {
			return statusCredito;
		}
		public void setStatusCredito(String statusCredito) {
			
			this.statusCredito =  montarStatusCredito(statusCredito);
		}
		public Date getDtCredito() {
			return dtCredito;
		}
		public void setDtCredito(Date dtCredito) {
			this.dtCredito = dtCredito;
		}
		public Double getValorCredito() {
			return valorCredito;
		}
		public void setValorCredito(Double valorCredito) {
			this.valorCredito = valorCredito;
		}
		public Long getNuCDR() {
			return nuCDR;
		}
		public void setNuCDR(Long nuCDR) {
			this.nuCDR = nuCDR;
		}
		public Integer getCdCredito() {
			return cdCredito;
		}
		public void setCdCredito(Integer cdCredito) {
			this.cdCredito = cdCredito;
		}
		public String getNuAssA() {
			return nuAssA;
		}
		public void setNuAssA(String nuAssA) {
			this.nuAssA = nuAssA;
		}
		public String getNuAssB() {
			return nuAssB;
		}
		public void setNuAssB(String nuAssB) {
			this.nuAssB = nuAssB;
		}
		public Date getDtChamada() {
			return dtChamada;
		}
		public void setDtChamada(Date dtChamada) {
			this.dtChamada = dtChamada;
		}
		public Integer getInicioChamada() {
			return inicioChamada;
		}
		public void setInicioChamada(Integer inicioChamada) {
			this.inicioChamada = inicioChamada;
		}
		public Double getDuracaoTarifada() {
			return duracaoTarifada;
		}
		public void setDuracaoTarifada(Double duracaoTarifada) {
			this.duracaoTarifada = duracaoTarifada;
		}
		public Double getValorBrutoChamada() {
			return valorBrutoChamada;
		}
		public void setValorBrutoChamada(Double valorBrutoChamada) {
			this.valorBrutoChamada = valorBrutoChamada;
		}
		
		private String montarStatusCredito(String value){
			String retorno = "";
			if(value.equalsIgnoreCase("C")){
				retorno = "Confirmado";
			}
			
			if(value.equalsIgnoreCase("P")){
				retorno = "Pendente";
			}
			
			if(value.equalsIgnoreCase("E")){
				retorno = "Erro";
			}
			
			return retorno;
		}
	
	
		
		
}
