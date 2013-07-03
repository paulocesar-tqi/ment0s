package com.claro.cobillingweb.persistence.dao.query;


public class SccQuestionamentoSQL {
	
	public static final String SQL = ""+
									" SELECT  Q.CD_EOT_LD,  "+
									" 		  O.DS_OPERADORA ||'( ' ||Q.CD_EOT_LD ||' )' AS DS_EOTLD, "+
									" 		  Q.CD_EOT_CLARO, "+
									"         CAST(Q.SG_UF AS VARCHAR2(2)) AS SG_UF, "+
									"         Q.SQ_QUESTIONAMENTO, "+
									"         Q.NO_ARQUIVO, "+
									"         Q.QT_CDRS_QUEST, "+
									"         Q.MI_REAIS_QUEST, "+
									"         Q.MI_ARREDONDADOS_QUEST, "+
									"         Q.VL_LIQUIDO_QUEST, "+
									"         Q.QT_CDRS_CSA, "+
									"         Q.MI_REAIS_CSA, "+
									"         Q.MI_ARREDONDADOS_CSA, "+
									"         Q.VL_LIQUIDO_CSA, "+
									"         Q.QT_CDRS_CCAP, "+
									"         Q.MI_REAIS_CCAP, "+
									"         Q.MI_ARREDONDADOS_CCAP, "+
									"         Q.VL_LIQUIDO_CCAP, "+
									"         Q.QT_CDRS_CANP, "+
									"         Q.MI_REAIS_CANP, "+
									"         Q.MI_ARREDONDADOS_CANP, "+
									"         Q.VL_LIQUIDO_CANP, "+
									"         Q.QT_CDRS_CLARO, "+
									"         Q.MI_REAIS_CLARO, "+
									"         Q.MI_ARREDONDADOS_CLARO, "+
									"         Q.VL_LIQUIDO_CLARO, "+
									"         Q.QT_CDRS_LD, "+
									"         Q.MI_REAIS_LD, "+
									"         Q.MI_ARREDONDADOS_LD, "+
									"         Q.VL_LIQUIDO_LD, "+
									"         Q.SQ_ARQUIVO "+
									" FROM (         "+
									"       SELECT  RQ.CD_EOT_LD AS CD_EOT_LD, "+
									"               RQ.CD_EOT_CLARO AS CD_EOT_CLARO, "+
									"               OP.SG_UF AS SG_UF, "+
									"               RQ.SQ_QUESTIONAMENTO AS SQ_QUESTIONAMENTO, "+
									"               AC.NO_ARQUIVO AS NO_ARQUIVO, "+
									"               NVL(CDRQ.QT_CDRS,0) AS QT_CDRS_QUEST, "+
									"               NVL(CDRQ.MI_REAIS,0) AS MI_REAIS_QUEST, "+
									"               NVL(CDRQ.MI_ARREDONDADOS,0) AS MI_ARREDONDADOS_QUEST, "+
									"               NVL(CDRQ.VL_LIQUIDO,0) AS VL_LIQUIDO_QUEST, "+
									"               NVL(CDRCSA.QT_CDRS,0) AS QT_CDRS_CSA, "+
									"               NVL(CDRCSA.MI_REAIS,0) AS MI_REAIS_CSA, "+
									"               NVL(CDRCSA.MI_ARREDONDADOS,0) AS MI_ARREDONDADOS_CSA, "+
									"               NVL(CDRCSA.VL_LIQUIDO,0) AS VL_LIQUIDO_CSA, "+
									"               NVL(CDRCCAP.QT_CDRS,0) AS QT_CDRS_CCAP, "+
									"               NVL(CDRCCAP.MI_REAIS,0) AS MI_REAIS_CCAP, "+
									"               NVL(CDRCCAP.MI_ARREDONDADOS,0) AS MI_ARREDONDADOS_CCAP, "+
									"               NVL(CDRCCAP.VL_LIQUIDO,0) AS VL_LIQUIDO_CCAP, "+
									"               NVL(CDRCCANP.QT_CDRS,0) AS QT_CDRS_CANP, "+
									"               NVL(CDRCCANP.MI_REAIS,0) AS MI_REAIS_CANP, "+
									"               NVL(CDRCCANP.MI_ARREDONDADOS,0) AS MI_ARREDONDADOS_CANP, "+
									"               NVL(CDRCCANP.VL_LIQUIDO,0) AS VL_LIQUIDO_CANP, "+
									"               NVL(CDRPCLARO.QT_CDRS,0)AS QT_CDRS_CLARO, "+
									"               NVL(CDRPCLARO.MI_REAIS,0) AS MI_REAIS_CLARO, "+
									"               NVL(CDRPCLARO.MI_ARREDONDADOS,0) AS MI_ARREDONDADOS_CLARO, "+
									"               NVL(CDRPCLARO.VL_LIQUIDO,0) AS VL_LIQUIDO_CLARO, "+
									"               NVL(CDRPLD.QT_CDRS,0) AS QT_CDRS_LD, "+
									"               NVL(CDRPLD.MI_REAIS,0) AS MI_REAIS_LD, "+
									"               NVL(CDRPLD.MI_ARREDONDADOS,0) AS MI_ARREDONDADOS_LD, "+
									"               NVL(CDRPLD.VL_LIQUIDO,0) AS VL_LIQUIDO_LD, "+
									"               AC.SQ_ARQUIVO AS SQ_ARQUIVO "+
									"       FROM (SELECT CD_EOT, SG_UF  "+
									"             FROM SCC_OPERADORA  "+
									"             WHERE CD_TIPO_SERVICO = 'M') OP, "+
									"             SCC_RESULTADO_QUESTIONAMENTO RQ, "+
									"             SCC_ARQUIVO_COBILLING AC, "+
									"            (SELECT RQ.SQ_QUESTIONAMENTO, AC.NO_ARQUIVO, AC.SQ_ARQUIVO, "+
									"                   NVL(SUM(RQ.QT_CDRS),0) AS QT_CDRS, "+
									"                   NVL(SUM(RQ.MI_TARIFADOS),0) AS MI_REAIS, "+
									"                   NVL(SUM(ROUND(RQ.MI_TARIFADOS)),0) AS MI_ARREDONDADOS, "+
									"                   NVL(SUM(VL_LIQUIDO_CHAMADA),0) AS VL_LIQUIDO "+
									"            FROM   (SELECT VL_LIQUIDO_CHAMADA, SQ_REMESSA_QUESTIONAMENTO  "+
									"                    FROM SCC_CDR_QUESTIONAMENTO  "+
									"                    WHERE NU_CDR>0) CDR, "+
									"                   SCC_RESULTADO_QUESTIONAMENTO RQ, "+
									"                   SCC_ARQUIVO_COBILLING AC "+
									"            WHERE  RQ.SQ_ARQUIVO = AC.SQ_ARQUIVO "+
									"            AND    RQ.SQ_ARQUIVO = CDR.SQ_REMESSA_QUESTIONAMENTO "+
									"            AND    RQ.DT_REPASSE IS NOT NULL "+
									"            GROUP BY RQ.CD_EOT_LD, RQ.CD_EOT_CLARO, RQ.SQ_QUESTIONAMENTO, AC.NO_ARQUIVO "+
									"        , AC.SQ_ARQUIVO "+
									"            ) CDRQ, "+
									"            (SELECT RQ.SQ_QUESTIONAMENTO, AC.NO_ARQUIVO, AC.SQ_ARQUIVO, "+
									"                   NVL(SUM(RQ.QT_CDRS),0) AS QT_CDRS, "+
									"                   NVL(SUM(RQ.MI_TARIFADOS),0) AS MI_REAIS, "+
									"                   NVL(SUM(ROUND(RQ.MI_TARIFADOS)),0) AS MI_ARREDONDADOS, "+
									"                   NVL(SUM(VL_LIQUIDO_CHAMADA),0) AS VL_LIQUIDO "+
									"            FROM   (SELECT VL_LIQUIDO_CHAMADA, SQ_REMESSA_QUESTIONAMENTO  "+
									"                    FROM SCC_CDR_QUESTIONAMENTO  "+
									"                    WHERE NU_CDR>0  "+
									"                       AND IN_RESULTADO_ANALISE = 'P'  "+
									"                       AND CD_STATUS_ANALISE = 'QSA') CDR, "+
									"                   SCC_RESULTADO_QUESTIONAMENTO RQ, "+
									"                   SCC_ARQUIVO_COBILLING AC "+
									"            WHERE  RQ.SQ_ARQUIVO = AC.SQ_ARQUIVO "+
									"            AND    RQ.SQ_ARQUIVO = CDR.SQ_REMESSA_QUESTIONAMENTO "+
									"            AND    RQ.DT_REPASSE IS NOT NULL "+
									"            GROUP BY RQ.CD_EOT_LD, RQ.CD_EOT_CLARO, RQ.SQ_QUESTIONAMENTO, AC.NO_ARQUIVO "+
									"       , AC.SQ_ARQUIVO "+
									"            ) CDRCSA, "+
									"            (SELECT RQ.SQ_QUESTIONAMENTO, AC.NO_ARQUIVO, AC.SQ_ARQUIVO, "+
									"                   NVL(SUM(RQ.QT_CDRS),0) AS QT_CDRS, "+
									"                   NVL(SUM(RQ.MI_TARIFADOS),0) AS MI_REAIS, "+
									"                   NVL(SUM(ROUND(RQ.MI_TARIFADOS)),0) AS MI_ARREDONDADOS, "+
									"                   NVL(SUM(VL_LIQUIDO_CHAMADA),0) AS VL_LIQUIDO "+
									"            FROM   (SELECT VL_LIQUIDO_CHAMADA, SQ_REMESSA_QUESTIONAMENTO  "+
									"                    FROM SCC_CDR_QUESTIONAMENTO  "+
									"                    WHERE NU_CDR>0  "+
									"                       AND IN_RESULTADO_ANALISE = 'P'  "+
									"                       AND CD_STATUS_ANALISE = 'QSP') CDR, "+
									"                   SCC_RESULTADO_QUESTIONAMENTO RQ, "+
									"                   SCC_ARQUIVO_COBILLING AC "+
									"            WHERE  RQ.SQ_ARQUIVO = AC.SQ_ARQUIVO "+
									"            AND    RQ.SQ_ARQUIVO = CDR.SQ_REMESSA_QUESTIONAMENTO "+
									"            AND    RQ.DT_REPASSE IS NOT NULL "+
									"            GROUP BY RQ.CD_EOT_LD, RQ.CD_EOT_CLARO, RQ.SQ_QUESTIONAMENTO, AC.NO_ARQUIVO "+
									"       , AC.SQ_ARQUIVO "+
									"            ) CDRCCAP, "+
									"            (SELECT RQ.SQ_QUESTIONAMENTO, AC.NO_ARQUIVO, AC.SQ_ARQUIVO, "+
									"                   NVL(SUM(RQ.QT_CDRS),0) AS QT_CDRS, "+
									"                   NVL(SUM(RQ.MI_TARIFADOS),0) AS MI_REAIS, "+
									"                   NVL(SUM(ROUND(RQ.MI_TARIFADOS)),0) AS MI_ARREDONDADOS, "+
									"                   NVL(SUM(VL_LIQUIDO_CHAMADA),0) AS VL_LIQUIDO "+
									"            FROM   (SELECT VL_LIQUIDO_CHAMADA, SQ_REMESSA_QUESTIONAMENTO  "+
									"                    FROM SCC_CDR_QUESTIONAMENTO  "+
									"                    WHERE NU_CDR>0  "+
									"                       AND IN_RESULTADO_ANALISE = 'N'  "+
									"                       AND CD_STATUS_ANALISE = 'QSN') CDR, "+
									"                   SCC_RESULTADO_QUESTIONAMENTO RQ, "+
									"                   SCC_ARQUIVO_COBILLING AC "+
									"            WHERE  RQ.SQ_ARQUIVO = AC.SQ_ARQUIVO "+
									"            AND    RQ.SQ_ARQUIVO = CDR.SQ_REMESSA_QUESTIONAMENTO "+
									"            AND    RQ.DT_REPASSE IS NOT NULL "+
									"            GROUP BY RQ.CD_EOT_LD, RQ.CD_EOT_CLARO, RQ.SQ_QUESTIONAMENTO, AC.NO_ARQUIVO "+
									"       , AC.SQ_ARQUIVO "+
									"            ) CDRCCANP, "+
									"            (SELECT RQ.SQ_QUESTIONAMENTO, AC.NO_ARQUIVO, AC.SQ_ARQUIVO, "+
									"                   NVL(SUM(RQ.QT_CDRS),0) AS QT_CDRS, "+
									"                   NVL(SUM(RQ.MI_TARIFADOS),0) AS MI_REAIS, "+
									"                   NVL(SUM(ROUND(RQ.MI_TARIFADOS)),0) AS MI_ARREDONDADOS, "+
									"                   NVL(SUM(RQ.VL_PENALIDADE_CLARO),0) AS VL_LIQUIDO "+
									"            FROM   SCC_RESULTADO_QUESTIONAMENTO RQ, "+
									"                   SCC_ARQUIVO_COBILLING AC "+
									"            WHERE  RQ.SQ_ARQUIVO = AC.SQ_ARQUIVO "+
									"            AND    RQ.DT_REPASSE IS NOT NULL "+
									"            GROUP BY RQ.CD_EOT_LD, RQ.CD_EOT_CLARO, RQ.SQ_QUESTIONAMENTO, AC.NO_ARQUIVO "+
									"       , AC.SQ_ARQUIVO "+
									"           ) CDRPCLARO, "+
									"            (SELECT RQ.SQ_QUESTIONAMENTO, AC.NO_ARQUIVO, AC.SQ_ARQUIVO, "+
									"                   NVL(SUM(RQ.QT_CDRS),0) AS QT_CDRS, "+
									"                   NVL(SUM(RQ.MI_TARIFADOS),0) AS MI_REAIS, "+
									"                   NVL(SUM(ROUND(RQ.MI_TARIFADOS)),0) AS MI_ARREDONDADOS, "+
									"                   NVL(SUM(RQ.VL_PENALIDADE_LD),0) AS VL_LIQUIDO "+
									"            FROM   SCC_RESULTADO_QUESTIONAMENTO RQ, "+
									"                   SCC_ARQUIVO_COBILLING AC "+
									"            WHERE  RQ.SQ_ARQUIVO = AC.SQ_ARQUIVO "+
									"            AND    RQ.DT_REPASSE IS NOT NULL "+
									"            GROUP BY RQ.CD_EOT_LD, RQ.CD_EOT_CLARO, RQ.SQ_QUESTIONAMENTO, AC.NO_ARQUIVO "+
									"       , AC.SQ_ARQUIVO "+
									"            ) CDRPLD "+
									"       WHERE RQ.CD_EOT_CLARO = OP.CD_EOT "+
									"       AND   RQ.SQ_ARQUIVO = AC.SQ_ARQUIVO "+
									"       AND   RQ.SQ_ARQUIVO = CDRQ.SQ_ARQUIVO(+) "+
									"       AND   RQ.SQ_ARQUIVO = CDRCSA.SQ_ARQUIVO(+) "+
									"       AND   RQ.SQ_ARQUIVO = CDRCCAP.SQ_ARQUIVO(+) "+
									"       AND   RQ.SQ_ARQUIVO = CDRCCANP.SQ_ARQUIVO(+) "+
									"       AND   RQ.SQ_ARQUIVO = CDRPCLARO.SQ_ARQUIVO(+) "+
									"       AND   RQ.SQ_ARQUIVO = CDRPLD.SQ_ARQUIVO(+) "+
									"       AND   TO_CHAR(RQ.DT_REPASSE,'MMYYYY') = :dtRepasse ";
			
	public static final String FILTRO_SQ_QUESTIONAMENTO = "       AND RQ.SQ_QUESTIONAMENTO = :sqQuestionamento ";	
	public static final String FILTRO_DATA = "AND   TO_CHAR(RQ.DT_REPASSE,'YYYYMM') = :dtRepasse ";
	public static final String FILTRO_CD_EOT_LD = "AND RQ.CD_EOT_LD = :cdEotLd ";
	public static final String FILTRO_CD_EOT_CLARO ="AND RQ.CD_EOT_CLARO = :cdEotClaro";
	public static final String FILTRO_SQL_PARTE2 = ") Q, "+
												   " SCC_OPERADORA O      "+
												   " WHERE  Q.CD_EOT_LD = O.CD_EOT(+) ";
	
	public static final String SQL_COMBO =" 	SELECT Q.SQ_QUESTIONAMENTO, Q.CD_EOT_LD, O.DS_OPERADORA "+
											"	FROM ( "+
											"			SELECT 	CQ.SQ_QUESTIONAMENTO AS SQ_QUESTIONAMENTO, "+
											"					CQ.CD_EOT_LD AS CD_EOT_LD, "+
											"		            CQ.CD_STATUS_QUESTIONAMENTO, "+
											"		            CQ.DT_EVENTO, "+
											"		            CQ.DT_PROTOCOLO_CLARO, "+
											"		            CQ.DT_PROTOCOLO_LD, "+
											"		            CQ.DT_ANALISE, "+
											"		            CQ.DT_CORRECAO, "+
											"		            CQ.TX_COMENTARIO_CLARO, "+
											"		            CQ.TX_COMENTARIO_LD, "+
											"		            CQ.TX_ANALISE, "+
											"		            CQ.TX_CORRECAO, "+
											"		            CQ.TX_MOTIVOS_REJEICAO, "+
											"		            CQ.TX_ARQUIVOS, "+
											"		            CQ.CD_IDENTIFICACAO_CARTA_CLARO, "+
											"		            CQ.CD_IDENTIFICACAO_CARTA_LD, "+
											"		            CQ.NO_RESPONSAVEL_CLARO, "+
											"		            CQ.NO_RESPONSAVEL_LD, "+
											"		            CQ.CD_PROCESSO, "+
											"		            CQ.DT_CRIACAO, "+
											"		            CQ.DT_ATUALIZACAO, "+
											"		            CQ.CD_USUARIO_MANUT, "+
											"		            0 AS MI_DURACAO_TARIFADA, "+
											"		            0 AS VL_LIQUIDO_CHAMADA, "+
											"		            0 AS VL_BRUTO_CHAMADA, "+
											"		            0 AS VL_POTENCIAL_CLARO "+
											"		   FROM SCC_CONTROLE_QUESTIONAMENTO CQ "+
											"		   WHERE SQ_QUESTIONAMENTO IN ( SELECT SQ_QUESTIONAMENTO FROM SCC_RESULTADO_QUESTIONAMENTO WHERE DT_REPASSE IS NOT NULL ) "+
											"			AND CQ.SQ_QUESTIONAMENTO IN ( SELECT SQ_QUESTIONAMENTO FROM SCC_CDR_QUESTIONAMENTO ))Q, SCC_OPERADORA O "+
											"	WHERE Q.CD_EOT_LD = O.CD_EOT (+) ";
	
	public static final String SQL_COMBO_QUESTIONAMENTO =""+
															"          SELECT Q.SQ_QUESTIONAMENTO, Q.CD_EOT_LD, O.DS_OPERADORA "+
															"          FROM( "+
															"                  SELECT CQ.SQ_QUESTIONAMENTO, "+
															"                              CQ.CD_EOT_LD, "+
															"                              CQ.CD_STATUS_QUESTIONAMENTO, "+
															"                              CQ.DT_EVENTO, "+
															"                              CQ.DT_PROTOCOLO_CLARO, "+
															"                              CQ.DT_PROTOCOLO_LD, "+
															"                              CQ.DT_ANALISE, "+
															"                              CQ.DT_CORRECAO, "+
															"                              CQ.TX_COMENTARIO_CLARO, "+
															"                              CQ.TX_COMENTARIO_LD, "+
															"                             CQ.TX_ANALISE, "+
															"                              CQ.TX_CORRECAO, "+
															"                              CQ.TX_MOTIVOS_REJEICAO, "+
															"                              CQ.TX_ARQUIVOS, "+
															"                              CQ.CD_IDENTIFICACAO_CARTA_CLARO, "+
															"                              CQ.CD_IDENTIFICACAO_CARTA_LD, "+
															"                              CQ.NO_RESPONSAVEL_CLARO, "+
															"                              CQ.NO_RESPONSAVEL_LD, "+
															"                              CQ.CD_PROCESSO, "+
															"                              CQ.DT_CRIACAO, "+
															"                              CQ.DT_ATUALIZACAO, "+
															"                              CQ.CD_USUARIO_MANUT, "+
															"                              NVL(SUM(CDR.MI_DURACAO_TARIFADA),0), "+
															"                              NVL(SUM(CDR.VL_LIQUIDO_CHAMADA),0), "+
															"                              NVL(SUM(CDR.VL_BRUTO_CHAMADA),0), "+
															"                              NVL(SUM(CDR.VL_POTENCIAL_CLARO),0) "+
															"                    FROM SCC_CONTROLE_QUESTIONAMENTO CQ, SCC_CDR_QUESTIONAMENTO CDR "+
															"                  WHERE CQ.SQ_QUESTIONAMENTO = CDR.SQ_QUESTIONAMENTO "+
															"                  GROUP BY CQ.SQ_QUESTIONAMENTO, "+
															"                              CQ.CD_EOT_LD, "+
															"                              CQ.CD_STATUS_QUESTIONAMENTO, "+
															"                              CQ.DT_EVENTO, "+
															"                              CQ.DT_PROTOCOLO_CLARO, "+
															"                              CQ.DT_PROTOCOLO_LD, "+
															"                              CQ.DT_ANALISE, "+
															"                              CQ.DT_CORRECAO, "+
															"                              CQ.TX_COMENTARIO_CLARO, "+
															"                              CQ.TX_COMENTARIO_LD, "+
															"                              CQ.TX_ANALISE, "+
															"                              CQ.TX_CORRECAO, "+
															"                              CQ.TX_MOTIVOS_REJEICAO, "+
															"                              CQ.TX_ARQUIVOS, "+
															"                              CQ.CD_IDENTIFICACAO_CARTA_CLARO, "+
															"                              CQ.CD_IDENTIFICACAO_CARTA_LD, "+
															"                              CQ.NO_RESPONSAVEL_CLARO, "+
															"                              CQ.NO_RESPONSAVEL_LD, "+
															"                              CQ.CD_PROCESSO, "+
															"                              CQ.DT_CRIACAO, "+
															"                              CQ.DT_ATUALIZACAO, "+
															"                              CQ.CD_USUARIO_MANUT "+
															"                  ORDER BY CQ.DT_EVENTO)Q, SCC_OPERADORA O "+
															"          WHERE Q.CD_EOT_LD = O.CD_EOT(+) "+
															"          ORDER BY 3 ";	
	
	public static final String SQL_COMBO_ARQUIVO =""+
													"          SELECT Q.SQ_QUESTIONAMENTO, Q.SQ_REMESSA_QUESTIONAMENTO, Q.CD_EOT_LD, O.DS_OPERADORA  "+
													"          FROM(  "+
													"                SELECT DISTINCT CDR.SQ_QUESTIONAMENTO,  "+
													"                       CDR.SQ_REMESSA_QUESTIONAMENTO,  "+
													"                       CDR.CD_EOT_LD,  "+
													"                       CDR.CD_EOT_CLARO,  "+
													"                       NVL(ASSINANTE.QT_ASSINANTE,0),  "+
													"                       NVL(REGRA.QT_REGRA,0),  "+
													"                       NVL(PARAMETRO.QT_PARAMETRO,0),  "+
													"                       NVL(LOGICA.QT_LOGICA,0),  "+
													"                       NVL(QUANTIDADE.QT_CDR,0),  "+
													"                       NVL(MINUTOS.MI_DURACAO_TARIFADA,0),  "+
													"                       NVL(VALOR.VL_LIQUIDO_CHAMADA,0),  "+
													"                       AC.NO_ARQUIVO  "+
													"                  FROM SCC_CDR_QUESTIONAMENTO CDR,  "+
													"                                (SELECT COUNT(1) QT_ASSINANTE, CDR.SQ_REMESSA_QUESTIONAMENTO  "+
													"                                FROM   SCC_CDR_QUESTIONAMENTO CDR  "+
													"                                WHERE  1=1  "+
													"                                AND    CDR.CD_MOTIVO_REJEICAO IN ('104','105','109','143')             "+
													"                                AND   CDR.CD_CONFIRMACAO_QUESTIONAMENTO NOT IN ('DVN', 'QNA')   "+
													"                                AND       CDR.NU_CDR > 0  "+
													"                                GROUP BY CDR.SQ_REMESSA_QUESTIONAMENTO ) ASSINANTE,  "+
													"                                (SELECT COUNT(1) QT_REGRA, CDR.SQ_REMESSA_QUESTIONAMENTO  "+
													"                                FROM   SCC_CDR_QUESTIONAMENTO CDR  "+
													"                                WHERE  1=1  "+
													"                                AND    CDR.CD_MOTIVO_REJEICAO IN ('121','124','125','126','127','128','129','130','132','133','134','135','136','137','138','140','141','150')                 "+
													"                                AND   CDR.CD_CONFIRMACAO_QUESTIONAMENTO NOT IN ('DVN','QNA')          "+
													"                                AND CDR.NU_CDR > 0  "+
													"                                GROUP BY CDR.SQ_REMESSA_QUESTIONAMENTO ) REGRA,  "+
													"                                (SELECT COUNT(1) QT_PARAMETRO, CDR.SQ_REMESSA_QUESTIONAMENTO  "+
													"                                FROM   SCC_CDR_QUESTIONAMENTO CDR  "+
													"                                WHERE  1=1  "+
													"                                AND    CDR.CD_MOTIVO_REJEICAO IN ('100','101','102','103','116','117','119','120','122','131','144','146')  "+
													"                                AND   CDR.CD_CONFIRMACAO_QUESTIONAMENTO NOT IN ('DVN','QNA')  "+
													"                                AND   CDR.NU_CDR > 0  "+
													"                                GROUP BY CDR.SQ_REMESSA_QUESTIONAMENTO ) PARAMETRO,  "+
													"                                (SELECT COUNT(1) QT_LOGICA, CDR.SQ_REMESSA_QUESTIONAMENTO  "+
													"                                FROM   SCC_CDR_QUESTIONAMENTO CDR  "+
													"                                WHERE  1=1  "+
													"                                AND    CDR.CD_MOTIVO_REJEICAO IN ('145')               "+
													"                                AND   CDR.CD_CONFIRMACAO_QUESTIONAMENTO NOT IN ('DVN','QNA')   "+
													"                                AND        CDR.NU_CDR > 0  "+
													"                                GROUP BY CDR.SQ_REMESSA_QUESTIONAMENTO ) LOGICA,  "+
													"                                (SELECT COUNT(1) QT_CDR, CDR.SQ_REMESSA_QUESTIONAMENTO  "+
													"                                FROM   SCC_CDR_QUESTIONAMENTO CDR  "+
													"                                WHERE  1=1  "+
													"                                GROUP BY CDR.SQ_REMESSA_QUESTIONAMENTO ) QUANTIDADE,  "+
													"                                (SELECT SUM(CDR.MI_DURACAO_TARIFADA) MI_DURACAO_TARIFADA, CDR.SQ_REMESSA_QUESTIONAMENTO  "+
													"                                FROM   SCC_CDR_QUESTIONAMENTO CDR  "+
													"                                WHERE  1=1  "+
													"                                AND   CDR.CD_CONFIRMACAO_QUESTIONAMENTO NOT IN ('DVN','QNA')   "+
													"                                AND        CDR.NU_CDR > 0  "+
													"                                GROUP BY CDR.SQ_REMESSA_QUESTIONAMENTO ) MINUTOS,  "+
													"                                (SELECT SUM(CDR.VL_LIQUIDO_CHAMADA) VL_LIQUIDO_CHAMADA, CDR.SQ_REMESSA_QUESTIONAMENTO  "+
													"                                FROM   SCC_CDR_QUESTIONAMENTO CDR  "+
													"                                WHERE  1=1  "+
													"                                AND   CDR.CD_CONFIRMACAO_QUESTIONAMENTO NOT IN ('DVN','QNA')   "+
													"                                AND        CDR.NU_CDR > 0  "+
													"                                GROUP BY CDR.SQ_REMESSA_QUESTIONAMENTO ) VALOR,  "+
													"                                (SELECT SQ_ARQUIVO, NO_ARQUIVO  "+
													"                                FROM SCC_ARQUIVO_COBILLING  "+
													"                                WHERE NO_ARQUIVO LIKE 'TCQE.T%'  "+
													"                                AND CD_TIPO_ARQUIVO = 105) AC  "+
													"                WHERE CDR.SQ_REMESSA_QUESTIONAMENTO = AC.SQ_ARQUIVO  "+
													"                AND   CDR.CD_CONFIRMACAO_QUESTIONAMENTO NOT IN ('DVN')   "+
													"                AND     CDR.NU_CDR > 0  "+
													"                AND   CDR.SQ_REMESSA_QUESTIONAMENTO = ASSINANTE.SQ_REMESSA_QUESTIONAMENTO(+)  "+
													"                AND    CDR.SQ_REMESSA_QUESTIONAMENTO = REGRA.SQ_REMESSA_QUESTIONAMENTO(+)  "+
													"                AND    CDR.SQ_REMESSA_QUESTIONAMENTO = PARAMETRO.SQ_REMESSA_QUESTIONAMENTO(+)  "+
													"                AND    CDR.SQ_REMESSA_QUESTIONAMENTO = LOGICA.SQ_REMESSA_QUESTIONAMENTO(+)  "+
													"                AND    CDR.SQ_REMESSA_QUESTIONAMENTO = QUANTIDADE.SQ_REMESSA_QUESTIONAMENTO(+)  "+
													"                AND    CDR.SQ_REMESSA_QUESTIONAMENTO = MINUTOS.SQ_REMESSA_QUESTIONAMENTO (+)  "+
													"                AND    CDR.SQ_REMESSA_QUESTIONAMENTO = VALOR.SQ_REMESSA_QUESTIONAMENTO(+))Q, SCC_OPERADORA O  "+
													"          WHERE Q.CD_EOT_LD = O.CD_EOT(+)  "+
													"          ORDER BY 2  ";
	public static final String SQL_QUESTIONAMENTO_ARQUIVO =""+
															"          SELECT DISTINCT CDR.SQ_QUESTIONAMENTO,   "+
															"                 CDR.SQ_REMESSA_QUESTIONAMENTO,   "+
															"                 CDR.CD_EOT_LD,   "+
															"                 CDR.CD_EOT_CLARO,   "+
															"                 NVL(ASSINANTE.QT_ASSINANTE,0),   "+
															"                 NVL(REGRA.QT_REGRA,0),   "+
															"                 NVL(PARAMETRO.QT_PARAMETRO,0),   "+
															"                 NVL(LOGICA.QT_LOGICA,0),   "+
															"                 NVL(QUANTIDADE.QT_CDR,0),   "+
															"                 NVL(MINUTOS.MI_DURACAO_TARIFADA,0),   "+
															"                 NVL(VALOR.VL_LIQUIDO_CHAMADA,0),   "+
															"                 AC.NO_ARQUIVO   "+
															"            FROM SCC_CDR_QUESTIONAMENTO CDR,   "+
															"                          (SELECT COUNT(1) QT_ASSINANTE, CDR.SQ_REMESSA_QUESTIONAMENTO   "+
															"                          FROM   SCC_CDR_QUESTIONAMENTO CDR   "+
															"                          WHERE  1=1   "+
															"                          AND   CDR.SQ_QUESTIONAMENTO = 23   "+
															"                          AND    CDR.CD_MOTIVO_REJEICAO IN ('104','105','109','143')              "+
															"                          AND   CDR.CD_CONFIRMACAO_QUESTIONAMENTO NOT IN ('DVN', 'QNA')    "+
															"                          AND       CDR.NU_CDR > 0   "+
															"                          GROUP BY CDR.SQ_REMESSA_QUESTIONAMENTO ) ASSINANTE,   "+
															"                          (SELECT COUNT(1) QT_REGRA, CDR.SQ_REMESSA_QUESTIONAMENTO   "+
															"                          FROM   SCC_CDR_QUESTIONAMENTO CDR   "+
															"                          WHERE  1=1   "+
															"                          AND   CDR.SQ_QUESTIONAMENTO = 23   "+
															"                          AND    CDR.CD_MOTIVO_REJEICAO IN ('121','124','125','126','127','128','129','130','132','133','134','135','136','137','138','140','141','150')                  "+
															"                          AND   CDR.CD_CONFIRMACAO_QUESTIONAMENTO NOT IN ('DVN','QNA')           "+
															"                          AND CDR.NU_CDR > 0   "+
															"                          GROUP BY CDR.SQ_REMESSA_QUESTIONAMENTO ) REGRA,   "+
															"                          (SELECT COUNT(1) QT_PARAMETRO, CDR.SQ_REMESSA_QUESTIONAMENTO   "+
															"                          FROM   SCC_CDR_QUESTIONAMENTO CDR   "+
															"                          WHERE  1=1   "+
															"                          AND   CDR.SQ_QUESTIONAMENTO = 23   "+
															"                          AND    CDR.CD_MOTIVO_REJEICAO IN ('100','101','102','103','116','117','119','120','122','131','144','146')   "+
															"                          AND   CDR.CD_CONFIRMACAO_QUESTIONAMENTO NOT IN ('DVN','QNA')    "+
															"                          AND   CDR.NU_CDR > 0   "+
															"                          GROUP BY CDR.SQ_REMESSA_QUESTIONAMENTO ) PARAMETRO,   "+
															"                          (SELECT COUNT(1) QT_LOGICA, CDR.SQ_REMESSA_QUESTIONAMENTO   "+
															"                          FROM   SCC_CDR_QUESTIONAMENTO CDR   "+
															"                          WHERE  1=1   "+
															"                          AND   CDR.SQ_QUESTIONAMENTO = 23   "+
															"                          AND    CDR.CD_MOTIVO_REJEICAO IN ('145')                "+
															"                          AND   CDR.CD_CONFIRMACAO_QUESTIONAMENTO NOT IN ('DVN','QNA')    "+
															"                          AND        CDR.NU_CDR > 0   "+
															"                          GROUP BY CDR.SQ_REMESSA_QUESTIONAMENTO ) LOGICA,   "+
															"                          (SELECT COUNT(1) QT_CDR, CDR.SQ_REMESSA_QUESTIONAMENTO   "+
															"                          FROM   SCC_CDR_QUESTIONAMENTO CDR   "+
															"                          WHERE  1=1   "+
															"                          AND   CDR.SQ_QUESTIONAMENTO = 23   "+
															"                          GROUP BY CDR.SQ_REMESSA_QUESTIONAMENTO ) QUANTIDADE,   "+
															"                          (SELECT SUM(CDR.MI_DURACAO_TARIFADA) MI_DURACAO_TARIFADA, CDR.SQ_REMESSA_QUESTIONAMENTO   "+
															"                          FROM   SCC_CDR_QUESTIONAMENTO CDR   "+
															"                          WHERE  1=1   "+
															"                          AND   CDR.SQ_QUESTIONAMENTO = 23   "+
															"                          AND   CDR.CD_CONFIRMACAO_QUESTIONAMENTO NOT IN ('DVN','QNA')    "+
															"                          AND   CDR.NU_CDR > 0   "+
															"                          GROUP BY CDR.SQ_REMESSA_QUESTIONAMENTO ) MINUTOS,   "+
															"                          (SELECT SUM(CDR.VL_LIQUIDO_CHAMADA) VL_LIQUIDO_CHAMADA, CDR.SQ_REMESSA_QUESTIONAMENTO   "+
															"                          FROM   SCC_CDR_QUESTIONAMENTO CDR   "+
															"                          WHERE  1=1   "+
															"                          AND   CDR.SQ_QUESTIONAMENTO = 23   "+
															"                          AND   CDR.CD_CONFIRMACAO_QUESTIONAMENTO NOT IN ('DVN','QNA')    "+
															"                          AND   CDR.NU_CDR > 0   "+
															"                          GROUP BY CDR.SQ_REMESSA_QUESTIONAMENTO ) VALOR,   "+
															"                          (SELECT SQ_ARQUIVO, NO_ARQUIVO   "+
															"                          FROM SCC_ARQUIVO_COBILLING   "+
															"                          WHERE NO_ARQUIVO LIKE 'TCQE.T%'   "+
															"                          AND CD_TIPO_ARQUIVO = 105) AC   "+
															"          WHERE CDR.SQ_REMESSA_QUESTIONAMENTO = AC.SQ_ARQUIVO   "+
															"          AND   CDR.CD_CONFIRMACAO_QUESTIONAMENTO NOT IN ('DVN')   "+
															"          AND     CDR.NU_CDR > 0   "+
															"          AND CDR.SQ_QUESTIONAMENTO = 23   "+
															"          AND   CDR.SQ_REMESSA_QUESTIONAMENTO = ASSINANTE.SQ_REMESSA_QUESTIONAMENTO(+)   "+
															"          AND    CDR.SQ_REMESSA_QUESTIONAMENTO = REGRA.SQ_REMESSA_QUESTIONAMENTO(+)   "+
															"          AND    CDR.SQ_REMESSA_QUESTIONAMENTO = PARAMETRO.SQ_REMESSA_QUESTIONAMENTO(+)   "+
															"          AND    CDR.SQ_REMESSA_QUESTIONAMENTO = LOGICA.SQ_REMESSA_QUESTIONAMENTO(+)   "+
															"          AND    CDR.SQ_REMESSA_QUESTIONAMENTO = QUANTIDADE.SQ_REMESSA_QUESTIONAMENTO(+)   "+
															"          AND    CDR.SQ_REMESSA_QUESTIONAMENTO = MINUTOS.SQ_REMESSA_QUESTIONAMENTO (+)   "+
															"          AND    CDR.SQ_REMESSA_QUESTIONAMENTO = VALOR.SQ_REMESSA_QUESTIONAMENTO(+)  ";  
}
