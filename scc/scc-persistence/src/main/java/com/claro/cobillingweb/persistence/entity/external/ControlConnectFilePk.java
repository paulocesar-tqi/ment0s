package com.claro.cobillingweb.persistence.entity.external;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ControlConnectFilePk implements Serializable {

	private Integer	PROC_NUMB;
	private Date	STRT_DATE;
	
	@Column(name="PROC_NUMB")
	public Integer getPROC_NUMB() {
		return PROC_NUMB;
	}
	public void setPROC_NUMB(Integer pROC_NUMB) {
		PROC_NUMB = pROC_NUMB;
	}
	
	@Column(name="STRT_DATE")
	public Date getSTRT_DATE() {
		return STRT_DATE;
	}
	public void setSTRT_DATE(Date sTRT_DATE) {
		STRT_DATE = sTRT_DATE;
	}
	
}
