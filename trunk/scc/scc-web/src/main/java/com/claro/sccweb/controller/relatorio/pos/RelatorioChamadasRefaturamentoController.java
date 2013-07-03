package com.claro.sccweb.controller.relatorio.pos;

import java.util.ArrayList;
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
import org.springframework.web.servlet.ModelAndView;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccOperadora;
import com.claro.cobillingweb.persistence.view.SccChamadasRefaturamentoView;
import com.claro.sccweb.controller.BaseOperationController;
import com.claro.sccweb.controller.util.BasicStringItem;
import com.claro.sccweb.controller.validator.RelatorioChamadasRefaturamentoValidator;
import com.claro.sccweb.decorator.rownum.entity.SccChamadasRefaturamentoViewDecorator;
import com.claro.sccweb.form.BaseForm;
import com.claro.sccweb.form.RelatorioChamadasRefaturamentoForm;
import com.claro.sccweb.service.SccChamadasRefaturamentoService;
import com.claro.sccweb.service.ServiceException;

@Controller
@RequestMapping(value="/user/relatorio/refaturamento/chamadasRefaturamento")
public class RelatorioChamadasRefaturamentoController extends
		BaseOperationController<RelatorioChamadasRefaturamentoForm> {
	
	@Autowired
	private SccChamadasRefaturamentoService sccChamadasRefaturamentoService;
	
	private final RelatorioChamadasRefaturamentoValidator validator = new RelatorioChamadasRefaturamentoValidator();
	
	public ModelAndView pesquisar(HttpServletRequest request, HttpServletResponse response, BaseForm form, BindingResult bindingResult, Model model) throws Exception {
		
		RelatorioChamadasRefaturamentoForm formChamadasRefaturamento = (RelatorioChamadasRefaturamentoForm)form;
		List<SccChamadasRefaturamentoView> rows = gerarRelatorioChamadasRefaturamento(formChamadasRefaturamento);
		
		List<SccChamadasRefaturamentoViewDecorator> decoratorList = new ArrayList<SccChamadasRefaturamentoViewDecorator>(rows.size());
		
		for (int i = 0; i < rows.size(); i++) {
			
			SccChamadasRefaturamentoViewDecorator decorator = new SccChamadasRefaturamentoViewDecorator(rows.get(i), i);
			decoratorList.add(decorator);
		}
		storeInSession(getClass(), DISPLAY_TAG_SPACE_1, decoratorList, request);
		ModelAndView mav = new ModelAndView(getViewName());
		return mav;

	}
	
	private List<SccChamadasRefaturamentoView> gerarRelatorioChamadasRefaturamento(RelatorioChamadasRefaturamentoForm form) throws DAOException, ServiceException {
		return sccChamadasRefaturamentoService.gerarRelatorioChamadasRefaturamento(form.getOperadoraLD(), form.getOperadoraClaro(), form.getTipoRefaturamento(), form.getStatusChamada(), form.getDtInicial(), form.getDtFinal());
	}


	@Override
	protected String getViewName() {
		
		return "relatorio_chamadas_refaturamento_filtro";
	}

	@Override
	protected RelatorioChamadasRefaturamentoForm getForm() {
		
		return new RelatorioChamadasRefaturamentoForm();
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
	
	@ModelAttribute("codigoRefaturamento")
	public List<BasicStringItem> populaCodigoRefaturamento() {
		List<BasicStringItem> comboList = new ArrayList<BasicStringItem>();
		comboList.add(new BasicStringItem(BasicDAO.GET_ALL_STRING,"Todos"));
		comboList.add(new BasicStringItem("RC","RC"));
		comboList.add(new BasicStringItem("RD","RD"));
		comboList.add(new BasicStringItem("RR","RR"));
		return comboList;
	}
	
	@ModelAttribute("statusChamada")
	public List<BasicStringItem> populaStatusChamada() {
		List<BasicStringItem> comboList = new ArrayList<BasicStringItem>();
		comboList.add(new BasicStringItem("Fat","A Faturar/Faturada"));
		comboList.add(new BasicStringItem("Rej","Aceite/Rejeitada"));
		return comboList;
	}
	
	@RequestMapping(value="/tab1" , method = RequestMethod.GET)
	public ModelAndView tab1(HttpServletRequest request, HttpServletResponse response) throws Exception {
			ModelAndView mav = new ModelAndView(getViewName());
			Object form = getMyFormFromCache(getClass());
			if (form != null)
				mav.addObject(FORM_NAME, form);
			else
				mav.addObject(FORM_NAME, getForm());
	    	return mav;  
	}

	public ModelAndView excel(HttpServletRequest request,HttpServletResponse response,@Valid @ModelAttribute(FORM_NAME) BaseForm _form,BindingResult bindingResult, Model model) throws Exception {
		return new ModelAndView("relatorio_chamadas_refaturamento_excel");
	}
	
	@Override
	protected Validator getValidator() {
		
		return this.validator;
	}
	
	

	public SccChamadasRefaturamentoService getSccChamadasRefaturamentoService() {
		return sccChamadasRefaturamentoService;
	}

	public void setSccChamadasRefaturamentoService(
			SccChamadasRefaturamentoService sccChamadasRefaturamentoService) {
		this.sccChamadasRefaturamentoService = sccChamadasRefaturamentoService;
	}

}
