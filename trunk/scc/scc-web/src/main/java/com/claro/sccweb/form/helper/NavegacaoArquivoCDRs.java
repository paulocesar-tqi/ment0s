package com.claro.sccweb.form.helper;

import java.util.List;

import com.claro.cobillingweb.persistence.entity.SccArquivoCobilling;
import com.claro.cobillingweb.persistence.entity.SccCdrCobilling;
import com.claro.cobillingweb.persistence.view.SumarioCDRView;
import com.claro.sccweb.form.ItemSumarioArquivoProcessado;


/**
 * Representa a estrutura de navega��o em um arquivo de CDRs.
 * Esse objeto fica na session como um result list.
 * 
 * A navega��o por arquivos de CDRs � baseada nos seguintes passos:
 * 
 * 1. Uma pesquisa � executada e retorna uma lista de arquivos : arquivosFiltro.
 * 2. Um desses arquivos � selecionado : arquivoSelecionado
 * 3. Ap�s selecionado , o sistema mostra um sum�rio desse arquivo : sumario.
 * 4. O sum�rios possui os itens do arquivo (CDR's) agrupados de acordo algum crit�rio.
 * 5. Um grupo desse sum�rio pode ser selecionado para listagem : items.
 * 6. Finalmente um �nico item desse grupo pode ser selecionado para visualiza��o : itemSelecionado.
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
