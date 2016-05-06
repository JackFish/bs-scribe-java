package org.beansugar.oauth.o20.builder.api;

import lombok.extern.slf4j.Slf4j;
import org.beansugar.oauth.model.ParameterList;
import org.beansugar.oauth.model.type.HttpVerb;
import org.beansugar.oauth.model.type.SignatureType;
import org.beansugar.oauth.o20.OAuth2Constants;
import org.beansugar.oauth.o20.extractor.AccessTokenExtractor20;
import org.beansugar.oauth.o20.model.OAuth20Config;
import org.beansugar.oauth.o20.model.Token20Result;
import org.beansugar.oauth.o20.type.OAuth2ResponseType;
import org.beansugar.oauth.utils.JsonUtil;
import org.beansugar.oauth.utils.ParameterUtils;
import org.beansugar.tools.core.check.Check;

import java.util.Map;

/**
 * @author archmagece
 * @date 2015-11-18
 */
@Slf4j
public class Naver20Api extends Default20Api {

	private static final String AUTHORIZE_URL = "https://nid.naver.com/oauth2.0/authorize";
	private static final String ACCESS_TOKEN_URL = "https://nid.naver.com/oauth2.0/token";

	@Override
	public String getAccessTokenUrl() {
		return ACCESS_TOKEN_URL;
	}

	@Override
	public String getAuthorizationUrl(OAuth20Config config) {
		Check.urlCustomPattern(config.getCallback(), "Must provide a valid url as callback. Naver does not support OOB");

		ParameterList parameterList = new ParameterList()
				.add(OAuth2Constants.CLIENT_ID.value, config.getApiKey())
				.add(OAuth2Constants.REDIRECT_URI.value, config.getCallback())
				.add(OAuth2Constants.RESPONSE_TYPE.value, "code");
//				.add(OAuth2Constants.STATE.value, "g4tg34tg34");
		if (config.getScope() != null) {
			parameterList.add(OAuth2Constants.SCOPE.value, config.getScope());
		}
		return ParameterUtils.buildUrl(AUTHORIZE_URL, parameterList);
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
	public SignatureType getSignatureType() {
		return SignatureType.QueryString;
	}

	@Override
	public HttpVerb getAccessTokenVerb() {
		return HttpVerb.GET;
	}
}
