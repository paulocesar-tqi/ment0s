package com.claro.sccweb.service;

import java.util.Date;
import java.util.List;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.view.RelAlarmeOperacionalView;

public interface RelatorioAlarmeOperacionalService {
		
	List<RelAlarmeOperacionalView> listarAlarmeOperacional(Date dtInicio, Date dtFim, String nomeArquivo) throws ServiceException, DAOException;

}
