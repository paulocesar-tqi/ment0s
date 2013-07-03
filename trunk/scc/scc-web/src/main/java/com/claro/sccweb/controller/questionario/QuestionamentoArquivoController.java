package com.claro.sccweb.controller.questionario;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.claro.cobillingweb.persistence.constants.CobillingConstants;
import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccCdrQuestionamento;
import com.claro.cobillingweb.persistence.entity.SccOperadora;
import com.claro.cobillingweb.persistence.view.SccQuestionamentoArquivoView;
import com.claro.cobillingweb.persistence.view.SccQuestionamentoView;
import com.claro.sccweb.controller.BaseOperationController;
import com.claro.sccweb.controller.util.BasicStringItem;
import com.claro.sccweb.controller.validator.QuestionamentoArquivoValidator;
import com.claro.sccweb.form.QuestionamentoArquivoForm;
import com.claro.sccweb.service.SccQuestionamentoService;
import com.claro.sccweb.service.ServiceException;

@Controller
@RequestMapping(value="/user/questionamento/arquivo")
public class QuestionamentoArquivoController extends BaseOperationController<QuestionamentoArquivoForm> {
	
	private static final String FWD_VIEW_QUESTIONAMENTO_ARQUIVO = "relatorio_questionamento_arquivo";
	
	private static final String FWD_VIEW_ANALISE_CHAMADA = "relatorio_questionamento_analise_chamada";
	
	@Autowired
	private SccQuestionamentoService sccQuestionamentoService;
	
	private final QuestionamentoArquivoValidator validator = new QuestionamentoArquivoValidator();
	
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
		super.initBinder(binder);
	}
	

	private List<SccCdrQuestionamento> montarListaCdrQuestionamento(QuestionamentoArquivoForm form){
		
		List<SccCdrQuestionamento> listCdrQuestionamento = new ArrayList<SccCdrQuestionamento>();
		
		int qtdeAnaliseChamadas = form.getSqRemessaQuestionamentos().length;
		SccCdrQuestionamento sccCdrQuestionamento = null;
		
		for(int i=0; i < qtdeAnaliseChamadas; i++){				
			sccCdrQuestionamento = new SccCdrQuestionamento();
			
			sccCdrQuestionamento.setSqCdrQuestionamento(form.getSqCdrQuestionamentos()[i]);
			sccCdrQuestionamento.setSqRemessaQuestionamento(form.getSqRemessaQuestionamentos()[i]);
			sccCdrQuestionamento.setInResultadoAnalise(form.getResultadoAnalises()[i]);
			sccCdrQuestionamento.setCdStatusAnalise("A");
			
			listCdrQuestionamento.add(sccCdrQuestionamento);			
		}

		return listCdrQuestionamento;
	}
	
	@RequestMapping(value="confirmarAnalise")
	public ModelAndView confirmarAnalise(QuestionamentoArquivoForm form) throws Exception {
		
		this.sccQuestionamentoService.updateAnaliseChamadas(montarListaCdrQuestionamento(form));
		
		Collection<SccCdrQuestionamento> colecao = this.sccQuestionamentoService.pesquisarAnaliseChamadas(form.getFiltro().getSqQuestionamento(), form.getFiltro().getSqRemessaQuestionamento());
		form.setListQuestionamentoAnalise(colecao);
		
		ModelAndView mav = new ModelAndView(FWD_VIEW_ANALISE_CHAMADA, "filtro", form);
		
		return mav;
		
	}
	
	@RequestMapping(value="listar")
	public ModelAndView pesquisar(QuestionamentoArquivoForm form) throws Exception {
		
		List<SccQuestionamentoArquivoView> rows = (List<SccQuestionamentoArquivoView>) this.sccQuestionamentoService.gerarRelatorioQuestionamentoArquivo(form.getFiltro());
		
		form.setListQuestionamentoArquivo(rows);
		
		ModelAndView mav = new ModelAndView(FWD_VIEW_QUESTIONAMENTO_ARQUIVO, "filtro", form);
		
		return mav;
		
	}
	
	@RequestMapping(value = "/analiseChamadas", method=RequestMethod.GET)
	public ModelAndView gerarListaChamadas(@RequestParam("sqQuestionamento") Long sqQuestionamento, @RequestParam("sqRemessaQuestionamento") Long sqRemessaQuestionamento, HttpServletRequest request, HttpServletResponse response, Model model, QuestionamentoArquivoForm form) throws ServiceException, DAOException{
		
		Collection<SccCdrQuestionamento> colecao = this.sccQuestionamentoService.pesquisarAnaliseChamadas(sqQuestionamento, sqRemessaQuestionamento);
		
		form.setListQuestionamentoAnalise(colecao);
		storeInSession(getClass(), DISPLAY_TAG_SPACE_1, form.getListQuestionamentoAnalise(), request);
		ModelAndView mav = new ModelAndView(FWD_VIEW_ANALISE_CHAMADA, "filtro", form);
		 
		return mav;

	}
	
	@Override
	protected String getViewName() {
		
		return "relatorio_questionamento_arquivo";
	}

	@Override
	protected QuestionamentoArquivoForm getForm() {
		
		return new QuestionamentoArquivoForm();
	}

	@Override
	protected Validator getValidator() {
		
		return this.validator;
	}
	
	@ModelAttribute("Questionamento")
	public Collection<SccQuestionamentoView> popularQuestionamento() throws Exception{
		
		List<SccQuestionamentoView> comboList = new ArrayList<SccQuestionamentoView>();
		
		SccQuestionamentoView todos = new SccQuestionamentoView();
		todos.setSqQuestionamento(BasicDAO.GET_ALL);
		todos.setDescricaoEotLD("Todos");
		comboList.add(todos);
		
		Collection<SccQuestionamentoView> colecao = this.sccQuestionamentoService.gerarComboQuestionamento();
		for (SccQuestionamentoView sccQuestionamentoView : colecao) {
			
			String value = "(Q) " + sccQuestionamentoView.getSqQuestionamento().toString() + "-" + sccQuestionamentoView.getDescricaoEotLD() +"(" + sccQuestionamentoView.getCdEotLD() + ")";
			sccQuestionamentoView.setDescricaoEotLD(value);

			comboList.add(sccQuestionamentoView);
		}
		
		return comboList;
	}
	
	
	@ModelAttribute("Arquivos")
	public Collection<SccQuestionamentoView> popularArquivo() throws Exception{
		List<SccQuestionamentoView> comboList = new ArrayList<SccQuestionamentoView>();
	
		SccQuestionamentoView todos = new SccQuestionamentoView();
		todos.setSqQuestionamento(BasicDAO.GET_ALL);
		todos.setDescricaoEotLD("Todos");
		comboList.add(todos);
		
		Collection<SccQuestionamentoView> colecao = this.sccQuestionamentoService.gerarComboArquivo();
		for (SccQuestionamentoView sccQuestionamentoView : colecao) {
			
			String value = "Arquivo : " + sccQuestionamentoView.getSqRemessaQuestionamento() + " - (Q): " + sccQuestionamentoView.getSqQuestionamento() + " - " + sccQuestionamentoView.getDescricaoEotLD() + " ( "+ sccQuestionamentoView.getCdEotLD() + ")";
			sccQuestionamentoView.setDescricaoEotLD(value);
			comboList.add(sccQuestionamentoView);
		}
		
		return comboList;
	}
	
	/*@ModelAttribute("status")
    public List<BasicStringItem> getCdStatusQuestionamentoList() {
		List<BasicStringItem> comboList = new ArrayList<BasicStringItem>();
        for (int i = 0; i < CobillingConstants.vPROCESSO_QUESTIONAMENTO.size(); i++) {
        	String[] tPROCESSO = (String[]) CobillingConstants.vPROCESSO_QUESTIONAMENTO.elementAt(i);
            comboList.add(new BasicStringItem( tPROCESSO[0], tPROCESSO[2]));
		}
		
        return comboList;
    }*/
	
	@ModelAttribute("status")
	public List<BasicStringItem> populaStatus() {
		List<BasicStringItem> comboList = new ArrayList<BasicStringItem>();
		comboList.add(new BasicStringItem("*","Todos"));
		comboList.add(new BasicStringItem("RGS","Registro LD"));
		comboList.add(new BasicStringItem("PRP","Preparação"));
		comboList.add(new BasicStringItem("ANL","Análise"));
		comboList.add(new BasicStringItem("CRR","Correção"));
		comboList.add(new BasicStringItem("APR","Apuração"));
		comboList.add(new BasicStringItem("CNF","Confirmação"));
		comboList.add(new BasicStringItem("RGC","Registro Claro"));
		comboList.add(new BasicStringItem("INC","Inclusão"));
		comboList.add(new BasicStringItem("RPS","Repasse"));
		
		return comboList;
	}
	
	
	public ModelAndView pesquisarChamadas(@PathVariable("sqQuestionamento") Long sqQuestionamento, @PathVariable("sqRemessaQuestionamento") Long sqRemessaQuestionamento, HttpServletRequest request, HttpServletResponse response,@ModelAttribute("form") QuestionamentoArquivoForm form) throws Exception{
		 
		SccQuestionamentoArquivoView entidade = (SccQuestionamentoArquivoView) form.getEntidadeSelecionada();
		Collection<SccCdrQuestionamento> colecao =  this.sccQuestionamentoService.pesquisarAnaliseChamadas(entidade.getSqQuestionamento(), entidade.getSqRemessaQuestionamento());
		
		form.setListQuestionamentoAnalise(colecao);
		storeInSession(getClass(), DISPLAY_TAG_SPACE_2, colecao, request);
		ModelAndView mav = new ModelAndView(getViewName());
		return mav;
		
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
	
	@RequestMapping(value="/tab2" , method = RequestMethod.GET)
	public ModelAndView tab2(HttpServletRequest request, HttpServletResponse response) throws Exception {
			ModelAndView mav = new ModelAndView(getViewName());
			Object form = getMyFormFromCache(getClass());
			if (form != null)
				mav.addObject(FORM_NAME, form);
			else
				mav.addObject(FORM_NAME, getForm());
	    	return mav;  
	}

	
	public void setSccQuestionamentoService(SccQuestionamentoService sccQuestionamentoService) {
		this.sccQuestionamentoService = sccQuestionamentoService;
	}


}
