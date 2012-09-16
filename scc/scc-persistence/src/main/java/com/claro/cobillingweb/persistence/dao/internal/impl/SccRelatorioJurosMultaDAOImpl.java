package com.claro.cobillingweb.persistence.dao.internal.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.SQLQuery;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.impl.HibernateBasicDAOImpl;
import com.claro.cobillingweb.persistence.dao.internal.SccOperadoraDAO;
import com.claro.cobillingweb.persistence.dao.internal.SccRelatorioJurosMultaDAO;
import com.claro.cobillingweb.persistence.entity.SccOperadora;
import com.claro.cobillingweb.persistence.entity.SccRelatorioJurosMulta;

public class SccRelatorioJurosMultaDAOImpl extends HibernateBasicDAOImpl<SccRelatorioJurosMulta> implements SccRelatorioJurosMultaDAO {

	private SccOperadoraDAO operadoraDAO;
	
	 
	public List<SccRelatorioJurosMulta> getAll() throws DAOException {
		throw new UnsupportedOperationException();
	}
	
	

	 
	public SccRelatorioJurosMulta getByPk(Serializable pk, Class entityClazz)	throws DAOException {
		throw new UnsupportedOperationException();
	}



	 
	public void delete(SccRelatorioJurosMulta entity) throws DAOException {
		throw new UnsupportedOperationException();
	}



	 
	public Serializable create(SccRelatorioJurosMulta entity) throws DAOException {
		throw new UnsupportedOperationException();
	}



	 
	public void update(SccRelatorioJurosMulta entity) throws DAOException {
		throw new UnsupportedOperationException();
	}



	 
	public List<SccRelatorioJurosMulta> pesquisaDemonstrativoJurosMultas(String cdEOTClaro, String cdEOT, Long cdProdutoCobilling,Date dtInicialRepasse) 
			throws DAOException {
		String SQL = "SELECT CD_EOT_CLARO,SG_UF,NU_FATURA,DT_ARRECADACAO,DT_VENCIMENTO,VL_JUROS," +
			"VL_MULTAS,NO_ARQUIVO ,CD_EOT_LD FROM SCC_RELATORIO_JUROS_MULTAS WHERE CD_EOT_CLARO = ? "+
			"AND CD_EOT_LD = ? AND CD_PRODUTO_COBILLING = ? AND DT_INICIAL_REPASSE = ?";
		List<SccRelatorioJurosMulta> list = new ArrayList<SccRelatorioJurosMulta>();
		try {
			SQLQuery query = getSessionFactory().getCurrentSession().createSQLQuery(SQL);
			query.setString(0, cdEOTClaro);
			query.setString(1, cdEOT);
			query.setLong(2, cdProdutoCobilling);
			query.setDate(3, dtInicialRepasse);
			List<Object[]> rows =  query.list();
			for (int i=0;i<rows.size();i++)
				{
				SccRelatorioJurosMulta multa = new SccRelatorioJurosMulta();
				SccOperadora operadoraClaro = getOperadoraDAO().getByPk((String)rows.get(i)[0], SccOperadora.class);
				multa.setOperadoraClaro(operadoraClaro);
				multa.setSgUf(((Character)rows.get(i)[1]).toString());
				multa.setNuFatura((String)rows.get(i)[2]);
				multa.setDtArrecadacao((Date)rows.get(i)[3]);
				multa.setDtVencimento((Date)rows.get(i)[4]);
				if (rows.get(i)[5] != null)
					multa.setVlJuros(((BigDecimal)rows.get(i)[5]).doubleValue());
				
				if (rows.get(i)[6] != null)
					multa.setVlMultas(((BigDecimal)rows.get(i)[6]).doubleValue());
				
				multa.setNoArquivo((String)rows.get(i)[7]);				
				
				SccOperadora operadorald = getOperadoraDAO().getByPk((String)rows.get(i)[8], SccOperadora.class);
				multa.setOperadoraLD(operadorald);
				multa.setRowNum(i);
				list.add(i,multa);
				}
			return list;
		} catch (Exception e)
			{
			throw new DAOException(e.getMessage(), "SccRelatorioJurosMultaDAO.pesquisaDemonstrativoJurosMultas");
			}
	}



	public SccOperadoraDAO getOperadoraDAO() {
		return operadoraDAO;
	}



	public void setOperadoraDAO(SccOperadoraDAO operadoraDAO) {
		this.operadoraDAO = operadoraDAO;
	}



	 
	public int apagaJurosMultasRepasse(String cdEOTClaro, String cdEOT,Long cdPeriodicidade,Long cdProdutoCobilling, Date dtInicialRepasse) throws DAOException {
		StringBuffer SQL = new StringBuffer("DELETE FROM SCC_RELATORIO_JUROS_MULTAS WHERE ");
		SQL.append(" CD_EOT_CLARO = ? ");
		SQL.append(" AND DT_INICIAL_REPASSE = ? ");
		SQL.append(" AND  NU_QUINZENA = ? ");
		SQL.append(" AND  CD_PRODUTO_COBILLING = ? ");
		SQL.append(" AND  CD_EOT_LD = ? ");
		try {
			SQLQuery query = getSessionFactory().getCurrentSession().createSQLQuery(SQL.toString());
			query.setString(0, cdEOTClaro);
			query.setDate(1, dtInicialRepasse);
			query.setLong(2, cdPeriodicidade);
			query.setLong(3, cdProdutoCobilling);
			query.setString(3, cdEOT);
			return query.executeUpdate();
		} catch (Exception e)
			{
			throw new DAOException(e.getMessage(), "SccRelatorioJurosMultaDAO.apagaJurosMultasRepasse");
			}
		
	}

	
	
}
