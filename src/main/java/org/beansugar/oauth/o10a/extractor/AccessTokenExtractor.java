package org.beansugar.oauth.o10a.extractor;

import org.beansugar.oauth.o10a.model.Token10a;

/**
 * Simple command object that extracts a {@link Token10a} from a String
 *
 * @author Pablo Fernandez
 */
public interface AccessTokenExtractor {
	/**
	 * Extracts the access token from the contents of an Http Response
	 *
	 * @param response the contents of the response
	 * @return OAuth access token
	 */
	Token10a extract(String response);
}
