package com.claro.cobillingweb.persistence.dao.internal.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.impl.HibernateBasicDAOImpl;
import com.claro.cobillingweb.persistence.dao.internal.SccNaoConfAplAjustesDAO;
import com.claro.cobillingweb.persistence.dao.query.SccNaoConfAplAjustesSQL;
import com.claro.cobillingweb.persistence.filtro.SccFiltro;
import com.claro.cobillingweb.persistence.view.AjustesView;
import com.claro.cobillingweb.persistence.view.mapper.NativeSQLViewMapper;

@Repository
public class SccNaoConfAplAjustesDAOImpl extends HibernateBasicDAOImpl<AjustesView>
		implements SccNaoConfAplAjustesDAO {


	
	@Override
	public List<AjustesView> gerarRelatorioNaoConfAplAjuste(SccFiltro filtro)throws DAOException {
		
		List<AjustesView> listAjustes = null;
		try {
			Session session = getSessionFactory().getCurrentSession();
			NativeSQLViewMapper<AjustesView> mapper = new NativeSQLViewMapper<AjustesView>(session, SccNaoConfAplAjustesSQL.SQL, AjustesView.class);
			mapper.addArgument("dtInicio", filtro.getDataInicialPeriodo(), SccNaoConfAplAjustesSQL.FILTRO_DT_INICIO);
			mapper.addArgument("dtFim", filtro.getDataFinalPeriodo(), SccNaoConfAplAjustesSQL.FILTRO_DT_FIM);
			if(filtro.getOperadoraClaro() != null && !filtro.getOperadoraClaro().equals(BasicDAO.GET_ALL_STRING)){
				mapper.addArgument("cdEOTClaro", filtro.getOperadoraClaro(), SccNaoConfAplAjustesSQL.FILTRO_CD_EOT_CLARO);
			}
			
			if(filtro.getOperadoraExterna() != null && !filtro.getOperadoraExterna().equals(BasicDAO.GET_ALL_STRING)){
				mapper.addArgument("cdEOTLD", filtro.getOperadoraExterna(), SccNaoConfAplAjustesSQL.FILTRO_CD_EOT_LD);
			}
			
			mapper.setProjections(SccNaoConfAplAjustesSQL.PROJECTION);
			mapper.addResultMap("operadoraLD", String.class);
			mapper.addResultMap("operadoraClaro", String.class);
			mapper.addResultMap("uf", String.class);
			mapper.addResultMap("dataAjuste", Date.class);
			mapper.addResultMap("valorAjustado", Double.class);
			
			listAjustes = (List<AjustesView>) mapper.execute();
			
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
		
		return listAjustes;
	}
	

	@Override
	public List<AjustesView> getAll() throws DAOException {
		
		return null;
	}


}
