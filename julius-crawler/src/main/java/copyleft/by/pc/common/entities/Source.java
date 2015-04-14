package copyleft.by.pc.common.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="source")
public class Source implements Serializable{

	private static final long serialVersionUID = -7340774847445899229L;

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="active")
	private Integer active; 

	@Column(name="url_query")
	private String urlQuery;

	@Column(name="days_to_purge")
	private Integer daysToPurge;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getActive() {
		return active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}

	public String getUrlQuery() {
		return urlQuery;
	}

	public void setUrlQuery(String urlQuery) {
		this.urlQuery = urlQuery;
	}

	public Integer getDaysToPurge() {
		return daysToPurge;
	}

	public void setDaysToPurge(Integer daysToPurge) {
		this.daysToPurge = daysToPurge;
	}
}