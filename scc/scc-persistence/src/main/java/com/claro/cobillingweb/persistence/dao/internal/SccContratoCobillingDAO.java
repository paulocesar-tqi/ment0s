package com.claro.cobillingweb.persistence.dao.internal;

import java.util.Date;
import java.util.List;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.FwjBaseDaoHibernate;
import com.claro.cobillingweb.persistence.entity.SccContratoCobilling;
import com.claro.cobillingweb.persistence.entity.SccContratoCobillingPK;
import com.claro.cobillingweb.persistence.view.SccContratoCobillingSearchView;
import com.claro.cobillingweb.persistence.view.SccProdutoContratadoView;

public interface SccContratoCobillingDAO extends FwjBaseDaoHibernate<SccContratoCobilling, String>{

	public List<SccContratoCobillingSearchView> pesquisaContratosOperadoras(String cdEotClaro,String cdEotLD) throws DAOException;

	List<SccContratoCobillingSearchView> pesquisaContratosOperadorasByFiltro(
			String cdEotClaro, String cdEotLD, String cdTipo)
			throws DAOException;

	SccContratoCobilling findByFiltro(SccContratoCobillingPK pk)
			throws DAOException;

	void excluirEntity(SccContratoCobillingPK pk) throws DAOException;

	List<SccContratoCobillingSearchView> pesquisarProdutosContratos(
			String cdEotClaro, String cdEotLD) throws DAOException;

	List<SccProdutoContratadoView> pesquisarProdutoContatatado(
			String cdEotClaro, String cdEotLd, Date dtInicioContrato)
			throws DAOException;
	
}
