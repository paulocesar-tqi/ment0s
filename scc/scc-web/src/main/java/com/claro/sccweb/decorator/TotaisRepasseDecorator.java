package com.claro.sccweb.decorator;

/**
 * Decorator utilizado no export para Excel do demonstrativo de repasse pós pago.
 *
 */
public class TotaisRepasseDecorator {

	private String operadoraClaro;
	private String operadoraLD;
	private Double valorRepasse;
	
	public String getOperadoraClaro() {
		return operadoraClaro;
	}
	public void setOperadoraClaro(String operadoraClaro) {
		this.operadoraClaro = operadoraClaro;
	}
	public String getOperadoraLD() {
		return operadoraLD;
	}
	public void setOperadoraLD(String operadoraLD) {
		this.operadoraLD = operadoraLD;
	}
	public Double getValorRepasse() {
		return valorRepasse;
	}
	public void setValorRepasse(Double valorRepasse) {
		this.valorRepasse = valorRepasse;
	}
	
	
	
}
