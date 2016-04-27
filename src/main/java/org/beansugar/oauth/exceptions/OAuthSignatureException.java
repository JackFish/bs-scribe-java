package org.beansugar.oauth.exceptions;

/**
 * Specialized exception that represents a problem in the signature
 *
 * @author Pablo Fernandez
 */
public class OAuthSignatureException extends OAuthException {
	private static final String MSG = "Error while signing string: %s";

	/**
	 * Default constructor
	 *
	 * @param stringToSign plain string that gets signed (HMAC-SHA, etc)
	 * @param e            original exception
	 */
	public OAuthSignatureException(String stringToSign, Exception e) {
		super(String.format(MSG, stringToSign), e);
	}

}
