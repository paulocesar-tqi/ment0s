/**
 * 
 */
package com.claro.cobillingweb.persistence.utils;

import java.io.Serializable;

/**
 * @author vagner.souza
 *
 */
public class StringValueBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6206977341858952238L;
	
    
	private String key;
    private String value;
    
    /**
     * Default Constructor
     */
    public StringValueBean() {
        
    }
    
    /**
     * Constructor with parameters
     * @param key
     * @param value
     */
    public StringValueBean(String key, String value) {
        this.key = key;
        this.value = value;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {
    	if (obj==this) { return true; }
    	if (obj!=null && obj instanceof StringValueBean) {
    		StringValueBean other = (StringValueBean) obj;
    		if (CommonsUtils.isEquals(key, other.key)) {
    			if (CommonsUtils.isEquals(value, other.value)) {
    				return true;
    			}
    		}
    	}
    	return false;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("["+this.getClass().getName()+": ");
        sb.append("key="+key);
        sb.append(", value="+value);
        sb.append("]");
        return sb.toString();
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        int hash = 1;
        hash += CommonsUtils.getHash(key);
        hash += 37;
        hash += CommonsUtils.getHash(value);
        return hash;
    }

    /**
     * @return Returns the key.
     */
    public String getKey() {
        return key;
    }
    
    /**
     * @param key The key to set.
     */
    public void setKey(String key) {
        this.key = key;
    }
    
    /**
     * @return Returns the value.
     */
    public String getValue() {
        return value;
    }
    
    /**
     * @param value The value to set.
     */
    public void setValue(String value) {
        this.value = value;
    }


}
