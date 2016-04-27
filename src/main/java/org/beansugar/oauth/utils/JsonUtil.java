package org.beansugar.oauth.utils;

import com.google.gson.Gson;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @author archmagece
 * @date 2015-11-18
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JsonUtil {
	private static Gson gson = new Gson();

	//	public static Map<OAuth2ResponseType,String> json2map(String json){
	public static Map<String, String> json2map(String json) {
		return gson.fromJson(json, Map.class);
	}

}
