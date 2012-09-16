package br.com.sky;

public class Cookie {
	private String key;
	private String value;
	private String domain;
	private String path;
	private String rawData;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getRawData() {
		return rawData;
	}

	public void parseValue(String value) {
		rawData = key + "=" + value;
		// check=1297971204264; Domain=.globo.com; Path=/
		String[] values = value.split(";");
		for (String val : values) {
			String[] vals = val.split("=");
			if (vals.length > 1) {
				String valKey = vals[0];
				if (valKey != null) {
					valKey = valKey.trim();
					if ("Domain".equalsIgnoreCase(vals[0])) {
						setDomain(vals[1]);
					} else if ("Path".equalsIgnoreCase(vals[0])) {
						setPath(vals[1]);
					}
				}
			} else {
				setValue(val);
			}
		}
	}
}
