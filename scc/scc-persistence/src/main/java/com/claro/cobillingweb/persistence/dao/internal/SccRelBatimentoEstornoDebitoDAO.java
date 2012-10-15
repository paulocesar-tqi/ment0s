package com.claro.cobillingweb.persistence.dao.internal;

import java.util.List;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.view.SccRelBatimentoEstornoDebitoView;

/**
 * @author 92038883
 *
 */

public interface SccRelBatimentoEstornoDebitoDAO {
	
	List<SccRelBatimentoEstornoDebitoView> gerarRelBatimentoEstornoDebito(String cdEOTLD, String cdEOTClaro, String cdStatusArquivo, Integer mmCiclo, Integer aaCiclo) throws DAOException;

}
