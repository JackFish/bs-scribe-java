package org.beansugar.oauth.o10a.model;

import lombok.Getter;
import org.beansugar.oauth.model.type.OAuthConstants;
import org.beansugar.oauth.model.type.SignatureType;
import org.beansugar.tools.core.check.Check;

public final class OAuth10aConfig {

	@Getter
	private final String apiKey;

	@Getter
	private final String apiSecret;

	@Getter
	private final String callback;

	@Getter
	private final SignatureType signatureType;

	@Getter
	private final String scope;

	public OAuth10aConfig(String apiKey, String apiSecret, String callback, String scope) {
		this(apiKey, apiSecret, callback, scope, SignatureType.Header);
	}

	public OAuth10aConfig(String apiKey, String apiSecret, String callback, String scope, SignatureType signatureType) {
		Check.notNull(signatureType, "callback can't be null");
		Check.notNullOrEmptyString(apiKey, "apiKey will not be null or empty");
		Check.notEmptyString(apiSecret, "apiSecret will not be empty");
		Check.notNullOrEmptyString(callback, "callback can't be null");
		Check.notEmptyString(scope, "scope will not be empty");
		this.apiKey = apiKey;
		this.apiSecret = apiSecret;
		this.callback = callback != null ? callback : OAuthConstants.OUT_OF_BAND.toString();
		this.scope = scope;
		this.signatureType = signatureType;
	}

}
