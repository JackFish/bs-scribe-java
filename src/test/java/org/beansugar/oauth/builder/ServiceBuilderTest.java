package org.beansugar.oauth.builder;

import org.beansugar.oauth.model.type.OAuthConstants;
import org.beansugar.oauth.o10a.builder.OAuth10aServiceBuilder;
import org.beansugar.oauth.o10a.builder.api.Api10a;
import org.beansugar.oauth.o10a.model.OAuth10aConfig;
import org.beansugar.oauth.o10a.service.OAuth10aService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ServiceBuilderTest {
	private OAuth10aServiceBuilder builder;

	@Before
	public void setup() {
		builder = new OAuth10aServiceBuilder();
	}

	@Test
	public void shouldReturnConfigDefaultValues() {
		builder.provider(ApiMock.class).apiKey("key").apiSecret("secret").build();
		Assert.assertEquals(ApiMock.config.getApiKey(), "key");
		Assert.assertEquals(ApiMock.config.getApiSecret(), "secret");
		Assert.assertEquals(ApiMock.config.getCallback(), OAuthConstants.OUT_OF_BAND.toString());
//    assertEquals(ApiMock.config.getSignatureType(), SignatureType.Header);
	}

	@Test
	public void shouldAcceptValidCallbackUrl() {
		builder.provider(ApiMock.class).apiKey("key").apiSecret("secret").callback("http://example.com").build();
		Assert.assertEquals(ApiMock.config.getApiKey(), "key");
		Assert.assertEquals(ApiMock.config.getApiSecret(), "secret");
		Assert.assertEquals(ApiMock.config.getCallback(), "http://example.com");
	}

	@Test
	public void shouldAcceptASignatureType() {
//    builder.provider(ApiMock.class).apiKey("key").apiSecret("secret").signatureType(SignatureType.QueryString).build();
		builder.provider(ApiMock.class).apiKey("key").apiSecret("secret").build();
		Assert.assertEquals(ApiMock.config.getApiKey(), "key");
		Assert.assertEquals(ApiMock.config.getApiSecret(), "secret");
//    assertEquals(ApiMock.config.getSignatureType(), SignatureType.QueryString);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptNullAsCallback() {
		builder.provider(ApiMock.class).apiKey("key").apiSecret("secret").callback(null).build();
	}

	@Test
	public void shouldAcceptAnScope() {
		builder.provider(ApiMock.class).apiKey("key").apiSecret("secret").scope("rss-api").build();
		Assert.assertEquals(ApiMock.config.getApiKey(), "key");
		Assert.assertEquals(ApiMock.config.getApiSecret(), "secret");
		Assert.assertEquals(ApiMock.config.getScope(), "rss-api");
	}

	public static class ApiMock implements Api10a {
		public static OAuth10aConfig config;

		@Override
		public OAuth10aService createService(OAuth10aConfig config) {
			ApiMock.config = config;
			return null;
		}
	}
}
