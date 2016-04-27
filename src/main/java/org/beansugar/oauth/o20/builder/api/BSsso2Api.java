package org.beansugar.oauth.o20.builder.api;

import lombok.extern.slf4j.Slf4j;
import org.beansugar.oauth.o20.extractor.AccessTokenExtractor20;
import org.beansugar.oauth.o20.extractor.AccessTokenJsonExtractor20;
import org.beansugar.oauth.o20.model.OAuth20Config;
import org.beansugar.oauth.utils.OAuthEncoder;
import org.beansugar.tools.core.check.Check;

/**
 * @author Boris G. Tsirkin <mail@dotbg.name>
 * @since 20.4.2011
 */
@Slf4j
public class BSsso2Api extends Default20Api {

	private static final String AUTHORIZE_URL = "http://localhost:8080/oauth/authorize?client_id=%s&redirect_uri=%s&response_type=code";
	private static final String SCOPED_AUTHORIZE_URL = AUTHORIZE_URL + "&scope=%s";

	@Override
	public String getAccessTokenUrl() {
		return "http://localhost:8080/oauth/token?grant_type=authorization_code";
	}

	@Override
	public String getAuthorizationUrl(OAuth20Config config) {
		Check.urlCustomPattern(config.getCallback(), "Must provide a valid url as callback. Live does not support OOB");

		// Append scope if present
		if (config.getScope() != null) {
			return String.format(SCOPED_AUTHORIZE_URL, config.getApiKey(), OAuthEncoder.encode(config.getCallback()), OAuthEncoder.encode(config.getScope()));
		} else {
			return String.format(AUTHORIZE_URL, config.getApiKey(), OAuthEncoder.encode(config.getCallback()));
		}
	}

	@Override
	public AccessTokenExtractor20 getAccessTokenExtractor() {
		return new AccessTokenJsonExtractor20();
	}
}
