package com.claro.sccweb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.internal.SccComposicaoGrupoEmailDAO;
import com.claro.cobillingweb.persistence.entity.SccEmailCobilling;
import com.claro.sccweb.service.AbstractService;
import com.claro.sccweb.service.SccComposicaoGrupoEmailService;

@Service
public class SccComposicaoGrupoEmailServiceImpl extends AbstractService
		implements SccComposicaoGrupoEmailService {
	
	@Autowired
	public SccComposicaoGrupoEmailDAO sccComposicaoGrupoEmailDAO;

	public SccComposicaoGrupoEmailDAO getSccComposicaoGrupoEmailDAO() {
		return sccComposicaoGrupoEmailDAO;
	}
	
	

	public void setSccComposicaoGrupoEmailDAO(
			SccComposicaoGrupoEmailDAO sccComposicaoGrupoEmailDAO) {
		this.sccComposicaoGrupoEmailDAO = sccComposicaoGrupoEmailDAO;
	}



	@Override
	public List<SccEmailCobilling> gerarListaEmailCadastrado(Long seqGrupo) throws DAOException {
		
		return this.sccComposicaoGrupoEmailDAO.gerarListaEmailCadastrado(seqGrupo);
	}
	
	
	
	
	

}
