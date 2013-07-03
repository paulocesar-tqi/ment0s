package com.claro.sccweb.form;

import java.util.Date;

import com.claro.cobillingweb.persistence.view.SccPreRelatorioCriticasView;

public class SccPreRelatorioCriticasForm extends BaseForm {
		
		private SccPreRelatorioCriticasView entity;
		
		private String cdEOTLD;
		
		private String cdEOTClaro;
		
		private String cdStatus;
		
		private String cdMotivoRejeicao;
		
		private String cdDefeito;
		
		private Date dtInicioEvento;
		
		private Date dtFimEvento;
		
		
		
		
		public SccPreRelatorioCriticasView getEntity() {
			return entity;
		}

		public void setEntity(SccPreRelatorioCriticasView entity) {
			this.entity = entity;
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

		public String getCdStatus() {
			return cdStatus;
		}

		public void setCdStatus(String cdStatus) {
			this.cdStatus = cdStatus;
		}

		public String getCdMotivoRejeicao() {
			return cdMotivoRejeicao;
		}

		public void setCdMotivoRejeicao(String cdMotivoRejeicao) {
			this.cdMotivoRejeicao = cdMotivoRejeicao;
		}

		public String getCdDefeito() {
			return cdDefeito;
		}

		public void setCdDefeito(String cdDefeito) {
			this.cdDefeito = cdDefeito;
		}

		public Date getDtInicioEvento() {
			return dtInicioEvento;
		}

		public void setDtInicioEvento(Date dtInicioEvento) {
			this.dtInicioEvento = dtInicioEvento;
		}

		public Date getDtFimEvento() {
			return dtFimEvento;
		}

		public void setDtFimEvento(Date dtFimEvento) {
			this.dtFimEvento = dtFimEvento;
		}

}
