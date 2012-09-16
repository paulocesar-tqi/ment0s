package com.claro.sccweb.service.data;

import com.claro.sccweb.decorator.BasicSccDecorator;


/**
 * Sumário de CDRs agrupados por status;
 *
 */
public class GrupoCDR extends BasicSccDecorator {
	
	/**
	 * Descrição do status.
	 */
	private String descricao;
	
	private Long grupoStatus;
	private String mesAno;
	
	/**
	 * Quantidade de CDRs no status.
	 */
	private Long quantidadeCDR = 0L;
	
	private Long quantidadeTotalCDRs = 0L;
	
	/**
	 * Proporção dos CDRS (%) nesse status em relação à quantidade
	 * total de CDRs.
	 */
	private Double proporcaoCDR;
	
	/**
	 * Lista de status reais de CDRs que compoem o grupo.
	 */
	private Long[] statusConcretos;
	
	public GrupoCDR(String descricao,Long grupoStatus,Long... statusConcretos)
	{		
		this.descricao = descricao;
		this.grupoStatus = grupoStatus;
		this.statusConcretos = statusConcretos;
	}
	
	
	
	public void addQuatidade(Long quantidade)
	{
		if (quantidade != null)
			{
			quantidadeCDR = quantidadeCDR+quantidade;
			addTotal(quantidade);
			}
			
	}
	
	public void addTotal(Long quantidade)
	{
		if (quantidade != null)
			quantidadeTotalCDRs = quantidadeTotalCDRs+quantidade;
	}
	
	
	public boolean contemStatus(Long status)
	{
		if (statusConcretos != null)
			{
			for (int i=0;i<statusConcretos.length;i++)
				{
				if (status.longValue() == statusConcretos[i].longValue())
					return true;
				}			
			}
		return false;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Long getGrupoStatus() {
		return grupoStatus;
	}

	public void setGrupoStatus(Long grupoStatus) {
		this.grupoStatus = grupoStatus;
	}

	public Long getQuantidadeCDR() {
		return quantidadeCDR;
	}

	public void setQuantidadeCDR(Long quantidadeCDR) {
		this.quantidadeCDR = quantidadeCDR;
	}

	public Long getQuantidadeTotalCDRs() {
		return quantidadeTotalCDRs;
	}

	public void setQuantidadeTotalCDRs(Long quantidadeTotalCDRs) {
		this.quantidadeTotalCDRs = quantidadeTotalCDRs;
	}

	public String getProporcaoCDR() {
		double p = 0.0;
		if ((getQuantidadeCDR() == 0L) || (getQuantidadeTotalCDRs() == 0L))
			return formataDouble(0.0)+"%";
		double a = getQuantidadeTotalCDRs();
		double b = getQuantidadeCDR();	
		p = (b/a)*100;
		return formataDouble(p)+"%";
	}

	public Double getPercentual() {
		double p = 0.0;
		if ((getQuantidadeCDR() == 0L) || (getQuantidadeTotalCDRs() == 0L))
			return 0.0;
		double a = getQuantidadeTotalCDRs();
		double b = getQuantidadeCDR();	
		p = (b/a)*100;
		return p;
	}
	
	public void setProporcaoCDR(Double proporcaoCDR) {
		this.proporcaoCDR = proporcaoCDR;
	}

	public Long[] getStatusConcretos() {
		return statusConcretos;
	}

	public void setStatusConcretos(Long[] statusConcretos) {
		this.statusConcretos = statusConcretos;
	}



	public String getMesAno() {
		return mesAno;
	}



	public void setMesAno(String mesAno) {
		this.mesAno = mesAno;
	}
	
	
}
