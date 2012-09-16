package com.claro.cobillingweb.persistence.dao.external;

import java.util.List;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.external.ViewArquivoPrePago;

public interface ViewArquivoPrePagoDAO extends BasicDAO<ViewArquivoPrePago> {
	
	public List<ViewArquivoPrePago> carregaDetalhesArquivoPrePago(Long noSeqArquivo) throws DAOException;	
	
}
