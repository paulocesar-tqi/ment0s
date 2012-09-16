package com.claro.sccweb.form.helper;

import java.util.List;

import com.claro.cobillingweb.persistence.entity.SccArquivoCobilling;
import com.claro.cobillingweb.persistence.entity.SccCdrCobilling;
import com.claro.cobillingweb.persistence.view.SumarioCDRView;
import com.claro.sccweb.form.ItemSumarioArquivoProcessado;


/**
 * Representa a estrutura de navegação em um arquivo de CDRs.
 * Esse objeto fica na session como um result list.
 * 
 * A navegação por arquivos de CDRs é baseada nos seguintes passos:
 * 
 * 1. Uma pesquisa é executada e retorna uma lista de arquivos : arquivosFiltro.
 * 2. Um desses arquivos é selecionado : arquivoSelecionado
 * 3. Após selecionado , o sistema mostra um sumário desse arquivo : sumario.
 * 4. O sumários possui os itens do arquivo (CDR's) agrupados de acordo algum critério.
 * 5. Um grupo desse sumário pode ser selecionado para listagem : items.
 * 6. Finalmente um único item desse grupo pode ser selecionado para visualização : itemSelecionado.
 */
public class NavegacaoArquivoCDRs {

	private List<SccArquivoCobilling> arquivosFiltro;
	
	private SccArquivoCobilling arquivoSelecionado;
	
	private List<ItemSumarioArquivoProcessado> sumario;
	
	private SccCdrCobilling itemSelecionado;

	private Long statusSelecionado;
	
	private Integer chaveCiclo;
	
	public List<SccArquivoCobilling> getArquivosFiltro() {
		return arquivosFiltro;
	}

	public void setArquivosFiltro(List<SccArquivoCobilling> arquivosFiltro) {
		this.arquivosFiltro = arquivosFiltro;
	}

	public SccArquivoCobilling getArquivoSelecionado() {
		return arquivoSelecionado;
	}

	public void setArquivoSelecionado(SccArquivoCobilling arquivoSelecionado) {
		this.arquivoSelecionado = arquivoSelecionado;
	}

	public List<ItemSumarioArquivoProcessado> getSumario() {
		return sumario;
	}

	public void setSumario(List<ItemSumarioArquivoProcessado> sumario) {
		this.sumario = sumario;
	}

	

	

	public SccCdrCobilling getItemSelecionado() {
		return itemSelecionado;
	}

	public void setItemSelecionado(SccCdrCobilling itemSelecionado) {
		this.itemSelecionado = itemSelecionado;
	}

	public Long getStatusSelecionado() {
		return statusSelecionado;
	}

	public void setStatusSelecionado(Long statusSelecionado) {
		this.statusSelecionado = statusSelecionado;
	}

	public Integer getChaveCiclo() {
		return chaveCiclo;
	}

	public void setChaveCiclo(Integer chaveCiclo) {
		this.chaveCiclo = chaveCiclo;
	}
	
	
	
}
