package org.beansugar.oauth.model;

import org.beansugar.oauth.model.type.HttpVerb;
import org.beansugar.oauth.net.Request;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RequestTest {
	private Request getRequest;
	private Request postRequest;
	private ConnectionStub connection;

	@Before
	public void setup() throws Exception {
		connection = new ConnectionStub();
		postRequest = new Request(HttpVerb.POST, "http://example.com");
		postRequest.addBodyParameter("param", "value");
		postRequest.addBodyParameter("param with spaces", "value with spaces");
		postRequest.setConnection(connection);
		getRequest = new Request(HttpVerb.GET, "http://example.com?qsparam=value&other+param=value+with+spaces");
		getRequest.setConnection(connection);
	}

	@Test
	public void shouldSetRequestVerb() {
		getRequest.send();
		assertEquals("GET", connection.getRequestMethod());
	}

	@Test
	public void shouldGetQueryStringParameters() {
		assertEquals(2, getRequest.getQueryStringParams().size());
		assertEquals(0, postRequest.getQueryStringParams().size());
		assertTrue(getRequest.getQueryStringParams().contains(new Parameter("qsparam", "value")));
	}

	@Test
	public void shouldAddRequestHeaders() {
		getRequest.addHeader("Header", "1");
		getRequest.addHeader("Header2", "2");
		getRequest.send();
		assertEquals(2, getRequest.getHeaders().size());
		assertEquals(2, connection.getHeaders().size());
	}

	@Test
	public void shouldSetBodyParamsAndAddContentLength() {
		assertEquals("param=value&param%20with%20spaces=value%20with%20spaces", postRequest.getBodyContents());
		postRequest.send();
		assertTrue(connection.getHeaders().containsKey("Content-Length"));
	}

	@Test
	public void shouldSetPayloadAndHeaders() {
		postRequest.addPayload("PAYLOAD");
		postRequest.send();
		assertEquals("PAYLOAD", postRequest.getBodyContents());
		assertTrue(connection.getHeaders().containsKey("Content-Length"));
	}

	@Test
	public void shouldAllowAddingQueryStringParametersAfterCreation() {
		Request request = new Request(HttpVerb.GET, "http://example.com?one=val");
		request.addQueryStringParameter("two", "other val");
		request.addQueryStringParameter("more", "params");
		assertEquals(3, request.getQueryStringParams().size());
	}

	@Test
	public void shouldHandleQueryStringSpaceEncodingProperly() {
		assertTrue(getRequest.getQueryStringParams().contains(new Parameter("other param", "value with spaces")));
	}

	@Test
	public void shouldAutomaticallyAddContentTypeForPostRequestsWithBytePayload() {
		postRequest.addPayload("PAYLOAD".getBytes());
		postRequest.send();
		assertEquals(Request.DEFAULT_CONTENT_TYPE, connection.getHeaders().get("Content-Type"));
	}

	@Test
	public void shouldAutomaticallyAddContentTypeForPostRequestsWithStringPayload() {
		postRequest.addPayload("PAYLOAD");
		postRequest.send();
		assertEquals(Request.DEFAULT_CONTENT_TYPE, connection.getHeaders().get("Content-Type"));
	}

	@Test
	public void shouldAutomaticallyAddContentTypeForPostRequestsWithBodyParameters() {
		postRequest.send();
		assertEquals(Request.DEFAULT_CONTENT_TYPE, connection.getHeaders().get("Content-Type"));
	}

	@Test
	public void shouldBeAbleToOverrideItsContentType() {
		postRequest.addHeader("Content-Type", "my-content-type");
		postRequest.send();
		assertEquals("my-content-type", connection.getHeaders().get("Content-Type"));
	}

	@Test
	public void shouldNotAddContentTypeForGetRequests() {
		getRequest.send();
		assertFalse(connection.getHeaders().containsKey("Content-Type"));
	}
}