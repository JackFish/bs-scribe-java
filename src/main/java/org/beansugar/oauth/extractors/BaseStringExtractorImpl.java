package org.beansugar.oauth.extractors;

import org.beansugar.oauth.exceptions.OAuthParametersMissingException;
import org.beansugar.oauth.model.ParameterList;
import org.beansugar.oauth.net.OAuthRequest;
import org.beansugar.oauth.utils.OAuthEncoder;
import org.beansugar.oauth.utils.ParameterUtils;
import org.beansugar.tools.core.check.Check;

/**
 * Default implementation of {@link BaseStringExtractor}. Conforms to OAuth 1.0a
 *
 * @author Pablo Fernandez
 *         <p>
 *         TODO 나중에 확인필요
 */
public class BaseStringExtractorImpl implements BaseStringExtractor {

	private static final String AMPERSAND_SEPARATED_STRING = "%s&%s&%s";

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String extract(OAuthRequest request) {
		checkPreconditions(request);
		String verb = OAuthEncoder.encode(request.getVerb().name());
		String url = OAuthEncoder.encode(request.getSanitizedUrl());
		String params = getSortedAndEncodedParams(request);
		return String.format(AMPERSAND_SEPARATED_STRING, verb, url, params);
	}

	//TODO 나중에 확인필요
	private String getSortedAndEncodedParams(OAuthRequest request) {
		ParameterList params = new ParameterList();
		params.addAll(request.getQueryStringParams());
		params.addAll(request.getBodyParams());
		params.addAll(request.getOauthParameters());
		return ParameterUtils.buildOauthBaseString(params.sort());
	}

	private void checkPreconditions(OAuthRequest request) {
		Check.notNull(request, "Cannot extract base string from a null object");

		if (request.getOauthParameters() == null || request.getOauthParameters().size() <= 0) {
			throw new OAuthParametersMissingException(request);
		}
	}
}
