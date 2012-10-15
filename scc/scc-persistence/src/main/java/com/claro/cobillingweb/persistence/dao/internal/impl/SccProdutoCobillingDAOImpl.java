package com.claro.cobillingweb.persistence.dao.internal.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.impl.HibernateBasicDAOImpl;
import com.claro.cobillingweb.persistence.dao.internal.SccProdutoCobillingDAO;
import com.claro.cobillingweb.persistence.entity.SccComposicaoProduto;
import com.claro.cobillingweb.persistence.entity.SccContratoAcordado;
import com.claro.cobillingweb.persistence.entity.SccProdutoCobilling;
import com.claro.cobillingweb.persistence.entity.SccProdutoContratado;

public class SccProdutoCobillingDAOImpl extends HibernateBasicDAOImpl<SccProdutoCobilling> implements SccProdutoCobillingDAO {
	
	public List<SccProdutoCobilling> getAll() throws DAOException {		
		try {
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SccProdutoCobilling.class);
			return criteria.list();
		} catch (Exception e) { throw new DAOException(e.getMessage(), e); }		
	}
	
	public List<SccProdutoCobilling> pesquisaProdutosOperadoraLD(String cdEOT) throws DAOException {
		Map<Long, SccProdutoCobilling> filter = new HashMap<Long, SccProdutoCobilling>();
		try {
			Query query = getSessionFactory().getCurrentSession().createQuery("SELECT A FROM SccContratoAcordado A WHERE A.operadoraExterna.cdEot = ? AND dtFimVigencia > ? and id.dtInicioVigencia < ?" );
			query.setString(0, cdEOT);			
			query.setDate(1, new Date());
			query.setDate(2, new Date());
			List<SccContratoAcordado> acordos = query.list();
			
			for (int a=0;a<acordos.size();a++) {
				SccContratoAcordado acordo = (SccContratoAcordado)acordos.get(a);
				query = getSessionFactory().getCurrentSession().createQuery("SELECT P FROM SccProdutoContratado P WHERE P.sccContratoCobl.cdContratoCobilling = ?");				
				query.setLong(0, acordo.getSccContratoCobl().getCdContratoCobilling());
				List<SccProdutoContratado> produtosInner = query.list();
				for (int p=0;p<produtosInner.size();p++) {
					SccProdutoCobilling produtoCobilling = (SccProdutoCobilling)getSessionFactory().getCurrentSession().get(SccProdutoCobilling.class, produtosInner.get(p).getSccProdutoCobilling().getCdProdutoCobilling());
					filter.put(produtoCobilling.getCdProdutoCobilling(), produtoCobilling);
				}
			}	
			List<SccProdutoCobilling> resultados = new ArrayList<SccProdutoCobilling>();
			resultados.addAll(filter.values());
			return resultados;
		} catch (Exception e) { throw new DAOException(e.getMessage(), "SccProdutoCobillingDAO.pesquisaProdutosOperadoraLD"); }		
	}
	
	public List<SccProdutoCobilling> pesquisaProdutosOperadoras(String cdEOTLD,String cdEOTClaro) throws DAOException {
		Map<Long, SccProdutoCobilling> filter = new HashMap<Long, SccProdutoCobilling>();
		try {
			Query query = getSessionFactory().getCurrentSession().createQuery("SELECT A FROM SccContratoAcordado A WHERE A.operadoraExterna.cdEot = ? AND A.operadoraClaro.cdEot = ? AND dtFimVigencia > ? and id.dtInicioVigencia < ?" );
			query.setString(0, cdEOTLD);
			query.setString(1, cdEOTClaro);
			query.setDate(2, new Date());
			query.setDate(3, new Date());
			List<SccContratoAcordado> acordos = query.list();
		
			for (int a=0;a<acordos.size();a++) {
				SccContratoAcordado acordo = (SccContratoAcordado)acordos.get(a);
				query = getSessionFactory().getCurrentSession().createQuery("SELECT P FROM SccProdutoContratado P WHERE P.sccContratoCobl.cdContratoCobilling = ?");				
				query.setLong(0, acordo.getSccContratoCobl().getCdContratoCobilling());
				List<SccProdutoContratado> produtosInner = query.list();
				for (int p=0;p<produtosInner.size();p++) {
					SccProdutoCobilling produtoCobilling = (SccProdutoCobilling)getSessionFactory().getCurrentSession().get(SccProdutoCobilling.class, produtosInner.get(p).getSccProdutoCobilling().getCdProdutoCobilling());
					filter.put(produtoCobilling.getCdProdutoCobilling(), produtoCobilling);
				}
			}	
			List<SccProdutoCobilling> resultados = new ArrayList<SccProdutoCobilling>();
			resultados.addAll(filter.values());
			return resultados;
		} catch (Exception e) { throw new DAOException(e.getMessage(), e); }		
	}
	
	public List<SccComposicaoProduto> carregaComposicaoProduto(Long cdProdutoCobilling) throws DAOException {
		try {
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SccComposicaoProduto.class);
			criteria.add(Restrictions.eq("sccProdutoCobilling.cdProdutoCobilling", cdProdutoCobilling));
			criteria.setFetchMode("sccItemCobilling", FetchMode.JOIN);
			criteria.setFetchMode("sccProdutoCobilling", FetchMode.JOIN);		
			return criteria.list();
		}catch (Exception e) { throw new DAOException(e.getMessage(), e); }		
	}
	
	@SuppressWarnings("unchecked")
	public List<SccComposicaoProduto> carregaComposicaoProduto(Long cdProdutoCobilling, Long cdItemCobilling) throws DAOException {
		try {
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SccComposicaoProduto.class);
			criteria.add(Restrictions.eq("sccProdutoCobilling.cdProdutoCobilling", cdProdutoCobilling));
			criteria.add(Restrictions.eq("sccItemCobilling.cdItemCobilling", cdProdutoCobilling));
			criteria.setFetchMode("sccItemCobilling", FetchMode.JOIN);
			return criteria.list();
		}catch (Exception e) { 
			throw new DAOException(e.getMessage(), e); 
		}		
	}
	
	
	@SuppressWarnings("unchecked")
	public List<SccProdutoCobilling> pesquisaProdutosOperadoraLDTodas() throws DAOException{
		
		List<SccProdutoCobilling> list = null;
		try {
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SccProdutoCobilling.class);
			list = (List<SccProdutoCobilling> ) criteria.list();
			
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), e); 
		}
		return list;
	}

	
}
