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
import com.claro.cobillingweb.persistence.entity.SccArquivoCobilling;
import com.claro.cobillingweb.persistence.entity.SccOperadora;
import com.claro.sccweb.controller.validator.CadastroSolicitacaoVUMValidator;
import com.claro.sccweb.decorator.rownum.entity.SccArquivoCobillingDecorator;
import com.claro.sccweb.form.BaseForm;
import com.claro.sccweb.form.CadastroSolicitacaoVUMForm;

@Controller
@RequestMapping(value = "/user/admin/vum/download")
public class ArquivoSolicitacaoVUMController extends CadastroSolicitacaoVUMController {

	private CadastroSolicitacaoVUMValidator validator = new CadastroSolicitacaoVUMValidator();

	@Override
	protected String getViewName() {
		return "arquivo_solicitacao_vum";
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
		nullValue.setCdEot(BasicDAO.GET_ALL_STRING);
		nullValue.setDsOperadora("Todas");
		comboList.add(0, nullValue);
		comboList.addAll(getServiceManager().getPesquisaDominiosService().pesquisaOperadorasExternas());
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
		return mav;
	}

	public ModelAndView pesquisar(HttpServletRequest request, HttpServletResponse response,
			@Valid @ModelAttribute(FORM_NAME) BaseForm _form, BindingResult bindingResult, Model model)
			throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());
		CadastroSolicitacaoVUMForm form = (CadastroSolicitacaoVUMForm) _form;
		form.setErro(null);
		long tipoArquivo = form.getTipoArquivo().equals("P") ? CobillingConstants.ARQUIVO_VUM_PARCIAL : CobillingConstants.ARQUIVO_VUM_TOTAL;
		
		List<SccArquivoCobilling> rows = getServiceManager().getSccSolicitacaoVumService().pesquisaArquivos(
				form.getCdEOTLD(), form.getPlataforma(), tipoArquivo, form.getDataInicioRepasse(), form.getDataFimRepasse());
		List<SccArquivoCobillingDecorator> decoratorList = new ArrayList<SccArquivoCobillingDecorator>(rows.size());
		for (int i = 0; i < rows.size(); i++) {
			SccArquivoCobillingDecorator decorator = new SccArquivoCobillingDecorator(rows.get(i), i);
			decoratorList.add(decorator);
		}
		storeInSession(getClass(), DISPLAY_TAG_SPACE_1, decoratorList, request);
		return mav;
	}

	public ModelAndView download(HttpServletRequest request, HttpServletResponse response,
			@Valid @ModelAttribute(FORM_NAME) BaseForm _form, BindingResult bindingResult, Model model)
			throws Exception {
		ModelAndView mav = null;
		CadastroSolicitacaoVUMForm form = (CadastroSolicitacaoVUMForm) _form;
		
		@SuppressWarnings("unchecked")
		List<SccArquivoCobillingDecorator> decoratorList = (List<SccArquivoCobillingDecorator>) request
				.getSession().getAttribute(DISPLAY_TAG_SPACE_1);
		SccArquivoCobillingDecorator item = decoratorList.get(form.getItemSelecionado());
		form.setNomeArquivo(item.getNomeArquivo());
		form.setNomeDiretorio(item.getRow().getNoDiretorioArquivo());
			
		if (!getServiceManager().getSccSolicitacaoVumService().fileExists(form)) {
			form.setErro("Arquivo não existe");
			mav = new ModelAndView(getViewName());
		} else {
			mav = new ModelAndView("arquivo_solicitacao_vum_download");
			
			cacheMyForm(getClass(), form);
		}
		return mav;
	}
}
