package com.claro.sccweb.controller.admin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.claro.cobillingweb.persistence.entity.SccCtlParalizacaoPlat;
import com.claro.cobillingweb.persistence.entity.SccOperadora;
import com.claro.sccweb.controller.BaseCRUDAndMethodController;
import com.claro.sccweb.controller.util.BasicIntegerItem;
import com.claro.sccweb.controller.util.BasicStringItem;
import com.claro.sccweb.controller.validator.ControleParalisacaoValidator;
import com.claro.sccweb.decorator.SccCtlParalizacaoPlatDecorator;
import com.claro.sccweb.form.ControleParalisacaoForm;

@Controller
@RequestMapping(value="/user/admin/paralisacao")
public class ControleParalisacaoController extends BaseCRUDAndMethodController<ControleParalisacaoForm> {
	
	private final ControleParalisacaoValidator validator = new ControleParalisacaoValidator();
	
	protected ModelAndView limpar(HttpServletRequest request,HttpServletResponse response, ControleParalisacaoForm form,BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());
		mav.addObject(FORM_NAME, getForm());
		return mav;
	}
	
	protected ModelAndView pesquisar(HttpServletRequest request,HttpServletResponse response, ControleParalisacaoForm form,BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());
		List<SccCtlParalizacaoPlat> rows = getServiceManager().getAdminService().pesquisaParalizacoes(form.getFiltro(), form.getDtInicial(), form.getDtFinal());
		List<SccCtlParalizacaoPlatDecorator> decoratorList = new ArrayList<SccCtlParalizacaoPlatDecorator>(rows.size());
		
		for (int i=0;i<rows.size();i++) {
			SccOperadora objOpe =  getServiceManager().getOperadoraService().pesquisaOperadoraByPk(rows.get(i).getCdEotLd());
			rows.get(i).setCdEotLd(objOpe.getDsOperadora());
			SccCtlParalizacaoPlatDecorator decorator = new SccCtlParalizacaoPlatDecorator(rows.get(i), i);
			decoratorList.add(decorator);
		}
		
		storeInSession(getClass(), DISPLAY_TAG_SPACE_1, decoratorList, request);
		cacheMyForm(getClass(), form);
		mav.addObject(FORM_NAME, form);
		
		return mav;
	}
	
	protected ModelAndView salvar(HttpServletRequest request,HttpServletResponse response, ControleParalisacaoForm form,BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());
		SccCtlParalizacaoPlat entity = form.getEntity();
		entity.setCdUsuarioRespRegistro(getSessionDataManager().getUserPrincipal());
		entity.setDtRegistroOcorrencia(Calendar.getInstance().getTime());
		getServiceManager().getAdminService().create(entity);
		return mav;
	}
	
	/*Operação não suportada.*/
	protected ModelAndView remover(HttpServletRequest request,HttpServletResponse response, ControleParalisacaoForm form,BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());
		return mav;
	}
	
	protected ModelAndView atualizar(HttpServletRequest request,HttpServletResponse response, ControleParalisacaoForm form,BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());
		SccCtlParalizacaoPlat entity = form.getEntity();
		entity.setCdUsuarioRespRegistro(getSessionDataManager().getUserPrincipal());
		entity.setDtRegistroOcorrencia(Calendar.getInstance().getTime());
		getServiceManager().getAdminService().update(entity);
		return mav;
	}
	
	protected ModelAndView editar(HttpServletRequest request,HttpServletResponse response, ControleParalisacaoForm form,BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());
		int itemIndex = form.getItemSelecionado();
		SccCtlParalizacaoPlatDecorator decorator = ((List<SccCtlParalizacaoPlatDecorator>)request.getSession().getAttribute(DISPLAY_TAG_SPACE_1)).get(itemIndex);
		SccCtlParalizacaoPlat entity = decorator.getRow();
		form.setEntity(entity);
		mav.addObject("filtro",form);
		return mav;
	}
	
	protected String getViewName() {
		return "controle_paralisacao_filtro";
	}
	
	protected ControleParalisacaoForm getForm() {
		return new ControleParalisacaoForm();
	}
	
	protected Validator getValidator() {
		return this.validator;
	}
	
	protected Serializable getPkEntidade(Object entidadeSelecionada) {
		SccCtlParalizacaoPlatDecorator decorator = (SccCtlParalizacaoPlatDecorator)entidadeSelecionada;
		return decorator.getRow().getSqParalizacaoPlat();
	}
	
	protected void atualizarDadosTabela(HttpServletRequest request) throws Exception {
		ControleParalisacaoForm form = (ControleParalisacaoForm)getMyFormFromCache(getClass());
		if (form != null) {
			List<SccCtlParalizacaoPlat> rows = getServiceManager().getAdminService().pesquisaParalizacoes(form.getFiltro(), form.getDtInicial(), form.getDtFinal());
			List<SccCtlParalizacaoPlatDecorator> decoratorList = new ArrayList<SccCtlParalizacaoPlatDecorator>(rows.size());
			for (int i=0;i<rows.size();i++) {
				SccCtlParalizacaoPlatDecorator decorator = new SccCtlParalizacaoPlatDecorator(rows.get(i), i);
				decoratorList.add(decorator);
			}
			storeInSession(getClass(), DISPLAY_TAG_SPACE_1, decoratorList, request);
			cacheMyForm(getClass(), form);
		}
	}
	
	@ModelAttribute("operadorasExternas")
	public List<SccOperadora> populaOperadorasExternas() throws Exception {
		return super.populaOperadorasExternas(false);
	}
	
	@ModelAttribute("tecnologia")
	public List<BasicIntegerItem> populaTecnologias() {
		List<BasicIntegerItem> comboList = new ArrayList<BasicIntegerItem>();
		comboList.add(new BasicIntegerItem(-1L,"Todas"));
		comboList.add(new BasicIntegerItem(1L,"GSM"));
		comboList.add(new BasicIntegerItem(2L,"TDMA BCP"));
		comboList.add(new BasicIntegerItem(3L,"TDMA TESS"));
		comboList.add(new BasicIntegerItem(4L,"TDMA ATL"));
		comboList.add(new BasicIntegerItem(5L,"TDMA BSE"));
		comboList.add(new BasicIntegerItem(6L,"TDMA AMERICEL"));
		comboList.add(new BasicIntegerItem(7L,"TDMA TELET"));
		return comboList;
	}
	/*
	@ModelAttribute("tipoFalha")
	public List<BasicIntegerItem> populaTipoFalha()
	{
		List<BasicIntegerItem> comboList = new ArrayList<BasicIntegerItem>();
		comboList.add(new BasicIntegerItem(-1L,"Todas"));
		comboList.add(new BasicIntegerItem(1L,"SCP"));
		comboList.add(new BasicIntegerItem(2L,"SDP"));
		return comboList;
	}
	*/
	
	@ModelAttribute("tipoFalha")
	public List<BasicStringItem> populaTipoFalha() {
		List<BasicStringItem> comboList = new ArrayList<BasicStringItem>();
		comboList.add(new BasicStringItem("*","Todas"));
		comboList.add(new BasicStringItem("1","SCP"));
		comboList.add(new BasicStringItem("2","SDP"));
		return comboList;
	}
	
	@ModelAttribute("desbloqueioSolicitado")
	public List<BasicStringItem> populaDesbloqueioSolicitado() {
		List<BasicStringItem> comboList = new ArrayList<BasicStringItem>();
		comboList.add(new BasicStringItem("*", "Todos"));
		comboList.add(new BasicStringItem("S", "Sim"));
		comboList.add(new BasicStringItem("N", "Não"));
		return comboList;
	}
	
}
