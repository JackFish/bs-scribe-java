package org.beansugar.oauth.services.signature;

import org.beansugar.oauth.exceptions.OAuthSignatureException;
import org.beansugar.oauth.utils.OAuthEncoder;
import org.beansugar.tools.core.check.Check;

/**
 * plaintext implementation of {@link SignatureService}
 *
 * @author Pablo Fernandez
 */
public class PlaintextSignatureService implements SignatureService {
	private static final String METHOD = "PLAINTEXT";

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getSignature(String baseString, String apiSecret, String tokenSecret) {
		try {
			Check.notNullOrEmptyString(apiSecret, "Api10a secret cant be null or empty string");
			return OAuthEncoder.encode(apiSecret) + '&' + OAuthEncoder.encode(tokenSecret);
		} catch (Exception e) {
			throw new OAuthSignatureException(baseString, e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getSignatureMethod() {
		return METHOD;
	}
}

