package com.claro.sccweb.controller.relatorio.fatura;

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
import com.claro.cobillingweb.persistence.filtro.SccFiltro;
import com.claro.cobillingweb.persistence.view.SccAcoesCobrancaView;
import com.claro.sccweb.controller.BaseOperationController;
import com.claro.sccweb.controller.util.BasicIntegerItem;
import com.claro.sccweb.controller.validator.SccAcoesCobrancaValidator;
import com.claro.sccweb.decorator.view.SccAcoesCobrancaViewDecorator;
import com.claro.sccweb.form.BaseForm;
import com.claro.sccweb.form.SccAcoesCobrancaForm;
import com.claro.sccweb.service.SccAcoesCobrancaService;
import com.claro.sccweb.service.ServiceException;

@Controller
@RequestMapping(value="/user/relatorio/faturas/acoes/cobranca")
public class SccAcoesCobrancaController extends BaseOperationController<SccAcoesCobrancaForm> {
	
	@Autowired
	private SccAcoesCobrancaService sccAcoesCobrancaService;
	
	private final SccAcoesCobrancaValidator validator = new SccAcoesCobrancaValidator();
	
	public ModelAndView pesquisar(HttpServletRequest request, HttpServletResponse response, BaseForm _form, BindingResult bindingResult, Model model) throws Exception {
		
		SccAcoesCobrancaForm form = (SccAcoesCobrancaForm)_form;
		SccFiltro filtro = getFiltro(form);
		
		List<SccAcoesCobrancaView> rows = gerarRelatorioAcoesCobranca(filtro);
		
		List<SccAcoesCobrancaViewDecorator> decoratorList = new ArrayList<SccAcoesCobrancaViewDecorator>(rows.size());
		
		for (int i = 0; i < rows.size(); i++) {
			
			SccAcoesCobrancaViewDecorator decorator = new SccAcoesCobrancaViewDecorator(rows.get(i), i);
			decoratorList.add(decorator);
		}
		
		storeInSession(getClass(), DISPLAY_TAG_SPACE_1, decoratorList, request);
		ModelAndView mav = new ModelAndView(getViewName());
		return mav;
		
	}
	
	private SccFiltro getFiltro(SccAcoesCobrancaForm form){
		
		SccFiltro filtro = new SccFiltro();
		filtro.setCdCsp(form.getCdCsp());
		filtro.setDataInicialPeriodo(calculaDataInicialPeriodo(form.getMes(), form.getAno()));
		filtro.setDataFinalPeriodo(calculaDataFinalPeriodo(form.getMes(), form.getAno()));
		
		return filtro;
	}
	
	
	private List<SccAcoesCobrancaView> gerarRelatorioAcoesCobranca(SccFiltro filtro) throws DAOException, ServiceException {
		
		return sccAcoesCobrancaService.gerarRelatorioControleAcoesCobranca(filtro);
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
