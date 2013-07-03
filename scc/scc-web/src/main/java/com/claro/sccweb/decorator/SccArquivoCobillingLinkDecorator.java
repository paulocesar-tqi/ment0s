package com.claro.sccweb.decorator;

import org.displaytag.decorator.TableDecorator;

import com.claro.cobillingweb.persistence.entity.SccArquivoCobilling;

public class SccArquivoCobillingLinkDecorator extends TableDecorator {
	
	
	
	
	public String getNomeArquivoLink() {
		SccArquivoCobilling entity = (SccArquivoCobilling) getCurrentRowObject();
		
		return "<a href=download.scc?nomeArquivo="+entity.getNoArquivo()+"&diretorio="+entity.getNoDiretorioArquivo()+">"+entity.getNoArquivo()+" </a>";
				
	}

	

}
