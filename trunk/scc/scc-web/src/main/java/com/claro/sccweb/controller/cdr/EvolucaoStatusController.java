package com.claro.sccweb.controller.cdr;

import java.util.ArrayList;
import java.util.Date;
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

import com.claro.cobillingweb.persistence.constants.StatusCDRConstants;
import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.entity.SccOperadora;
import com.claro.sccweb.controller.BaseOperationController;
import com.claro.sccweb.controller.util.BasicIntegerItem;
import com.claro.sccweb.controller.util.BasicStringItem;
import com.claro.sccweb.controller.validator.EvolucaoStatusValidator;
import com.claro.sccweb.form.BaseForm;
import com.claro.sccweb.form.EvolucaoStatusForm;
import com.claro.sccweb.service.data.GrupoStatusConstants;
import com.claro.sccweb.service.data.PeriodoCDR;

@Controller
@RequestMapping(value="/user/cdr/evolucao")
public class EvolucaoStatusController extends BaseOperationController<EvolucaoStatusForm> {

	private EvolucaoStatusValidator validator = new EvolucaoStatusValidator();
	
	 public ModelAndView gerar(HttpServletRequest request, HttpServletResponse response,@Valid @ModelAttribute(FORM_NAME)  BaseForm _form,BindingResult bindingResult,Model model) throws Exception
	 {
		 ModelAndView mav = new ModelAndView(getViewName());
		 EvolucaoStatusForm form = (EvolucaoStatusForm)_form;
		 Date dataInicial = calculaDataInicialPeriodo(form.getMesInicial(), form.getAnoInicial());
		 Date dataFinal = calculaDataFinalPeriodo(form.getMesFinal(), form.getAnoFinal());
		 List<PeriodoCDR> periodos = getServiceManager().getEvolucaoCDRService().geraEvolucaoCDRs(form.getCdEOTClaro(), form.getCdEOTLD(), form.getCdProdutoCobilling(), dataInicial, dataFinal, form.getTipoOperadora().equals("H"));
		 storeInSession(getClass(), DISPLAY_TAG_SPACE_1, periodos, request);
		 mav.addObject(FORM_NAME, form);
		 return mav;
	 }
	
	 	@RequestMapping(value="/grafico/rejeitado/qt") 
		public  ModelAndView  graficoRejeitado(HttpServletRequest request, HttpServletResponse response) throws Exception
		 {	 		
	 		return new ModelAndView("evolucao_status_rejeitado_qt");
		 }
	 	
	 	@RequestMapping(value="/grafico/faturado/qt") 
		public  ModelAndView  graficoFaturado(HttpServletRequest request, HttpServletResponse response) throws Exception
		 {	 		
	 		return new ModelAndView("evolucao_status_faturado_qt");
		 }
	 
	 	@RequestMapping(value="/grafico/aceitos/qt") 
		public  ModelAndView  graficoAceitos(HttpServletRequest request, HttpServletResponse response) throws Exception
		 {	 		
	 		return new ModelAndView("evolucao_aceitos_qt");
		 }
	 	
	 	@RequestMapping(value="/grafico/encaminhado_reciclar/qt") 
		public  ModelAndView  graficoEncaminhadoReciclar(HttpServletRequest request, HttpServletResponse response) throws Exception
		 {	 		
	 		return new ModelAndView("evolucao_encaminhado_reciclar_qt");
		 }
	 	
	 	
	 
	protected String getViewName() {
		return "evolucao_status_filtro";
	}

	 
	protected EvolucaoStatusForm getForm() {	
		return new EvolucaoStatusForm();
	}

	 
	protected Validator getValidator() {
		return this.validator;
	}

	@ModelAttribute("meses")
	public List<BasicIntegerItem> populaMeses() 
	{
		return _populaComboMeses();
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
	
	@ModelAttribute("gruposStatus")
	public List<BasicIntegerItem> populaCompoStatus() throws Exception
	{
		 List<BasicIntegerItem> comboList = new ArrayList<BasicIntegerItem>();
		 comboList.add(new BasicIntegerItem(GrupoStatusConstants.GRUPO_CDR_ENCAMINHADO,"Encaminhado"));
		 comboList.add(new BasicIntegerItem(GrupoStatusConstants.GRUPO_CDR_REJEITADO,"Rejeitado"));
		 comboList.add(new BasicIntegerItem(GrupoStatusConstants.GRUPO_CDR_EXCLUIDO,"Excluído"));
		 comboList.add(new BasicIntegerItem(GrupoStatusConstants.GRUPO_CDR_PERDIDO,"Perdido"));
		 comboList.add(new BasicIntegerItem(GrupoStatusConstants.GRUPO_CDR_CONTESTADO,"Contestado"));
		 comboList.add(new BasicIntegerItem(GrupoStatusConstants.GRUPO_RETARIFADO,"Retarifado"));
		 comboList.add(new BasicIntegerItem(GrupoStatusConstants.GRUPO_CDR_FATURADO,"Faturado"));
		 comboList.add(new BasicIntegerItem(GrupoStatusConstants.GRUPO_CDR_ALOCADO,"Alocado"));
		 comboList.add(new BasicIntegerItem(GrupoStatusConstants.GRUPO_CDR_A_RECICLAR,"A Reciclar"));
		 comboList.add(new BasicIntegerItem(GrupoStatusConstants.GRUPO_CDR_A_RECUPERAR,"A Recuperar"));
		 comboList.add(new BasicIntegerItem(GrupoStatusConstants.GRUPO_EM_PARCELAMENTO,"Em Parcelamento"));
		 comboList.add(new BasicIntegerItem(StatusCDRConstants.CDRSTATUS_CONTESTADO_MOB_ARRECADADA,"Contestado Arrecadado"));
		 comboList.add(new BasicIntegerItem(StatusCDRConstants.CDRSTATUS_CONTESTADO_MOB_REPASSADA,"Contestado Repassado"));
		 comboList.add(new BasicIntegerItem(StatusCDRConstants.CDRSTATUS_EXCLUIDO_MOB_CONTESTADO,"Excluído Contestado"));
		 comboList.add(new BasicIntegerItem(StatusCDRConstants.CDRSTATUS_ARRECADADA,"Arrecadado"));
		 comboList.add(new BasicIntegerItem(StatusCDRConstants.CDRSTATUS_INADIMPLENTE,"Indadimplente"));
		 comboList.add(new BasicIntegerItem(StatusCDRConstants.CDRSTATUS_REVERSAO_PGTO,"Reversão de Pagamento"));
		 comboList.add(new BasicIntegerItem(StatusCDRConstants.CDRSTATUS_REPASSADA,"Repassado"));
		 comboList.add(new BasicIntegerItem(StatusCDRConstants.CDRSTATUS_REVERSAO,"Reversão"));
		 comboList.add(new BasicIntegerItem(StatusCDRConstants.CDRSTATUS_PARCELADA,"Parcelado"));
		 comboList.add(new BasicIntegerItem(StatusCDRConstants.CDRSTATUS_ALTERACAO_VCTO,"Alteração de Vencimento"));
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
	
}
