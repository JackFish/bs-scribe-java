package org.beansugar.oauth.examples.o2;

import lombok.extern.slf4j.Slf4j;
import org.beansugar.oauth.model.Verifier;
import org.beansugar.oauth.model.type.HttpVerb;
import org.beansugar.oauth.net.Response;
import org.beansugar.oauth.o20.builder.api.Facebook20Api;
import org.beansugar.oauth.o20.model.OAuth20Config;
import org.beansugar.oauth.o20.model.Token20Result;
import org.beansugar.oauth.o20.service.OAuth20ServiceSimple;
import org.junit.Before;
import org.junit.Test;

@Slf4j
public class Facebook20SimpleExample {
	private static final String NETWORK_NAME = "Facebook";
	private static final String API_KEY = "your_api_key";
	private static final String API_SECRET = "your_api_secret";
	//	private static final String PROTECTED_RESOURCE_URL = "https://graph.facebook.com/me";
//	private static final String PROTECTED_RESOURCE_URL = "https://graph.facebook.com/v2.2/me";
	private static final String PROTECTED_RESOURCE_URL = "https://graph.facebook.com/v2.4/me";
//	private static final String PROTECTED_RESOURCE_URL = "https://graph.facebook.com/me?fields=token_for_business";
//	private static final String PROTECTED_RESOURCE_URL = "https://graph.facebook.com/v2.0/me";

	OAuth20ServiceSimple callService;

	/**
	 * caution !!
	 * 1 not automation test!!!
	 * you should copy and paste key-secret manaully
	 * 2 english is not my mother language
	 */

	/**
	 * OAuth10aService 인스턴스 생성
	 * create instance for OAuth10aService
	 */
	@Before
	public void before() {
//		beansugar
		callService = new OAuth20ServiceSimple(new Facebook20Api(), new OAuth20Config(API_KEY, API_SECRET, "http://sso.beansugar.org/fwe", null));
	}

	/**
	 * 첫번째 First
	 * 패쓰!! OAuth2.0은 리퀘스트 토큰이 필요 없습니다
	 * pass!! OAuth2.0 no need request token
	 */
//	@Test
	public void testRequestToken() {
		System.out.println("no request token for oauth2");
	}

	/**
	 * 두번째 Second
	 * 콘솔에서 authorizationUrl을 브라우저의 주소바에 복붙 그리고 로그인
	 * copy authorizationUrl to your web browser address-bar and login
	 */
	@Test
	public void testAuthorizationUrl() {
		String authorizationUrl = callService.getAuthorizationUrl();
		System.out.println(authorizationUrl);
	}

	/**
	 * 셋째 Third
	 * 두번째 과정을 마쳤으면 code(verifierString)을 브라우저에 복사하세요
	 * if you successfully pass second~ you can see code(verifierString) on your browser string
	 * ex)
	 * http://sso.beansugar.org/fwe?code=AQBLlf6RHOs72VCZARUPvuZ9qLrHHUZrsVQ3e7wg4uR0wkZ2rZD-Y2E9FqbiV-zoRDZ2dWKT5DF7m-57bIIVO1tlXHzXxMO_mhyu0JtNtDDergoM9nGz_na_AjIm2NRpsf0TlXY6O_jvuYAM2oDFd_yGnpQTKOHUIANBv-gfTyxY2NEg1--rlDh43XUTrUKRB_1PE3RZFppyW74jIu9ArrhaR2kSOaMUEiKTafElPOc5dcpAlVvlTiYfRsmSBbG4ylGZq-yllA_qOvz-HCDmKn-JVzta5wKePAIXwBd4v1kLe69QJZrOjHg1B9rBSEtr_U6ZGghUi0iClT83fe5zz8dq#_=_
	 * <p>
	 * accessToken : [fjoejwejiweoi, ]
	 * OAuth2는 token-secret이 없습니다. token-key만
	 * OAuth2 don't have access token-secret!! only token-key
	 */
	@Test
	public void testAccessToken() {
		String verifierString = "AQDUEfI1-_vxEnfj2G5gwWyqfBCCC38Onl3nyHw8pK3HZ2eVhOraOPFFMtP8c1puXkDm-6UUlu3MntyMXCtNpXNDOx8Tk033vknIdyTARk3LtprmV9AQ0-uEy8NM0EZ3FH82Exzw9A-pcQYy0QvIMu1zvZUawN55G907SZslFWQcbyERKKegM5zhwFGQ_Udzrrcbm4QGvXA01W0LgCuPTKbm_N-MfU1rYFaQacn0qMQrtdReULq8TqiPjUjVyY3OhjVk4yJAk2Do93LdHLauHbNFgLv7WnzlZ5AEodZBh2Qd17sCZcFMEFBGBjnYIsk5nBnMqVJEs17sA3S0gABl9QXs#_=_";
		String state = "AQDUEfI1-_vxEnfj2G5gwWyqfBCCC38Onl3nyHw8pK3HZ2eVhOraOPFFMtP8c1puXkDm-6UUlu3MntyMXCtNpXNDOx8Tk033vknIdyTARk3LtprmV9AQ0-uEy8NM0EZ3FH82Exzw9A-pcQYy0QvIMu1zvZUawN55G907SZslFWQcbyERKKegM5zhwFGQ_Udzrrcbm4QGvXA01W0LgCuPTKbm_N-MfU1rYFaQacn0qMQrtdReULq8TqiPjUjVyY3OhjVk4yJAk2Do93LdHLauHbNFgLv7WnzlZ5AEodZBh2Qd17sCZcFMEFBGBjnYIsk5nBnMqVJEs17sA3S0gABl9QXs#_=_";
		Verifier verifier = new Verifier(verifierString);

		Token20Result tokenResult = callService.getTokenResult(verifier, state);
		System.out.println(tokenResult);
	}

	/**
	 * fourth
	 * 끝. 페이스북 계정 정보를 콘솔에서 볼 수 있습니다
	 * fin. you can see your facebook account info
	 */
	@Test
	public void accessResource() {
		//code="~~~~"
		String accessTokenKeyReuse = "CAAHbCBuEkzsBABzhToMNZA4cjJTOgZCoAfurvzlSgctIAMn5HCoGeJAluLWFFHPeYTnriqaBnSRZBcRjZCU9tcNONYSEjyY9KkGVVkkvziMfLiYb0He8cQJpaNFjcRZC9FrCOyffqIt3DSZBZAShEqpfyZAi322OYyuDG3CVPgEUwPhXdZBQT62DfbUmsmIEcsQkZD";

		OAuth20ServiceSimple callService = new OAuth20ServiceSimple(new Facebook20Api(), new OAuth20Config(API_KEY, API_SECRET, null, null));
		Response response2 = callService.getResult(HttpVerb.GET, PROTECTED_RESOURCE_URL, new Token20Result(accessTokenKeyReuse, -1));

		// Now let's go and ask for a protected resource!
		System.out.println("Got it! Lets see what we found...");
		System.out.println();
		System.out.println(response2.getBody());

		System.out.println();
		System.out.println("That's it man! Go and build something awesome with Scribe! :)");
	}

}