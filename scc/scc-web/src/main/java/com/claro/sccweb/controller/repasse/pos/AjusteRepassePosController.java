package com.claro.sccweb.controller.repasse.pos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.claro.cobillingweb.persistence.entity.SccOperadora;
import com.claro.cobillingweb.persistence.entity.SccRepasse;
import com.claro.sccweb.controller.BaseFormController;
import com.claro.sccweb.controller.ControllerExecutionException;
import com.claro.sccweb.controller.util.BasicIntegerItem;
import com.claro.sccweb.controller.util.BasicStringItem;
import com.claro.sccweb.controller.validator.AjusteRepassePosValidator;
import com.claro.sccweb.decorator.AjusteRepassePosDecorator;
import com.claro.sccweb.form.AjusteRepassePosForm;
import com.claro.sccweb.service.helper.DemonstrativoPosPagoConstantes;


/**
 * Controller responsável pela funcionalidade de ajuste de valores de itens de repasse
 *
 */
@Controller
@RequestMapping(value="/user/repasse/pos/ajuste")
public class AjusteRepassePosController extends BaseFormController {
	
	/**
	 * Operação indicando que o usuário ajustou o valor de um item de repasse.
	 */
	public static final String OPERACAO_AJUSTAR = "OPERACAO_AJUSTAR";
	
	/**
	 * Operação indicando que o usuário removeu um item de repasse.
	 */
	public static final String OPERACAO_REMOVER = "OPERACAO_REMOVER";
	
	public static final String OPCAO_CREDITAR = "CRED";
	
	public static final String OPCAO_DEBITAR = "DEB";
	
	private AjusteRepassePosValidator validator = new AjusteRepassePosValidator();
	
	 
	public void initBinder(WebDataBinder binder) {		
		super.initBinder(binder);
		binder.setValidator(validator);
	}

	 @RequestMapping(value="/new/{nuRepasse}" , method = RequestMethod.GET)
	  public ModelAndView novoAjuste(@PathVariable("nuRepasse") Long nuRepasse,HttpServletRequest request, HttpServletResponse response) throws Exception
	  {
		 ModelAndView mav = new ModelAndView();
		 mav.setViewName("repasse_pos_ajuste");
		 AjusteRepassePosForm form = new AjusteRepassePosForm();
		 form.setNuRepasse(nuRepasse);
		 mav.addObject("ajuste", form);
		 cacheMyForm(getClass(), form);		 
		 storeInSession(getClass(), DISPLAY_TAG_SPACE_1, carregaRepasse(nuRepasse), request);
		 return mav;
	  }
	
	 
	 /**
	  * Handler para requests da página de ajuste de repasse. 
	  * @param request
	  * @param response
	  * @param form
	  * @param bindingResult
	  * @param model
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping(value="/execute" , method=RequestMethod.POST)
	  public ModelAndView executa(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("ajuste")  AjusteRepassePosForm form,BindingResult bindingResult,Model model) throws Exception
	  {		 
		 String operacao = form.getOperacao();
		 if (bindingResult.hasErrors())
		 	{
			 ModelAndView mav = new ModelAndView();
			 mav.addObject("ajuste", form);
			 mav.setViewName("repasse_pos_ajuste");
			 return mav;
		 	}
		 else if (operacao.equals(OPERACAO_AJUSTAR))
		 	{
			 return ajustar(request, response, form, bindingResult, model);
		 	}
		 else if (operacao.equals(OPERACAO_REMOVER))
		 	{
			 return remover(request, response, form, bindingResult, model);
		 	}
		 return null;
	  }
	 
	 /*TODO Verificar com Dennis se o ajuste é sempre um novo item.*/
	 private ModelAndView ajustar(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("ajuste")  AjusteRepassePosForm form,BindingResult bindingResult,Model model) throws Exception
	 {
		 boolean novoItem = true;
		 ModelAndView mav = new ModelAndView();
		 mav.setViewName("repasse_pos_ajuste");
		 List<AjusteRepassePosDecorator> decorator = (List<AjusteRepassePosDecorator>)request.getSession().getAttribute(DISPLAY_TAG_SPACE_1);
		 /*
		 List<AjusteRepassePosDecorator> decorator = (List<AjusteRepassePosDecorator>)request.getSession().getAttribute(DISPLAY_TAG_SPACE_1);
		 for (int i=0;i<decorator.size();i++)
		 	{
			 AjusteRepassePosDecorator item = decorator.get(i);
			 if (item.getRow().getCdItemRepasse().equals(form.getCdItemRepasse()))
			 	{
				 novoItem = false;
				 if (item.editavel())
				 	{
					 Double valor = Double.parseDouble(form.getValor());
					 if (form.getCreditoDebito().equals(OPCAO_CREDITAR))
					 	{
						 valor = item.getRow().getVlBrutoItemRepasse() + valor;
					 	}
					 else if (form.getCreditoDebito().equals(OPCAO_DEBITAR))
					 	{
						 valor = item.getRow().getVlBrutoItemRepasse() - valor;
					 	}
					 getServiceManager().getRepasseService().ajustaValorBrutoItem(valor, item.getRow());
				 	}
				 else
				 	{
					 throw new ControllerExecutionException("Item de repasse não editável! Navegação inválida!");
				 	}
			 	}
		 	}
		 	*/
		 if (novoItem)
		 	{
			 Double valor = Double.parseDouble(form.getValor());
			 if (form.getCreditoDebito().equals(OPCAO_DEBITAR))
			 	{
				 valor = valor*-1;
			 	}
			 AjusteRepassePosDecorator toClone = decorator.get(0);
			 SccRepasse item = new SccRepasse();
			 item.setNuRepasse(form.getNuRepasse());			 
			 item.setVlBrutoItemRepasse(valor);
			 item.setCdItemRepasse(form.getCdItemRepasse());
			 item.setProduto(toClone.getRow().getProduto());
			 item.setNuQuinzena(toClone.getRow().getNuQuinzena());
			 item.setCdEotClaro(toClone.getRow().getCdEotClaro());
			 item.setCdEotLd(toClone.getRow().getCdEotLd());
			 item.setDtInicialRepasse(toClone.getRow().getDtInicialRepasse());
			 item.setDtCriacao(new Date());
			 item.setCdStatusRepasse(toClone.getRow().getCdStatusRepasse());
			 item.setDtFimRepasse(toClone.getRow().getDtFimRepasse());
			 item.setSccTipoContrato(toClone.getRow().getSccTipoContrato());
			 item.setCdUsuarioManut(getSessionDataManager().getUserPrincipal());
			 item.setTxObservacao(form.getObservacao());
			 //item.setVlLiquidoItemRepasse(Double.parseDouble(form.getValor()));
			 getServiceManager().getRepasseService().ajustaValorBrutoItem(valor, item);
		 	}
		 storeInSession(getClass(), DISPLAY_TAG_SPACE_1, carregaRepasse(form.getNuRepasse()), request);
		 mav.addObject("ajuste", form);
		 return mav;				 
	 }
	 
	 private ModelAndView remover(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("ajuste")  AjusteRepassePosForm form,BindingResult bindingResult,Model model) throws Exception
	 {
		 ModelAndView mav = new ModelAndView();
		 mav.setViewName("repasse_pos_ajuste");
		 List<AjusteRepassePosDecorator> decorator = (List<AjusteRepassePosDecorator>)request.getSession().getAttribute(DISPLAY_TAG_SPACE_1);
		 for (int i=0;i<decorator.size();i++)
		 	{
			 AjusteRepassePosDecorator item = decorator.get(i);
			 if (item.getRow().getCdItemRepasse().equals(form.getCdItemRepasse()))
			 	{
				 if (item.editavel())
				 	{
					 getServiceManager().getRepasseService().removeItemRepasse(form.getCdItemRepasse(),form.getNuRepasse());
				 	}
				 else
				 	{
					 throw new ControllerExecutionException("Item de repasse não editável! Navegação inválida!");
				 	}
			 	}
		 	}
		 storeInSession(getClass(), DISPLAY_TAG_SPACE_1, carregaRepasse(form.getNuRepasse()), request);		 
		 mav.addObject("ajuste", form);
		 return mav;		 
	 }
	 
	 private List<AjusteRepassePosDecorator> carregaRepasse(Long nuRepasse) throws Exception
	 {
		 List<SccRepasse> itens = getServiceManager().getRepasseService().carregaItensRepasse(nuRepasse);
		 List<AjusteRepassePosDecorator> decorator = new ArrayList<AjusteRepassePosDecorator>(itens.size());
		 for (int i=0;i<itens.size();i++)
		 	{			 
			 if (i == 0)
			 	{
				 SccOperadora operadoraClaro = getServiceManager().getOperadoraService().pesquisaOperadoraByPk(itens.get(i).getCdEotClaro());
				 SccOperadora operadoraLD = getServiceManager().getOperadoraService().pesquisaOperadoraByPk(itens.get(i).getCdEotLd());				  
				// ((AjusteRepassePosForm)getMyFormFromCache(getClass())).setTitulo("Repasse "+operadoraClaro.getDsOperadora()+" para "+operadoraLD.getDsOperadora()+" : "+getSccDateFormat().format(itens.get(i).getDtInicialRepasse()));
			 	}
			 decorator.add(new AjusteRepassePosDecorator(itens.get(i),getServiceManager().getRepasseService().getItemRepasse(itens.get(i).getCdItemRepasse())));
		 	}	
		 return decorator;
		 
	 }
	 
	    @ModelAttribute("opcoes")
		public List<BasicStringItem> populaMeses() 
		{
	    	List<BasicStringItem> comboList = new ArrayList<BasicStringItem>();
	    	comboList.add(new BasicStringItem(OPCAO_CREDITAR, "Creditar(+)"));
	    	comboList.add(new BasicStringItem(OPCAO_DEBITAR, "Deduzir(-)"));
			return comboList;
		}
	    
	    @ModelAttribute("editaveis")
	    public List<BasicIntegerItem> populaItensEditaveis()
	    {
	    	List<BasicIntegerItem> comboList = new ArrayList<BasicIntegerItem>();
	    	comboList.add(new BasicIntegerItem(DemonstrativoPosPagoConstantes.ACERTO_CONCILIACAO_CONTRA_OPERADORA_LD, "ACERTO DE CONCILIAÇÃO CONTRA PRESTADORA LD (8)"));
	    	comboList.add(new BasicIntegerItem(DemonstrativoPosPagoConstantes.ACERTO_CONCILIACAO_CONTRA_CLARO, "ACERTO CONCILIAÇÃO CONTRA CLARO (9)"));
	    	comboList.add(new BasicIntegerItem(DemonstrativoPosPagoConstantes.MULTAS_JUROS_ATRASO_REPASSE_CONTRA_CLARO, "MULTA/JUROS  POR ATRASO - CONTRA CLARO (12)"));
	    	comboList.add(new BasicIntegerItem(DemonstrativoPosPagoConstantes.MULTAS_JUROS_ATRASO_PAGAMENTO_CONTRA_LD, "MULTA/JUROS  POR ATRASO - CONTRA LD (13)"));
	    	comboList.add(new BasicIntegerItem(DemonstrativoPosPagoConstantes.PENALIDADE_REJEICOES_INDEVIDAS_CONTRA_CLARO, "PENALIDADE POR REJEIÇÕES CONTRA CLARO (10)"));
	    	comboList.add(new BasicIntegerItem(DemonstrativoPosPagoConstantes.PENALIDADE_REJEICOES_DEVIDAS_CONTRA_LD, "PENALIDADE POR REJEIÇÕES CONTRA LD (11)"));	    	
	    	return comboList;
	    }
	    
}
