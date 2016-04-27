package org.beansugar.oauth.services.encoder;

import java.io.Serializable;

/**
 * @author archmagece
 * @since 2014-11-14-16
 */
public interface Encoder extends Serializable {
	String encode(byte[] bytes);

	String getType();
}
