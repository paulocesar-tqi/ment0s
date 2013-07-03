/**
 * 
 */
package com.claro.cobillingweb.persistence.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * @author 93046251
 *
 */

@Entity
@SequenceGenerator(name="SCC_RELATORIO_COBILLING_SQ01" , sequenceName="SCC_RELATORIO_COBILLING_SQ01")
@Table(name="SCC_RELATORIO_COBILLING")
public class SccRelatorioCobilling extends FwjBaseEntidade {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4530729919014642913L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SCC_RELATORIO_COBILLING_SQ01")
	@Column(name="SQ_RELATORIO")
	private Long sqRelatorio;

	@Column(name="CD_USUARIO_MANUT")
	private String cdUsuarioManut;

    @Temporal( TemporalType.DATE)
	@Column(name="DT_ATUALIZACAO")
	private Date dtAtualizacao;

    @Temporal( TemporalType.DATE)
	@Column(name="DT_CRIACAO")
	private Date dtCriacao;

    @Temporal( TemporalType.DATE)
	@Column(name="DT_FIM_VIGENCIA")
	private Date dtFimVigencia;

    @Temporal( TemporalType.DATE)
	@Column(name="DT_INICIO_VIGENCIA")
	private Date dtInicioVigencia;

	@Column(name="NO_RELATORIO")
	private String noRelatorio;

	//bi-directional many-to-one association to SccAssociacaoRelatorioGrupo
	@JsonIgnore
	@OneToMany(mappedBy="sccRelatorioCobilling", fetch=FetchType.LAZY)
	private Set<SccAssociacaoRelatorioGrupo> sccAssociacaoRelatorioGrupos;

	//bi-directional many-to-one association to SccRelatorioSumarizado
	@JsonIgnore
	@OneToMany(mappedBy="sccRelatorioCobilling", fetch=FetchType.LAZY)
	private Set<SccRelatorioSumarizado> sccRelatorioSumarizados;

	public Long getSqRelatorio() {
		return sqRelatorio;
	}

	public void setSqRelatorio(Long sqRelatorio) {
		this.sqRelatorio = sqRelatorio;
	}

	public String getCdUsuarioManut() {
		return cdUsuarioManut;
	}

	public void setCdUsuarioManut(String cdUsuarioManut) {
		this.cdUsuarioManut = cdUsuarioManut;
	}

	public Date getDtAtualizacao() {
		return dtAtualizacao;
	}

	public void setDtAtualizacao(Date dtAtualizacao) {
		this.dtAtualizacao = dtAtualizacao;
	}

	public Date getDtCriacao() {
		return dtCriacao;
	}

	public void setDtCriacao(Date dtCriacao) {
		this.dtCriacao = dtCriacao;
	}

	public Date getDtFimVigencia() {
		return dtFimVigencia;
	}

	public void setDtFimVigencia(Date dtFimVigencia) {
		this.dtFimVigencia = dtFimVigencia;
	}

	public Date getDtInicioVigencia() {
		return dtInicioVigencia;
	}

	public void setDtInicioVigencia(Date dtInicioVigencia) {
		this.dtInicioVigencia = dtInicioVigencia;
	}

	public String getNoRelatorio() {
		return noRelatorio;
	}

	public void setNoRelatorio(String noRelatorio) {
		this.noRelatorio = noRelatorio;
	}

	public Set<SccAssociacaoRelatorioGrupo> getSccAssociacaoRelatorioGrupos() {
		return sccAssociacaoRelatorioGrupos;
	}

	public void setSccAssociacaoRelatorioGrupos(
			Set<SccAssociacaoRelatorioGrupo> sccAssociacaoRelatorioGrupos) {
		this.sccAssociacaoRelatorioGrupos = sccAssociacaoRelatorioGrupos;
	}

	public Set<SccRelatorioSumarizado> getSccRelatorioSumarizados() {
		return sccRelatorioSumarizados;
	}

	public void setSccRelatorioSumarizados(
			Set<SccRelatorioSumarizado> sccRelatorioSumarizados) {
		this.sccRelatorioSumarizados = sccRelatorioSumarizados;
	}

	
	
	
	
	
	
	
	
/*	private Long codigo;
	
	private String descricao;
	
	private Date dtInicioVigencia;
	
	private Date dtFimVigencia;
	
	private Date dtCriacao;
	
	private Date dtAtualizacao;
	
	private String cdUsuarioManutencao;
	
	private List<SccGrupoCobilling> lstGrupos;

*/	

/*
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SCC_RELATORIO_COBILLING_SQ01")
	@Column(name="SQ_RELATORIO")
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	@Column(name="NO_RELATORIO")
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Column(name="DT_INICIO_VIGENCIA")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getDtInicioVigencia() {
		return dtInicioVigencia;
	}

	public void setDtInicioVigencia(Date dtInicioVigencia) {
		this.dtInicioVigencia = dtInicioVigencia;
	}

	@Column(name="DT_FIM_VIGENCIA")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getDtFimVigencia() {
		return dtFimVigencia;
	}

	public void setDtFimVigencia(Date dtFimVigencia) {
		this.dtFimVigencia = dtFimVigencia;
	}

	@Column(name="DT_CRIACAO")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getDtCriacao() {
		return dtCriacao;
	}

	public void setDtCriacao(Date dtCriacao) {
		this.dtCriacao = dtCriacao;
	}

	@Column(name="DT_ATUALIZACAO")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getDtAtualizacao() {
		return dtAtualizacao;
	}

	public void setDtAtualizacao(Date dtAtualizacao) {
		this.dtAtualizacao = dtAtualizacao;
	}

	@Column(name="CD_USUARIO_MANUT")
	public String getCdUsuarioManutencao() {
		return cdUsuarioManutencao;
	}

	public void setCdUsuarioManutencao(String cdUsuarioManutencao) {
		this.cdUsuarioManutencao = cdUsuarioManutencao;
	}
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="SCC_ASSOCIACAO_RELATORIO_GRUPO", 
	joinColumns={@JoinColumn(name="SQ_RELATORIO")},
	inverseJoinColumns={@JoinColumn(name="SQ_GRUPO")})
	public List<SccGrupoCobilling> getLstGrupos() {
		return lstGrupos;
	}

	public void setLstGrupos(List<SccGrupoCobilling> lstGrupos) {
		this.lstGrupos = lstGrupos;
	}
	
*/
}
