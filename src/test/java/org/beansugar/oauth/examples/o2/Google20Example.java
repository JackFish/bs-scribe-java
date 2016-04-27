package org.beansugar.oauth.examples.o2;

import lombok.extern.slf4j.Slf4j;
import org.beansugar.oauth.o20.builder.OAuth20ServiceBuilder;
import org.beansugar.oauth.o20.builder.api.Google20Api;
import org.beansugar.oauth.o20.service.OAuth20Service;

@Slf4j
public class Google20Example {
	private static final String NETWORK_NAME = "Google20";
	private static final String API_KEY = "your_api_key";
	private static final String API_SECRET = "your_api_secret";
	private static final String PROTECTED_RESOURCE_URL = "https://www.googleapis.com/oauth2/v1/userinfo?alt=json";

	private static final String callbackUrl = "urn:ietf:wg:oauth:2.0:oob";
	private static final String scope = "https://www.googleapis.com/auth/plus.me https://www.googleapis.com/auth/userinfo.email ";

	public static void main(String[] args) {
		OAuth20Service service = new OAuth20ServiceBuilder()
				.provider(Google20Api.class)
				.apiKey(API_KEY)
				.apiSecret(API_SECRET)
				.callback(callbackUrl)
				.scope(scope)
				.build();
		OAuth2ExampleHelper.test(service, NETWORK_NAME, PROTECTED_RESOURCE_URL);
	}

}