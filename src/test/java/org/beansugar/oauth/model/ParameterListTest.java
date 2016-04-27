package org.beansugar.oauth.model;

import org.beansugar.oauth.utils.ParameterUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

/**
 * @author Pablo Fernandez
 */
public class ParameterListTest {
	private ParameterList params;

	@Before
	public void setup() {
		this.params = new ParameterList();
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionWhenAppendingNullMapToQueryString() {
		String url = null;
		ParameterUtils.buildUrl(url, params);
	}

	@Test
	public void shouldAppendNothingToQuerystringIfGivenEmptyMap() {
		String url = "http://www.example.com";
		Assert.assertEquals(url, ParameterUtils.buildUrl(url, params));
	}

	@Test
	public void shouldAppendParametersToSimpleUrl() {
		String url = "http://www.example.com";
		String expectedUrl = "http://www.example.com?param1=value1&param2=value%20with%20spaces";

		params.add("param1", "value1");
		params.add("param2", "value with spaces");

		url = ParameterUtils.buildUrl(url, params);
		Assert.assertEquals(url, expectedUrl);
	}

	@Test
	public void shouldReturnTheCompleteUrl() {
		params.add("two", "other val");
		params.add("more", "params");
		assertEquals("http://example.com?one=val&two=other%20val&more=params", ParameterUtils.buildUrl("http://example.com?one=val", params));
	}

	@Test
	public void shouldAppendParametersToUrlWithQuerystring() {
		String url = "http://www.example.com?already=present";
		String expectedUrl = "http://www.example.com?already=present&param1=value1&param2=value%20with%20spaces";

		params.add("param1", "value1");
		params.add("param2", "value with spaces");

		url = ParameterUtils.buildUrl(url, params);
		Assert.assertEquals(url, expectedUrl);
	}

	@Test
	public void shouldProperlySortParameters() {
		params.add("param1", "v1");
		params.add("param6", "v2");
		params.add("a_param", "v3");
		params.add("param2", "v4");
		Assert.assertEquals("a_param=v3&param1=v1&param2=v4&param6=v2", ParameterUtils.buildFormUrlEncodedString(params.sort()));
	}

	@Test
	public void shouldProperlySortParametersWithTheSameName() {
		params.add("param1", "v1");
		params.add("param6", "v2");
		params.add("a_param", "v3");
		params.add("param1", "v4");
		Assert.assertEquals("a_param=v3&param1=v1&param1=v4&param6=v2", ParameterUtils.buildFormUrlEncodedString(params.sort()));
	}

//	@Test
//	public void shouldNotModifyTheOriginalParameterList() {
//		params.add("param1", "v1");
//		params.add("param6", "v2");
//
//		assertNotSame(params, params.sort());
//	}
}
