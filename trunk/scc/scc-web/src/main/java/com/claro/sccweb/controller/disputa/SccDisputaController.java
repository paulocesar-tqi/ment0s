/**
 * 
 */
package com.claro.sccweb.controller.disputa;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.claro.cobillingweb.persistence.constants.CobillingConstants;
import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccDisputa;
import com.claro.cobillingweb.persistence.entity.SccOperadora;
import com.claro.cobillingweb.persistence.filtro.SccFiltroDisputa;
import com.claro.sccweb.controller.BaseOperationController;
import com.claro.sccweb.controller.util.BasicIntegerItem;
import com.claro.sccweb.controller.util.BasicStringItem;
import com.claro.sccweb.controller.validator.SccDisputaValidator;
import com.claro.sccweb.form.BaseForm;
import com.claro.sccweb.form.SccDisputaForm;
import com.claro.sccweb.service.SccDisputaService;
import com.claro.sccweb.service.ServiceException;

/**
 * @author vagner.souza
 *
 */
@Controller
@RequestMapping(value="/user/disputa/cadastro")
public class SccDisputaController extends BaseOperationController<SccDisputaForm> {
	
	@Autowired
	private SccDisputaService sccDisputaService;
	
	private final SccDisputaValidator validator = new SccDisputaValidator();
	
	private static final String FWD_VIEW_LISTA_DISPUTA = "relatorio_disputa_pos_pago";
	
	public static final String FWD_EXCEL_LISTA_DISPUTA = "relatorio_disputa_pos_pago_excel";
	
	
	@RequestMapping(value="listar", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView pesquisar(HttpServletRequest request, HttpServletResponse response, SccDisputaForm form) throws Exception {
		
		form.setListDisputa(gerarListaDisputa(form.getFiltro()));
		ModelAndView mav = null;
		if(form.getOperacao().equals("pesquisar")){
			mav = new ModelAndView(FWD_VIEW_LISTA_DISPUTA, "filtro", form);
		}else{
			mav = new ModelAndView(FWD_EXCEL_LISTA_DISPUTA, "filtro", form);
		}
		return mav;
		
	}
	
	private List<SccDisputa> gerarListaDisputa(SccFiltroDisputa filtro) throws ServiceException, DAOException{
		
		return (List<SccDisputa>) this.sccDisputaService.pesquisarDisputaByFiltro(filtro);
		
	}
	@SuppressWarnings("unchecked")
	public ModelAndView editar(HttpServletRequest request,HttpServletResponse response, SccDisputaForm form,BindingResult bindingResult, Model model) throws Exception {
		
		ModelAndView mav = new ModelAndView(FWD_VIEW_LISTA_DISPUTA);
		int itemIndex = form.getItemSelecionado();
		
		SccDisputa entidade  = ((List<SccDisputa>)request.getSession().getAttribute(DISPLAY_TAG_SPACE_1)).get(itemIndex);
		form.setEntity(entidade);
		mav.addObject("filtro",form);
		return mav;

	}
	
	@RequestMapping(value="/salvarEntity", method=RequestMethod.POST)
	public @ResponseBody ModelAndView salvarEntity(HttpServletRequest request, HttpServletResponse response, SccDisputaForm form) throws Exception {
		ModelAndView mav = new ModelAndView(FWD_VIEW_LISTA_DISPUTA);
		
		form.getEntity().setDtAtualizacao(new Date());
		if(form.getEntity().getDtCriacao() == null){
			form.getEntity().setDtCriacao(new Date());
			
		}
		
		this.sccDisputaService.saveOrUpdate(form.getEntity());
		mav.addObject("filtro", form);	
		return mav;

	}
	
	@RequestMapping(value="/novaEntity", method=RequestMethod.POST)
	public @ResponseBody ModelAndView novaEntity(HttpServletRequest request, HttpServletResponse response, SccDisputaForm form) throws Exception{
		
		ModelAndView mav = new ModelAndView(FWD_VIEW_LISTA_DISPUTA);
		this.sccDisputaService.create(form.getEntity());
		mav.addObject("filtro", getForm());
		return mav;
	}
	
	
	@RequestMapping(value = "/editarDisputa", method=RequestMethod.GET)
	public @ResponseBody SccDisputa editarEntity(@RequestParam("sqDisputa")Long sqDisputa, HttpServletRequest request,HttpServletResponse response, SccDisputaForm form, BindingResult bindingResult, Model model) throws Exception {
		
		ModelAndView mav = new ModelAndView(FWD_VIEW_LISTA_DISPUTA);
		SccDisputa entity = (SccDisputa) this.sccDisputaService.pesquisarBySqDisputa(sqDisputa);
		form.setEntity(entity);
		mav.addObject("filtro",form);
		return entity;
	}

	@RequestMapping(value="excel")
	public ModelAndView excel(HttpServletRequest request, HttpServletResponse response,
			@Valid @ModelAttribute(FORM_NAME)  BaseForm _form,BindingResult bindingResult,Model model) throws Exception	{
		
		SccDisputaForm form = (SccDisputaForm)_form;
		form.setListDisputa(gerarListaDisputa(form.getFiltro()));
		ModelAndView mav = new ModelAndView(FWD_EXCEL_LISTA_DISPUTA);
		return mav;
	}
	
	

	
	@Override
	protected String getViewName() {

		return FWD_VIEW_LISTA_DISPUTA;
	}

	@Override
	protected SccDisputaForm getForm() {
		return new SccDisputaForm();
	}

	@Override
	protected Validator getValidator() {
		return this.validator;
	}
	
	
	@ModelAttribute("meses")
	public List<BasicIntegerItem> populaMeses() {
		return _populaComboMeses();
	}
	
	@ModelAttribute("riscoDisputa")
	public List<BasicStringItem> popularRiscoDispta(){
		List<BasicStringItem> comboList = new ArrayList<BasicStringItem>();
		comboList.add(new BasicStringItem("L", "Local"));
		comboList.add(new BasicStringItem("R", "Remoto"));
		return comboList;

		
	}

	@ModelAttribute("operadorasExternas")
	public List<SccOperadora> populaOperadorasExternas() throws Exception {
		List<SccOperadora> comboList = new ArrayList<SccOperadora>();
		SccOperadora allValues = new SccOperadora();
		allValues.setCdEot(BasicDAO.GET_ALL_STRING);
		allValues.setDsOperadora("Todas");
		comboList.add(0,allValues);
		comboList.addAll(getServiceManager().getPesquisaDominiosService().pesquisaOperadorasExternas());
		return comboList;
	}
	
	
	@ModelAttribute("status")
    public List<BasicStringItem> getCdStatusQuestionamentoList() {
		List<BasicStringItem> comboList = new ArrayList<BasicStringItem>();
        for (int i = 0; i < CobillingConstants.vPROCESSO_QUESTIONAMENTO.size(); i++) {
        	String[] tPROCESSO = (String[]) CobillingConstants.vPROCESSO_QUESTIONAMENTO.elementAt(i);
            comboList.add(new BasicStringItem( tPROCESSO[0], tPROCESSO[2]));
		}
		
        return comboList;
    }
	
	protected ModelAndView remover(HttpServletRequest request,HttpServletResponse response, SccDisputaForm form,BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());
		SccDisputa entity = (SccDisputa) form.getEntidadeSelecionada();
		this.sccDisputaService.delete(entity);
		return mav;
	}
	

	public void setSccDisputaService(SccDisputaService sccDisputaService) {
		this.sccDisputaService = sccDisputaService;
	}
	
}
