package com.claro.cobillingweb.persistence.dao.internal;



import java.util.Date;
import java.util.List;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.view.RelAlarmeOperacionalView;

public interface SccRelatorioAlarmeOperacionalDAO extends BasicDAO<List<Object[]>> {
	
	public List<RelAlarmeOperacionalView> listarAlarmeOperacional(Date dtInicio, Date dtFim, String nomeArquivo) throws DAOException;

}
