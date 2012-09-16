package com.claro.sccweb.service.data;

import java.util.List;

import com.claro.cobillingweb.persistence.entity.SccArquivoSumarizado;

public class PeriodoCDR implements Comparable<PeriodoCDR>{
 
	private Long totalCDRs;
	private Long mes;
	private Long ano;
	private String mesAno;	
	private List<GrupoCDR> cdrs;
	private Long aceitos = 0L;
	
	public Long getTotalCDRs() {
		return totalCDRs;
	}
	public void setTotalCDRs(Long totalCDRs) {
		this.totalCDRs = totalCDRs;
	}
	public String getMesAno() {
		return mesAno;
	}
	public void setMesAno(String mesAno) {
		this.mesAno = mesAno;
	}
	public List<GrupoCDR> getCdrs() {
		return cdrs;
	}
	public void setCdrs(List<GrupoCDR> cdrs) {
		this.cdrs = cdrs;
	}
	
	public void addSumario(SccArquivoSumarizado row)
	{
		for (int i=0;i<cdrs.size();i++)
			{
			if (cdrs.get(i).contemStatus(row.getCdStatusCdr()))
				{
				cdrs.get(i).addQuatidade(row.getQtCdrs());				
				}
			else
				{
				cdrs.get(i).addTotal(row.getQtCdrs());
				}			
			}
		if ((!row.getCdStatusCdr().equals(GrupoStatusConstants.GRUPO_CDR_REJEITADO)) && (!row.getCdStatusCdr().equals(GrupoStatusConstants.GRUPO_CDR_REJEITADO_C2)))
			{
			this.aceitos = this.aceitos+row.getQtCdrs();
			}
	}
	public Long getAceitos() {
		return aceitos;
	}
	public void setAceitos(Long aceitos) {
		this.aceitos = aceitos;
	}
	public Long getMes() {
		return mes;
	}
	public void setMes(Long mes) {
		this.mes = mes;
	}
	public Long getAno() {
		return ano;
	}
	public void setAno(Long ano) {
		this.ano = ano;
	}
	 
	public int compareTo(PeriodoCDR o) {
		int x = getAno().compareTo(o.getAno());
		if (x != 0)
			return x;
		x = getMes().compareTo(o.getMes());				
		return x;
	}
	
	
	
}
