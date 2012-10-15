package com.claro.cobillingweb.persistence.dao.internal.impl;

import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.impl.HibernateBasicDAOImpl;
import com.claro.cobillingweb.persistence.dao.internal.SccFinanceiroDAO;
import com.claro.cobillingweb.persistence.filtro.SccFinanceiroFiltro;
import com.claro.cobillingweb.persistence.view.SccFinanceiroView;
import com.claro.cobillingweb.persistence.view.mapper.NativeSQLViewMapper;

@Repository
public class SccFinanceiroDAOImpl extends HibernateBasicDAOImpl<SccFinanceiroView> implements
		SccFinanceiroDAO {
	
	
	@Override
	public Collection<SccFinanceiroView> findByFiltro(SccFinanceiroFiltro filtro)
			throws DAOException {
		Collection<SccFinanceiroView> list = null;
		try {
			
			Session session = getSessionFactory().getCurrentSession();
			NativeSQLViewMapper<SccFinanceiroView> mapper = new NativeSQLViewMapper<SccFinanceiroView>(session, gerarSQL(filtro), SccFinanceiroView.class);
			mapper.addResultMap("cdEotLD", String.class);
			mapper.addResultMap("cdEotClaro", String.class);
			mapper.addResultMap("cdItemRelFinanceiro", Integer.class);
			mapper.addResultMap("qtCdrs", Integer.class);
			mapper.addResultMap("vlLiquidoChamada", Double.class);
			mapper.addResultMap("vlBrutoChamada", Double.class);
			mapper.addResultMap("peFatorImposto", Double.class);
			mapper.addResultMap("sgUf", String.class);

			mapper.addResultMap("dtReferencia", String.class);
			mapper.addResultMap("cdProcesso", String.class);
			mapper.addResultMap("peRetencao", Double.class);
			mapper.addResultMap("minDuracaoTarifada", Double.class);
			mapper.addResultMap("descItem", String.class);
			
			list = (Collection<SccFinanceiroView>) mapper.execute();
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
		return list;
	}
	


	@Override
	public List<SccFinanceiroView> getAll() throws DAOException {
		
		return null;
	}
	
	private String gerarSQL(SccFinanceiroFiltro filtro) throws DAOException{
		
		StringBuilder lQuery = new StringBuilder();
		try {
			
			lQuery.append("SELECT ");
			if (StringUtils.isNotEmpty(filtro.getEotExtCompany())) {
				lQuery.append("	RF.CD_EOT_LD, ");
			}else{
				lQuery.append("	' ' AS CD_EOT_LD, ");
			}
			if (StringUtils.isNotEmpty(filtro.getEotClaroCompany())) {
				lQuery.append("	RF.CD_EOT_CLARO, ");
			}else{
				lQuery.append("	' ' AS CD_EOT_CLARO, ");
			}
			
			lQuery.append("	RF.CD_ITEM_REL_FINANCEIRO, ");
			lQuery.append("	SUM(RF.QT_CDRS), ");
			lQuery.append("	TRUNC(NVL(SUM(RF.VL_LIQUIDO_CHAMADA),0),2), ");
			lQuery.append("	TRUNC(NVL(SUM(RF.VL_BRUTO_CHAMADA),0),2), ");
			
			if  (StringUtils.isNotEmpty(filtro.getEotClaroCompany())
			&&  (StringUtils.isEmpty(filtro.getEotExtCompany()))  
			&&  (filtro.getTipoRelatorio() == "RR") 
			) {
				lQuery.append("	SUM(RF.PE_FATOR_IMPOSTO), ");
			}else{
				if (StringUtils.isNotEmpty(filtro.getEotClaroCompany())
				&& (StringUtils.isNotBlank(filtro.getEotExtCompany()))){
						lQuery.append("	SUM(RF.PE_FATOR_IMPOSTO), ");
				}else{
					lQuery.append("	0 AS PE_FATOR_IMPOSTO, ");
				}
			}
			if (StringUtils.isNotEmpty(filtro.getEotClaroCompany())) {
				lQuery.append("	RF.SG_UF, ");
			}else{
				lQuery.append("	' ' AS SG_UF, ");
			}
			lQuery.append("RF.DT_REFERENCIA, ");
			
			lQuery.append("	RF.CD_PROCESSO, ");
			
			if  (StringUtils.isEmpty(filtro.getEotClaroCompany())
			&&  (StringUtils.isNotEmpty(filtro.getEotExtCompany()))  
			&&  (filtro.getTipoRelatorio().equalsIgnoreCase("RR"))){
				lQuery.append("	SUM(RF.PE_RETENCAO), ");
			}else{
				if (StringUtils.isNotEmpty(filtro.getEotClaroCompany())
				&& (StringUtils.isNotEmpty(filtro.getEotExtCompany()))){
					lQuery.append("	SUM(RF.PE_RETENCAO), ");
				}else{
					lQuery.append("	0 AS PE_RETENCAO, ");
				}
			}
		
			lQuery.append("	SUM(RF.MI_DURACAO_TARIFADA), ");
			lQuery.append("I.DS_ITEM_REL_FINANCEIRO ");
			
			lQuery.append("	FROM SCC_RELATORIO_FINANCEIRO RF, SCC_ITEM_REL_FINANCEIRO I ");
			lQuery.append("	WHERE RF.CD_PROCESSO IN ( '"+filtro.getTipoRelatorio()+ "A' ) ");
		    lQuery.append(" AND RF.CD_PROCESSO = I.CD_PROCESSO ");
		    lQuery.append(" AND RF.CD_ITEM_REL_FINANCEIRO = I.CD_ITEM_REL_FINANCEIRO ");

			if ((filtro.getTipoRelatorio().equalsIgnoreCase("R2P"))||
				(filtro.getTipoRelatorio().equalsIgnoreCase("R3P"))||
				(filtro.getTipoRelatorio().equalsIgnoreCase("R4P"))){
				
				lQuery.append("	AND RF.DT_APURACAO IN ( '"+ filtro.getData() +"' ) ");
				
			}else{
				lQuery.append("	AND RF.DT_REFERENCIA IN ( '"+ filtro.getData() +"' ) ");
			
			}
			
			if  (StringUtils.isNotEmpty(filtro.getUf())){
				lQuery.append("	AND RF.SG_UF = '" + filtro.getUf()+"' ");
			}

			if  (StringUtils.isNotEmpty(filtro.getEotClaroCompany())){
				lQuery.append("	AND RF.CD_EOT_CLARO = '" + filtro.getEotClaroCompany()+"' ");
			}

			if  (StringUtils.isNotEmpty(filtro.getEotExtCompany())){
				lQuery.append("	AND RF.CD_EOT_LD = '" + filtro.getEotExtCompany()+"' ");
			}
			if(StringUtils.isNotEmpty(filtro.getCdProduto())){
				lQuery.append("	AND RF.CD_PRODUTO_COBILLING = '" + filtro.getCdProduto()+"' ");
			}
			
			lQuery.append("	GROUP BY ");

			if  (StringUtils.isNotEmpty(filtro.getEotExtCompany())){
				lQuery.append("	RF.CD_EOT_LD, ");
			}
			if  (StringUtils.isNotEmpty(filtro.getEotClaroCompany())){
				lQuery.append("	RF.CD_EOT_CLARO, ");
			}
			lQuery.append("	RF.CD_ITEM_REL_FINANCEIRO, ");

			if  (StringUtils.isNotEmpty(filtro.getUf())){
				lQuery.append("	RF.SG_UF, ");
			}
			lQuery.append("	RF.DT_REFERENCIA, RF.CD_PROCESSO, RF.DT_APURACAO, I.DS_ITEM_REL_FINANCEIRO ");
			lQuery.append("	ORDER  BY RF.DT_REFERENCIA, RF.CD_ITEM_REL_FINANCEIRO ASC ");
			
			
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), e);
			
		}
		return lQuery.toString();
	}


}
