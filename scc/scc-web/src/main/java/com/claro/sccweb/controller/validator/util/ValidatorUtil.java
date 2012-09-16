package com.claro.sccweb.controller.validator.util;

import java.util.Date;

import org.springframework.validation.Errors;

import com.claro.cobillingweb.persistence.dao.BasicDAO;

public class ValidatorUtil {

	
	public static void verificaCampoObrigatorio(String nome,String valor,Errors errors)
	{
		if ((valor == null) || (valor.equals(BasicDAO.NULL_STRING)) || (valor.equals("")))
			{
			errors.rejectValue(nome, "campoObrigatorio", "Campo Obrigatório");
			}
	}
	
	public static void verificaPeriodo(String nome ,Date dtInicial,Date dtFinal,Errors errors)
	{
		if ((dtInicial == null) || (dtFinal == null))
			{
			/*Nada a fazer.*/
			}
		else if (!dtFinal.after(dtInicial))
			{
			errors.rejectValue(nome, "campoObrigatorio", "Período Inválido");
			}
	}
	
	public static  void verificaCampoObrigatorio(String nome,Date valor,Errors errors)
	{
		if ((valor == null) || (valor.equals(BasicDAO.NULL_STRING)))
		{
		errors.rejectValue(nome, "campoObrigatorio", "Campo Obrigatório");
		}
	}
	
	public static  void verificaCampoObrigatorio(String nome,Double valor,Errors errors)
	{
		if ((valor == null) || (valor.equals(BasicDAO.NULL_DOUBLE)))
		{
		errors.rejectValue(nome, "campoObrigatorio", "Campo Obrigatório");
		}
	}
	
	public static  void verificaCampoObrigatorio(String nome,Long valor,Errors errors)
	{
		if ((valor == null) || (valor.equals(BasicDAO.NULL)))
		{
		errors.rejectValue(nome, "campoObrigatorio", "Campo Obrigatório");
		}
	}
	
	public static  void verificaCampoObrigatorio(String nome,Integer valor,Errors errors)
	{
		if ((valor == null) || (valor.equals(BasicDAO.NULL)))
		{
		errors.rejectValue(nome, "campoObrigatorio", "Campo Obrigatório");
		}
	}
	
	public static void verificaNumeroPositivo(String nome,Long valor,Errors errors)
	{
		if ((valor != null) && (valor.longValue() < 0))
			{
			errors.rejectValue(nome, "valorNegativo", "Valor Negativo");
			}
	}
	
	public static void verificaNumeroPositivo(String nome,Double valor,Errors errors)
	{
		if ((valor != null) && (valor.doubleValue() < 0.0))
			{
			errors.rejectValue(nome, "valorNegativo", "Valor Negativo");
			}
	}
	
	public static  void verificaMes(String nome,Long valor,Errors errors)
	{
		if (valor != null)
			{
			if ((valor.longValue() < 1L) || (valor.longValue() > 12L))
				{
				errors.rejectValue(nome, "mesInvalido", "Mês Inválido");
				}
			}
	}
}
