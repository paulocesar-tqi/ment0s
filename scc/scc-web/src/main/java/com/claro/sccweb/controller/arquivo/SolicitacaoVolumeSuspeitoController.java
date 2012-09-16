package com.claro.sccweb.controller.arquivo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.claro.sccweb.controller.BaseOperationController;
import com.claro.sccweb.controller.validator.SolicitacaoVolumeSuspeitoValidator;
import com.claro.sccweb.form.BaseForm;
import com.claro.sccweb.form.SolicitacaoVolumeSuspeitoForm;

@Controller
@RequestMapping(value="/user/arquivo/suspeito/solicitacao")
public class SolicitacaoVolumeSuspeitoController extends BaseOperationController<SolicitacaoVolumeSuspeitoForm> {
	
	private final SolicitacaoVolumeSuspeitoValidator validator = new SolicitacaoVolumeSuspeitoValidator();
	
	public ModelAndView inserir(HttpServletRequest request, HttpServletResponse response,@Valid @ModelAttribute(FORM_NAME)  BaseForm _form,BindingResult bindingResult,Model model) throws Exception
	{
		ModelAndView mav = new ModelAndView(getViewName());
		SolicitacaoVolumeSuspeitoForm form = (SolicitacaoVolumeSuspeitoForm)_form;
		form.setResultado(null);
		getServiceManager().getVolumeSuspeitoService().criaSolicitacaoArquivo(form.getMinutos(), form.getValorBruto(), form.getDataInicial(), form.getDataFinal());
		form = new SolicitacaoVolumeSuspeitoForm();
		form.setResultado("OK");
		mav.addObject(FORM_NAME, form);
		return mav;
	}
	
	 
	protected String getViewName() {
		return "assinante_suspeito_solicitacao";
	}

	 
	protected SolicitacaoVolumeSuspeitoForm getForm() {
		return new SolicitacaoVolumeSuspeitoForm();
	}

	 
	protected Validator getValidator() {
		return this.validator;
	}

	
	
}
