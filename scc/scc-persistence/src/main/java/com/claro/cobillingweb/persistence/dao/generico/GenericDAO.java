/**
 * 
 */
package com.claro.cobillingweb.persistence.dao.generico;

import java.io.Serializable;
import java.util.List;

/**
 * @author rodvagne
 *
 */
interface GenericDAO<T, PK extends Serializable> {
	
	
	T save(T object);
	T load(PK primaryKey);
	T get(PK primaryKey);
	@SuppressWarnings("rawtypes")
	List listAll();
	@SuppressWarnings("rawtypes")
	List findByExample(final T example);
	T findOneByExample(final T example);
	@SuppressWarnings("rawtypes")
	List listAll(final int first,final int max);
	int listAllPageCount();
	@SuppressWarnings("rawtypes")
	List findByExample(final T example,final int first,final int max);
	int findByExamplePageCount(final T example);
	void update(T object);
	void delete(T object);
	void rebind(T object);

}
