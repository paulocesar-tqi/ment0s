/**
 * 
 */
package com.claro.sccweb.decorator.rownum.entity;

import com.claro.cobillingweb.persistence.view.SccArquivosNaoProcessadosView;
import com.claro.sccweb.decorator.rownum.RownumDecorator;

/**
 * @author 92031709
 *
 */
public class SccArquivosNaoProcessadosViewDecorator extends RownumDecorator<SccArquivosNaoProcessadosView> {
	

	private SccArquivosNaoProcessadosView entity;

	private String dtRelatorio;
	private String noArquivoRetorno;
	private String qtChamadas;
	private String qtMinutoTarifados;
	private String vlLiquido;
	private String dtRecebimento;

	public SccArquivosNaoProcessadosViewDecorator(SccArquivosNaoProcessadosView entity,
			int rownum) {
		super(entity, rownum);
		formatarCampos(entity);
		
	}

	@Override
	protected boolean isDeleteEnabled() {
		return false;
	}

	
	private void formatarCampos(SccArquivosNaoProcessadosView entity){

		this.setDtRelatorio(formataDate(entity.getDtRelatorio()));
		this.setNoArquivoRetorno(entity.getNoArquivoRetorno());
		this.setQtChamadas(formataInteger(entity.getQtChamadas()));
		this.setQtMinutoTarifados(formataDouble(entity.getQtMinutoTarifados()));
		this.setVlLiquido(formataDouble(entity.getVlLiquido()));
		this.setDtRecebimento(formataDate(entity.getDtRecebimento()));	
		
	}

	public SccArquivosNaoProcessadosView getEntity() {
		return entity;
	}

	public void setEntity(SccArquivosNaoProcessadosView entity) {
		this.entity = entity;
	}

	public String getDtRelatorio() {
		return dtRelatorio;
	}

	public void setDtRelatorio(String dtRelatorio) {
		this.dtRelatorio = dtRelatorio;
	}

	public String getNoArquivoRetorno() {
		return noArquivoRetorno;
	}

	public void setNoArquivoRetorno(String noArquivoRetorno) {
		this.noArquivoRetorno = noArquivoRetorno;
	}

	public String getQtChamadas() {
		return qtChamadas;
	}

	public void setQtChamadas(String qtChamadas) {
		this.qtChamadas = qtChamadas;
	}

	public String getQtMinutoTarifados() {
		return qtMinutoTarifados;
	}

	public void setQtMinutoTarifados(String qtMinutoTarifados) {
		this.qtMinutoTarifados = qtMinutoTarifados;
	}

	public String getVlLiquido() {
		return vlLiquido;
	}

	public void setVlLiquido(String vlLiquido) {
		this.vlLiquido = vlLiquido;
	}

	public String getDtRecebimento() {
		return dtRecebimento;
	}

	public void setDtRecebimento(String dtRecebimento) {
		this.dtRecebimento = dtRecebimento;
	}

}
