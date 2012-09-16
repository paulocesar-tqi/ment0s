/**
 * 
 */
package com.claro.sccweb.controller.relatorio;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccParamProcesso;
import com.claro.sccweb.controller.BaseOperationController;
import com.claro.sccweb.controller.validator.ProcessoParametroValidator;
import com.claro.sccweb.decorator.rownum.entity.SccParamProcessoDecorator;
import com.claro.sccweb.dto.SccParamProcessoDto;
import com.claro.sccweb.form.BaseForm;
import com.claro.sccweb.form.ProcessoParametroForm;
import com.claro.sccweb.service.ServiceException;

/**
 * @author 93046251
 *
 */
@Controller
@RequestMapping(value="/user/relatorio/processo/parametro")
public class ProcessosParametrosController extends BaseOperationController<ProcessoParametroForm> {
	
	private final ProcessoParametroValidator validator = new ProcessoParametroValidator();
	
	
	@ModelAttribute("tiposProcessos")
	public List<SccParamProcessoDto> populaComboTipoProcesso() throws Exception{
		
		List<SccParamProcessoDto> comboList = (List<SccParamProcessoDto>) converterEntity();
		SccParamProcessoDto allvalues = new SccParamProcessoDto();
		
		allvalues.setCdProcesso("Todos");
		allvalues.setNmParametro(BasicDAO.GET_ALL_STRING);
		comboList.add(allvalues);
		return comboList;
	}
	
	private List<SccParamProcessoDto> converterEntity() throws DAOException, ServiceException{
		List<String> lstStr = getServiceManager().getSccParamProcessoService().listarProcessos();
		List<SccParamProcessoDto> rows = new ArrayList<SccParamProcessoDto>();
		if (lstStr != null && !lstStr.isEmpty()){
			for (String cdProcesso : lstStr) {
				SccParamProcessoDto dto = new SccParamProcessoDto();
				dto.setCdProcesso(cdProcesso);
				rows.add(dto);
			}
		}
		return rows;
	}
	
	public ModelAndView pesquisar(HttpServletRequest request, HttpServletResponse response, BaseForm form, BindingResult bindingResult, Model model) throws Exception {
		ProcessoParametroForm formProcesso = (ProcessoParametroForm) form;
		
		List<SccParamProcessoDto> rows = buscarProcessoParametros(formProcesso);
		
		List<SccParamProcessoDecorator> decoratorList = montarRelatorio(rows);
		
		storeInSession(getClass(), DISPLAY_TAG_SPACE_1, decoratorList, request);
		ModelAndView mav = new ModelAndView(getViewName());
		return mav;
	}
	
	private List<SccParamProcessoDecorator> montarRelatorio(List<SccParamProcessoDto> rows){
		
		List<SccParamProcessoDecorator> decoratorList = new ArrayList<SccParamProcessoDecorator>(rows.size());
		if (rows != null && !rows.isEmpty()) {
			for (int i = 0; i < rows.size(); i++) {
				SccParamProcessoDecorator decorator = new SccParamProcessoDecorator(rows.get(i), i);
				decoratorList.add(decorator);
			}
		}
		return decoratorList;
	}
	
	
	private List<SccParamProcessoDto> buscarProcessoParametros(ProcessoParametroForm form) throws DAOException, ServiceException, ParseException {
		
		formatarData(form);
		List<SccParamProcesso> list = getServiceManager().getSccParamProcessoService().pesquisaProcessoParametros(form.getTipoProcesso(), form.getDataInicial(), form.getDataFinal());
		List<SccParamProcessoDto> listDto = new ArrayList<SccParamProcessoDto>();
		for (int i = 0; i < list.size(); i++) {
			SccParamProcessoDto dto = new SccParamProcessoDto();
			dto.setCdProcesso(list.get(i).getId().getCdProcesso());
			dto.setNmParametro(list.get(i).getId().getNmParametro());
			dto.setCdTipoParametro(list.get(i).getCdTipoParametro());
			dto.setDtAtualizacao(list.get(i).getDtAtualizacao());
			dto.setDtCriacao(list.get(i).getDtCriacao());
			dto.setTxValorParametro(list.get(i).getTxValorParametro());
			dto.setCdUsuarioManut(list.get(i).getCdUsuarioManut());
			listDto.add(dto);
		}
		return listDto; 
	}

	@Override
	protected String getViewName() {
		
		return "relatorio_processo_parametro";
	}

	@Override
	protected Validator getValidator() {

		return this.validator;
	}
	
	@Override
	protected ProcessoParametroForm getForm() {
		
		return new ProcessoParametroForm();
	}
	public static void main(String[] args) throws ParseException{
		Date dt = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String str = sdf.format(dt);
		SimpleDateFormat sdfNew = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		dt = new Date(sdfNew.parse(str.concat(" 23:59:59")).getTime());
		
		
	}
	
	private String pegarData(Date data){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(data);
	}
	private void formatarData(ProcessoParametroForm form) throws ParseException{
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
		form.setDataInicial(new Date(sdf.parse(pegarData(form.getDataInicial()).concat(" 00:00:00")).getTime()));
		
		form.setDataFinal(new Date(sdf.parse(pegarData(form.getDataFinal()).concat(" 23:59:59")).getTime()));
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



}
