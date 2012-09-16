package com.claro.sccweb.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.claro.sccweb.controller.util.BasicStringItem;
import com.claro.sccweb.form.LoginForm;

@Controller
@RequestMapping("/login")
public class LoginController extends BaseFormController {

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView novoLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		SecurityContextHolder.clearContext();
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		getSessionDataManager().setModule("POS");		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("scc.login");
		mav.addObject("loginUsuario", new LoginForm());
		mav.addObject("sessionDataManager", getSessionDataManager());
		return mav;
	}
	
	@ModelAttribute("modulos")
	public List<BasicStringItem> populaMododulosDisponiveis() {
		List<BasicStringItem> list = new ArrayList<BasicStringItem>();
		list.add(new BasicStringItem("POS","Pos-Pago"));
		list.add(new BasicStringItem("PRE","Pre-Pago"));
		list.add(new BasicStringItem("CMM","Comum"));		
		return list;
	}
	
}
