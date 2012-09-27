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
import com.claro.cobillingweb.persistence.entity.SccOperadora;
import com.claro.cobillingweb.persistence.view.SccBatimentoInterfaceView;
import com.claro.sccweb.controller.BaseOperationController;
import com.claro.sccweb.controller.util.BasicStringItem;
import com.claro.sccweb.controller.validator.PesquisaBatimentoInterfacePosValidator;
import com.claro.sccweb.decorator.rownum.entity.SccBatimentoInterfaceViewDecorator;
import com.claro.sccweb.form.BaseForm;
import com.claro.sccweb.form.PesquisaBatimentoInterfacePosForm;

@Controller
@RequestMapping(value="/user/arquivo/batimento/interface")
public class PesquisaBatimentoInterfacePosController extends BaseOperationController<PesquisaBatimentoInterfacePosForm>{

	private final PesquisaBatimentoInterfacePosValidator validator = new PesquisaBatimentoInterfacePosValidator();
	
	public ModelAndView pesquisar(HttpServletRequest request, HttpServletResponse response,@Valid @ModelAttribute(FORM_NAME)  BaseForm form,BindingResult bindingResult,Model model) throws Exception {
		cleanDisplayTag(request);
		ModelAndView mav = new ModelAndView(getViewName());
		PesquisaBatimentoInterfacePosForm myForm = (PesquisaBatimentoInterfacePosForm)form;
		info("Pesquisando batimento interface pós-pago:  "+myForm.toString());
		
		List<SccBatimentoInterfaceView> rows = null;
		rows = getServiceManager().getSccBatimentoInterfaceService().listarBatimentoInterface(myForm.getDataInicial(), myForm.getDataFinal(), myForm.getCdEOTLD(), myForm.getCdEOTClaro(), myForm.getTipoArquivo());
		
		debug("Pesquisa de batimento interface pós-pago retornou "+rows.size()+" linhas com filtro "+myForm.toString());
		List<SccBatimentoInterfaceViewDecorator> decoratorList = new ArrayList<SccBatimentoInterfaceViewDecorator>(rows.size());	
		for (int i=0;i<rows.size();i++) {
			SccBatimentoInterfaceViewDecorator decorator = new SccBatimentoInterfaceViewDecorator(rows.get(i), i);
			decoratorList.add(decorator); 
		}
		debug("Resultado de pequisa batimento interface pós-pago armazenada em DISPLAY_TAG_SPACE_1");
		storeInSession(getClass(), DISPLAY_TAG_SPACE_1, decoratorList, request);
		mav.addObject(FORM_NAME, form);
		cacheMyForm(getClass(), form);
		return mav;	
	}
	
	
	public ModelAndView excel(HttpServletRequest request, HttpServletResponse response,@Valid @ModelAttribute(FORM_NAME)  BaseForm form,BindingResult bindingResult,Model model) throws Exception {
		info("Iniciando geração de Excel para pesquisa de arquivos processados pós-pago");
		ModelAndView mav = new ModelAndView("pesquisa_arquivos_processados_excel");
		return mav;
	}
	
	protected String getViewName() {
		return "pesquisa_arquivos_batimento_interface_pos";
	}	
	
	protected PesquisaBatimentoInterfacePosForm getForm() {
		return new PesquisaBatimentoInterfacePosForm();
	}
	
	protected Validator getValidator() {		
		return this.validator;
	}
	
	@ModelAttribute("tiposOperadora")
	public List<BasicStringItem> populaTiposOperadoras() throws Exception {
		List<BasicStringItem> comboList = new ArrayList<BasicStringItem>();
		comboList.add(new BasicStringItem("OP", "Operadora"));
		comboList.add(new BasicStringItem("HO", "Holding"));
		return comboList;
	}
	
	@ModelAttribute("tiposArquivo")
	public List<BasicStringItem> populaTiposArquivo() throws Exception {
		List<BasicStringItem> comboList = new ArrayList<BasicStringItem>();
		comboList.add(new BasicStringItem(BasicDAO.GET_ALL_STRING, "Todos"));

		comboList.add(new BasicStringItem("scc.INVOICES%.output", "Arquivo de Faturas LD"));
		comboList.add(new BasicStringItem("scc.LATEPYM%.output", "Arquivo de Juros e Multas LD"));
		comboList.add(new BasicStringItem("scc.ADJUSTMENT%.output", "Arquivo de Ajustes e Reversões"));
		comboList.add(new BasicStringItem("scc.EVENTS%.output", "Arquivo de Chamadas Faturadas"));
		comboList.add(new BasicStringItem("scc.FISCAL%.output", "Arquivo Fiscal SCC"));
		comboList.add(new BasicStringItem("scc.ESTORNO%.output", "Arquivo de Estorno de Débito SCC"));
		comboList.add(new BasicStringItem("scc.COBRANCA%.output", "Arquivo de Cobrança LD"));
		comboList.add(new BasicStringItem("scc.PAYMENT-ARRANGEMENT%.output", "Arquivo de Acordo de Pagamentos"));
		comboList.add(new BasicStringItem("scc.PA-FOLLOWUP%.output", "Arquivo de Acompanhamento de PA"));
		comboList.add(new BasicStringItem("scc.INVCHGDT%.output", "Arquivo de Alteração de Data de Vencimento"));
		comboList.add(new BasicStringItem("TCOU.%.IN", "Extração de Informações CM - Input"));
		comboList.add(new BasicStringItem("scc.CMDATA%.extract", "Extração de Informações CM - Output"));	
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

}
