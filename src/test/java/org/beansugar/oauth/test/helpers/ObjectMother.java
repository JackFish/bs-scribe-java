package org.beansugar.oauth.test.helpers;

import org.beansugar.oauth.model.type.HttpVerb;
import org.beansugar.oauth.net.OAuthRequest;
import org.beansugar.oauth.o10a.OAuth1Constants;

public class ObjectMother {

	public static OAuthRequest createSampleOAuthRequest() {
		OAuthRequest request = new OAuthRequest(HttpVerb.GET, "http://example.com");
		request.addOAuthParameter(OAuth1Constants.TIMESTAMP.getValue(), "123456");
		request.addOAuthParameter(OAuth1Constants.CONSUMER_KEY.getValue(), "AS#$^*@&");
		request.addOAuthParameter(OAuth1Constants.CALLBACK.getValue(), "http://example/callback");
		request.addOAuthParameter(OAuth1Constants.SIGNATURE.getValue(), "OAuth-Signature");
		return request;
	}

	public static OAuthRequest createSampleOAuthRequestPort80() {
		OAuthRequest request = new OAuthRequest(HttpVerb.GET, "http://example.com:80");
		request.addOAuthParameter(OAuth1Constants.TIMESTAMP.getValue(), "123456");
		request.addOAuthParameter(OAuth1Constants.CONSUMER_KEY.getValue(), "AS#$^*@&");
		request.addOAuthParameter(OAuth1Constants.CALLBACK.getValue(), "http://example/callback");
		request.addOAuthParameter(OAuth1Constants.SIGNATURE.getValue(), "OAuth-Signature");
		return request;
	}

	public static OAuthRequest createSampleOAuthRequestPort80_2() {
		OAuthRequest request = new OAuthRequest(HttpVerb.GET, "http://example.com:80/test");
		request.addOAuthParameter(OAuth1Constants.TIMESTAMP.getValue(), "123456");
		request.addOAuthParameter(OAuth1Constants.CONSUMER_KEY.getValue(), "AS#$^*@&");
		request.addOAuthParameter(OAuth1Constants.CALLBACK.getValue(), "http://example/callback");
		request.addOAuthParameter(OAuth1Constants.SIGNATURE.getValue(), "OAuth-Signature");
		return request;
	}

	public static OAuthRequest createSampleOAuthRequestPort8080() {
		OAuthRequest request = new OAuthRequest(HttpVerb.GET, "http://example.com:8080");
		request.addOAuthParameter(OAuth1Constants.TIMESTAMP.getValue(), "123456");
		request.addOAuthParameter(OAuth1Constants.CONSUMER_KEY.getValue(), "AS#$^*@&");
		request.addOAuthParameter(OAuth1Constants.CALLBACK.getValue(), "http://example/callback");
		request.addOAuthParameter(OAuth1Constants.SIGNATURE.getValue(), "OAuth-Signature");
		return request;
	}

	public static OAuthRequest createSampleOAuthRequestPort443() {
		OAuthRequest request = new OAuthRequest(HttpVerb.GET, "https://example.com:443");
		request.addOAuthParameter(OAuth1Constants.TIMESTAMP.getValue(), "123456");
		request.addOAuthParameter(OAuth1Constants.CONSUMER_KEY.getValue(), "AS#$^*@&");
		request.addOAuthParameter(OAuth1Constants.CALLBACK.getValue(), "http://example/callback");
		request.addOAuthParameter(OAuth1Constants.SIGNATURE.getValue(), "OAuth-Signature");
		return request;
	}

	public static OAuthRequest createSampleOAuthRequestPort443_2() {
		OAuthRequest request = new OAuthRequest(HttpVerb.GET, "https://example.com:443/test");
		request.addOAuthParameter(OAuth1Constants.TIMESTAMP.getValue(), "123456");
		request.addOAuthParameter(OAuth1Constants.CONSUMER_KEY.getValue(), "AS#$^*@&");
		request.addOAuthParameter(OAuth1Constants.CALLBACK.getValue(), "http://example/callback");
		request.addOAuthParameter(OAuth1Constants.SIGNATURE.getValue(), "OAuth-Signature");
		return request;
	}
}
