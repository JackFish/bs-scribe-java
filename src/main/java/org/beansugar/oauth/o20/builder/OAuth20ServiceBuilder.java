package org.beansugar.oauth.o20.builder;

import org.beansugar.oauth.o20.builder.api.Api20;
import org.beansugar.oauth.o20.model.OAuth20Config;
import org.beansugar.oauth.o20.service.OAuth20Service;
import org.beansugar.oauth.utils.ClassUtils;
import org.scriptonbasestar.tool.core.check.Check;

/**
 * Implementation of the Builder pattern, with a fluent interface that creates a
 * {@link OAuth20Service}
 *
 * @author Pablo Fernandez
 */
public class OAuth20ServiceBuilder {
	private String apiKey;
	private String apiSecret;
	private String callback;
	private Api20 api;
	private String scope;

	/**
	 * Configures the {@link Api20}
	 *
	 * @param apiClass the class of one of the existent {@link Api20}s on org.scribe.api package
	 * @return the {@link OAuth20ServiceBuilder} instance for method chaining
	 */
	public OAuth20ServiceBuilder provider(Class<? extends Api20> apiClass) {
		this.api = ClassUtils.createApi(apiClass);
		return this;
	}

	/**
	 * Configures the {@link Api20}
	 * <p>
	 * Overloaded version. Let's you use an instance instead of a class.
	 *
	 * @param api instance of {@link Api20}s
	 * @return the {@link OAuth20ServiceBuilder} instance for method chaining
	 */
	public OAuth20ServiceBuilder provider(Api20 api) {
		this.api = api;
		return this;
	}

	/**
	 * Adds an OAuth callback url
	 *
	 * @param callback callback url. Must be a valid url or 'oob' for out of band OAuth
	 * @return the {@link OAuth20ServiceBuilder} instance for method chaining
	 */
	public OAuth20ServiceBuilder callback(String callback) {
		this.callback = callback;
		return this;
	}

	/**
	 * Configures the api key
	 *
	 * @param apiKey The api key for your application
	 * @return the {@link OAuth20ServiceBuilder} instance for method chaining
	 */
	public OAuth20ServiceBuilder apiKey(String apiKey) {
		this.apiKey = apiKey;
		return this;
	}

	/**
	 * Configures the api secret
	 *
	 * @param apiSecret The api secret for your application
	 * @return the {@link OAuth20ServiceBuilder} instance for method chaining
	 */
	public OAuth20ServiceBuilder apiSecret(String apiSecret) {
		this.apiSecret = apiSecret;
		return this;
	}

	/**
	 * Configures the OAuth scope. This is only necessary in some APIs (like Google's).
	 *
	 * @param scope The OAuth scope
	 * @return the {@link OAuth20ServiceBuilder} instance for method chaining
	 */
	public OAuth20ServiceBuilder scope(String scope) {
		this.scope = scope;
		return this;
	}

	public OAuth20Service build() {
		Check.notNull(api, "You must specify a valid api through the provider() method");
		Check.notNullOrEmptyString(apiKey, "api key must provided");
		Check.notEmptyString(apiSecret, "api secret must not empty. but allow null (ex kakao)");
		Check.notEmptyString(callback, "Callback can't be null");
		Check.notEmptyString(scope, "scope allow null");
		OAuth20Config config = new OAuth20Config(apiKey, apiSecret, callback, scope);
		return api.createService(config);
	}
}
