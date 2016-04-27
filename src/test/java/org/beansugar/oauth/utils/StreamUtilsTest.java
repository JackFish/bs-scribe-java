package org.beansugar.oauth.utils;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class StreamUtilsTest {

	@Test
	public void shouldCorrectlyDecodeAStream() {
		String value = "expected";
		InputStream is = new ByteArrayInputStream(value.getBytes());
		String decoded = StreamUtils.getStreamContents(is);
		assertEquals("expected", decoded);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldFailForNullParameter() {
		InputStream is = null;
		StreamUtils.getStreamContents(is);
		fail("Must throw exception before getting here");
	}
}
