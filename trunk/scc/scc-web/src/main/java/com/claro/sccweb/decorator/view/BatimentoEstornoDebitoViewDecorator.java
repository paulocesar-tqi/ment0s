/**
 * 
 */
package com.claro.sccweb.decorator.view;

import com.claro.cobillingweb.persistence.view.BatimentoEstornoDebitoView;
import com.claro.sccweb.decorator.rownum.RownumDecorator;

/**
 * @author 93046251
 *
 */
public class BatimentoEstornoDebitoViewDecorator extends RownumDecorator<BatimentoEstornoDebitoView>{

	public BatimentoEstornoDebitoViewDecorator(
			BatimentoEstornoDebitoView entity, int rownum) {
		super(entity, rownum);
		
	}
	

	@Override
	protected boolean isDeleteEnabled() {
		
		return false;
	}


	public String getOperadoraLD() {
		return getRow().getOperadoraLD();
	}


	public String getOperadoraClaro() {
		return getRow().getOperadoraClaro();
	}


	public String getUf() {
		return getRow().getUf();
	}


	public String getCiclo() {
		return formataInteger(getRow().getCiclo());
	}


	public String getNotaFiscal() {
		return formataLong(getRow().getNotaFiscal());
	}


	public String getEstornoDebitoToAjustado() {
		return formataDouble(getRow().getEstornoDebitoToAjustado());
	}


	public String getChamadasToAjustado() {
		return formataDouble(getRow().getChamadasToAjustado());
	}


	public String getDiferencaTOajustado() {
		return formataDouble(getRow().getDiferencaTOajustado());
	}


	public String getEstornoDebitoCrediCMS() {
		return formataDouble(getRow().getEstornoDebitoCrediCMS());
	}


	public String getChamadasCrediCMS() {
		return formataDouble(getRow().getChamadasCrediCMS());
	}


	public String getDiferencaCrediCMS() {
		return formataDouble(getRow().getDiferencaCrediCMS());
	}


	

}
