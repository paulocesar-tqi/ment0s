package com.claro.cobillingweb.persistence.dao.internal;

import java.util.Date;
import java.util.List;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccContratoAcordado;

public interface SccContratoAcordadoDAO extends BasicDAO<SccContratoAcordado> {

	/**
	 * Pesquisa contratos acordados entre a Claro e a operadora LD para um dado produto.
	 * @param cdEOT - EOT da Operadora LD.
	 * @param cdProduto - C�digo do Produto de cobilling.
	 * @return
	 * @throws DAOException
	 */
	public List<SccContratoAcordado> pesquisaContratosPorProduto(String cdEOT,Long cdProduto) throws DAOException;
	
	/**
	 * Pesquisa contratos acordados entre holdings/operadoras Claro e operadoras LD em um dado per�odo de vig�ncia.
	 * Os par�metros dtInicia e dtFinal representam o per�odo de tempo em que busca-se esses contratos , ou seja , as operadoras envolvidas
	 * devem ter contratos acordados ativos durante esse per�odo. 	
	 * @param cdEOTClaro - EOT da Holding/Operadora Claro.
	 * @param cdEOTLD - EOT da Operadora LD.
	 * @param cdProduto - C�digo do produto de cobilling.
	 * @param dtInicio - In�cio do periodo a ser pesquisado.
	 * @param dtFinal - Final do per�odo a ser pesquisado.
	 * @param holding - Indica se a operadora Claro informada � uma holding.
	 * @return
	 * @throws DAOException
	 */
	public List<SccContratoAcordado> pesquisaContratosPorProduto(String cdEOTClaro,String cdEOTLD,Long cdProduto,Date dtInicio,Date dtFinal,boolean holding) throws DAOException;
	
	/**
	 * Pesquisa a composi��o de um contrato de acordo com o nome desse contrato.
	 * @param dsContratoCobilling
	 * @return
	 * @throws DAOException
	 */
	public List<SccContratoAcordado> pesquisaAcordosContrato(Long cdContratoCobilling) throws DAOException;
	
	/**
	 * Pesquisa a composi��o de um contrato de acordo com o nome desse contrato.
	 * @param dsContratoCobilling
	 * @return
	 * @throws DAOException
	 */
	public void atualizarAcordosContrato(SccContratoAcordado entity) throws DAOException;
	
	
}
