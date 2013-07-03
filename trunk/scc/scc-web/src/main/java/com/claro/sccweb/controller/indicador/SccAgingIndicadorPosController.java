package com.claro.sccweb.controller.indicador;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccAgingIndicador;
import com.claro.cobillingweb.persistence.entity.SccIndicador;
import com.claro.sccweb.controller.BaseOperationController;
import com.claro.sccweb.controller.util.BasicStringItem;
import com.claro.sccweb.controller.validator.SccIndicadorValidator;
import com.claro.sccweb.form.SccIndicadorForm;
import com.claro.sccweb.service.SccIndicadorService;
import com.claro.sccweb.service.ServiceException;

@Controller
@RequestMapping(value="/user/indicador/aging/pos")
public class SccAgingIndicadorPosController extends BaseOperationController<SccIndicadorForm> {
	
	private static final String FWD_VIEW_AGING_INDICADOR = "relatorio_indicador_aging_pos";
	private static final String FWD_EXCEL_AGING_INDICADOR = "relatorio_indicador_aging_pos_excel";
	private static final String OPERACAO_EXCEL =	"excel";
	
	private static final String TIPO_RELATORIO_POS ="F";
	
	@Autowired
	private SccIndicadorService sccIndicadorService;
	
	@SuppressWarnings("unused")
	private final SccIndicadorValidator validator = new SccIndicadorValidator();
	
	@RequestMapping(value="listar", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody ModelAndView pesquisar(HttpServletRequest request, HttpServletResponse response, SccIndicadorForm form) throws Exception {

		ModelAndView mav = null;
		form.getFiltro().setCdTipoContrato(TIPO_RELATORIO_POS);
		List<SccAgingIndicador> listDisputaPosPago = (List<SccAgingIndicador>) this.sccIndicadorService.pesquisaByFiltro(form.getFiltro());
		
		form.setListAgingIndicador(listDisputaPosPago);
		
		if(form.getOperacao().equals(OPERACAO_EXCEL)){
			if(form.getListIndicador() != null && form.getListIndicador().size() > 0){
				mav = new ModelAndView(FWD_EXCEL_AGING_INDICADOR, "filtro", form);
			}
			
		}else{
			mav = new ModelAndView(FWD_VIEW_AGING_INDICADOR, "filtro", form);
		}

		return mav;

	}
	
	@RequestMapping(value = "/editarAging", method=RequestMethod.GET)
	public @ResponseBody SccAgingIndicador editarIndicador(@RequestParam("idIndicador") String idIndicador, @RequestParam("sqAgingIndicador") Long sqAgingIndicador , HttpServletRequest request,HttpServletResponse response, SccIndicadorForm form) throws Exception {
		
		SccAgingIndicador entidade = (SccAgingIndicador) this.sccIndicadorService.getSccAgingIndicador(idIndicador, sqAgingIndicador);
		
		form.setEntity(entidade);
		
		return form.getEntity();

	}
	
	@RequestMapping(value = "/removerAging", method=RequestMethod.DELETE)
	public ModelAndView removerIndicador(@RequestParam("idIndicador") String idIndicador, @RequestParam("sqAgingIndicador") Long sqAgingIndicador, HttpServletRequest request,HttpServletResponse response, SccIndicadorForm form) throws Exception {
		
		ModelAndView mav = new ModelAndView(FWD_VIEW_AGING_INDICADOR);
		
		this.sccIndicadorService.excluirByParam(idIndicador, sqAgingIndicador);
		mav.addObject("filtro", form);
		return mav;
	}
	
	
	@RequestMapping(value="/salvarEntity", method=RequestMethod.POST)
	public @ResponseBody ModelAndView salvarEntity(HttpServletRequest request, HttpServletResponse response, SccIndicadorForm form) throws Exception {
		ModelAndView mav = new ModelAndView(FWD_VIEW_AGING_INDICADOR);
		
		this.sccIndicadorService.saveOrUpdate(form.getEntity());
		mav.addObject("filtro", form);	
		return mav;

	}
		
	protected String getViewName() {		
		return FWD_VIEW_AGING_INDICADOR;
	}
	
	protected SccIndicadorForm getForm() {		
		return new SccIndicadorForm();
	}
	
	protected Validator getValidator() {	
		
		//return this.validator;
		return null;
	}
	
	
	@ModelAttribute("indicadores")
	public List<BasicStringItem> carregarCombo(SccIndicadorForm form) throws ServiceException, DAOException{
		
		List<SccIndicador> list = null;
		
		form.getFiltro().setCdTipoContrato(TIPO_RELATORIO_POS);
		
		list = (List<SccIndicador>) this.sccIndicadorService.gerarCombo(form.getFiltro());
		form.setListIndicador(list);
		
		
		return gerarCombo(list);
		
	}
	
	private List<BasicStringItem> gerarCombo(List<SccIndicador> list){

		List<BasicStringItem> comboList = new ArrayList<BasicStringItem>();
		comboList.add(new BasicStringItem("T", "Todos"));
		for (SccIndicador entity : list) {
			comboList.add(new BasicStringItem(entity.getCdIndicador(), entity.getCdIndicador()));
		}
		
		return comboList;
		
	}
	
	
	public void setSccIndicadorService(SccIndicadorService sccIndicadorService) {
		this.sccIndicadorService = sccIndicadorService;
	}
	
	
	
}
