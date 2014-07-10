package vk.auth;

import java.util.Date;

public class Token {
	final private String id;
	final private String token;
	final private Date expires;

    /**
     *
     * @return Id of current user
     */
	public String getId() {
		return id;
	}

    /**
     *
     * @return Token of current session
     */
	public String getToken() {
		return token;
	}

	public Token(String id, String token, Date expires){
		this.id = id;
		this.token = token;
		this.expires = expires;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((token == null) ? 0 : token.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Token other = (Token) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (token == null) {
			if (other.token != null)
				return false;
		} else if (!token.equals(other.token))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Token [id=" + id + ", token=" + token + "]";
	}
	
	public boolean isExpired() {
		return System.currentTimeMillis() > expires.getTime();
	}
	
	
}
