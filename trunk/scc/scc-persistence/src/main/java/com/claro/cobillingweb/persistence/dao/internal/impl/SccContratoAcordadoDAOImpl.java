package com.claro.cobillingweb.persistence.dao.internal.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.impl.HibernateBasicDAOImpl;
import com.claro.cobillingweb.persistence.dao.internal.SccContratoAcordadoDAO;
import com.claro.cobillingweb.persistence.dao.internal.SccOperadoraDAO;
import com.claro.cobillingweb.persistence.entity.SccContratoAcordado;
import com.claro.cobillingweb.persistence.entity.SccContratoCobl;
import com.claro.cobillingweb.persistence.entity.SccProdutoContratado;

public class SccContratoAcordadoDAOImpl extends HibernateBasicDAOImpl<SccContratoAcordado> implements SccContratoAcordadoDAO {
	
	private SccOperadoraDAO operadoraDAO;
	
	public List<SccContratoAcordado> getAll() throws DAOException {		
		return null;
	}
	
	public List<SccContratoAcordado> pesquisaContratosPorProduto(String cdEOT,Long cdProduto) throws DAOException {
		try {
			String sql = "SELECT A.* FROM  SCC_CONTRATO_ACORDADO A , SCC_PRODUTO_CONTRATADO P  "+
					" WHERE A.CD_CONTRATO_COBILLING = P.CD_CONTRATO_COBILLING AND	A.CD_EOT_LD = ? "+
					"  AND	P.CD_PRODUTO_COBILLING = ? AND   A.DT_FIM_VIGENCIA > SYSDATE ";
			SQLQuery query = getSessionFactory().getCurrentSession().createSQLQuery(sql);
			query.addEntity(SccContratoAcordado.class);
			query.setString(0, cdEOT);
			query.setLong(1, cdProduto);
			return query.list();
		} catch (Exception e)
		{
			throw new DAOException(e.getMessage(), "SccContratoAcordadoDAO.pesquisaContratosPorProduto");
		}
	}
	
	public List<SccContratoAcordado> pesquisaContratosPorProduto(String cdEOTClaro, String cdEOTLD, Long cdProduto, Date dtInicio,Date dtFinal,boolean holding) throws DAOException {
		List<SccContratoAcordado> acordosFilter = new ArrayList<SccContratoAcordado>();
		try {
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SccContratoAcordado.class);
			criteria.add(Restrictions.lt("dtCriacao", dtFinal));
			criteria.add(Restrictions.gt("dtFimVigencia", dtInicio));
			criteria.add(Restrictions.eq("operadoraExterna.cdEot", cdEOTLD));
			if (!cdEOTClaro.equals(BasicDAO.GET_ALL_STRING))
				{
				if (holding)
					criteria.add(Restrictions.in("operadoraClaro",getOperadoraDAO().pesquisaOperadorasHolding(cdEOTClaro) ));
				else
					criteria.add(Restrictions.eq("operadoraClaro.cdEot", cdEOTClaro));
				}
			criteria.createAlias("operadoraClaro", "op").addOrder(Order.asc("op.dsOperadora"));  
			List<SccContratoAcordado> acordos = criteria.list();
			if (acordos != null)
				{
				for (int i=0;i<acordos.size();i++)
					{					
					SccContratoCobl contratoCobl = acordos.get(i).getSccContratoCobl();
					Query query = getSessionFactory().getCurrentSession().createQuery("SELECT p from SccProdutoContratado p where p.id.cdContratoCobilling = ? ");
					query.setLong(0, contratoCobl.getCdContratoCobilling());
					List<SccProdutoContratado> produtos = query.list();					
					if (produtos == null)
						break;
					for (int p=0;p<produtos.size();p++)
						{
						if (produtos.get(p).getSccProdutoCobilling().getCdProdutoCobilling().equals(cdProduto))
							{
							acordosFilter.add(acordos.get(i));
							break;
							}							
						
						}
					}
				}			
		} catch (Exception e)
		{
			throw new DAOException(e.getMessage(), "SccContratoAcordadoDAO.pesquisaContratosPorProduto");
		}
		return acordosFilter;
	}
	
	public SccOperadoraDAO getOperadoraDAO() {
		return operadoraDAO;
	}
	
	public void setOperadoraDAO(SccOperadoraDAO operadoraDAO) {
		this.operadoraDAO = operadoraDAO;
	}
	
	public List<SccContratoAcordado> pesquisaAcordosContrato(Long cdContratoCobilling) throws DAOException {
		try {
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SccContratoAcordado.class);
			criteria.add(Restrictions.eq("id.cdContratoCobilling", cdContratoCobilling));
			return criteria.list();
		} catch (Exception e)
		{
			throw new DAOException(e.getMessage(), e);
		}		
	}
	
	public void atualizarAcordosContrato(SccContratoAcordado entity) throws DAOException {
		
		/*
		update SCC_CONTRATO_ACORDADO set cd_eot_ld = 211, cd_eot_claro = 447
				where cd_contrato_cobilling = 5
				 and cd_eot_ld = 201
				 and cd_eot_claro = 441
		*/
		//SccContratoAcordado acordo = (SccContratoAcordado)acordos.get(a);
		//query = getSessionFactory().getCurrentSession().createQuery("SELECT P FROM SccProdutoContratado P WHERE P.sccContratoCobl.cdContratoCobilling = ?");				
		
		
		//query.setLong(0, acordo.getSccContratoCobl().getCdContratoCobilling());
		/*
		Query query = getSessionFactory().getCurrentSession().createQuery("UPDATE SCC_CONTRATO_ACORDADO set CD_EOT_CLARO = :cc, CD_EOT_LD = :cd where CD_CONTRATO_COBILLING = :id");
		query.setLong("id", entity.getSccContratoCobl().getCdContratoCobilling());
		query.setString("cc", entity.getOperadoraClaro());
		query.setString("ce", entity.getOperadoraExterna());
		query.executeUpdate();
		
		
		
		*/
	}
	
}
