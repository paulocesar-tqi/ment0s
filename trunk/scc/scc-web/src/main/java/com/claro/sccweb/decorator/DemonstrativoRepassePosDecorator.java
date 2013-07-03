package com.claro.sccweb.decorator;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import com.claro.cobillingweb.persistence.entity.SccRepasse;



/**
 * Decorator para exibir o demonstrativo de repasse para um operadora/holding.
 *
 */
public class DemonstrativoRepassePosDecorator  extends BasicSccDecorator {

	private String descricao;	
	private Double bruto = null;
	private String cssClass;
	private SccRepasse repasse;
	
	
	/**
	 * Indica se esse item é o valor a ser repassado.
	 */
	private boolean valorBrutoRepasse;
	
	protected static NumberFormat decimalFormat = new DecimalFormat("#.##");
	protected static NumberFormat integerFormat = new DecimalFormat("#.##");
	static {
    	Locale locale = new Locale("pt","BR");
    	decimalFormat = NumberFormat.getInstance(locale);
    	decimalFormat.setMinimumFractionDigits(2);
    	decimalFormat.setMaximumFractionDigits(2);
    	integerFormat = NumberFormat.getInstance(locale);
    	integerFormat.setMinimumFractionDigits(0);
    	integerFormat.setMaximumFractionDigits(0);
    }
	
	public DemonstrativoRepassePosDecorator()
	{
		
	}
	
	
	public DemonstrativoRepassePosDecorator(String descricao)
	{
		setDescricao(descricao);
	}
	
	public DemonstrativoRepassePosDecorator(SccRepasse repasse)
	{
		this.repasse = repasse;
	}
	
	
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getChamadas() {
		if ((repasse == null) || (repasse.getQtCdrs() == null))
			return " ";
		else
		if ((repasse.getQtCdrs().equals(0.0)) || (repasse.getQtCdrs() == 0))
			return " ";
		else
			return integerFormat.format(repasse.getQtCdrs());		
	}
	
	
	public Long getChamadasAsLong(){
		if ((repasse == null) || (repasse.getQtCdrs() == null))
			return 0L;
		else
			return repasse.getQtCdrs();
	}
	
	public String getMinutos() {
		if ((repasse == null) || (repasse.getQtDuracaoTarifada() == null))
			return " ";
		else
		if (repasse.getQtDuracaoTarifada().equals(0.0))
			return " ";
		else
		return decimalFormat.format(repasse.getQtDuracaoTarifada());	
	}
	
	
	
	public String getLiquido() {
		if ((repasse == null) || (repasse.getVlLiquidoItemRepasse() == null))
			return " ";
		else
		if (repasse.getVlLiquidoItemRepasse().equals(0.0))
			return " ";
		else
			return decimalFormat.format(repasse.getVlLiquidoItemRepasse());
	}
	
	
	public Double getLiquidoAsDouble()
	{
		if (repasse == null)
			return trunc(0.00,2);
		return trunc(repasse.getVlLiquidoItemRepasse(),2);
	}
	
	
	/*public Double getMinutosAsDouble()
	{
		if (repasse == null)
			return 0.00;
		return repasse.getVlLiquidoItemRepasse();
	}*/
	
	
	
	public String getMinutosAsDouble()
	{
		String value = " ";
		if ((repasse == null) || (repasse.getVlLiquidoItemRepasse()== null))
			return value;
		else
		if (repasse.getVlLiquidoItemRepasse().equals(0.0))
			return value;
		else
		return formataDouble(repasse.getVlLiquidoItemRepasse());
	}
	
	public String getPis() {
		if ((repasse == null) || (repasse.getVlPis() == null))
			return " ";
		else
		if (repasse.getVlPis().equals(0.0))
			return " ";
		else
			return decimalFormat.format(repasse.getVlPis());
	}
	
	public Double getPisAsDouble()
	{		
		if (repasse == null)
			return 0.00;
		return repasse.getVlPis();
	}
	
	
	public String getCofins() {
		if ((repasse == null) || (repasse.getVlCofins() == null))
			return " ";
		else
		if (repasse.getVlCofins().equals(0.0))
			return " ";
		else
			return decimalFormat.format(repasse.getVlCofins());
	}
	
	public Double getCofinsAsDouble()
	{		
		if (repasse == null)
			return 0.00;
		return repasse.getVlCofins();
	}
	
	public String getIcms() {
		if ((repasse == null) || (repasse.getVlIcms() == null))
			return " ";
		else
		if (repasse.getVlIcms().equals(0.0))
			return " ";
		else
			return decimalFormat.format(repasse.getVlIcms());
	}
	
	public Double getIcmsAsDouble()
	{		
		if (repasse == null)
			return 0.00;
		return repasse.getVlIcms();
	}
	
	
	public String getIss() {
		if ((repasse == null) || (repasse.getVlIss() == null))
			return " ";
		else
		if (repasse.getVlIss().equals(0.0))
			return " ";
		else
			return decimalFormat.format(repasse.getVlIss());
	}
	
	public Double getIssAsDouble()
	{		
		if (repasse == null)
			return 0.00;
		return repasse.getVlIss();
	}
	
	
	public String getBruto() {
		if (bruto != null)
			return decimalFormat.format(bruto);
		if ((repasse == null) || (repasse.getVlBrutoItemRepasse() == null)) 
			return " ";
		else
		if ((repasse.getVlBrutoItemRepasse().equals(0.00)) || (repasse.getVlBrutoItemRepasse() == 0)) 
			return " ";
		else
			return decimalFormat.format(repasse.getVlBrutoItemRepasse());
	}
	
	public Double getBrutoAsDouble()
	{
		if (bruto != null)
			return bruto;
		else 
		if ((repasse == null) || (repasse.getVlBrutoItemRepasse() == null)) 
			return 0.00;
		else
			return repasse.getVlBrutoItemRepasse();
	}
	


	public String getCssClass() {
		return cssClass;
	}


	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}


	public SccRepasse getRepasse() {
		return repasse;
	}


	public void setRepasse(SccRepasse repasse) {
		this.repasse = repasse;
	}


	
	

	public void setBruto(Double bruto) {
		this.bruto = bruto;
	}


	public boolean isValorBrutoRepasse() {
		return valorBrutoRepasse;
	}


	public void setValorBrutoRepasse(boolean valorBrutoRepasse) {
		this.valorBrutoRepasse = valorBrutoRepasse;
	}

	
	
}
