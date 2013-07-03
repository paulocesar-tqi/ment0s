/**
 * 
 */
package com.claro.sccweb.decorator.rownum.entity;

import com.claro.cobillingweb.persistence.view.SccPrePagoCreditosView;
import com.claro.sccweb.decorator.rownum.RownumDecorator;

/**
 * @author 92031709
 *
 */
public class SccPrePagoCreditosViewDecorator extends RownumDecorator<SccPrePagoCreditosView> {
	

	private SccPrePagoCreditosView entity;

	private String operadoraLD;
	private String operadoraClaro;
	private String sqArquivo;
	private String noArquivo;
	private String tpCredito;
	private String cdStatus;
	private String dsCredito;
	private String dsStatus;
	private String vlCredito;
	private String dtCredito;
	private String sqCredito;
	private String numeroA;
	private String quantidade;
	private String minutosQueimados;
	private String minutosAjustados;
	
	public SccPrePagoCreditosViewDecorator(SccPrePagoCreditosView entity,
			int rownum) {
		super(entity, rownum);
		formatarCampos(entity);
		
	}

	@Override
	protected boolean isDeleteEnabled() {
		return false;
	}

	
	private void formatarCampos(SccPrePagoCreditosView entity){

		this.setOperadoraLD(entity.getOperadoraLD());
		this.setOperadoraClaro(entity.getOperadoraClaro());
		this.setSqArquivo(formataInteger(entity.getSqArquivo()));
		this.setNoArquivo(entity.getNoArquivo());
		this.setTpCredito(entity.getTpCredito());
		this.setCdStatus(entity.getCdStatus());
		this.setDsCredito(entity.getDsCredito());
		this.setDsStatus(entity.getDsStatus());
		this.setVlCredito(formataDouble(entity.getVlCredito()));
		this.setDtCredito(formataDate(entity.getDtCredito()));
		this.setSqCredito(formataInteger(entity.getSqCredito()));
		this.setNumeroA(entity.getNumeroA());
		this.setQuantidade(formataInteger(entity.getQuantidade()));
		this.setMinutosQueimados(formataInteger(entity.getMinutosQueimados()));
		this.setMinutosAjustados(formataInteger(entity.getMinutosAjustados()));
	}
	
	public SccPrePagoCreditosView getEntity() {
		return entity;
	}

	public void setEntity(SccPrePagoCreditosView entity) {
		this.entity = entity;
	}

	public String getOperadoraLD() {
		return operadoraLD;
	}

	public void setOperadoraLD(String operadoraLD) {
		this.operadoraLD = operadoraLD;
	}

	public String getOperadoraClaro() {
		return operadoraClaro;
	}

	public void setOperadoraClaro(String operadoraClaro) {
		this.operadoraClaro = operadoraClaro;
	}

	public String getSqArquivo() {
		return sqArquivo;
	}

	public void setSqArquivo(String sqArquivo) {
		this.sqArquivo = sqArquivo;
	}

	public String getNoArquivo() {
		return noArquivo;
	}

	public void setNoArquivo(String noArquivo) {
		this.noArquivo = noArquivo;
	}

	public String getTpCredito() {
		return tpCredito;
	}

	public void setTpCredito(String tpCredito) {
		this.tpCredito = tpCredito;
	}

	public String getCdStatus() {
		return cdStatus;
	}

	public void setCdStatus(String cdStatus) {
		this.cdStatus = cdStatus;
	}

	public String getDsCredito() {
		return dsCredito;
	}

	public void setDsCredito(String dsCredito) {
		this.dsCredito = dsCredito;
	}

	public String getDsStatus() {
		return dsStatus;
	}

	public void setDsStatus(String dsStatus) {
		this.dsStatus = dsStatus;
	}

	public String getVlCredito() {
		return vlCredito;
	}

	public void setVlCredito(String vlCredito) {
		this.vlCredito = vlCredito;
	}

	public String getDtCredito() {
		return dtCredito;
	}

	public void setDtCredito(String dtCredito) {
		this.dtCredito = dtCredito;
	}

	public String getSqCredito() {
		return sqCredito;
	}

	public void setSqCredito(String sqCredito) {
		this.sqCredito = sqCredito;
	}

	public String getNumeroA() {
		return numeroA;
	}

	public void setNumeroA(String numeroA) {
		this.numeroA = numeroA;
	}

	public String getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}

	public String getMinutosQueimados() {
		return minutosQueimados;
	}

	public void setMinutosQueimados(String minutosQueimados) {
		this.minutosQueimados = minutosQueimados;
	}

	public String getMinutosAjustados() {
		return minutosAjustados;
	}

	public void setMinutosAjustados(String minutosAjustados) {
		this.minutosAjustados = minutosAjustados;
	}
}
