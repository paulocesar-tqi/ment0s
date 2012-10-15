package com.claro.sccweb.decorator.rownum.entity;

import com.claro.cobillingweb.persistence.view.SccRelatorioChamadasRefaturamentoView;
import com.claro.sccweb.decorator.rownum.RownumDecorator;

public class SccRelatorioChamadasRefaturamentoViewDecorator extends RownumDecorator<SccRelatorioChamadasRefaturamentoView>  {
	
	
	private SccRelatorioChamadasRefaturamentoView entity;
	
	private String numeroA;
	private String numeroB;
	private String dataHora;
	private String valor;
	private String minutoTarifado;
	private String codigoCriticaInicial;
	private String arquivoRemessa;
	private String arquivoRetorno;
	private String arquivoRemessaRefaturamento;
	
	public SccRelatorioChamadasRefaturamentoViewDecorator(SccRelatorioChamadasRefaturamentoView entity,
			int rownum) {
		super(entity, rownum);
		formatarCampos(entity);
		
	}

	@Override
	protected boolean isDeleteEnabled() {
		return false;
	}
		
	
	private void formatarCampos(SccRelatorioChamadasRefaturamentoView entity){

		
		this.setNumeroA(entity.getNumeroA());
		this.setNumeroB(entity.getNumeroB());
		this.setDataHora(formataDate(entity.getDataHora()));
		this.setValor(formataDouble(entity.getValor()));
		this.setMinutoTarifado(formataDouble(entity.getMinutoTarifado()));
		this.setCodigoCriticaInicial(entity.getCodigoCriticaInicial());
		this.setArquivoRemessa(formataInteger(entity.getArquivoRemessa()));
		this.setArquivoRetorno(formataInteger(entity.getArquivoRetorno()));
		this.setArquivoRemessaRefaturamento(formataInteger(entity.getArquivoRemessaRefaturamento()));

	}	
	
	
	public SccRelatorioChamadasRefaturamentoView getEntity() {
		return entity;
	}
	public void setEntity(SccRelatorioChamadasRefaturamentoView entity) {
		this.entity = entity;
	}
		
	
	public String getNumeroA() {
		return numeroA;
	}
	public void setNumeroA(String numeroA) {
		this.numeroA = numeroA;
	}
	public String getNumeroB() {
		return numeroB;
	}
	public void setNumeroB(String numeroB) {
		this.numeroB = numeroB;
	}
	public String getDataHora() {
		return dataHora;
	}
	public void setDataHora(String dataHora) {
		this.dataHora = dataHora;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public String getMinutoTarifado() {
		return minutoTarifado;
	}
	public void setMinutoTarifado(String minutoTarifado) {
		this.minutoTarifado = minutoTarifado;
	}
	public String getCodigoCriticaInicial() {
		return codigoCriticaInicial;
	}
	public void setCodigoCriticaInicial(String codigoCriticaInicial) {
		this.codigoCriticaInicial = codigoCriticaInicial;
	}
	public String getArquivoRemessa() {
		return arquivoRemessa;
	}
	public void setArquivoRemessa(String arquivoRemessa) {
		this.arquivoRemessa = arquivoRemessa;
	}
	public String getArquivoRetorno() {
		return arquivoRetorno;
	}
	public void setArquivoRetorno(String arquivoRetorno) {
		this.arquivoRetorno = arquivoRetorno;
	}
	public String getArquivoRemessaRefaturamento() {
		return arquivoRemessaRefaturamento;
	}
	public void setArquivoRemessaRefaturamento(String arquivoRemessaRefaturamento) {
		this.arquivoRemessaRefaturamento = arquivoRemessaRefaturamento;
	}

}
