package org.beansugar.oauth.o10a.builder;

import org.beansugar.oauth.model.type.OAuthConstants;
import org.beansugar.oauth.o10a.builder.api.Api10a;
import org.beansugar.oauth.o10a.model.OAuth10aConfig;
import org.beansugar.oauth.o10a.service.OAuth10aService;
import org.beansugar.oauth.utils.ClassUtils;
import org.beansugar.tools.core.check.Check;

/**
 * Implementation of the Builder pattern, with a fluent interface that creates a
 * {@link OAuth10aService}
 *
 * @author Pablo Fernandez
 */
public class OAuth10aServiceBuilder {
	private String apiKey;
	private String apiSecret;
	private String callback;
	private Api10a api;
	private String scope;

	/**
	 * Default constructor
	 */
	public OAuth10aServiceBuilder() {
		this.callback = OAuthConstants.OUT_OF_BAND.getValue();
	}

	/**
	 * Configures the {@link Api10a}
	 *
	 * @param apiClass the class of one of the existent {@link Api10a}s on org.scribe.api package
	 * @return the {@link OAuth10aServiceBuilder} instance for method chaining
	 */
	public OAuth10aServiceBuilder provider(Class<? extends Api10a> apiClass) {
		this.api = ClassUtils.createApi(apiClass);
		return this;
	}

	/**
	 * Configures the {@link Api10a}
	 * <p>
	 * Overloaded version. Let's you use an instance instead of a class.
	 *
	 * @param api instance of {@link Api10a}s
	 * @return the {@link OAuth10aServiceBuilder} instance for method chaining
	 */
	public OAuth10aServiceBuilder provider(Api10a api) {
		this.api = api;
		return this;
	}

	/**
	 * Adds an OAuth callback url
	 *
	 * @param callback callback url. Must be a valid url or 'oob' for out of band OAuth
	 * @return the {@link OAuth10aServiceBuilder} instance for method chaining
	 */
	public OAuth10aServiceBuilder callback(String callback) {
		this.callback = callback;
		return this;
	}

	/**
	 * Configures the api key
	 *
	 * @param apiKey The api key for your application
	 * @return the {@link OAuth10aServiceBuilder} instance for method chaining
	 */
	public OAuth10aServiceBuilder apiKey(String apiKey) {
		this.apiKey = apiKey;
		return this;
	}

	/**
	 * Configures the api secret
	 *
	 * @param apiSecret The api secret for your application
	 * @return the {@link OAuth10aServiceBuilder} instance for method chaining
	 */
	public OAuth10aServiceBuilder apiSecret(String apiSecret) {
		this.apiSecret = apiSecret;
		return this;
	}

	/**
	 * Configures the OAuth scope. This is only necessary in some APIs (like Google's).
	 *
	 * @param scope The OAuth scope
	 * @return the {@link OAuth10aServiceBuilder} instance for method chaining
	 */
	public OAuth10aServiceBuilder scope(String scope) {
		this.scope = scope;
		return this;
	}

	/**
	 * Returns the fully configured {@link OAuth10aService}
	 *
	 * @return fully configured {@link OAuth10aService}
	 */
	public OAuth10aService build() {
		Check.notNull(api, "You must specify a valid api through the provider() method");
		Check.notNullOrEmptyString(apiKey, "api key must provided");
		Check.notEmptyString(apiSecret, "api secret must not empty. but allow null (ex kakao)");
		Check.notNullOrEmptyString(callback, "Callback can't be null");
		Check.notEmptyString(scope, "scope allow null");
		return api.createService(new OAuth10aConfig(apiKey, apiSecret, callback, scope));
	}
}
