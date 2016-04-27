package org.beansugar.oauth.o10a.builder.api;

import lombok.Getter;
import org.beansugar.oauth.extractors.*;
import org.beansugar.oauth.model.type.HttpVerb;
import org.beansugar.oauth.model.type.SignatureType;
import org.beansugar.oauth.o10a.extractor.AccessTokenExtractor;
import org.beansugar.oauth.o10a.extractor.TokenExtractorImpl;
import org.beansugar.oauth.o10a.model.OAuth10aConfig;
import org.beansugar.oauth.o10a.model.Token10a;
import org.beansugar.oauth.o10a.service.OAuth10aService;
import org.beansugar.oauth.o10a.service.OAuth10aServiceSimple;
import org.beansugar.oauth.services.signature.HMACSha1SignatureService;
import org.beansugar.oauth.services.signature.SignatureService;
import org.beansugar.oauth.services.timestamp.TimestampService;
import org.beansugar.oauth.services.timestamp.TimestampServiceImpl;

/**
 * Default implementation of the OAuth protocol, version 1.0a
 * <p>
 * This class is meant to be extended by concrete implementations of the API,
 * providing the endpoints and endpoint-http-verbs.
 * <p>
 * If your Api10a adheres to the 1.0a protocol correctly, you just need to extend
 * this class and define the getters for your endpoints.
 * <p>
 * If your Api10a does something a bit different, you can override the different
 * extractors or services, in order to fine-tune the process. Please read the
 * javadocs of the interfaces to get an idea of what to do.
 *
 * @author Pablo Fernandez
 */
public abstract class DefaultApi10a implements Api10a {
	/**
	 * Returns the access token extractor.
	 */
	@Getter
	private AccessTokenExtractor accessTokenExtractor = new TokenExtractorImpl();

	/**
	 * Returns the base string extractor.
	 */
	@Getter
	private BaseStringExtractor baseStringExtractor = new BaseStringExtractorImpl();

	/**
	 * Returns the header extractor.
	 */
	@Getter
	private HeaderExtractor headerExtractor = new HeaderExtractorImpl();

	/**
	 * Returns the request token extractor.
	 */
	@Getter
	private RequestTokenExtractor requestTokenExtractor = new TokenExtractorImpl();

	/**
	 * Returns the signature service.
	 */
	@Getter
	private SignatureService signatureService = new HMACSha1SignatureService();

	/**
	 * Returns the timestamp service.
	 */
	@Getter
	private TimestampService timestampService = new TimestampServiceImpl();

	/**
	 * Returns the verb for the access token endpoint (defaults to POST)
	 */
	@Getter
	public HttpVerb accessTokenVerb = HttpVerb.POST;

	/**
	 * Returns the verb for the request token endpoint (defaults to POST)
	 */
	@Getter
	private HttpVerb requestTokenVerb = HttpVerb.POST;

	/**
	 * signatureType
	 */
	@Getter
	private SignatureType signatureType = SignatureType.Header;

	/**
	 * Returns the URL that receives the request token requests.
	 *
	 * @return request token URL
	 */
	public abstract String getRequestTokenUrl();

	/**
	 * Returns the URL that receives the access token requests.
	 *
	 * @return access token URL
	 */
	public abstract String getAccessTokenUrl();

	/**
	 * Returns the URL where you should redirect your users to authenticate
	 * your application.
	 *
	 * @param requestToken the request token you need to authorize
	 * @return the URL where you should redirect your users
	 */
	public abstract String getAuthorizationUrl(Token10a requestToken);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OAuth10aService createService(OAuth10aConfig oAuthConfig) {
		return new OAuth10aServiceSimple(this, oAuthConfig);
	}
}
