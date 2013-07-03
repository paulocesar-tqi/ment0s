package com.claro.sccweb.controller.financeiro;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
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

import com.claro.cobillingweb.persistence.constants.StatusCdrEnum;
import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccArquivoSumarizado;
import com.claro.cobillingweb.persistence.entity.SccOperadora;
import com.claro.cobillingweb.persistence.filtro.SccFiltroRelPerdaFaturamento;
import com.claro.sccweb.controller.BaseOperationController;
import com.claro.sccweb.controller.util.BasicIntegerItem;
import com.claro.sccweb.controller.util.BasicStringItem;
import com.claro.sccweb.decorator.view.SccFinanceiroPerdaFaturamentoDecorator;
import com.claro.sccweb.form.BaseForm;
import com.claro.sccweb.form.SccPerdaFaturamentoForm;
import com.claro.sccweb.service.ControleRemessaService;
import com.claro.sccweb.vo.PerdaFaturamentoVO;

@Controller
@RequestMapping(value="/user/financeiro/perda/faturamento")
public class SccFinanceiroPerdaFaturamentoController extends BaseOperationController<SccPerdaFaturamentoForm> {
	
	@Autowired
	private ControleRemessaService controleRemessaService;
	
	
	public ModelAndView pesquisar(HttpServletRequest request, HttpServletResponse response, BaseForm _form, BindingResult bindingResult, Model model) throws Exception {
		
		SccPerdaFaturamentoForm form = (SccPerdaFaturamentoForm)_form;
		SccFiltroRelPerdaFaturamento filtro = getFiltro(form);
		
		List<PerdaFaturamentoVO> rows = (List<PerdaFaturamentoVO>) gerarRelatorioPerdaFaturamento(filtro);
		
		List<SccFinanceiroPerdaFaturamentoDecorator> decoratorList = new ArrayList<SccFinanceiroPerdaFaturamentoDecorator>(rows.size());
		
		for (int i = 0; i < rows.size(); i++) {
			
			SccFinanceiroPerdaFaturamentoDecorator decorator = new SccFinanceiroPerdaFaturamentoDecorator(rows.get(i), i);
			decoratorList.add(decorator);
		}
		
		storeInSession(getClass(), DISPLAY_TAG_SPACE_1, decoratorList, request);
		ModelAndView mav = new ModelAndView(getViewName());
		return mav;
	}
	
	private SccFiltroRelPerdaFaturamento getFiltro(SccPerdaFaturamentoForm form) {
		SccFiltroRelPerdaFaturamento filtro = new SccFiltroRelPerdaFaturamento();
		
		filtro.setOperadoraClaro(form.getCdEOTClaro());
		filtro.setOperadoraExterna(form.getCdEOTLD());
		filtro.setHolding(form.isHolding());
		filtro.setDataInicialPeriodo(form.getDataInicial());
		filtro.setDataFinalPeriodo(form.getDataFinal());
		filtro.setFileType(form.getTipoEvento().toString());
		
		return filtro;
	}
	
	
	
	private Collection<PerdaFaturamentoVO> gerarRelatorioPerdaFaturamento(SccFiltroRelPerdaFaturamento filtro) throws DAOException{
		
		return this.controleRemessaService.gerarRelatorioPerdaFaturamento(filtro);
	}


	@Override
	protected String getViewName() {
		
		return "relatorio_perda_faturamento";
	}

	@Override
	protected SccPerdaFaturamentoForm getForm() {
		
		return new SccPerdaFaturamentoForm();
	}

	@Override
	protected Validator getValidator() {

		return null;
	}
	
	public ModelAndView excel(HttpServletRequest request,HttpServletResponse response,@Valid @ModelAttribute(FORM_NAME) BaseForm _form,BindingResult bindingResult, Model model) throws Exception {
		return new ModelAndView("relatorio_perda_faturamento_excel");
	}
	
	@ModelAttribute("tiposOperadora")
	public List<BasicStringItem> populaTiposOperadoras() throws Exception {
		List<BasicStringItem> comboList = new ArrayList<BasicStringItem>();
		comboList.add(new BasicStringItem("O", "Operadora"));
		comboList.add(new BasicStringItem("H", "Holding"));
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
	

	
	@ModelAttribute("eventos")
	public List<BasicIntegerItem> popularComboEvento(){
		List<BasicIntegerItem> comboList = new ArrayList<BasicIntegerItem>();
		comboList.add(new BasicIntegerItem(BasicDAO.GET_ALL, "Todos"));
		
		for(StatusCdrEnum status :StatusCdrEnum.findAll()){
			comboList.add(new BasicIntegerItem((long) status.getValor(), status.getDescricao()));
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



	public ControleRemessaService getControleRemessaService() {
		return controleRemessaService;
	}


	public void setControleRemessaService(ControleRemessaService controleRemessaService) {
		this.controleRemessaService = controleRemessaService;
	}
}
