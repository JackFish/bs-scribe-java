package org.beansugar.oauth.model;

import org.beansugar.oauth.o10a.model.Token10a;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotSame;

public class TokenTest {
	@Test
	public void shouldTestEqualityBasedOnTokenAndSecret() throws Exception {
		Token10a expected = new Token10a("access", "secret");
		Token10a actual = new Token10a("access", "secret");

		assertEquals(expected, actual);
		assertEquals(actual, actual);
	}

	@Test
	public void shouldNotDependOnRawString() throws Exception {
		Token10a expected = new Token10a("access", "secret");
		Token10a actual = new Token10a("access", "secret");

		assertEquals(expected, actual);
	}

	@Test
	public void shouldReturnSameHashCodeForEqualObjects() throws Exception {
		Token10a expected = new Token10a("access", "secret");
		Token10a actual = new Token10a("access", "secret");

		assertEquals(expected.hashCode(), actual.hashCode());
	}

	@Test
	public void shouldNotBeEqualToNullOrOtherObjects() throws Exception {
		Token10a expected = new Token10a("access", "secret");

		assertNotSame(expected, null);
		assertNotSame(expected, new Object());
	}
}
