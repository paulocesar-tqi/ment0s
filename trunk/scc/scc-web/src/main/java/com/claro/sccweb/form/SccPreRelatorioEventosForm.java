package com.claro.sccweb.form;

import java.util.Date;

import com.claro.cobillingweb.persistence.view.SccPreRelatorioEventosView;

public class SccPreRelatorioEventosForm extends BaseForm {
		
		private SccPreRelatorioEventosView entity;
		
		private String cdEOTLD;
		
		private String cdEOTClaro;
		
		private String tpStatus;
		
		private Date dtInicioEvento;
		
		private Date dtFimEvento;
		
		
		
		
		public SccPreRelatorioEventosView getEntity() {
			return entity;
		}

		public void setEntity(SccPreRelatorioEventosView entity) {
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

		public String getTpStatus() {
			return tpStatus;
		}

		public void setTpStatus(String tpStatus) {
			this.tpStatus = tpStatus;
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
