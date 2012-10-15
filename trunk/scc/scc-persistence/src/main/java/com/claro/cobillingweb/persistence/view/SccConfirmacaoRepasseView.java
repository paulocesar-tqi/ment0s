/**
 * 
 */
package com.claro.cobillingweb.persistence.view;

/**
 * @author 92031709
 *
 */
public class SccConfirmacaoRepasseView {

	private String anoMesRepasse;
	private String operadoraLD;
	private String operadoraClaro;
	private Double valorRepasse;
	private String statusRepasse;
	
	public String getAnoMesRepasse() {
		return anoMesRepasse;
	}
	public void setAnoMesRepasse(String anoMesRepasse) {
		this.anoMesRepasse = anoMesRepasse;
	}
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
	public Double getValorRepasse() {
		return valorRepasse;
	}
	public void setValorRepasse(Double valorRepasse) {
		this.valorRepasse = valorRepasse;
	}
	public String getStatusRepasse() {
		return statusRepasse;
	}
	public void setStatusRepasse(String statusRepasse) {
		this.statusRepasse = statusRepasse;
	}
}
