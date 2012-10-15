/**
 * 
 */
package com.claro.cobillingweb.persistence.view;

import java.io.Serializable;

/**
 * @author 93046251
 *
 */
public class BatimentoEstornoDebitoView implements Serializable{
	
	private static final long serialVersionUID = 6016477626574704714L;

	private String operadoraLD;
	
	private String operadoraClaro;
	
	private String uf;
	
	private Integer ciclo;
	
	private Long notaFiscal;
	
	private Double estornoDebitoToAjustado;
	
	private Double chamadasToAjustado;
	
	private Double diferencaTOajustado;
	
	private Double estornoDebitoCrediCMS;
	
	private Double chamadasCrediCMS;
	
	private Double diferencaCrediCMS;

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

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public Integer getCiclo() {
		return ciclo;
	}

	public void setCiclo(Integer ciclo) {
		this.ciclo = ciclo;
	}

	public Long getNotaFiscal() {
		return notaFiscal;
	}

	public void setNotaFiscal(Long notaFiscal) {
		this.notaFiscal = notaFiscal;
	}

	public Double getEstornoDebitoToAjustado() {
		return estornoDebitoToAjustado;
	}

	public void setEstornoDebitoToAjustado(Double estornoDebitoToAjustado) {
		this.estornoDebitoToAjustado = estornoDebitoToAjustado;
	}

	public Double getChamadasToAjustado() {
		return chamadasToAjustado;
	}

	public void setChamadasToAjustado(Double chamadasToAjustado) {
		this.chamadasToAjustado = chamadasToAjustado;
	}

	public Double getDiferencaTOajustado() {
		return diferencaTOajustado;
	}

	public void setDiferencaTOajustado(Double diferencaTOajustado) {
		this.diferencaTOajustado = diferencaTOajustado;
	}

	public Double getEstornoDebitoCrediCMS() {
		return estornoDebitoCrediCMS;
	}

	public void setEstornoDebitoCrediCMS(Double estornoDebitoCrediCMS) {
		this.estornoDebitoCrediCMS = estornoDebitoCrediCMS;
	}

	public Double getChamadasCrediCMS() {
		return chamadasCrediCMS;
	}

	public void setChamadasCrediCMS(Double chamadasCrediCMS) {
		this.chamadasCrediCMS = chamadasCrediCMS;
	}

	public Double getDiferencaCrediCMS() {
		return diferencaCrediCMS;
	}

	public void setDiferencaCrediCMS(Double diferencaCrediCMS) {
		this.diferencaCrediCMS = diferencaCrediCMS;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
