package com.claro.cobillingweb.persistence.dao.internal;

import java.io.Serializable;
import java.util.List;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.FwjBaseDaoHibernate;
import com.claro.cobillingweb.persistence.entity.SccPreItemTarifaAcb;
import com.claro.cobillingweb.persistence.entity.SccPreTarifaAcb;

public interface SccTarifasCobrarDAO extends FwjBaseDaoHibernate<SccPreTarifaAcb, Serializable> {

	List<SccPreTarifaAcb> findByOperadora(String cdEotLd) throws DAOException;

	List<SccPreItemTarifaAcb> findByIdTarifa(Long idTarifa) throws DAOException;

	SccPreTarifaAcb findById(Long idTarifa) throws DAOException;

}
