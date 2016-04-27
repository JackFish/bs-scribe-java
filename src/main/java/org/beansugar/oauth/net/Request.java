package org.beansugar.oauth.net;

import lombok.Getter;
import lombok.Setter;
import org.beansugar.oauth.exceptions.OAuthConnectionException;
import org.beansugar.oauth.exceptions.OAuthException;
import org.beansugar.oauth.model.ParameterList;
import org.beansugar.oauth.model.type.HttpVerb;
import org.beansugar.oauth.utils.ParameterUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Represents an HTTP Request object
 *
 * @author Pablo Fernandez
 */
public class Request {
	private static final String CONTENT_LENGTH = "Content-Length";
	private static final String CONTENT_TYPE = "Content-Type";
	private static RequestTuner NOOP = new RequestTuner() {
		@Override
		public void tune(Request request) {
		}
	};
	public static final String DEFAULT_CONTENT_TYPE = "application/x-www-form-urlencoded";

	@Getter
	private String url;
	@Getter
	private HttpVerb verb;
	private ParameterList queryStringParams;
	@Getter
	private ParameterList bodyParams;
	@Getter
	private Map<String, String> headers;
	private String payload = null;
	@Setter
	private HttpURLConnection connection;
	@Setter
	private String charset;
	private byte[] bytePayload = null;
	/**
	 * Sets whether the underlying Http Connection is persistent or not.
	 *
	 * @param connectionKeepAlive
	 * @see http://download.oracle.com/javase/1.5.0/docs/guide/net/http-keepalive.html
	 */
	@Setter
	private boolean connectionKeepAlive = false;
	/**
	 * Sets whether the underlying Http Connection follows redirects or not.
	 * <p>
	 * Defaults to true (follow redirects)
	 *
	 * @param followRedirects
	 * @see http://docs.oracle.com/javase/6/docs/api/java/net/HttpURLConnection.html#setInstanceFollowRedirects(boolean)
	 */
	@Setter
	private boolean followRedirects = true;
	private Long connectTimeout = null;
	private Long readTimeout = null;

	/**
	 * Creates a new Http Request
	 *
	 * @param verb Http Verb (GET, POST, etc)
	 * @param url  url with optional querystring parameters.
	 */
	public Request(HttpVerb verb, String url) {
		this.verb = verb;
		this.url = url;
		this.queryStringParams = new ParameterList();
		this.bodyParams = new ParameterList();
		this.headers = new HashMap<>();
	}

	/**
	 * Execute the request and return a {@link Response}
	 *
	 * @return Http Response
	 * @throws RuntimeException if the connection cannot be created.
	 */
	public Response send(RequestTuner tuner) {
		try {
			createConnection();
			return doSend(tuner);
		} catch (Exception e) {
			throw new OAuthConnectionException(e);
		}
	}

	public Response send() {
		return send(NOOP);
	}

	private void createConnection() throws IOException {
		String completeUrl = ParameterUtils.buildUrl(url, queryStringParams);
		if (connection == null) {
			System.setProperty("http.keepAlive", connectionKeepAlive ? "true" : "false");
			connection = (HttpURLConnection) new URL(completeUrl).openConnection();
			connection.setInstanceFollowRedirects(followRedirects);
		}
	}

	Response doSend(RequestTuner tuner) throws IOException {
		connection.setRequestMethod(this.verb.name());
		if (connectTimeout != null) {
			connection.setConnectTimeout(connectTimeout.intValue());
		}
		if (readTimeout != null) {
			connection.setReadTimeout(readTimeout.intValue());
		}
		addHeaders(connection);
		if (verb.equals(HttpVerb.PUT) || verb.equals(HttpVerb.POST)) {
			addBody(connection, getByteBodyContents());
		}
		tuner.tune(this);
		return new Response(connection);
	}

	void addHeaders(HttpURLConnection conn) {
		for (String key : headers.keySet()) {
			conn.setRequestProperty(key, headers.get(key));
		}
	}

	void addBody(HttpURLConnection conn, byte[] content) throws IOException {
		conn.setRequestProperty(CONTENT_LENGTH, String.valueOf(content.length));

		// Set default content type if none is set.
		if (conn.getRequestProperty(CONTENT_TYPE) == null) {
			conn.setRequestProperty(CONTENT_TYPE, DEFAULT_CONTENT_TYPE);
		}
		conn.setDoOutput(true);
		conn.getOutputStream().write(content);
	}

	/**
	 * Add an HTTP Header to the Request
	 *
	 * @param key   the header name
	 * @param value the header value
	 */
	public void addHeader(String key, String value) {
		this.headers.put(key, value);
	}

	/**
	 * Add a body Parameter (for POST/ PUT Requests)
	 *
	 * @param key   the parameter name
	 * @param value the parameter value
	 */
	public void addBodyParameter(String key, String value) {
		this.bodyParams.add(key, value);
	}

	/**
	 * Add a QueryString parameter
	 *
	 * @param key   the parameter name
	 * @param value the parameter value
	 */
	public void addQueryStringParameter(String key, String value) {
		this.queryStringParams.add(key, value);
	}

	/**
	 * Add body payload.
	 * <p>
	 * This method is used when the HTTP body is not a form-url-encoded string,
	 * but another thing. Like for example XML.
	 * <p>
	 * Note: The contents are not part of the OAuth signature
	 *
	 * @param payload the body of the request
	 */
	public void addPayload(String payload) {
		this.payload = payload;
	}

	/**
	 * Overloaded version for byte arrays
	 *
	 * @param payload
	 */
	public void addPayload(byte[] payload) {
		this.bytePayload = payload.clone();
	}

	/**
	 * Get a {@link ParameterList} with the query string parameters.
	 *
	 * @return a {@link ParameterList} containing the query string parameters.
	 * @throws OAuthException if the request URL is not valid.
	 */
	public ParameterList getQueryStringParams() {
		try {
			ParameterList result = new ParameterList();
			String queryString = new URL(url).getQuery();
			result.addQueryString(queryString);
			result.addAll(queryStringParams);
			return result;
		} catch (MalformedURLException mue) {
			throw new OAuthException("Malformed URL", mue);
		}
	}

	/**
	 * Returns the URL without the default port and the query string part.
	 *
	 * @return the OAuth-sanitized URL
	 */
	public String getSanitizedUrl() {
		if (url.startsWith("http://") && (url.endsWith(":80") || url.contains(":80/"))) {
			return url.replaceAll("\\?.*", "").replaceAll(":80", "");
		} else if (url.startsWith("https://") && (url.endsWith(":443") || url.contains(":443/"))) {
			return url.replaceAll("\\?.*", "").replaceAll(":443", "");
		} else {
			return url.replaceAll("\\?.*", "");
		}
	}

	/**
	 * Returns the body of the request
	 *
	 * @return form encoded string
	 * @throws OAuthException if the charset chosen is not supported
	 */
	public String getBodyContents() {
		try {
			return new String(getByteBodyContents(), getCharset());
		} catch (UnsupportedEncodingException uee) {
			throw new OAuthException("Unsupported Charset: " + charset, uee);
		}
	}

	byte[] getByteBodyContents() {
		if (bytePayload != null) return bytePayload;
		String body = (payload != null) ? payload : ParameterUtils.buildFormUrlEncodedString(bodyParams);
		try {
			return body.getBytes(getCharset());
		} catch (UnsupportedEncodingException uee) {
			throw new OAuthException("Unsupported Charset: " + getCharset(), uee);
		}
	}

	/**
	 * Returns the connection charset. Defaults to {@link Charset} defaultCharset if not set
	 *
	 * @return charset
	 */
	public String getCharset() {
		return charset == null ? Charset.defaultCharset().name() : charset;
	}

	/**
	 * Sets the connect timeout for the underlying {@link HttpURLConnection}
	 *
	 * @param duration duration of the timeout
	 * @param unit     unit of time (milliseconds, seconds, etc)
	 */
	public void setConnectTimeout(int duration, TimeUnit unit) {
		this.connectTimeout = unit.toMillis(duration);
	}

	/**
	 * Sets the read timeout for the underlying {@link HttpURLConnection}
	 *
	 * @param duration duration of the timeout
	 * @param unit     unit of time (milliseconds, seconds, etc)
	 */
	public void setReadTimeout(int duration, TimeUnit unit) {
		this.readTimeout = unit.toMillis(duration);
	}


	@Override
	public String toString() {
		return String.format("@Request(%s %s)", getVerb(), getUrl());
	}
}
