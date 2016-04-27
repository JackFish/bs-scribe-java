package org.beansugar.oauth.net;

import lombok.Getter;
import lombok.Setter;
import org.beansugar.oauth.model.type.HttpVerb;
import org.beansugar.oauth.model.type.OAuthConstants;

import java.util.HashMap;
import java.util.Map;

/**
 * The representation of an OAuth HttpRequest.
 * <p>
 * Adds OAuth-related functionality to the {@link Request}
 *
 * @author Pablo Fernandez
 */
public class OAuthRequest extends Request {
	private static final String OAUTH_PREFIX = "oauth_";
	@Getter
	private Map<String, String> oauthParameters;
	@Getter
	@Setter
	private String realm;

	/**
	 * Default constructor.
	 *
	 * @param verb Http verb/method
	 * @param url  resource URL
	 */
	public OAuthRequest(HttpVerb verb, String url) {
		super(verb, url);
		this.oauthParameters = new HashMap<>();
	}

	/**
	 * Adds an OAuth parameter.
	 *
	 * @param key   name of the parameter
	 * @param value value of the parameter
	 * @throws IllegalArgumentException if the parameter is not an OAuth parameter
	 */
	public void addOAuthParameter(String key, String value) {
		oauthParameters.put(checkKey(key), value);
	}

	private String checkKey(String key) {
		if (key.startsWith(OAUTH_PREFIX.toString()) || key.equals(OAuthConstants.SCOPE.toString()) || key.equals(OAuthConstants.REALM.toString())) {
			return key;
		} else {
			throw new IllegalArgumentException(String.format("OAuth parameters must either be '%s', '%s' or start with '%s'", OAuthConstants.SCOPE.toString(), OAuthConstants.REALM, OAUTH_PREFIX));
		}
	}

	@Override
	public String toString() {
		return String.format("@OAuthRequest(%s, %s)", getVerb(), getUrl());
	}
}
