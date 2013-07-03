package com.claro.cobillingweb.persistence.dao.internal.impl;

import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;

import com.claro.cobillingweb.persistence.constants.CobillingConstants;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.impl.HibernateBasicDAOImpl;
import com.claro.cobillingweb.persistence.dao.internal.SccIndicadoresDAO;
import com.claro.cobillingweb.persistence.entity.SccIndicador;
import com.claro.cobillingweb.persistence.filtro.SccFiltroIndicador;

public class SccIndicadoresDAOImpl extends HibernateBasicDAOImpl<SccIndicador> implements
		SccIndicadoresDAO {

	@Override
	public List<SccIndicador> getAll() throws DAOException {
		
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<SccIndicador> pesquisaByFiltro(SccFiltroIndicador filtro)	throws DAOException {
		Collection<SccIndicador> list = null;
		try {
			Query query = getSessionFactory().getCurrentSession().createQuery(gerarHqlPesquisa(filtro));
			if(!filtro.getIdIndicador().equalsIgnoreCase("T")){
				query.setString("idIndicador", filtro.getIdIndicador());
			}
			
			list = (Collection<SccIndicador>) query.list();
			
			
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), "SccAgingIndicador.pesquisaByFiltro");
		}
		return list;
	}
	
	private String gerarHqlPesquisa(SccFiltroIndicador filtro){
		
		StringBuilder hql = new StringBuilder();
		
		hql.append("select distinct ind ");
		hql.append("from SccIndicador ind ");
		hql.append("left join fetch ind.sccAgingIndicadors agis ");
		
		if(filtro.getCdTipoContrato().equalsIgnoreCase(CobillingConstants.CD_TIPO_CONTRATO_F)){
			hql.append(" where substring(ind.cdIndicador,1,3) not in('PRE') ");
		}else if(filtro.getCdTipoContrato().equalsIgnoreCase(CobillingConstants.CD_TIPO_CONTRATO_P)){
			hql.append(" where substring(ind.cdIndicador,1,3) = 'PRE' ");
		}

		if(StringUtils.isNotEmpty(filtro.getCdTipoContrato()) && !filtro.getIdIndicador().equalsIgnoreCase("T")){
		
			hql.append("and ind.cdIndicador = :idIndicador ");
			
		}
		
		
		return hql.toString();
	}


	@Override
	public SccIndicador getSccIndicador(String value) throws DAOException {

		SccIndicador entity = null;
		try{
			Query query = getSessionFactory().getCurrentSession().createQuery(gerarHqlEntity(value));
			query.setString("idIndicador", value);
			entity = (SccIndicador) query.list().get(0);
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), "SccAgingIndicador.getSccIndicador");
		}
		return entity;
	
	}
	
	private String gerarHqlEntity(String value){
		StringBuilder hql = new StringBuilder();
		hql.append("select distinct ind ");
		hql.append("from SccIndicador ind ");
		hql.append("left join ind.sccAgingIndicadors agis ");
		hql.append("where ind.cdIndicador = :idIndicador ");
		return hql.toString();
	}
	
	public void excluirByPk(String value) throws DAOException{
		try{
			Query query = getSessionFactory().getCurrentSession().createQuery("delete SccIndicador ind where ind.cdIndicador = :idIndicador ");
			query.setString("idIndicador", value);
			query.executeUpdate();
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), "SccIndicador.excluirByPk");
		}

	}



}
