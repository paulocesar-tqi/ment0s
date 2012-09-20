package com.claro.cobillingweb.persistence.view.mapper;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.claro.cobillingweb.persistence.dao.DAOException;

/**
 * Certas pesquisas no SCC Evolution terão melhor performance e facilidade de 
 * manutenção se usarmos o SQL nativo original.
 * Para transformar essas pesquisas em objetos Java utiliza-se um ViewMapper.
 * O ViewMapper transforma uma List de array de objetos List<Object[]> (que é o retorno
 * do Hibernate para NativeSQL) em uma lista de objetos.
 *
 */
public class NativeSQLViewMapper<E> {
	
	protected Logger logger = Logger.getLogger(NativeSQLViewMapper.class);
	
	public static final Integer DEFAULT_PAGE_SIZE = 100;
		
	private List<ViewResultMap> resultMapping = new ArrayList<ViewResultMap>();
	private StringBuffer SQL;
	private Session session;
	private String projections;
	
	private SQLQuery query;
	private Class<E> resultClass;
	private Map<String , Object> arguments = new HashMap<String, Object>();
	
	private Integer pageNumber;
	private Integer rowsPerPage;
	
	/**	  
	 * @param session Hibernate session
	 * @param sql Instrução SQL para pesquisa.
	 * @param resultClass
	 */
	public NativeSQLViewMapper(Session session,String sql,Class<E> resultClass) {
		SQL = new StringBuffer(sql);		
		this.resultClass = resultClass;
		this.session = session;
	}
	
	public void addResultMap(String propertyName,Class clazz) {
		ViewResultMap mapping = new ViewResultMap(propertyName, clazz);		
		resultMapping.add(mapping);		
	}
	
	/**
	 * Configura o valor de um argumento da query.
	 * @param name
	 * @param value
	 * @throws DAOException
	 */
	public void addArgument(String name,Object value) throws DAOException {
		arguments.put(name, value);
	}
	
	/**
	 * Configura o valor de um argumento da query.
	 * @param name
	 * @param value
	 * @throws DAOException
	 */
	public void addArgument(String name,Object value,String SQL) throws DAOException {
		arguments.put(name, value);
		appendSQL(SQL);
	}
	
	/**
	 * Modifica dinamicamente o SQL nativo.
	 * @param sql
	 */
	public void appendSQL(String sql) {
		if (!sql.startsWith(" ")) {
			sql = " "+sql;
		}
		SQL.append(sql);
	}
	
	private void populateArguments() throws DAOException {
		Iterator<String> names = arguments.keySet().iterator();
		while (names.hasNext()) {
			String name = names.next();
			Object value = arguments.get(name);			
			if (value instanceof String)
				query.setString(name, (String)value);
			else if (value instanceof Integer)
				query.setInteger(name, (Integer)value);
			else if (value instanceof Double)
				query.setDouble(name, (Double)value);
			else if (value instanceof Date)
				query.setDate(name, (Date)value);
			else if (value instanceof Long)
				query.setLong(name, (Long)value);
			else if (value instanceof Long[])
				query.setParameterList(name, (Long[])value);
			else
				throw new DAOException("Tipo "+value.getClass()+" não reconhecido por ");
		}
	}
	
	/**
	 * Executa a pesquisa.
	 * @return
	 * @throws DAOException
	 */
	public List<E> execute() throws DAOException {
		if (getProjections() != null) {
			SQL.append(" ");
			SQL.append(getProjections());
		}
		
		query = session.createSQLQuery(SQL.toString());		
		populateArguments();
		List<E> results = new ArrayList<E>();
		
		List<Object[]> rows = query.list();
		if ((pageNumber != null) && (pageNumber > 0)) {
			if (rowsPerPage == null)
				rowsPerPage = DEFAULT_PAGE_SIZE;
			query.setFirstResult(pageNumber*rowsPerPage);
		}
		try {
			for (int i=0;i<rows.size();i++) {
				E item = this.resultClass.newInstance();
				for (int m=0;m<resultMapping.size();m++) {
					ViewResultMap itemMap = resultMapping.get(m);
					String property = itemMap.getPropertyName();
					String methodName = "set"+property.replaceFirst(property.substring(0, 1),property.substring(0, 1).toUpperCase());
					
					Method setter = this.resultClass.getDeclaredMethod(methodName, itemMap.getType());
					
					if ((rows.get(i)[m] != null) && (rows.get(i)[m] instanceof BigDecimal)) {
						//System.out.println("Setter (fixBigDecimal) "+setter.getName()+" com valor "+rows.get(i)[m]);
						setter.invoke(item, fixBigDecimal(itemMap.getType(), (BigDecimal)rows.get(i)[m]));
						
					} else if ((rows.get(i)[m] != null) && (rows.get(i)[m] instanceof Character)) {
						//System.out.println("Setter (fixCharacter) "+setter.getName()+" com valor "+rows.get(i)[m]);
						setter.invoke(item, fixCharacter((Character)rows.get(i)[m]));
						
					} else if ((rows.get(i)[m] != null) && (rows.get(i)[m] instanceof Timestamp)) {
						//System.out.println("Setter (fixTimeStamp) "+setter.getName()+" com valor "+rows.get(i)[m]);
						setter.invoke(item, fixTimeStamp((Timestamp)rows.get(i)[m]));
						
					} else {
						if (rows.get(i)[m] != null) {
							//System.out.println("Setter "+setter.getName()+" com valor "+rows.get(i)[m]+" e tipo "+rows.get(i)[m].getClass());
							setter.invoke(item, rows.get(i)[m]);
							
						} else {
							//System.out.println("Setter "+setter.getName()+" com valor null");
							setter.invoke(item, " ");
							
						}
					}
					
				}
				results.add(item);
			} 
			return results;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	
	/**
	 * Driver Oracle retorna Bigdecimal para Hibernate. Como SCC não utiliza esse tipo , é necessária fazer uma 
	 * conversão para o tipo esperado.
	 * @param clazz Double ou Long
	 * @param value
	 * @return
	 * @throws DAOException
	 */
	private Object fixBigDecimal(Class clazz,BigDecimal value) throws DAOException {
		Object result = null;
		if (clazz.equals(Double.class))
			result =  value.doubleValue();
		else if (clazz.equals(Long.class))
			result =  value.longValue();
		else if (clazz.equals(Integer.class))
			result =  value.intValue();
		else
			throw new DAOException("Tipo "+clazz+" não suportado por fixBigDecimal().");
		return result;
	}
	
	private String fixCharacter(Character value) {		
		if (value == null)
			return " ";
		else
			return value.toString();
	}
	
	private Date fixTimeStamp(Timestamp value) {
		if (value == null)
			return null;
		else
			return new Date(value.getTime());
	}
	
	public String getProjections() {
		return projections;
	}
	
	public void setProjections(String projections) {
		this.projections = projections;
	}
	
	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}
	
	public void setRowsPerPage(Integer rowsPerPage) {
		this.rowsPerPage = rowsPerPage;
	}
	
}

/**
 * Inner class para mapear os objetos de retorno.
 *
 */
class ViewResultMap {
	
	private String propertyName;
	private Class type;
	
	public ViewResultMap(String propertyName,Class type) {
		this.propertyName = propertyName;
		this.type = type;
	}
	
	public String getPropertyName() {
		return propertyName;
	}
	
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
	
	public Class getType() {
		return type;
	}
	
	public void setType(Class type) {
		this.type = type;
	}
	
}
