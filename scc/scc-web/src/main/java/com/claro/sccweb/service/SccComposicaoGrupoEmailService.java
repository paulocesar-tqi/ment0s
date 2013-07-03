package com.claro.sccweb.service;

import java.util.List;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccEmailCobilling;

public interface SccComposicaoGrupoEmailService {

	List<SccEmailCobilling> gerarListaEmailCadastrado(Long seqGrupo) throws DAOException;

}
