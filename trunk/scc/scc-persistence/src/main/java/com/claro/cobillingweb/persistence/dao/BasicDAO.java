package com.claro.cobillingweb.persistence.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Interface de DAO com métodos de CRUD.
 *
 */
public interface BasicDAO<E> {
	
	/**
	 * Constante para indicar que o campo foi informado como * (todos) em uma pesquisa.
	 */
	public static final Long GET_ALL = -1L;
	
	/**
	 * Constante para indicar que o campo foi informado como * (todos) em uma pesquisa quando a chave primária é um campo de texto.
	 */
	public static final String GET_ALL_STRING = "*";
	
	/**
	 * Constante para indicar que nenhum valor foi selecionado.
	 */
	public static final long NULL = -999;
	
	public static final double NULL_DOUBLE = -999;
	
	/**
	 * Constante para indicar que nenhum valor foi selecionado.
	 */
	public static final String NULL_STRING = "NULL";
	
	public E getByPk(Serializable pk,Class entityClazz) throws DAOException;
	
	public void delete(E entity) throws DAOException;
	
	public Serializable create(E entity) throws DAOException;
	
	public void update(E entity) throws DAOException;
	
	public List<E> getAll() throws DAOException;
	
}
