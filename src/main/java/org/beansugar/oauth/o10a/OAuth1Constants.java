package org.beansugar.oauth.o10a;

import lombok.Getter;

/**
 * @author archmagece
 * @date 2015-11-16
 */
public enum OAuth1Constants {

	TIMESTAMP("oauth_timestamp"),
	SIGN_METHOD("oauth_signature_method"),
	SIGNATURE("oauth_signature"),
	CONSUMER_SECRET("oauth_consumer_secret"),
	CONSUMER_KEY("oauth_consumer_key"),
	CALLBACK("oauth_callback"),
	VERSION("oauth_version"),
	NONCE("oauth_nonce"),
	TOKEN("oauth_token"),
	TOKEN_SECRET("oauth_token_secret"),
	VERIFIER("oauth_verifier"),
	HEADER("Authorization");

	@Getter
	public String value;

	OAuth1Constants(String value) {
		this.value = value;
	}

	public String toString() {
		return value;
	}

}
