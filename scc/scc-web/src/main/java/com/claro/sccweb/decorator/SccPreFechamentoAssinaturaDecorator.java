package com.claro.sccweb.decorator;

import com.claro.cobillingweb.persistence.entity.SccOperadora;
import com.claro.cobillingweb.persistence.entity.SccPreFechamentoAssinatura;
import com.claro.cobillingweb.persistence.entity.SccProdutoPrepago;

/**
 * Decorator para exibir assinaturas pré-pago. Usado na tela de Demonstrativo de Repasse Pré-pago.
 *
 */
public class SccPreFechamentoAssinaturaDecorator extends BasicSccDecorator {

	private SccPreFechamentoAssinatura entity;
	private SccOperadora operadoraClaro;
	private SccOperadora operadoraLD;
	private SccProdutoPrepago produtoPrepago;
	
	private String periodo;
	

	
	public SccPreFechamentoAssinaturaDecorator(SccPreFechamentoAssinatura entity,SccOperadora operadoraClaro,SccOperadora operadoraLD)
	{
		this.entity = entity;
		this.operadoraClaro = operadoraClaro;
		this.operadoraLD = operadoraLD;
	}


	public SccPreFechamentoAssinatura getEntity() {
		return entity;
	}

	
	public SccOperadora getOperadoraClaro() {
		return operadoraClaro;
	}


	public void setOperadora(SccOperadora operadoraClaro) {
		this.operadoraClaro = operadoraClaro;
	}
	

	public SccOperadora getOperadoraLD() {
		return operadoraLD;
	}

	
	public void setOperadoraLD(SccOperadora operadoraLD) {
		this.operadoraLD = operadoraLD;
	}
	

	public String getOperadoraClaroDs() {
		return operadoraClaro.getDsOperadora();
	}


	public String getQtAssinaturas() {
		if ((entity.getQtAssinaturas() == null) || (entity.getQtAssinaturas().equals(0L)))
			return "0.0";
		return sccCurrencyFormat.format(entity.getQtAssinaturas());
	}


	public String getQtChamadas() {
		if ((entity.getQtCdrs() == null) || (entity.getQtCdrs().equals(0L)))
			return "0.0";
		return sccCurrencyFormat.format(entity.getQtCdrs());
	}


	public String getQtMinutos() {
		if ((entity.getQtMinutosConcedidos() == null) || (entity.getQtMinutosConcedidos().equals(0.0)))
			return "0.0";
		return sccCurrencyFormat.format(entity.getQtMinutosConcedidos());
	}


	public String getMinutosQueimados() {
		if ((entity.getQtMinutosUtilizados() == null) || (entity.getQtMinutosUtilizados().equals(0.0)))
			return "0.0";
		return sccCurrencyFormat.format(entity.getQtMinutosUtilizados());
	}


	public String getMinutosExpirados() {
		if ((entity.getQtMinutosExpirados() == null) || (entity.getQtMinutosExpirados().equals(0.0)))
			return "0.0";
		return  sccCurrencyFormat.format(entity.getQtMinutosExpirados());
	}


	public String getValorBruto() {
		if ((entity.getVlBrutoAssinatura() == null) || (entity.getVlBrutoAssinatura().equals(0.0)))
			return "0.0";
		return sccCurrencyFormat.format(entity.getVlBrutoAssinatura());
	}


	public String getValorLiquido() {
		if ((entity.getVlLiquidoAssinatura() == null) || (entity.getVlLiquidoAssinatura().equals(0.0)))
			return "0.0";
		return sccCurrencyFormat.format(entity.getVlLiquidoAssinatura());
	}


	public String getPisCofins() {
		Double valor = zeroIfNull(entity.getVlCofins())+zeroIfNull(entity.getVlPis());
		if (valor.equals(0.0))
			return "0.0";
		return sccCurrencyFormat.format(valor);
	}


	public String getIcmsRepassar() {
		if (entity.repassaICMS())
			{
			if ((entity.getVlIcms() == null) || (entity.getVlIcms().equals(0.0)))
				return "0.0";
			return sccCurrencyFormat.format(entity.getVlIcms());
			}
		return "0.0";
	}


	public String getIcmsNaoRepassado() {
		if (!entity.repassaICMS())
		{
		if ((entity.getVlIcms() == null) || (entity.getVlIcms().equals(0.0)))
			return "0.0";
		return sccCurrencyFormat.format(entity.getVlIcms());
		}
	return "0.0";
	}


	public String getValorRepassar() {
		if ((entity.getVlRepasse() == null) || (entity.getVlRepasse().equals(0.0)))
			return "0.0";
		return sccCurrencyFormat.format(entity.getVlRepasse());
	}


	public String getCusto() {
		if ((entity.getVlCusto() == null) || (entity.getVlCusto().equals(0.0)))
			return "0.0";
		return sccCurrencyFormat.format(entity.getVlCusto());
	}


	public String getIcmsDescontarMesAnt() {
		if (!entity.repassaICMS())
		{
		if ((entity.getVlIcmsAnt() == null) || (entity.getVlIcmsAnt().equals(0.0)))
			return "0.0";
		return sccCurrencyFormat.format(entity.getVlIcmsAnt());
		}
	return "0.0";
	}


	public String getIcmsRepassarMesAnt() {
		if (entity.repassaICMS())
		{
		if ((entity.getVlIcmsAnt() == null) || (entity.getVlIcmsAnt().equals(0.0)))
			return "0.0";
		return sccCurrencyFormat.format(entity.getVlIcmsAnt());
		}
	return "0.0";
	}


	public String getRepasse() {
		if ((entity.getVlRepasseFinal() == null) || (entity.getVlRepasseFinal().equals(0.0)))
			return "0.0";
		return sccCurrencyFormat.format(entity.getVlRepasseFinal());
	}
	
	public SccProdutoPrepago getProdutoPrepago() {
		return produtoPrepago;
	}

	public void setProdutoPrepago(SccProdutoPrepago produtoPrepago) {
		this.produtoPrepago = produtoPrepago;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	
	
}
