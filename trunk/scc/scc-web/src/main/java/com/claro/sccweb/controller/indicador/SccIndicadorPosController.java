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

import com.claro.cobillingweb.persistence.constants.CobillingConstants;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccIndicador;
import com.claro.sccweb.controller.BaseOperationController;
import com.claro.sccweb.controller.util.BasicStringItem;
import com.claro.sccweb.controller.validator.SccIndicadorValidator;
import com.claro.sccweb.form.SccIndicadorForm;
import com.claro.sccweb.service.SccIndicadorService;
import com.claro.sccweb.service.ServiceException;

@Controller
@RequestMapping(value="/user/indicador/indicador/pos")
public class SccIndicadorPosController extends BaseOperationController<SccIndicadorForm> {

	private static final String FWD_VIEW_INDICADOR = "relatorio_indicador_pos";
	private static final String FWD_EXCEL_INDICADOR = "relatorio_indicador_pos_excel";
	private static final String TIPO_RELATORIO_POS ="F";
	private static final String OPERACAO_EXCEL =	"excel";
	
	private final SccIndicadorValidator validator = new SccIndicadorValidator();
	
	@Autowired
	private SccIndicadorService sccIndicadorService;

	
	@RequestMapping(value="listar", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView pesquisarByFiltro(HttpServletRequest request, HttpServletResponse response, SccIndicadorForm form) throws Exception {
		
		form.getFiltro().setCdTipoContrato(TIPO_RELATORIO_POS);
		comboSelecionado(form);
		form.setListIndicador((List<SccIndicador>) this.sccIndicadorService.pesquisaByFiltroIndicador(form.getFiltro()));
		
		return definirOperacao(form);
		
	}
	
	private ModelAndView definirOperacao(SccIndicadorForm form){
		ModelAndView mav = null;
		if(form.getOperacao().equals(OPERACAO_EXCEL)){
			if(form.getListIndicador() != null && form.getListIndicador().size() > 0 ){
				mav = new ModelAndView(FWD_EXCEL_INDICADOR, "filtro", form);
			}
		}else{
			mav = new ModelAndView(FWD_VIEW_INDICADOR, "filtro", form);
		}
		
		return mav;
	}
/*	@RequestMapping(value="listar", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView pesquisarByFiltro(HttpServletRequest request, HttpServletResponse response, SccIndicadorForm form) throws Exception {

		form.getFiltro().setCdTipoContrato(TIPO_RELATORIO_POS);
		List<SccIndicador> listIndicadorPos = (List<SccIndicador>) this.sccIndicadorService.pesquisaByFiltroIndicador(form.getFiltro());
		
		form.setListIndicador(listIndicadorPos);
		
		storeInSession(getClass(), DISPLAY_TAG_SPACE_1, form.getListIndicador(), request);
		
		ModelAndView mav = new ModelAndView(FWD_VIEW_INDICADOR, "filtro", form);
		
		return mav;

	}
*/	
	
	@RequestMapping(value = "/editarIndicador", method=RequestMethod.GET)
	public @ResponseBody  SccIndicador editarIndicador(@RequestParam("indicador") String indicador , HttpServletRequest request,HttpServletResponse response, SccIndicadorForm form) throws Exception {
		
		form.getFiltro().setIdIndicador(indicador);
		SccIndicador entidade = (SccIndicador) this.sccIndicadorService.getSccIndicador(indicador);
		
		form.setEntidade(entidade);
		
		return form.getEntidade();
	}
	
	@RequestMapping(value="/salvarEntity", method=RequestMethod.POST)
	public @ResponseBody ModelAndView salvar(HttpServletRequest request, HttpServletResponse response, SccIndicadorForm form) throws Exception {
		ModelAndView mav = new ModelAndView(FWD_VIEW_INDICADOR);
		
		this.sccIndicadorService.saveOrUpdate(form.getEntidade());
		mav.addObject("filtro", form);	
		return mav;

	}
	
	@RequestMapping(value = "/removerIndicador", method=RequestMethod.GET)
	public ModelAndView removerIndicador(@RequestParam("indicador") String indicador , HttpServletRequest request,HttpServletResponse response, SccIndicadorForm form) throws Exception {
		
		ModelAndView mav = new ModelAndView(FWD_VIEW_INDICADOR);
		
		this.sccIndicadorService.excluirByPk(indicador);
		
		mav.addObject("filtro", form);
		return mav;
	}

	
	@ModelAttribute("indicadores")
	public List<BasicStringItem> carregarCombo(SccIndicadorForm form) throws ServiceException, DAOException{
		
		List<SccIndicador> list = null;
		
		form.getFiltro().setCdTipoContrato(TIPO_RELATORIO_POS);
		
		list = (List<SccIndicador>) this.sccIndicadorService.gerarCombo(form.getFiltro());
		
		return gerarCombo(list);
		
	}
	
	private void comboSelecionado(SccIndicadorForm form){
		if(form.getFiltro().getIdIndicador().equals("Todos")){
			form.getFiltro().setIdIndicador("T");
		}
	}

	
	private List<BasicStringItem> gerarCombo(List<SccIndicador> list){

		List<BasicStringItem> comboList = new ArrayList<BasicStringItem>();
		comboList.add(new BasicStringItem("T", "Todos"));
		for (SccIndicador entity : list) {
			comboList.add(new BasicStringItem(entity.getCdIndicador(), entity.getCdIndicador()));
		}
		
		return comboList;
		
	}
	
	
	@ModelAttribute("periodicidade")
	public List<BasicStringItem> popularListaPeriodicidade(){
		List<BasicStringItem> comboList = new ArrayList<BasicStringItem>();
		comboList.add(new BasicStringItem("1", CobillingConstants.BSC_PERIODICIDADE_ANUAL));
		comboList.add(new BasicStringItem("2", CobillingConstants.BSC_PERIODICIDADE_MENSAL));
		comboList.add(new BasicStringItem("3", CobillingConstants.BSC_PERIODICIDADE_DIARIA ));
		
		return comboList;
		
	}

	@ModelAttribute("tipo")
	public List<BasicStringItem> popularListaTipo(){
		List<BasicStringItem> comboList = new ArrayList<BasicStringItem>();
		comboList.add(new BasicStringItem("1", CobillingConstants.BSC_TIPO_INDICADOR_QUANTIDADE));
		comboList.add(new BasicStringItem("2", CobillingConstants.BSC_TIPO_INDICADOR_MINUTO));
		comboList.add(new BasicStringItem("3", CobillingConstants.BSC_TIPO_INDICADOR_VALOR));
		comboList.add(new BasicStringItem("3", CobillingConstants.BSC_TIPO_INDICADOR_PERCENTUAL));
		return comboList;
		
	}
	
	@ModelAttribute("status")
	public List<BasicStringItem> popularListaStatus(){
		List<BasicStringItem> comboList = new ArrayList<BasicStringItem>();
		comboList.add(new BasicStringItem("1", CobillingConstants.BSC_STATUS_INDICADOR_ATIVO));
		comboList.add(new BasicStringItem("2", CobillingConstants.BSC_STATUS_INDICADOR_SUSPENSO));
		comboList.add(new BasicStringItem("3", CobillingConstants.BSC_STATUS_INDICADOR_DMR));
		return comboList;
		
	}
	
    
	@ModelAttribute("formato")
	public List<BasicStringItem> popularListaFormato(){
		List<BasicStringItem> comboList = new ArrayList<BasicStringItem>();
		comboList.add(new BasicStringItem("1", CobillingConstants.BSC_FORMATO_INDICADOR_QUANTIDADE));
		comboList.add(new BasicStringItem("2", CobillingConstants.BSC_FORMATO_INDICADOR_VALOR));
		comboList.add(new BasicStringItem("3", CobillingConstants.BSC_FORMATO_INDICADOR_PERIODO));
		comboList.add(new BasicStringItem("4", CobillingConstants.BSC_FORMATO_INDICADOR_HORARIO));
		comboList.add(new BasicStringItem("5", CobillingConstants.BSC_FORMATO_INDICADOR_PERDAS));
		comboList.add(new BasicStringItem("6", CobillingConstants.BSC_FORMATO_INDICADOR_FALHASQTD));
		comboList.add(new BasicStringItem("7", CobillingConstants.BSC_FORMATO_INDICADOR_FALHASMIN));
		comboList.add(new BasicStringItem("8", CobillingConstants.BSC_FORMATO_INDICADOR_SINTETICO));
		comboList.add(new BasicStringItem("9", CobillingConstants.BSC_FORMATO_INDICADOR_PRAZOQTD));
		return comboList;
		
	}
	
	
	@Override
	protected String getViewName() {

		return FWD_VIEW_INDICADOR;
	}

	@Override
	protected SccIndicadorForm getForm() {
		
		return new SccIndicadorForm();
	}

	@Override
	protected Validator getValidator() {
		
		return this.validator;
	}


	public void setSccIndicadorService(SccIndicadorService sccIndicadorService) {
		this.sccIndicadorService = sccIndicadorService;
	}


}
