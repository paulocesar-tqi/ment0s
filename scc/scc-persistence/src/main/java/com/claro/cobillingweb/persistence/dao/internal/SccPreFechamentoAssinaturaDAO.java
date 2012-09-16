package com.claro.cobillingweb.persistence.dao.internal;

import java.util.List;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccPreFechamentoAssinatura;

public interface SccPreFechamentoAssinaturaDAO extends BasicDAO<SccPreFechamentoAssinatura>{
	
	/**
	 * Pesquisa assinaturas de acordo com filtro informado.
	 * Aceita BasicDAO.GET_ALL_STRING para operadora Claro , operadora LD e Produto Pré-pago.
	 * @param filtro
	 * @return
	 * @throws DAOException
	 */
	public List<SccPreFechamentoAssinatura> pesquisaAssinaturas(SccPreFechamentoAssinatura filtro) throws DAOException;

}
