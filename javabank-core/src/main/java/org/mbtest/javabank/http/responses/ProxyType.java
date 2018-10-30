package org.mbtest.javabank.http.responses;

//@formatter:off
public enum ProxyType {
	PROXY_ALWAYS("proxyAlways"), 
	PROXY_ONCE("proxyOnce");

	private String value;

	ProxyType(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
