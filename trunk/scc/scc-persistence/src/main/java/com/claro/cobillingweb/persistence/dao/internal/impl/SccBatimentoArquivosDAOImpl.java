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
import com.claro.cobillingweb.persistence.dao.internal.SccBatimentoArquivosDAO;
import com.claro.cobillingweb.persistence.dao.query.SccBatimentoArquivosDAONativeSQL;
import com.claro.cobillingweb.persistence.view.SccBatimentoArquivosView;
import com.claro.cobillingweb.persistence.view.mapper.NativeSQLViewMapper;

/**
 * @author 93046251
 *
 */
public class SccBatimentoArquivosDAOImpl extends HibernateBasicDAOImpl<SccBatimentoArquivosView>
		implements SccBatimentoArquivosDAO {

	@Override
	public List<SccBatimentoArquivosView> getAll() throws DAOException {
		
		return null;
	}

	
	
	@Override
	public List<SccBatimentoArquivosView> gerarRelatorioBatimento(Date dtInicioBatimento, Date dtFimBatimento, 
			String cdEOTLD, String cdEOTClaro, String tpArquivo) throws DAOException {
		
		List<SccBatimentoArquivosView> list = null;
		try {
			Session session = getSessionFactory().getCurrentSession();
			NativeSQLViewMapper<SccBatimentoArquivosView> mapper = new NativeSQLViewMapper<SccBatimentoArquivosView>(session, SccBatimentoArquivosDAONativeSQL.SQL, SccBatimentoArquivosView.class);
			if(dtInicioBatimento != null) {
				mapper.addArgument("dtInicioBatimento", dtInicioBatimento, SccBatimentoArquivosDAONativeSQL.FILTRO_DT_INICIO_BATIMENTO);
			}
			
			if(dtFimBatimento != null) {
				mapper.addArgument("dtFimBatimento", dtFimBatimento, SccBatimentoArquivosDAONativeSQL.FILTRO_DT_FIM_BATIMENTO);
			}
			
			if(cdEOTClaro != null && !cdEOTClaro.equals(BasicDAO.GET_ALL_STRING)){
				mapper.addArgument("cdEOTClaro", cdEOTClaro, SccBatimentoArquivosDAONativeSQL.FILTRO_CDEOTCLARO);
			}
			
			if(cdEOTLD != null && !cdEOTLD.equals(BasicDAO.GET_ALL_STRING)){
				mapper.addArgument("cdEOTLD", cdEOTLD, SccBatimentoArquivosDAONativeSQL.FILTRO_CDEOTLD);
			}
			
			mapper.addArgument("tpArquivo", tpArquivo, SccBatimentoArquivosDAONativeSQL.FILTRO_TIPO_BATIMENTO);
			
			//Claro
			mapper.addResultMap("dtConnectClaro", Date.class);
			mapper.addResultMap("dtReferenciaClaro",Date.class);
			mapper.addResultMap("nomeArquivoClaro", String.class);
			mapper.addResultMap("dnsProtocoloClaro", String.class);
			mapper.addResultMap("duracaoTarifadaClaro", Double.class);
			mapper.addResultMap("quantidadeClaro", Double.class);
			mapper.addResultMap("valorLiquidoClaro", Double.class);
			mapper.addResultMap("erroProtocoloClaro", String.class);
			mapper.addResultMap("descErroProtocoloClaro", String.class);
			//LD
			mapper.addResultMap("nomeArquivoLD", String.class);
			mapper.addResultMap("duracaoTarifadaLD", Double.class);
			mapper.addResultMap("quantidadeLD", Double.class);
			mapper.addResultMap("valorLiquidoLD", Double.class);
			mapper.addResultMap("statusLD", String.class);
			//Resultado do Batimento
			
			mapper.addResultMap("duracaoTarifadaBat", Double.class);
			mapper.addResultMap("quantidadeBat", Double.class);
			mapper.addResultMap("valorLiquidoBat", Double.class);
			
			list = mapper.execute();
		} catch(Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
		return list;
	}

	
}
