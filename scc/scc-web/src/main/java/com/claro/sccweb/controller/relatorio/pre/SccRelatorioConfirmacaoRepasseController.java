package com.claro.sccweb.controller.relatorio.pre;

import java.util.ArrayList;
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
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccOperadora;
import com.claro.cobillingweb.persistence.view.SccConfirmacaoRepasseView;
import com.claro.sccweb.controller.BaseOperationController;
import com.claro.sccweb.controller.util.BasicIntegerItem;
import com.claro.sccweb.controller.util.BasicStringItem;
import com.claro.sccweb.controller.validator.RelatorioConfirmacaoRepasseValidator;
import com.claro.sccweb.form.RelatorioConfirmacaoRepasseForm;
import com.claro.sccweb.service.SccConfirmacaoRepasseService;
import com.claro.sccweb.service.ServiceException;

@Controller
@RequestMapping(value="/user/relatorio/confirmacao/repasse")
public class SccRelatorioConfirmacaoRepasseController extends
		BaseOperationController<RelatorioConfirmacaoRepasseForm> {
	
	@Autowired
	private SccConfirmacaoRepasseService sccConfirmacaoRepasseService;
	
	public static final String FWD_EXCEL_CONFIRMACAO_REPASSE = "relatorio_confirmacao_repasse_filtro_excel";
	private static final String FWD_VIEW_CONFIRMACAO_REPASSE = "relatorio_confirmacao_repasse_filtro";
	private static final String OPERACAO_EXCEL =	"excel";
	private final RelatorioConfirmacaoRepasseValidator validator = new RelatorioConfirmacaoRepasseValidator();
	
	
	@RequestMapping(value="listar", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView pesquisarByFiltro(HttpServletRequest request, HttpServletResponse response, RelatorioConfirmacaoRepasseForm form) throws Exception {
		
		ModelAndView mav = null;
		
		form.setLstConfirmacaoRepasse(gerarRelatorioConfirmacaoRepasse(form));
		
		if(form.getOperacao().equals(OPERACAO_EXCEL)){
			if(form.getLstConfirmacaoRepasse() != null && form.getLstConfirmacaoRepasse().size() > 0){
				mav = new ModelAndView(FWD_EXCEL_CONFIRMACAO_REPASSE, "filtro", form);
			}
			
		}else{
			mav = new ModelAndView(FWD_VIEW_CONFIRMACAO_REPASSE, "filtro", form);
		}
		
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
