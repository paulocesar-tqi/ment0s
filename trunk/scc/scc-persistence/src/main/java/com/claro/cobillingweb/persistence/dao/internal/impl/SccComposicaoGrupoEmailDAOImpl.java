package com.claro.cobillingweb.persistence.dao.internal.impl;

import java.util.List;

import org.hibernate.Query;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.impl.HibernateBasicDAOImpl;
import com.claro.cobillingweb.persistence.dao.internal.SccComposicaoGrupoEmailDAO;
import com.claro.cobillingweb.persistence.entity.SccComposicaoGrupoEmail;
import com.claro.cobillingweb.persistence.entity.SccEmailCobilling;

public class SccComposicaoGrupoEmailDAOImpl extends HibernateBasicDAOImpl<SccComposicaoGrupoEmail> implements
		SccComposicaoGrupoEmailDAO {

	@Override
	public List<SccComposicaoGrupoEmail> getAll() throws DAOException {
		
		return null;
	}
	
	
	public String gerarHql(){
		StringBuilder hql = new StringBuilder();
		hql.append("select ec from SccComposicaoGrupoEmail cmp ");
		hql.append(" join cmp.sccEmailCobilling ec ");
		hql.append("where cmp.id.sqGrupo =:sqGrupo ");
		
		return hql.toString();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<SccEmailCobilling> gerarListaEmailCadastrado(Long sqGrupo) throws DAOException{
		
		List<SccEmailCobilling> list = null;
		try {
			
			Query query = getSessionFactory().getCurrentSession().createQuery(gerarHql());
			query.setParameter("sqGrupo", sqGrupo);
			list = (List<SccEmailCobilling>) query.list();
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), "gerarListaEmailCadastrado.gerarListaEmailCadastrado");
		}
		return list;
	}


}
