package copyleft.by.pc.pojos;

import java.util.Arrays;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = {"title", "message", "badge", "registrationIdsToSend"})
public class Notification {

    private Integer badge;

    private String title;

    private String message;

    private List<String> registrationIdsToSend;

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

    public List<String> getRegistrationIdsToSend() {
		return registrationIdsToSend;
	}

	public void setRegistrationIdsToSend(List<String> registrationIdsToSend) {
		this.registrationIdsToSend = registrationIdsToSend;
	}

	@Override
    public String toString() {
        return "Notification{" +
                "badge='" + badge + '\'' +
                ", title='" + title + '\'' +
                ", message='" + message + '\'' +
                ", registrationIdsToSend=" + (registrationIdsToSend == null ? null : Arrays.asList(registrationIdsToSend)) +
                '}';
    }
}
