package org.beansugar.oauth.examples.o1;

import org.beansugar.oauth.o10a.builder.OAuth10aServiceBuilder;
import org.beansugar.oauth.o10a.builder.api.TumblrApi;
import org.beansugar.oauth.o10a.service.OAuth10aService;

public class TumblrExample {
	private static final String NETWORK_NAME = "Tumblr";
	private static final String API_KEY = "your_api_key";
	private static final String API_SECRET = "your_api_secret";
	private static final String PROTECTED_RESOURCE_URL = "http://api.tumblr.com/v2/user/info";

	public static void main(String[] args) {
		OAuth10aService service = new OAuth10aServiceBuilder()
				.provider(TumblrApi.class)
				.apiKey(API_KEY)
				.apiSecret(API_SECRET)
				.callback("http://www.tumblr.com/connect/login_success.html") // OOB forbidden. We need an url and the better is on the tumblr website !
				.build();

		OAuth1ExampleHelper.test(service, NETWORK_NAME, PROTECTED_RESOURCE_URL);
	}
}
