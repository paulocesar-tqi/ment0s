package com.claro.sccweb.decorator;

import com.claro.cobillingweb.persistence.entity.SccOperadora;
import com.claro.cobillingweb.persistence.entity.SccPreFechamentoAssinatura;
import com.claro.cobillingweb.persistence.entity.SccProdutoPrepago;

/**
 * Decorator para exibir assinaturas pré-pago. Usado na tela de Demonstrativo de Repasse Pré-pago.
 *
 */
public class SccPreFechamentoAssinaturaDecorator extends BasicSccDecorator {

	public static enum Tipo {
		TOTAL,
		MES_ANTERIOR,
		OUTROS_MESES
	}
	
	private SccPreFechamentoAssinatura entity;
	private SccOperadora operadoraClaro;
	private SccOperadora operadoraLD;
	private SccProdutoPrepago produtoPrepago;
	
	private String periodo;
	private Tipo tipo;

	
	public SccPreFechamentoAssinaturaDecorator(SccPreFechamentoAssinatura entity,SccOperadora operadoraClaro,SccOperadora operadoraLD, Tipo tipo)
	{
		this.entity = entity;
		this.operadoraClaro = operadoraClaro;
		this.operadoraLD = operadoraLD;
		this.tipo = tipo;
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
		String ds = operadoraClaro.getDsOperadora();
		switch (tipo) {
		case TOTAL:
			ds += " - TOTAL DO VALOR APURADO";
			break;
		case MES_ANTERIOR:
			ds += " - VALOR ASSINATURAS APURADAS MÊS ANTERIOR";
			break;
		case OUTROS_MESES:
			ds += " - VALOR ASSINATURAS APURADAS OUTROS MESES";
			break;
		}	
		return ds;
	}


	public String getQtAssinaturas() {
		Long val1 = (entity.getQtAssinaturas() == null) || (entity.getQtAssinaturas().equals(0L)) ? 0L : entity.getQtAssinaturas();
		Long val2 = (entity.getQtdAssinaturasOm() == null) || (entity.getQtdAssinaturasOm().equals(0L)) ? 0L : entity.getQtdAssinaturasOm();
		Long val = 0L;
		switch (tipo) {
		case TOTAL:
			val = val1 + val2;
			break;
		case MES_ANTERIOR:
			val = val1;
			break;
		case OUTROS_MESES:
			val = val2;
		}
		if (val.equals(0L))
			return "0.0";
		return sccCurrencyFormat.format(val);
	}


	public String getQtChamadas() {
		if ((entity.getQtCdrs() == null) || (entity.getQtCdrs().equals(0L)))
			return "0.0";
		Long val1 = (entity.getQtCdrs() == null) || (entity.getQtCdrs().equals(0L)) ? 0L : entity.getQtCdrs();
		Long val2 = (entity.getQtCdrsOm() == null) || (entity.getQtCdrsOm().equals(0L)) ? 0L : entity.getQtCdrsOm();
		Long val = 0L;
		switch (tipo) {
		case TOTAL:
			val = val1 + val2;
			break;
		case MES_ANTERIOR:
			val = val1;
			break;
		case OUTROS_MESES:
			val = val2;
		}
		if (val.equals(0L))
			return "0.0";
		return sccCurrencyFormat.format(val);
	}


	public String getQtMinutos() {
		Double val1 = zeroIfNull(entity.getQtMinutosConcedidos());
		Double val2 = zeroIfNull(entity.getQtMinutosConcedidosOm());
		Double val = 0.0;
		switch (tipo) {
		case TOTAL:
			val = val1 + val2;
			break;
		case MES_ANTERIOR:
			val = val1;
			break;
		case OUTROS_MESES:
			val = val2;
		}
		if (val.equals(0.0))
			return "0.0";
		return sccCurrencyFormat.format(val);
	}


	public String getMinutosQueimados() {
		Double val1 = zeroIfNull(entity.getQtMinutosUtilizados());
		Double val2 = zeroIfNull(entity.getQtMinutosUtilizadosOm());
		Double val = 0.0;
		switch (tipo) {
		case TOTAL:
			val = val1 + val2;
			break;
		case MES_ANTERIOR:
			val = val1;
			break;
		case OUTROS_MESES:
			val = val2;
		}
		if (val.equals(0.0))
			return "0.0";
		return sccCurrencyFormat.format(val);
	}


	public String getMinutosExpirados() {
		Double val1 = zeroIfNull(entity.getQtMinutosExpirados());
		Double val2 = zeroIfNull(entity.getQtMinutosExpiradosOm());
		Double val = 0.0;
		switch (tipo) {
		case TOTAL:
			val = val1 + val2;
			break;
		case MES_ANTERIOR:
			val = val1;
			break;
		case OUTROS_MESES:
			val = val2;
		}
		if (val.equals(0.0))
			return "0.0";
		return sccCurrencyFormat.format(val);
	}


	public String getValorBruto() {
		Double val1 = zeroIfNull(entity.getVlBrutoAssinatura());
		Double val2 = zeroIfNull(entity.getVlBrutoAssinaturaOm());
		Double val = 0.0;
		switch (tipo) {
		case TOTAL:
			val = val1 + val2;
			break;
		case MES_ANTERIOR:
			val = val1;
			break;
		case OUTROS_MESES:
			val = val2;
		}
		if (val.equals(0.0))
			return "0.0";
		return sccCurrencyFormat.format(val);
	}


	public String getValorLiquido() {
		Double val1 = zeroIfNull(entity.getVlLiquidoAssinatura());
		Double val2 = zeroIfNull(entity.getVlLiquidoAssinaturaOm());
		Double val = 0.0;
		switch (tipo) {
		case TOTAL:
			val = val1 + val2;
			break;
		case MES_ANTERIOR:
			val = val1;
			break;
		case OUTROS_MESES:
			val = val2;
		}
		if (val.equals(0.0))
			return "0.0";
		return sccCurrencyFormat.format(val);
	}


	public String getPisCofins() {
		Double val1 = zeroIfNull(entity.getVlCofins()) + zeroIfNull(entity.getVlPis());
		Double val2 = zeroIfNull(entity.getVlCofinsOm()) + zeroIfNull(entity.getVlPisOm());
		Double val = 0.0;
		switch (tipo) {
		case TOTAL:
			val = val1 + val2;
			break;
		case MES_ANTERIOR:
			val = val1;
			break;
		case OUTROS_MESES:
			val = val2;
		}
		if (val.equals(0.0))
			return "0.0";
		return sccCurrencyFormat.format(val);
	}

	private String getValorIcms() {
		Double val1 = zeroIfNull(entity.getVlIcms());
		Double val2 = zeroIfNull(entity.getVlIcmsOm());
		Double val = 0.0;
		switch (tipo) {
		case TOTAL:
			val = val1 + val2;
			break;
		case MES_ANTERIOR:
			val = val1;
			break;
		case OUTROS_MESES:
			val = val2;
		}
		if (val.equals(0.0))
			return "0.0";
		return sccCurrencyFormat.format(val);
	}

	public String getIcmsRepassar() {
		if (entity.repassaICMS()) {
			return getValorIcms();
		}
		return "0.0";
	}


	public String getIcmsNaoRepassado() {
		if (!entity.repassaICMS()) {
			return getValorIcms();
		}
		return "0.0";
	}


	public String getValorRepassar() {
		Double val1 = zeroIfNull(entity.getVlRepasse());
		Double val2 = zeroIfNull(entity.getVlRepasseOm());
		Double val = 0.0;
		switch (tipo) {
		case TOTAL:
			val = val1 + val2;
			break;
		case MES_ANTERIOR:
			val = val1;
			break;
		case OUTROS_MESES:
			val = val2;
		}
		if (val.equals(0.0))
			return "0.0";
		return sccCurrencyFormat.format(val);
	}


	public String getCusto() {
		Double val1 = zeroIfNull(entity.getVlCusto());
		Double val2 = zeroIfNull(entity.getVlCustoOm());
		Double val = 0.0;
		switch (tipo) {
		case TOTAL:
			val = val1 + val2;
			break;
		case MES_ANTERIOR:
			val = val1;
			break;
		case OUTROS_MESES:
			val = val2;
		}
		if (val.equals(0.0))
			return "0.0";
		return sccCurrencyFormat.format(val);
	}

	private String getValorIcmsAnt() {
		Double val1 = zeroIfNull(entity.getVlIcmsAnt());
		Double val2 = zeroIfNull(entity.getVlIcmsAntOm());
		Double val = 0.0;
		switch (tipo) {
		case TOTAL:
			val = val1 + val2;
			break;
		case MES_ANTERIOR:
			val = val1;
			break;
		case OUTROS_MESES:
			val = val2;
		}
		if (val.equals(0.0))
			return "0.0";
		return sccCurrencyFormat.format(val);
	}

	public String getIcmsDescontarMesAnt() {
		if (!entity.repassaICMS()) {
			return getValorIcmsAnt();
		}
		return "0.0";
	}


	public String getIcmsRepassarMesAnt() {
		if (entity.repassaICMS()) {
			return getValorIcmsAnt();
		}
		return "0.0";
	}


	public String getRepasse() {
		Double val1 = zeroIfNull(entity.getVlRepasseFinal());
		Double val2 = zeroIfNull(entity.getVlRepasseFinalOm());
		Double val = 0.0;
		switch (tipo) {
		case TOTAL:
			val = val1 + val2;
			break;
		case MES_ANTERIOR:
			val = val1;
			break;
		case OUTROS_MESES:
			val = val2;
		}
		if (val.equals(0.0))
			return "0.0";
		return sccCurrencyFormat.format(val);
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
	
	public String getValorNotaFiscal() {
		String ds = operadoraClaro.getDsOperadora();
		if (ds.equals("Consolidado")) return "";
		return getValorRepassar();
	}
	
	public String getValorDestaqueIcms() {
		String ds = operadoraClaro.getDsOperadora();
		if (ds.equals("Consolidado")) return "";
		return getIcmsRepassar();
	}
	
	public String getPercIcms() {
		String ds = operadoraClaro.getDsOperadora();
		if (ds.equals("Consolidado")) return "";
		String vlNota = getValorNotaFiscal();
		if (vlNota.equals("0.0"))
			return "0.0";
		
		try {
			Number vNf = sccCurrencyFormat.parse(vlNota);
			Number vIcms = sccCurrencyFormat.parse(getValorDestaqueIcms());
			
			if (vIcms.doubleValue() == 0.0)
				return "0%";
			
			Double val = vIcms.doubleValue() / vNf.doubleValue();			
			return sccCurrencyFormat.format(val * 100) + "%";
		} catch (Exception ex) {
			return "ERRO";
		}
	}
}
