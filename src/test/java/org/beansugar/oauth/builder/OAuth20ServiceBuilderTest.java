package org.beansugar.oauth.builder;

import org.beansugar.oauth.model.type.OAuthConstants;
import org.beansugar.oauth.o20.builder.OAuth20ServiceBuilder;
import org.beansugar.oauth.o20.builder.api.Api20;
import org.beansugar.oauth.o20.model.OAuth20Config;
import org.beansugar.oauth.o20.service.OAuth20Service;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OAuth20ServiceBuilderTest {
	private OAuth20ServiceBuilder builder;

	@Before
	public void setup() {
		builder = new OAuth20ServiceBuilder();
	}

	@Test
	public void shouldReturnConfigDefaultValues() {
		builder.provider(ApiMock.class).apiKey("key").apiSecret("secret").build();
		assertEquals(ApiMock.config.getApiKey(), "key");
		assertEquals(ApiMock.config.getApiSecret(), "secret");
		assertEquals(ApiMock.config.getCallback(), OAuthConstants.OUT_OF_BAND.toString());
//    assertEquals(ApiMock.config.getSignatureType(), SignatureType.Header);
	}

	@Test
	public void shouldAcceptValidCallbackUrl() {
		builder.provider(ApiMock.class).apiKey("key").apiSecret("secret").callback("http://example.com").build();
		assertEquals(ApiMock.config.getApiKey(), "key");
		assertEquals(ApiMock.config.getApiSecret(), "secret");
		assertEquals(ApiMock.config.getCallback(), "http://example.com");
	}

	@Test
	public void shouldAcceptASignatureType() {
//    builder.provider(ApiMock.class).apiKey("key").apiSecret("secret").signatureType(SignatureType.QueryString).build();
		builder.provider(ApiMock.class).apiKey("key").apiSecret("secret").build();
		assertEquals(ApiMock.config.getApiKey(), "key");
		assertEquals(ApiMock.config.getApiSecret(), "secret");
//    assertEquals(ApiMock.config.getSignatureType(), SignatureType.QueryString);
	}

	@Test
	public void shouldNotAcceptNullAsCallback() {
		builder.provider(ApiMock.class).apiKey("key").apiSecret("secret").callback(null).build();
	}

	@Test
	public void shouldAcceptAnScope() {
		builder.provider(ApiMock.class).apiKey("key").apiSecret("secret").scope("rss-api").build();
		assertEquals(ApiMock.config.getApiKey(), "key");
		assertEquals(ApiMock.config.getApiSecret(), "secret");
		assertEquals(ApiMock.config.getScope(), "rss-api");
	}

	public static class ApiMock implements Api20 {
		public static OAuth20Config config;

		@Override
		public OAuth20Service createService(OAuth20Config config) {
			ApiMock.config = config;
			return null;
		}
	}
}
