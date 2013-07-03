package com.claro.sccweb.decorator.rownum.entity;

import com.claro.cobillingweb.persistence.view.SccPreRelatorioCriticasView;
import com.claro.sccweb.decorator.rownum.RownumDecorator;

/**
* @author 92038883
*
*/
	public class SccPreRelatorioCriticasViewDecorator extends RownumDecorator<SccPreRelatorioCriticasView> {
		

		private SccPreRelatorioCriticasView entity;
		
		private String dtChamada;
		private String statusChamada;
		private String motivoRejeicao;
		private String cdDefeito;
		private String dsOperadoraClaro;
		private String dsOperadoraExterna;
		private String csp;
		private String nroA;
		private String nroB;
		private String cdPais;
		private String cnAreaVisitada;
		private String tipoChamada;
		private String duracaoReal;
		private String duracaoTarifada;
		private String valorBruto;
		
		
		public SccPreRelatorioCriticasViewDecorator(SccPreRelatorioCriticasView entity,
				int rownum) {
			super(entity, rownum);
			formatarCampos(entity);
			
		}

		@Override
		protected boolean isDeleteEnabled() {
			return false;
		}

		
		private void formatarCampos(SccPreRelatorioCriticasView entity){

			this.setDtChamada(formataDate(entity.getDtChamada()));
			this.setStatusChamada(entity.getStatusChamada());
			this.setMotivoRejeicao(entity.getMotivoRejeicao());
			this.setCdDefeito(entity.getCdDefeito());
			this.setDsOperadoraClaro(entity.getDsOperadoraClaro());
			this.setDsOperadoraExterna(entity.getDsOperadoraExterna());
			this.setCsp(entity.getCsp());
			this.setNroA(entity.getNroA());
			this.setNroB(entity.getNroB());
			this.setCdPais(entity.getCdPais());
			this.setCnAreaVisitada(entity.getCnAreaVisitada());
			this.setTipoChamada(entity.getTipoChamada());
			this.setDuracaoReal(formataDouble(entity.getDuracaoReal()));
			this.setDuracaoTarifada(formataDouble(entity.getDuracaoTarifada()));
			this.setValorBruto(formataDouble(entity.getValorBruto()));
		}	

		
		public SccPreRelatorioCriticasView getEntity() {
			return entity;
		}
		public void setEntity(SccPreRelatorioCriticasView entity) {
			this.entity = entity;
		}

		public String getDtChamada() {
			return dtChamada;
		}

		public void setDtChamada(String dtChamada) {
			this.dtChamada = dtChamada;
		}

		public String getStatusChamada() {
			return statusChamada;
		}

		public void setStatusChamada(String statusChamada) {
			this.statusChamada = statusChamada;
		}

		public String getMotivoRejeicao() {
			return motivoRejeicao;
		}

		public void setMotivoRejeicao(String motivoRejeicao) {
			this.motivoRejeicao = motivoRejeicao;
		}

		public String getCdDefeito() {
			return cdDefeito;
		}

		public void setCdDefeito(String cdDefeito) {
			this.cdDefeito = cdDefeito;
		}

		public String getDsOperadoraClaro() {
			return dsOperadoraClaro;
		}

		public void setDsOperadoraClaro(String dsOperadoraClaro) {
			this.dsOperadoraClaro = dsOperadoraClaro;
		}

		public String getDsOperadoraExterna() {
			return dsOperadoraExterna;
		}

		public void setDsOperadoraExterna(String dsOperadoraExterna) {
			this.dsOperadoraExterna = dsOperadoraExterna;
		}

		public String getCsp() {
			return csp;
		}

		public void setCsp(String csp) {
			this.csp = csp;
		}

		public String getNroA() {
			return nroA;
		}

		public void setNroA(String nroA) {
			this.nroA = nroA;
		}

		public String getNroB() {
			return nroB;
		}

		public void setNroB(String nroB) {
			this.nroB = nroB;
		}

		public String getCdPais() {
			return cdPais;
		}

		public void setCdPais(String cdPais) {
			this.cdPais = cdPais;
		}

		public String getCnAreaVisitada() {
			return cnAreaVisitada;
		}

		public void setCnAreaVisitada(String cnAreaVisitada) {
			this.cnAreaVisitada = cnAreaVisitada;
		}

		public String getTipoChamada() {
			return tipoChamada;
		}

		public void setTipoChamada(String tipoChamada) {
			this.tipoChamada = tipoChamada;
		}

		public String getDuracaoReal() {
			return duracaoReal;
		}

		public void setDuracaoReal(String duracaoReal) {
			this.duracaoReal = duracaoReal;
		}

		public String getDuracaoTarifada() {
			return duracaoTarifada;
		}

		public void setDuracaoTarifada(String duracaoTarifada) {
			this.duracaoTarifada = duracaoTarifada;
		}

		public String getValorBruto() {
			return valorBruto;
		}

		public void setValorBruto(String valorBruto) {
			this.valorBruto = valorBruto;
		}

}
