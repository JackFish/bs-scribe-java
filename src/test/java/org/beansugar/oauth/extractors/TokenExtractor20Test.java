package org.beansugar.oauth.extractors;

import org.beansugar.oauth.exceptions.OAuthException;
import org.beansugar.oauth.o20.extractor.TokenExtractor20Impl;
import org.beansugar.oauth.o20.model.Token20Result;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TokenExtractor20Test {

	private TokenExtractor20Impl extractor;

	@Before
	public void setup() {
		extractor = new TokenExtractor20Impl();
	}

	@Test
	public void shouldExtractTokenFromOAuthStandardResponse() {
		String response = "access_token=166942940015970|2.2ltzWXYNDjCtg5ZDVVJJeg__.3600.1295816400-548517159|RsXNdKrpxg8L6QNLWcs2TVTmcaE";
		Token20Result tokenResult = extractor.extract(response);
		assertEquals("166942940015970|2.2ltzWXYNDjCtg5ZDVVJJeg__.3600.1295816400-548517159|RsXNdKrpxg8L6QNLWcs2TVTmcaE", tokenResult.getAccessToken());
	}

	@Test
	public void shouldExtractTokenFromResponseWithExpiresParam() {
		String response = "access_token=166942940015970|2.2ltzWXYNDjCtg5ZDVVJJeg__.3600.1295816400-548517159|RsXNdKrpxg8L6QNLWcs2TVTmcaE&expires=5108";
		Token20Result tokenResult = extractor.extract(response);
		assertEquals("166942940015970|2.2ltzWXYNDjCtg5ZDVVJJeg__.3600.1295816400-548517159|RsXNdKrpxg8L6QNLWcs2TVTmcaE", tokenResult.getAccessToken());
	}

	@Test
	public void shouldExtractTokenFromResponseWithManyParameters() {
		String response = "access_token=foo1234&other_stuff=yeah_we_have_this_too&number=42";
		Token20Result tokenResult = extractor.extract(response);
		assertEquals("foo1234", tokenResult.getAccessToken());
	}

	@Test(expected = OAuthException.class)
	public void shouldThrowExceptionIfTokenIsAbsent() {
		String response = "&expires=5108";
		extractor.extract(response);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionIfResponseIsNull() {
		String response = null;
		extractor.extract(response);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionIfResponseIsEmptyString() {
		String response = "";
		extractor.extract(response);
	}
}
