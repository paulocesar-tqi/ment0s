package com.claro.sccweb.form;

/**
 * Extensão do form de resultados de pesquisa em que um arquivo pode ser selecionado.
 *
 */
public class SelecaoArquivoResultForm extends SearchResultForm {

	/**
	 * PK do arquivo selecionado.
	 */
	private long sqArquivo;

	public long getSqArquivo() {
		return sqArquivo;
	}

	public void setSqArquivo(long sqArquivo) {
		this.sqArquivo = sqArquivo;
	}
	
	
	
}
