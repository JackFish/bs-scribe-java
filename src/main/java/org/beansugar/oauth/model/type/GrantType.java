package org.beansugar.oauth.model.type;

import lombok.Getter;

/**
 * @author archmagece
 * @since 2015-01-30-10
 * <p>
 * 구글때문에 추가. 인증방식을 코드로 할지 비밀번호로 할지 선택하는듯 다른 변수값이 있는지 확인 필요.
 * UserCredentials
 * RefreshToken
 * AuthorizationCode
 * <p>
 * Error Response
 * Access Token10a Request : grant_type=authorization_code
 * <p>
 * Resource Owner Password Credentials Grant
 * Access Token10a Request : grant_type=password
 * <p>
 * Client Credentials Grant
 * Access Token10a Request : grant_type=client_credentials
 */
public enum GrantType {
	AUTHORIZATION_CODE("authorization_code"),
	PASSWORD("password"),
	IMPLICIT("implicit"),
	CLIENT_CREDENTIALS("client_credentials"),
	REFRESH_TOKEN("refresh_token");

	@Getter
	public String value;

	GrantType(String value) {
		this.value = value;
	}

	public String toString() {
		return value;
	}
}
