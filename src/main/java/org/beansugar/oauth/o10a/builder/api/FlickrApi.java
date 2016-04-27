package org.beansugar.oauth.o10a.builder.api;

import org.beansugar.oauth.o10a.model.Token10a;

public class FlickrApi extends DefaultApi10a {
	private static final String AUTHORIZE_URL = "https://www.flickr.com/services/oauth/authorize?oauth_token=%s";
	private static final String REQUEST_TOKEN_RESOURCE = "https://www.flickr.com/services/oauth/request_token";
	private static final String ACCESS_TOKEN_RESOURCE = "https://www.flickr.com/services/oauth/access_token";

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
