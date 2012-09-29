/**
 * 
 */
package com.claro.sccweb.decorator.rownum.entity;

import java.text.SimpleDateFormat;

import com.claro.cobillingweb.persistence.view.SccRelatorioConciliacaoView;
import com.claro.sccweb.decorator.rownum.RownumDecorator;

/**
 * @author 93046251
 *
 */
public class RelatorioConciliacaoPreDecorator extends RownumDecorator<SccRelatorioConciliacaoView> {
	

	private SccRelatorioConciliacaoView entity;
	
	private String dataLancamento;
	private String descricao;
	private String contaCredito;
	private String contaDebito;
	private String operadoraLd;
	private String localNegocio;
	private String empresaContabil;
	private String valorBruto;

	public RelatorioConciliacaoPreDecorator(SccRelatorioConciliacaoView entity,
			int rownum) {
		super(entity, rownum);
		formatarCampos(entity);
		
	}

	@Override
	protected boolean isDeleteEnabled() {
		return false;
	}

	
	private void formatarCampos(SccRelatorioConciliacaoView entity){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");
		this.setDataLancamento(sdf.format(entity.getDataLancamento()));
		this.setDescricao(formataString(entity.getDescricao()));
		this.setContaCredito(formataLong(entity.getContaCredito()));
		this.setContaDebito(formataLong(entity.getContaDebito()));
		this.setOperadoraLd(formataString(entity.getCodCsp()));
		this.setLocalNegocio(formataString(entity.getLocalNegocio()));
		this.setEmpresaContabil(formataString(entity.getEmpresaContabil()));
		this.setValorBruto(formataDouble(entity.getValorBruto()));
	}

	
	public SccRelatorioConciliacaoView getEntity() {
		return entity;
	}

	public void setEntity(SccRelatorioConciliacaoView entity) {
		this.entity = entity;
	}

	public String getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(String dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getContaCredito() {
		return contaCredito;
	}

	public void setContaCredito(String contaCredito) {
		this.contaCredito = contaCredito;
	}

	public String getContaDebito() {
		return contaDebito;
	}

	public void setContaDebito(String contaDebito) {
		this.contaDebito = contaDebito;
	}

	public String getOperadoraLd() {
		return operadoraLd;
	}

	public void setOperadoraLd(String operadoraLd) {
		this.operadoraLd = operadoraLd;
	}

	public String getLocalNegocio() {
		return localNegocio;
	}

	public void setLocalNegocio(String localNegocio) {
		this.localNegocio = localNegocio;
	}

	public String getEmpresaContabil() {
		return empresaContabil;
	}

	public void setEmpresaContabil(String empresaContabil) {
		this.empresaContabil = empresaContabil;
	}

	public String getValorBruto() {
		return valorBruto;
	}

	public void setValorBruto(String valorBruto) {
		this.valorBruto = valorBruto;
	}

	

}
