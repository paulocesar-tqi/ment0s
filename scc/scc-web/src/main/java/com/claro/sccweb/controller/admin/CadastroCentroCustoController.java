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

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.entity.SccCentro;
import com.claro.cobillingweb.persistence.entity.SccCentroPK;
import com.claro.cobillingweb.persistence.entity.SccOperadora;
import com.claro.sccweb.controller.BaseCRUDAndMethodController;
import com.claro.sccweb.controller.util.BasicStringItem;
import com.claro.sccweb.controller.validator.CadastroCentroCustoValidator;
import com.claro.sccweb.decorator.rownum.entity.SccCentroDecorator;
import com.claro.sccweb.form.CadastroCentroCustoForm;

@Controller
@RequestMapping(value="/user/admin/centros")
public class CadastroCentroCustoController extends BaseCRUDAndMethodController<CadastroCentroCustoForm> {
	
	private final CadastroCentroCustoValidator validator = new CadastroCentroCustoValidator();
	
	public ModelAndView iniciar(HttpServletRequest request,HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());
		super.iniciar(request, response);
		List<SccCentro> rows = getServiceManager().getAdminService().getAllCentro();
		List<SccCentroDecorator> decoratorList = new ArrayList<SccCentroDecorator>(rows.size());
		for (int i=0;i<rows.size();i++) {
			SccOperadora objOpe =  getServiceManager().getOperadoraService().pesquisaOperadoraByPk(rows.get(i).getId().getCdEotClaro());
			rows.get(i).getId().setCdEotClaro(objOpe.getDsOperadora());
			SccCentroDecorator decorator = new SccCentroDecorator(rows.get(i), i);
			decoratorList.add(decorator);
		}
		storeInSession(getClass(), DISPLAY_TAG_SPACE_1, decoratorList, request);
		cacheMyForm(getClass(), getForm());
		mav.addObject(FORM_NAME, getForm());
		return mav;
	}
	
	protected ModelAndView limpar(HttpServletRequest request,HttpServletResponse response, CadastroCentroCustoForm form,BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());
		mav.addObject(FORM_NAME, getForm());
		return mav;
	}
	
	protected ModelAndView pesquisar(HttpServletRequest request,HttpServletResponse response, CadastroCentroCustoForm form,BindingResult bindingResult, Model model) throws Exception {	
		ModelAndView mav = new ModelAndView(getViewName());
		return mav;
	}
	
	protected ModelAndView salvar(HttpServletRequest request,HttpServletResponse response, CadastroCentroCustoForm form,BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());
		SccCentro entity = form.getEntity();
		entity.setDtCriacao(Calendar.getInstance().getTime());
		entity.setCdUsuario(getSessionDataManager().getUserPrincipal());
		getServiceManager().getAdminService().create(entity);
		return mav;
	}
	
	protected ModelAndView remover(HttpServletRequest request,HttpServletResponse response, CadastroCentroCustoForm form,BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());
		SccCentroDecorator decorator = (SccCentroDecorator)form.getEntidadeSelecionada();
		getServiceManager().getAdminService().delete(decorator.getRow());
		return mav;
	}
	
	protected ModelAndView atualizar(HttpServletRequest request,HttpServletResponse response, CadastroCentroCustoForm form,BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());
		SccCentro entity = form.getEntity();
		entity.setId((SccCentroPK)getPkEntidade(getItem(request, form)));
		entity.setDtAtualizacao(Calendar.getInstance().getTime());
		entity.setCdUsuario(getSessionDataManager().getUserPrincipal());
		getServiceManager().getAdminService().update(entity);
		return mav;
	}
	
	protected ModelAndView editar(HttpServletRequest request,HttpServletResponse response, CadastroCentroCustoForm form,BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());
		int itemIndex = form.getItemSelecionado();
		SccCentroDecorator decorator = ((List<SccCentroDecorator>)request.getSession().getAttribute(DISPLAY_TAG_SPACE_1)).get(itemIndex);
		SccCentro entity = decorator.getRow();
		form.setEntity(entity);
		mav.addObject("filtro",form);
		return mav;
	}
	
	protected String getViewName() {		
		return "centro_custo_filtro";
	}
	
	protected CadastroCentroCustoForm getForm() {		
		return new CadastroCentroCustoForm();
	}
	
	protected Validator getValidator() {
		return this.validator;
	}
	
	protected Serializable getPkEntidade(Object entidadeSelecionada) {
		SccCentroDecorator decorator = (SccCentroDecorator)entidadeSelecionada;		
		return decorator.getRow().getId();
	}
	
	protected void atualizarDadosTabela(HttpServletRequest request) throws Exception {
		List<SccCentro> rows = getServiceManager().getAdminService().getAllCentro();
		List<SccCentroDecorator> decoratorList = new ArrayList<SccCentroDecorator>(rows.size());
		for (int i=0;i<rows.size();i++) {
			SccCentroDecorator decorator = new SccCentroDecorator(rows.get(i), i);
			decoratorList.add(decorator);
		}
		storeInSession(getClass(), DISPLAY_TAG_SPACE_1, decoratorList, request);
	}
	
	@ModelAttribute("centro")
	public List<BasicStringItem> populaCentro() {
		List<BasicStringItem> comboList = new ArrayList<BasicStringItem>();
		comboList.add(new BasicStringItem("CC", "Centro de Custo"));
		comboList.add(new BasicStringItem("CL", "Centro de Lucro"));
		return comboList;				
	}
	
	@ModelAttribute("operadorasClaro")
	public List<SccOperadora> populaOperadorasClaro() throws Exception {
		List<SccOperadora> comboList = new ArrayList<SccOperadora>();
		SccOperadora allValues = new SccOperadora();
		allValues.setCdEot(BasicDAO.GET_ALL_STRING);
		allValues.setDsOperadora("Todas");
		comboList.add(0,allValues);
		comboList.addAll(getServiceManager().getPesquisaDominiosService().pequisaOperadorasClaroComM());
		return comboList;
	}
	/*
	@ModelAttribute("operadorasClaro")
	public  List<SccOperadora> populaOperadoras() throws Exception {
		return super._populaOperadorasClaro(true);
	}
	*/
	
}
