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
import org.springframework.web.servlet.ModelAndView;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.entity.SccArquivoCobilling;
import com.claro.cobillingweb.persistence.entity.SccOperadora;
import com.claro.sccweb.controller.BaseOperationController;
import com.claro.sccweb.controller.util.BasicIntegerItem;
import com.claro.sccweb.controller.util.BasicStringItem;
import com.claro.sccweb.controller.validator.ArquivoRetornoValidator;
import com.claro.sccweb.decorator.rownum.entity.SccArquivoCobillingDecorator;
import com.claro.sccweb.form.ArquivoRetornoForm;
import com.claro.sccweb.form.BaseForm;

@Controller
@RequestMapping(value="/user/arquivo/retorno")
public class ArquivoRetornoController extends BaseOperationController<ArquivoRetornoForm>{

	private ArquivoRetornoValidator validator = new ArquivoRetornoValidator();
	
	
	public ModelAndView pesquisar(HttpServletRequest request,HttpServletResponse response,@Valid @ModelAttribute(FORM_NAME) BaseForm _form,BindingResult bindingResult, Model model) throws Exception
	{
		ModelAndView mav = new ModelAndView(getViewName());
		ArquivoRetornoForm form = (ArquivoRetornoForm)_form;
		List<SccArquivoCobilling> rows = getServiceManager().getArquivosService().pesquisaArquivosRetorno(form.getCdEOTClaro(), form.getCdEOTLD(), form.getCdTipoArquivo(), form.getStatusArquivo(), form.getSistemaDestino(), form.getTipoPeriodo(), form.getDataInicial(), form.getDataFinal(), form.getTipoOperadora().equals("H"));
		List<SccArquivoCobillingDecorator> decoratorList = new ArrayList<SccArquivoCobillingDecorator>(rows.size());
		for (int i=0;i<rows.size();i++)
			{
			SccArquivoCobillingDecorator decorator = new SccArquivoCobillingDecorator(rows.get(i), i);
			decoratorList.add(decorator);
			}
		storeInSession(getClass(), DISPLAY_TAG_SPACE_1, decoratorList, request);
		cacheMyForm(getClass(), form);
		return mav;
	}
	
	public ModelAndView excel(HttpServletRequest request,HttpServletResponse response,@Valid @ModelAttribute(FORM_NAME) BaseForm _form,BindingResult bindingResult, Model model) throws Exception
	{
		return new ModelAndView("arquivo_retorno_excel");
	}
	
	 
	protected String getViewName() {
		return "arquivo_retorno";
	}

	 
	protected ArquivoRetornoForm getForm() {
		return new ArquivoRetornoForm();
	}

	 
	protected Validator getValidator() {
		return this.validator;
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
	public List<SccOperadora> populaOperadoras() throws Exception
	{
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
	public List<SccOperadora> populaOperadorasExternas() throws Exception
	{
		return super.populaOperadorasExternas(false);
	}
	*/
	
	@ModelAttribute("sistemas")
	public List<BasicStringItem> populaSistemas()
	{
		List<BasicStringItem> comboList = new ArrayList<BasicStringItem>();
		comboList.add(new BasicStringItem(BasicDAO.GET_ALL_STRING,"Todos"));
		comboList.add(new BasicStringItem(".S02.", "Mobile"));
		comboList.add(new BasicStringItem(".S01.", "Ensemble"));
		return comboList;
	}
	
	
	@ModelAttribute("tipoPeriodo")
	public List<BasicStringItem> populaTipoPeriodo()
	{
		List<BasicStringItem> comboList = new ArrayList<BasicStringItem>();
		comboList.add(new BasicStringItem("R", "Data Referência"));
		comboList.add(new BasicStringItem("P", "Data Processamento Claro"));
		comboList.add(new BasicStringItem("T", "Data Início Trafego"));
		comboList.add(new BasicStringItem("F", "Data Fim Trafego"));
		comboList.add(new BasicStringItem("S", "Data do Status Arquivo"));
		return comboList;
		
	}
	
	@ModelAttribute("tiposOperadora")
	public List<BasicStringItem> populaTiposOperadoras() throws Exception
	{
		List<BasicStringItem> comboList = new ArrayList<BasicStringItem>();
		comboList.add(new BasicStringItem("O", "Operadora"));
		comboList.add(new BasicStringItem("H", "Holding"));
		return comboList;
	}
	
	@ModelAttribute("statusArquivo")
	public List<BasicStringItem> populaStatusArquivo()
	{
		List<BasicStringItem> comboList = new ArrayList<BasicStringItem>();
		comboList.add(new BasicStringItem(BasicDAO.GET_ALL_STRING, "Todos"));
		comboList.add(new BasicStringItem("OK", "Pronto"));
		comboList.add(new BasicStringItem("LO", "Carregando"));
		comboList.add(new BasicStringItem("WK", "Em Processamento"));
		comboList.add(new BasicStringItem("NO", "Sem Descricao"));
		return comboList;
	}
	
	@ModelAttribute("tiposArquivo")
	public List<BasicIntegerItem> populaTiposArquivo()	
	{
		List<BasicIntegerItem> comboList = new ArrayList<BasicIntegerItem>();
		comboList.add(new BasicIntegerItem(25L, "Arquivo de Rejeitado (25)"));
        comboList.add(new BasicIntegerItem(26L, "Arquivo de Retarifacao (26)"));
        comboList.add(new BasicIntegerItem(30L, "Arquivo de Faturado (30)"));
        comboList.add(new BasicIntegerItem(31L, "Arquivo de Faturado TZLD (31)"));
        comboList.add(new BasicIntegerItem(35L, "Arquivo de Contestado Diário (35)"));
        comboList.add(new BasicIntegerItem(40L, "Arquivo de Contestado Mensal (40)"));
        comboList.add(new BasicIntegerItem(41L, "Arquivo de Contestado Mensal DK (41)"));
        comboList.add(new BasicIntegerItem(45L, "Arquivo de Alocado (45)"));
        comboList.add(new BasicIntegerItem(50L, "Arquivo de A Reciclar (50)"));
        comboList.add(new BasicIntegerItem(55L, "Arquivo de Remessa FAKE (55)"));
        comboList.add(new BasicIntegerItem(70L, "Arquivo de Perda (70)"));
        comboList.add(new BasicIntegerItem(75L, "Arquivo de Fiscal ESB (75)"));
        comboList.add(new BasicIntegerItem(80L, "Arquivo de Fiscal MOB (80)"));
        comboList.add(new BasicIntegerItem(85L, "Arquivo de Exclusão (85)"));
        comboList.add(new BasicIntegerItem(90L, "Arquivo de A Recuperar (90)"));
        comboList.add(new BasicIntegerItem(95L, "Arquivo de Não Identificado (95)"));
        comboList.add(new BasicIntegerItem(105L, "Arquivo de Questionamento Remessa (105)"));
        comboList.add(new BasicIntegerItem(110L, "Arquivo de Questionamento Retorno (110)"));
        comboList.add(new BasicIntegerItem(140L, "Arquivo Arrecadado (140)"));
        comboList.add(new BasicIntegerItem(141L, "Arquivo Arrecadado TZLD (141)"));
        comboList.add(new BasicIntegerItem(150L, "Arquivo de Expurgo (150)"));
        comboList.add(new BasicIntegerItem(151L, "Arquivo de Expurgo TZLD (151)"));
        comboList.add(new BasicIntegerItem(160L, "Arquivo de Reversão de Pagamento (160)"));
        comboList.add(new BasicIntegerItem(161L, "Arquivo de Reversão de Pagamento TZLD (161)"));
        comboList.add(new BasicIntegerItem(180L, "Arquivo de Parcelamento TCOR (180)"));
        comboList.add(new BasicIntegerItem(182L, "Arquivo de Parcelamento TCOP (182)"));
        comboList.add(new BasicIntegerItem(185L, "Arquivo de Juros e Multa (185)"));
        comboList.add(new BasicIntegerItem(190L, "Arquivo de Repasse (190)"));
		return comboList;
	}
	
	
}
