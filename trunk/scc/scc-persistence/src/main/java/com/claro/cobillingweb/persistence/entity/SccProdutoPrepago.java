package com.claro.cobillingweb.persistence.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@NamedQueries({
	@NamedQuery(name="SccProdutoPrepago.GET_ALL" , query="SELECT t from SccProdutoPrepago t")
		})

@Entity
@Table(name="SCC_PRODUTO_PREPAGO")
public class SccProdutoPrepago {

	private static final long serialVersionUID = 1L;
	private String cdProdutoPrepago;
	private String cdUsuarioManut;
	private Date dtAtualizacao;
	private Date dtCriacao;
	private Date dtFimVigencia;
	private Date dtInicioVigencia;
	private String fgCobrarPrestacaoServico = "N";
	private String noProdutoPrepago;
	
	 public SccProdutoPrepago() {
	    }


		@Id
		@Column(name="CD_PRODUTO_PREPAGO")
		public String getCdProdutoPrepago() {
			return this.cdProdutoPrepago;
		}

		public void setCdProdutoPrepago(String cdProdutoPrepago) {
			this.cdProdutoPrepago = cdProdutoPrepago;
		}


		@Column(name="CD_USUARIO_MANUT")
		public String getCdUsuarioManut() {
			return this.cdUsuarioManut;
		}

		public void setCdUsuarioManut(String cdUsuarioManut) {
			this.cdUsuarioManut = cdUsuarioManut;
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


	    @Temporal( TemporalType.DATE)
		@Column(name="DT_FIM_VIGENCIA")
		public Date getDtFimVigencia() {
			return this.dtFimVigencia;
		}

		public void setDtFimVigencia(Date dtFimVigencia) {
			this.dtFimVigencia = dtFimVigencia;
		}


	    @Temporal( TemporalType.DATE)
		@Column(name="DT_INICIO_VIGENCIA")
		public Date getDtInicioVigencia() {
			return this.dtInicioVigencia;
		}

		public void setDtInicioVigencia(Date dtInicioVigencia) {
			this.dtInicioVigencia = dtInicioVigencia;
		}


		@Column(name="FG_COBRAR_PRESTACAO_SERVICO")
		public String getFgCobrarPrestacaoServico() {
			return this.fgCobrarPrestacaoServico;
		}

		public void setFgCobrarPrestacaoServico(String fgCobrarPrestacaoServico) {
			this.fgCobrarPrestacaoServico = fgCobrarPrestacaoServico;
		}


		@Column(name="NO_PRODUTO_PREPAGO")
		public String getNoProdutoPrepago() {
			return this.noProdutoPrepago;
		}

		public void setNoProdutoPrepago(String noProdutoPrepago) {
			this.noProdutoPrepago = noProdutoPrepago;
		}
}
