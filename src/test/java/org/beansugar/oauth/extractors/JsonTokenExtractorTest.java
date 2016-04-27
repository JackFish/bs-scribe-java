package org.beansugar.oauth.extractors;

import org.beansugar.oauth.o10a.model.Token10a;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JsonTokenExtractorTest {
	private static final String response = "'{ \"access_token\":\"I0122HHJKLEM21F3WLPYHDKGKZULAUO4SGMV3ABKFTDT3T3X\"}'";
	private JsonTokenExtractor extractor = new JsonTokenExtractor();

	@Test
	public void shouldParseResponse() {
		Token10a token = extractor.extract(response);
		assertEquals(token.getToken(), "I0122HHJKLEM21F3WLPYHDKGKZULAUO4SGMV3ABKFTDT3T3X");
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionIfForNullParameters() {
		extractor.extract(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionIfForEmptyStrings() {
		extractor.extract("");
	}
}
