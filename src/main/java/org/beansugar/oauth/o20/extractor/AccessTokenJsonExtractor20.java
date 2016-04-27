package org.beansugar.oauth.o20.extractor;

import lombok.extern.slf4j.Slf4j;
import org.beansugar.oauth.exceptions.OAuthException;
import org.beansugar.oauth.o20.model.Token20Result;
import org.beansugar.oauth.utils.JsonUtil;
import org.beansugar.tools.core.check.Check;

import java.util.Map;
import java.util.regex.Pattern;

@Slf4j
public class AccessTokenJsonExtractor20 implements AccessTokenExtractor20 {
	private static final Pattern accessTokenPattern = Pattern.compile("\"access_token\":\\s*\"(\\S*?)\"");

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Token20Result extract(String response) {
		Check.notNullOrEmptyString(response, "Can't extract a token from a null or empty String");
		try{
			Map<String,String> map = JsonUtil.json2map(response);
			Token20Result tokenResult = new Token20Result(map.get("access_token"), -1);
			return tokenResult;
		}catch (Exception e){
			e.printStackTrace();
			throw new OAuthException("Can't extract an access token. Response was: " + response);
		}
	}

}
