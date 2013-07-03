package com.claro.cobillingweb.persistence.dao.internal.impl;

import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.claro.cobillingweb.persistence.constants.CobillingConstants;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.impl.HibernateBasicDAOImpl;
import com.claro.cobillingweb.persistence.dao.internal.SccIndicadorDAO;
import com.claro.cobillingweb.persistence.entity.SccAgingIndicador;
import com.claro.cobillingweb.persistence.entity.SccAgingIndicadorPK;
import com.claro.cobillingweb.persistence.entity.SccIndicador;
import com.claro.cobillingweb.persistence.filtro.SccFiltroIndicador;

@Repository
public class SccIndicadorDAOImpl extends HibernateBasicDAOImpl<SccAgingIndicador> implements SccIndicadorDAO {
	
	//private static final String STATUS_PRE_PAGO = "PRE";

	@Override
	public List<SccAgingIndicador> getAll() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<SccAgingIndicador> pesquisaByFiltro(SccFiltroIndicador filtro) throws DAOException {
		
		Collection<SccAgingIndicador> list = null;
		try {
			Query query = getSessionFactory().getCurrentSession().createQuery(gerarHqlPesquisa(filtro));
			if(!filtro.getIdIndicador().equalsIgnoreCase("T")){
				query.setString("idIndicador", filtro.getIdIndicador());
			}
			
			list = (Collection<SccAgingIndicador>) query.list();
			
			
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), "SccAgingIndicador.pesquisaByFiltro");
		}
		return list;
	}
	
	public SccAgingIndicador getSccAgingIndicador(String idIndicador, Long sqAgingIndicador) throws DAOException{
		
		SccAgingIndicador entidade = null;
		try{
			Query query = getSessionFactory().getCurrentSession().createQuery(gerarHqlEntity());
			query.setString("idIndicador", idIndicador);
			query.setLong("sqAgingIndicador", sqAgingIndicador);
			entidade = (SccAgingIndicador) query.list().get(0);
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), "SccAgingIndicador.getSccAgingIndicador");
		}
		return entidade;
	}
	
	private String gerarHqlEntity(){
		StringBuilder hql = new StringBuilder();
		hql.append("select distinct agi ");
		hql.append("from SccAgingIndicador agi ");
		hql.append("left join agi.sccResultadoIndicadors res ");
		hql.append("where agi.id.cdIndicador = :idIndicador ");
		hql.append( "and agi.id.sqAgingIndicador = :sqAgingIndicador ");
		return hql.toString();
	}
	
	private String gerarHqlPesquisa(SccFiltroIndicador filtro){
		
		StringBuilder hql = new StringBuilder();
		
		hql.append("select distinct agi ");
		hql.append("from SccAgingIndicador agi ");
		hql.append("left join agi.sccResultadoIndicadors res ");
		
		if(filtro.getCdTipoContrato().equalsIgnoreCase(CobillingConstants.CD_TIPO_CONTRATO_F)){
			hql.append(" where substring(agi.id.cdIndicador,1,3) not in('PRE') ");
		}else if(filtro.getCdTipoContrato().equalsIgnoreCase(CobillingConstants.CD_TIPO_CONTRATO_P)){
			hql.append(" where substring(agi.id.cdIndicador,1,3) = 'PRE' ");
		}

		if(StringUtils.isNotEmpty(filtro.getCdTipoContrato()) && !filtro.getIdIndicador().equalsIgnoreCase("T")){
		
			hql.append("and agi.id.cdIndicador = :idIndicador ");
			
		}
		
		
		return hql.toString();
	}
	
	private String gerarHqlComboBox(){
		
		StringBuilder hql = new StringBuilder();
		
		hql.append("select i from SccIndicador i ");
		//hql.append("left join fetch i.sccResultadoIndicadors ri ");
		hql.append("where i.dsPeriodicidade = :dsPeriodicidade ");
		return hql.toString();
	}
	

	@Override
	public SccAgingIndicador getSccAgingIndicadorByPk(SccAgingIndicadorPK id)
			throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public Collection<SccIndicador> gerarCombo(SccFiltroIndicador filtro) throws DAOException {
		
		Collection<SccIndicador> list = null;
		try {
			
			Query query = getSessionFactory().getCurrentSession().createQuery(gerarHqlCombo(filtro));
			list = (Collection<SccIndicador>) query.list();
			
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), "SccAgingIndicador.gerarCombo");

		}
		
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Collection<SccIndicador> montarComboBox(String dsPeriodicidade, String tipo) throws DAOException{
		
		Collection<SccIndicador> combo = null;
		try {
			
			String sql = gerarHqlComboBox();
			
			if(tipo.equalsIgnoreCase("PRE")){
				sql = sql +"and substring(i.cdIndicador,1,3) = 'PRE' ";
			}else{
				sql = sql +"and substring(i.cdIndicador,1,3) not in('PRE')"; 
			}
			
			Query query = getSessionFactory().getCurrentSession().createQuery(sql);
			query.setParameter("dsPeriodicidade", dsPeriodicidade);
			combo = (Collection<SccIndicador>) query.list();
			
			
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), "SccAgingIndicador.montarComboBox");

		}
		return combo;
	}
	
	@SuppressWarnings("unused")
	public void excluirAgingIndicador(String value) throws DAOException{
		
		try {
			Query query = getSessionFactory().getCurrentSession().createQuery("delete from SccAgingIndicador where id.cdIndicador = :idIndicador");
			query.setString("idIndicador", value);
			 int rowCount = query.executeUpdate();
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), "SccAgingIndicador.excluirAgingIndicador");
		}
	}
	
	
	private String gerarHqlCombo(SccFiltroIndicador filtro){
		StringBuilder hql = new StringBuilder();
		
		hql.append("select distinct ind ");
		hql.append("from SccIndicador ind  ");
		hql.append("left join ind.sccAgingIndicadors as agi ");
		
		if(filtro.getCdTipoContrato().equalsIgnoreCase(CobillingConstants .CD_TIPO_CONTRATO_F)){
			
			hql.append("where substring(ind.cdIndicador,1,3) not in('PRE')");
		}else{
			hql.append("where substring(ind.cdIndicador,1,3) = 'PRE'");
		}
		
		return hql.toString();

	}
	
	@SuppressWarnings("unused")
	@Override
	public void excluirByParam(String idIndicador, Long sqAgingIndicador) throws DAOException{
		try {
			Query query = getSessionFactory().getCurrentSession().createQuery("delete from SccAgingIndicador where id.cdIndicador = :idIndicador and id.sqAgingIndicador = :sqAgingIndicador ");
			query.setString("idIndicador", idIndicador);
			query.setLong("sqAgingIndicador", sqAgingIndicador);
			 int rowCount = query.executeUpdate();
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), "SccAgingIndicador.excluirByParam");
		}
		
	}
	
	@Override
	public SccIndicador findById(String cdIndicador)throws DAOException{
		
		SccIndicador entity = null;
		try {
			Criteria criteria =  getSessionFactory().getCurrentSession().createCriteria(SccIndicador.class);
			criteria.add(Restrictions.eq("cdIndicador", cdIndicador));
			entity = (SccIndicador) criteria.list().get(0);
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), "SccAgingIndicador.findById");
		}
		
		return entity;
	}
	
	
}
