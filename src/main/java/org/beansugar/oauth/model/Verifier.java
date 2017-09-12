package org.beansugar.oauth.model;

import lombok.Getter;
import org.scriptonbasestar.tool.core.check.Check;

/**
 * Represents an OAuth verifier code.
 *
 * @author Pablo Fernandez
 */
public class Verifier {

	@Getter
	private final String value;

	/**
	 * Default constructor.
	 *
	 * @param value verifier value
	 */
	public Verifier(String value) {
		Check.notNullOrEmptyString(value, "Verifier Must not null or empty");
		this.value = value;
	}
}
