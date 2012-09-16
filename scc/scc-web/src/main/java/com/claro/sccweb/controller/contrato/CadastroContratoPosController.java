package com.claro.sccweb.controller.contrato;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.claro.cobillingweb.persistence.entity.SccContratoCobl;
import com.claro.sccweb.controller.BaseCRUDAndMethodController;
import com.claro.sccweb.controller.validator.CadastroContratoPosValidator;
import com.claro.sccweb.decorator.rownum.entity.SccContratoCoblDecorator;
import com.claro.sccweb.form.CadastroContratoPosForm;

@Controller
@RequestMapping(value="/user/contrato/pos")
public class CadastroContratoPosController extends BaseCRUDAndMethodController<CadastroContratoPosForm> {

	private final CadastroContratoPosValidator validator = new CadastroContratoPosValidator();
	
	public ModelAndView iniciar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = super.iniciar(request, response);
		List<SccContratoCobl> listaContratos = getServiceManager().getContratoService().getAll();
		List<SccContratoCoblDecorator> decoratorList = new ArrayList<SccContratoCoblDecorator>(); 
		for (int i=0;i<listaContratos.size();i++) {
			SccContratoCoblDecorator decorator = new SccContratoCoblDecorator(listaContratos.get(i), i);
			decoratorList.add(decorator);
		}
		storeInSession(getClass(), DISPLAY_TAG_SPACE_1, decoratorList, request);
		return mav;
	}
	
	protected String getViewName() {
		return "contrato_pos_cadastro";
	}
	
	protected CadastroContratoPosForm getForm() {
		CadastroContratoPosForm form = new CadastroContratoPosForm();
		form.setEntity(new SccContratoCobl());
		return form;
	}
	
	protected Validator getValidator() {
		return validator;
	}
	
	protected ModelAndView limpar(HttpServletRequest request, HttpServletResponse response, CadastroContratoPosForm form,BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());
		form.setEntity(new SccContratoCobl());
		mav.addObject("filtro", form);
		return mav; 
	}

	/*No início da tela todos os contratos já são mostrados.*/
	 
	protected ModelAndView pesquisar(HttpServletRequest request, HttpServletResponse response, CadastroContratoPosForm form,BindingResult bindingResult, Model model) throws Exception {		
		return null;
	}
	
	protected ModelAndView salvar(HttpServletRequest request, HttpServletResponse response, CadastroContratoPosForm form,BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = null;
		Date dtCriacao = Calendar.getInstance().getTime();
		String usuario = getSessionDataManager().getUserPrincipal();
		form.getEntity().setDtCriacao(dtCriacao);
		form.getEntity().setCdUsuarioManut(usuario);
		getServiceManager().getContratoService().create(form.getEntity());
		form.setEntity(new SccContratoCobl());
		return iniciar(request, response);		
	}
	
	protected ModelAndView remover(HttpServletRequest request, HttpServletResponse response, CadastroContratoPosForm form,BindingResult bindingResult, Model model) throws Exception {
		int itemIndex = form.getItemSelecionado();
		SccContratoCoblDecorator decorator = ((List<SccContratoCoblDecorator>)request.getSession().getAttribute(DISPLAY_TAG_SPACE_1)).get(itemIndex);
		getServiceManager().getContratoService().delete(decorator.getRow());
		return iniciar(request, response);
	}
	
	protected ModelAndView atualizar(HttpServletRequest request, HttpServletResponse response, CadastroContratoPosForm form,BindingResult bindingResult, Model model) throws Exception {
		Date dtAtualizacao = Calendar.getInstance().getTime();
		String usuario = getSessionDataManager().getUserPrincipal();
		int itemIndex = form.getItemSelecionado();
		
		SccContratoCoblDecorator decorator = ((List<SccContratoCoblDecorator>)request.getSession().getAttribute(DISPLAY_TAG_SPACE_1)).get(itemIndex);
		
		SccContratoCobl entity = form.getEntity();
		
		entity.setDtAtualizacao(dtAtualizacao);
		entity.setCdUsuarioManut(usuario);
		
		getServiceManager().getContratoService().update(entity);
		
		return iniciar(request, response);
	}
	
	protected ModelAndView editar(HttpServletRequest request, HttpServletResponse response, CadastroContratoPosForm form,BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());
		int itemIndex = form.getItemSelecionado();
		SccContratoCoblDecorator decorator = ((List<SccContratoCoblDecorator>)request.getSession().getAttribute(DISPLAY_TAG_SPACE_1)).get(itemIndex);
		SccContratoCobl entity = decorator.getRow();
		form.setEntity(entity);
		mav.addObject("filtro",form);
		return mav;
	}
	
	protected Serializable getPkEntidade(Object entidadeSelecionada) {
		SccContratoCoblDecorator entity = (SccContratoCoblDecorator)entidadeSelecionada;
		return entity.getRow().getCdContratoCobilling();
	}
	
	protected void atualizarDadosTabela(HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
	}
	
}
