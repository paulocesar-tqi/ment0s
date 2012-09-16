package com.claro.cobillingweb.persistence.dao.internal;

import java.util.Date;
import java.util.List;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccRepasseServicoAdicional;

public interface SccRepasseServicoAdicionalDAO extends BasicDAO<SccRepasseServicoAdicional> {

	/**
	 * Pesquisa o detalhamento de assinaturas do repasse.
	 * @param cdEOTLD EOT da operadora LD.
	 * @param cdProdutoCobilling PK do produto de cobilling.
	 * @param cdPeriodicidade PK da periodicidade.
	 * @param dtInicioRepasse Data início do repasse.
	 * @return
	 * @throws DAOException
	 */
	public List<SccRepasseServicoAdicional> pesquisaAssinaturas(String cdEOTClaro,String cdEOTLD,Long cdProdutoCobilling,Long cdPeriodicidade,Date dtInicioRepasse,boolean holding) throws DAOException;
	
}
