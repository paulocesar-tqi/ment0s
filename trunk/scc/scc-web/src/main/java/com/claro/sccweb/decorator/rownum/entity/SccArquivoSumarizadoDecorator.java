package com.claro.sccweb.decorator.rownum.entity;

import com.claro.cobillingweb.persistence.entity.SccArquivoSumarizado;
import com.claro.cobillingweb.persistence.entity.SccOperadora;
import com.claro.sccweb.decorator.rownum.RownumDecorator;

public class SccArquivoSumarizadoDecorator extends RownumDecorator<SccArquivoSumarizado> {

	private SccOperadora operadoraClaro; 
	private SccOperadora operadoraLD;
	private Long totalCDRs;
	private Long totalRejeitados;
	
	private Long totalAlocado;
	private Long totalFaturado;
	private Long totalEncaminhado;
	
	
	
	public SccArquivoSumarizadoDecorator(SccArquivoSumarizado entity, int rownum) {
		super(entity, rownum);		
	}
	
	/**
	 * Usado pelo relatório de distribuição de CDRs.
	 * @param entity
	 * @param totalCDRs
	 * @param totalRejeitados
	 * @param rownum
	 */
	public SccArquivoSumarizadoDecorator(SccArquivoSumarizado entity, Long totalCDRs , Long totalRejeitados ,int rownum) {
		super(entity, rownum);		
		this.totalCDRs = totalCDRs;
		this.totalRejeitados = totalRejeitados;
	}

	public SccArquivoSumarizadoDecorator(SccArquivoSumarizado entity, SccOperadora operadoraClaro , SccOperadora operadoraLD ,int rownum) {
		super(entity, rownum);	
		this.operadoraClaro = operadoraClaro;
		this.operadoraLD = operadoraLD;
	}
	
	public String getInfoCiclo()
	{
		return getRow().getCdCiclo()+" "+getRow().getMmCiclo()+"/"+getRow().getAaCiclo();
	}
	
	public String getMesAno()
	{
		return getRow().getMmCiclo()+"/"+getRow().getAaCiclo();
	}
	
	public Long getAnoCiclo(){
		
		Long anoCiclo = null;
		if(getRow().getAaCiclo() != null) {
			anoCiclo = getRow().getAaCiclo();
		}
		return anoCiclo;
	}
	
	public Long getMesCiclo(){
		Long mes = null;
		if(getRow().getMmCiclo() != null){
			mes = getRow().getMmCiclo();
		}
		return mes;
	}
	
	public String getOperadoraClaro()
	{
		if (operadoraClaro != null)
			return operadoraClaro.getDsOperadora();
		else
			return getRow().getCdEotClaro();
	}
	
	public String getOperadoraLD()
	{
		if (operadoraLD != null)
			return operadoraLD.getDsOperadora();
		else
			return getRow().getCdEotLd();
	}
	
	public String getDsOperadora(){
		String dsOperadora = null;
		if(getRow().getDsOperadoraClaro() != null && !"".equals(getRow().getDsOperadoraClaro())){
			dsOperadora = getRow().getDsOperadoraClaro();
		}
		return dsOperadora;
	}
	
	public String getDsOperadoraLd(){
		String dsOperadora = null;
		if(getRow().getDsOperadoraLd() != null && !"".equals(getRow().getDsOperadoraLd())){
			dsOperadora = getRow().getDsOperadoraLd();
		}
		return dsOperadora;
	}
	
	
	public String getQuantidade()
	{
		if (getRow().getQtCdrs() == null)
			return "0";
		return formataLong(getRow().getQtCdrs());
	}
	
	
	public String getValorLiquido()
	{
		return formataDouble(getRow().getVlLiquidoChamada());
	}
	
	public String getValorBruto()
	{
		return formataDouble(getRow().getVlBrutoChamada());
	}
	
	public String getDuracaoTarifada()
	{
		return formataDouble(getRow().getQtDuracaoTarifada());
	}
	
	public String getStatusComposto()
	{
		return  getRow().getDsStatusCdr()+"("+getRow().getCdStatusCdr()+")";
	}
	
	public String getMetrica()
	{	
		
		if ((totalCDRs == null) || (totalRejeitados == null || (getRow().getQtCdrs() == null))) 
			{
			return "0";
			}
		else if (totalCDRs.longValue() == totalRejeitados.longValue())
			{
			return "0%";
			}
		else
			{
			double a = totalCDRs-totalRejeitados;
			double b = getRow().getQtCdrs();
			double c = b/a;
			c = c*100L;
			return formataDouble(c)+" %";
			}
	}
	
	
	public String getDsStatusCdr(){
		
		if(getRow().getDsStatusCdr() != null && !"".equals(getRow().getDsStatusCdr())){
			return getRow().getDsStatusCdr(); 
		}
		return "";
	}
	
	public String getDsProduto(){
		if(getRow().getDsProduto() != null && !"".equals(getRow().getDsProduto())){
			return getRow().getDsProduto(); 
		}
		return "";
	}
	
	public String getDtProcExterna(){
		
		return formataDate(getRow().getDtProcExterna());
	}
	
	
	public Long getCdCiclo(){
		
		Long cdCiclo = null;
		if(getRow().getCdCiclo() != null){
			cdCiclo = getRow().getCdCiclo();
		}
		
		return cdCiclo;
	}
	
		
	 
	protected boolean isDeleteEnabled() {
		return false;
	}

	public Long getTotalCDRs() {
		return totalCDRs;
	}

	public void setTotalCDRs(Long totalCDRs) {
		this.totalCDRs = totalCDRs;
	}

	public Long getTotalAlocado() {
		return totalAlocado;
	}

	public void setTotalAlocado(Long totalAlocado) {
		this.totalAlocado = totalAlocado;
	}

	public Long getTotalFaturado() {
		return totalFaturado;
	}

	public void setTotalFaturado(Long totalFaturado) {
		this.totalFaturado = totalFaturado;
	}

	public Long getTotalEncaminhado() {
		return totalEncaminhado;
	}

	public void setTotalEncaminhado(Long totalEncaminhado) {
		this.totalEncaminhado = totalEncaminhado;
	}

	public Long getTotalRejeitados() {
		return totalRejeitados;
	}

	
	
}
