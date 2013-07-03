package com.claro.sccweb.service;

import java.util.Date;
import java.util.List;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccAgingIndicador;
import com.claro.cobillingweb.persistence.service.ServiceException;
import com.claro.cobillingweb.persistence.view.SccResultadoIndicadorView;

public interface SccResultadoIndicadorService {

	List<SccResultadoIndicadorView> gerarList(String cdEotLd,
			String cdEotClaro, Date dataInicial, Date dataFinal, Long vlMinimo,
			Long vlMaximo, String cdIndicador) throws DAOException;

	List<SccResultadoIndicadorView> gerarListaFinal(String cdEotLd,
			String cdEotClaro, Date dataInicial, Date dataFinal)
			throws DAOException;

	List<SccResultadoIndicadorView> pesquisaByFiltro(String cdEotLd,
			String cdEotClaro, String cdIndicador, boolean tipo,
			Date dataInicial, Date dataFinal) throws DAOException;

	List<SccAgingIndicador> gerarListAging(String cdIndicador)
			throws ServiceException, DAOException;

	List<SccResultadoIndicadorView> gerarRelatorioDiario(String cdEotLd,
			String cdEotClaro, String cdIndicador, boolean tipo,
			Date dataInicial, Date dataFinal) throws DAOException;

}
