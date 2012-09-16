package com.claro.sccweb.service;

import java.util.Date;
import java.util.Map;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.sccweb.service.data.saldos.DemonstrativoSaldoEvento;

public interface SaldosService {

	public Map<String, DemonstrativoSaldoEvento> geraDemonstrativoSaldo(String cdEOTClaro,String cdEOTLD,Long cdProdutoCobilling,Long cdTipoArquivo,Date dataInicial,Date dataFinal)
	throws DAOException,ServiceException;
	
}
