package com.claro.sccweb.controller.contabil;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.claro.cobillingweb.persistence.entity.SccArquivoCobilling;
import com.claro.sccweb.controller.BaseOperationController;
import com.claro.sccweb.controller.util.BasicIntegerItem;
import com.claro.sccweb.controller.validator.RelatorioContabilTransicaoValidator;
import com.claro.sccweb.decorator.rownum.entity.SccArquivoCobillingDecorator;
import com.claro.sccweb.form.BaseForm;
import com.claro.sccweb.form.RelatorioContabilTransicaoForm;

@Controller
@RequestMapping(value="/user/contabil/transicao")
public class RelatorioContabilTransicaoController extends BaseOperationController<RelatorioContabilTransicaoForm> {

	private final RelatorioContabilTransicaoValidator validator = new RelatorioContabilTransicaoValidator();
	
	public ModelAndView pesquisar(HttpServletRequest request, HttpServletResponse response,@Valid @ModelAttribute(FORM_NAME)  BaseForm _form,BindingResult bindingResult,Model model) throws Exception
	{
		ModelAndView mav = new ModelAndView(getViewName());
		RelatorioContabilTransicaoForm form = (RelatorioContabilTransicaoForm)_form;
		List<SccArquivoCobilling> rows = getServiceManager().getArquivosService().pesquisaRelatoriosTransicao(form.getCdTipoArquivo(), form.getDataInicial(), form.getDataFinal());
		List<SccArquivoCobillingDecorator> decoratorList = new ArrayList<SccArquivoCobillingDecorator>(rows.size());
		for (int i=0;i<rows.size();i++)
			{
			SccArquivoCobillingDecorator decorator = new SccArquivoCobillingDecorator(rows.get(i), i);
			decoratorList.add(decorator);
			}
		storeInSession(getClass(), DISPLAY_TAG_SPACE_1, decoratorList, request);
		return mav;
	}

	public ModelAndView download(HttpServletRequest request, HttpServletResponse response,@Valid @ModelAttribute(FORM_NAME)  BaseForm _form,BindingResult bindingResult,Model model) throws Exception
	{
		ModelAndView mav = null;
		RelatorioContabilTransicaoForm form = (RelatorioContabilTransicaoForm)_form;
		if (getServiceManager().getFtpService().fileExists(form.getNomeArquivo()) == null)
			{
			form.setErro("Arquivo não existe");
			mav = new ModelAndView(getViewName());
			}
		else
			{
			mav = new ModelAndView("relatorio_contabil_transicao_download");
			List<SccArquivoCobillingDecorator> decoratorList = (List<SccArquivoCobillingDecorator>) request.getSession().getAttribute(DISPLAY_TAG_SPACE_1);
			form.setNomeArquivo(decoratorList.get(form.getItemSelecionado()).getNomeArquivo());
			cacheMyForm(getClass(), form);		
			}
		return mav;
	}
	
	
	 
	protected String getViewName() {
		return "relatorio_contabil_transicao_filtro";
	}

	 
	protected RelatorioContabilTransicaoForm getForm() {		
		return new RelatorioContabilTransicaoForm();
	}

	 
	protected Validator getValidator() {		
		return this.validator;
	}

	@ModelAttribute("tiposArquivo")
	public List<BasicIntegerItem> populaTiposArquivo() 
	{
		List<BasicIntegerItem>  comboList = new ArrayList<BasicIntegerItem>();
		comboList.add(new BasicIntegerItem(900L, "Relatório Interface Contábil"));
		comboList.add(new BasicIntegerItem(905L, "Relatório Eventos Recebidos do Mobile"));
		return comboList;
	}
	
}
