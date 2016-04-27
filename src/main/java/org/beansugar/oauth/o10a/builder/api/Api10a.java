package org.beansugar.oauth.o10a.builder.api;

import org.beansugar.oauth.o10a.model.OAuth10aConfig;
import org.beansugar.oauth.o10a.service.OAuth10aService;

import java.io.Serializable;

/**
 * Contains all the configuration needed to instantiate a valid {@link OAuth10aService}
 *
 * @author Pablo Fernandez
 */
public interface Api10a extends Serializable {
	String VERSION = "1.0";

	/**
	 * Creates an {@link OAuth10aService}
	 *
	 * @param oAuthConfig apiKey    your application api key
	 *                    apiSecret your application api secret
	 *                    callback  the callback url (or 'oob' for out of band OAuth)
	 *                    scope     the OAuth scope (optional)
	 *                    fully configured {@link OAuth10aService}
	 */
	OAuth10aService createService(OAuth10aConfig oAuthConfig);
}
