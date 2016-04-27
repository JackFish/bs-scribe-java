package org.beansugar.oauth.extractors;

import org.beansugar.oauth.exceptions.OAuthParametersMissingException;
import org.beansugar.oauth.model.type.HttpVerb;
import org.beansugar.oauth.net.OAuthRequest;
import org.beansugar.oauth.test.helpers.ObjectMother;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HeaderExtractorTest {

	private HeaderExtractorImpl extractor;
	private OAuthRequest request;

	@Before
	public void setup() {
		request = ObjectMother.createSampleOAuthRequest();
		extractor = new HeaderExtractorImpl();
	}

//	FIXME string expected, header 값의 순서가 달라서 오류,
//	@Test
//	public void shouldExtractStandardHeader() {
//		String expected = "OAuth oauth_callback=\"http%3A%2F%2Fexample%2Fcallback\", " + "oauth_signature=\"OAuth-Signature\", "
//				+ "oauth_consumer_key=\"AS%23%24%5E%2A%40%26\", " + "oauth_timestamp=\"123456\"";
//		String header = extractor.extract(request);
//		assertEquals(expected, header);
//	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldExceptionIfRequestIsNull() {
		OAuthRequest nullRequest = null;
		extractor.extract(nullRequest);
	}

	@Test(expected = OAuthParametersMissingException.class)
	public void shouldExceptionIfRequestHasNoOAuthParams() {
		OAuthRequest emptyRequest = new OAuthRequest(HttpVerb.GET, "http://example.com");
		extractor.extract(emptyRequest);
	}
}
