package org.beansugar.oauth.o20.builder.api;

import lombok.Getter;
import org.beansugar.oauth.model.type.HttpVerb;
import org.beansugar.oauth.model.type.SignatureType;
import org.beansugar.oauth.o20.extractor.AccessTokenExtractor20;
import org.beansugar.oauth.o20.extractor.TokenExtractor20Impl;
import org.beansugar.oauth.o20.model.OAuth20Config;
import org.beansugar.oauth.o20.service.OAuth20Service;
import org.beansugar.oauth.o20.service.OAuth20ServiceSimple;

/**
 * Default implementation of the OAuth protocol, version 2.0 (draft 11)
 * <p>
 * This class is meant to be extended by concrete implementations of the API,
 * providing the endpoints and endpoint-http-verbs.
 * <p>
 * If your Api20 adheres to the 2.0 (draft 11) protocol correctly, you just need to extend
 * this class and define the getters for your endpoints.
 * <p>
 * If your Api20 does something a bit different, you can override the different
 * extractors or services, in order to fine-tune the process. Please read the
 * javadocs of the interfaces to get an idea of what to do.
 *
 * @author Diego Silveira
 */
public abstract class Default20Api implements Api20 {

	/**
	 * Returns the URL where you should redirect your users to authenticate
	 * your application.
	 *
	 * @param config OAuth 2.0 configuration param object
	 * @return the URL where you should redirect your users
	 */
	public abstract String getAuthorizationUrl(OAuth20Config config);

	/**
	 * Returns the access token extractor.
	 */
	@Getter
	private AccessTokenExtractor20 accessTokenExtractor = new TokenExtractor20Impl();

	/**
	 * Returns the verb for the access token endpoint (defaults to GET)
	 */
	@Getter
	public HttpVerb accessTokenVerb = HttpVerb.GET;

	/**
	 * signatureType
	 */
	@Getter
	private SignatureType signatureType = SignatureType.Header;

	/**
	 * Returns the URL that receives the access token requests.
	 *
	 * @return access token URL
	 */
	public abstract String getAccessTokenUrl();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OAuth20Service createService(OAuth20Config config) {
		return new OAuth20ServiceSimple(this, config);
	}

}
