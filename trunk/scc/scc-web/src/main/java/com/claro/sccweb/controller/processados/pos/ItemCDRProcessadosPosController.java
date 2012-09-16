package com.claro.sccweb.controller.processados.pos;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.claro.cobillingweb.persistence.entity.SccCdrCobilling;
import com.claro.sccweb.controller.ControllerExecutionException;
import com.claro.sccweb.decorator.DetalheCDRDecorator;
import com.claro.sccweb.form.SccPaginatedList;


@Controller
@RequestMapping(value="/user/pos/processados/item")
public class ItemCDRProcessadosPosController {

	/**
	 * Seleciona um CDR para visualizar detalhes.
	 * @param data
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/cdr/{cdr}", method = RequestMethod.GET)
	public ModelAndView selecionaCDR(@PathVariable("cdr") Long data,HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ModelAndView mav = new ModelAndView();
		SccCdrCobilling cdrSelecionado = null;
		SccPaginatedList paginatedList = (SccPaginatedList)request.getSession().getAttribute("_cdrs_");
		List<SccCdrCobilling> cdrs = paginatedList.getList();
		for (int i=0;i<cdrs.size();i++){
			if (cdrs.get(i).getNuCdr().equals(data))
				{
				cdrSelecionado = cdrs.get(i);
				break;
				}
		}
		if (cdrSelecionado == null)
			throw new ControllerExecutionException("Navegação inválida. Um CDR deve ser selecionado");
		DetalheCDRDecorator decorator = new DetalheCDRDecorator(cdrSelecionado);
		mav.addObject("cdr", decorator);
		mav.setViewName("item_cdr_detalhe");
		return mav;
	}
	
}
