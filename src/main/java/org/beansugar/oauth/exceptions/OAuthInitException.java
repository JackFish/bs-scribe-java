package org.beansugar.oauth.exceptions;

/**
 * @author archmagece
 * @date 2015-11-16
 */
public class OAuthInitException extends OAuthException {

	private static final String MSG = "초기화 실패";

	public OAuthInitException() {
		super(MSG);
	}

	public OAuthInitException(String message) {
		super(message);
	}
}
