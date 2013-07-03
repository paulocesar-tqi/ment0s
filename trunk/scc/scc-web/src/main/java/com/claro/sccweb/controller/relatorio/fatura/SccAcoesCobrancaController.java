package com.claro.sccweb.controller.relatorio.fatura;

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
import com.claro.cobillingweb.persistence.filtro.SccFiltroAcoesCobranca;
import com.claro.cobillingweb.persistence.view.SccAcoesCobrancaView;
import com.claro.sccweb.controller.BaseOperationController;
import com.claro.sccweb.controller.util.BasicIntegerItem;
import com.claro.sccweb.controller.validator.SccAcoesCobrancaValidator;
import com.claro.sccweb.form.SccAcoesCobrancaForm;
import com.claro.sccweb.service.SccAcoesCobrancaService;
import com.claro.sccweb.service.ServiceException;

@Controller
@RequestMapping(value="/user/relatorio/faturas/acoes/cobranca")
public class SccAcoesCobrancaController extends BaseOperationController<SccAcoesCobrancaForm> {
	
	@Autowired
	private SccAcoesCobrancaService sccAcoesCobrancaService;
	public static final String FWD_VIEW_ACOES_COBRANCA   = "relatorio_acoes_cobranca";
	public static final String FWD_EXCEL_ACOES_COBRANCA   = "relatorio_acoes_cobranca_excel";
	
	private final SccAcoesCobrancaValidator validator = new SccAcoesCobrancaValidator();
	
	@RequestMapping(value="listar", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView pesquisar(HttpServletRequest request, HttpServletResponse response, SccAcoesCobrancaForm form) throws Exception {
		
		ModelAndView mav = null;
		
		form.setListAcoesCobranca(gerarRelatorioAcoesCobranca(form.getFiltro()));

		if(form.getOperacao().equals("excel")){
			if(form.getListAcoesCobranca() != null && form.getListAcoesCobranca().size() > 0){
				mav = new ModelAndView(FWD_EXCEL_ACOES_COBRANCA, "filtro", form);
			}
		}else{

			mav = new ModelAndView(FWD_VIEW_ACOES_COBRANCA, "filtro", form);
		}
		
		return mav;
	}
	
	
	
	private List<SccAcoesCobrancaView> gerarRelatorioAcoesCobranca(SccFiltroAcoesCobranca filtro) throws DAOException, ServiceException {
		
		return sccAcoesCobrancaService.gerarRelatorioControleAcoesCobranca(filtro);
	}
	
	
	@ModelAttribute("operadorasExternas")
	public List<SccOperadora> populaOperadorasExternas() throws Exception {
		List<SccOperadora> comboList = new ArrayList<SccOperadora>();
		SccOperadora allValues = new SccOperadora();
		allValues.setCdCsp(BasicDAO.GET_ALL_STRING);
		allValues.setDsOperadora("Todas");
		comboList.add(0,allValues);
		comboList.addAll(getServiceManager().getPesquisaDominiosService().pesquisaOperadorasExternas());
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
		
		return "relatorio_acoes_cobranca";
	}

	@Override
	protected SccAcoesCobrancaForm getForm() {
		
		return new SccAcoesCobrancaForm();
	}

	@Override
	protected Validator getValidator() {
		
		return this.validator;
	}

	public void setSccAcoesCobrancaService(	SccAcoesCobrancaService sccAcoesCobrancaService) {
		this.sccAcoesCobrancaService = sccAcoesCobrancaService;
	}
	
	

}
