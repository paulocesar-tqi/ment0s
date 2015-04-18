package copyleft.by.pc.common.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User implements Serializable{

	private static final long serialVersionUID = 373272697984502898L;

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="reg_id")
	private String regId;
	
	@Column(name="registration_date")
	private Date registrationDate;

	public User(String regId) {
		super();
		this.regId = regId;
		this.registrationDate = new Date();
	}

	public Long getId() {
		return id;
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


}