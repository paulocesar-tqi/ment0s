/**
 * 
 */
package com.claro.sccweb.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.claro.cobillingweb.persistence.constants.CobillingConstants;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.internal.SccCdrQuestionamentoDAO;
import com.claro.cobillingweb.persistence.dao.internal.SccQuestionamentoDAO;
import com.claro.cobillingweb.persistence.entity.SccCdrQuestionamento;
import com.claro.cobillingweb.persistence.filtro.SccFiltroQuestionamento;
import com.claro.cobillingweb.persistence.view.SccQuestionamentoArquivoView;
import com.claro.cobillingweb.persistence.view.SccQuestionamentoPenalidadeView;
import com.claro.cobillingweb.persistence.view.SccQuestionamentoView;
import com.claro.sccweb.service.AbstractService;
import com.claro.sccweb.service.SccQuestionamentoService;
import com.claro.sccweb.service.ServiceException;

/**
 * @author vagner.souza
 *
 */
@Service
public class SccQuestionamentoServiceImpl extends AbstractService implements SccQuestionamentoService {

	@Autowired
	private SccQuestionamentoDAO sccQuestionamentoDAO;
	
	@Autowired
	private SccCdrQuestionamentoDAO sccCdrQuestionamentoDAO;
	
	@Override
	public Collection<SccQuestionamentoView> gerarCombo() throws ServiceException, DAOException {
		
		return this.sccQuestionamentoDAO.gerarCombo();
	}
	
	@Override
	public List<SccCdrQuestionamento> listAll() throws ServiceException, DAOException{
		
		return this.sccCdrQuestionamentoDAO.listAll();
		
	}
	
	@Override
	@Transactional
	public void updateAnaliseChamadas(Collection<SccCdrQuestionamento> listCdrQuestionamento) throws ServiceException, DAOException {
		
		for (SccCdrQuestionamento sccCdrQuestionamento : listCdrQuestionamento) {
			this.sccCdrQuestionamentoDAO.updateAnaliseChamadas(sccCdrQuestionamento);
		}
		
		
	}



	@Override
	public Collection<SccQuestionamentoView> gerarComboQuestionamento()	throws ServiceException, DAOException {
		
		return this.sccQuestionamentoDAO.gerarComboQuestionamento();
	}

	@Override
	public Collection<SccQuestionamentoView> gerarComboArquivo() throws ServiceException, DAOException {
		
		return this.sccQuestionamentoDAO.gerarComboArquivo();
	}
	
	

	@Override
	public Collection<SccQuestionamentoArquivoView> gerarRelatorioQuestionamentoArquivo(
			SccFiltroQuestionamento filtro) throws ServiceException,
			DAOException {
		
		return this.sccQuestionamentoDAO.gerarRelatorioQuestionamentoArquivo(filtro);
	}
	
	

	@Override
	public Collection<SccCdrQuestionamento> pesquisarAnaliseChamadas(Long sqQuestionamento, Long sqRemessaQuestionamento) throws ServiceException, DAOException {
		
		return this.sccQuestionamentoDAO.pesquisarAnaliseChamadas(sqQuestionamento, sqRemessaQuestionamento);
	}

	@Override
	public Collection<SccQuestionamentoView> gerarRelatorioQuestionamentoFinanceiro(SccFiltroQuestionamento filtro) throws ServiceException, DAOException {
		
		return this.sccQuestionamentoDAO.gerarRelatorioQuestionamentoFinanceiro(filtro);
	}
	
	public List<SccQuestionamentoPenalidadeView> gerarRelatorioQuestionamentoPenalidade(SccFiltroQuestionamento filtro) throws ServiceException{
		
		List<SccQuestionamentoPenalidadeView> list = new ArrayList<SccQuestionamentoPenalidadeView>();
		
		try {
			
			if(filtro != null){
				filtro.setInResultadoAnalise(CobillingConstants.NAOPROCEDENTE);
				formatarProcNaoProc(list, this.sccQuestionamentoDAO.gerarRelatorioQuestionamentoPenalidade(filtro), CobillingConstants.NAOPROCEDENTE);
				
				filtro.setInResultadoAnalise(CobillingConstants.PROCEDENTE);
				formatarProcNaoProc(list, this.sccQuestionamentoDAO.gerarRelatorioQuestionamentoPenalidade(filtro), CobillingConstants.PROCEDENTE);
				
				filtro.setInResultadoAnalise(CobillingConstants.NAOAPLICAVEL);
				formatarPenalidadeNaoAplic(list, this.sccQuestionamentoDAO.gerarRelatorioQuestionamentoPenalidade(filtro));
			}
			
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
			
		}
		
		return list;
	}
	
	
	private void formatarProcNaoProc(List<SccQuestionamentoPenalidadeView>list, Collection<SccQuestionamentoPenalidadeView> colecao, String analise ) throws ServiceException{
		
		SccQuestionamentoPenalidadeView subTotal1 = new SccQuestionamentoPenalidadeView();
		SccQuestionamentoPenalidadeView subTotal2 = new SccQuestionamentoPenalidadeView();
		SccQuestionamentoPenalidadeView acumulador = new SccQuestionamentoPenalidadeView();
		SccQuestionamentoPenalidadeView total 	   = new SccQuestionamentoPenalidadeView();	       	         
 
		
		for (SccQuestionamentoPenalidadeView entity : colecao) {
			if(entity.getCdPosicao().equalsIgnoreCase("03")){
				String[] tITENSRELATORIO = CobillingConstants.buscaConfigItens("01", analise);
				if (tITENSRELATORIO!=null) {
					SccQuestionamentoPenalidadeView item1MenuTO = new SccQuestionamentoPenalidadeView();
					item1MenuTO.setDsMotivoRejeicao(tITENSRELATORIO[1]+" "+tITENSRELATORIO[2]);
					list.add(item1MenuTO);
				}

				tITENSRELATORIO = CobillingConstants.buscaConfigItens("02", analise);
				if (tITENSRELATORIO!=null) {
					SccQuestionamentoPenalidadeView item2MenuTO = new SccQuestionamentoPenalidadeView();
					item2MenuTO.setDsMotivoRejeicao(tITENSRELATORIO[1]+" "+tITENSRELATORIO[2]);
					list.add(item2MenuTO);
				}
				
				tITENSRELATORIO = CobillingConstants.buscaConfigItens(entity.getCdPosicao(), analise);
				if (tITENSRELATORIO!=null) {
					entity.setDsMotivoRejeicao(tITENSRELATORIO[1]+" "+tITENSRELATORIO[2]);
					list.add(entity);
					adicionarTotal(acumulador, entity);
				}


			} else if(entity.getCdPosicao().equalsIgnoreCase("37")){
				String[] tITENSRELATORIO = CobillingConstants.buscaConfigItens(entity.getCdPosicao(), analise);
				if (tITENSRELATORIO!=null) {
					entity.setDsMotivoRejeicao(tITENSRELATORIO[1]+" "+tITENSRELATORIO[2]);
					list.add(entity);
					adicionarTotal( acumulador, entity );
				}
				tITENSRELATORIO = CobillingConstants.buscaConfigItens("38", analise);
				if (tITENSRELATORIO!=null) {
					//PenalidadeTO item1MenuTO = new PenalidadeTO();
					//item1MenuTO.setDsMotivoRejeicao(tITENSRELATORIO[1]+" "+tITENSRELATORIO[2]);
					//list.add(item1MenuTO);
					try {
						BeanUtils.copyProperties(subTotal1, acumulador);
					} catch (IllegalAccessException e) {
						throw new ServiceException(e.getMessage());
					} catch (InvocationTargetException e) {
						throw new ServiceException(e.getMessage());
					}
					
					subTotal1.setDsMotivoRejeicao(tITENSRELATORIO[1]+" "+tITENSRELATORIO[2]);
					list.add(subTotal1);
					acumulador = new SccQuestionamentoPenalidadeView();
				}
				tITENSRELATORIO = CobillingConstants.buscaConfigItens("39", analise);
				if (tITENSRELATORIO!=null) {
					SccQuestionamentoPenalidadeView item2MenuTO = new SccQuestionamentoPenalidadeView();
					item2MenuTO.setDsMotivoRejeicao(tITENSRELATORIO[1]+" "+tITENSRELATORIO[2]);
					list.add(item2MenuTO);
				}
			} else
				if ( entity.getCdPosicao().equalsIgnoreCase("50" )) {
					String[] tITENSRELATORIO = CobillingConstants.buscaConfigItens(entity.getCdPosicao(), analise);
					if (tITENSRELATORIO!=null) {
						entity.setDsMotivoRejeicao(tITENSRELATORIO[1]+" "+tITENSRELATORIO[2]);
						list.add(entity);
						adicionarTotal( entity, entity );
					}
					tITENSRELATORIO = CobillingConstants.buscaConfigItens("47", entity.getInResultadoAnalise());
					if (tITENSRELATORIO!=null) {
						//PenalidadeTO item1MenuTO = new PenalidadeTO();
						//item1MenuTO.setDsMotivoRejeicao(tITENSRELATORIO[1]+" "+tITENSRELATORIO[2]);
						//list.add(item1MenuTO);
						try {
							BeanUtils.copyProperties(subTotal2, acumulador);
						} catch (IllegalAccessException e) {
							throw new ServiceException(e.getMessage());
						} catch (InvocationTargetException e) {
							throw new ServiceException(e.getMessage());
							
						} 
						subTotal2.setDsMotivoRejeicao(tITENSRELATORIO[1]+" "+tITENSRELATORIO[2]);
						list.add(subTotal2);
						acumulador = new SccQuestionamentoPenalidadeView();						
					}
					tITENSRELATORIO = CobillingConstants.buscaConfigItens("48", analise);
					if (tITENSRELATORIO!=null) {
						//PenalidadeTO item2MenuTO = new PenalidadeTO();
						//item2MenuTO.setDsMotivoRejeicao(tITENSRELATORIO[1]+" "+tITENSRELATORIO[2]);
						//list.add(item2MenuTO);
						total = calcularTotal( subTotal1 , subTotal2);
						total.setDsMotivoRejeicao(tITENSRELATORIO[1]+" "+tITENSRELATORIO[2]);
						list.add(total);						
					}
			} else {
				String[] tITENSRELATORIO = CobillingConstants.buscaConfigItens(entity.getCdPosicao(), entity.getInResultadoAnalise());
				if (tITENSRELATORIO!=null) {
					entity.setDsMotivoRejeicao(tITENSRELATORIO[1]+" "+tITENSRELATORIO[2]);
					list.add(entity);
					adicionarTotal( acumulador, entity );
				}
			}
				
		}
			
	}
	
    @SuppressWarnings({ "unchecked", "rawtypes" })
	private void formatarPenalidadeNaoAplic( List list, Collection<SccQuestionamentoPenalidadeView> colecao ) throws Exception { 

    	SccQuestionamentoPenalidadeView subTotal1 = new SccQuestionamentoPenalidadeView();
    	SccQuestionamentoPenalidadeView acumulador = new SccQuestionamentoPenalidadeView();
    	SccQuestionamentoPenalidadeView total 	   = new SccQuestionamentoPenalidadeView();	       	         
    	    	    
    	if ( colecao != null && colecao.size() > 0 ){
    		String[] tITENMenu1 = CobillingConstants.buscaConfigItens("01", CobillingConstants.NAOAPLICAVEL);
    		if (tITENMenu1!=null) {
    			SccQuestionamentoPenalidadeView item1MenuTO = new SccQuestionamentoPenalidadeView();
    			item1MenuTO.setDsMotivoRejeicao(tITENMenu1[1]+" "+tITENMenu1[2]);
    			list.add(item1MenuTO);
    		}
    		String[] tITENMenu2 = CobillingConstants.buscaConfigItens("02", CobillingConstants.NAOAPLICAVEL);
    		if (tITENMenu2!=null) {
    			SccQuestionamentoPenalidadeView item2MenuTO = new SccQuestionamentoPenalidadeView();
    			item2MenuTO.setDsMotivoRejeicao(tITENMenu2[1]+" "+tITENMenu2[2]);
    			list.add(item2MenuTO);
    		}
    	}
    	for (SccQuestionamentoPenalidadeView entity : colecao) {
    		
    		if (entity.getCdPosicao().equalsIgnoreCase("40") || 
    				entity.getCdPosicao().equalsIgnoreCase("41") ||
    				entity.getCdPosicao().equalsIgnoreCase("43") ||
    				entity.getCdPosicao().equalsIgnoreCase("50" )) {
    			
    			String[] tITENSRELATORIO = CobillingConstants.buscaConfigItens(entity.getCdPosicao(), CobillingConstants.NAOAPLICAVEL);
    			if (tITENSRELATORIO!=null) {
    				entity.setDsMotivoRejeicao(tITENSRELATORIO[1]+" "+tITENSRELATORIO[2]);
    				list.add(entity);
    				adicionarTotal( acumulador, entity );
    			}					
    		} else
    			if (entity.getCdPosicao().equalsIgnoreCase("51")) {
    				String[] tITENSRELATORIO = CobillingConstants.buscaConfigItens(entity.getCdPosicao(), CobillingConstants.NAOAPLICAVEL);
    				if (tITENSRELATORIO!=null) {
    					entity.setDsMotivoRejeicao(tITENSRELATORIO[1]+" "+tITENSRELATORIO[2]);
    					list.add(entity);
    					adicionarTotal( acumulador, entity );
    				}
    				tITENSRELATORIO = CobillingConstants.buscaConfigItens("05", CobillingConstants.NAOAPLICAVEL);
    				if (tITENSRELATORIO!=null) {
    					//PenalidadeTO item1MenuTO = new PenalidadeTO();
    					//item1MenuTO.setDsMotivoRejeicao(tITENSRELATORIO[1]+" "+tITENSRELATORIO[2]);
    					//list.add(item1MenuTO);
    					BeanUtils.copyProperties(subTotal1, acumulador); 
    					subTotal1.setDsMotivoRejeicao(tITENSRELATORIO[1]+" "+tITENSRELATORIO[2]);
    					list.add(subTotal1);
    					acumulador = new SccQuestionamentoPenalidadeView();
    				}
    				tITENSRELATORIO = CobillingConstants.buscaConfigItens("06", CobillingConstants.NAOAPLICAVEL);
    				if (tITENSRELATORIO!=null) {
    					//PenalidadeTO item2MenuTO = new PenalidadeTO();
    					//item2MenuTO.setDsMotivoRejeicao(tITENSRELATORIO[1]+" "+tITENSRELATORIO[2]);
    					//list.add(item2MenuTO);
    					BeanUtils.copyProperties(total, subTotal1 ); 
						total.setDsMotivoRejeicao(tITENSRELATORIO[1]+" "+tITENSRELATORIO[2]);
						list.add(total);
						acumulador = new SccQuestionamentoPenalidadeView();	
    				}
    			}
    	}
    }    
	
	
	
    private void adicionarTotal( SccQuestionamentoPenalidadeView penalidadeTOTotal, SccQuestionamentoPenalidadeView penalidadeTO ){
    	penalidadeTOTotal.setQtCdrs( penalidadeTOTotal.getQtCdrs() + penalidadeTO.getQtCdrs()); 
    	penalidadeTOTotal.setQtMinutos( penalidadeTOTotal.getQtMinutos() + penalidadeTO.getQtMinutos());
    	penalidadeTOTotal.setVlLiquido( penalidadeTOTotal.getVlLiquido() + penalidadeTO.getVlLiquido());
    	penalidadeTOTotal.setVlBruto( penalidadeTOTotal.getVlBruto() + penalidadeTO.getVlBruto());
    	penalidadeTOTotal.setQtCdrsRefaturadas( penalidadeTOTotal.getQtCdrsRefaturadas() + penalidadeTO.getQtCdrsRefaturadas());
    	penalidadeTOTotal.setQtMinutosRefaturadas( penalidadeTOTotal.getQtMinutosRefaturadas() + penalidadeTO.getQtMinutosRefaturadas());
    	penalidadeTOTotal.setQtCdrsNaoRefaturadas( penalidadeTOTotal.getQtCdrsNaoRefaturadas() + penalidadeTO.getQtCdrsNaoRefaturadas());
    	penalidadeTOTotal.setQtMinutosNaoRefaturadas( penalidadeTOTotal.getQtMinutosNaoRefaturadas() + penalidadeTO.getQtMinutosNaoRefaturadas());
    	penalidadeTOTotal.setVlPenalidade( penalidadeTOTotal.getVlPenalidade() + penalidadeTO.getVlPenalidade());
    }
    
    private SccQuestionamentoPenalidadeView calcularTotal( SccQuestionamentoPenalidadeView subTotal1, SccQuestionamentoPenalidadeView subTotal2 ){
    	SccQuestionamentoPenalidadeView penalidadeTOTotal = new SccQuestionamentoPenalidadeView();
    	penalidadeTOTotal.setQtCdrs( subTotal1.getQtCdrs() + subTotal2.getQtCdrs()); 
    	penalidadeTOTotal.setQtMinutos( subTotal1.getQtMinutos() + subTotal2.getQtMinutos());
    	penalidadeTOTotal.setVlLiquido( subTotal1.getVlLiquido() + subTotal2.getVlLiquido());
    	penalidadeTOTotal.setVlBruto( subTotal1.getVlBruto() + subTotal2.getVlBruto());
    	penalidadeTOTotal.setQtCdrsRefaturadas( subTotal1.getQtCdrsRefaturadas() + subTotal2.getQtCdrsRefaturadas());
    	penalidadeTOTotal.setQtMinutosRefaturadas( subTotal1.getQtMinutosRefaturadas() + subTotal2.getQtMinutosRefaturadas());
    	penalidadeTOTotal.setQtCdrsNaoRefaturadas( subTotal1.getQtCdrsNaoRefaturadas() + subTotal2.getQtCdrsNaoRefaturadas());
    	penalidadeTOTotal.setQtMinutosNaoRefaturadas( subTotal1.getQtMinutosNaoRefaturadas() + subTotal2.getQtMinutosNaoRefaturadas());
    	penalidadeTOTotal.setVlPenalidade( subTotal1.getVlPenalidade() + subTotal2.getVlPenalidade());
    	return penalidadeTOTotal;
    }

	public void setSccQuestionamentoDAO(SccQuestionamentoDAO sccQuestionamentoDAO) {
		this.sccQuestionamentoDAO = sccQuestionamentoDAO;
	}

	public void setSccCdrQuestionamentoDAO(
			SccCdrQuestionamentoDAO sccCdrQuestionamentoDAO) {
		this.sccCdrQuestionamentoDAO = sccCdrQuestionamentoDAO;
	}


	
	
}