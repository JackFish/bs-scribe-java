package org.beansugar.oauth.o10a.builder.api;

import lombok.extern.slf4j.Slf4j;
import org.beansugar.oauth.o10a.model.Token10a;

@Slf4j
public class BSsso1Api extends DefaultApi10a {
	private static final String AUTHORIZE_URL = "http://localhost:8080/oauth1/oauth/confirm_access?oauth_token=%s";
	private static final String REQUEST_TOKEN_RESOURCE = "http://localhost:8080/oauth1/oauth/request_token";
	private static final String ACCESS_TOKEN_RESOURCE = "http://localhost:8080/oauth1/oauth/access_token";

	@Override
	public String getAuthorizationUrl(Token10a requestToken) {
		return String.format(AUTHORIZE_URL, requestToken.getToken());
	}

	@Override
	public String getRequestTokenUrl() {
		return REQUEST_TOKEN_RESOURCE;
	}

	@Override
	public String getAccessTokenUrl() {
		return ACCESS_TOKEN_RESOURCE;
	}

}
