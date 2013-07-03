package com.claro.sccweb.controller.questionario;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.entity.SccOperadora;
import com.claro.cobillingweb.persistence.view.SccQuestionamentoView;
import com.claro.sccweb.controller.BaseOperationController;
import com.claro.sccweb.controller.util.BasicIntegerItem;
import com.claro.sccweb.controller.validator.SccRequisicaoValidator;
import com.claro.sccweb.form.SccRequisicaoForm;
import com.claro.sccweb.service.SccParamProcessoService;
import com.claro.sccweb.service.SccQuestionamentoService;

@Controller
@RequestMapping(value="/user/questionamento/requisicao")
public class SccRequisicaoController extends BaseOperationController<SccRequisicaoForm> {
	
	@Autowired
	private SccParamProcessoService sccParamProcessoService;

	@Autowired
	private SccQuestionamentoService sccQuestionamentoService;
	
	private static final String FWD_REL_REQUISICAO = "relatorio_questionamento_requisicao";
	
	private final SccRequisicaoValidator validator = new SccRequisicaoValidator();
	
	
	@RequestMapping(value="listar", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView pesquisar(HttpServletRequest request, HttpServletResponse response, SccRequisicaoForm form) throws Exception {
		
		form.setListLoaded(this.sccParamProcessoService.gerarRelatorios("Q_APURACAO", "LOADED"));
		form.setListToLoad(this.sccParamProcessoService.gerarRelatorios("Q_APURACAO", "TOLOAD"));
		form.setListLoading(this.sccParamProcessoService.gerarRelatorios("Q_APURACAO", "LOADING"));
		
		ModelAndView mav = new ModelAndView(FWD_REL_REQUISICAO, "filtro", form);
		return mav;
		
	}
	
	@ModelAttribute("operadorasExternas")
	public List<SccOperadora> populaOperadorasExternas() throws Exception {
		List<SccOperadora> comboList = new ArrayList<SccOperadora>();
		comboList.addAll(getServiceManager().getPesquisaDominiosService().pesquisaOperadorasExternas());
		return comboList;
	}
	
	@ModelAttribute("Questionamento")
	public Collection<SccQuestionamentoView> popularQuestionamento() throws Exception{
		
		List<SccQuestionamentoView> comboList = new ArrayList<SccQuestionamentoView>();
		
		Collection<SccQuestionamentoView> colecao = this.sccQuestionamentoService.gerarComboQuestionamento();
		for (SccQuestionamentoView sccQuestionamentoView : colecao) {
			
			String value = "(Q) " + sccQuestionamentoView.getSqQuestionamento().toString() + "-" + sccQuestionamentoView.getDescricaoEotLD() +"(" + sccQuestionamentoView.getCdEotLD() + ")";
			sccQuestionamentoView.setDescricaoEotLD(value);

			comboList.add(sccQuestionamentoView);
		}
		
		return comboList;
	}
	

	
	@ModelAttribute("meses")
	public List<BasicIntegerItem> populaComboMeses() {
		List<BasicIntegerItem> lista = new ArrayList<BasicIntegerItem>();
		lista.add(new BasicIntegerItem(BasicDAO.GET_ALL, "Todos"));
		lista.add(new BasicIntegerItem(1L,"Janeiro"));
		lista.add(new BasicIntegerItem(2L,"Fevereiro"));
		lista.add(new BasicIntegerItem(3L,"Março"));
		lista.add(new BasicIntegerItem(4L,"Abril"));
		lista.add(new BasicIntegerItem(5L,"Maio"));
		lista.add(new BasicIntegerItem(6L,"Junho"));
		lista.add(new BasicIntegerItem(7L,"Julho"));
		lista.add(new BasicIntegerItem(8L,"Agosto"));
		lista.add(new BasicIntegerItem(9L,"Setembro"));
		lista.add(new BasicIntegerItem(10L,"Outubro"));
		lista.add(new BasicIntegerItem(11L,"Novembro"));
		lista.add(new BasicIntegerItem(12L,"Dezembro"));		
		return lista;
	}
		
	
	
	@Override
	protected String getViewName() {
		
		return FWD_REL_REQUISICAO;
	}

	@Override
	protected SccRequisicaoForm getForm() {
		
		return new SccRequisicaoForm();
	}

	@Override
	protected Validator getValidator() {
		
		return this.validator;
	}

	public void setSccParamProcessoService(
			SccParamProcessoService sccParamProcessoService) {
		this.sccParamProcessoService = sccParamProcessoService;
	}

	public void setSccQuestionamentoService(
			SccQuestionamentoService sccQuestionamentoService) {
		this.sccQuestionamentoService = sccQuestionamentoService;
	}

	
	
}
