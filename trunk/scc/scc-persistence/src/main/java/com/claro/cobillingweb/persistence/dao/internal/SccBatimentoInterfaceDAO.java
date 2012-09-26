package com.claro.cobillingweb.persistence.dao.internal;



import java.util.Date;
import java.util.List;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.view.SccBatimentoInterfaceView;

public interface SccBatimentoInterfaceDAO extends BasicDAO<SccBatimentoInterfaceView> {
	
	List<SccBatimentoInterfaceView> listarBatimentoInterface(Date dtInicio, Date dtFim, String cdEOTLD, String cdEOTClaro, String tpArquivo) throws DAOException;

}
