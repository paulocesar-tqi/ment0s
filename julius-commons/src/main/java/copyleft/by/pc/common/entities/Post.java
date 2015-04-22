package copyleft.by.pc.common.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "post")
@JsonInclude(Include.NON_NULL)
public class Post implements Serializable {

	private static final long serialVersionUID = 219264453988823416L;

	public Post() {
		
	}
	
	public Post(Long id, String title, Date publicationDate, Integer sourceId) {
		super();
		this.id = id;
		this.title = title;
		this.publicationDate = publicationDate;
		this.sourceId = sourceId;
	}

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "title")
	private String title;

	@Column(name = "html")
	private String html;

	@Column(name = "url")
	private String url;

	@Column(name = "publication_date")
	@JsonFormat(pattern="dd/MM/yyyy HH:mm", timezone="GMT-3:00")
	private Date publicationDate;

	@Column(name = "source_id")
	private Integer sourceId;

	@Column(name = "external_id")
	private String externalId;

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public Date getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
	}

	public Integer getSourceId() {
		return sourceId;
	}

	public void setSourceId(Integer sourceId) {
		this.sourceId = sourceId;
	}

	public String getExternalId() {
		return externalId;
	}

	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}