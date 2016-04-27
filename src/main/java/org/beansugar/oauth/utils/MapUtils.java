package org.beansugar.oauth.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @author Pablo Fernandez
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MapUtils {

	public static <K, V> String toString(Map<K, V> map) {
		if (map == null) return "";
		if (map.isEmpty()) return "{}";

		StringBuilder result = new StringBuilder();
		for (Map.Entry<K, V> entry : map.entrySet()) {
			result.append(String.format(", %s -> %s ", entry.getKey().toString(), entry.getValue().toString()));
		}
		return "{" + result.substring(1) + "}";
	}
}
