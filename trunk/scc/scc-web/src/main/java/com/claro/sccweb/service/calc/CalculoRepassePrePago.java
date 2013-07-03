package com.claro.sccweb.service.calc;

import com.claro.cobillingweb.persistence.entity.SccPreFechamento;
import com.claro.sccweb.decorator.DemonstrativoRepassePreItemDecorator;


/**
 * O demonstrativo de repasse para pré-pago é gerado através de cálculos aplicados
 * a {@link SccPreFechamento}. Essa classe possui as fórmulas de cálculos
 * para todos os ítens.
 * Cada item do demonstrativo é representado por um método que retorna um {@link DemonstrativoRepassePreItemDecorator}
 * e recebe {@link SccPreFechamento}.
 *
 */
public class CalculoRepassePrePago {
	 
	private Double valorFinal = 0.0;
	private Double valorPenalidades = 0.0;
	private Double valorTotalDescontos = 0.0;
	private Double valorTotalAcertos = 0.0;
	private Double valorICMSDescontar = 0.0;
	private Double valorICMSRepassar = 0.0;
	private SccPreFechamento preFechamento;
	
	
		
	public CalculoRepassePrePago(SccPreFechamento preFechamento)
	{
		this.valorFinal = zeroIfNull(preFechamento.getVlBrutoChamada())+zeroIfNull(preFechamento.getVlBrutoChamadaOm())-((zeroIfNull(preFechamento.getVlIcms())+zeroIfNull(preFechamento.getVlIcmsOm())));
		this.preFechamento = preFechamento;
		valorPenalidades = zeroIfNull(preFechamento.getVlPenalMinPerd())+zeroIfNull(preFechamento.getVlMultasClaro())+zeroIfNull(preFechamento.getVlMultasLd());
		valorTotalAcertos = zeroIfNull(preFechamento.getVlAcertoClaro())+zeroIfNull(preFechamento.getVlAcertoLd());
		if (preFechamento.repassarICMS())
			{
			valorTotalDescontos = zeroIfNull(preFechamento.getVlServPrestBruto())+zeroIfNull(preFechamento.getVlCreditoAut())+zeroIfNull(preFechamento.getVlCredito226());	
			}
		else
			{
			valorTotalDescontos = zeroIfNull(preFechamento.getVlServPrestBruto())+zeroIfNull(preFechamento.getVlCreditoAut())+zeroIfNull(preFechamento.getVlCredito226())-zeroIfNull(preFechamento.getVlIcms226());
			}
		
		if (preFechamento.repassarICMS())
			{
			valorICMSDescontar = zeroIfNull(preFechamento.getVlIcms())+zeroIfNull(preFechamento.getVlIcmsOm());
			}
		if (preFechamento.repassarICMS())
			{
			valorICMSRepassar = zeroIfNull(preFechamento.getVlIcmsAnt());
			}
	}
	
	public DemonstrativoRepassePreItemDecorator getTotalDeducoes()
	{
		DemonstrativoRepassePreItemDecorator item = new DemonstrativoRepassePreItemDecorator();
		item.setDescricao("2-TOTAL DAS DEDUÇÕES");
		item.setValorRepassar(valorTotalDescontos);
		return item;
	}
	
	public DemonstrativoRepassePreItemDecorator getSubTitulo(String texto)
	{
		DemonstrativoRepassePreItemDecorator item = new DemonstrativoRepassePreItemDecorator();
		item.setDescricao(texto);
		return item;
	}
	
	public DemonstrativoRepassePreItemDecorator getTotalPenalidades()
	{
		DemonstrativoRepassePreItemDecorator item = new DemonstrativoRepassePreItemDecorator();
		item.setDescricao("3-TOTAL DAS PENALIDADES");		
		return item;
	}
	
	public DemonstrativoRepassePreItemDecorator getTotalAcertos()
	{
		DemonstrativoRepassePreItemDecorator item = new DemonstrativoRepassePreItemDecorator();
		item.setDescricao("4-TOTAL DOS ACERTOS");		
		return item;
	}
	
	public DemonstrativoRepassePreItemDecorator getTotalValorApurado()
	{
		DemonstrativoRepassePreItemDecorator item = new DemonstrativoRepassePreItemDecorator();	
		item.setDescricao("1-TOTAL DO VALOR APURADO");
		item.setQuantidadeChamandas(zeroIfNull(preFechamento.getQtCdrs())+zeroIfNull(preFechamento.getQtCdrsOm()));		
		item.setQuantidadeMinutos(zeroIfNull(preFechamento.getQtDuracaoTarifada())+zeroIfNull(preFechamento.getQtDuracaoTarifadaOm()));
		item.setValorBruto(zeroIfNull(preFechamento.getVlBrutoChamada())+zeroIfNull(preFechamento.getVlBrutoChamadaOm()));
		if (preFechamento.repassarICMS())
			item.setIcmsRepassar(zeroIfNull(preFechamento.getVlIcms())+zeroIfNull(preFechamento.getVlIcmsOm()));
		else
			item.setIcmsNaoRepassado(zeroIfNull(preFechamento.getVlIcms())+zeroIfNull(preFechamento.getVlIcmsOm()));
		item.setPisCofins(zeroIfNull(preFechamento.getVlPis())+zeroIfNull(preFechamento.getVlCofins())+zeroIfNull(preFechamento.getVlPisOm())+zeroIfNull(preFechamento.getVlCofinsOm()));
		item.setValorRepassar(zeroIfNull(item.getValorBruto())-zeroIfNull(item.getIcmsNaoRepassado()));
		item.setValorLiquido(zeroIfNull(item.getValorRepassar())-zeroIfNull(item.getIcmsRepassar())-zeroIfNull(item.getPisCofins()));
		return item;
	}
	
	public DemonstrativoRepassePreItemDecorator getValorChamadasApuradasMesAnterior()
	{
		DemonstrativoRepassePreItemDecorator item = new DemonstrativoRepassePreItemDecorator();
		item.setDescricao(" 1.1 VALOR CHAMADAS APURADAS MÊS ANTERIOR");
		item.setQuantidadeChamandas(zeroIfNull(preFechamento.getQtCdrs()));
		item.setQuantidadeMinutos(zeroIfNull(preFechamento.getQtDuracaoTarifada()));
		item.setValorBruto(zeroIfNull(preFechamento.getVlBrutoChamada()));
		item.setPisCofins(zeroIfNull(preFechamento.getVlPis())+zeroIfNull(preFechamento.getVlCofins()));
		if (preFechamento.repassarICMS())
			item.setIcmsRepassar(zeroIfNull(preFechamento.getVlIcms()));
		else
			item.setIcmsNaoRepassado(zeroIfNull(preFechamento.getVlIcms()));		
		item.setValorRepassar(zeroIfNull(preFechamento.getVlBrutoChamada())-zeroIfNull(item.getIcmsNaoRepassado()));
		item.setValorLiquido(zeroIfNull(item.getValorRepassar())-zeroIfNull(item.getPisCofins())-zeroIfNull(item.getIcmsRepassar()));
		return item;
	}
	
	public DemonstrativoRepassePreItemDecorator getValorChamadasApuradasOutrosMeses()
	{		
		DemonstrativoRepassePreItemDecorator item = new DemonstrativoRepassePreItemDecorator();
		item.setDescricao("1.2 VALOR CHAMADAS APURADAS OUTROS MESES");
		item.setQuantidadeChamandas(zeroIfNull(preFechamento.getQtCdrsOm()));
		item.setQuantidadeMinutos(zeroIfNull(preFechamento.getQtDuracaoTarifadaOm()));
		item.setValorBruto(zeroIfNull(preFechamento.getVlBrutoChamadaOm()));
		if (preFechamento.repassarICMS())
			item.setIcmsRepassar(zeroIfNull(preFechamento.getVlIcmsOm()));
		else
			item.setIcmsNaoRepassado(zeroIfNull(preFechamento.getVlIcmsOm()));
		item.setPisCofins(zeroIfNull(preFechamento.getVlPisOm())+zeroIfNull(preFechamento.getVlCofinsOm()));
		item.setValorRepassar(zeroIfNull(item.getValorBruto())-zeroIfNull(item.getIcmsNaoRepassado()));
		item.setValorLiquido(zeroIfNull(item.getValorRepassar())-zeroIfNull(item.getPisCofins())-zeroIfNull(item.getIcmsRepassar()));
		return item;
	}
	
	public DemonstrativoRepassePreItemDecorator getUtilizacaoPlataformaSimplificado(){
		DemonstrativoRepassePreItemDecorator item = new DemonstrativoRepassePreItemDecorator();
		item.setDescricao(" 2.1 SERVIÇO PRESTADO (UTILIZAÇÃO DA PLATAFORMA)");		
		item.setQuantidadeChamandas(zeroIfNull(preFechamento.getQtCdrs())+zeroIfNull(preFechamento.getQtCdrsOm()));
		item.setQuantidadeMinutos(zeroIfNull(preFechamento.getQtDuracaoTarifada())+(zeroIfNull(preFechamento.getQtDuracaoTarifadaOm())));
		item.setValorBruto(zeroIfNull(preFechamento.getVlServPrestBruto()));
		item.setPisCofins(zeroIfNull(preFechamento.getVlServPrestCofins())+zeroIfNull(preFechamento.getVlServPrestPis()));
		item.setIss(zeroIfNull(preFechamento.getVlServPrestIss()));
		item.setValorLiquido(zeroIfNull(item.getValorBruto())-zeroIfNull((item.getPisCofins()+item.getIss())));
		item.setValorRepassar(preFechamento.getVlServPrestBruto());
		return item;

	}
	
	public DemonstrativoRepassePreItemDecorator getUtilizacaoPlataforma()
	{
		DemonstrativoRepassePreItemDecorator item = new DemonstrativoRepassePreItemDecorator();
		item.setDescricao(" 2.1 SERVIÇO PRESTADO (UTILIZAÇÃO DA PLATAFORMA)");		
		if (preFechamento.getDsCriterioCusto() != null)
			{
			if (preFechamento.getDsCriterioCusto().equals(SccPreFechamento.CRITERIO_CHAMADAS))
				item.setQuantidadeChamandas(zeroIfNull(preFechamento.getQtCdrs())+zeroIfNull(preFechamento.getQtCdrsOm()));
			else if (preFechamento.getDsCriterioCusto().equals(SccPreFechamento.CRITERIO_MINUTO))
				item.setQuantidadeMinutos(zeroIfNull(preFechamento.getQtDuracaoTarifada())+(zeroIfNull(preFechamento.getQtDuracaoTarifadaOm())));
				
			}
		item.setValorBruto(zeroIfNull(preFechamento.getVlServPrestBruto()));
		item.setPisCofins(zeroIfNull(preFechamento.getVlServPrestCofins())+zeroIfNull(preFechamento.getVlServPrestPis()));
		item.setIss(zeroIfNull(preFechamento.getVlServPrestIss()));
		item.setValorLiquido(zeroIfNull(item.getValorBruto())-zeroIfNull((item.getPisCofins()+item.getIss())));
		item.setValorRepassar(preFechamento.getVlServPrestBruto());
		return item;
	}
	
	public DemonstrativoRepassePreItemDecorator getCreditosAutorizadosLD()
	{
		DemonstrativoRepassePreItemDecorator item = new DemonstrativoRepassePreItemDecorator();
		item.setDescricao("  2.2 CRÉDITOS AUTORIZADOS PELA PRESTADORA LD");
		item.setValorRepassar(zeroIfNull(preFechamento.getVlCreditoAut()));
		return item;
	}
	
	public DemonstrativoRepassePreItemDecorator getANATEL226()
	{
		DemonstrativoRepassePreItemDecorator item = new DemonstrativoRepassePreItemDecorator();
		item.setDescricao("  2.3 CRÉDITOS - ANATEL 226");
		if (!preFechamento.repassarICMS())
			item.setValorRepassar(zeroIfNull(preFechamento.getVlCredito226())-zeroIfNull(preFechamento.getVlIcms226()));
		else
			item.setValorRepassar(zeroIfNull(preFechamento.getVlCredito226()));
		item.setValorLiquido(zeroIfNull(preFechamento.getVlLiquido226()));
		item.setValorBruto(zeroIfNull(preFechamento.getVlCredito226()));
				
		if (preFechamento.repassarICMS())
			item.setIcmsRepassar(zeroIfNull(preFechamento.getVlIcms226()));
		else
			item.setIcmsNaoRepassado(zeroIfNull(preFechamento.getVlIcms226()));
		
		
		item.setPisCofins(zeroIfNull(preFechamento.getVlPisCofins226()));
		item.setQuantidadeChamandas(preFechamento.getQtCdrs226());		
		return item;
	}
	
	public DemonstrativoRepassePreItemDecorator getPenalidadesMinutosContraClaro()
	{
		DemonstrativoRepassePreItemDecorator item = new DemonstrativoRepassePreItemDecorator();
		item.setDescricao("  3.1 PENALIDADE RELATIVA A MINUTOS PERDIDOS CONTRA CLARO");
		item.setValorRepassar(zeroIfNull(preFechamento.getVlPenalMinPerd()));
		return item;
	}
	
	
	public DemonstrativoRepassePreItemDecorator getMultasAtrasoContraClaro()
	{
		DemonstrativoRepassePreItemDecorator item = new DemonstrativoRepassePreItemDecorator();
		item.setDescricao("  3.2 MULTAS/JUROS POR ATRASO DE PAGAMENTO CONTRA CLARO");
		item.setValorRepassar(zeroIfNull(preFechamento.getVlMultasClaro()));
		return item;
	}
	
	public DemonstrativoRepassePreItemDecorator getMultasAtrasoContraLD()
	{
		DemonstrativoRepassePreItemDecorator item = new DemonstrativoRepassePreItemDecorator();
		item.setDescricao("  3.3 MULTAS/JUROS POR ATRASO DE PAGAMENTO CONTRA PRESTADORA LD");
		item.setValorRepassar(zeroIfNull(preFechamento.getVlMultasLd()));
		return item;
	}
	
	public DemonstrativoRepassePreItemDecorator getConciliacaoContraClaro()
	{
		DemonstrativoRepassePreItemDecorator item = new DemonstrativoRepassePreItemDecorator();
		item.setDescricao("  4.1 ACERTO DE CONCILIAÇÃO CONTRA CLARO");
		item.setValorRepassar(zeroIfNull(preFechamento.getVlAcertoClaro()));
		return item;
	}
	
	public DemonstrativoRepassePreItemDecorator getConciliacaoContraLD()
	{
		DemonstrativoRepassePreItemDecorator item = new DemonstrativoRepassePreItemDecorator();
		item.setDescricao(" 4.2 ACERTO DE CONCILIAÇÃO CONTRA PRESTADORA LD");
		item.setValorRepassar(zeroIfNull(preFechamento.getVlAcertoLd()));		
		return item;
	}
	
	public DemonstrativoRepassePreItemDecorator getCPMF()
	{
		DemonstrativoRepassePreItemDecorator item = new DemonstrativoRepassePreItemDecorator();
		item.setDescricao("5-CPMF A DESCONTAR (BASE DE CÁLCULO: 1 ± 2 ± 3 ± 4)");
		return item;
	}
	
	public DemonstrativoRepassePreItemDecorator getICMSDescontar()
	{
		DemonstrativoRepassePreItemDecorator item = new DemonstrativoRepassePreItemDecorator();
		item.setDescricao("6-ICMS A DESCONTAR (Repasse Próximo Mês)");
		if (preFechamento.repassarICMS())
			{
			valorICMSDescontar = zeroIfNull(preFechamento.getVlIcms())+zeroIfNull(preFechamento.getVlIcmsOm());
			item.setValorRepassar(valorICMSDescontar);
			}
		return item;
	}
	
	public DemonstrativoRepassePreItemDecorator getICMSRepassar()
	{
		DemonstrativoRepassePreItemDecorator item = new DemonstrativoRepassePreItemDecorator();
		item.setDescricao("7-ICMS A REPASSAR (Descontado Mês Anterior)");		
		if (preFechamento.repassarICMS())
			{
			valorICMSRepassar = zeroIfNull(preFechamento.getVlIcmsAnt());
			item.setValorRepassar(valorICMSRepassar);
			}					
		return item;
	}
	
	public DemonstrativoRepassePreItemDecorator getValorFinal()
	{
		DemonstrativoRepassePreItemDecorator item = new DemonstrativoRepassePreItemDecorator();
		item.setDescricao("8-VALOR FINAL A REPASSAR");
			item.setValorRepassar(valorFinal-valorTotalDescontos+valorPenalidades+valorTotalAcertos+valorICMSRepassar);// retirado para bater com aplicação antiga -valorICMSDescontar);
		return item;
	}
	
	private Double zeroIfNull(Double value)
	{
		if (value == null)
			return 0.00;
		return value;
	}
}
