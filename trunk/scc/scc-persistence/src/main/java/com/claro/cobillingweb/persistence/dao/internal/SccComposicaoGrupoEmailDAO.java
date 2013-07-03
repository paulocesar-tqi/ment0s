package com.claro.cobillingweb.persistence.dao.internal;

import java.util.List;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccComposicaoGrupoEmail;
import com.claro.cobillingweb.persistence.entity.SccEmailCobilling;

public interface SccComposicaoGrupoEmailDAO extends BasicDAO<SccComposicaoGrupoEmail>{

	List<SccEmailCobilling> gerarListaEmailCadastrado(Long sqGrupo)
			throws DAOException;
	

}
