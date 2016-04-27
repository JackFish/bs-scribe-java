package org.beansugar.oauth.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.beansugar.tools.core.check.Check;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * Utils to deal with Streams.
 *
 * @author Pablo Fernandez
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class StreamUtils {

	/**
	 * Returns the stream contents as an UTF-8 encoded string
	 *
	 * @param is input stream
	 * @return string contents
	 */
	public static String getStreamContents(InputStream is) {
		Check.notNull(is, "Cannot get String from a null object");
		try {
			final char[] buffer = new char[0x10000];
			StringBuilder out = new StringBuilder();
			Reader in = new InputStreamReader(is, "UTF-8");
			int read;
			do {
				read = in.read(buffer, 0, buffer.length);
				if (read > 0) {
					out.append(buffer, 0, read);
				}
			} while (read >= 0);
			in.close();
			return out.toString();
		} catch (IOException ioe) {
			throw new IllegalStateException("Error while reading response body", ioe);
		}
	}
}
