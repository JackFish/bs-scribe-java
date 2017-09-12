package org.beansugar.oauth.o20.builder.api;

import org.beansugar.oauth.model.ParameterList;
import org.beansugar.oauth.o20.OAuth2Constants;
import org.beansugar.oauth.o20.model.OAuth20Config;
import org.beansugar.oauth.utils.ParameterUtils;
import org.scriptonbasestar.tool.core.check.Check;

public class Facebook20Api extends Default20Api {
	private static final String AUTHORIZE_URL = "https://www.facebook.com/dialog/oauth";
	private static final String ACCESS_TOKEN_ENDPOINT = "https://graph.facebook.com/oauth/access_token";

	@Override
	public String getAccessTokenUrl() {
		return ACCESS_TOKEN_ENDPOINT;
	}

	@Override
	public String getAuthorizationUrl(OAuth20Config config) {
		Check.urlCustomPattern(config.getCallback(), "Must provide a valid url as callback. Facebook does not support OOB");

		ParameterList parameterList = new ParameterList()
				.add(OAuth2Constants.CLIENT_ID.value, config.getApiKey())
				.add(OAuth2Constants.REDIRECT_URI.value, config.getCallback());
		if (config.getScope() != null) {
			parameterList.add(OAuth2Constants.SCOPE.value, config.getScope());
		}
		return ParameterUtils.buildUrl(AUTHORIZE_URL, parameterList);
	}
}
