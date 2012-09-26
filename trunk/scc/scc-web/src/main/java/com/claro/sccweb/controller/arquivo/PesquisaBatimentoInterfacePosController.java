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
import com.claro.cobillingweb.persistence.entity.SccCdrCobilling;
import com.claro.cobillingweb.persistence.entity.SccOperadora;
import com.claro.sccweb.controller.BaseOperationController;
import com.claro.sccweb.controller.util.BasicStringItem;
import com.claro.sccweb.controller.validator.PesquisaBatimentoInterfacePosValidator;
import com.claro.sccweb.decorator.rownum.entity.SccArquivoCobillingDecorator;
import com.claro.sccweb.decorator.rownum.entity.SccCdrCobillingDecorator;
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
		List<SccArquivoCobilling> rows = null;
		if (myForm.getTipoArquivo().equals("REM")) {
			rows = getServiceManager().getArquivosService().pesquisaArquivosRemessa(myForm.getCdEOTClaro(), myForm.getCdEOTLD(), myForm.getPeriodo(), myForm.getDataInicial(), myForm.getDataFinal(), myForm.getStatusArquivo(), myForm.getTipoOperadora().equals("H"));
		} else {
			rows = getServiceManager().getArquivosService().pesquisaArquivosRemessaFranquia(myForm.getCdEOTClaro(), myForm.getCdEOTLD(), myForm.getPeriodo(), myForm.getDataInicial(), myForm.getDataFinal(), myForm.getStatusArquivo(), myForm.getTipoOperadora().equals("H"));
		}
		debug("Pesquisa de batimento interface pós-pago retornou "+rows.size()+" linhas com filtro "+myForm.toString());
		List<SccArquivoCobillingDecorator> decoratorList = new ArrayList<SccArquivoCobillingDecorator>(rows.size());	
		for (int i=0;i<rows.size();i++) {
			SccArquivoCobilling arquivoProtocolo = getServiceManager().getArquivosService().pesquisaArquivoProtocolo(rows.get(i));
			SccArquivoCobillingDecorator decorator = new SccArquivoCobillingDecorator(rows.get(i), arquivoProtocolo, i);
			decoratorList.add(decorator); 
		}
		debug("Resultado de pequisa de arquivos processados pós-pago armazenada em DISPLAY_TAG_SPACE_1");
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
		comboList.add(new BasicStringItem("REM", "Remessa"));
		comboList.add(new BasicStringItem("FRANQ", "Remessa Franquia"));
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
