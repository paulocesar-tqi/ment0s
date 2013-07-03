/**
 * 
 */
package com.claro.sccweb.decorator;

import com.claro.cobillingweb.persistence.view.RelPrestacaoServicoView;
import com.claro.sccweb.decorator.rownum.RownumDecorator;

/**
 * @author 93046251
 *
 */
public class RelatorioPrestacaoServicoPreDecorator extends RownumDecorator<RelPrestacaoServicoView> {
	
	public RelatorioPrestacaoServicoPreDecorator(RelPrestacaoServicoView entity, int rownum) {
		super(entity, rownum);
		
	}

	@Override
	protected boolean isDeleteEnabled() {
		return false;
	}
	
	public String getOperadoraClaro(){
		return getRow().getDsOperadoraClaro();
	}
	
	public String getEmbratel(){
		return formataDouble(getRow().getEmbratel());
	}
	
	
	public String getIntelig(){
		return formataDouble(getRow().getIntelig());
	}
	
	public String getBrasilTelecom(){
		return formataDouble(getRow().getBrasil_telecom());
	}
	
	public String getTelefonica(){
		return formataDouble(getRow().getTelefonica());
	}
	
	public String getTnl(){
		return formataDouble(getRow().getTnl());
	}
	
	public String getGvt(){
		return formataDouble(getRow().getGvt());
	}
	
	public String getSercomtel(){
		return formataDouble(getRow().getSercontel());
	}
	
	public String getTim(){
		return formataDouble(getRow().getTim());
	}
	
	public String getCtbc(){
		return formataDouble(getRow().getCtbc());
	}
	
	public String getTelemar(){
		return formataDouble(getRow().getTelemar());
	}
	
	public String getIpCorp(){
		return formataDouble(getRow().getIpCorp());
	}
	
	public String getNexus(){
		return formataDouble(getRow().getNexus());
	}
	
	public String getTelecom65(){
		return formataDouble(getRow().getTelecom65());
	}
	
	public String getCambridge(){
		return formataDouble(getRow().getCambridge());
	}
	

}
