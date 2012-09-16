package com.claro.sccweb.form;

public class ControleRemessaCicloForm extends BaseForm {

	/**
	 * O = Operadora
	 * H = Holding
	 */
	private String tipoOperadora = "O";
	
	private String cdEOTClaro;
	
	private String cdEOTLD;
	
	private Long cdProdutoCobilling;
	
	private Long cdTipoArquivo;
	
	private Long aaCiclo;
	
	private Long mmCiclo;
	
	private Long cdCiclo;

	public String getTipoOperadora() {
		return tipoOperadora;
	}

	public void setTipoOperadora(String tipoOperadora) {
		this.tipoOperadora = tipoOperadora;
	}

	public String getCdEOTClaro() {
		return cdEOTClaro;
	}

	public void setCdEOTClaro(String cdEOTClaro) {
		this.cdEOTClaro = cdEOTClaro;
	}

	public String getCdEOTLD() {
		return cdEOTLD;
	}

	public void setCdEOTLD(String cdEOTLD) {
		this.cdEOTLD = cdEOTLD;
	}

	public Long getCdProdutoCobilling() {
		return cdProdutoCobilling;
	}

	public void setCdProdutoCobilling(Long cdProdutoCobilling) {
		this.cdProdutoCobilling = cdProdutoCobilling;
	}

	public Long getCdTipoArquivo() {
		return cdTipoArquivo;
	}

	public void setCdTipoArquivo(Long cdTipoArquivo) {
		this.cdTipoArquivo = cdTipoArquivo;
	}

	public Long getAaCiclo() {
		return aaCiclo;
	}

	public void setAaCiclo(Long aaCiclo) {
		this.aaCiclo = aaCiclo;
	}

	public Long getMmCiclo() {
		return mmCiclo;
	}

	public void setMmCiclo(Long mmCiclo) {
		this.mmCiclo = mmCiclo;
	}

	public Long getCdCiclo() {
		return cdCiclo;
	}

	public void setCdCiclo(Long cdCiclo) {
		this.cdCiclo = cdCiclo;
	}
	
	
	
}
