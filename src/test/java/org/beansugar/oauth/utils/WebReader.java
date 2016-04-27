package org.beansugar.oauth.utils;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class WebReader {
	public static String Header = "";
	public static String Cookie = "";

	public static String readPage(String surl, String ENCODING, boolean isGetHeader) {

		String ret = "";
		String param = "";

		try {
			URL url = new URL(surl);

			URLConnection urlC = url.openConnection();
			urlC.setUseCaches(false);

			urlC.setRequestProperty("accept-language", "ko");

			urlC.setDoOutput(true);
			urlC.setRequestProperty("Cookie", Cookie);

			if (!param.equals("")) {
				OutputStream outStream = urlC.getOutputStream();
				Writer writer = new OutputStreamWriter(outStream);
				writer.write(param);
			}

			urlC.connect();

			InputStreamReader isr = null;
			BufferedReader br = null;
			String temp;
			String data = "";

			try {
				isr = new InputStreamReader(urlC.getInputStream(), ENCODING);
				br = new BufferedReader(isr);

				while ((temp = br.readLine()) != null)
					data = data + temp;

				br.close();
				isr.close();

				Header = "";
				if (isGetHeader) {
					int n = 0;
					String field = "a";
					while (field != null && !field.equals("")) {
						field = urlC.getHeaderField(n);
						Header += urlC.getHeaderFieldKey(n) + "=" + field
								+ "\n";
						n++;
					}
				}

				ret = data;
			} catch (IOException e) {
				ret = "fail.." + e;
			}
		} catch (IOException e) {
			ret = "fail." + e;
		}

		return (ret);
	}
}
