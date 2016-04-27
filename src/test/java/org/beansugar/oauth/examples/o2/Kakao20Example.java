package org.beansugar.oauth.examples.o2;

import lombok.extern.slf4j.Slf4j;
import org.beansugar.oauth.o20.builder.OAuth20ServiceBuilder;
import org.beansugar.oauth.o20.builder.api.Kakao20Api;
import org.beansugar.oauth.o20.service.OAuth20Service;

/**
 * @author archmagece
 * @date 2015-11-18
 */
@Slf4j
public class Kakao20Example {
	private static final String NETWORK_NAME = "kakao";
	private static final String API_KEY = "your_api_key";
	//BSWARN 미친놈들이라 이게 널임
	private static final String API_SECRET = "your_api_secret";
	private static final String PROTECTED_RESOURCE_URL = "https://kapi.kakao.com/v1/api/talk/profile";

	private static final String callbackUrl = "http://sso.beansugar.org/action/oauth2/naver/redirect";
	private static final String scope = null;

	public static void main(String[] args) {
		OAuth20Service service = new OAuth20ServiceBuilder()
				.provider(Kakao20Api.class)
				.apiKey(API_KEY)
				.apiSecret(API_SECRET)
				.callback(callbackUrl)
				.scope(scope)
				.build();
		OAuth2ExampleHelper.test(service, NETWORK_NAME, PROTECTED_RESOURCE_URL);
	}
}
