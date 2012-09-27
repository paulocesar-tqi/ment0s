/**
 * 
 */
package com.claro.sccweb.decorator.rownum.entity;

import com.claro.cobillingweb.persistence.view.SccBatimentoInterfaceView;
import com.claro.sccweb.decorator.rownum.RownumDecorator;

/**
 * @author 93046251
 *
 */
public class SccBatimentoInterfaceViewDecorator extends RownumDecorator<SccBatimentoInterfaceView> {
	

	private SccBatimentoInterfaceView entity;
	
	private String nomeArquivo;
	private String operadoraLD;
	private String operadoraClaro;
	private String dataMovimentacao;
	private String dataTransferencia;
	private String quantidadeRegistrosMobile;
	private String dataProcessamento;
	private String quantidadeRegistrosScc;

	private String diferenca;
	private String status;

	
	public SccBatimentoInterfaceViewDecorator(SccBatimentoInterfaceView entity,
			int rownum) {
		super(entity, rownum);
		formatarCampos(entity);
		
	}

	@Override
	protected boolean isDeleteEnabled() {
		return false;
	}

	
	private void formatarCampos(SccBatimentoInterfaceView entity){

		this.setNomeArquivo(formataString(entity.getNomeArquivo()));
		this.setOperadoraLD(formataLong(entity.getOperadoraLD()));
		this.setOperadoraClaro(formataString(entity.getOperadoraClaro()));
		this.setDataMovimentacao(formataDateTime(entity.getDataMovimentacao()));
		this.setDataTransferencia(formataDateTime(entity.getDataTransferencia()));
		this.setQuantidadeRegistrosMobile(formataLong(entity.getQuantidadeRegistrosMobile()));
		this.setDataProcessamento(formataDateTime(entity.getDataProcessamento()));
		this.setQuantidadeRegistrosScc(formataLong(entity.getQuantidadeRegistrosScc()));

		this.setDiferenca(formataLong((entity.getQuantidadeRegistrosScc() != null ? entity.getQuantidadeRegistrosScc() : 0L) - 
									  (entity.getQuantidadeRegistrosMobile() != null ? entity.getQuantidadeRegistrosMobile() : 0L)));
		
		this.setStatus("0".equals(this.getDiferenca()) ? "OK" : "NOK");
	}

	
	public SccBatimentoInterfaceView getEntity() {
		return entity;
	}

	public void setEntity(SccBatimentoInterfaceView entity) {
		this.entity = entity;
	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	public String getOperadoraLD() {
		return operadoraLD;
	}

	public void setOperadoraLD(String operadoraLD) {
		this.operadoraLD = operadoraLD;
	}

	public String getOperadoraClaro() {
		return operadoraClaro;
	}

	public void setOperadoraClaro(String operadoraClaro) {
		this.operadoraClaro = operadoraClaro;
	}

	public String getDataMovimentacao() {
		return dataMovimentacao;
	}

	public void setDataMovimentacao(String dataMovimentacao) {
		this.dataMovimentacao = dataMovimentacao;
	}

	public String getDataTransferencia() {
		return dataTransferencia;
	}

	public void setDataTransferencia(String dataTransferencia) {
		this.dataTransferencia = dataTransferencia;
	}

	public String getQuantidadeRegistrosMobile() {
		return quantidadeRegistrosMobile;
	}

	public void setQuantidadeRegistrosMobile(String quantidadeRegistrosMobile) {
		this.quantidadeRegistrosMobile = quantidadeRegistrosMobile;
	}

	public String getDataProcessamento() {
		return dataProcessamento;
	}

	public void setDataProcessamento(String dataProcessamento) {
		this.dataProcessamento = dataProcessamento;
	}

	public String getQuantidadeRegistrosScc() {
		return quantidadeRegistrosScc;
	}

	public void setQuantidadeRegistrosScc(String quantidadeRegistrosScc) {
		this.quantidadeRegistrosScc = quantidadeRegistrosScc;
	}

	public String getDiferenca() {
		return diferenca;
	}

	public void setDiferenca(String diferenca) {
		this.diferenca = diferenca;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
