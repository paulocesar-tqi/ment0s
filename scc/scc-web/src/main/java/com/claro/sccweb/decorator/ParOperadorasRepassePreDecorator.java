package com.claro.sccweb.decorator;

import com.claro.cobillingweb.persistence.entity.SccOperadora;


/**
 * Decorator para a lista de pares de operadoras no demonstrativo de repasse pré-pago.
 *
 */
public class ParOperadorasRepassePreDecorator  {

	private SccOperadora operadoraLD;
	private SccOperadora operadoraClaro;
	
	 public ParOperadorasRepassePreDecorator(SccOperadora operadoraLD,SccOperadora operadoraClaro)
	 {
			this.operadoraClaro = operadoraClaro;
			this.operadoraLD = operadoraLD;
	 }
	 
	 public String getDsOperadoraClaro()
	 {
		 String label;
		 if (operadoraClaro.isHolding())
			 label =  operadoraClaro.getDsOperadora()+" (Holding)";
		 else
			 label =  operadoraClaro.getDsOperadora()+ "("+operadoraClaro.getCdEot()+")";
		 return getLink(label);
	 }
	 
	 public String getDsOperadoraLD()
	 {
		 return getLink(operadoraLD.getDsOperadora()+" ("+operadoraLD.getCdEot()+")");
	 }
	 
	 public String getLink(String label)
	 {
		 return "<a href='#' onClick=selecionarParOperadoras('"+operadoraClaro.getCdEot()+"')>"+label+"</a>";
	 }
	 
}