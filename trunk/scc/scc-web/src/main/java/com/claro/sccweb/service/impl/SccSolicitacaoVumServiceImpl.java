package com.claro.sccweb.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import com.claro.cobillingweb.persistence.constants.CobillingConstants;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.internal.SccArquivoCobillingDAO;
import com.claro.cobillingweb.persistence.dao.internal.SccOperadoraDAO;
import com.claro.cobillingweb.persistence.dao.internal.SccParamProcessoDAO;
import com.claro.cobillingweb.persistence.entity.SccArquivoCobilling;
import com.claro.cobillingweb.persistence.entity.SccOperadora;
import com.claro.cobillingweb.persistence.entity.SccParamProcesso;
import com.claro.cobillingweb.persistence.entity.SccParamProcessoPK;
import com.claro.sccweb.form.CadastroSolicitacaoVUMForm;
import com.claro.sccweb.service.AbstractService;
import com.claro.sccweb.service.FTPService;
import com.claro.sccweb.service.SccSolicitacaoVumService;
import com.claro.sccweb.service.ServiceException;
import com.claro.sccweb.service.composite.SolicitacaoVumComposite;
import com.claro.sccweb.service.to.SolicitacaoVumTO;

public class SccSolicitacaoVumServiceImpl extends AbstractService implements SccSolicitacaoVumService {
	Logger logger = Logger.getLogger(SccSolicitacaoVumServiceImpl.class);

	private SccParamProcessoDAO paramProcessoDAO;
	private SccOperadoraDAO sccOperadoraDAO;
	private SccArquivoCobillingDAO sccArquivoCobillingDAO;
	private FTPService ftpService;

	SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");

	@Override
	@Transactional
	public void insereSolicitacaoVum(final SolicitacaoVumTO to) throws DAOException, ServiceException {
		final Date now = new Date();

		try {
			StringBuilder valorParametro = new StringBuilder();

			// Data Inicio Repasse DDMMYYYY (8)
			// Data Fim Repasse DDMMYYYY (8)
			// Flag (tipo arquivo) (1) - P ou T
			// Plataforma (4) - 0000 Pós, 0001 Pré
			valorParametro.append(dateFormat.format(to.getDataInicioRepasse()));
			valorParametro.append(dateFormat.format(to.getDataFimRepasse()));
			valorParametro.append(to.getTipoArquivo());
			valorParametro.append(to.getPlataforma());

			List<SolicitacaoVumComposite> lstToLoad = pesquisaSolicitacoes(CobillingConstants.TO_LOAD);

			for (SolicitacaoVumComposite sol : lstToLoad) {
				if (sol.getCdEOTLD().equals(to.getCdEOTLD())) {
					if (sol.getVlParametro().equals(valorParametro.toString())) {
						logger.info("Solicitação VUM já existente TO_LOAD " + to.getCdEOTLD() + " - " + valorParametro);
						return;
					}
				}
			}

			final SccParamProcessoPK pk = new SccParamProcessoPK();
			pk.setCdProcesso(CobillingConstants.PARAMETER_ARQ_VUM);
			long valSeq = getParamProcessoDAO().getProximoValorSequence();
			pk.setNmParametro(to.getCdEOTLD() + "_" + leftZeroFill(valSeq + "", 10));

			SccParamProcesso paramProcesso = new SccParamProcesso();
			paramProcesso.setCdTipoParametro(CobillingConstants.TO_LOAD);

			paramProcesso.setTxValorParametro(valorParametro.toString());
			paramProcesso.setDtCriacao(now);
			paramProcesso.setCdUsuarioManut(to.getUsuario());
			paramProcesso.setId(pk);

			getParamProcessoDAO().create(paramProcesso);
		} catch (DAOException daoException) {
			throw daoException;
		} catch (Exception e) {
			throw new ServiceException("Erro em service SccSolicitacaoVumService.insereSolicitacaoVum "
					+ e.getMessage(), e);
		}
	}

	@Override
	@Transactional
	public void deleteSolicitacaoVum(String noRequisicao) throws DAOException, ServiceException {
		getParamProcessoDAO().deleteParamProcesso(CobillingConstants.PARAMETER_ARQ_VUM, noRequisicao);
	}

	@Override
	public List<SolicitacaoVumComposite> pesquisaSolicitacoes(String status) throws DAOException, ServiceException {
		List<SolicitacaoVumComposite> resultados = new ArrayList<SolicitacaoVumComposite>();
		List<SccParamProcesso> paramProcessoList = null;
		if (status.equals(CobillingConstants.TO_LOAD))
			paramProcessoList = getParamProcessoDAO().pesquisaRepassesAgendados(CobillingConstants.PARAMETER_ARQ_VUM);
		else if (status.equals(CobillingConstants.LOADING)) {
			paramProcessoList = getParamProcessoDAO().pesquisaRepassesProcessando(CobillingConstants.PARAMETER_ARQ_VUM,
					-1);
		} else if (status.equals(CobillingConstants.LOADED)) {
			paramProcessoList = getParamProcessoDAO().pesquisaRepassesProcessados(CobillingConstants.PARAMETER_ARQ_VUM,
					-1);
		}

		if (paramProcessoList != null) {
			String valParametro = null;
			for (int i = 0; i < paramProcessoList.size(); i++) {
				try {
					final SccParamProcesso paramProcesso = paramProcessoList.get(i);
					String nmParametro = paramProcesso.getId().getNmParametro();
					SolicitacaoVumComposite composite = new SolicitacaoVumComposite();

					int idx = nmParametro.indexOf("_");
					String cdEOTLD = nmParametro;
					if (idx > 0) {
						cdEOTLD = cdEOTLD.substring(0, idx);
					}
					composite.setCdEOTLD(cdEOTLD);
					composite.setNmParametro(nmParametro);
					composite.setOperadoraLD(getSccOperadoraDAO().getByPk(cdEOTLD, SccOperadora.class));

					valParametro = paramProcesso.getTxValorParametro();
					composite.setDtCriacao(paramProcesso.getDtCriacao());
					composite.setDtEvento(paramProcesso.getDtAtualizacao());
					composite.setUsuario(paramProcesso.getCdUsuarioManut());

					// Data Inicio Repasse DDMMYYYY (8)
					// Data Fim Repasse DDMMYYYY (8)
					// Flag (tipo arquivo) (1) - P ou T
					// Plataforma (4) - 0000 Pós, 0001 Pré
					String str = valParametro.substring(0, 8);
					composite.setDataInicioRepasse(this.dateFormat.parse(str));
					str = valParametro.substring(8, 16);
					composite.setDataFimRepasse(this.dateFormat.parse(str));
					composite.setTipoArquivo(valParametro.substring(16, 17));
					composite.setPlataforma(valParametro.substring(17));

					composite.setVlParametro(valParametro);

					resultados.add(composite);
				} catch (Exception e) {
					error("Erro ao realizar parse em " + valParametro, e);
				}
			}
		}
		return resultados;
	}

	@Override
	public List<SccArquivoCobilling> pesquisaArquivos(String cdEOTLD, String plataforma, long tipoArquivo,
			Date dataInicioRepasse, Date dataFimRepasse) throws DAOException, ServiceException {

		return getSccArquivoCobillingDAO().pesquisaArquivosVum(cdEOTLD, plataforma, tipoArquivo, dataInicioRepasse,
				dataFimRepasse);
	}

	@Override
	public boolean fileExists(CadastroSolicitacaoVUMForm form) throws ServiceException {
		File f = new File(form.getNomeDiretorio(), form.getNomeArquivo());
		return f.exists();
		
		//String ret = ftpService.fileExists(form.getNomeArquivo());
		//return ret != null;
	}

	@Override
	public void writeFile(CadastroSolicitacaoVUMForm form, OutputStream stream) throws ServiceException {
		try {
			File f = new File(form.getNomeDiretorio(), form.getNomeArquivo());
			FileInputStream fis = new FileInputStream(f);
			copyStream(fis, stream);
			fis.close();
			//ftpService.writeFile(stream, form.getNomeDiretorio() + form.getNomeArquivo());
		} catch (Exception ex) {
			throw new ServiceException(ex.getMessage(), ex);
		}
	}

	private static void copyStream(InputStream input, OutputStream output) throws IOException {
		byte[] buffer = new byte[1024]; // Adjust if you want
		int bytesRead;
		while ((bytesRead = input.read(buffer)) != -1) {
			output.write(buffer, 0, bytesRead);
		}
	}

	public SccParamProcessoDAO getParamProcessoDAO() {
		return paramProcessoDAO;
	}

	public void setParamProcessoDAO(SccParamProcessoDAO paramProcessoDAO) {
		this.paramProcessoDAO = paramProcessoDAO;
	}

	public SccOperadoraDAO getSccOperadoraDAO() {
		return sccOperadoraDAO;
	}

	public void setSccOperadoraDAO(SccOperadoraDAO sccOperadoraDAO) {
		this.sccOperadoraDAO = sccOperadoraDAO;
	}

	public SccArquivoCobillingDAO getSccArquivoCobillingDAO() {
		return sccArquivoCobillingDAO;
	}

	public void setSccArquivoCobillingDAO(SccArquivoCobillingDAO sccArquivoCobillingDAO) {
		this.sccArquivoCobillingDAO = sccArquivoCobillingDAO;
	}

	public FTPService getFtpService() {
		return ftpService;
	}

	public void setFtpService(FTPService ftpService) {
		this.ftpService = ftpService;
	}

}
