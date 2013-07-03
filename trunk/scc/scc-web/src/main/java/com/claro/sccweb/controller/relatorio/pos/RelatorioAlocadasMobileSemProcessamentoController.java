package com.claro.sccweb.controller.relatorio.pos;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.view.SccAlocadasMobileSemProcessamentoView;
import com.claro.sccweb.controller.BaseOperationController;
import com.claro.sccweb.controller.validator.RelatorioAlocadasMobileSemProcessamentoValidator;
import com.claro.sccweb.form.RelatorioAlocadasMobileSemProcessamentoForm;
import com.claro.sccweb.service.SccAlocadasMobileSemProcessamentoService;
import com.claro.sccweb.service.ServiceException;

@Controller
@RequestMapping(value="/user/relatorio/alocadasMobileSemProcessamento")
public class RelatorioAlocadasMobileSemProcessamentoController extends
		BaseOperationController<RelatorioAlocadasMobileSemProcessamentoForm> {
	
	@Autowired
	private SccAlocadasMobileSemProcessamentoService sccAlocadasMobileSemProcessamentoService;
	
	public static final String FWD_EXCEL_ALOCADAS_MOBILE_SEM_PROCESSAMENTO = "relatorio_alocadas_mobile_sem_processamento_filtro_excel";
	private static final String FWD_VIEW_ALOCADAS_MOBILE_SEM_PROCESSAMENTO ="relatorio_alocadas_mobile_sem_processamento_filtro";
	private static final String OPERACAO_EXCEL =	"excel";
	
	private final RelatorioAlocadasMobileSemProcessamentoValidator validator = new RelatorioAlocadasMobileSemProcessamentoValidator();
	
	@RequestMapping(value="listar", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView pesquisarByFiltro(HttpServletRequest request, HttpServletResponse response, RelatorioAlocadasMobileSemProcessamentoForm form) throws Exception {
		
		ModelAndView mav = null;
		
		form.setLstSccAlocadasMobileSemProcessamento(gerarRelatorioAlocadasMobileSemProcessamento(form));
		
		if(form.getOperacao().equals(OPERACAO_EXCEL)){
			if(form.getLstSccAlocadasMobileSemProcessamento() != null && form.getLstSccAlocadasMobileSemProcessamento().size() > 0){
				mav = new ModelAndView(FWD_EXCEL_ALOCADAS_MOBILE_SEM_PROCESSAMENTO, "filtro", form);
			}
			
		}else{
			mav = new ModelAndView(FWD_VIEW_ALOCADAS_MOBILE_SEM_PROCESSAMENTO, "filtro", form);
		}
		
		return mav;
		
	}
	
	private List<SccAlocadasMobileSemProcessamentoView> gerarRelatorioAlocadasMobileSemProcessamento(RelatorioAlocadasMobileSemProcessamentoForm form) throws DAOException, ServiceException {
		return sccAlocadasMobileSemProcessamentoService.gerarRelatorioAlocadasMobileSemProcessamento(form.getDtInicial(), form.getDtFinal(), form.getNoArquivoGerado());
	}


	@Override
	protected String getViewName() {
		
		return "relatorio_alocadas_mobile_sem_processamento_filtro";
	}

	@Override
	protected RelatorioAlocadasMobileSemProcessamentoForm getForm() {
		
		return new RelatorioAlocadasMobileSemProcessamentoForm();
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

	@Override
	protected Validator getValidator() {
		
		return this.validator;
	}
	
	public SccAlocadasMobileSemProcessamentoService getSccAlocadasMobileSemProcessamentoService() {
		return sccAlocadasMobileSemProcessamentoService;
	}

	public void setSccAlocadasMobileSemProcessamentoService(
			SccAlocadasMobileSemProcessamentoService sccAlocadasMobileSemProcessamentoService) {
		this.sccAlocadasMobileSemProcessamentoService = sccAlocadasMobileSemProcessamentoService;
	}
}
