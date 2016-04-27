package org.beansugar.oauth.utils;

import org.beansugar.tools.core.check.Check;
import org.junit.Test;

public class PreconditionsTest {

	private static final String ERROR_MSG = "";

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionForNullObjects() {
		Check.notNull(null, ERROR_MSG);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionForNullStrings() {
		Check.notNullOrEmptyString(null, ERROR_MSG);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionForEmptyStrings() {
		Check.notEmptyString("", ERROR_MSG);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionForSpacesOnlyStrings() {
		Check.notEmptyString("               ", ERROR_MSG);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionForInvalidUrls() {
		Check.urlDomainPattern("this/is/not/a/valid/url", ERROR_MSG);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionForNullUrls() {
		Check.urlDomainPattern(null, ERROR_MSG);
	}

	@Test
	public void shouldAllowValidUrls() {
		Check.urlDomainPattern("http://www.example.com", ERROR_MSG);
	}

	@Test
	public void shouldAllowSSLUrls() {
		Check.urlDomainPattern("https://www.example.com", ERROR_MSG);
	}

	@Test
	public void shouldAllowSpecialCharsInScheme() {
		Check.urlCustomPattern("custom+9.3-1://www.example.com", ERROR_MSG);
	}

	@Test
	public void shouldAllowNonStandardProtocolsForAndroid() {
		Check.urlCustomPattern("x-url-custom://www.example.com", ERROR_MSG);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAllowStrangeProtocolNames() {
		Check.urlCustomPattern("$weird*://www.example.com", ERROR_MSG);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAllowUnderscoreInScheme() {
		Check.urlCustomPattern("http_custom://www.example.com", ERROR_MSG);
	}

//	@Test
//	public void shouldAllowOutOfBandAsValidCallbackValue() {
//		Check.checkValidOAuthCallback("oob", ERROR_MSG);
//	}
}
