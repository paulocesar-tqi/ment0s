/**
 * 
 */
package com.claro.cobillingweb.persistence.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author 93046251
 *
 */

@Entity
@SequenceGenerator(name="SCC_RELATORIO_COBILLING_SQ01" , sequenceName="SCC_RELATORIO_COBILLING_SQ01")
@Table(name="SCC_RELATORIO_COBILLING")
public class SccRelatorioCobilling {
	
	
	private Long codigo;
	
	private String descricao;
	
	private Date dtInicioVigencia;
	
	private Date dtFimVigencia;
	
	private Date dtCriacao;
	
	private Date dtAtualizacao;
	
	private String cdUsuarioManutencao;
	
	private List<SccGrupoCobilling> lstGrupos;

	
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
	

}
