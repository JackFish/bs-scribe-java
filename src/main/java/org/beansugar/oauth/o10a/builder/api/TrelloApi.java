package org.beansugar.oauth.o10a.builder.api;

import org.beansugar.oauth.o10a.model.Token10a;

public class TrelloApi extends DefaultApi10a {
	private static final String AUTHORIZE_URL = "https://trello.com/1/OAuthAuthorizeToken?oauth_token=%s";
	private static final String REQUEST_TOKEN_RESOURCE = "https://trello.com/1/OAuthGetRequestToken";
	private static final String ACCESS_TOKEN_RESOURCE = "https://trello.com/1/OAuthGetAccessToken";

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
