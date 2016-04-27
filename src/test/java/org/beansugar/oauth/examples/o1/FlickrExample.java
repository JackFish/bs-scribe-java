package org.beansugar.oauth.examples.o1;

import org.beansugar.oauth.o10a.builder.OAuth10aServiceBuilder;
import org.beansugar.oauth.o10a.builder.api.FlickrApi;
import org.beansugar.oauth.o10a.service.OAuth10aService;

public class FlickrExample {
	private static final String NETWORK_NAME = "Flickr";
	private static final String API_KEY = "your_api_key";
	private static final String API_SECRET = "your_api_secret";
	private static final String PROTECTED_RESOURCE_URL = "https://api.flickr.com/services/rest/";

	public static void main(String[] args) {
		OAuth10aService service = new OAuth10aServiceBuilder()
				.provider(FlickrApi.class)
				.apiKey(API_KEY)
				.apiSecret(API_SECRET)
				.build();

		OAuth1ExampleHelper.test(service, NETWORK_NAME, PROTECTED_RESOURCE_URL);
	}
}
