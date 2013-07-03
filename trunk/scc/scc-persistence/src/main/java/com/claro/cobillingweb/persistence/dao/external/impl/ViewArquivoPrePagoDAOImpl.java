package com.claro.cobillingweb.persistence.dao.external.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.SQLQuery;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.external.ViewArquivoPrePagoDAO;
import com.claro.cobillingweb.persistence.dao.impl.HibernateBasicDAOImpl;
import com.claro.cobillingweb.persistence.entity.external.ViewArquivoPrePago;

public class ViewArquivoPrePagoDAOImpl extends HibernateBasicDAOImpl<ViewArquivoPrePago> implements ViewArquivoPrePagoDAO {
	
	public ViewArquivoPrePago getByPk(Serializable pk, Class entityClazz) throws DAOException {
		throw new DAOException("Método não suportado");
	}
	
	public void delete(ViewArquivoPrePago entity) throws DAOException {
		throw new DAOException("Método não suportado");
	}
	
	public Serializable create(ViewArquivoPrePago entity) throws DAOException {
		throw new DAOException("Método não suportado");
	}
	
	public void update(ViewArquivoPrePago entity) throws DAOException {
		throw new DAOException("Método não suportado");
	}
	
	public List<ViewArquivoPrePago> getAll() throws DAOException {
		throw new DAOException("Método não suportado");
	}
	
	public List<ViewArquivoPrePago> carregaDetalhesArquivoPrePago(Long noSeqArquivo) throws DAOException {
		String sql = "SELECT  "+
				" APP.SQ_ARQUIVO,  "+
				" PCA.NO_ARQUIVO, "+
				" PCA.NO_DIRETORIO_ARQUIVO, "+    
				" PCA.NO_MAQUINA_ARQUIVO, "+
				" PCA.DT_MOVIMENTO, "+
				" PCA.NO_SISTEMA_RESP,  "+
				" PCA.NO_PROCESSO_RESP,  "+ 
				" PCA.IN_STATUS_PROCESSO, "+    
				" PCA.DH_PROCESSO, "+
				" APP.SQ_ARQUIVO_CONTROLE_PRE, "+         			    
				" PCA.QT_REGISTROS_ARQUIVO,  "+
				" PCA.QT_REGISTROS_PROCESSADOS "+
				" FROM SCC_ARQUIVO_PRE_PAGO APP, PRE_CONTROLE_ARQUIVO PCA       "+ 
				" WHERE APP.SQ_ARQUIVO_CONTROLE_PRE = PCA.SQ_CONTROLE_ARQUIVO "  +
				" AND APP.SQ_ARQUIVO =  ? ";
		try {
			List<ViewArquivoPrePago> resultados = new ArrayList<ViewArquivoPrePago>();
			SQLQuery query = getSessionFactory().getCurrentSession().createSQLQuery(sql);
			query.setLong(0, noSeqArquivo);
			List<Object[]> rows = query.list();
			for (int i=0;i<rows.size();i++){
				Object[] row = rows.get(i);
 				ViewArquivoPrePago item = new ViewArquivoPrePago();
				item.setSQ_ARQUIVO(((BigDecimal)row[0]).longValue());
				item.setNO_ARQUIVO((String)row[1]);
				item.setNO_DIRETORIO_ARQUIVO((String)row[2]);
				item.setNO_MAQUINA_ARQUIVO((String)row[3]);
				item.setDT_MOVIMENTO((Date)row[4]);
				item.setNO_SISTEMA_RESP((String)row[5]);
				item.setNO_PROCESSO_RESP((String)row[6]);
				item.setIN_STATUS_PROCESSO((String)row[7]);
				item.setDH_PROCESSO((Date)row[8]);
				item.setSQ_ARQUIVO_CONTROLE_PRE(((BigDecimal)row[9]).longValue());
				item.setQT_REGISTROS_ARQUIVO(((BigDecimal)row[10]).intValue());
				item.setQT_REGISTROS_PROCESSADOS(((BigDecimal)row[11]).intValue());
				
				resultados.add(item);
			}
			return resultados;
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), "ViewArquivoPrePagoDAO.carregaDetalhesArquivoPrePago");
		}
		
	}
	
}
