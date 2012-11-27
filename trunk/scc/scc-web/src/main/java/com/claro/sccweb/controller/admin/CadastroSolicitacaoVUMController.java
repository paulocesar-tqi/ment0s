package com.claro.sccweb.controller.admin;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.claro.cobillingweb.persistence.constants.CobillingConstants;
import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.entity.SccOperadora;
import com.claro.sccweb.controller.BaseOperationController;
import com.claro.sccweb.controller.util.BasicStringItem;
import com.claro.sccweb.controller.validator.CadastroSolicitacaoVUMValidator;
import com.claro.sccweb.form.CadastroSolicitacaoVUMForm;
import com.claro.sccweb.service.to.SolicitacaoVumTO;

@Controller
@RequestMapping(value = "/user/admin/vum/cadastro")
public class CadastroSolicitacaoVUMController extends BaseOperationController<CadastroSolicitacaoVUMForm> {

	private CadastroSolicitacaoVUMValidator validator = new CadastroSolicitacaoVUMValidator();

	@Override
	protected String getViewName() {
		return "cadastro_solicitacao_vum";
	}

	@Override
	protected CadastroSolicitacaoVUMForm getForm() {
		return new CadastroSolicitacaoVUMForm();
	}

	@Override
	protected Validator getValidator() {
		return this.validator;
	}

	/**
	 * Popula combo com a lista de operadoras LD (externas).
	 * 
	 * @return
	 * @throws Exception
	 */
	@ModelAttribute("operadorasExternas")
	public List<SccOperadora> populaOperadorasExternas() throws Exception {
		List<SccOperadora> comboList = new ArrayList<SccOperadora>();
		SccOperadora nullValue = new SccOperadora();
		nullValue.setCdEot(BasicDAO.NULL_STRING);
		nullValue.setDsOperadora("Selecione...");
		comboList.add(0, nullValue);
		comboList.addAll(getServiceManager().getPesquisaDominiosService().pesquisaOperadorasExternas());
		return comboList;
	}

	@ModelAttribute("tipoArquivo")
	public List<BasicStringItem> populaTiposArquivo() {
		List<BasicStringItem> comboList = new ArrayList<BasicStringItem>();
		comboList.add(new BasicStringItem("P", "Parcial"));
		comboList.add(new BasicStringItem("T", "Total"));
		return comboList;
	}

	@ModelAttribute("plataformas")
	public List<BasicStringItem> populaPlataformas() {
		List<BasicStringItem> comboList = new ArrayList<BasicStringItem>();
		comboList.add(new BasicStringItem("0000", "Pós-Pago"));
		comboList.add(new BasicStringItem("0001", "Pré-Pago"));
		return comboList;
	}

	/**
	 * Handler para quando o usuário inicia a navegação na página de solicitação
	 * vum.
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public ModelAndView novaSolicitacao(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = super.iniciar(request, response);
		request.getSession().setAttribute(DISPLAY_TAG_SPACE_1,
				getServiceManager().getSccSolicitacaoVumService().pesquisaSolicitacoes(CobillingConstants.TO_LOAD));
		request.getSession().setAttribute(DISPLAY_TAG_SPACE_2,
				getServiceManager().getSccSolicitacaoVumService().pesquisaSolicitacoes(CobillingConstants.LOADING));
		request.getSession().setAttribute(DISPLAY_TAG_SPACE_3,
				getServiceManager().getSccSolicitacaoVumService().pesquisaSolicitacoes(CobillingConstants.LOADED));
		return mav;
	}

	/**
	 * Handler para inserir uma nova requisição vum.
	 * 
	 * @param request
	 * @param response
	 * @param form
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/insere", method = RequestMethod.POST)
	public ModelAndView insere(HttpServletRequest request, HttpServletResponse response,
			@Valid @ModelAttribute(FORM_NAME) CadastroSolicitacaoVUMForm form, BindingResult bindingResult, Model model)
			throws Exception {
		debug("Processando requisição para criação de solicitação vum.");
		ModelAndView mav = new ModelAndView(getViewName());

		String usuario = getSessionDataManager().getUserPrincipal();
		if (bindingResult.hasErrors()) {
			debug("Impossível processar requisição para criação de solicitação vum : Erros de validação (binding).");
		} else {
			SolicitacaoVumTO to = new SolicitacaoVumTO();
			to.setCdEOTLD(form.getCdEOTLD());
			to.setDataFimRepasse(form.getDataFimRepasse());
			to.setDataInicioRepasse(form.getDataInicioRepasse());
			to.setPlataforma(form.getPlataforma());
			to.setTipoArquivo(form.getTipoArquivo());
			to.setUsuario(usuario);
			
			getServiceManager().getSccSolicitacaoVumService().insereSolicitacaoVum(to);
			request.getSession().setAttribute(DISPLAY_TAG_SPACE_1,
					getServiceManager().getSccSolicitacaoVumService().pesquisaSolicitacoes(CobillingConstants.TO_LOAD));
			request.getSession().setAttribute(DISPLAY_TAG_SPACE_2,
					getServiceManager().getSccSolicitacaoVumService().pesquisaSolicitacoes(CobillingConstants.LOADING));
			request.getSession().setAttribute(DISPLAY_TAG_SPACE_3,
					getServiceManager().getSccSolicitacaoVumService().pesquisaSolicitacoes(CobillingConstants.LOADED));
		}
		return mav;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ModelAndView delete(HttpServletRequest request, HttpServletResponse response,
			@Valid @ModelAttribute(FORM_NAME) CadastroSolicitacaoVUMForm form, BindingResult bindingResult, Model model)
			throws Exception {
		getServiceManager().getSccSolicitacaoVumService().deleteSolicitacaoVum(form.getNmParam());
		return novaSolicitacao(request, response);
	}

}
