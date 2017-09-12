package org.beansugar.oauth.o10a.model;

import lombok.Getter;
import org.scriptonbasestar.tool.core.check.Check;

import java.io.Serializable;

/**
 * Represents an OAuth token (either request or access token) and its secret
 *
 * @author Pablo Fernandez
 */
public class Token10a implements Serializable {

	@Getter
	private final String token;
	@Getter
	private final String secret;

	public static final Token10a EMPTY = new Token10a();
	private Token10a(){
		this.token = "";
		this.secret = "";
	}

	/**
	 * Default constructor
	 *
	 * @param token  token value. Can't be null.
	 * @param secret token secret. Can't be null.
	 */
	public Token10a(String token, String secret) {
		Check.notNull(token, "token can't be null");
		Check.notEmptyString(secret, "secret can't be empty");

		this.token = token;
		this.secret = secret;
	}

	@Override
	public String toString() {
		return String.format("Token10a[%s , %s]", token, secret);
	}

	/**
	 * Returns true if the token is empty (token = "", secret = "")
	 */
	public boolean isEmpty() {
		return "".equals(this.token) && "".equals(this.secret);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Token10a that = (Token10a) o;
		return token.equals(that.token) && secret.equals(that.secret);
	}

	@Override
	public int hashCode() {
		return 31 * token.hashCode() + secret.hashCode();
	}
}
