package com.claro.sccweb.service;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccPagamentoRepasse;
import com.claro.sccweb.service.composite.RepassePosPagoComposite;
import com.claro.sccweb.service.composite.RepassePrePagoComposite;


/**
 * Interface de serviço com métodos para pagamento e ajuste de repasses pós e pré pagos.
 *
 */
public interface PagamentoRepasseService {

	
	/**
	 * Realiza o pagamento de um repasse após confirmação do usuário.
	 * O processo de pagamento segue os seguintes passos:
	 * 1. Atualiza os arquivos de cobilling associados a esse repasse (usando SCC_FECHAMENTO_SUMARIZADO como ligação entre repasse e arquivo).
	 * 2. Atualiza o status dos items do repasse para 'C'.
	 * 3. Atualiza o status dos items de assinatura.
	 * 4. Cria um registro de pagamento.
	 * 5. Registra em SCC_MEMO o pagamento e o usuário que o confirmou.
	 * @param repasse Composite com todos os valores do repasse.
	 * @throws DAOException
	 * @throws ServiceException
	 */
	public void realizaPagamentoRepasse(RepassePosPagoComposite repasse) throws DAOException,ServiceException;
	
	
	/**
	 * Realiza o pagamento de um repasse de pré-pago após confirmação do usuário.
	 * O processo de pagamento segue os seguintes passos: 
	 * 1. Atualiza o status  do repasse para 'C'.
	 * 2. Atualiza o status dos items de assinatura.
	 * 3. Cria um registro de pagamento.
	 * 4. Registra em SCC_MEMO o pagamento e o usuário que o confirmou.
	 * @param repasse Composite com todos os valores do repasse.
	 * @throws DAOException
	 * @throws ServiceException
	 */
	public void realizaPagamentoRepasse(RepassePrePagoComposite repasse) throws DAOException,ServiceException;
	
	
	/**
	 * Cancela o pagamento de um repasse. Basicamente , além de setar o valor do status para cancelado , o sistema remove os 
	 * juros e multas.
	 * @param repasse
	 * @param usuario
	 * @throws DAOException
	 * @throws ServiceException
	 */
	public void cancelaPagamentoRepasse(RepassePosPagoComposite repasse,String usuario) throws DAOException,ServiceException;
	
	/**
	 * Cancela o pagamento de um repasse. Basicamente , além de setar o valor do status para cancelado , o sistema remove os 
	 * juros e multas.
	 * @param repasse
	 * @param usuario
	 * @throws DAOException
	 * @throws ServiceException
	 */
	public void cancelaPagamentoRepasse(RepassePrePagoComposite repasse) throws DAOException,ServiceException;
	
	/**
	 * Pesquisa pagamento associado ao repasse.
	 * @param nuRepasse
	 * @param cdEOTClaro
	 * @param cdEOTLD
	 * @return
	 * @throws DAOException
	 */
	public SccPagamentoRepasse getPagamentoByRepasse(Long nuRepasse,String cdEOTClaro,String cdEOTLD) throws DAOException;
	
	/**
	 * Atualiza os dados do pagamento e confirma.
	 * @param pagamentoRepasse
	 * @throws DAOException
	 */
	public void confirmaDadosRepasse(SccPagamentoRepasse pagamentoRepasse) throws DAOException;
	
	 
}
