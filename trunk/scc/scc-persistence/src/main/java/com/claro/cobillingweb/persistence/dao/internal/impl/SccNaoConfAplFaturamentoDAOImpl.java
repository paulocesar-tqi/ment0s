/**
 * 
 */
package com.claro.cobillingweb.persistence.dao.internal.impl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.impl.HibernateBasicDAOImpl;
import com.claro.cobillingweb.persistence.dao.internal.SccNaoConfAplFaturamentoDAO;
import com.claro.cobillingweb.persistence.dao.query.SccNãoConfAplFaturamentoSQL;
import com.claro.cobillingweb.persistence.filtro.SccFiltro;
import com.claro.cobillingweb.persistence.view.FaturamentoView;
import com.claro.cobillingweb.persistence.view.mapper.NativeSQLViewMapper;

/**
 * @author 93046251
 *
 */
@Repository
public class SccNaoConfAplFaturamentoDAOImpl extends HibernateBasicDAOImpl<FaturamentoView>
		implements SccNaoConfAplFaturamentoDAO {

	@Override
	public List<FaturamentoView> gerarRelatorioNaoConfFaturamento(
			SccFiltro filtro) throws DAOException {
		
		List<FaturamentoView> listFaturamento = null;
		try {
			Session session = getSessionFactory().getCurrentSession();
			NativeSQLViewMapper<FaturamentoView> mapper = new NativeSQLViewMapper<FaturamentoView>(session, SccNãoConfAplFaturamentoSQL.SQL, FaturamentoView.class);
			if(filtro.getCdCiclo() != null && filtro.getMmCiclo() > 0 && filtro.getAaCiclo() != null ){
				mapper.addArgument("cdCiclo", filtro.getCdCiclo(), SccNãoConfAplFaturamentoSQL.FILTRO_CD_CICLO);
				mapper.addArgument("mmCiclo", filtro.getMmCiclo(), SccNãoConfAplFaturamentoSQL.FILTRO_MM_CICLO);
				mapper.addArgument("aaCiclo", filtro.getAaCiclo(), SccNãoConfAplFaturamentoSQL.FITLRO_AA_CICLO);
			}
			
			
			if(filtro.getCdCsp() != null && !filtro.getCdCsp().equals(BasicDAO.GET_ALL_STRING)){
				mapper.addArgument("cdCSP", filtro.getCdCsp(), SccNãoConfAplFaturamentoSQL.FILTRO_CSP);
			}
			
			
			mapper.addResultMap("operadoraClaro", String.class);
			mapper.addResultMap("cdCsp", String.class);
			mapper.addResultMap("uf", String.class);
			mapper.addResultMap("cdCiclo", Long.class);
			mapper.addResultMap("mmCiclo", Long.class);
			mapper.addResultMap("aaCiclo", Long.class);
			mapper.addResultMap("serieNotaFiscal", String.class);
			mapper.addResultMap("subSerieNotaFiscal", String.class);
			mapper.addResultMap("numNotaFiscal", Integer.class);
			
			listFaturamento = (List<FaturamentoView>) mapper.execute();
			
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
		
		return listFaturamento;

	}

	@Override
	public List<FaturamentoView> getAll() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

}
