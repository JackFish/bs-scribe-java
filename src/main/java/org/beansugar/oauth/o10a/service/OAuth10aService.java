package org.beansugar.oauth.o10a.service;

import org.beansugar.oauth.model.Verifier;
import org.beansugar.oauth.model.type.HttpVerb;
import org.beansugar.oauth.net.Response;
import org.beansugar.oauth.o10a.model.Token10a;

import java.io.Serializable;

/**
 * The main Scribe object.
 * <p>
 * A facade responsible for the retrieval of request and access tokens and for the signing of HTTP requests.
 *
 * @author Pablo Fernandez
 */
public interface OAuth10aService extends Serializable {
	/**
	 * Retrieve the request token.
	 *
	 * @return request token
	 */
	Token10a getRequestToken();

	/**
	 * Retrieve the access token
	 *
	 * @param requestToken request token (obtained previously)
	 * @param verifier     verifier code
	 * @return access token
	 */
	Token10a getAccessToken(Token10a requestToken, Verifier verifier);

	/**
	 * access and get oauth resource
	 *
	 * @param verb        HttpVerb
	 * @param resourceUrl api resource url protected by oauth
	 * @param accessToken accessToken
	 * @return API result Response
	 */
	Response getResult(HttpVerb verb, String resourceUrl, Token10a accessToken);

	/**
	 * Returns the URL where you should redirect your users to authenticate
	 * your application.
	 *
	 * @param requestToken the request token you need to authorize
	 * @return the URL where you should redirect your users
	 */
	String getAuthorizationUrl(Token10a requestToken);
}
