/**
 * 
 */
package com.claro.sccweb.decorator.rownum.entity;

import com.claro.cobillingweb.persistence.view.SccBatimentoArquivosView;
import com.claro.sccweb.decorator.rownum.RownumDecorator;

/**
 * @author 93046251
 *
 */
public class SccBatimentoArquivosViewDecorator extends RownumDecorator<SccBatimentoArquivosView> {
	

	private SccBatimentoArquivosView entity;
	
	//colunas CLARO
	private String dtConnectClaro;
	private String dtReferenciaClaro;
	private String nomeArquivoClaro;
	private String dnsProtocoloClaro;
	private String duracaoTarifadaClaro;
	private String quantidadeClaro;
	private String valorLiquidoClaro;
	private String erroProtocoloClaro;
	private String descErroProtocoloClaro;

	//colunas LD
	private String nomeArquivoLD;
	private String duracaoTarifadaLD;
	private String quantidadeLD;
	private String valorLiquidoLD;
	private String statusLD;

	//colunas RESULTADO BATIMENTO	
	private String statusBatimento;//???
	private String duracaoTarifadaBat;
	private String quantidadeBat;
	private String valorLiquidoBat;

	
	public SccBatimentoArquivosViewDecorator(SccBatimentoArquivosView entity,
			int rownum) {
		super(entity, rownum);
		formatarCampos(entity);
		
	}

	@Override
	protected boolean isDeleteEnabled() {
		return false;
	}

	
	private void formatarCampos(SccBatimentoArquivosView entity){

		this.setDtConnectClaro(formataDate(entity.getDtConnectClaro()));
		this.setDtReferenciaClaro(formataDate(entity.getDtReferenciaClaro()));
		this.setNomeArquivoClaro(entity.getNomeArquivoClaro());
		this.setDnsProtocoloClaro(entity.getDnsProtocoloClaro());
		this.setDuracaoTarifadaClaro(formataDouble(entity.getDuracaoTarifadaClaro()));
		this.setQuantidadeClaro(formataDouble(entity.getQuantidadeClaro()));
		this.setValorLiquidoClaro(formataDouble(entity.getValorLiquidoClaro()));
		this.setNomeArquivoLD(entity.getNomeArquivoLD());
		this.setQuantidadeLD(formataDouble(entity.getQuantidadeLD()));
		this.setStatusLD(entity.getStatusLD());
		this.setQuantidadeBat(formataDouble(entity.getQuantidadeBat()));
		this.setErroProtocoloClaro(entity.getErroProtocoloClaro() != null && entity.getErroProtocoloClaro().equals("OK") ? entity.getErroProtocoloClaro() : "-");
		this.setDescErroProtocoloClaro(entity.getDescErroProtocoloClaro() != null ? entity.getDescErroProtocoloClaro() : "-");
		
		
		
	}

	public SccBatimentoArquivosView getEntity() {
		return entity;
	}

	public void setEntity(SccBatimentoArquivosView entity) {
		this.entity = entity;
	}

	public String getDtConnectClaro() {
		return dtConnectClaro;
	}

	public void setDtConnectClaro(String dtConnectClaro) {
		this.dtConnectClaro = dtConnectClaro;
	}

	public String getDtReferenciaClaro() {
		return dtReferenciaClaro;
	}

	public void setDtReferenciaClaro(String dtReferenciaClaro) {
		this.dtReferenciaClaro = dtReferenciaClaro;
	}

	public String getNomeArquivoClaro() {
		return nomeArquivoClaro;
	}

	public void setNomeArquivoClaro(String nomeArquivoClaro) {
		this.nomeArquivoClaro = nomeArquivoClaro;
	}

	public String getDnsProtocoloClaro() {
		return dnsProtocoloClaro;
	}

	public void setDnsProtocoloClaro(String dnsProtocoloClaro) {
		this.dnsProtocoloClaro = dnsProtocoloClaro;
	}

	public String getDuracaoTarifadaClaro() {
		return duracaoTarifadaClaro;
	}

	public void setDuracaoTarifadaClaro(String duracaoTarifadaClaro) {
		this.duracaoTarifadaClaro = duracaoTarifadaClaro;
	}

	public String getQuantidadeClaro() {
		return quantidadeClaro;
	}

	public void setQuantidadeClaro(String quantidadeClaro) {
		this.quantidadeClaro = quantidadeClaro;
	}

	public String getValorLiquidoClaro() {
		return valorLiquidoClaro;
	}

	public void setValorLiquidoClaro(String valorLiquidoClaro) {
		this.valorLiquidoClaro = valorLiquidoClaro;
	}

	public String getErroProtocoloClaro() {
		return erroProtocoloClaro;
	}

	public void setErroProtocoloClaro(String erroProtocoloClaro) {
		this.erroProtocoloClaro = erroProtocoloClaro;
	}

	public String getDescErroProtocoloClaro() {
		return descErroProtocoloClaro;
	}

	public void setDescErroProtocoloClaro(String descErroProtocoloClaro) {
		this.descErroProtocoloClaro = descErroProtocoloClaro;
	}

	public String getNomeArquivoLD() {
		return nomeArquivoLD;
	}

	public void setNomeArquivoLD(String nomeArquivoLD) {
		this.nomeArquivoLD = nomeArquivoLD;
	}

	public String getDuracaoTarifadaLD() {
		return duracaoTarifadaLD;
	}

	public void setDuracaoTarifadaLD(String duracaoTarifadaLD) {
		this.duracaoTarifadaLD = duracaoTarifadaLD;
	}

	public String getQuantidadeLD() {
		return quantidadeLD;
	}

	public void setQuantidadeLD(String quantidadeLD) {
		this.quantidadeLD = quantidadeLD;
	}

	public String getValorLiquidoLD() {
		return valorLiquidoLD;
	}

	public void setValorLiquidoLD(String valorLiquidoLD) {
		this.valorLiquidoLD = valorLiquidoLD;
	}

	public String getStatusLD() {
		return statusLD;
	}

	public void setStatusLD(String statusLD) {
		this.statusLD = statusLD;
	}

	public String getStatusBatimento() {
		return statusBatimento;
	}

	public void setStatusBatimento(String statusBatimento) {
		this.statusBatimento = statusBatimento;
	}

	public String getDuracaoTarifadaBat() {
		return duracaoTarifadaBat;
	}

	public void setDuracaoTarifadaBat(String duracaoTarifadaBat) {
		this.duracaoTarifadaBat = duracaoTarifadaBat;
	}

	public String getQuantidadeBat() {
		return quantidadeBat;
	}

	public void setQuantidadeBat(String quantidadeBat) {
		this.quantidadeBat = quantidadeBat;
	}

	public String getValorLiquidoBat() {
		return valorLiquidoBat;
	}

	public void setValorLiquidoBat(String valorLiquidoBat) {
		this.valorLiquidoBat = valorLiquidoBat;
	}


}
