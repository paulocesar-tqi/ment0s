package com.claro.cobillingweb.persistence.entity.external;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.claro.cobillingweb.persistence.entity.BaseEntity;

/**
 * Entidade que representa a tabela CTRL_CONNECT_FILES.  
 *
 */
@Entity
@Table(name="CTRL_CONNECT_FILES")
public class ControlConnectFile implements BaseEntity {
	
	private ControlConnectFilePk pk;
	private Date	STOP_DATE;
	private String	SUBM_NODE;
	private Integer	EXIT_CODE;
	private String	EXIT_DESC;
	private String	ORIG_NODE;
	private String	ORIG_PATH;
	private String	ORIG_FILE;
	private String	DEST_NODE;
	private String	DEST_PATH;
	private String	DEST_FILE;
	private Integer	RECS_READ;
	private Integer	BYTE_READ;
	private Integer	BYTE_SENT;
	private Integer	BYTE_RECV;
	private Integer	BYTE_WRIT;
	private Integer	RECS_WRIT;
	private Date	COBL_Date;
	
	@Id
	public ControlConnectFilePk getPk() {
		return pk;
	}
	public void setPk(ControlConnectFilePk pk) {
		this.pk = pk;
	}
	@Column(name="STOP_DATE")
	public Date getSTOP_DATE() {
		return STOP_DATE;
	}
	public void setSTOP_DATE(Date sTOP_DATE) {
		STOP_DATE = sTOP_DATE;
	}
	
	@Column(name="SUBM_NODE")
	public String getSUBM_NODE() {
		return SUBM_NODE;
	}
	public void setSUBM_NODE(String sUBM_NODE) {
		SUBM_NODE = sUBM_NODE;
	}
	
	@Column(name="EXIT_CODE")
	public Integer getEXIT_CODE() {
		return EXIT_CODE;
	}
	public void setEXIT_CODE(Integer eXIT_CODE) {
		EXIT_CODE = eXIT_CODE;
	}
	
	@Column(name="EXIT_DESC")
	public String getEXIT_DESC() {
		return EXIT_DESC;
	}
	public void setEXIT_DESC(String eXIT_DESC) {
		EXIT_DESC = eXIT_DESC;
	}
	
	@Column(name="ORIG_NODE")
	public String getORIG_NODE() {
		return ORIG_NODE;
	}
	public void setORIG_NODE(String oRIG_NODE) {
		ORIG_NODE = oRIG_NODE;
	}
	
	@Column(name="ORIG_PATH")
	public String getORIG_PATH() {
		return ORIG_PATH;
	}
	public void setORIG_PATH(String oRIG_PATH) {
		ORIG_PATH = oRIG_PATH;
	}
	
	@Column(name="ORIG_FILE")
	public String getORIG_FILE() {
		return ORIG_FILE;
	}
	public void setORIG_FILE(String oRIG_FILE) {
		ORIG_FILE = oRIG_FILE;
	}
	
	@Column(name="DEST_NODE")
	public String getDEST_NODE() {
		return DEST_NODE;
	}
	public void setDEST_NODE(String dEST_NODE) {
		DEST_NODE = dEST_NODE;
	}
	
	@Column(name="DEST_PATH")
	public String getDEST_PATH() {
		return DEST_PATH;
	}
	public void setDEST_PATH(String dEST_PATH) {
		DEST_PATH = dEST_PATH;
	}
	
	@Column(name="DEST_FILE")
	public String getDEST_FILE() {
		return DEST_FILE;
	}
	public void setDEST_FILE(String dEST_FILE) {
		DEST_FILE = dEST_FILE;
	}
	
	@Column(name="RECS_READ")
	public Integer getRECS_READ() {
		return RECS_READ;
	}
	public void setRECS_READ(Integer rECS_READ) {
		RECS_READ = rECS_READ;
	}
	
	@Column(name="BYTE_READ")
	public Integer getBYTE_READ() {
		return BYTE_READ;
	}
	public void setBYTE_READ(Integer bYTE_READ) {
		BYTE_READ = bYTE_READ;
	}
	
	@Column(name="BYTE_SENT")
	public Integer getBYTE_SENT() {
		return BYTE_SENT;
	}
	public void setBYTE_SENT(Integer bYTE_SENT) {
		BYTE_SENT = bYTE_SENT;
	}
	
	@Column(name="BYTE_RECV")
	public Integer getBYTE_RECV() {
		return BYTE_RECV;
	}
	public void setBYTE_RECV(Integer bYTE_RECV) {
		BYTE_RECV = bYTE_RECV;
	}
	
	@Column(name="BYTE_WRIT")
	public Integer getBYTE_WRIT() {
		return BYTE_WRIT;
	}
	public void setBYTE_WRIT(Integer bYTE_WRIT) {
		BYTE_WRIT = bYTE_WRIT;
	}
	
	@Column(name="RECS_WRIT")
	public Integer getRECS_WRIT() {
		return RECS_WRIT;
	}
	public void setRECS_WRIT(Integer rECS_WRIT) {
		RECS_WRIT = rECS_WRIT;
	}
	
	@Column(name="COBL_DATE")
	public Date getCOBL_Date() {
		return COBL_Date;
	}
	public void setCOBL_Date(Date cOBL_Date) {
		COBL_Date = cOBL_Date;
	}	
	
}
