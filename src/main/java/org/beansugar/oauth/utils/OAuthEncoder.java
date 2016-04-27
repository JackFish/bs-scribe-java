package org.beansugar.oauth.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.beansugar.oauth.exceptions.OAuthException;
import org.beansugar.tools.core.check.Check;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author Pablo Fernandez
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class OAuthEncoder {
	private static String CHARSET = "UTF-8";
	private static final Map<String, String> ENCODING_RULES;

	static {
		Map<String, String> rules = new HashMap<>();
		rules.put("*", "%2A");
		rules.put("+", "%20");
		rules.put("%7E", "~");
		ENCODING_RULES = Collections.unmodifiableMap(rules);
	}

	public static String encode(String plain) {
		//FIXME null or empty인지 null인지 empty인지
		Check.notNull(plain, "Cannot encode null object");
		String encoded = "";
		try {
			encoded = URLEncoder.encode(plain, CHARSET);
		} catch (UnsupportedEncodingException uee) {
			throw new OAuthException("Charset not found while encoding string: " + CHARSET, uee);
		}
		for (Map.Entry<String, String> rule : ENCODING_RULES.entrySet()) {
			encoded = applyRule(encoded, rule.getKey(), rule.getValue());
		}
		return encoded;
	}

	private static String applyRule(String encoded, String toReplace, String replacement) {
		return encoded.replaceAll(Pattern.quote(toReplace), replacement);
	}

	public static String decode(String encoded) {
		Check.notNull(encoded, "Cannot decode null object");
		try {
			return URLDecoder.decode(encoded, CHARSET);
		} catch (UnsupportedEncodingException uee) {
			throw new OAuthException("Charset not found while decoding string: " + CHARSET, uee);
		}
	}
}
