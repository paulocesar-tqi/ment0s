package com.claro.cobillingweb.persistence.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;


@NamedQueries({
	@NamedQuery(name="SccItemRepasse.GET_ALL" , query="SELECT t from SccItemRepasse t")
		})
@Entity
@Table(name="SCC_ITEM_REPASSE")
public class SccItemRepasse {

	private static final long serialVersionUID = 1L;
	private Long cdItemRepasse;
	private String cdTipoCalculo;
	private String cdUsuarioManut;
	private String dsItemRepasse;
	private Date dtAtualizacao;
	private Date dtCriacao;	
	

    public SccItemRepasse() {
    }


	@Id
	@Column(name="CD_ITEM_REPASSE")
	public Long getCdItemRepasse() {
		return this.cdItemRepasse;
	}

	public void setCdItemRepasse(Long cdItemRepasse) {
		this.cdItemRepasse = cdItemRepasse;
	}


	@Column(name="CD_TIPO_CALCULO")
	public String getCdTipoCalculo() {
		return this.cdTipoCalculo;
	}

	public void setCdTipoCalculo(String cdTipoCalculo) {
		this.cdTipoCalculo = cdTipoCalculo;
	}


	@Column(name="CD_USUARIO_MANUT")
	public String getCdUsuarioManut() {
		return this.cdUsuarioManut;
	}

	public void setCdUsuarioManut(String cdUsuarioManut) {
		this.cdUsuarioManut = cdUsuarioManut;
	}


	@Column(name="DS_ITEM_REPASSE")
	public String getDsItemRepasse() {
		return this.dsItemRepasse;
	}

	public void setDsItemRepasse(String dsItemRepasse) {
		this.dsItemRepasse = dsItemRepasse;
	}


    @Temporal( TemporalType.DATE)
	@Column(name="DT_ATUALIZACAO")
	public Date getDtAtualizacao() {
		return this.dtAtualizacao;
	}

	public void setDtAtualizacao(Date dtAtualizacao) {
		this.dtAtualizacao = dtAtualizacao;
	}


    @Temporal( TemporalType.DATE)
	@Column(name="DT_CRIACAO")
	public Date getDtCriacao() {
		return this.dtCriacao;
	}

	public void setDtCriacao(Date dtCriacao) {
		this.dtCriacao = dtCriacao;
	}

	 
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj instanceof SccItemRepasse)
			{
			return ((SccItemRepasse)obj).equals(cdItemRepasse);
			}
		else
			{
			return false;
			}		
	}
}
