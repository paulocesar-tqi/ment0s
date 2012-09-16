package com.claro.cobillingweb.persistence.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@SequenceGenerator(name="SCC_CTL_PARALIZACAO_PLAT_SQ01" , sequenceName="SCC_CTL_PARALIZACAO_PLAT_SQ01")
@Table(name="SCC_CTL_PARALIZACAO_PLAT")
public class SccCtlParalizacaoPlat {
	
	private Long sqParalizacaoPlat;
	private String cdAreaPlataforma;
	private String cdEotLd;
	private Long cdTecnologiaPlataforma;
	private String cdTipoFalha;
	private String cdUsuarioRespRegistro;
	private Date dtEnvioBoletimLd;
	private Date dtFimParalizacao;
	private Date dtInicioParalizacao;
	private Date dtRegistroOcorrencia;
	private String fgSolicDesbloqueioLd;
	private String nuBoletimClaro;
	private String nuBoletimLd;
	private Long qtDuracaoParalizacao;
	private String txMotivoFalha;
	private String txObservacao;
	
    public SccCtlParalizacaoPlat() {
    }
    
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SCC_CTL_PARALIZACAO_PLAT_SQ01")
	@Column(name="SQ_PARALIZACAO_PLAT")
	public Long getSqParalizacaoPlat() {
		return this.sqParalizacaoPlat;
	}
	
	public void setSqParalizacaoPlat(Long sqParalizacaoPlat) {
		this.sqParalizacaoPlat = sqParalizacaoPlat;
	}
	
	@Column(name="CD_AREA_PLATAFORMA")
	public String getCdAreaPlataforma() {
		return this.cdAreaPlataforma;
	}
	
	public void setCdAreaPlataforma(String cdAreaPlataforma) {
		this.cdAreaPlataforma = cdAreaPlataforma;
	}
	
	@Column(name="CD_EOT_LD")
	public String getCdEotLd() {
		return this.cdEotLd;
	}
	
	public void setCdEotLd(String cdEotLd) {
		this.cdEotLd = cdEotLd;
	}
	
	@Column(name="CD_TECNOLOGIA_PLATAFORMA")
	public Long getCdTecnologiaPlataforma() {
		return this.cdTecnologiaPlataforma;
	}
	
	public void setCdTecnologiaPlataforma(Long cdTecnologiaPlataforma) {
		this.cdTecnologiaPlataforma = cdTecnologiaPlataforma;
	}
	
	@Column(name="CD_TIPO_FALHA")
	public String getCdTipoFalha() {
		return this.cdTipoFalha;
	}
	
	public void setCdTipoFalha(String cdTipoFalha) {
		this.cdTipoFalha = cdTipoFalha;
	}
	
	@Column(name="CD_USUARIO_RESP_REGISTRO")
	public String getCdUsuarioRespRegistro() {
		return this.cdUsuarioRespRegistro;
	}
	
	public void setCdUsuarioRespRegistro(String cdUsuarioRespRegistro) {
		this.cdUsuarioRespRegistro = cdUsuarioRespRegistro;
	}
	
    @Temporal( TemporalType.DATE)
	@Column(name="DT_ENVIO_BOLETIM_LD")
	public Date getDtEnvioBoletimLd() {
		return this.dtEnvioBoletimLd;
	}
    
	public void setDtEnvioBoletimLd(Date dtEnvioBoletimLd) {
		this.dtEnvioBoletimLd = dtEnvioBoletimLd;
	}
	
    @Temporal( TemporalType.DATE)
	@Column(name="DT_FIM_PARALIZACAO")
	public Date getDtFimParalizacao() {
		return this.dtFimParalizacao;
	}
    
	public void setDtFimParalizacao(Date dtFimParalizacao) {
		this.dtFimParalizacao = dtFimParalizacao;
	}
	
    @Temporal( TemporalType.DATE)
	@Column(name="DT_INICIO_PARALIZACAO")
	public Date getDtInicioParalizacao() {
		return this.dtInicioParalizacao;
	}
    
	public void setDtInicioParalizacao(Date dtInicioParalizacao) {
		this.dtInicioParalizacao = dtInicioParalizacao;
	}
	
    @Temporal( TemporalType.DATE)
	@Column(name="DT_REGISTRO_OCORRENCIA")
	public Date getDtRegistroOcorrencia() {
		return this.dtRegistroOcorrencia;
	}
    
	public void setDtRegistroOcorrencia(Date dtRegistroOcorrencia) {
		this.dtRegistroOcorrencia = dtRegistroOcorrencia;
	}
	
	@Column(name="FG_SOLIC_DESBLOQUEIO_LD")
	public String getFgSolicDesbloqueioLd() {
		return this.fgSolicDesbloqueioLd;
	}
	
	public void setFgSolicDesbloqueioLd(String fgSolicDesbloqueioLd) {
		this.fgSolicDesbloqueioLd = fgSolicDesbloqueioLd;
	}
	
	@Column(name="NU_BOLETIM_CLARO")
	public String getNuBoletimClaro() {
		return this.nuBoletimClaro;
	}
	
	public void setNuBoletimClaro(String nuBoletimClaro) {
		this.nuBoletimClaro = nuBoletimClaro;
	}
	
	@Column(name="NU_BOLETIM_LD")
	public String getNuBoletimLd() {
		return this.nuBoletimLd;
	}
	
	public void setNuBoletimLd(String nuBoletimLd) {
		this.nuBoletimLd = nuBoletimLd;
	}
	
	@Column(name="QT_DURACAO_PARALIZACAO")
	public Long getQtDuracaoParalizacao() {
		return this.qtDuracaoParalizacao;
	}
	
	public void setQtDuracaoParalizacao(Long qtDuracaoParalizacao) {
		this.qtDuracaoParalizacao = qtDuracaoParalizacao;
	}
	
	@Column(name="TX_MOTIVO_FALHA")
	public String getTxMotivoFalha() {
		return this.txMotivoFalha;
	}
	
	public void setTxMotivoFalha(String txMotivoFalha) {
		this.txMotivoFalha = txMotivoFalha;
	}
	
	@Column(name="TX_OBSERVACAO")
	public String getTxObservacao() {
		return this.txObservacao;
	}
	
	public void setTxObservacao(String txObservacao) {
		this.txObservacao = txObservacao;
	}
	
}
