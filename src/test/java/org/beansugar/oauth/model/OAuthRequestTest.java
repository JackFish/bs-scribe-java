package org.beansugar.oauth.model;

import org.beansugar.oauth.model.type.HttpVerb;
import org.beansugar.oauth.model.type.OAuthConstants;
import org.beansugar.oauth.net.OAuthRequest;
import org.beansugar.oauth.o10a.OAuth1Constants;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OAuthRequestTest {

	private OAuthRequest request;

	@Before
	public void setup() {
		request = new OAuthRequest(HttpVerb.GET, "http://example.com");
	}

	@Test
	public void shouldAddOAuthParamters() {
		request.addOAuthParameter(OAuth1Constants.TOKEN.getValue(), "token");
		request.addOAuthParameter(OAuth1Constants.NONCE.getValue(), "nonce");
		request.addOAuthParameter(OAuth1Constants.TIMESTAMP.getValue(), "ts");
		request.addOAuthParameter(OAuthConstants.SCOPE.getValue(), "feeds");
		request.addOAuthParameter(OAuthConstants.REALM.getValue(), "some-realm");

		assertEquals(5, request.getOauthParameters().size());
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionIfParameterIsNotOAuth() {
		request.addOAuthParameter("otherParam", "value");
	}
}
