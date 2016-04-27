package org.beansugar.oauth.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class StringUtils {

	public static String join(String separator, String... params) {
		StringBuilder sb = new StringBuilder();
		int size = params.length;
		for (String param : params) {
			sb.append(param);
			if (--size == 0) {
				break;
			}
			sb.append(separator);
		}
		return sb.toString();
	}

	public static String join(String separator, Map<String, String> map) {
		StringBuilder sb = new StringBuilder();
		int size = map.size();
		for (Map.Entry entry : map.entrySet()) {
			sb.append(entry.getKey()).append('=').append(entry.getValue());
			if (--size == 0) {
				break;
			}
			sb.append(separator);
		}
		return sb.toString();
	}
}
