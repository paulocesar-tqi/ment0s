package com.claro.cobillingweb.persistence.dao.internal.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.impl.HibernateBasicDAOImpl;
import com.claro.cobillingweb.persistence.dao.internal.SccPenalidadePorRejeicaoDAO;
import com.claro.cobillingweb.persistence.entity.SccPenalidadePorRejeicao;
import com.claro.cobillingweb.persistence.view.PenalidadeRejeicaoView;
import com.claro.cobillingweb.persistence.view.mapper.NativeSQLViewMapper;

public class SccPenalidadePorRejeicaoDAOImpl extends HibernateBasicDAOImpl<SccPenalidadePorRejeicao> implements SccPenalidadePorRejeicaoDAO {

	 
	public List<SccPenalidadePorRejeicao> getAll() throws DAOException {
		try{
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SccPenalidadePorRejeicao.class);
			return criteria.list();
		} catch (Exception e)
			{
			throw new DAOException(e.getMessage(), e);
			}
	}

	 
	public List<SccPenalidadePorRejeicao> pesquisar(String cdEotLd, String cdMotivoRejeicao) throws DAOException {
		try{
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SccPenalidadePorRejeicao.class);
			
			if(cdEotLd != null && !cdEotLd.equals(BasicDAO.GET_ALL_STRING))
				criteria.add(Restrictions.eq("id.cdEotLd", cdEotLd));

			if(cdMotivoRejeicao != null && !cdMotivoRejeicao.equals(BasicDAO.GET_ALL_STRING))
				criteria.add(Restrictions.eq("id.cdMotivoRejeicao", cdMotivoRejeicao));
			
			return criteria.list();
		} catch (Exception e)
			{
			throw new DAOException(e.getMessage(), e);
			}
	}


	@Override
	public List<PenalidadeRejeicaoView> pesquisarRelatorioPenalidadeRejeicao(
			String cdEOTClaro, String cdEOTLD, Long cdProdutoCobilling,
			Date dtInicialRepasse) throws DAOException {
		List<PenalidadeRejeicaoView> list = null;

		String sql = "SELECT  LD.DS_OPERADORA as operadoraLD, " +
					        "CLARO.DS_OPERADORA as operadoraClaro, " +
					        "REJ.DS_MOTIVO_REJEICAO as motivoRejeicao, " +
					        "COUNT(1) as qtdCdrs, " +
					        "SUM(CDR.MI_DURACAO_TARIFADA) as qtdeMinutos, " +
					        "SUM(CDR.VL_BRUTO_CHAMADA) as valor " +
					"FROM SCC_CDR_REPASSE CDR, SCC_MOTIVO_REJEICAO REJ, SCC_OPERADORA LD, SCC_OPERADORA CLARO " +
					"WHERE CDR.CD_MOTIVO_REJEICAO = REJ.CD_MOTIVO_REJEICAO " +
					"AND  CDR.CD_EOT_LD = LD.CD_EOT " +
					"AND  CDR.CD_EOT_CLARO = CLARO.CD_EOT " +
					"AND  CD_STATUS_CDR = 10 ";
		
		String filtroDataInicialRepasse = "	AND  TRUNC(DT_INICIO_REPASSE) = TRUNC(:dtInicialRepasse) ";
		String filtroCdProdutoCobilling = "	AND  CD_PRODUTO_COBILLING =  :cdProdutoCobilling  ";
		String filtroCdEOTLD = " AND  CD_EOT_LD = :cdEOTLD  ";
		String filtroCdEOTClaro = "	AND  CD_EOT_CLARO = :cdEOTClaro  ";
		
		String projections = "GROUP BY LD.DS_OPERADORA, "+
						         "CLARO.DS_OPERADORA, "+
						         "REJ.DS_MOTIVO_REJEICAO ";
		
		try {
			Session session = getSessionFactory().getCurrentSession();
			NativeSQLViewMapper<PenalidadeRejeicaoView> mapper = new NativeSQLViewMapper<PenalidadeRejeicaoView>(session, sql, PenalidadeRejeicaoView.class);
			if(dtInicialRepasse != null) {
				mapper.addArgument("dtInicialRepasse", dtInicialRepasse, filtroDataInicialRepasse);
			}
			
			if(cdEOTClaro != null && !cdEOTClaro.equals(BasicDAO.GET_ALL_STRING)){
				mapper.addArgument("cdEOTClaro", cdEOTClaro, filtroCdEOTClaro);
			}
			
			if(cdEOTLD != null && !cdEOTLD.equals(BasicDAO.GET_ALL_STRING)){
				mapper.addArgument("cdEOTLD", cdEOTLD, filtroCdEOTLD);
			}

			if(cdProdutoCobilling != null){
				mapper.addArgument("cdProdutoCobilling", cdProdutoCobilling, filtroCdProdutoCobilling);
			}

			mapper.addResultMap("operadoraLD", String.class);
			mapper.addResultMap("operadoraClaro", String.class);
			mapper.addResultMap("motivoRejeicao", String.class);
			mapper.addResultMap("qtdCdrs", Long.class);
			mapper.addResultMap("qtdeMinutos", Double.class);
			mapper.addResultMap("valor", Double.class);

			mapper.setProjections(projections);
			
			list = mapper.execute();
		} catch(Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
		return list;
	}
		
}
