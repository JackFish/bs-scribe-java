package org.beansugar.oauth.utils;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.beansugar.oauth.exceptions.OAuthException;
import org.beansugar.tools.core.check.Check;

/**
 * @author archmagece
 * @since 2014-05-14
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ClassUtils {

	public static <Api> Api createApi(Class<? extends Api> apiClass) {
		Check.notNull(apiClass, "Api class cannot be null");
		try {
			return apiClass.newInstance();
		} catch (Exception e) {
			throw new OAuthException("Error while creating the Api object", e);
		}
	}
}
