package com.claro.sccweb.controller.relatorio.pre;

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
import org.springframework.web.servlet.ModelAndView;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccOperadora;
import com.claro.cobillingweb.persistence.view.SccConfirmacaoRepasseView;
import com.claro.sccweb.controller.BaseOperationController;
import com.claro.sccweb.controller.util.BasicIntegerItem;
import com.claro.sccweb.controller.util.BasicStringItem;
import com.claro.sccweb.controller.validator.RelatorioConfirmacaoRepasseValidator;
import com.claro.sccweb.decorator.rownum.entity.SccConfirmacaoRepasseViewDecorator;
import com.claro.sccweb.form.BaseForm;
import com.claro.sccweb.form.RelatorioConfirmacaoRepasseForm;
import com.claro.sccweb.service.SccConfirmacaoRepasseService;
import com.claro.sccweb.service.ServiceException;

@Controller
@RequestMapping(value="/user/relatorio/confirmacao/repasse")
public class SccRelatorioConfirmacaoRepasseController extends
		BaseOperationController<RelatorioConfirmacaoRepasseForm> {
	
	@Autowired
	private SccConfirmacaoRepasseService sccConfirmacaoRepasseService;
	
	private final RelatorioConfirmacaoRepasseValidator validator = new RelatorioConfirmacaoRepasseValidator();
	
	public ModelAndView pesquisar(HttpServletRequest request, HttpServletResponse response, BaseForm form, BindingResult bindingResult, Model model) throws Exception {
		
		RelatorioConfirmacaoRepasseForm formConfirmacaoRepasse = (RelatorioConfirmacaoRepasseForm)form;
		List<SccConfirmacaoRepasseView> rows = gerarRelatorioConfirmacaoRepasse(formConfirmacaoRepasse);
		
		List<SccConfirmacaoRepasseViewDecorator> decoratorList = new ArrayList<SccConfirmacaoRepasseViewDecorator>(rows.size());
		
		for (int i = 0; i < rows.size(); i++) {
			
			SccConfirmacaoRepasseViewDecorator decorator = new SccConfirmacaoRepasseViewDecorator(rows.get(i), i);
			decoratorList.add(decorator);
		}
		storeInSession(getClass(), DISPLAY_TAG_SPACE_1, decoratorList, request);
		ModelAndView mav = new ModelAndView(getViewName());
		return mav;

	}
	
	private List<SccConfirmacaoRepasseView> gerarRelatorioConfirmacaoRepasse(RelatorioConfirmacaoRepasseForm form) throws DAOException, ServiceException {
		return sccConfirmacaoRepasseService.gerarRelatorioConfirmacaoRepasse(form.getMesRepasse(), form.getAnoRepasse(), form.getCdEOTLD(), form.getCdStatusRepasse());
	}


	@Override
	protected String getViewName() {
		
		return "relatorio_confirmacao_repasse_filtro";
	}

	@Override
	protected RelatorioConfirmacaoRepasseForm getForm() {
		
		return new RelatorioConfirmacaoRepasseForm();
	}

	@Override
	protected Validator getValidator() {
		
		return this.validator;
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

	@ModelAttribute("statusRepasse")
	public List<BasicStringItem> populaStatusRepasse()
	{
		 List<BasicStringItem> comboList = new ArrayList<BasicStringItem>();
		 comboList.add(new BasicStringItem("C", "Confirmado"));
		 comboList.add(new BasicStringItem("N", "Cancelado"));
		 comboList.add(new BasicStringItem("", "Não Confirmado"));
		 return comboList;
	}
	
	@ModelAttribute("meses")
	public List<BasicIntegerItem> populaMeses() 
	{
		return _populaComboMeses();
	}
	
	
	public SccConfirmacaoRepasseService getSccConfirmacaoRepasseService() {
		return sccConfirmacaoRepasseService;
	}

	public void setSccBatimentoArquivosService(
			SccConfirmacaoRepasseService sccConfirmacaoRepasseService) {
		this.sccConfirmacaoRepasseService = sccConfirmacaoRepasseService;
	}

}
