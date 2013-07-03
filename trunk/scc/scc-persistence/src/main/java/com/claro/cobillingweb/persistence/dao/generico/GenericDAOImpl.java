/**
 * 
 */
package com.claro.cobillingweb.persistence.dao.generico;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * @author rodvagne
 *
 */
public class GenericDAOImpl<T, PK extends  Serializable> implements GenericDAO<T, PK> {
	
	private static Log LOG = LogFactory.getLog(GenericDAOImpl.class);
	
	private Class<T> type;
	
	@Autowired
    private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
	
    protected SessionFactory getSessionFactory() {
        if (sessionFactory == null)
            throw new IllegalStateException("SessionFactory has not been set on DAO before usage");
        return sessionFactory;
    }
    
  

    public Class<T> getType() {
        return type;
    }

    @SuppressWarnings("unchecked")
	public GenericDAOImpl() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }



	@Override
	public T save(T object) {
		return (T) this.sessionFactory.getCurrentSession().save(object);
		
	}

	@Override
	public T load(PK primaryKey) {
		return (T) this.sessionFactory.getCurrentSession().load(getType(), primaryKey);
		
	}

	@Override
	public T get(PK primaryKey) {
		return (T)this.sessionFactory.getCurrentSession().load(getType(), primaryKey);
	}

	@Override
	public List listAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List findByExample(T example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T findOneByExample(T example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List listAll(int first, int max) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int listAllPageCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List findByExample(T example, int first, int max) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int findByExamplePageCount(T example) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void update(T object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(T object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rebind(T object) {
		// TODO Auto-generated method stub
		
	}

}
