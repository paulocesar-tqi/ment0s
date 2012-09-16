package com.claro.sccweb.service;

import java.util.Date;

import com.claro.cobillingweb.persistence.dao.DAOException;

public interface VolumeSuspeitoService {

	public void criaSolicitacaoArquivo(Long minutos,Double valorBruto,Date dataInicial,Date dataFinal) throws DAOException,ServiceException;
	
	
}
