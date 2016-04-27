package org.beansugar.oauth.o20.service;

import lombok.extern.slf4j.Slf4j;
import org.beansugar.oauth.model.Verifier;
import org.beansugar.oauth.model.type.GrantType;
import org.beansugar.oauth.model.type.HttpVerb;
import org.beansugar.oauth.model.type.OAuthConstants;
import org.beansugar.oauth.net.OAuthRequest;
import org.beansugar.oauth.net.Response;
import org.beansugar.oauth.o20.OAuth2Constants;
import org.beansugar.oauth.o20.builder.api.Default20Api;
import org.beansugar.oauth.o20.model.OAuth20Config;
import org.beansugar.oauth.o20.model.Token20Result;

/**
 * @author archmagece
 * @since 2014-11-14-17
 */
@Slf4j
public class OAuth20ServiceSimple implements OAuth20Service {

	private final Default20Api api;
	private final OAuth20Config config;

	public OAuth20ServiceSimple(Default20Api api, OAuth20Config config) {
		this.api = api;
		this.config = config;
	}

	@Override
	public String getAuthorizationUrl() {
		return api.getAuthorizationUrl(config);
	}

	@Override
	public Token20Result getTokenResult(Verifier verifier, String state) {
		OAuthRequest request = new OAuthRequest(api.getAccessTokenVerb(), api.getAccessTokenUrl());
		switch (api.getAccessTokenVerb()) {
			case POST:
				request.addBodyParameter(OAuth2Constants.CLIENT_ID.toString(), config.getApiKey());
				//TODO 카카오만 secret을 안씀 미친 필수값이잖아
				if(config.getApiSecret()!=null && !config.getApiSecret().isEmpty()){
					request.addBodyParameter(OAuth2Constants.CLIENT_SECRET.toString(), config.getApiSecret());
				}
				request.addBodyParameter(OAuth2Constants.CODE.toString(), verifier.getValue());
				request.addBodyParameter(OAuth2Constants.REDIRECT_URI.toString(), config.getCallback());
				request.addBodyParameter(OAuth2Constants.GRANT_TYPE.toString(), GrantType.AUTHORIZATION_CODE.toString());
				if (state != null && !state.isEmpty()) {
					request.addBodyParameter(OAuth2Constants.STATE.toString(), state);
				}
				break;
			case GET:
			default:
				request.addQueryStringParameter(OAuth2Constants.CLIENT_ID.toString(), config.getApiKey());
				//TODO 카카오만 secret을 안씀 미친 필수값이잖아
				if(config.getApiSecret()!=null && !config.getApiSecret().isEmpty()){
					request.addQueryStringParameter(OAuth2Constants.CLIENT_SECRET.toString(), config.getApiSecret());
				}
				request.addQueryStringParameter(OAuth2Constants.CODE.toString(), verifier.getValue());
				request.addQueryStringParameter(OAuth2Constants.REDIRECT_URI.toString(), config.getCallback());
				request.addQueryStringParameter(OAuth2Constants.GRANT_TYPE.toString(), GrantType.AUTHORIZATION_CODE.toString());
				if (state != null && !state.isEmpty()) {
					request.addQueryStringParameter(OAuth2Constants.STATE.toString(), state);
				}
				if (config.getScope() != null && !config.getScope().isEmpty()) {
					request.addQueryStringParameter(OAuthConstants.SCOPE.toString(), config.getScope());
				}
		}
		Response response = request.send();
		return api.getAccessTokenExtractor().extract(response.getBody());
	}

	@Override
	public Response getResult(HttpVerb verb, String resource, Token20Result accessToken) {
		OAuthRequest request = new OAuthRequest(verb, resource);
		request.addQueryStringParameter(OAuth2Constants.ACCESS_TOKEN.getValue(), accessToken.getAccessToken());
		return request.send();
	}
}
