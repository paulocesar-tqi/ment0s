/**
 * 
 */
package com.claro.cobillingweb.persistence.dao.internal.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.impl.HibernateBasicDAOImpl;
import com.claro.cobillingweb.persistence.dao.internal.SccChamadasRefaturamentoDAO;
import com.claro.cobillingweb.persistence.dao.query.SccChamadasRefaturamentoDAONativeSQL;
import com.claro.cobillingweb.persistence.view.SccChamadasRefaturamentoView;
import com.claro.cobillingweb.persistence.view.mapper.NativeSQLViewMapper;

/**
 * @author 92031709
 *
 */
public class SccChamadasRefaturamentoDAOImpl extends HibernateBasicDAOImpl<SccChamadasRefaturamentoView>
		implements SccChamadasRefaturamentoDAO {

	@Override
	public List<SccChamadasRefaturamentoView> getAll() throws DAOException {
		
		return null;
	}

	@Override
	public List<SccChamadasRefaturamentoView> gerarRelatorioChamadasRefaturamento(Date dtInicial, Date dtFinal, String statusChamada, String operadoraLD, String operadoraClaro, String cdRefaturamento) throws DAOException {
		
		List<SccChamadasRefaturamentoView> list = null;
		try {
			Session session = getSessionFactory().getCurrentSession();
			NativeSQLViewMapper<SccChamadasRefaturamentoView> mapper = new NativeSQLViewMapper<SccChamadasRefaturamentoView>(session, SccChamadasRefaturamentoDAONativeSQL.SQL_INICIO, SccChamadasRefaturamentoView.class);
			
			if(statusChamada.equals("Fat")){ //Chamadas A Faturar/Faturado
					mapper.appendSQL(SccChamadasRefaturamentoDAONativeSQL.SQL_FAT);
			}else{ //Chamadas Aceite/Rejeitado
					mapper.appendSQL(SccChamadasRefaturamentoDAONativeSQL.SQL_REJ);
			}
			
			
			mapper.appendSQL(SccChamadasRefaturamentoDAONativeSQL.SQL_FIM);
			
			if(operadoraLD != null && !operadoraLD.equals(BasicDAO.GET_ALL_STRING)){
				mapper.addArgument("operadoraLD", operadoraLD, SccChamadasRefaturamentoDAONativeSQL.FILTRO_CD_EOT_LD);
			}
			if(operadoraClaro != null && !operadoraClaro.equals(BasicDAO.GET_ALL_STRING)){
				mapper.addArgument("operadoraClaro", operadoraClaro, SccChamadasRefaturamentoDAONativeSQL.FILTRO_CD_EOT_CLARO);
			}
			if(cdRefaturamento != null && !cdRefaturamento.equals(BasicDAO.GET_ALL_STRING)){
				mapper.addArgument("cdRefaturamento", cdRefaturamento, SccChamadasRefaturamentoDAONativeSQL.FILTRO_CD_REFATURAMENTO);
			}
			
			if(statusChamada.equals("Fat")){ //Chamadas A Faturar/Faturado
				mapper.appendSQL(SccChamadasRefaturamentoDAONativeSQL.FILTRO_CD_STATUS_CDR);
			}

			mapper.addArgument("dtInicial", dtInicial);
			mapper.addArgument("dtFinal", dtFinal);

			mapper.addResultMap("mesReferencia", String.class);
			mapper.addResultMap("operadoraLD", String.class);
			mapper.addResultMap("operadoraClaro", String.class);
			mapper.addResultMap("tipoRefaturamento", String.class);
			mapper.addResultMap("statusChamada", String.class);
			mapper.addResultMap("quantidade", Integer.class);
			mapper.addResultMap("minutagem", Integer.class);
			mapper.addResultMap("totalBruto", Double.class);
			mapper.addResultMap("totalLiquido", Double.class);
			
			mapper.appendSQL(SccChamadasRefaturamentoDAONativeSQL.SQL_GROUP);
			
			if(statusChamada != null && !statusChamada.equals("")){
				if(statusChamada.equals("Fat")){ //Chamadas A Faturar/Faturado
					mapper.appendSQL(SccChamadasRefaturamentoDAONativeSQL.SQL_GROUP_FAT);
				}else{ //Chamadas Aceite/Rejeitado
					mapper.appendSQL(SccChamadasRefaturamentoDAONativeSQL.SQL_GROUP_REJ);
				}
			}
						 
			list = mapper.execute();
		} catch(Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
		return list;
	}
}
