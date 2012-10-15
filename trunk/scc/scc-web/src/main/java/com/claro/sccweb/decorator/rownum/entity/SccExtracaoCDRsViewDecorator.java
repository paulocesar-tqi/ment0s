/**
 * 
 */
package com.claro.sccweb.decorator.rownum.entity;

import com.claro.cobillingweb.persistence.view.SccExtracaoCDRsView;
import com.claro.sccweb.decorator.rownum.RownumDecorator;

/**
 * @author 92031709
 *
 */
public class SccExtracaoCDRsViewDecorator extends RownumDecorator<SccExtracaoCDRsView> {
	

	private SccExtracaoCDRsView entity;

	private String numeroDeA;
	private String numeroDeB;
	private String duracaoTarifada;
	private String dtChamada;
	private String statusChamada;
	private String motivoRejeicao;
	
	public SccExtracaoCDRsViewDecorator(SccExtracaoCDRsView entity,
			int rownum) {
		super(entity, rownum);
		formatarCampos(entity);
		
	}

	@Override
	protected boolean isDeleteEnabled() {
		return false;
	}

	
	private void formatarCampos(SccExtracaoCDRsView entity){

		this.setNumeroDeA(entity.getNumeroDeA());
		this.setNumeroDeB(entity.getNumeroDeB());
		this.setDuracaoTarifada(formataDouble(entity.getDuracaoTarifada()));
		this.setDtChamada(formataDate(entity.getDtChamada()));
		this.setStatusChamada(entity.getStatusChamada());
		this.setMotivoRejeicao(entity.getMotivoRejeicao());		
		
	}

	public SccExtracaoCDRsView getEntity() {
		return entity;
	}

	public void setEntity(SccExtracaoCDRsView entity) {
		this.entity = entity;
	}

	public String getNumeroDeA() {
		return numeroDeA;
	}

	public void setNumeroDeA(String numeroDeA) {
		this.numeroDeA = numeroDeA;
	}

	public String getNumeroDeB() {
		return numeroDeB;
	}

	public void setNumeroDeB(String numeroDeB) {
		this.numeroDeB = numeroDeB;
	}
	
	public String getDuracaoTarifada() {
		return duracaoTarifada;
	}

	public void setDuracaoTarifada(String duracaoTarifada) {
		this.duracaoTarifada = duracaoTarifada;
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
	
}
