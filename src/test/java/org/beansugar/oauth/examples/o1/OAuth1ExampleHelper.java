package org.beansugar.oauth.examples.o1;

import org.beansugar.oauth.model.Verifier;
import org.beansugar.oauth.model.type.HttpVerb;
import org.beansugar.oauth.net.Response;
import org.beansugar.oauth.o10a.model.Token10a;
import org.beansugar.oauth.o10a.service.OAuth10aService;

import java.util.Scanner;

/**
 * @author archmagece
 * @date 2015-11-18
 */
public class OAuth1ExampleHelper {
	static void test(OAuth10aService service, String NETWORK_NAME, String PROTECTED_RESOURCE_URL) {
		Scanner in = new Scanner(System.in);

		System.out.println("=== " + NETWORK_NAME + "'s OAuth Workflow ===");
		System.out.println();

		// Obtain the Request Token10a
		System.out.println("Fetching the Request Token10a...");
		Token10a requestToken = service.getRequestToken();
		System.out.println("Got the Request Token10a!");
		System.out.println();

		// Obtain the Authorization URL
		System.out.println("Fetching the Authorization URL...");
		String authorizationUrl = service.getAuthorizationUrl(requestToken);
		System.out.println("Got the Authorization URL!");
		System.out.println("Now go and authorize Scribe here:");
		System.out.println(authorizationUrl);
		System.out.println("And paste the authorization code here");
		System.out.print(">>");
		Verifier verifier = new Verifier(in.nextLine());
		System.out.println();

		// Trade the Request Token10a and Verfier for the Access Token10a
		System.out.println("Trading the Request Token10a for an Access Token10a...");
		Token10a accessToken = service.getAccessToken(requestToken, verifier);
		System.out.println("Got the Access Token10a!");
		System.out.println("(if your curious it looks like this: " + accessToken + " )");
		System.out.println();

		// Now let's go and ask for a protected resource!
		System.out.println("Now we're going to access a protected resource...");
		Response response = service.getResult(HttpVerb.GET, PROTECTED_RESOURCE_URL, accessToken);
		System.out.println("Got it! Lets see what we found...");
		System.out.println();
		System.out.println(response.getCode());
		System.out.println(response.getBody());

		System.out.println();
		System.out.println("That's it man! Go and build something awesome with Scribe! :)");

	}
}
