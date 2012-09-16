package com.claro.sccweb.decorator.rownum.view;

import com.claro.cobillingweb.persistence.view.RelEventosArquivoView;
import com.claro.sccweb.decorator.rownum.RownumDecorator;

public class RelEventosArquivoViewDecorator extends RownumDecorator<RelEventosArquivoView> {

	private String operadoraClaro;
	private String operadoraExterna;
	private String dataEvento;
	private String dataReferencia;
	private String quantidadeChamadas;
	private String durcaoTarifada;
	private String valorLiquido;
	private String valorBruto;
	private String evento;
	private String info;
	
	
	public RelEventosArquivoViewDecorator(RelEventosArquivoView entity,int rownum) {
		super(entity, rownum);
	}
	
	public String getOperadoraClaro() {
		return getRow().getOperadoraClaro();
	}

	public String getOperadoraExterna() {
		return getRow().getOperadoraExterna();
	}

	public String getDataEvento() {
		return getRow().getDataEvento();
	}

	public String getDataReferencia() {
		return getRow().getDataReferencia();
	}

	public String getQuantidadeChamadas() {
		return formataDouble(getRow().getQuantidadeChamadas());
	}

	public String getDurcaoTarifada() {
		return formataDouble(getRow().getDurcaoTarifada());
	}

	public String getValorLiquido() {
		return formataDouble(getRow().getValorLiquido());
	}

	public String getValorBruto() {
		return formataDouble(getRow().getValorBruto());
	}

	public String getEvento() {
		return getRow().getEvento();
	}

	public String getInfo() {
		return getRow().getInfo();
	}

	

	 
	protected boolean isDeleteEnabled() {
		return false;
	}

	
	
}
