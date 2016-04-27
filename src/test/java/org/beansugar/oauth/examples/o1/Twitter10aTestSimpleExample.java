package org.beansugar.oauth.examples.o1;

import org.beansugar.oauth.model.Verifier;
import org.beansugar.oauth.model.type.HttpVerb;
import org.beansugar.oauth.model.type.OAuthConstants;
import org.beansugar.oauth.net.Response;
import org.beansugar.oauth.o10a.builder.api.TwitterApi;
import org.beansugar.oauth.o10a.model.OAuth10aConfig;
import org.beansugar.oauth.o10a.model.Token10a;
import org.beansugar.oauth.o10a.service.OAuth10aService;
import org.beansugar.oauth.o10a.service.OAuth10aServiceSimple;
import org.junit.Before;
import org.junit.Test;

public class Twitter10aTestSimpleExample {
	private static final String NETWORK_NAME = "Twitter";
	private static final String API_KEY = "your_api_key";
	private static final String API_SECRET = "your_api_secret";
	private static final String PROTECTED_RESOURCE_URL = "https://api.twitter.com/1.1/account/verify_credentials.json";


	String requestTokenKey = "mKU0bgAAAAAAX2ssAAABURoLwFk";
	String requestTokenSecret = "UcnYuoS4KhoGDWIHq1Hp3LxVSDL3byRj";

	String accessTokenKeyReuse = "208367948-fzzH9F1eRDq9EZUyMtqTykrIto57QGXgVVoSOuig";
	String accessTokenSecretReuse = "nyXMqDEasNZkvluYGEORyRbh4v0IB5pXJLq1Tqg1uwO7d";

	String verifierString = "0404848";


	private OAuth10aService callService;

	/**
	 * caution !!
	 * 1 not automation test!!!
	 * you should copy and paste key-secret manaully
	 * 2 english is not my mother language
	 */

	/**
	 * create instance for oauth10aservcie
	 */
	@Before
	public void before() {
		callService = new OAuth10aServiceSimple(new TwitterApi(), new OAuth10aConfig(API_KEY, API_SECRET, OAuthConstants.OUT_OF_BAND.getValue(), null));
	}

	/**
	 * first
	 * just run and copy request token key-secret
	 */
	@Test
	public void testRequestToken() {
		Token10a requestToken = callService.getRequestToken();
		System.out.println(requestToken);
	}

	/**
	 * second
	 * copy authorization Url to your web browser address-bar
	 * and login
	 */
	@Test
	public void testAuthorizationUrl() {
		Token10a requestToken = new Token10a(requestTokenKey, requestTokenSecret);

		String authorizationUrl = callService.getAuthorizationUrl(requestToken);
		System.out.println(authorizationUrl);
	}

	/**
	 * third
	 * 두번째 과정을 마쳤으면 verifierString을 브라우저에 복사하세요
	 * if you successfully pass second~ you can see verifierString
	 * and copy accessToken key-secret
	 */
	@Test
	public void testAccessToken() {
		//verifierString from your browser
		Token10a requestToken = new Token10a(requestTokenKey, requestTokenSecret);
		Verifier verifier = new Verifier(verifierString);

		Token10a accessToken = callService.getAccessToken(requestToken, verifier);
		System.out.println(accessToken);
	}

	/**
	 * fourth
	 * 끝. 페이스북 계정 정보를 콘솔에서 볼 수 있습니다
	 * fin. you can see your twitter account info
	 */
	@Test
	public void accessResource() {
		Response response2 = callService.getResult(HttpVerb.GET, PROTECTED_RESOURCE_URL, new Token10a(accessTokenKeyReuse, accessTokenSecretReuse));

		// Now let's go and ask for a protected resource!
		System.out.println("Got it! Lets see what we found...");
		System.out.println();
		System.out.println(response2.getBody());

		System.out.println();
		System.out.println("That's it man! Go and build something awesome with Scribe! :)");
	}

}
