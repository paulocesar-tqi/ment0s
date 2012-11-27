package com.claro.sccweb.service;

import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccArquivoCobilling;
import com.claro.sccweb.form.CadastroSolicitacaoVUMForm;
import com.claro.sccweb.service.composite.SolicitacaoVumComposite;
import com.claro.sccweb.service.to.SolicitacaoVumTO;

public interface SccSolicitacaoVumService {


	/**
	 * Cria uma solicitação vum
	 * @param to
	 * @return
	 * @throws DAOException
	 * @throws ServiceException
	 */
	public void insereSolicitacaoVum(SolicitacaoVumTO to) throws DAOException,ServiceException;
	
	/**
	 * Remove uma solicitação vum que esteja agendada (a carregar).
	 * @param noRequisicao
	 * @throws DAOException
	 * @throws ServiceException
	 */
	public void deleteSolicitacaoVum(String noRequisicao) throws DAOException,ServiceException;
	
	/**
	 * Pesquisa solicitações vum.
	 * @param status TO_LOAD , TO_LOAD ou LOADING.
	 * @return
	 * @throws DAOException
	 * @throws ServiceException
	 */
	public List<SolicitacaoVumComposite> pesquisaSolicitacoes(String status) throws DAOException,ServiceException;

	/**
	 * Pesquisa os arquivos de VUM
	 * @param cdEOTLD
	 * @param tipoArquivo
	 * @param dataInicioRepasse
	 * @param dataFimRepasse
	 * @return
	 */
	public List<SccArquivoCobilling> pesquisaArquivos(String cdEOTLD, String plataforma, long tipoArquivo, Date dataInicioRepasse,
			Date dataFimRepasse) throws DAOException,ServiceException;

	public boolean fileExists(CadastroSolicitacaoVUMForm form) throws ServiceException;

	public void writeFile(CadastroSolicitacaoVUMForm form, OutputStream stream) throws ServiceException;
	
}
