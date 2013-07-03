package com.claro.cobillingweb.persistence.dao.query;

public class SccContratoCobillingDAONativeSQL {

	
	public static final String PESQUISA_OPERADORAS_SQL="SELECT OPLD.Ds_Operadora as LD, "+
" OPCL.Ds_Operadora as Claro, "+
			" CC.CD_EOT_LD,  "+
			" CC.CD_EOT_CLARO,  "+
			" CC. DT_INICIO_CONTRATO, "+ 
			" CC.DT_FIM_CONTRATO,   "+
		    " DECODE(CC.CD_TIPO_CONTRATO,'A', 'ARRECADADO', "+
	        " DECODE(CC.CD_TIPO_CONTRATO,'P', 'PRE PAGO', "+
			" DECODE(CC.CD_TIPO_CONTRATO,'F', 'NOVO CONTRATO'))), "+ 
			" CC.DS_CRITERIO_CUSTO, "+
			" CC.VL_ASSOCIADO_CRITERIO_CUSTO, "+
			" CC.DS_PERIODO_REPASSE, "+
			" CC.VL_CPMF, "+
			" CC.VL_ISS, "+
			" COUNT(REPASSE.QTDE), "+ 
			"      CC.VL_CRITERIO_CUSTO_LIQUIDO, "+ 
			"      CC.PE_RETENCAO,    "+ 
			"      CC.DT_CRIACAO ,  "+
			"      CC.DT_ATUALIZACAO   ,"+
			"      CC.CD_USUARIO_MANUT,   "+
			"      OPLD.DS_OPERADORA,  "+
			"      OPCL.DS_OPERADORA, "+
			"      TCC.DS_TIPO_CONTRATO, "+ 
			"      CC.PE_COFINS,  "+
			"      CC.PE_PIS,  "+
			"      CC.FL_REPASSA_ICMS, "+ 
			"      CC.FL_REPASSA_CPMF  "+
			" FROM SCC_CONTRATO_COBILLING CC, "+
			"    SCC_OPERADORA OPLD,  "+
			"    SCC_OPERADORA OPCL,  "+
			"    SCC_TIPO_CONTRATO TCC,  "+
			"    (SELECT CCC.CD_EOT_LD,  "+
			"    CCC.CD_EOT_CLARO,   "+
			"    CCC.DT_INICIO_CONTRATO, "+ 
			"    CCC.CD_TIPO_CONTRATO,   "+
			"    COUNT(*) AS QTDE  "+
			"    FROM SCC_CONTRATO_COBILLING CCC, "+ 
			"    SCC_TIPO_CONTRATO TC,  "+
			"    SCC_ARQUIVO_COBILLING AC, "+ 
			"    SCC_FECHAMENTO_SUMARIZADO FS, "+ 
			"    SCC_PRE_FECHAMENTO R  "+
			"    WHERE CCC.CD_EOT_LD = R.CD_EOT_LD "+ 
			"    AND CCC.CD_EOT_CLARO = R.CD_EOT_CLARO "+ 
			"    AND CCC.CD_EOT_LD = FS.CD_EOT_LD  "+
			"    AND CCC.CD_EOT_CLARO = FS.CD_EOT_CLARO "+ 
			"    AND CCC.CD_EOT_LD = AC.CD_EOT_LD  "+
			"    AND CCC.CD_EOT_CLARO = AC.CD_EOT_CLARO "+ 
			"    AND CCC.CD_TIPO_CONTRATO = TC.CD_TIPO_CONTRATO "+ 
			"    AND FS.SQ_ARQUIVO = AC.SQ_ARQUIVO "+
			"    GROUP BY CCC.CD_EOT_LD,   "+
			"    CCC.CD_EOT_CLARO,   "+
			"    CCC.DT_INICIO_CONTRATO, "+  
			"    CCC.CD_TIPO_CONTRATO) REPASSE "+ 
			" WHERE CC.CD_EOT_LD = REPASSE.CD_EOT_LD(+) "+ 
			" AND CC.CD_EOT_CLARO = REPASSE.CD_EOT_CLARO(+) "+ 
			" AND CC.CD_TIPO_CONTRATO = REPASSE.CD_TIPO_CONTRATO(+) "+ 
			" AND CC.DT_INICIO_CONTRATO = REPASSE.DT_INICIO_CONTRATO(+) "+ 
			" AND CC.CD_EOT_LD = OPLD.CD_EOT  "+
			" AND CC.CD_EOT_CLARO = OPCL.CD_EOT "+ 
			" AND CC.CD_TIPO_CONTRATO = TCC.CD_TIPO_CONTRATO ";             
			//" AND CC.CD_TIPO_CONTRATO = 'P' ";
	
	
	public static final String  PESQUISA_OPERADORAS_PROJECTIONS = " GROUP BY CC.CD_EOT_LD,  "+
			"        CC.CD_EOT_CLARO,  "+
			"        CC.DT_INICIO_CONTRATO, "+ 
			"        CC.DT_FIM_CONTRATO,  "+
			"        CC.CD_TIPO_CONTRATO, "+
			"        CC.PE_RETENCAO,  "+
			"        CC.DS_CRITERIO_CUSTO, "+
			"        CC.VL_ASSOCIADO_CRITERIO_CUSTO, "+ 
			"        CC.VL_CRITERIO_CUSTO_LIQUIDO,  "+
			"        CC.DS_PERIODO_REPASSE,  "+
			"        CC.VL_CPMF,  "+
			"        CC.VL_ISS,  "+
			"        CC.DT_CRIACAO, "+ 
			"        CC.DT_ATUALIZACAO, "+ 
			"        CC.CD_USUARIO_MANUT,  "+        		      
			"   		OPLD.DS_OPERADORA,   "+
			"      	OPCL.DS_OPERADORA,  "+
			"        TCC.DS_TIPO_CONTRATO, "+ 
			"        CC.PE_COFINS,  "+
			"        CC.PE_PIS,  "+
			"        CC.FL_REPASSA_ICMS, "+ 
			"        CC.FL_REPASSA_CPMF  ";
	
	
	public static final String PESQUISA_PRODUTO_CONTRATO =

"			SELECT CC.CD_EOT_LD,  "+
"		       CC.CD_EOT_CLARO,  "+
"		       CC. DT_INICIO_CONTRATO,   "+
"		       CC.DT_FIM_CONTRATO,   "+
"		       CC.CD_TIPO_CONTRATO,   "+
"		       CC.PE_RETENCAO,   "+
"		       CC.DS_CRITERIO_CUSTO,  "+
"		       CC.VL_ASSOCIADO_CRITERIO_CUSTO,   "+
"		       CC.VL_CRITERIO_CUSTO_LIQUIDO,  "+
"		       CC.DS_PERIODO_REPASSE,  "+
"		       CC.VL_CPMF,  "+
"		       CC.VL_ISS,  "+
"		       TO_CHAR(CC.DT_CRIACAO,'DD/MM/YYYY') AS CRIACAO_DT,  "+
"		       TO_CHAR(CC.DT_ATUALIZACAO,'DD/MM/YYYY') AS ATUALIZACAO_DT,  "+
"		       CC.CD_USUARIO_MANUT,  "+
"		       COUNT(REPASSE.QTDE), "+
"		       OPLD.DS_OPERADORA, "+
"		       OPCL.DS_OPERADORA, "+
"		       TCC.DS_TIPO_CONTRATO, "+
"		       CC.PE_COFINS, "+
"		       CC.PE_PIS, "+
"		       CC.FL_REPASSA_ICMS, "+
"		       CC.FL_REPASSA_CPMF "+
"		FROM SCC_CONTRATO_COBILLING CC, "+
"		     SCC_OPERADORA OPLD, "+
"		     SCC_OPERADORA OPCL, "+
"		     SCC_TIPO_CONTRATO TCC, "+
"		     (SELECT CCC.CD_EOT_LD, "+
"		     CCC.CD_EOT_CLARO, "+
"		     CCC.DT_INICIO_CONTRATO, "+
"		     CCC.CD_TIPO_CONTRATO, "+
"		     COUNT(*) AS QTDE "+
"		     FROM SCC_CONTRATO_COBILLING CCC, "+
"		     SCC_TIPO_CONTRATO TC, "+
"		     SCC_ARQUIVO_COBILLING AC, "+
"		     SCC_FECHAMENTO_SUMARIZADO FS, "+
"		     SCC_PRE_FECHAMENTO R "+
"		     WHERE CCC.CD_EOT_LD = R.CD_EOT_LD "+
"		     AND CCC.CD_EOT_CLARO = R.CD_EOT_CLARO "+
"		     AND CCC.CD_EOT_LD = FS.CD_EOT_LD "+
"		     AND CCC.CD_EOT_CLARO = FS.CD_EOT_CLARO "+
"		     AND CCC.CD_EOT_LD = AC.CD_EOT_LD "+
"		     AND CCC.CD_EOT_CLARO = AC.CD_EOT_CLARO "+
"		     AND CCC.CD_TIPO_CONTRATO = TC.CD_TIPO_CONTRATO "+
"		     AND FS.SQ_ARQUIVO = AC.SQ_ARQUIVO "+
"		     GROUP BY CCC.CD_EOT_LD, "+
"		     CCC.CD_EOT_CLARO, "+
"		     CCC.DT_INICIO_CONTRATO, "+
"		     CCC.CD_TIPO_CONTRATO) REPASSE "+
"		WHERE CC.CD_EOT_LD = REPASSE.CD_EOT_LD(+) "+
"		AND CC.CD_EOT_CLARO = REPASSE.CD_EOT_CLARO(+) "+
"		AND CC.CD_TIPO_CONTRATO = REPASSE.CD_TIPO_CONTRATO(+) "+
"		AND CC.DT_INICIO_CONTRATO = REPASSE.DT_INICIO_CONTRATO(+) "+
"		AND CC.CD_EOT_LD = OPLD.CD_EOT "+
"		AND CC.CD_EOT_CLARO = OPCL.CD_EOT "+
"		AND CC.CD_TIPO_CONTRATO = TCC.CD_TIPO_CONTRATO "+
"       AND CC.CD_EOT_LD= :cdEotLd "+
"		AND CC.CD_EOT_CLARO= :cdEotClaro "+
"       AND CC.CD_TIPO_CONTRATO='P' "+
"       GROUP BY CC.CD_EOT_LD, "+
"		       CC.CD_EOT_CLARO, "+
"		       CC.DT_INICIO_CONTRATO, "+
"		       CC.DT_FIM_CONTRATO, "+
"		       CC.CD_TIPO_CONTRATO, "+
"		       CC.PE_RETENCAO, "+
"		       CC.DS_CRITERIO_CUSTO, "+
"		       CC.VL_ASSOCIADO_CRITERIO_CUSTO, "+
"		       CC.VL_CRITERIO_CUSTO_LIQUIDO,  "+
"		       CC.DS_PERIODO_REPASSE,  "+
"		       CC.VL_CPMF, "+
"		       CC.VL_ISS, "+
"		       CC.DT_CRIACAO, "+
"		       CC.DT_ATUALIZACAO, "+
"		       CC.CD_USUARIO_MANUT, "+
"		       REPASSE.QTDE, "+
"		                OPLD.DS_OPERADORA, "+
"		        OPCL.DS_OPERADORA, "+
"		       TCC.DS_TIPO_CONTRATO, "+
"		       CC.PE_COFINS, "+
"		       CC.PE_PIS, "+
"		       CC.FL_REPASSA_ICMS, "+
"		       CC.FL_REPASSA_CPMF ";
			
			
	public static final String PRODUTOS_CONTRATO_SQL =

					"			SELECT      item.CD_EOT_LD, "+
					"						item.CD_EOT_CLARO, "+
					"						item.DT_INICIO_CONTRATO as DT_INICIO_CONTRATO, "+
					"						item.CD_TIPO_CONTRATO, "+
					"						cast(item.CD_PRODUTO_PREPAGO as Numeric) as CD_PRODUTO_PREPAGO  , "+
					"						item.CD_TIPO_EVENTO, "+
					"						item.IN_CATEGORIA_EVENTO, "+
					"						item.IN_DESABILITAR_REPASSE, "+
					"						evento.NO_TIPO_EVENTO, "+
					"						produto.NO_PRODUTO_PREPAGO  "+
					"			FROM  SCC_PRODUTO_CONTRATADO_PREPAGO item, "+
					"				  SCC_TIPO_EVENTO evento, "+
					"				  SCC_PRODUTO_PREPAGO produto  "+
					"			WHERE produto.CD_PRODUTO_PREPAGO = item.CD_PRODUTO_PREPAGO  "+
					"			AND   item.CD_TIPO_EVENTO = evento.CD_TIPO_EVENTO "+
					"			AND   item.IN_CATEGORIA_EVENTO = evento.IN_CATEGORIA_EVENTO  "+
					"			AND   item.CD_EOT_LD = :cdEotLd  "+
					"			AND   item.CD_EOT_CLARO = :cdEotClaro  "+
					"			AND   item.DT_INICIO_CONTRATO = :dtInicio "+
					"			AND   item.CD_TIPO_CONTRATO = 'P'  "+
					"			ORDER BY item.CD_EOT_LD, item.CD_EOT_CLARO, item.DT_INICIO_CONTRATO, item.CD_PRODUTO_PREPAGO, "+
					"			item.IN_CATEGORIA_EVENTO, item.CD_TIPO_EVENTO, item.IN_DESABILITAR_REPASSE";

			
}
