package com.claro.sccweb.controller.admin;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.claro.cobillingweb.persistence.entity.SccOperadora;
import com.claro.cobillingweb.persistence.entity.SccPreTarifaAcb;
import com.claro.sccweb.controller.BaseOperationController;
import com.claro.sccweb.controller.util.BasicStringItem;
import com.claro.sccweb.controller.validator.SccTarifaValidator;
import com.claro.sccweb.form.SccTarifaForm;
import com.claro.sccweb.service.SccTarifasCobrarService;

@Controller
@RequestMapping(value="/user/admin/tarifas/cobrar")
public class SccTarifasCobrarController extends BaseOperationController<SccTarifaForm> {

	private static final String FWD_TARIFAS_COBRAR = "cad_tarifas_cobrar";
	
	private final SccTarifaValidator validator = new SccTarifaValidator();
	
	@Autowired
	private SccTarifasCobrarService sccTarifasCobrarService;
	
	@ModelAttribute("tipoPlanos")
	public List<BasicStringItem> popularPlanos() throws Exception {
		List<BasicStringItem> comboList = new ArrayList<BasicStringItem>();
		comboList.add(new BasicStringItem("Bs", "Básico"));
		comboList.add(new BasicStringItem("CT", "Controle"));
		
		return comboList;
		
	}
	
	@ModelAttribute("status")
	public List<BasicStringItem> popularStatus() throws Exception {
		List<BasicStringItem> comboList = new ArrayList<BasicStringItem>();
		comboList.add(new BasicStringItem("A", "Ativo"));
		comboList.add(new BasicStringItem("I", "Inativo"));
		
		return comboList;
		
	}
 
	@ModelAttribute("operadorasExternas")
	public List<SccOperadora> populaOperadorasExternas() throws Exception {
		List<SccOperadora> comboList = new ArrayList<SccOperadora>();
		comboList.addAll(getServiceManager().getPesquisaDominiosService().pesquisaOperadorasExternas());
		return comboList;
	}
	
	@RequestMapping(value="listar", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView pesquisar(HttpServletRequest request, HttpServletResponse response, SccTarifaForm form) throws Exception {
		
		ModelAndView mav = null;
		
		List<SccPreTarifaAcb> list = this.sccTarifasCobrarService.findByOperadora(form.getCdEotLd());
		
		form.setListPreTarifaAcb(list);
		mav = new ModelAndView(FWD_TARIFAS_COBRAR, "filtro", form);
		
		return mav;
		
	}
	
	@RequestMapping(value = "/editarItemsTarifa2", method=RequestMethod.GET)
	public ModelAndView editarItensTarifa(@RequestParam("idTarifa") Long idTarifa , HttpServletRequest request,HttpServletResponse response, SccTarifaForm form) throws Exception {
		
		
		form.setEntity(this.sccTarifasCobrarService.findById(idTarifa));
		ModelAndView mav = new ModelAndView(FWD_TARIFAS_COBRAR, "filtro", form);

		return mav;
		
	}
	
	@RequestMapping(value = "/editarItemsTarifa", method=RequestMethod.GET)
	public @ResponseBody SccPreTarifaAcb editarItemsTarifa(@RequestParam("idTarifa") Long idTarifa , HttpServletRequest request,HttpServletResponse response, SccTarifaForm form) throws Exception {
		
		form.setEntity(this.sccTarifasCobrarService.findById(idTarifa));
				
		return form.getEntity(); 
		
	}

	@Override
	protected String getViewName() {
		
		return FWD_TARIFAS_COBRAR;
	}

	@Override
	protected SccTarifaForm getForm() {
		
		return new SccTarifaForm();
	}

	@Override
	protected Validator getValidator() {
		
		return this.validator;
	}

	public void setSccTarifasCobrarService(
			SccTarifasCobrarService sccTarifasCobrarService) {
		this.sccTarifasCobrarService = sccTarifasCobrarService;
	}

	
	
}
