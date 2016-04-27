package org.beansugar.oauth.o20.builder.api;

import org.beansugar.oauth.model.ParameterList;
import org.beansugar.oauth.model.type.HttpVerb;
import org.beansugar.oauth.o20.OAuth2Constants;
import org.beansugar.oauth.o20.extractor.AccessTokenExtractor20;
import org.beansugar.oauth.o20.model.OAuth20Config;
import org.beansugar.oauth.o20.model.Token20Result;
import org.beansugar.oauth.o20.service.OAuth20Service;
import org.beansugar.oauth.o20.service.OAuth20ServiceSimple;
import org.beansugar.oauth.o20.type.OAuth2ResponseType;
import org.beansugar.oauth.utils.JsonUtil;
import org.beansugar.oauth.utils.ParameterUtils;
import org.beansugar.tools.collection.builder.SetBuilder;
import org.beansugar.tools.core.check.Check;

import java.util.Map;
import java.util.Set;

public class Google20Api extends Default20Api {
	//TODO 필수컬럼 등 필요한거 정리해서 변경.
	private static final String AUTHORIZE_URL = "https://accounts.google.com/o/oauth2/auth";
	private Set<OAuth2Constants> authEssential = SetBuilder.create(OAuth2Constants.class)
			.a(OAuth2Constants.CLIENT_ID, OAuth2Constants.REDIRECT_URI)
			.build();
	private Set<OAuth2Constants> authFixed = SetBuilder.create(OAuth2Constants.class)
			.a(OAuth2Constants.RESPONSE_TYPE)
			.build();
	private Set<OAuth2Constants> authOptional = SetBuilder.create(OAuth2Constants.class)
			.a(OAuth2Constants.SCOPE)
			.build();

	@Override
	public String getAuthorizationUrl(OAuth20Config config) {
		ParameterList parameterList = new ParameterList()
				.add(OAuth2Constants.CLIENT_ID.value, config.getApiKey())
				.add(OAuth2Constants.REDIRECT_URI.value, config.getCallback())
				.add(OAuth2Constants.RESPONSE_TYPE.value, "code");
		if (config.getScope() != null) {
			parameterList.add(OAuth2Constants.SCOPE.value, config.getScope());
		}
		return ParameterUtils.buildUrl(AUTHORIZE_URL, parameterList);
	}

	private static final String ACCESS_TOKEN_URL = "https://accounts.google.com/o/oauth2/token";
	private HttpVerb tokenVerb = HttpVerb.POST;
	private Set<OAuth2Constants> tokenEssential = SetBuilder.create(OAuth2Constants.class)
			.a(OAuth2Constants.CLIENT_ID, OAuth2Constants.CLIENT_SECRET, OAuth2Constants.CODE, OAuth2Constants.REDIRECT_URI)
			.build();
	private Set<OAuth2Constants> tokenFixed = SetBuilder.create(OAuth2Constants.class)
			.a(OAuth2Constants.GRANT_TYPE)
			.build();
	private Set<OAuth2Constants> tokenOptional = SetBuilder.create(OAuth2Constants.class)
			.a()
			.build();

	@Override
	public String getAccessTokenUrl() {
		return ACCESS_TOKEN_URL;
	}

	@Override
	public HttpVerb getAccessTokenVerb() {
		return tokenVerb;
	}

	@Override
	public AccessTokenExtractor20 getAccessTokenExtractor() {
		return new AccessTokenExtractor20() {

			@Override
			public Token20Result extract(String response) {
				Check.notNullOrEmptyString(response, "Response body is incorrect. Can't extract a token from an empty string");

				Map<String, String> map = JsonUtil.json2map(response);
				return new Token20Result(map.get(OAuth2ResponseType.ACCESS_TOKEN.toString()), -1);
			}
		};
	}

	@Override
	public OAuth20Service createService(OAuth20Config config) {
		return new OAuth20ServiceSimple(this, config);
	}

}
