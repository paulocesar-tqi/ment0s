/**
 * 
 */
package com.claro.sccweb.decorator.rownum.entity;

import com.claro.cobillingweb.persistence.view.SccConfirmacaoRepasseView;
import com.claro.sccweb.decorator.rownum.RownumDecorator;

/**
 * @author 92031709
 *
 */
public class SccConfirmacaoRepasseViewDecorator extends RownumDecorator<SccConfirmacaoRepasseView> {
	

	private SccConfirmacaoRepasseView entity;
	
	private String anoMesRepasse;
	private String operadoraLD;
	private String operadoraClaro;
	private String valorRepasse;
	private String statusRepasse;

	public SccConfirmacaoRepasseViewDecorator(SccConfirmacaoRepasseView entity,
			int rownum) {
		super(entity, rownum);
		formatarCampos(entity);
		
	}

	@Override
	protected boolean isDeleteEnabled() {
		return false;
	}

	
	private void formatarCampos(SccConfirmacaoRepasseView entity){

		this.setAnoMesRepasse(entity.getAnoMesRepasse());
		this.setOperadoraLD(entity.getOperadoraLD());
		this.setOperadoraClaro(entity.getOperadoraClaro());
		this.setValorRepasse(formataDouble(entity.getValorRepasse()));
		this.setStatusRepasse(entity.getStatusRepasse());
		
	}

	public SccConfirmacaoRepasseView getEntity() {
		return entity;
	}

	public void setEntity(SccConfirmacaoRepasseView entity) {
		this.entity = entity;
	}

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

	public String getValorRepasse() {
		return valorRepasse;		
	}

	public void setValorRepasse(String valorRepasse) {
		this.valorRepasse = valorRepasse;
	}

	public String getStatusRepasse() {
		return statusRepasse;
	}

	public void setStatusRepasse(String statusRepasse) {
		this.statusRepasse = statusRepasse;
	}

}
