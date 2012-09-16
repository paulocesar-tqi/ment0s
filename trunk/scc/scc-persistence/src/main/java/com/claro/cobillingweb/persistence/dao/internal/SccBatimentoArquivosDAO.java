package com.claro.cobillingweb.persistence.dao.internal;



import java.util.Date;
import java.util.List;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.view.SccBatimentoArquivosView;

public interface SccBatimentoArquivosDAO extends BasicDAO<SccBatimentoArquivosView> {
	
	List<SccBatimentoArquivosView> gerarRelatorioBatimento(Date dtInicioBatimento, Date dtFimBatimento, String cdEOTLD, String cdEOTClaro, String tpArquivo) throws DAOException;

}
