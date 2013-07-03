package com.claro.sccweb.controller.relatorio.pre;

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

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccOperadora;
import com.claro.cobillingweb.persistence.entity.SccPreDominio;
import com.claro.cobillingweb.persistence.view.SccPrePagoCreditosView;
import com.claro.sccweb.controller.BaseOperationController;
import com.claro.sccweb.controller.util.BasicStringItem;
import com.claro.sccweb.controller.validator.RelatorioPrePagoCreditosValidator;
import com.claro.sccweb.decorator.rownum.entity.SccPrePagoCreditosViewDecorator;
import com.claro.sccweb.form.BaseForm;
import com.claro.sccweb.form.RelatorioPrePagoCreditosForm;
import com.claro.sccweb.service.SccPrePagoCreditosService;
import com.claro.sccweb.service.ServiceException;

@Controller
@RequestMapping(value="/user/relatorio/pre/creditos")
public class RelatorioPrePagoCreditosController extends
		BaseOperationController<RelatorioPrePagoCreditosForm> {
	
	@Autowired
	private SccPrePagoCreditosService sccPrePagoCreditosService;
	
	private final RelatorioPrePagoCreditosValidator validator = new RelatorioPrePagoCreditosValidator();
	
	public ModelAndView pesquisar(HttpServletRequest request, HttpServletResponse response, BaseForm form, BindingResult bindingResult, Model model) throws Exception {
		
		RelatorioPrePagoCreditosForm formPrePagoCreditos = (RelatorioPrePagoCreditosForm)form;
		List<SccPrePagoCreditosView> rows = gerarRelatorioPrePagoCreditos(formPrePagoCreditos);
		
		List<SccPrePagoCreditosViewDecorator> decoratorList = new ArrayList<SccPrePagoCreditosViewDecorator>(rows.size());
		
		for (int i = 0; i < rows.size(); i++) {
			
			SccPrePagoCreditosViewDecorator decorator = new SccPrePagoCreditosViewDecorator(rows.get(i), i);
			decoratorList.add(decorator);
		}
		storeInSession(getClass(), DISPLAY_TAG_SPACE_1, decoratorList, request);
		ModelAndView mav = new ModelAndView(getViewName());
		return mav;

	}
	
	private List<SccPrePagoCreditosView> gerarRelatorioPrePagoCreditos(RelatorioPrePagoCreditosForm form) throws DAOException, ServiceException {
		return sccPrePagoCreditosService.gerarRelatorioPrePagoCreditos(form.getDtInicial(), form.getDtFinal(), form.getTipoCredito(), form.getCdStatusCredito(), form.getOperadoraClaro(), form.getOperadoraLD());				
	}


	@Override
	protected String getViewName() {		
		return "relatorio_prepago_creditos_filtro";
	}

	@Override
	protected RelatorioPrePagoCreditosForm getForm() {		
		return new RelatorioPrePagoCreditosForm();
	}
	
	//public ModelAndView listar(HttpServletRequest request, HttpServletResponse response,@Valid @ModelAttribute(FORM_NAME)  BaseForm form,BindingResult bindingResult,Model model) throws Exception {
		//cleanSession(getClass(), request, DISPLAY_TAG_SPACE_1);
		//ModelAndView mav = new ModelAndView(getListaCDRsView());
		//RelatorioPrePagoCreditosForm myForm = (RelatorioPrePagoCreditosForm)form;
		//RelatorioPrePagoCreditosForm myCachedForm = (RelatorioPrePagoCreditosForm)getMyFormFromCache(getClass());//
		//SccPrePagoCreditosViewDecorator decorator = (SccPrePagoCreditosViewDecorator)getItemSelecionado(request, DISPLAY_TAG_SPACE_1, form);
		//myForm.setFiltroSelecionado(decorator.getRow());
		//myForm.setArquivoSelecionado(myCachedForm.getArquivoSelecionado());

		//myForm.setVisaoArquivo(myCachedForm.getVisaoArquivo());
		//SccPaginatedList paginatedList = new SccPaginatedList();
		//paginatedList.setObjectsPerPage(TAMANHO_PAGINA);
		//paginatedList.setPageNumber(1);		
		
		//List<SccCdrCobilling> rows = getServiceManager().getArquivosService().listaCDRsArquivo(myForm.getArquivoSelecionado().getSqArquivo(), myForm.getFiltroSelecionado(), paginatedList.getPageNumber()-1, TAMANHO_PAGINA);
		//List<SccCdrCobillingDecorator> decoratorList = new ArrayList<SccCdrCobillingDecorator>(rows.size());
		//for (int i=0;i<rows.size();i++) {
		//	SccCdrCobillingDecorator cdrDecorator = new SccCdrCobillingDecorator(rows.get(i), i);
		//	decoratorList.add(cdrDecorator);			
		//}
		//paginatedList.setList(decoratorList);
		//paginatedList.setFullListSize(getServiceManager().getArquivosService().contaCDRsArquivo(myForm.getArquivoSelecionado().getSqArquivo(), myForm.getFiltroSelecionado()).intValue());
		//myForm.setCdrList(paginatedList);
		//storeInSession(getClass(), DISPLAY_TAG_SPACE_3, paginatedList, request);
		//cacheMyForm(getClass(), myForm);
		//return mav;
	
	@ModelAttribute("operadorasExternas")
	public List<SccOperadora> populaOperadorasExternas() throws Exception {
		List<SccOperadora> comboList = new ArrayList<SccOperadora>();
		SccOperadora allValues = new SccOperadora();
		allValues.setCdEot("");
		allValues.setDsOperadora("Todas");
		comboList.add(0,allValues);
		comboList.addAll(getServiceManager().getPesquisaDominiosService().pesquisaOperadorasExternas());
		return comboList;
	}
	
	@ModelAttribute("operadorasClaro")
	public List<SccOperadora> populaOperadorasClaro() throws Exception {
		List<SccOperadora> comboList = new ArrayList<SccOperadora>();
		SccOperadora allValues = new SccOperadora();
		allValues.setCdEot("");
		allValues.setDsOperadora("Todas");
		comboList.add(0,allValues);
		comboList.addAll(getServiceManager().getPesquisaDominiosService().pequisaOperadorasClaroComM());
		return comboList;
	}
	
    @ModelAttribute("tipoCredito")
    public List<BasicStringItem> populatipoCredito()   throws Exception
    {
           List<BasicStringItem> comboList = new ArrayList<BasicStringItem>();
           BasicStringItem allValues = new BasicStringItem("", "Todos");
           comboList.add(allValues);
           List<SccPreDominio> rows = getServiceManager().getPesquisaDominiosService().pesquisaPorTipoDominioPre("CDORG");
           for (int i=0;i<rows.size();i++)
                  {
                  comboList.add(new BasicStringItem(rows.get(i).getId().getCdDominio(), rows.get(i).getDsDominio()));
                  }
           return comboList;
    }

    @ModelAttribute("cdStatusCredito")
    public List<BasicStringItem> populacdStatusCredito()   throws Exception
    {
           List<BasicStringItem> comboList = new ArrayList<BasicStringItem>();
           BasicStringItem allValues = new BasicStringItem("", "Todos");
           comboList.add(allValues);
           List<SccPreDominio> rows = getServiceManager().getPesquisaDominiosService().pesquisaPorTipoDominioPre("STCRD");
           for (int i=0;i<rows.size();i++)
                  {
                  comboList.add(new BasicStringItem(rows.get(i).getId().getCdDominio(), rows.get(i).getDsDominio()));
                  }
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
		return new ModelAndView("relatorio_prepago_creditos_excel");
	}
	
	@Override
	protected Validator getValidator() {
		return this.validator;
	}	

	public SccPrePagoCreditosService getSccPrePagoCreditosService() {
		return sccPrePagoCreditosService;
	}

	public void setSccPrePagoCreditosService(
			SccPrePagoCreditosService sccPrePagoCreditosService) {
		this.sccPrePagoCreditosService = sccPrePagoCreditosService;
	}
	
}
