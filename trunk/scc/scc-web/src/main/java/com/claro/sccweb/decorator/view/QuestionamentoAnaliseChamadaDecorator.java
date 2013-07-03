/**
 * 
 */
package com.claro.sccweb.decorator.view;

import org.displaytag.decorator.TableDecorator;

import com.claro.cobillingweb.persistence.entity.SccCdrQuestionamento;

/**
 * @author vagner.souza
 *
 */
public class QuestionamentoAnaliseChamadaDecorator extends TableDecorator {
	
	private int indexLinha;
	
	public String getProcedente(){
		
		indexLinha ++;
		
		SccCdrQuestionamento entity = (SccCdrQuestionamento) getCurrentRowObject();		
		
		String radio_id = "isProcedente" + this.indexLinha;
		
		String resultado_analise_id = "resultado_analise_"+this.indexLinha;		
		
		String str = "<table>";
		str = str + "	<tr>";
		str = str + "		<td>";
		str = str + "			<input type='radio' id='"+ radio_id +"' name='"+ radio_id +"' onclick='bindProcedente(this, \""+ resultado_analise_id +"\")' value='P'  "+ (entity.getInResultadoAnalise().equalsIgnoreCase("P") ? "checked" : "") +" /> P";
		str = str + "		</td>";
		str = str + "		<td>";
		str = str + "			<input type='radio' id='"+ radio_id +"' name='"+ radio_id +"'onclick='bindProcedente(this, \""+ resultado_analise_id +"\")' value='N'  "+ (entity.getInResultadoAnalise().equalsIgnoreCase("N") ? "checked" : "") +" > N";
		str = str + "		</td>";
		str = str + "	</tr>";
		str = str + "	<tr>";
		str = str + "		<td>";
		str = str + "			<spring:bind path=\"filtro.resultadoAnalises\" >";			
		str = str + "				<input type='hidden' id='"+ resultado_analise_id +"' name='resultadoAnalises' value='"+ entity.getInResultadoAnalise() +"' />";
		str = str + "			</spring:bind>";
		str = str + "		</td>";		
		str = str + "	</tr>";
		str = str + "</table>";
		
		return str;
		
	}	

}
