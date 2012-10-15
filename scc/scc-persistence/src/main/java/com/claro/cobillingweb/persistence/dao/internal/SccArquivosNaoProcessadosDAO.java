package com.claro.cobillingweb.persistence.dao.internal;



import java.util.Date;
import java.util.List;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.view.SccArquivosNaoProcessadosView;

public interface SccArquivosNaoProcessadosDAO extends BasicDAO<SccArquivosNaoProcessadosView> {
	
	List<SccArquivosNaoProcessadosView> gerarRelatorioArquivosNaoProcessados(Date dtInicial, Date dtFinal, String noArquivoGerado) throws DAOException;

}
