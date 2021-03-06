package org.beansugar.oauth.examples.o2;

import lombok.extern.slf4j.Slf4j;
import org.beansugar.oauth.o20.builder.OAuth20ServiceBuilder;
import org.beansugar.oauth.o20.builder.api.Facebook20Api;
import org.beansugar.oauth.o20.service.OAuth20Service;

@Slf4j
public class Facebook20Example {
	private static final String NETWORK_NAME = "Facebook";
	private static final String API_KEY = "your_api_key";
	private static final String API_SECRET = "your_api_secret";
	private static final String PROTECTED_RESOURCE_URL = "https://graph.facebook.com/me";

	private static final String callbackUrl = "http://sso.beansugar.org/oauth_callback";
	private static final String scope = null;

	public static void main(String[] args) {
		OAuth20Service service = new OAuth20ServiceBuilder()
				.provider(Facebook20Api.class)
				.apiKey(API_KEY)
				.apiSecret(API_SECRET)
				.callback(callbackUrl)
				.scope(scope)
				.build();
		OAuth2ExampleHelper.test(service, NETWORK_NAME, PROTECTED_RESOURCE_URL);
	}

}