/**
 * 
 */
package com.claro.sccweb.decorator.rownum.entity;

import com.claro.cobillingweb.persistence.view.SccChamadasRefaturamentoView;
import com.claro.sccweb.decorator.rownum.RownumDecorator;

/**
 * @author 92031709
 *
 */
public class SccChamadasRefaturamentoViewDecorator extends RownumDecorator<SccChamadasRefaturamentoView> {
	

	private SccChamadasRefaturamentoView entity;

	private String mesReferencia;
	private String operadoraLD;
	private String operadoraClaro;
	private String tipoRefaturamento;
	private String statusChamada;
	private String quantidade;
	private String minutagem;
	private String totalBruto;
	private String totalLiquido;

	public SccChamadasRefaturamentoViewDecorator(SccChamadasRefaturamentoView entity,
			int rownum) {
		super(entity, rownum);
		formatarCampos(entity);
		
	}

	@Override
	protected boolean isDeleteEnabled() {
		return false;
	}

	
	private void formatarCampos(SccChamadasRefaturamentoView entity){

		this.setMesReferencia(entity.getMesReferencia());
		this.setOperadoraLD(entity.getOperadoraLD());
		this.setOperadoraClaro(entity.getOperadoraClaro());
		this.setTipoRefaturamento(entity.getTipoRefaturamento());
		this.setStatusChamada(entity.getStatusChamada());
		this.setQuantidade(formataInteger(entity.getQuantidade()));
		this.setMinutagem(formataInteger(entity.getMinutagem()));
		this.setTotalBruto(formataDouble(entity.getTotalBruto()));
		this.setTotalLiquido(formataDouble(entity.getTotalLiquido()));		
	}

	public SccChamadasRefaturamentoView getEntity() {
		return entity;
	}

	public void setEntity(SccChamadasRefaturamentoView entity) {
		this.entity = entity;
	}

	public String getMesReferencia() {
		return mesReferencia;
	}

	public void setMesReferencia(String mesReferencia) {
		this.mesReferencia = mesReferencia;
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

	public String getTipoRefaturamento() {
		return tipoRefaturamento;
	}

	public void setTipoRefaturamento(String tipoRefaturamento) {
		this.tipoRefaturamento = tipoRefaturamento;
	}

	public String getStatusChamada() {
		return statusChamada;
	}

	public void setStatusChamada(String statusChamada) {
		this.statusChamada = statusChamada;
	}

	public String getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}

	public String getMinutagem() {
		return minutagem;
	}

	public void setMinutagem(String minutagem) {
		this.minutagem = minutagem;
	}

	public String getTotalBruto() {
		return totalBruto;
	}

	public void setTotalBruto(String totalBruto) {
		this.totalBruto = totalBruto;
	}

	public String getTotalLiquido() {
		return totalLiquido;
	}

	public void setTotalLiquido(String totalLiquido) {
		this.totalLiquido = totalLiquido;
	}
}
