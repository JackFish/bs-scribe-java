package org.beansugar.oauth.extractors;

import org.beansugar.oauth.exceptions.OAuthParametersMissingException;
import org.beansugar.oauth.model.type.OAuthConstants;
import org.beansugar.oauth.net.OAuthRequest;
import org.beansugar.oauth.utils.OAuthEncoder;
import org.scriptonbasestar.tool.core.check.Check;

import java.util.Map;

/**
 * Default implementation of {@link HeaderExtractor}. Conforms to OAuth 1.0a
 *
 * @author Pablo Fernandez
 *         TODO 나중에 확인필요
 */
public class HeaderExtractorImpl implements HeaderExtractor {
	private static final String PARAM_SEPARATOR = ", ";
	private static final String PREAMBLE = "OAuth ";
	public static final int ESTIMATED_PARAM_LENGTH = 20;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String extract(OAuthRequest request) {
		checkPreconditions(request);
		Map<String, String> parameters = request.getOauthParameters();
		StringBuilder header = new StringBuilder(parameters.size() * ESTIMATED_PARAM_LENGTH);
		header.append(PREAMBLE);
		for (Map.Entry<String, String> entry : parameters.entrySet()) {
			if (header.length() > PREAMBLE.length()) {
				header.append(PARAM_SEPARATOR);
			}
			header.append(String.format("%s=\"%s\"", entry.getKey(), OAuthEncoder.encode(entry.getValue())));
		}

		if (request.getRealm() != null && !request.getRealm().isEmpty()) {
			header.append(PARAM_SEPARATOR);
			header.append(String.format("%s=\"%s\"", OAuthConstants.REALM, request.getRealm()));
		}

		return header.toString();
	}

	private void checkPreconditions(OAuthRequest request) {
		Check.notNull(request, "Cannot extract a header from a null object");

		if (request.getOauthParameters() == null || request.getOauthParameters().size() <= 0) {
			throw new OAuthParametersMissingException(request);
		}
	}

}
