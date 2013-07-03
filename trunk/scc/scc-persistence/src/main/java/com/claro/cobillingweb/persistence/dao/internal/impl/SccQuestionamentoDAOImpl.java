/**
 * 
 */
package com.claro.cobillingweb.persistence.dao.internal.impl;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.claro.cobillingweb.persistence.constants.CobillingConstants;
import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.impl.HibernateBasicDAOImpl;
import com.claro.cobillingweb.persistence.dao.internal.SccQuestionamentoDAO;
import com.claro.cobillingweb.persistence.dao.query.SccQuestionamentoSQL;
import com.claro.cobillingweb.persistence.entity.SccCdrQuestionamento;
import com.claro.cobillingweb.persistence.filtro.SccFiltroQuestionamento;
import com.claro.cobillingweb.persistence.view.SccQuestionamentoArquivoView;
import com.claro.cobillingweb.persistence.view.SccQuestionamentoPenalidadeView;
import com.claro.cobillingweb.persistence.view.SccQuestionamentoView;
import com.claro.cobillingweb.persistence.view.mapper.NativeSQLViewMapper;

/**
 * @author vagner.souza
 *
 */
@Repository
public class SccQuestionamentoDAOImpl extends HibernateBasicDAOImpl<SccQuestionamentoView> implements SccQuestionamentoDAO {


	@Override
	public Collection<SccQuestionamentoView> gerarCombo() throws DAOException {
		
		Collection<SccQuestionamentoView> colecao = null;
		try {
			
			Session session = getSessionFactory().getCurrentSession();
			
			NativeSQLViewMapper<SccQuestionamentoView> mapper = new NativeSQLViewMapper<SccQuestionamentoView>(session, SccQuestionamentoSQL.SQL_COMBO, SccQuestionamentoView.class);
			
			mapper.addResultMap("sqQuestionamento", Long.class);
			mapper.addResultMap("cdEotLD", String.class);
			mapper.addResultMap("descricaoEotLD", String.class);
			
			colecao = (Collection<SccQuestionamentoView>)mapper.execute();
			
		} catch(Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
		
		return colecao;
	}
	
	public Collection<SccQuestionamentoView> gerarComboQuestionamento() throws DAOException {

		Collection<SccQuestionamentoView> colecao = null;
		try {
			
			Session session = getSessionFactory().getCurrentSession();
			
			NativeSQLViewMapper<SccQuestionamentoView> mapper = new NativeSQLViewMapper<SccQuestionamentoView>(session, SccQuestionamentoSQL.SQL_COMBO_QUESTIONAMENTO, SccQuestionamentoView.class);
			
			mapper.addResultMap("sqQuestionamento", Long.class);
			mapper.addResultMap("cdEotLD", String.class);
			mapper.addResultMap("descricaoEotLD", String.class);
			
			colecao = (Collection<SccQuestionamentoView>)mapper.execute();
			
		} catch(Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
		
		return colecao;
		
	}
	
	public Collection<SccQuestionamentoView> gerarComboArquivo() throws DAOException {
		
		Collection<SccQuestionamentoView> colecao = null;
		try {
			
			Session session = getSessionFactory().getCurrentSession();
			
			NativeSQLViewMapper<SccQuestionamentoView> mapper = new NativeSQLViewMapper<SccQuestionamentoView>(session, SccQuestionamentoSQL.SQL_COMBO_ARQUIVO, SccQuestionamentoView.class);
			
			mapper.addResultMap("sqQuestionamento", Long.class);
			mapper.addResultMap("sqRemessaQuestionamento", Long.class);
			mapper.addResultMap("cdEotLD", String.class);
			mapper.addResultMap("descricaoEotLD", String.class);
			
			colecao = (Collection<SccQuestionamentoView>)mapper.execute();
			
		} catch(Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
		
		return colecao;

		
	}
	
	public Collection<SccQuestionamentoArquivoView> gerarRelatorioQuestionamentoArquivo(SccFiltroQuestionamento filtro) throws DAOException{
		
		Collection<SccQuestionamentoArquivoView> colecao = null;
		try {
			
			Session session = getSessionFactory().getCurrentSession();
			
			NativeSQLViewMapper<SccQuestionamentoArquivoView> mapper = new NativeSQLViewMapper<SccQuestionamentoArquivoView>(session, montarSqlQuestionamentoArquivo(filtro), SccQuestionamentoArquivoView.class);
			
			montarResultMapperArquivo(mapper);
			
			colecao = (Collection<SccQuestionamentoArquivoView>) mapper.execute();
			
		} catch(Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
		return colecao;
		
		
	}
	
	@Override
	public Collection<SccQuestionamentoView> gerarRelatorioQuestionamentoFinanceiro(SccFiltroQuestionamento filtro)	throws DAOException {
		
		Collection<SccQuestionamentoView> colecao = null;
		
		try {
			
			Session session = getSessionFactory().getCurrentSession();
			
			NativeSQLViewMapper<SccQuestionamentoView> mapper = new NativeSQLViewMapper<SccQuestionamentoView>(session, SccQuestionamentoSQL.SQL, SccQuestionamentoView.class);
			
			mapper.addArgument("dtRepasse", filtro.montarDataRepasse());

			if(filtro.getSqQuestionamento().longValue() != BasicDAO.GET_ALL){
				mapper.addArgument("sqQuestionamento", filtro.getSqQuestionamento(), SccQuestionamentoSQL.FILTRO_SQ_QUESTIONAMENTO);
			}
			
			if(StringUtils.isNotEmpty(filtro.getCdEOTClaro()) && !filtro.getCdEOTClaro().equals(BasicDAO.GET_ALL_STRING)){
				mapper.addArgument("cdEotClaro", filtro.getCdEOTClaro(), SccQuestionamentoSQL.FILTRO_CD_EOT_CLARO);
			}
			
			if(StringUtils.isNotEmpty(filtro.getCdEOTLD()) && !filtro.getCdEOTLD().equals(BasicDAO.GET_ALL_STRING)){
				mapper.addArgument("cdEotLd", filtro.getCdEOTLD(), SccQuestionamentoSQL.FILTRO_CD_EOT_LD);
			}
			
			mapper.appendSQL(SccQuestionamentoSQL.FILTRO_SQL_PARTE2);
			
			montarResultMapper(mapper);
			
			colecao = (Collection<SccQuestionamentoView>) mapper.execute();
			
		} catch(Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
		return colecao;
	}

	@Override
	public List<SccQuestionamentoView> getAll() throws DAOException {
		
		return null;
	}
	
	private void montarResultMapperArquivo(NativeSQLViewMapper<SccQuestionamentoArquivoView> mapper){
		
		mapper.addResultMap("sqQuestionamento", Long.class);
		mapper.addResultMap("sqRemessaQuestionamento", Long.class);
		mapper.addResultMap("cdEotLd", String.class);
		mapper.addResultMap("cdEotClaro", String.class);
		mapper.addResultMap("qtdAssinante", Long.class);
		mapper.addResultMap("qtdRegra", Long.class);
		mapper.addResultMap("qtdParametro", Long.class);
		mapper.addResultMap("qtdLogica", Long.class);
		mapper.addResultMap("qtdCdr", Long.class);
		mapper.addResultMap("minutosTarifados", Double.class);
		mapper.addResultMap("vlrLiquido", Double.class);
		mapper.addResultMap("noArquivo", String.class);
		
	}
	
	private void montarResultMapperChamadas(NativeSQLViewMapper<SccCdrQuestionamento> mapper){
		
		mapper.addResultMap("sqCdrQuestionamento", Long.class);
		mapper.addResultMap("sqQuestionamento", Long.class);
		mapper.addResultMap("cdEotClaro", String.class);
		mapper.addResultMap("cdEotLd", String.class);
		mapper.addResultMap("nuCdr", Long.class);
		mapper.addResultMap("sqRemessaQuestionamento", Long.class);
		mapper.addResultMap("sqRetornoQuestionamento", Long.class);
		mapper.addResultMap("nuRegistroArquivo", Long.class);
		mapper.addResultMap("nuMsisdnOrigem", String.class);
		mapper.addResultMap("dtChamada", Date.class);
		mapper.addResultMap("hrInicioChamada", Long.class);
		mapper.addResultMap("nuTelefoneDestino", String.class);
		mapper.addResultMap("miDuracaoTarifada", Double.class);
		mapper.addResultMap("vlLiquidoChamada", Double.class);
		mapper.addResultMap("vlBrutoChamada", Double.class);
		mapper.addResultMap("nuReclamacao", Integer.class);
		mapper.addResultMap("cdMotivoRejeicao", String.class);
		mapper.addResultMap("dtEvento", Date.class);
		mapper.addResultMap("dtReferencia", Date.class);
		mapper.addResultMap("dtRetorno", Date.class);
		mapper.addResultMap("dtPrazoQuestionamento", Date.class);
		mapper.addResultMap("dtLimiteQuestionamento", Date.class);
		mapper.addResultMap("dtPrazoConfirmacao", Date.class);
		mapper.addResultMap("dtLimiteConfirmacao", Date.class);
		mapper.addResultMap("vlPotencialClaro", Double.class);
		mapper.addResultMap("vlPotencialLd", Double.class);
		mapper.addResultMap("cdConfirmacaoQuestionamento", String.class);
		mapper.addResultMap("vlApuradoPenalidade", Double.class);
		mapper.addResultMap("vlAcertoInclusao", Double.class);
		mapper.addResultMap("dtCarga", Date.class);
		mapper.addResultMap("inResultadoAnalise", String.class);
		mapper.addResultMap("inTipoRegistro", String.class);
		
	}
	
	
	private void montarResultMapperPenalidade(NativeSQLViewMapper<SccQuestionamentoPenalidadeView> mapper){
		
		mapper.addResultMap("cdPosicao", String.class);
		mapper.addResultMap("txComentarioMotivo", String.class);
		mapper.addResultMap("qtCdrs", Long.class);
		mapper.addResultMap("qtMinutos", Double.class);
		mapper.addResultMap("vlLiquido", Double.class);
		mapper.addResultMap("vlBruto", Double.class);
		mapper.addResultMap("qtCdrsRefaturadas", Long.class);
		mapper.addResultMap("qtMinutosRefaturadas", Double.class);
		mapper.addResultMap("qtCdrsNaoRefaturadas", Long.class);
		mapper.addResultMap("qtMinutosNaoRefaturadas", Double.class);
		mapper.addResultMap("vlPenalidade", Double.class);
		mapper.addResultMap("inResultadoAnalise", String.class);
		
	}
	
	private void montarResultMapper(NativeSQLViewMapper<SccQuestionamentoView> mapper){
		
		mapper.addResultMap("cdEotLD", String.class);
		mapper.addResultMap("descricaoEotLD", String.class);
		mapper.addResultMap("cdEotClaro", String.class);
		mapper.addResultMap("sgUf", String.class);
		mapper.addResultMap("sqQuestionamento", Long.class);
		mapper.addResultMap("noArquivo", String.class);
		mapper.addResultMap("qtdCdrQuest", Long.class);
		mapper.addResultMap("miReaisQuest", Double.class);
		mapper.addResultMap("miArredondadosQuest", Double.class);
		mapper.addResultMap("vlrLiquidoQuest", Double.class);
		mapper.addResultMap("qtdCdrCsa", Long.class);
		mapper.addResultMap("miReaisCsa", Double.class);
		mapper.addResultMap("miArredondadosCsa", Double.class);
		mapper.addResultMap("vlrLiquidoCsa", Double.class);
		mapper.addResultMap("qtdCdrCcap", Long.class);
		mapper.addResultMap("miReaisCcap", Double.class);
		mapper.addResultMap("miArredondadosCcap", Double.class);
		mapper.addResultMap("vlrLiquidoCcap", Double.class);
		mapper.addResultMap("qtdCdrCanp", Long.class);
		mapper.addResultMap("miReaisCanp", Double.class);
		mapper.addResultMap("miArredondadosCanp", Double.class);
		mapper.addResultMap("vlrLiquidoCanp", Double.class);
		mapper.addResultMap("qtdCdrClaro", Long.class);
		mapper.addResultMap("miReaisClaro", Double.class);
		mapper.addResultMap("miArredondadosClaro", Double.class);
		mapper.addResultMap("vlrLiquidoClaro", Double.class);
		mapper.addResultMap("qtdCdrLd", Long.class);
		mapper.addResultMap("miReaisLd", Double.class);
		mapper.addResultMap("miArredondadosLd", Double.class);
		mapper.addResultMap("vlrLiquidoLd", Double.class);
		mapper.addResultMap("sqArquivo", Long.class);
		
	}
	
	
	public Collection<SccQuestionamentoPenalidadeView> gerarRelatorioQuestionamentoPenalidade(SccFiltroQuestionamento filtro) throws DAOException{
		
		Collection<SccQuestionamentoPenalidadeView> colecao = null;
		
		try {
			
			Session session = getSessionFactory().getCurrentSession();
			NativeSQLViewMapper<SccQuestionamentoPenalidadeView> mapper = new NativeSQLViewMapper<SccQuestionamentoPenalidadeView>(session, montarQuery(filtro), SccQuestionamentoPenalidadeView.class);
			montarResultMapperPenalidade(mapper);
			colecao = (Collection<SccQuestionamentoPenalidadeView>) mapper.execute();

		} catch (Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
		return colecao;
	}
	
	public Collection<SccCdrQuestionamento> pesquisarAnaliseChamadas(Long sqQuestionamento, Long sqRemessaQuestionamento) throws DAOException {
		
		Collection<SccCdrQuestionamento> colecao = null;
		
		try{
			
			Session session = getSessionFactory().getCurrentSession();
			NativeSQLViewMapper<SccCdrQuestionamento> mapper = new NativeSQLViewMapper<SccCdrQuestionamento>(session, montarQueryPesquisaChamada(sqQuestionamento, sqRemessaQuestionamento), SccCdrQuestionamento.class);
			montarResultMapperChamadas(mapper);
			colecao = (Collection<SccCdrQuestionamento>) mapper.execute();
			
		}catch (Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
		
		return colecao;
	}
	
	private String montarQueryPesquisaChamada(Long sqQuestionamento, Long sqRemessaQuestionamento) throws DAOException{
		
		StringBuilder queryStr = new StringBuilder();
		try {
			
			if(sqQuestionamento != null && sqRemessaQuestionamento != null){

				queryStr.append("        SELECT SQ_CDR_QUESTIONAMENTO,   ");
				queryStr.append("               SQ_QUESTIONAMENTO,   ");
				queryStr.append("               CD_EOT_CLARO,   ");
				queryStr.append("               CD_EOT_LD,   ");
				queryStr.append("               NU_CDR,   ");
				queryStr.append("               SQ_REMESSA_QUESTIONAMENTO,   ");
				queryStr.append("               SQ_RETORNO_QUESTIONAMENTO,   ");
				queryStr.append("               NU_REGISTRO_ARQUIVO,   ");
				queryStr.append("               NU_MSISDN_ORIGEM,   ");
				queryStr.append("               DT_CHAMADA,   ");
				queryStr.append("               HR_INICIO_CHAMADA,   ");
				queryStr.append("               NU_TELEFONE_DESTINO,   ");
				queryStr.append("               MI_DURACAO_TARIFADA,   ");
				queryStr.append("               VL_LIQUIDO_CHAMADA,   ");
				queryStr.append("               VL_BRUTO_CHAMADA,   ");
				queryStr.append("               NU_RECLAMACAO,   ");
				queryStr.append("               CD_MOTIVO_REJEICAO,   ");
				queryStr.append("               DT_EVENTO,   ");
				queryStr.append("               DT_REFERENCIA,   ");
				queryStr.append("               DT_RETORNO,   ");
				queryStr.append("               DT_PRAZO_QUESTIONAMENTO,   ");
				queryStr.append("               DT_LIMITE_QUESTIONAMENTO,   ");
				queryStr.append("               DT_PRAZO_CONFIRMACAO,   ");
				queryStr.append("               DT_LIMITE_CONFIRMACAO,   ");
				queryStr.append("               VL_POTENCIAL_CLARO,   ");
				queryStr.append("               VL_POTENCIAL_LD,   ");
				queryStr.append("               CD_CONFIRMACAO_QUESTIONAMENTO,   ");
				queryStr.append("               VL_APURADO_PENALIDADE,   ");
				queryStr.append("               VL_ACERTO_INCLUSAO,   ");
				queryStr.append("               DT_CARGA,   ");
				queryStr.append("               NVL(IN_RESULTADO_ANALISE,' '),   ");
				queryStr.append("               IN_TIPO_REGISTRO   ");
				queryStr.append("        FROM    SCC_CDR_QUESTIONAMENTO CDR   ");
				queryStr.append("        WHERE  SQ_QUESTIONAMENTO = "+ sqQuestionamento );
				queryStr.append("        AND    SQ_REMESSA_QUESTIONAMENTO = "+sqRemessaQuestionamento   );
				queryStr.append("        AND   CDR.CD_CONFIRMACAO_QUESTIONAMENTO NOT IN ('DVN','QNA') AND        CDR.NU_CDR > 0   ");
				
			}
			
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
		
		return queryStr.toString();
	}
	
	private String montarQuery(SccFiltroQuestionamento filtro){
		
     	StringBuilder queryStr = new StringBuilder();
        queryStr.append("SELECT LPAD(TO_CHAR( DECODE(MR.TX_COMENTARIO_MOTIVO,104,3,  \n");
        queryStr.append("        DECODE(MR.TX_COMENTARIO_MOTIVO,144,4,  \n");
        queryStr.append("        DECODE(MR.TX_COMENTARIO_MOTIVO,146,5,  \n");
        queryStr.append("        DECODE(MR.TX_COMENTARIO_MOTIVO,145,6,  \n");
        queryStr.append("        DECODE(MR.TX_COMENTARIO_MOTIVO,106,7,  \n");
        queryStr.append("        DECODE(MR.TX_COMENTARIO_MOTIVO,105,8,  \n");
        queryStr.append("        DECODE(MR.TX_COMENTARIO_MOTIVO,121,9,  \n");
        queryStr.append("        DECODE(MR.TX_COMENTARIO_MOTIVO,100,10,  \n");
        queryStr.append("        DECODE(MR.TX_COMENTARIO_MOTIVO,102,11,  \n");
        queryStr.append("        DECODE(MR.TX_COMENTARIO_MOTIVO,103,12,  \n");
        queryStr.append("        DECODE(MR.TX_COMENTARIO_MOTIVO,117,13,  \n");
        queryStr.append("        DECODE(MR.TX_COMENTARIO_MOTIVO,122,14,  \n");
        queryStr.append("        DECODE(MR.TX_COMENTARIO_MOTIVO,120,15,  \n");
        queryStr.append("        DECODE(MR.TX_COMENTARIO_MOTIVO,131,16,  \n");
        queryStr.append("        DECODE(MR.TX_COMENTARIO_MOTIVO,101,17,  \n");
        queryStr.append("        DECODE(MR.TX_COMENTARIO_MOTIVO,118,18,  \n");
        queryStr.append("        DECODE(MR.TX_COMENTARIO_MOTIVO,123,19,  \n");
        queryStr.append("        DECODE(MR.TX_COMENTARIO_MOTIVO,124,20,  \n");
        queryStr.append("        DECODE(MR.TX_COMENTARIO_MOTIVO,147,21,  \n");
        queryStr.append("        DECODE(MR.TX_COMENTARIO_MOTIVO,126,22,  \n");
        queryStr.append("        DECODE(MR.TX_COMENTARIO_MOTIVO,127,23,  \n");
        queryStr.append("        DECODE(MR.TX_COMENTARIO_MOTIVO,128,24,  \n");
        queryStr.append("        DECODE(MR.TX_COMENTARIO_MOTIVO,129,25,  \n");
        queryStr.append("        DECODE(MR.TX_COMENTARIO_MOTIVO,130,26,  \n");
        queryStr.append("        DECODE(MR.TX_COMENTARIO_MOTIVO,132,27,  \n");
        queryStr.append("        DECODE(MR.TX_COMENTARIO_MOTIVO,142,28,  \n");
        queryStr.append("        DECODE(MR.TX_COMENTARIO_MOTIVO,119,29,  \n");
        queryStr.append("        DECODE(MR.TX_COMENTARIO_MOTIVO,149,30,  \n");
        queryStr.append("        DECODE(MR.TX_COMENTARIO_MOTIVO,148,31,  \n");
        queryStr.append("        DECODE(MR.TX_COMENTARIO_MOTIVO,138,32,  \n");
        queryStr.append("        DECODE(MR.TX_COMENTARIO_MOTIVO,133,33,  \n");
        queryStr.append("        DECODE(MR.TX_COMENTARIO_MOTIVO,137,34,  \n");
        queryStr.append("        DECODE(MR.TX_COMENTARIO_MOTIVO,136,35,  \n");
        queryStr.append("        DECODE(MR.TX_COMENTARIO_MOTIVO,140,36,  \n");
        queryStr.append("        DECODE(MR.TX_COMENTARIO_MOTIVO,141,37,  \n");
        queryStr.append("        DECODE(MR.TX_COMENTARIO_MOTIVO,109,40,  \n");
        queryStr.append("        DECODE(MR.TX_COMENTARIO_MOTIVO,143,41,  \n");
        queryStr.append("        DECODE(MR.TX_COMENTARIO_MOTIVO,125,42,  \n");
        queryStr.append("        DECODE(MR.TX_COMENTARIO_MOTIVO,150,43,  \n");
        queryStr.append("        DECODE(MR.TX_COMENTARIO_MOTIVO,134,44,  \n");
        queryStr.append("        DECODE(MR.TX_COMENTARIO_MOTIVO,135,45,  \n");
        queryStr.append("        DECODE(MR.TX_COMENTARIO_MOTIVO,107,46,  \n");
        queryStr.append("        DECODE(MR.TX_COMENTARIO_MOTIVO,372,49,  \n");
        queryStr.append("        DECODE(MR.TX_COMENTARIO_MOTIVO,110,50,  \n");        
        queryStr.append("        DECODE(MR.TX_COMENTARIO_MOTIVO,373,51,99  \n");        
        queryStr.append("        ))))))))))))))))))))))))))))))))))))))))))))) ),2,'0') AS POSICAO,   \n");
        queryStr.append("        MR.TX_COMENTARIO_MOTIVO,   \n");
        queryStr.append("        COUNT(1) AS QT_CDRS,   \n");
        queryStr.append("        SUM(NVL(CDR.MI_DURACAO_TARIFADA,0)) AS QT_MINUTOS,   \n");
        queryStr.append("        SUM(NVL(CDR.VL_LIQUIDO_CHAMADA,0)) AS VL_LIQUIDO,  \n");
        queryStr.append("        SUM(NVL(CDR.VL_BRUTO_CHAMADA,0)) AS VL_BRUTO,        \n");
        queryStr.append("        0 ,   \n");
        queryStr.append("        0 ,   \n");
        queryStr.append("        0 ,   \n");
        queryStr.append("        0 ,   \n");
        queryStr.append("        SUM(NVL(CDR.VL_APURADO_PENALIDADE,0)) AS VL_APURADO,         \n");
        queryStr.append("        DECODE(CDR.IN_RESULTADO_ANALISE,'N','N','P') AS PROCEDENTE  \n");
        queryStr.append("FROM (SELECT TX_COMENTARIO_MOTIVO   \n");
        queryStr.append("       FROM SCC_MOTIVO_REJEICAO   \n");
        queryStr.append("       WHERE TX_COMENTARIO_MOTIVO >= '000'  \n");
        queryStr.append("       AND TX_COMENTARIO_MOTIVO <= '999'  \n");
        queryStr.append("       AND IN_TIPO_CLASSIFICACAO IS NULL  \n");
        queryStr.append("       ) MR,   \n");
        queryStr.append("      (SELECT SQ_QUESTIONAMENTO, CD_MOTIVO_REJEICAO, MI_DURACAO_TARIFADA, VL_APURADO_PENALIDADE, IN_RESULTADO_ANALISE, VL_LIQUIDO_CHAMADA, VL_BRUTO_CHAMADA   \n");
        queryStr.append("       FROM SCC_CDR_QUESTIONAMENTO  CQ \n");
        queryStr.append("       WHERE 1 = 1");
        if(StringUtils.isNotEmpty(filtro.getCdEOTLD()) && !filtro.getCdEOTLD().equals(BasicDAO.GET_ALL_STRING)){
        	queryStr.append("             AND CQ.CD_EOT_LD = '"+ filtro.getCdEOTLD() +"' \n");
        }
        if (StringUtils.isNotEmpty(filtro.getCdEOTClaro()) && !filtro.getCdEOTClaro().equals(BasicDAO.GET_ALL_STRING)) {
	        queryStr.append("      AND CQ.CD_EOT_CLARO = '"+filtro.getCdEOTClaro() +"' \n");
        }
        queryStr.append("       AND CQ.SQ_QUESTIONAMENTO IN   \n");
        queryStr.append("      (SELECT DISTINCT RQ.SQ_QUESTIONAMENTO   \n");
        queryStr.append("       FROM SCC_RESULTADO_QUESTIONAMENTO RQ   \n");
        queryStr.append("       WHERE 1=1 ");
        if (StringUtils.isNotEmpty(filtro.getCdEOTLD()) && !filtro.getCdEOTLD().equals(BasicDAO.GET_ALL_STRING)) {
        	queryStr.append("          AND RQ.CD_EOT_LD = '"+ filtro.getCdEOTLD() +"' \n");
        }
        if (StringUtils.isNotEmpty(filtro.getCdEOTClaro()) && !filtro.getCdEOTLD().equals(BasicDAO.GET_ALL_STRING)) {
	        queryStr.append("      AND RQ.CD_EOT_CLARO = '"+ filtro.getCdEOTClaro() +"' \n" );
        }
        queryStr.append("       AND TO_CHAR(RQ.DT_REPASSE,'MMYYYY') = '"+ filtro.montarDataRepasse() +"')  \n");
        queryStr.append("       AND IN_RESULTADO_ANALISE = '"+ filtro.getInResultadoAnalise() +"' ) CDR  \n ");
        queryStr.append(" WHERE MR.TX_COMENTARIO_MOTIVO = CDR.CD_MOTIVO_REJEICAO(+)  \n");
        queryStr.append(" GROUP BY MR.TX_COMENTARIO_MOTIVO  \n");
        queryStr.append("       , CDR.IN_RESULTADO_ANALISE  \n");
        queryStr.append("ORDER BY POSICAO  \n");                
        
        return queryStr.toString();
		
	}
	
	private String montarSqlQuestionamentoArquivo(SccFiltroQuestionamento filtro){
		
        StringBuilder queryStr = new StringBuilder(
        		"SELECT DISTINCT CDR.SQ_QUESTIONAMENTO, \n" +
        		"       CDR.SQ_REMESSA_QUESTIONAMENTO, \n" +
        		"       CDR.CD_EOT_LD, \n" +
        		"       CDR.CD_EOT_CLARO, \n" +
        		"       NVL(ASSINANTE.QT_ASSINANTE,0), \n" +
        		"       NVL(REGRA.QT_REGRA,0), \n" +
        		"       NVL(PARAMETRO.QT_PARAMETRO,0), \n" +
        		"       NVL(LOGICA.QT_LOGICA,0), \n" +
        		"       NVL(QUANTIDADE.QT_CDR,0), \n" +
        		"       NVL(MINUTOS.MI_DURACAO_TARIFADA,0), \n" +
        		"       NVL(VALOR.VL_LIQUIDO_CHAMADA,0), \n" +
        		"       AC.NO_ARQUIVO \n" +
        		"  FROM SCC_CDR_QUESTIONAMENTO CDR, \n" +
        		"		(SELECT COUNT(1) QT_ASSINANTE, CDR.SQ_REMESSA_QUESTIONAMENTO \n" +
        		"		FROM   SCC_CDR_QUESTIONAMENTO CDR\n" +
        		"		WHERE  1=1 \n"
		);
        if (filtro.getSqQuestionamento() != null && filtro.getSqQuestionamento() >0) {
	        queryStr.append(
		        "		AND   CDR.SQ_QUESTIONAMENTO = "+ filtro.getSqQuestionamento() +" \n"
	        );
        }
        queryStr.append(
        		"		AND    CDR.CD_MOTIVO_REJEICAO \n" + CobillingConstants.LIST_ASSINANTE +
        		"		AND   CDR.CD_CONFIRMACAO_QUESTIONAMENTO NOT IN ('DVN', 'QNA') AND	CDR.NU_CDR > 0  \n" +
        		"		GROUP BY CDR.SQ_REMESSA_QUESTIONAMENTO ) ASSINANTE, \n" +
        		"		(SELECT COUNT(1) QT_REGRA, CDR.SQ_REMESSA_QUESTIONAMENTO \n" +      
        		"		FROM   SCC_CDR_QUESTIONAMENTO CDR \n" +      
        		"		WHERE  1=1 \n"
		);
        if (filtro.getSqQuestionamento() != null &&  filtro.getSqQuestionamento()>0) {
	        queryStr.append(
		        "		AND   CDR.SQ_QUESTIONAMENTO = "+ filtro.getSqQuestionamento() +" \n"
	        );
        }
        queryStr.append(
        		"		AND    CDR.CD_MOTIVO_REJEICAO \n" +  CobillingConstants.LIST_REGRA +      
        		"		AND   CDR.CD_CONFIRMACAO_QUESTIONAMENTO NOT IN ('DVN','QNA') " +
        		"       AND	CDR.NU_CDR > 0  \n" +
        		"		GROUP BY CDR.SQ_REMESSA_QUESTIONAMENTO ) REGRA, \n" +      
        		"		(SELECT COUNT(1) QT_PARAMETRO, CDR.SQ_REMESSA_QUESTIONAMENTO \n" +             
        		"		FROM   SCC_CDR_QUESTIONAMENTO CDR \n" +      
        		"		WHERE  1=1 \n"
		);
        if (filtro.getSqQuestionamento() != null && filtro.getSqQuestionamento()>0) {
	        queryStr.append(
		        "		AND   CDR.SQ_QUESTIONAMENTO = "+ filtro.getSqQuestionamento() +" \n"
	        );
        }
        queryStr.append(
        		"		AND    CDR.CD_MOTIVO_REJEICAO \n" +  CobillingConstants.LIST_PARAMETRO +      
        		"		AND   CDR.CD_CONFIRMACAO_QUESTIONAMENTO NOT IN ('DVN','QNA') AND	CDR.NU_CDR > 0  \n" +
        		"		GROUP BY CDR.SQ_REMESSA_QUESTIONAMENTO ) PARAMETRO, \n" +      
        		"		(SELECT COUNT(1) QT_LOGICA, CDR.SQ_REMESSA_QUESTIONAMENTO \n" +            
        		"		FROM   SCC_CDR_QUESTIONAMENTO CDR \n" +      
        		"		WHERE  1=1 \n"
		);
        if (filtro.getSqQuestionamento() != null && filtro.getSqQuestionamento()>0) {
	        queryStr.append(
		        "		AND   CDR.SQ_QUESTIONAMENTO = "+ filtro.getSqQuestionamento() +" \n"
	        );
        }
        queryStr.append(
        		"		AND    CDR.CD_MOTIVO_REJEICAO \n" +  CobillingConstants.LIST_LOGICA +      
        		"		AND   CDR.CD_CONFIRMACAO_QUESTIONAMENTO NOT IN ('DVN','QNA') AND	CDR.NU_CDR > 0  \n" +
        		"		GROUP BY CDR.SQ_REMESSA_QUESTIONAMENTO ) LOGICA, \n" +      
        		"		(SELECT COUNT(1) QT_CDR, CDR.SQ_REMESSA_QUESTIONAMENTO \n" +            
        		"		FROM   SCC_CDR_QUESTIONAMENTO CDR \n" +      
        		"		WHERE  1=1 \n"
		);
        if (filtro.getSqQuestionamento() != null && filtro.getSqQuestionamento()>0) {
	        queryStr.append(
		       	"		AND   CDR.SQ_QUESTIONAMENTO = "+ filtro.getSqQuestionamento() +" \n"
	        );
        }
        queryStr.append(
        		//"		AND   CDR.CD_CONFIRMACAO_QUESTIONAMENTO NOT IN ('DVN','QNA') AND	CDR.NU_CDR > 0  \n" +
        		"		GROUP BY CDR.SQ_REMESSA_QUESTIONAMENTO ) QUANTIDADE, \n" +      
        		"		(SELECT SUM(CDR.MI_DURACAO_TARIFADA) MI_DURACAO_TARIFADA, CDR.SQ_REMESSA_QUESTIONAMENTO \n" +      
        		"		FROM   SCC_CDR_QUESTIONAMENTO CDR \n" +      
        		"		WHERE  1=1 \n"
		);
        if (filtro.getSqQuestionamento() != null && filtro.getSqQuestionamento()>0) {
	        queryStr.append(
		       	"		AND   CDR.SQ_QUESTIONAMENTO = "+ filtro.getSqQuestionamento() +" \n"
	        );
        }
        queryStr.append(
        		"		AND   CDR.CD_CONFIRMACAO_QUESTIONAMENTO NOT IN ('DVN','QNA') AND	CDR.NU_CDR > 0  \n" +
        		"		GROUP BY CDR.SQ_REMESSA_QUESTIONAMENTO ) MINUTOS, \n" +             
        		"		(SELECT SUM(CDR.VL_LIQUIDO_CHAMADA) VL_LIQUIDO_CHAMADA, CDR.SQ_REMESSA_QUESTIONAMENTO \n" +      
        		"		FROM   SCC_CDR_QUESTIONAMENTO CDR \n" +      
        		"		WHERE  1=1 \n"
		);
        if (filtro.getSqQuestionamento() != null && filtro.getSqQuestionamento()>0) {
	        queryStr.append(
		       	"		AND   CDR.SQ_QUESTIONAMENTO = "+ filtro.getSqQuestionamento() +" \n"
	        );
        }
        queryStr.append(
        		"		AND   CDR.CD_CONFIRMACAO_QUESTIONAMENTO NOT IN ('DVN','QNA') AND	CDR.NU_CDR > 0  \n" +
        		"		GROUP BY CDR.SQ_REMESSA_QUESTIONAMENTO ) VALOR, \n" +             
        		"		(SELECT SQ_ARQUIVO, NO_ARQUIVO \n" +      
        		"		FROM SCC_ARQUIVO_COBILLING \n" +      
        		"		WHERE NO_ARQUIVO LIKE 'TCQE.T%' \n" +
        		"		AND CD_TIPO_ARQUIVO = 105) AC \n" +
        		" WHERE CDR.SQ_REMESSA_QUESTIONAMENTO = AC.SQ_ARQUIVO \n" +
        		" AND   CDR.CD_CONFIRMACAO_QUESTIONAMENTO NOT IN ('DVN') AND	CDR.NU_CDR > 0  \n"
		);
        if (filtro.getSqQuestionamento() != null && filtro.getSqQuestionamento()>0) {
	        queryStr.append(
		       	" AND CDR.SQ_QUESTIONAMENTO = "+ filtro.getSqQuestionamento() +" \n"
	        );
        }
        queryStr.append(
        		"		AND   CDR.SQ_REMESSA_QUESTIONAMENTO = ASSINANTE.SQ_REMESSA_QUESTIONAMENTO(+) \n" +
        		" AND  	CDR.SQ_REMESSA_QUESTIONAMENTO = REGRA.SQ_REMESSA_QUESTIONAMENTO(+) \n" +
        		" AND  	CDR.SQ_REMESSA_QUESTIONAMENTO = PARAMETRO.SQ_REMESSA_QUESTIONAMENTO(+) \n" +
        		" AND  	CDR.SQ_REMESSA_QUESTIONAMENTO = LOGICA.SQ_REMESSA_QUESTIONAMENTO(+) \n" +
        		" AND  	CDR.SQ_REMESSA_QUESTIONAMENTO = QUANTIDADE.SQ_REMESSA_QUESTIONAMENTO(+) \n" +
        		" AND  	CDR.SQ_REMESSA_QUESTIONAMENTO = MINUTOS.SQ_REMESSA_QUESTIONAMENTO (+) \n" +
        		" AND  	CDR.SQ_REMESSA_QUESTIONAMENTO = VALOR.SQ_REMESSA_QUESTIONAMENTO(+) \n"
        );
        return queryStr.toString();
	}


}
