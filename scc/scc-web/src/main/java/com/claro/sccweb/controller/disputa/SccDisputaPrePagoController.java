package com.claro.sccweb.controller.disputa;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccDisputaPrePago;
import com.claro.cobillingweb.persistence.entity.SccOperadora;
import com.claro.cobillingweb.persistence.filtro.SccFiltroDisputaPrePago;
import com.claro.sccweb.controller.BaseOperationController;
import com.claro.sccweb.controller.util.BasicIntegerItem;
import com.claro.sccweb.controller.util.BasicStringItem;
import com.claro.sccweb.controller.validator.SccDisputaPrePagoValidator;
import com.claro.sccweb.form.SccDisputaPrePagoForm;
import com.claro.sccweb.service.SccDisputaPrePagoService;
import com.claro.sccweb.service.ServiceException;

@Controller
@RequestMapping(value="/user/disputa/pre/cadastro")
public class SccDisputaPrePagoController extends BaseOperationController<SccDisputaPrePagoForm> {
	
	private static final String FWD_VIEW_LISTA_DISPUTA_PRE_PAGO = "relatorio_disputa_pre_pago";
	private static final String FWD_EXCEL_LISTA_DISPUTA_PRE_PAGO ="relatorio_disputa_pre_pago_excel";
	
	@Autowired
	private SccDisputaPrePagoService sccDisputaPrePagoService;
	
	private final SccDisputaPrePagoValidator validator = new SccDisputaPrePagoValidator();
	
	@RequestMapping(value="listar", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView pesquisar(HttpServletRequest request, HttpServletResponse response, SccDisputaPrePagoForm form) throws Exception {
		
		form.setListDisputaPrePago(gerarListaDisputaPrePago(form.getFiltro()));
		ModelAndView mav = null;
		if(form.getOperacao().equals("pesquisar")){
			mav = new ModelAndView(FWD_VIEW_LISTA_DISPUTA_PRE_PAGO, "filtro", form);
		}else{
			mav = new ModelAndView(FWD_EXCEL_LISTA_DISPUTA_PRE_PAGO, "filtro", form);
		}
		return mav;
		
	}
	
	private List<SccDisputaPrePago> gerarListaDisputaPrePago(SccFiltroDisputaPrePago filtro) throws ServiceException, DAOException{
		
		return (List<SccDisputaPrePago>) this.sccDisputaPrePagoService.pesquisarDisputaPrePago(filtro);
	}
	
	@RequestMapping(value="/salvarEntity", method=RequestMethod.POST)
	public @ResponseBody ModelAndView salvarEntity(HttpServletRequest request, HttpServletResponse response, SccDisputaPrePagoForm form) throws Exception {

		ModelAndView mav = new ModelAndView(FWD_VIEW_LISTA_DISPUTA_PRE_PAGO);
		
		this.sccDisputaPrePagoService.saveOrUpdate(form.getEntity());
		mav.addObject("filtro", form);	
		return mav;

	}
	
	@RequestMapping(value="/novaEntity", method=RequestMethod.POST)
	public @ResponseBody ModelAndView novaEntity(HttpServletRequest request, HttpServletResponse response, SccDisputaPrePagoForm form) throws Exception{
		
		ModelAndView mav = new ModelAndView(FWD_VIEW_LISTA_DISPUTA_PRE_PAGO);
		this.sccDisputaPrePagoService.create(form.getEntity());
		mav.addObject("filtro", getForm());
		return mav;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public ModelAndView editar(HttpServletRequest request,HttpServletResponse response, SccDisputaPrePagoForm form,BindingResult bindingResult, Model model) throws Exception {
		
		ModelAndView mav = new ModelAndView(FWD_VIEW_LISTA_DISPUTA_PRE_PAGO);
		int itemIndex = form.getItemSelecionado();
		
		SccDisputaPrePago entidade  = ((List<SccDisputaPrePago>)request.getSession().getAttribute(DISPLAY_TAG_SPACE_1)).get(itemIndex);
		form.setEntity(entidade);
		mav.addObject("filtro",form);
		return mav;

	}
	
	@RequestMapping(value = "/editarDisputa", method=RequestMethod.GET)
	public @ResponseBody SccDisputaPrePago editarEntity(@RequestParam("sqDisputaPrePago")Long sqDisputaPrePago, HttpServletRequest request,HttpServletResponse response, SccDisputaPrePagoForm form, BindingResult bindingResult, Model model) throws Exception {
		
		ModelAndView mav = new ModelAndView(FWD_VIEW_LISTA_DISPUTA_PRE_PAGO);
		SccDisputaPrePago entity = (SccDisputaPrePago) this.sccDisputaPrePagoService.findBySqDisputaPrePago(sqDisputaPrePago);
		form.setEntity(entity);
		mav.addObject("filtro",form);
		return form.getEntity();
	}
	
	
	@ModelAttribute("meses")
	public List<BasicIntegerItem> populaMeses() {
		return _populaComboMeses();
	}

	@ModelAttribute("operadorasExternas")
	public List<SccOperadora> populaOperadorasExternas() throws Exception {
		List<SccOperadora> comboList = new ArrayList<SccOperadora>();
		SccOperadora allValues = new SccOperadora();
		allValues.setCdEot("*");
		allValues.setDsOperadora("Todas");
		comboList.add(0,allValues);
		comboList.addAll(getServiceManager().getPesquisaDominiosService().pesquisaOperadorasExternas());
		return comboList;
	}
	
	
	@ModelAttribute("statusDisputas")
	public List<BasicStringItem> popularListaStatusDisputa(){
		List<BasicStringItem> comboList = new ArrayList<BasicStringItem>();
		comboList.add(new BasicStringItem("1", "Registro"));
		
		return comboList;
		
	}
	
	@ModelAttribute("riscoDisputa")
	public List<BasicStringItem> popularRiscoDispta(){
		List<BasicStringItem> comboList = new ArrayList<BasicStringItem>();
		comboList.add(new BasicStringItem("L", "Local"));
		comboList.add(new BasicStringItem("R", "Remoto"));
		return comboList;

		
	}

	@Override
	protected String getViewName() {
		
		return FWD_VIEW_LISTA_DISPUTA_PRE_PAGO;
	}

	@Override
	protected SccDisputaPrePagoForm getForm() {
		
		return new SccDisputaPrePagoForm();
	}

	@Override
	protected Validator getValidator() {
		
		return this.validator;
	}

}
