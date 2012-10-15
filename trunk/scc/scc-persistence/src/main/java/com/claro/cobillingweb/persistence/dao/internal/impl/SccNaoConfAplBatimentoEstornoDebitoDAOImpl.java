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
import com.claro.cobillingweb.persistence.dao.internal.SccNaoConfAplBatimentoEstornoDebitoDAO;
import com.claro.cobillingweb.persistence.dao.query.SccNaoConfAplBatimentoEstornoDebitoSQL;
import com.claro.cobillingweb.persistence.filtro.SccFiltro;
import com.claro.cobillingweb.persistence.view.BatimentoEstornoDebitoView;
import com.claro.cobillingweb.persistence.view.mapper.NativeSQLViewMapper;

/**
 * @author 93046251
 *
 */
@Repository
public class SccNaoConfAplBatimentoEstornoDebitoDAOImpl extends
		HibernateBasicDAOImpl<BatimentoEstornoDebitoView> implements
		SccNaoConfAplBatimentoEstornoDebitoDAO {

	@Override
	public List<BatimentoEstornoDebitoView> getAll() throws DAOException {
		
		return null;
	}

	@Override
	public List<BatimentoEstornoDebitoView> gerarRelatorioNaoConfBatimentoEstornoDebito(
			SccFiltro filtro) throws DAOException {
		
		List<BatimentoEstornoDebitoView> listaBatimentoEstornoDebito = null;
		try {
			Session session = getSessionFactory().getCurrentSession();
			NativeSQLViewMapper<BatimentoEstornoDebitoView> mapper = new NativeSQLViewMapper<BatimentoEstornoDebitoView>(session, SccNaoConfAplBatimentoEstornoDebitoSQL.SQL, BatimentoEstornoDebitoView.class);
			mapper.addArgument("aaReferencia", filtro.getAaCiclo());
			
			if(filtro.getOperadoraExterna() != null && !filtro.getOperadoraExterna().equals(BasicDAO.GET_ALL_STRING)){
				mapper.addArgument("cdEotLd", filtro.getOperadoraExterna(), SccNaoConfAplBatimentoEstornoDebitoSQL.FILTRO_CD_EOT_LT);
			}
			
			if(filtro.getOperadoraClaro() != null && !filtro.getOperadoraClaro().equals(BasicDAO.GET_ALL_STRING)){
				mapper.addArgument("cdEotClaro", filtro.getOperadoraClaro(), SccNaoConfAplBatimentoEstornoDebitoSQL.FILTRO_CD_EOT_CLARO);
			}
			
			if(filtro.getMmCiclo() != null && !filtro.getMmCiclo().equals(BasicDAO.GET_ALL)){
				mapper.addArgument("mmReferencia", filtro.getOperadoraClaro(), SccNaoConfAplBatimentoEstornoDebitoSQL.FILTRO_MM_REFERENCIA);
			}

			
			mapper.addArgument("operadoraLD", String.class);
			mapper.addResultMap("operadoraClaro", String.class);
			mapper.addResultMap("uf", String.class);
			mapper.addResultMap("ciclo", Integer.class);
			mapper.addResultMap("notaFiscal", Long.class);
			mapper.addResultMap("estornoDebitoToAjustado", Double.class);
			mapper.addResultMap("chamadasToAjustado", Double.class);
			mapper.addResultMap("diferencaTOajustado", Double.class);
			mapper.addResultMap("estornoDebitoCrediCMS", Double.class);
			mapper.addResultMap("chamadasCrediCMS", Double.class);
			mapper.addResultMap("diferencaCrediCMS", Double.class);
			
			listaBatimentoEstornoDebito = (List<BatimentoEstornoDebitoView>) mapper.execute();
			
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
		
		return listaBatimentoEstornoDebito;

	}


}
