package org.beansugar.oauth.o20.extractor;

import org.beansugar.oauth.o20.model.Token20Result;

/**
 * Simple command object that extracts a {@link Token20Result} from a String
 *
 * @author Pablo Fernandez
 */
public interface AccessTokenExtractor20 {
	/**
	 * Extracts the access token from the contents of an Http Response
	 *
	 * @param response the contents of the response
	 * @return OAuth access token
	 */
	Token20Result extract(String response);
}
