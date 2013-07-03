package com.claro.cobillingweb.persistence.dao.internal;

import java.util.Date;
import java.util.List;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.FwjBaseDaoHibernate;
import com.claro.cobillingweb.persistence.entity.SccAgingIndicador;
import com.claro.cobillingweb.persistence.entity.SccResultadoIndicador;
import com.claro.cobillingweb.persistence.view.SccAgingIndicadorView;
import com.claro.cobillingweb.persistence.view.SccResultadoIndicadorView;

public interface SccResultadoIndicadoresDAO extends
		FwjBaseDaoHibernate<SccResultadoIndicador, String> {

	List<SccResultadoIndicadorView> gerarList(String cdEotLd,
			String cdEotClaro, Date dataInicial, Date dataFinal, Long vlMinimo,
			Long vlMaximo, String cdIndicador) throws DAOException;

	List<SccResultadoIndicadorView> gerarListaFinal(String cdEotLd,
			String cdEotClaro, Date dataInicial, Date dataFinal)
			throws DAOException;

	List<SccAgingIndicadorView> gerarPreList2(String cdIndicador)
			throws DAOException;

	List<SccResultadoIndicadorView> pesquisaByFiltro(String cdEotLd,
			String cdEotClaro, String cdIndicador, boolean tipo,
			Date dataInicial, Date dataFinal) throws DAOException;

	List<SccAgingIndicador> gerarListaAging(String cdIndicador)
			throws DAOException;


	SccResultadoIndicadorView gerarResultadoIndicador(String cdEotLd, String cdEotClaro, String cdIndicador,
			Long vlMinimo, Long vlMaximo, Date dataInicial, Date dataFinal)
			throws DAOException;


}
