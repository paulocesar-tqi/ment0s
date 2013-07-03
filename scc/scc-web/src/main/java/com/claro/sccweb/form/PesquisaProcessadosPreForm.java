package com.claro.sccweb.form;

import java.util.List;

import com.claro.cobillingweb.persistence.filtro.SCCArquivoCobillingFiltro;
import com.claro.sccweb.decorator.rownum.view.ViewArquivoPrePagoDecorator;

public class PesquisaProcessadosPreForm extends BaseForm {

	private SCCArquivoCobillingFiltro pesquisa;
	
	private List<ViewArquivoPrePagoDecorator> listArquivoPrePagoDecorators;

	public SCCArquivoCobillingFiltro getPesquisa() {
		return pesquisa;
	}

	public void setPesquisa(SCCArquivoCobillingFiltro pesquisa) {
		this.pesquisa = pesquisa;
	}

	public List<ViewArquivoPrePagoDecorator> getListArquivoPrePagoDecorators() {
		return listArquivoPrePagoDecorators;
	}

	public void setListArquivoPrePagoDecorators(
			List<ViewArquivoPrePagoDecorator> listArquivoPrePagoDecorators) {
		this.listArquivoPrePagoDecorators = listArquivoPrePagoDecorators;
	}
	
	
	
}
