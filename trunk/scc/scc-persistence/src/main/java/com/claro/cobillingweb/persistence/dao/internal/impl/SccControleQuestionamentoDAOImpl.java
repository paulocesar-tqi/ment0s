package com.claro.cobillingweb.persistence.dao.internal.impl;

import org.springframework.stereotype.Repository;

import com.claro.cobillingweb.persistence.dao.FwjBaseDaoHibernateImpl;
import com.claro.cobillingweb.persistence.dao.internal.SccControleQuestinamentoDAO;
import com.claro.cobillingweb.persistence.entity.SccControleQuestionamento;

@Repository
public class SccControleQuestionamentoDAOImpl extends
		FwjBaseDaoHibernateImpl<SccControleQuestionamento, Long> implements
		SccControleQuestinamentoDAO {
	
	
	private String gerarSQL(){
		
		StringBuilder sql = new StringBuilder();
		sql.append("");
		sql.append("");
		sql.append("");
		sql.append(""); 
		return sql.toString();
	}


}
