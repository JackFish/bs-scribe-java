package org.beansugar.oauth.model.type;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author archmagece
 * @since 2015-03-15
 */
public class OAuthConstantsTest {

	@Test
	public void test() {
		System.out.println("scope".equals(OAuthConstants.SCOPE.toString()));
		Assert.assertTrue("scope".equals(OAuthConstants.SCOPE.toString()));
	}
}
