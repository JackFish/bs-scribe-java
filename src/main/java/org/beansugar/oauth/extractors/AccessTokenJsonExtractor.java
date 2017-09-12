package org.beansugar.oauth.extractors;

import lombok.extern.slf4j.Slf4j;
import org.beansugar.oauth.exceptions.OAuthException;
import org.beansugar.oauth.o10a.extractor.AccessTokenExtractor;
import org.beansugar.oauth.o10a.model.Token10a;
import org.scriptonbasestar.tool.core.check.Check;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

// TODO 나중에 확인필요
@Slf4j
public class AccessTokenJsonExtractor implements AccessTokenExtractor {
	private static final Pattern accessTokenPattern = Pattern.compile("\"access_token\":\\s*\"(\\S*?)\"");

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Token10a extract(String response) {
		Check.notNullOrEmptyString(response, "Can't extract a token from a null or empty String");
		Matcher matcher = accessTokenPattern.matcher(response);
		if (matcher.find()) {
			return new Token10a(matcher.group(1), "");
		} else {
			throw new OAuthException("Can't extract an access token. Response was: " + response);
		}
	}

}
