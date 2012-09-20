package com.claro.sccweb.controller.arquivo;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

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
import com.claro.cobillingweb.persistence.entity.SccArquivoSumarizado;
import com.claro.cobillingweb.persistence.entity.SccOperadora;
import com.claro.cobillingweb.persistence.entity.SccProdutoCobilling;
import com.claro.cobillingweb.persistence.entity.SccStatusCdr;
import com.claro.sccweb.controller.BaseOperationController;
import com.claro.sccweb.controller.util.BasicIntegerItem;
import com.claro.sccweb.controller.util.BasicStringItem;
import com.claro.sccweb.controller.validator.ControleRemessaEventoValidator;
import com.claro.sccweb.decorator.rownum.entity.SccArquivoSumarizadoDecorator;
import com.claro.sccweb.form.BaseForm;
import com.claro.sccweb.form.ControleRemessaEventoForm;

@Controller
@RequestMapping(value="/user/arquivo/controle/evento")
public class ControleRemessaEventoController extends BaseOperationController<ControleRemessaEventoForm> {
	
	private final ControleRemessaEventoValidator validator = new ControleRemessaEventoValidator();
	public static final String FORM_NAME = "filtro";
	public static final String DT_REF = "REF";
	public static final String DT_CLARO = "PROC";
	
	public ModelAndView pesquisar(HttpServletRequest request, HttpServletResponse response, BaseForm form, BindingResult bindingResult, Model model) throws Exception {

		ControleRemessaEventoForm formEvento = (ControleRemessaEventoForm) form;

		/*		
		boolean operadoraClaroInformada = !formEvento.getCdEOTClaro().equals(BasicDAO.GET_ALL_STRING);
		boolean operadoraLDInformada = !formEvento.getCdEOTLD().equals(BasicDAO.GET_ALL_STRING);
		*/	
		List<SccArquivoSumarizado> rows = buscarListaEvento(formEvento);

		List<SccArquivoSumarizadoDecorator> decoratorList = new ArrayList<SccArquivoSumarizadoDecorator>(rows.size());

		/*		
		SccOperadora operadoraClaro = null;
		SccOperadora operadoraLD = null;
		if (formEvento.getCdEOTClaro().equals(BasicDAO.GET_ALL_STRING)) {
			operadoraClaro = getServiceManager().getOperadoraService().pesquisaOperadoraByPk(formEvento.getCdEOTClaro());
		}
		if (formEvento.getCdEOTLD().equals(BasicDAO.GET_ALL_STRING)) {
			operadoraLD = getServiceManager().getOperadoraService().pesquisaOperadoraByPk(formEvento.getCdEOTLD());
		}
		*/		
		for (int i=0;i<rows.size();i++) {
			/*
			if (!operadoraClaroInformada) {
				operadoraClaro = getServiceManager().getOperadoraService().pesquisaOperadoraByPk(rows.get(i).getCdEotClaro());
			}
			if (!operadoraLDInformada) {
				operadoraLD = getServiceManager().getOperadoraService().pesquisaOperadoraByPk(rows.get(i).getCdEotLd());
			}
			*/
			SccArquivoSumarizadoDecorator decorator = new SccArquivoSumarizadoDecorator(rows.get(i), i);
			//SccArquivoSumarizadoDecorator decorator = new SccArquivoSumarizadoDecorator(rows.get(i), operadoraClaro, operadoraLD, i);
			decoratorList.add(decorator);
		}
		storeInSession(getClass(), DISPLAY_TAG_SPACE_1, decoratorList, request);
		ModelAndView mav = new ModelAndView(getViewName());
		return mav;
	}
	
	private List<SccArquivoSumarizado> buscarListaEvento(ControleRemessaEventoForm form) throws DAOException {
		return getServiceManager().getControleRemessaService().pesquisaSumarioPorEvento(form.getCdEOTClaro(), form.getCdEOTLD(), form.getDataInicial(), form.getDataFinal(), form.getCdProdutoCobilling(), form.getCdStatusCdr(), form.getTipoOperadora().equals("H"), form.getTipoPeriodo().equals(DT_REF));
	}
	
	@Override
	protected String getViewName() {
		return "arquivo_controle_remessa_evento";
	}
	
	@Override
	protected ControleRemessaEventoForm getForm() {
		return new ControleRemessaEventoForm();
	}
	
	@Override
	protected Validator getValidator() {
		return this.validator;
	}
	
	@ModelAttribute("produtos")
	public List<SccProdutoCobilling> populaProdutos() throws Exception {
		return super.populaProdutos(false);
	}
	
	@ModelAttribute("tiposArquivo")
	public List<BasicIntegerItem> populaTiposArquivo() throws Exception {
		List<BasicIntegerItem> comboList = new ArrayList<BasicIntegerItem>();
		comboList.add(new BasicIntegerItem(BasicDAO.GET_ALL, "Todos"));
        comboList.add(new BasicIntegerItem( 5L, "Tipo de Arquivo Remessa CLT (5)"));
        comboList.add(new BasicIntegerItem(10L, "Tipo de Arquivo Remessa PRE (10)"));
        comboList.add(new BasicIntegerItem(15L, "Tipo de Arquivo Remessa ESB (15)"));
        comboList.add(new BasicIntegerItem(20L, "Tipo de Arquivo Remessa MOB (20)"));        
        comboList.add(new BasicIntegerItem(25L, "Tipo de Arquivo Rejeitado (25)"));
        comboList.add(new BasicIntegerItem(30L, "Tipo de Arquivo Faturado (30)"));
        comboList.add(new BasicIntegerItem(35L, "Tipo de Arquivo Contestado Diário (35)"));
        comboList.add(new BasicIntegerItem(40L, "Tipo de Arquivo Contestado Mensal (40)"));
        comboList.add(new BasicIntegerItem(45L, "Tipo de Arquivo Alocado (45)"));
        comboList.add(new BasicIntegerItem(50L, "Tipo de Arquivo A Reciclar (50)"));
        comboList.add(new BasicIntegerItem(55L, "Tipo de Arquivo Remessa FAKE (55)"));
        comboList.add(new BasicIntegerItem(70L, "Tipo de Arquivo Perda (70)"));
        comboList.add(new BasicIntegerItem(75L, "Tipo de Arquivo Fiscal ESB (75)"));
        comboList.add(new BasicIntegerItem(80L, "Tipo de Arquivo Fiscal MOB (80)"));
        comboList.add(new BasicIntegerItem(85L, "Tipo de Arquivo Exclusão (85)"));
        comboList.add(new BasicIntegerItem(90L, "Tipo de Arquivo A Recuperar (90)"));
        comboList.add(new BasicIntegerItem(95L, "Tipo de Arquivo Não Identificado (95)"));
		return comboList;
	}
	
	@ModelAttribute("tiposOperadora")
	public List<BasicStringItem> populaTiposOperadoras() throws Exception {
		List<BasicStringItem> comboList = new ArrayList<BasicStringItem>();
		comboList.add(new BasicStringItem("O", "Operadora"));
		comboList.add(new BasicStringItem("H", "Holding"));
		return comboList;
	}
	
	@ModelAttribute("eventos")
	public List<SccStatusCdr> populaEventos() throws Exception {
		List<SccStatusCdr> comboList = new ArrayList<SccStatusCdr>();
		comboList.addAll(getServiceManager().getPesquisaDominiosService().getAllStatus());
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
	
	@ModelAttribute("tiposPeriodo")
	public List<BasicStringItem> populaTiposPeriodo() throws Exception {
		List<BasicStringItem> comboList = new ArrayList<BasicStringItem>();
		comboList.add(new BasicStringItem(DT_REF, "Data de Referência"));
		comboList.add(new BasicStringItem(DT_CLARO, "Data de Processamento Claro"));
		return comboList;
	}
	
	public ModelAndView excel(HttpServletRequest request,HttpServletResponse response,@Valid @ModelAttribute(FORM_NAME) BaseForm _form,BindingResult bindingResult, Model model) throws Exception {
		return new ModelAndView("arquivo_controle_remessa_evento_excel");
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
	public List<SccOperadora> populaOperadoras() throws Exception {
		return super.populaOperadorasClaro(false);
	}
	*/
	
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
	/*
	@ModelAttribute("operadorasExternas")
	public List<SccOperadora> populaOperadorasExternas() throws Exception {
		return super.populaOperadorasExternas(false);
	}
	*/
	
}
