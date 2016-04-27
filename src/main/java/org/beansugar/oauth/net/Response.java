package org.beansugar.oauth.net;

import lombok.Getter;
import org.beansugar.oauth.exceptions.OAuthException;
import org.beansugar.oauth.utils.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents an HTTP Response.
 *
 * @author Pablo Fernandez
 */
public class Response {
	private static final String EMPTY = "";

	/**
	 * Obtains the HTTP status code
	 */
	@Getter
	private int code;
	/**
	 * Obtains the HTTP status message.
	 * Returns <code>null</code> if the message can not be discerned from the response (not valid HTTP)
	 */
	@Getter
	private String message;
	private String body;
	/**
	 * Obtains the meaningful stream of the HttpUrlConnection, either inputStream
	 * or errorInputStream, depending on the status code
	 * <p>
	 * input stream / error stream
	 */
	@Getter
	private InputStream stream;
	/**
	 * Obtains a {@link Map} containing the HTTP Response Headers
	 */
	@Getter
	private Map<String, String> headers;

	public Response(HttpURLConnection connection) throws IOException {
		try {
			connection.connect();
			code = connection.getResponseCode();
			message = connection.getResponseMessage();
			headers = parseHeaders(connection);
			stream = isSuccessful() ? connection.getInputStream() : connection.getErrorStream();
		} catch (UnknownHostException e) {
			throw new OAuthException("The IP address of a host could not be determined.", e);
		}
	}

	private String parseBodyContents() {
		body = StreamUtils.getStreamContents(stream);
		return body;
	}

	private Map<String, String> parseHeaders(HttpURLConnection conn) {
		Map<String, String> headers = new HashMap<>();
		for (String key : conn.getHeaderFields().keySet()) {
			headers.put(key, conn.getHeaderFields().get(key).get(0));
		}
		return headers;
	}

	public boolean isSuccessful() {
		return code >= 200 && code < 400;
	}

	/**
	 * Obtains the HTTP Response body
	 *
	 * @return response body
	 */
	public String getBody() {
		return body != null ? body : parseBodyContents();
	}


	/**
	 * Obtains a single HTTP Header value, or null if undefined
	 *
	 * @param name the header name.
	 * @return header value or null.
	 */
	public String getHeader(String name) {
		return headers.get(name);
	}

}