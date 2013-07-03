package com.claro.sccweb.form;

import java.util.List;

import com.claro.cobillingweb.persistence.entity.SccComposicaoGrupoEmail;
import com.claro.cobillingweb.persistence.entity.SccEmailCobilling;
import com.claro.cobillingweb.persistence.entity.SccGrupoCobilling;

public class CadastroEmailGrupoForm extends BaseForm{
	
	
	private SccGrupoCobilling sccGrupo;
	
	private Long seqGrupo;
	
	private List<SccGrupoCobilling>listGrupoCobilling;
	
	
	private List<SccComposicaoGrupoEmail> listComposicaoGrupoEmail;
	
	private SccComposicaoGrupoEmail sccComposicaoGrupoEmail;
	
	private SccEmailCobilling sccEmailCobilling;
	
	private List<SccEmailCobilling> listEmailCobilling;
	
	private String noEmail;


	public SccGrupoCobilling getSccGrupo() {
		return sccGrupo;
	}


	public void setSccGrupo(SccGrupoCobilling sccGrupo) {
		this.sccGrupo = sccGrupo;
	}


	public Long getSeqGrupo() {
		return seqGrupo;
	}


	public void setSeqGrupo(Long seqGrupo) {
		this.seqGrupo = seqGrupo;
	}


	public List<SccGrupoCobilling> getListGrupoCobilling() {
		return listGrupoCobilling;
	}


	public void setListGrupoCobilling(List<SccGrupoCobilling> listGrupoCobilling) {
		this.listGrupoCobilling = listGrupoCobilling;
	}


	public List<SccComposicaoGrupoEmail> getListComposicaoGrupoEmail() {
		return listComposicaoGrupoEmail;
	}


	public void setListComposicaoGrupoEmail(
			List<SccComposicaoGrupoEmail> listComposicaoGrupoEmail) {
		this.listComposicaoGrupoEmail = listComposicaoGrupoEmail;
	}


	public SccComposicaoGrupoEmail getSccComposicaoGrupoEmail() {
		return sccComposicaoGrupoEmail;
	}


	public void setSccComposicaoGrupoEmail(
			SccComposicaoGrupoEmail sccComposicaoGrupoEmail) {
		this.sccComposicaoGrupoEmail = sccComposicaoGrupoEmail;
	}


	public SccEmailCobilling getSccEmailCobilling() {
		return sccEmailCobilling;
	}


	public void setSccEmailCobilling(SccEmailCobilling sccEmailCobilling) {
		this.sccEmailCobilling = sccEmailCobilling;
	}


	public void setListEmailCobilling(List<SccEmailCobilling> listEmailCobilling) {
		this.listEmailCobilling = listEmailCobilling;
	}


	public List<SccEmailCobilling> getListEmailCobilling() {
		return listEmailCobilling;
	}


	public String getNoEmail() {
		return noEmail;
	}


	public void setNoEmail(String noEmail) {
		this.noEmail = noEmail;
	}
	
	
	
	
	
	

}
