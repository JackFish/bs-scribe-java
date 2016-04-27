package org.beansugar.oauth.o20.extractor;

import org.beansugar.oauth.exceptions.OAuthException;
import org.beansugar.oauth.o20.model.Token20Result;
import org.beansugar.oauth.utils.OAuthEncoder;
import org.beansugar.tools.core.check.Check;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Default implementation of {@link AccessTokenExtractor20}. Conforms to OAuth 2.0
 */
public class TokenExtractor20Impl implements AccessTokenExtractor20 {
	private static final String TOKEN_REGEX = "access_token=([^&]+)";
//	private static final String EXPIRES_IN = "access_token=([^&]+)";

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Token20Result extract(String response) {
		Check.notNullOrEmptyString(response, "Response body is incorrect. Can't extract a token from an empty string");

		Matcher matcher = Pattern.compile(TOKEN_REGEX).matcher(response);
		if (matcher.find()) {
			String token = OAuthEncoder.decode(matcher.group(1));
			return new Token20Result(token, -1);
		} else {
			throw new OAuthException("Response body is incorrect. Can't extract a token from this: '" + response + "'", null);
		}
	}
}
