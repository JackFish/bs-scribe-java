package org.beansugar.oauth.services.encoder;

import javax.xml.bind.DatatypeConverter;

public class DatatypeConverterEncoder implements Encoder {
	@Override
	public String encode(byte[] bytes) {
		return DatatypeConverter.printBase64Binary(bytes);
	}

	@Override
	public String getType() {
		return "DatatypeConverter";
	}
}
