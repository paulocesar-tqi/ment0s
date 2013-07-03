/**
 * 
 */
package com.claro.sccweb.decorator.view;

import org.displaytag.decorator.TableDecorator;

import com.claro.cobillingweb.persistence.entity.SccArquivoSumarizado;

/**
 * @author vagner.souza
 *
 */
public class SccDistribuicaoCDRDecorator extends TableDecorator {
	
	public String getMesAno(){
		String value = "";
		SccArquivoSumarizado entity = (SccArquivoSumarizado) getCurrentRowObject();
		if(entity.getMmCiclo() != null && entity.getAaCiclo() != null){
			value = entity.getMmCiclo().toString() + "/" + entity.getAaCiclo();
		}
		
		return value;
	}

}
