package com.claro.cobillingweb.persistence.dao.internal;

import java.util.Date;
import java.util.List;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.view.SccRetornoRepasseView;

public interface SccRetornoRepasseDAO extends BasicDAO<List<Object[]>>{
	
	public List<SccRetornoRepasseView> pesquisaRetornoRepasse(String cdEOTClaro, String cdEOTLD, Long cdProdutoCobilling, Date dtInicialRepasse, Date  dtFinalRepasse) throws DAOException;
	
	public List<SccRetornoRepasseView> pesquisaRetornoRepasseAss(String cdEOTClaro, String cdEOTLD, Long cdProdutoCobilling, Date dtInicialRepasse, Date  dtFinalRepasse) throws DAOException;
	
}
