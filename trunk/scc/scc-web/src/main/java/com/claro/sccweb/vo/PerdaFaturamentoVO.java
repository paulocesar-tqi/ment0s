/**
 * 
 */
package com.claro.sccweb.vo;

import java.util.Date;

import com.claro.cobillingweb.persistence.utils.CommonsUtils;




/**
 * @author 93046251
 *
 */
public class PerdaFaturamentoVO implements Comparable<PerdaFaturamentoVO>, Cloneable {
	
	private Date dtProcExterna;
	
	private String operadoraLd;
	
	private String operadoraClaro;
	
	private Double valorLiquido = 0d ;
	
	private Double valorBruto = 0d ;
	
	private Long qtdCdr = 0L;
	
	private String status;
	
    private String fileType;
    
    private Integer cdStatusCdr;
    
    private String cdSubStatusCdr;
    
    private String dtReferencia;
    
	public void addVlBruto(double vlBruto) {
		this.valorBruto += vlBruto;
	}

	public void addQtCdrs(long qtdCdr) {
		this.qtdCdr += qtdCdr;
	}
    
	public void addVlLiquido(double valorLiquido) {
		this.valorLiquido += valorLiquido;
	}


	
	public Date getDtProcExterna() {
		return dtProcExterna;
	}

	public void setDtProcExterna(Date dtProcExterna) {
		this.dtProcExterna = dtProcExterna;
	}

	public String getOperadoraLd() {
		return operadoraLd;
	}

	public void setOperadoraLd(String operadoraLd) {
		this.operadoraLd = operadoraLd;
	}

	public String getOperadoraClaro() {
		return operadoraClaro;
	}

	public void setOperadoraClaro(String operadoraClaro) {
		this.operadoraClaro = operadoraClaro;
	}

	public Double getValorLiquido() {
		return valorLiquido;
	}

	public void setValorLiquido(Double valorLiquido) {
		this.valorLiquido = valorLiquido;
	}

	public Double getValorBruto() {
		return valorBruto;
	}

	public void setValorBruto(Double valorBruto) {
		this.valorBruto = valorBruto;
	}

	public Long getQtdCdr() {
		return qtdCdr;
	}

	public void setQtdCdr(Long qtdCdr) {
		this.qtdCdr = qtdCdr;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public Integer getCdStatusCdr() {
		return cdStatusCdr;
	}

	public void setCdStatusCdr(Integer cdStatusCdr) {
		this.cdStatusCdr = cdStatusCdr;
	}
	
	
	public void addValorlLiquido(Double valorLiquido){
		this.valorLiquido += valorLiquido;
	}
	
	public void addValorBruto(Double valorBruto){
		this.valorBruto += valorBruto;
	}
	
	public void addQtdCdr(Long qtdCdr){
		this.qtdCdr += qtdCdr;
	}

	public String getCdSubStatusCdr() {
		return cdSubStatusCdr;
	}

	public void setCdSubStatusCdr(String cdSubStatusCdr) {
		this.cdSubStatusCdr = cdSubStatusCdr;
	}

	public String getDtReferencia() {
		return dtReferencia;
	}

	public void setDtReferencia(String dtReferencia) {
		this.dtReferencia = dtReferencia;
	}

	@Override
	public int hashCode() {
        int h = 1;
        h += CommonsUtils.getHash(dtProcExterna);
        h *= 37;
        h += CommonsUtils.getHash(operadoraLd);
        h *= 37;
        h += CommonsUtils.getHash(operadoraClaro);
        h *= 37;
        h += (int) cdStatusCdr;
        h *= 37;
        h += CommonsUtils.getHash(cdSubStatusCdr);
        return h;        
    }
    

	@Override
	public boolean equals(Object obj) {
        if (obj==this) { return true; }
        if (obj!=null && obj instanceof PerdaFaturamentoVO) {
        	PerdaFaturamentoVO other = (PerdaFaturamentoVO) obj;
            if (CommonsUtils.isEquals(dtProcExterna, other.dtProcExterna)) {
                if (CommonsUtils.isEquals(operadoraLd, other.operadoraLd)) {
                    if (CommonsUtils.isEquals(operadoraClaro, other.operadoraClaro)) {
                        if (cdStatusCdr == other.cdStatusCdr) {
                            if (cdSubStatusCdr == other.cdSubStatusCdr) {
                               return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

	@Override
	public String toString() {
		return "PerdaFaturamentoVO [dtProcExterna=" + dtProcExterna
				+ ", operadoraLd=" + operadoraLd + ", operadoraClaro="
				+ operadoraClaro + ", valorLiquido=" + valorLiquido
				+ ", valorBruto=" + valorBruto + ", qtdCdr=" + qtdCdr
				+ ", status=" + status + ", fileType=" + fileType
				+ ", cdStatusCdr=" + cdStatusCdr + ", cdSubStatusCdr="
				+ cdSubStatusCdr + ", dtReferencia=" + dtReferencia + "]";
	}
	
	
    /* (non-Javadoc)
     * @see java.lang.Object#clone()
     */
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

	@Override
	public int compareTo(PerdaFaturamentoVO obj) {
        int c = -1;
        if (obj!=null && obj instanceof PerdaFaturamentoVO) {
        	PerdaFaturamentoVO other = (PerdaFaturamentoVO) obj;
            c = CommonsUtils.compare(dtProcExterna, other.dtProcExterna);
            if (c==0) {
                c = CommonsUtils.compare(operadoraLd, other.operadoraLd);
                if (c==0) {
                    c = CommonsUtils.compare(operadoraClaro, other.operadoraClaro);
                    if (c==0) {
                        c = cdStatusCdr - other.cdStatusCdr;
                        if (c==0) {
                            c = CommonsUtils.compare(cdSubStatusCdr, other.cdSubStatusCdr);
                        }
                    }
                }
            }
        }
        return c;
	}

	
	
	
}
