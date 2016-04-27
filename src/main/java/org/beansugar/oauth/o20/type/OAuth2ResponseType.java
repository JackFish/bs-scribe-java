package org.beansugar.oauth.o20.type;

import lombok.Getter;

/**
 * @author archmagece
 * @date 2015-11-18
 */
public enum OAuth2ResponseType {
	//google,naver
	ACCESS_TOKEN("access_token"),
	//google,naver
	REFRESH_TOKEN("refresh_token"),
	//google,naver
	TOKEN_TYPE("token_type"),
	//google,naver
	EXPIRES_IN("expires_in"),
	//facebook
	EXPIRES("expires"),
	//google
	ID_TOKEN("id_token");

	@Getter
	public String value;

	OAuth2ResponseType(String value) {
		this.value = value;
	}

	public String toString() {
		return value;
	}

}
