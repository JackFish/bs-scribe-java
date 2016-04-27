package org.beansugar.oauth.examples.o1;

import org.beansugar.oauth.o10a.builder.OAuth10aServiceBuilder;
import org.beansugar.oauth.o10a.builder.api.FoursquareApi;
import org.beansugar.oauth.o10a.service.OAuth10aService;

public class FoursquareExample {
	private static final String NETWORK_NAME = "Foursquare";
	private static final String API_KEY = "FEGFXJUFANVVDHVSNUAMUKTTXCP1AJQD53E33XKJ44YP1S4I";
	private static final String API_SECRET = "AYWKUL5SWPNC0CTQ202QXRUG2NLZYXMRA34ZSDW4AUYBG2RC";
	private static final String PROTECTED_RESOURCE_URL = "http://api.foursquare.com/v1/user";

	public static void main(String[] args) {
		OAuth10aService service = new OAuth10aServiceBuilder()
				.provider(FoursquareApi.class)
				.apiKey(API_KEY)
				.apiSecret(API_SECRET)
				.build();

		OAuth1ExampleHelper.test(service, NETWORK_NAME, PROTECTED_RESOURCE_URL);
	}

}