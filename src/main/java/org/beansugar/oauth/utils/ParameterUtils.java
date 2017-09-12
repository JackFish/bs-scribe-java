package org.beansugar.oauth.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.beansugar.oauth.model.Parameter;
import org.beansugar.oauth.model.ParameterList;
import org.scriptonbasestar.tool.core.check.Check;

/**
 * @author archmagece
 * @date 2015-11-17
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ParameterUtils {
	private static final char QUERY_STRING_SEPARATOR = '?';
	private static final char PARAM_SEPARATOR = '&';
	private static final char PAIR_SEPARATOR = '=';
	private static final String EMPTY_STRING = "";

	//한개짜리
	public static String buildUrlEncodedPair(Parameter parameter) {
		return OAuthEncoder.encode(parameter.getKey()) + PAIR_SEPARATOR + OAuthEncoder.encode(parameter.getValue());
	}

	//목록
	public static String buildUrl(String url, ParameterList parameterList) {
		Check.notNullOrEmptyString(url, "Cannot append to null URL");
		String queryString = buildFormUrlEncodedString(parameterList);
		if (queryString.equals(EMPTY_STRING)) {
			return url;
		} else {
			url += url.indexOf(QUERY_STRING_SEPARATOR) != -1 ? PARAM_SEPARATOR : QUERY_STRING_SEPARATOR;
			url += queryString;
			return url;
		}
	}

	public static String buildOauthBaseString(ParameterList parameterList) {
		return OAuthEncoder.encode(buildFormUrlEncodedString(parameterList));
	}

	public static String buildFormUrlEncodedString(ParameterList parameterList) {
		if (parameterList.size() == 0) return EMPTY_STRING;

		StringBuilder builder = new StringBuilder();
		for (Parameter p : parameterList) {
			builder.append(PARAM_SEPARATOR).append(ParameterUtils.buildUrlEncodedPair(p));
		}
		return builder.toString().substring(1);
	}
}
