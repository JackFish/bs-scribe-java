package org.beansugar.oauth.o20.model;

import lombok.Getter;
import org.beansugar.oauth.model.type.OAuthConstants;
import org.beansugar.tools.core.check.Check;

public final class OAuth20Config {

	@Getter
	private final String apiKey;

	@Getter
	private final String apiSecret;

	@Getter
	private final String callback;

	@Getter
	private final String scope;

	public OAuth20Config(String apiKey, String apiSecret, String callback, String scope) {
		Check.notNullOrEmptyString(apiKey, "apiKey will not be null or empty");
		Check.notEmptyString(apiSecret, "apiSecret will not be empty");
		Check.notEmptyString(callback, "callback can't be null");
		Check.notEmptyString(scope, "scope will not be empty");
		this.apiKey = apiKey;
		this.apiSecret = apiSecret;
		this.callback = callback != null ? callback : OAuthConstants.OUT_OF_BAND.toString();
		this.scope = scope;
	}

}
