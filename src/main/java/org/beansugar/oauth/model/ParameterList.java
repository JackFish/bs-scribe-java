package org.beansugar.oauth.model;

import lombok.extern.slf4j.Slf4j;
import org.beansugar.oauth.utils.OAuthEncoder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

@Slf4j
public final class ParameterList extends ArrayList<Parameter> {
	private static final char QUERY_STRING_SEPARATOR = '?';
	private static final String PARAM_SEPARATOR = "&";
	private static final String PAIR_SEPARATOR = "=";
	private static final String EMPTY_STRING = "";

	public ParameterList add(String key, String value) {
		super.add(new Parameter(key, value));
		return this;
	}

	public ParameterList addAll(Map<String, String> map) {
		for (Map.Entry<String, String> entry : map.entrySet()) {
			super.add(new Parameter(entry.getKey(), entry.getValue()));
		}
		return this;
	}

	public ParameterList addAll(ParameterList other) {
		super.addAll(other);
		return this;
	}

	public void addQueryString(String queryString) {
		if (queryString != null && queryString.length() > 0) {
			for (String param : queryString.split(PARAM_SEPARATOR)) {
				String pair[] = param.split(PAIR_SEPARATOR);
				String key = OAuthEncoder.decode(pair[0]);
				String value = pair.length > 1 ? OAuthEncoder.decode(pair[1]) : EMPTY_STRING;
				super.add(new Parameter(key, value));
			}
		}
	}

	public boolean contains(Parameter param) {
		return super.contains(param);
	}

	public int size() {
		return super.size();
	}

	public ParameterList sort() {
		Collections.sort(this);
		return this;
	}
}
