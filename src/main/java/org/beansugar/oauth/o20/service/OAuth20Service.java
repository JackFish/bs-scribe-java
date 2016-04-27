package org.beansugar.oauth.o20.service;

import org.beansugar.oauth.model.Verifier;
import org.beansugar.oauth.model.type.HttpVerb;
import org.beansugar.oauth.net.Response;
import org.beansugar.oauth.o20.model.Token20Result;

import java.io.Serializable;

/**
 * The main Scribe object.
 * <p>
 * A facade responsible for the retrieval of request and access tokens and for the signing of HTTP requests.
 *
 * @author Pablo Fernandez
 */
public interface OAuth20Service extends Serializable {

	/**
	 * Returns the URL where you should redirect your users to authenticate
	 * your application.
	 *
	 * @return the URL where you should redirect your users
	 */
	String getAuthorizationUrl();

	/**
	 * Retrieve the access token
	 *
	 * @param verifier verifier code
	 * @return access token
	 */
//	Token20 getTokenResult(Verifier verifier, String state);
	Token20Result getTokenResult(Verifier verifier, String state);

	/**
	 * access and get oauth resource
	 *
	 * @param verb        HttpVerb
	 * @param resourceUrl api resource url protected by oauth
	 * @param accessToken accessToken
	 * @return API result Response
	 */
	Response getResult(HttpVerb verb, String resourceUrl, Token20Result accessToken);
}
