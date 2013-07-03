/**
 * 
 */
package com.claro.cobillingweb.persistence.view;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.apache.commons.lang.StringUtils;


/**
 * @author 92038883
 *
 */
public class SccRelDemonstrativoDesempenhoPenalidadeView {

	public static NumberFormat decimalFormat = new DecimalFormat("#.##");
	
	private String indicador;
	private String descIndicador;
	private String respIndicador;
	private Double vlOrigem;
	private Double metaIndicador;
	private Double pesoIndicador;
	
	private Double atingimento;
	private Double desempenho;
	
	private Double acelerador;
	
	
	public String getIndicador() {
		return indicador;
	}
	public void setIndicador(String indicador) {
		this.indicador = indicador;
	}
	public String getDescIndicador() {
		return descIndicador;
	}
	public void setDescIndicador(String descIndicador) {
		this.descIndicador = descIndicador;
	}
	public String getRespIndicador() {
		return respIndicador;
	}
	public void setRespIndicador(String respIndicador) {
		this.respIndicador = respIndicador;
	}
	public Double getVlOrigem() {
		return vlOrigem;
	}
	public void setVlOrigem(Double vlOrigem) {
		this.vlOrigem = vlOrigem;
	}
	public Double getMetaIndicador() {
		return metaIndicador;
	}
	public void setMetaIndicador(Double metaIndicador) {
		this.metaIndicador = metaIndicador;
	}
	public Double getPesoIndicador() {
		return pesoIndicador;
	}
	public void setPesoIndicador(Double pesoIndicador) {
		this.pesoIndicador = pesoIndicador;
	}
	
		
	
	
	public Double getAtingimento() {
		return atingimento;
	}
	
	public void setAtingimento(Double atingimento) {
		
		String value = decimalFormat.format(zeroIfNull((this.vlOrigem)*10000
				/ zeroIfNull(this.metaIndicador))/10000);
		
		if(StringUtils.isNotEmpty(value)){
			this.atingimento = new Double(value);
		}
		this.atingimento = atingimento;
	}
	
	
	
	public Double getDesempenho() {
		return desempenho;
	}
	
	public void setDesempenho(Double desempenho) {
		this.desempenho = desempenho;
	}
	
	
		
	public Double getAcelerador() {
		return acelerador;
	}
	public void setAcelerador(Double acelerador) {
		this.acelerador = acelerador;
	}
	
	private Double zeroIfNull(Double value) {
		if (value == null) {
			return 0.0;
		}
		return value;	
	}
	
}
