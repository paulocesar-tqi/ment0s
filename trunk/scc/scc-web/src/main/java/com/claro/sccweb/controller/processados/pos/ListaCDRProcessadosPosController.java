package com.claro.sccweb.controller.processados.pos;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.claro.cobillingweb.persistence.entity.SccCdrCobilling;
import com.claro.sccweb.controller.BaseFormController;
import com.claro.sccweb.form.SccPaginatedList;
import com.claro.sccweb.form.helper.NavegacaoArquivoCDRs;

@Controller
@RequestMapping(value = "/user/pos/processados/lista")
public class ListaCDRProcessadosPosController extends BaseFormController {

	/**
	 * Seleciona uma data do sumário para listagem completa dos CDRs.
	 * 
	 * @param cdStatus
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/data/{data}", method = RequestMethod.GET)
	public ModelAndView selecionaData(@PathVariable("data") Integer data,HttpServletRequest request, HttpServletResponse response ) throws Exception {
		ModelAndView mav = new ModelAndView();		
		
		NavegacaoArquivoCDRs navegacaoArquivoCDRs = (NavegacaoArquivoCDRs)getSessionDataManager().getSearchResultList().getResult().get(0);
		Long[] chavesCiclo = (Long[])navegacaoArquivoCDRs.getSumario().get(data).getKey();
		navegacaoArquivoCDRs.setChaveCiclo(data);
		SccPaginatedList paginatedList = new SccPaginatedList();
		paginatedList.setObjectsPerPage(50);
		paginatedList.setPageNumber(1);
		paginatedList.setFullListSize(getServiceManager().getSumarioService().getCountListaCDRsSumario(navegacaoArquivoCDRs.getArquivoSelecionado().getSqArquivo(), navegacaoArquivoCDRs.getStatusSelecionado(), chavesCiclo[0], chavesCiclo[1], chavesCiclo[2]));
		List<SccCdrCobilling> cdrs = getServiceManager().getSumarioService().gerarListaCDRsSumario(navegacaoArquivoCDRs.getArquivoSelecionado().getSqArquivo(), navegacaoArquivoCDRs.getStatusSelecionado(), chavesCiclo[0], chavesCiclo[1], chavesCiclo[2], paginatedList.getPageNumber()-1, paginatedList.getObjectsPerPage());		
		paginatedList.setList(cdrs);
		mav.setViewName("lista_cdrs_processados");
		request.getSession().setAttribute("_cdrs_", paginatedList);		
		return mav;
	}
	
	@RequestMapping(value="/pagina" , method=RequestMethod.GET)
	public ModelAndView pagina(@RequestParam("page") Integer page,HttpServletRequest request, HttpServletResponse response ) throws Exception
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("lista_cdrs_processados");
		SccPaginatedList paginatedList = new SccPaginatedList();
		paginatedList.setObjectsPerPage(50);
		paginatedList.setPageNumber(page);
		NavegacaoArquivoCDRs navegacaoArquivoCDRs = (NavegacaoArquivoCDRs)getSessionDataManager().getSearchResultList().getResult().get(0);
		Long[] chavesCiclo = (Long[])navegacaoArquivoCDRs.getSumario().get(navegacaoArquivoCDRs.getChaveCiclo()).getKey();
		paginatedList.setFullListSize(getServiceManager().getSumarioService().getCountListaCDRsSumario(navegacaoArquivoCDRs.getArquivoSelecionado().getSqArquivo(), navegacaoArquivoCDRs.getStatusSelecionado(), chavesCiclo[1], chavesCiclo[1], chavesCiclo[2]));
		List<SccCdrCobilling> cdrs = getServiceManager().getSumarioService().gerarListaCDRsSumario(navegacaoArquivoCDRs.getArquivoSelecionado().getSqArquivo(), navegacaoArquivoCDRs.getStatusSelecionado(), chavesCiclo[1], chavesCiclo[1], chavesCiclo[2], paginatedList.getPageNumber()-1, paginatedList.getObjectsPerPage());		
		paginatedList.setList(cdrs);
		request.getSession().setAttribute("_cdrs_", paginatedList);
		return mav;
	}
	
	@RequestMapping(value="/back" , method=RequestMethod.GET)
	public ModelAndView back() throws Exception
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("lista_cdrs_processados");
		return mav;
	}
	
	

}
