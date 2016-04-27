package org.beansugar.oauth.o20;

import lombok.Getter;

/**
 * @author archmagece
 * @date 2015-11-16
 */
public enum OAuth2Constants {

	//OAuth 2.0
	ACCESS_TOKEN("access_token"),
	CLIENT_ID("client_id"),
	CLIENT_SECRET("client_secret"),
	REDIRECT_URI("redirect_uri"),
	CODE("code"),
	RESPONSE_TYPE("response_type"),
	GRANT_TYPE("grant_type"),
	STATE("state"),

	SCOPE("scope");

	@Getter
	public String value;

	OAuth2Constants(String value) {
		this.value = value;
	}

	public String toString() {
		return value;
	}
}
