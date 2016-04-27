package org.beansugar.oauth.o20.builder.api;

import org.beansugar.oauth.o20.model.OAuth20Config;
import org.beansugar.oauth.o20.service.OAuth20Service;

import java.io.Serializable;

/**
 * Contains all the configuration needed to instantiate a valid {@link OAuth20Service}
 *
 * @author Pablo Fernandez
 */
public interface Api20 extends Serializable {
	String VERSION = "2.0";

	/**
	 * Creates an {@link OAuth20Service}
	 *
	 * @param oAuthConfig apiKey    your application api key
	 *                    apiSecret your application api secret
	 *                    callback  the callback url (or 'oob' for out of band OAuth)
	 *                    scope     the OAuth scope (optional)
	 *                    fully configured {@link OAuth20Service}
	 */
	OAuth20Service createService(OAuth20Config oAuthConfig);
}
