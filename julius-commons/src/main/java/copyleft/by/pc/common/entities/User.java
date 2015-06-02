package copyleft.by.pc.common.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name="user")
@JsonInclude(Include.NON_NULL)
public class User implements Serializable{

	private static final long serialVersionUID = 373272697984502898L;

	@Id
	@Column(name="reg_id")
	private String regId;
	
	@Column(name="platform")
	private String platform;
	
	@Column(name="registration_date")
	private Date registrationDate;

	@Column(name="active_notifications")
	private Integer activeNotifications;

	@Column(name="active_vibration")
	private Integer activeVibration;
	
	@Column(name="active_filter")
	private Integer activeFilter;

	@Column(name="word1")
	private String word1;

	@Column(name="word2")
	private String word2;

	@Column(name="word3")
	private String word3;
	
	
	public User() {
		super();
	}

	public User(String regId, String platform) {
		super();
		this.regId = regId;
		this.platform = platform;
		this.registrationDate = new Date();
		this.activeNotifications = 1; //active by default
		this.activeVibration = 1; //active by default
		this.activeFilter = 0; //inactive by default
	}

	public String getRegId() {
		return regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public Integer getActiveNotifications() {
		return activeNotifications;
	}

	public void setActiveNotifications(Integer activeNotifications) {
		this.activeNotifications = activeNotifications;
	}

	public Integer getActiveVibration() {
		return activeVibration;
	}

	public void setActiveVibration(Integer activeVibration) {
		this.activeVibration = activeVibration;
	}

	public Integer getActiveFilter() {
		return activeFilter;
	}

	public void setActiveFilter(Integer activeFilter) {
		this.activeFilter = activeFilter;
	}

	public String getWord1() {
		return word1;
	}

	public void setWord1(String word1) {
		this.word1 = word1;
	}

	public String getWord2() {
		return word2;
	}

	public void setWord2(String word2) {
		this.word2 = word2;
	}

	public String getWord3() {
		return word3;
	}

	public void setWord3(String word3) {
		this.word3 = word3;
	}

}