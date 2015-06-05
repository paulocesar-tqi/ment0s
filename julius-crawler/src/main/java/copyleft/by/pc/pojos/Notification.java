package copyleft.by.pc.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = {"title", "message", "badge", "registrationIdsToSend"})
public class Notification implements Serializable{

	private static final long serialVersionUID = -5877586453974207878L;

	private Integer badge;

    private String title;

    private String message;

    private List<String> androidIdsToSend;

    private List<String> iosIdsToSend;
    
    private List<String> wpIdsToSend;

    public Notification(String title, String message, List<String> androidIdsToSend, List<String> iosIdsToSend, List<String> wpIdsToSend) {
		super();
		this.title = title;
		this.message = message;
		
		if(androidIdsToSend == null)
			this.androidIdsToSend = new ArrayList<String>();
		else
			this.androidIdsToSend = androidIdsToSend;
		
		if(iosIdsToSend == null)
			this.iosIdsToSend = new ArrayList<String>();
		else
			this.iosIdsToSend = iosIdsToSend;
		
		if(wpIdsToSend == null)
			this.wpIdsToSend = new ArrayList<String>();
		else
			this.wpIdsToSend = wpIdsToSend;
	}

	public List<String> getAndroidIdsToSend() {
		return androidIdsToSend;
	}

	public void setAndroidIdsToSend(List<String> androidIdsToSend) {
		this.androidIdsToSend = androidIdsToSend;
	}

	public List<String> getIosIdsToSend() {
		return iosIdsToSend;
	}

	public void setIosIdsToSend(List<String> iosIdsToSend) {
		this.iosIdsToSend = iosIdsToSend;
	}

	public List<String> getWpIdsToSend() {
		return wpIdsToSend;
	}

	public void setWpIdsToSend(List<String> wpIdsToSend) {
		this.wpIdsToSend = wpIdsToSend;
	}

	public Integer getBadge() {
        return badge;
    }

    public void setBadge(Integer badge) {
        this.badge = badge;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

  
	@Override
    public String toString() {
        return "Notification{" +
                "badge='" + badge + '\'' +
                ", title='" + title + '\'' +
                ", message='" + message + '\'' +
                ", androidIdsToSend=" + (androidIdsToSend == null ? null : Arrays.asList(androidIdsToSend)) +
                ", iosIdsToSend=" + (iosIdsToSend == null ? null : Arrays.asList(iosIdsToSend)) +
                ", wpIdsToSend=" + (wpIdsToSend == null ? null : Arrays.asList(wpIdsToSend)) +
                '}';
    }
}
