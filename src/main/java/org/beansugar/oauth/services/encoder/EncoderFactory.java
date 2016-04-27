package org.beansugar.oauth.services.encoder;


import lombok.Getter;

/**
 * @author archmagece
 * @since 2014-11-14-16
 */
public class EncoderFactory {
	@Getter
	private Encoder encoder;

	private static EncoderFactory instance;

	public static synchronized EncoderFactory getInstance() {
		if (instance == null) {
			instance = new EncoderFactory();
		}
		return instance;
	}

	private EncoderFactory() {
		try {
			this.encoder = new CommonsEncoder();
		} catch (ClassNotFoundException e) {
			this.encoder = new DatatypeConverterEncoder();
		}
	}

}
