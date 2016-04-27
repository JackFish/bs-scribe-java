package org.beansugar.oauth.o20.type;

import lombok.Getter;

/**
 * @author archmagece
 * @date 2015-11-18
 */
public enum AccessTokenType {

	BEARER("Bearer");

	@Getter
	public String value;

	AccessTokenType(String value) {
		this.value = value;
	}

	public String toString() {
		return value;
	}

}
