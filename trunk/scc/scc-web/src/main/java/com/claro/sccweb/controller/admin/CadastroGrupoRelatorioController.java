/**
 * 
 */
package com.claro.sccweb.controller.admin;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
import com.claro.cobillingweb.persistence.entity.SccAssociacaoRelatorioGrupo;
import com.claro.cobillingweb.persistence.entity.SccGrupoCobilling;
import com.claro.cobillingweb.persistence.entity.SccRelatorioCobilling;
import com.claro.sccweb.controller.BaseOperationController;
import com.claro.sccweb.controller.util.BasicIntegerItem;
import com.claro.sccweb.controller.validator.CadastroGrupoRelatorioValidator;
import com.claro.sccweb.form.CadastroGrupoRelatorioForm;
import com.claro.sccweb.service.SccAssociacaoRelatorioGrupoService;
import com.claro.sccweb.service.SccGrupoCobillingService;
import com.claro.sccweb.service.SccRelatorioCobillingService;

/**
 * @author 92038883
 *
 */
@Controller
@RequestMapping(value="/user/admin/rel/associado")
public class CadastroGrupoRelatorioController extends BaseOperationController<CadastroGrupoRelatorioForm> {


	@Autowired
	private SccAssociacaoRelatorioGrupoService sccAssociacaoRelatorioGrupoService;
											   
	
	@Autowired
	private SccRelatorioCobillingService sccRelatorioCobillingService;
	
	@Autowired
	private SccGrupoCobillingService sccGrupoCobillingService;

	

	private static final String FWD_VIEW_GRUPO_RELATORIO ="cadastro_grupo_relatorio_cobilling_filtro";
	@SuppressWarnings("unused")
	private static final String FWD_EXCEL_GRUPO_RELATORIO ="cadastro_grupo_relatorio_cobilling_filtro_excel";

	private CadastroGrupoRelatorioValidator validator = new CadastroGrupoRelatorioValidator();
	
	

	@RequestMapping(value="listar", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView listarTodos(HttpServletRequest request, HttpServletResponse response, CadastroGrupoRelatorioForm form) throws Exception {
		
		List<SccRelatorioCobilling> rows = sccRelatorioCobillingService.findAll();
		form.setListRelatorios(rows);
		ModelAndView mav = new ModelAndView(FWD_VIEW_GRUPO_RELATORIO, "filtro", form);
		
		return mav;
		
	}
	
	
	@SuppressWarnings("unused")
	@RequestMapping(value = "/editarListGrupoAssociado", method=RequestMethod.GET)
	public @ResponseBody List<SccAssociacaoRelatorioGrupo> editarListaGrupoAssociado(@RequestParam("sqRelatorio") Long sqRelatorio, HttpServletRequest request,HttpServletResponse response, CadastroGrupoRelatorioForm form) throws Exception {
		
		ModelAndView mav = null;
		List<SccAssociacaoRelatorioGrupo> list = this.sccAssociacaoRelatorioGrupoService.buscarGrupoAssociado(sqRelatorio);
		form.setListAssociados(list);
		mav = new ModelAndView(FWD_VIEW_GRUPO_RELATORIO, "filtro", form);
		return form.getListAssociados();
	}
	
	@SuppressWarnings("unused")
	@RequestMapping(value = "/editarGrupoAssociado", method=RequestMethod.GET)
	public @ResponseBody SccAssociacaoRelatorioGrupo editarGrupoAssociado(@RequestParam("sqGrupo") Long sqGrupo, @RequestParam("sqRelatorio")Long sqRelatorio, @RequestParam("dtInicioVigencia") Date dtInicioVigencia, HttpServletRequest request,HttpServletResponse response, CadastroGrupoRelatorioForm form) throws Exception {
		ModelAndView mav = null;
		SccAssociacaoRelatorioGrupo entity = this.sccAssociacaoRelatorioGrupoService.editEntity(sqGrupo, sqRelatorio, dtInicioVigencia);
		form.setEntity(entity);
		mav = new ModelAndView(FWD_VIEW_GRUPO_RELATORIO, "filtro", form);
		return form.getEntity();
	}
	
	@RequestMapping(value="/removerGrupoAssociado", method=RequestMethod.DELETE)
	public @ResponseBody ModelAndView  deleteGrupoAssociado(@RequestParam("sqGrupo") Long sqGrupo, @RequestParam("sqRelatorio")Long sqRelatorio, @RequestParam("dtInicioVigencia") String dtInicioVigencia, HttpServletRequest request,HttpServletResponse response, CadastroGrupoRelatorioForm form) throws Exception {
	
		ModelAndView mav = new ModelAndView(FWD_VIEW_GRUPO_RELATORIO);
		
		this.sccAssociacaoRelatorioGrupoService.deleteEntity(sqGrupo, sqRelatorio, StringToDaten(dtInicioVigencia));
		mav.addObject("filtro", form);
		return mav;
		
		
	}
	
	@SuppressWarnings("unused")
	@RequestMapping(value = "/listGrupoAssociado", method=RequestMethod.GET)
	public @ResponseBody List<SccAssociacaoRelatorioGrupo> editarListaGrupoAssociado(@RequestParam("sqRelatorio") String sqRelatorio,  HttpServletRequest request,HttpServletResponse response, CadastroGrupoRelatorioForm form) throws Exception {
		
		ModelAndView mav = null;
		String query = request.getQueryString();
		List<SccAssociacaoRelatorioGrupo> list = this.sccAssociacaoRelatorioGrupoService.buscarGrupoAssociado(new Long(sqRelatorio));
		form.setListAssociados(list);
		mav = new ModelAndView(FWD_VIEW_GRUPO_RELATORIO, "filtro", form);
		return form.getListAssociados();
	}
	
		

	
	@ModelAttribute("relatorios")
	public List<BasicIntegerItem> gerarCombo()throws DAOException{
		List<BasicIntegerItem> comboList = new ArrayList<BasicIntegerItem>();
		comboList.add(new BasicIntegerItem(0l, "Todos"));
		List<SccRelatorioCobilling> rows = sccRelatorioCobillingService.findAll();
		for (SccRelatorioCobilling sccRelatorioCobilling : rows) {
			comboList.add(new BasicIntegerItem(sccRelatorioCobilling.getSqRelatorio(), sccRelatorioCobilling.getNoRelatorio()));
		}
		
		return comboList;
	}
	
	@ModelAttribute("grupos")
	public List<SccGrupoCobilling> gerarComboGrupo() throws DAOException{
		
		return this.sccGrupoCobillingService.findAll();
		
	}
	
	
	
	@Override
	protected String getViewName() {
		
		return "cadastro_grupo_relatorio_cobilling_filtro";
	}
	
	public String formataData(String data){
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy"); 
		String dataFormatada = formato.format(data);
		return dataFormatada;
		
	}
	
	public Date StringToDaten(String data){
	    Date DataNascFr = null;  
	    String DataNascDb = null; 
	    Date DataConvertida = null;
	    try{  
	    DataNascFr = new SimpleDateFormat("yyyy-MM-dd").parse(data);    
	    DataNascDb = new SimpleDateFormat("dd-MM-yyyy").format(DataNascFr);
	    DataConvertida = new SimpleDateFormat("dd-MM-yyyy").parse(DataNascDb);
	    }catch( java.text.ParseException e ){  
	    e.printStackTrace();  
	    }
		return DataConvertida;  


		
	}
	
	public Date converterString(String data) throws Exception {

		String novaData = formataData(data);
		if (novaData == null || novaData.equals(""))
			return null;

		Date date = null;
		try {
			DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			date = new Date(((Date) formatter.parse(data)).getTime());
		} catch (ParseException e) {
			throw e;
		}
		return date;
	}

	public void setSccRelatorioCobillingService(
			SccRelatorioCobillingService sccRelatorioCobillingService) {
		this.sccRelatorioCobillingService = sccRelatorioCobillingService;
	}


	@Override
	protected CadastroGrupoRelatorioForm getForm() {

		return new CadastroGrupoRelatorioForm();
	}


	@Override
	protected Validator getValidator() {
		
		return this.validator;
	}


	public void setSccAssociacaoRelatorioGrupoService(
			SccAssociacaoRelatorioGrupoService sccAssociacaoRelatorioGrupoService) {
		this.sccAssociacaoRelatorioGrupoService = sccAssociacaoRelatorioGrupoService;
	}


	public void setSccGrupoCobillingService(
			SccGrupoCobillingService sccGrupoCobillingService) {
		this.sccGrupoCobillingService = sccGrupoCobillingService;
	}


	
	
}
