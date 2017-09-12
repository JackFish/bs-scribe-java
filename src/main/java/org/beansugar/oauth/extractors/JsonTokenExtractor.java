package org.beansugar.oauth.extractors;

import org.beansugar.oauth.exceptions.OAuthException;
import org.beansugar.oauth.o10a.extractor.AccessTokenExtractor;
import org.beansugar.oauth.o10a.model.Token10a;
import org.scriptonbasestar.tool.core.check.Check;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonTokenExtractor implements AccessTokenExtractor {
	private Pattern accessTokenPattern = Pattern.compile("\"access_token\":\\s*\"(\\S*?)\"");

	@Override
	public Token10a extract(String response) {
		Check.notNullOrEmptyString(response, "Cannot extract a token from a null or empty String");
		Matcher matcher = accessTokenPattern.matcher(response);
		if (matcher.find()) {
			return new Token10a(matcher.group(1), null);
		} else {
			throw new OAuthException("Cannot extract an access token. Response was: " + response);
		}
	}

}