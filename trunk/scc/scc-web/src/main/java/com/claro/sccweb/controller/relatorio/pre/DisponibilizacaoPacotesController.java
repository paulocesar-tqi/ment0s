package com.claro.sccweb.controller.relatorio.pre;

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
import com.claro.cobillingweb.persistence.entity.SccOperadora;
import com.claro.cobillingweb.persistence.view.DisponibilizacaoPacotePrePagoView;
import com.claro.sccweb.controller.BaseOperationController;
import com.claro.sccweb.controller.util.BasicStringItem;
import com.claro.sccweb.decorator.rownum.view.DisponibilizacaoPacotePrePagoViewDecorator;
import com.claro.sccweb.form.BaseForm;
import com.claro.sccweb.form.DisponibilizacaoPacotesPreForm;

@Controller
@RequestMapping(value = "/user/relatorio/disponibilizacaoPacotes/pre")
public class DisponibilizacaoPacotesController extends BaseOperationController<DisponibilizacaoPacotesPreForm> {

	@Override
	protected String getViewName() {
		return "relatorio_disponibilizacao_pacotes_pre_filtro";
	}

	@Override
	protected DisponibilizacaoPacotesPreForm getForm() {
		return new DisponibilizacaoPacotesPreForm();
	}

	@Override
	protected Validator getValidator() {
		return null;
	}

	/**
	 * Popula combo com a lista de operadoras Claro
	 * 
	 * @return
	 * @throws Exception
	 */
	@ModelAttribute("operadorasClaro")
	public List<SccOperadora> populaOperadorasClaro() throws Exception {
		List<SccOperadora> comboList = new ArrayList<SccOperadora>();
		SccOperadora allValues = new SccOperadora();
		allValues.setCdEot(BasicDAO.GET_ALL_STRING);
		allValues.setDsOperadora("Todas");
		comboList.add(0, allValues);
		comboList.addAll(getServiceManager().getPesquisaDominiosService().pequisaOperadorasClaroComM());

		return comboList;
	}

	/**
	 * Popula combo com a lista de operadoras LD (externas).
	 * 
	 * @return
	 * @throws Exception
	 */
	@ModelAttribute("operadorasExternas")
	public List<SccOperadora> populaOperadorasExternas() throws Exception {
		List<SccOperadora> comboList = new ArrayList<SccOperadora>();
		SccOperadora nullValue = new SccOperadora();
		nullValue.setCdEot(BasicDAO.GET_ALL_STRING);
		nullValue.setDsOperadora("Todas");
		comboList.add(0, nullValue);
		comboList.addAll(getServiceManager().getPesquisaDominiosService().pesquisaOperadorasExternas());
		return comboList;
	}

	/**
	 * Popula combo com a lista de pacotes de assinatura
	 * 
	 * @return
	 * @throws Exception
	 */
	@ModelAttribute("pacotes")
	public List<BasicStringItem> populaPacotes() throws Exception {
		List<BasicStringItem> comboList = new ArrayList<BasicStringItem>();
		BasicStringItem nullValue = new BasicStringItem(BasicDAO.GET_ALL_STRING, "Todos");
		comboList.add(0, nullValue);
		comboList.addAll(getServiceManager().getSccAssinaturaPreService().findPacotesAssinatura());
		return comboList;
	}

	public ModelAndView pesquisar(HttpServletRequest request, HttpServletResponse response,
			@Valid @ModelAttribute(FORM_NAME) BaseForm _form, BindingResult bindingResult, Model model)
			throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());
		DisponibilizacaoPacotesPreForm form = (DisponibilizacaoPacotesPreForm) _form;

		Long cdPacote = null;
		Integer qtdMinutos = null;
		if (form.getCdPacoteMinuto() != null && !form.getCdPacoteMinuto().equals(BasicDAO.GET_ALL_STRING)) {
			String[] parts = form.getCdPacoteMinuto().split("_");
			cdPacote = Long.parseLong(parts[0]);
			qtdMinutos = Integer.parseInt(parts[1]);
		}
		List<DisponibilizacaoPacotePrePagoView> rows = getServiceManager().getSccAssinaturaPreService().pesquisarDisponibilidade(
				form.getCdEOTClaro(), form.getCdEOTLD(), cdPacote, qtdMinutos, form.getDtInicio(), form.getDtFim());
		DisponibilizacaoPacotePrePagoView total = null;
		
		if (rows.size() > 0)
			total = getServiceManager().getSccAssinaturaPreService().pesquisarSumarioDisponibilidade(
				form.getCdEOTClaro(), form.getCdEOTLD(), cdPacote, qtdMinutos, form.getDtInicioProcExterna(), form.getDtFimProcExterna());

		List<DisponibilizacaoPacotePrePagoViewDecorator> decoratorList = new ArrayList<DisponibilizacaoPacotePrePagoViewDecorator>(rows.size());
		for (int i = 0; i < rows.size(); i++) {
			DisponibilizacaoPacotePrePagoViewDecorator decorator = new DisponibilizacaoPacotePrePagoViewDecorator(rows.get(i), i);
			decoratorList.add(decorator);
		}
		if (total != null)
			decoratorList.add(new DisponibilizacaoPacotePrePagoViewDecorator(total, rows.size()));
		
		storeInSession(getClass(), DISPLAY_TAG_SPACE_1, decoratorList, request);
		
		return mav;
	}

	public ModelAndView excel(HttpServletRequest request, HttpServletResponse response,
			@Valid @ModelAttribute(FORM_NAME) BaseForm form, BindingResult bindingResult, Model model) throws Exception {
		info("Iniciando geração de Excel para pesquisa de batimento wrupp pré-pago");
		ModelAndView mav = new ModelAndView("relatorio_disponibilizacao_pacotes_pre_excel");
		return mav;
	}
}
