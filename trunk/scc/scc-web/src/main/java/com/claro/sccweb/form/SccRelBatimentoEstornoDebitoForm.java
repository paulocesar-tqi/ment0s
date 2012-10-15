package com.claro.sccweb.form;

import com.claro.cobillingweb.persistence.view.SccRelBatimentoEstornoDebitoView;

/**
 * @author 92038883
 *
 */

public class SccRelBatimentoEstornoDebitoForm extends BaseForm {

		private SccRelBatimentoEstornoDebitoView entity;
		
		private String cdEOTLD;
		
		private String cdEOTClaro;
		
		private String cdStatusArquivo;
		
		private Integer mmCiclo;
		
		private Integer aaCiclo;

		public SccRelBatimentoEstornoDebitoView getEntity() {
			return entity;
		}

		public void setEntity(SccRelBatimentoEstornoDebitoView entity) {
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

		public String getCdStatusArquivo() {
			return cdStatusArquivo;
		}

		public void setCdStatusArquivo(String cdStatusArquivo) {
			this.cdStatusArquivo = cdStatusArquivo;
		}

		public Integer getMmCiclo() {
			return mmCiclo;
		}

		public void setMmCiclo(Integer mmCiclo) {
			this.mmCiclo = mmCiclo;
		}

		public Integer getAaCiclo() {
			return aaCiclo;
		}

		public void setAaCiclo(Integer aaCiclo) {
			this.aaCiclo = aaCiclo;
		}

}
