package com.claro.sccweb.decorator;

import java.text.SimpleDateFormat;

import org.displaytag.decorator.TableDecorator;

import com.claro.sccweb.service.composite.SolicitacaoVumComposite;

/**
 * Decorator para tela de solicitação vum.
 * 
 */
public class SolicitacaoVumDecorator extends TableDecorator {
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

	public String getRequisicao() {
		return getRow().getNmParametro();
	}

	public String getOperadoraLD() {
		return getRow().getOperadoraLD().getDsOperadora();
	}

	public String getDtInicioRepasse() {
		return dateFormat.format(getRow().getDataInicioRepasse());
	}
	
	public String getDtFimRepasse() {
		return dateFormat.format(getRow().getDataFimRepasse());
	}
	
	public String getTipoArquivo() {
		String tipo = getRow().getTipoArquivo();
		if (tipo.equals("P"))
			return "Parcial";
		return "Total";
	}
	
	public String getPlataforma() {
		String tipo = getRow().getPlataforma();
		if (tipo.equals("0000"))
			return "Pós-Pago";
		return "Pré-Pago";
	}
	
	
	public String getDtCriacao() {
		return dateFormat.format(getRow().getDtCriacao());
	}

	public String getDtEvento() {
		if (getRow().getDtEvento() != null)
			return dateFormat.format(getRow().getDtEvento());
		else
			return "";
	}

	public String getUsuario() {
		return getRow().getUsuario();
	}

	public String getRemover() {
		return "<a href='#' onClick=\"remove('" + getRow().getNmParametro() + "')\">Remover</a>";
	}

	public SolicitacaoVumComposite getRow() {
		return (SolicitacaoVumComposite) getCurrentRowObject();
	}
}
