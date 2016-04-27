package org.beansugar.oauth.o10a.service;

import lombok.extern.slf4j.Slf4j;
import org.beansugar.oauth.model.Verifier;
import org.beansugar.oauth.model.type.HttpVerb;
import org.beansugar.oauth.model.type.OAuthConstants;
import org.beansugar.oauth.net.OAuthRequest;
import org.beansugar.oauth.net.Request;
import org.beansugar.oauth.net.RequestTuner;
import org.beansugar.oauth.net.Response;
import org.beansugar.oauth.o10a.OAuth1Constants;
import org.beansugar.oauth.o10a.builder.api.Api10a;
import org.beansugar.oauth.o10a.builder.api.DefaultApi10a;
import org.beansugar.oauth.o10a.model.OAuth10aConfig;
import org.beansugar.oauth.o10a.model.Token10a;
import org.beansugar.oauth.services.encoder.EncoderFactory;
import org.beansugar.oauth.utils.MapUtils;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
public class OAuth10aServiceSimple implements OAuth10aService {
	private static final String VERSION = "1.0";

	private final DefaultApi10a api;
	private final OAuth10aConfig config;
//	private final Token10a accessToken;

	//	public OAuth10aServiceSimple(DefaultApi10a api, OAuth10aConfig config, String accessTokenKey, String accessTokenSecret){
	public OAuth10aServiceSimple(DefaultApi10a api, OAuth10aConfig config) {
		this.api = api;
		this.config = config;
//		this.accessToken = new Token10a(accessTokenKey, accessTokenSecret);
	}

	@Override
	public String getAuthorizationUrl(Token10a requestToken) {
		return api.getAuthorizationUrl(requestToken);
	}

	public Token10a getRequestToken(int timeout, TimeUnit unit) {
		return getRequestToken(new TimeoutTuner(timeout, unit));
	}

	@Override
	public Token10a getRequestToken() {
		return getRequestToken(2, TimeUnit.SECONDS);
	}

	public Token10a getRequestToken(RequestTuner tuner) {
		log.debug("obtaining request token from " + api.getRequestTokenUrl());
		OAuthRequest request = new OAuthRequest(api.getRequestTokenVerb(), api.getRequestTokenUrl());

		log.debug("setting oauth_callback to " + config.getCallback());
		request.addOAuthParameter(OAuth1Constants.CALLBACK.getValue(), config.getCallback());
//		addOAuthDefaultParams(request, OAuthConstants.EMPTY_TOKEN);
		addOAuthDefaultParams(request, Token10a.EMPTY);
		appendSignature(request);

		log.debug("sending request...");
		Response response = request.send(tuner);
		String body = response.getBody();

		log.debug("response status code: " + response.getCode());
		log.debug("response body: " + body);
		return api.getRequestTokenExtractor().extract(body);
	}

	public Token10a getAccessToken(Token10a requestToken, Verifier verifier, int timeout, TimeUnit unit) {
		return getAccessToken(requestToken, verifier, new TimeoutTuner(timeout, unit));
	}

	@Override
	public Token10a getAccessToken(Token10a requestToken, Verifier verifier) {
		return getAccessToken(requestToken, verifier, 2, TimeUnit.SECONDS);
	}

	public Token10a getAccessToken(Token10a requestToken, Verifier verifier, RequestTuner tuner) {
		log.debug("obtaining access token from " + api.getAccessTokenUrl());
		OAuthRequest request = new OAuthRequest(api.getAccessTokenVerb(), api.getAccessTokenUrl());
		request.addOAuthParameter(OAuth1Constants.TOKEN.getValue(), requestToken.getToken());
		request.addOAuthParameter(OAuth1Constants.VERIFIER.getValue(), verifier.getValue());

		log.debug("setting token to: " + requestToken + " and verifier to: " + verifier);
		addOAuthDefaultParams(request, requestToken);
		appendSignature(request);

		log.debug("sending request...");
		Response response = request.send(tuner);
		String body = response.getBody();

		log.debug("response status code: " + response.getCode());
		log.debug("response body: " + body);
		return api.getAccessTokenExtractor().extract(body);
	}

	@Override
	public Response getResult(HttpVerb verb, String resource, Token10a accessToken) {
		OAuthRequest request = new OAuthRequest(verb, resource);

		log.debug("signing request: " + request.getUrl());

		// Do not append the token if empty. This is for two legged OAuth calls.
		if (!accessToken.isEmpty()) {
			request.addOAuthParameter(OAuth1Constants.TOKEN.getValue(), accessToken.getToken());
		}
		log.debug("setting token to: " + accessToken);
		addOAuthDefaultParams(request, accessToken);
		appendSignature(request);
		return request.send();
	}

	private void addOAuthDefaultParams(OAuthRequest request, Token10a token) {
		request.addOAuthParameter(OAuth1Constants.TIMESTAMP.getValue(), api.getTimestampService().getTimestampInSeconds());
		request.addOAuthParameter(OAuth1Constants.NONCE.getValue(), api.getTimestampService().getNonce());
		request.addOAuthParameter(OAuth1Constants.CONSUMER_KEY.getValue(), config.getApiKey());
		request.addOAuthParameter(OAuth1Constants.SIGN_METHOD.getValue(), api.getSignatureService().getSignatureMethod());
		request.addOAuthParameter(OAuth1Constants.VERSION.getValue(), Api10a.VERSION);
		if (config.getScope() != null) {
			request.addOAuthParameter(OAuthConstants.SCOPE.getValue(), config.getScope());
		}
		request.addOAuthParameter(OAuth1Constants.SIGNATURE.getValue(), getSignature(request, token));

		log.debug("appended additional OAuth parameters: " + MapUtils.toString(request.getOauthParameters()));
	}

	private String getSignature(OAuthRequest request, Token10a token) {
		log.debug("generating signature...");
		log.debug("using base64 encoder: " + EncoderFactory.getInstance().getEncoder().getType());
		String baseString = api.getBaseStringExtractor().extract(request);
		String signature = api.getSignatureService().getSignature(baseString, config.getApiSecret(), token.getSecret());

		log.debug("base string is: " + baseString);
		log.debug("signature is: " + signature);
		return signature;
	}

	private void appendSignature(OAuthRequest request) {
		switch (api.getSignatureType()) {
			case Header:
				log.debug("using Http Header signature");

				String oauthHeader = api.getHeaderExtractor().extract(request);
				request.addHeader(OAuth1Constants.HEADER.getValue(), oauthHeader);
				break;
			case QueryString:
				log.debug("using QueryString signature");

				for (Map.Entry<String, String> entry : request.getOauthParameters().entrySet()) {
					request.addQueryStringParameter(entry.getKey(), entry.getValue());
				}
				break;
		}
	}

	private static class TimeoutTuner extends RequestTuner {
		private final int duration;
		private final TimeUnit unit;

		public TimeoutTuner(int duration, TimeUnit unit) {
			this.duration = duration;
			this.unit = unit;
		}

		@Override
		public void tune(Request request) {
			request.setReadTimeout(duration, unit);
		}
	}
}
