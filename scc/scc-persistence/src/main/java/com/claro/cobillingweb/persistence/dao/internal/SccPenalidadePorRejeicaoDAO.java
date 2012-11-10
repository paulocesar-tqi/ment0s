package com.claro.cobillingweb.persistence.dao.internal;

import java.util.Date;
import java.util.List;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccPenalidadePorRejeicao;
import com.claro.cobillingweb.persistence.view.PenalidadeRejeicaoView;

public interface SccPenalidadePorRejeicaoDAO extends BasicDAO<SccPenalidadePorRejeicao> {

	public List<SccPenalidadePorRejeicao> pesquisar(String cdEotLD, String cdMotivoRejeicao) throws DAOException;

	public List<PenalidadeRejeicaoView> pesquisarRelatorioPenalidadeRejeicao(String cdEOTClaro, String cdEOTLD, Long cdProdutoCobilling, Date dtInicialRepasse) throws DAOException;

}
