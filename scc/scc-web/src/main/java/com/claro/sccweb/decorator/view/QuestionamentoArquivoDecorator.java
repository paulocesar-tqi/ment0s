package com.claro.sccweb.decorator.view;

import org.displaytag.decorator.TableDecorator;

import com.claro.cobillingweb.persistence.view.SccQuestionamentoArquivoView;

public class QuestionamentoArquivoDecorator extends TableDecorator {
	
	
	public String getLink1(){
		
		
		//ListObject lObject= (ListObject)getCurrentRowObject();
		SccQuestionamentoArquivoView questionamento;
		questionamento = (SccQuestionamentoArquivoView) getCurrentRowObject();
		//return questionamento.getSqQuestionamento().toString();
		//Long sqRemessaQuestionamento= getRow().getSqRemessaQuestionamento();
		//return "&lt;a href=\"analiseChamadas?sqRemessaQuestionamento=" + questionamento.getSqQuestionamento() + "\">" + questionamento.getNoArquivo() + "&lt;/a>";
		return "<a href=\"analiseChamadas?sqRemessaQuestionamento=" + questionamento.getSqQuestionamento() + "\">" + questionamento.getNoArquivo() + "</a>";
	}


}
