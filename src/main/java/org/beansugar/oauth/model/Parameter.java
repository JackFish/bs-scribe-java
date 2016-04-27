package org.beansugar.oauth.model;

import lombok.Getter;

/**
 * @author Pablo Fernandez
 */
public class Parameter implements Comparable<Parameter> {
	private static final String ENCODING = "UTF-8";

	@Getter
	private final String key;
	@Getter
	private final String value;
	@Getter
	private final boolean essential;

	public Parameter(String key, String value) {
		this(key, value, false);
	}

	public Parameter(String key, String value, boolean essential) {
		this.key = key;
		this.value = value;
		this.essential = essential;
	}


	public boolean equals(Object other) {
		if (other == null) return false;
		if (other == this) return true;
		if (!(other instanceof Parameter)) return false;

		Parameter otherParam = (Parameter) other;
		return otherParam.key.equals(key) && otherParam.value.equals(value);
	}

	public int hashCode() {
		return key.hashCode() + value.hashCode();
	}

	@Override
	public int compareTo(Parameter parameter) {
		int keyDiff = key.compareTo(parameter.key);

		return keyDiff != 0 ? keyDiff : value.compareTo(parameter.value);
	}
}
