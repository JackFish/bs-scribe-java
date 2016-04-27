package org.beansugar.oauth.services.encoder;

import org.apache.commons.codec.binary.Base64;
import org.beansugar.oauth.exceptions.OAuthSignatureException;

import java.io.UnsupportedEncodingException;

public class CommonsEncoder implements Encoder {

	public CommonsEncoder() throws ClassNotFoundException {
		//check essential dependency before construct
		Class.forName("org.apache.commons.codec.binary.Base64");
	}

	@Override
	public String encode(byte[] bytes) {
		try {
			return new String(Base64.encodeBase64(bytes), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new OAuthSignatureException("Can't perform base64 encoding", e);
		}
	}

	@Override
	public String getType() {
		return "CommonsCodec";
	}

	public static boolean isAvailable() {
		try {
			Class.forName("org.apache.commons.codec.binary.Base64");
			return true;
		} catch (ClassNotFoundException e) {
			return false;
		}
	}
}
