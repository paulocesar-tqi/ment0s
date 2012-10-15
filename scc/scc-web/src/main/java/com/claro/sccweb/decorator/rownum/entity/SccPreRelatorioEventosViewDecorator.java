package com.claro.sccweb.decorator.rownum.entity;

import com.claro.cobillingweb.persistence.view.SccPreRelatorioEventosView;
import com.claro.sccweb.decorator.rownum.RownumDecorator;

/**
* @author 92038883
*
*/
	public class SccPreRelatorioEventosViewDecorator extends RownumDecorator<SccPreRelatorioEventosView> {
		

		private SccPreRelatorioEventosView entity;
		
		private String dtReferencia;
		private String dsOperadoraClaro;
		private String dsOperadoraExterna;
		private String statusChamada;
		private String motivoRejeicao;
		private String dsDefeito;
		private String quantidadeCdrs;
		private String duracaoReal;
		private String duracaoTarifada;
		private String valorBruto;
		
		
		public SccPreRelatorioEventosViewDecorator(SccPreRelatorioEventosView entity,
				int rownum) {
			super(entity, rownum);
			formatarCampos(entity);
			
		}

		@Override
		protected boolean isDeleteEnabled() {
			return false;
		}

		
		private void formatarCampos(SccPreRelatorioEventosView entity){

			this.setDtReferencia(formataDate(entity.getDtReferencia()));
			this.setDsOperadoraClaro(entity.getDsOperadoraClaro());
			this.setDsOperadoraExterna(entity.getDsOperadoraExterna());
			this.setStatusChamada(entity.getStatusChamada());
			this.setMotivoRejeicao(entity.getMotivoRejeicao());
			this.setDsDefeito(entity.getDsDefeito());
			this.setQuantidadeCdrs(formataDouble(entity.getQuantidadeCdrs()));
			this.setDuracaoReal(formataDouble(entity.getDuracaoReal()));
			this.setDuracaoTarifada(formataDouble(entity.getDuracaoTarifada()));
			this.setValorBruto(formataDouble(entity.getValorBruto()));
		}	
		
		public SccPreRelatorioEventosView getEntity() {
			return entity;
		}
		public void setEntity(SccPreRelatorioEventosView entity) {
			this.entity = entity;
		}

		public String getDtReferencia() {
			return dtReferencia;
		}

		public void setDtReferencia(String dtReferencia) {
			this.dtReferencia = dtReferencia;
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
		public String getDsDefeito() {
			return dsDefeito;
		}
		public void setDsDefeito(String dsDefeito) {
			this.dsDefeito = dsDefeito;
		}

		public String getQuantidadeCdrs() {
			return quantidadeCdrs;
		}

		public void setQuantidadeCdrs(String quantidadeCdrs) {
			this.quantidadeCdrs = quantidadeCdrs;
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
