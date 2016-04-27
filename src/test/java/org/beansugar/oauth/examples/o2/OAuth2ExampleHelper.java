package org.beansugar.oauth.examples.o2;

import org.beansugar.oauth.model.Verifier;
import org.beansugar.oauth.model.type.HttpVerb;
import org.beansugar.oauth.net.Response;
import org.beansugar.oauth.o20.model.Token20Result;
import org.beansugar.oauth.o20.service.OAuth20Service;

import java.util.Scanner;

/**
 * @author archmagece
 * @date 2015-11-18
 */
public class OAuth2ExampleHelper {
	static void test(OAuth20Service service, String NETWORK_NAME, String PROTECTED_RESOURCE_URL) {
		Scanner in = new Scanner(System.in);

		System.out.println("=== " + NETWORK_NAME + "'s OAuth Workflow ===");
		System.out.println();

		// Obtain the Authorization URL
		System.out.println("Fetching the Authorization URL...");
		String authorizationUrl = service.getAuthorizationUrl();
		System.out.println("Got the Authorization URL!");
		System.out.println("Now go and authorize Scribe here:");
		System.out.println(authorizationUrl);
		System.out.println("And paste the authorization code here");
		System.out.print("verifier >>");
		Verifier verifier = new Verifier(in.nextLine());
		System.out.print("state >>");
		String state = in.nextLine();
		System.out.println();

		// Trade the Request Token20 and Verifier for the Access Token20
		System.out.println("Trading the Request Token20 for an Access Token20...");
		Token20Result tokenResult = service.getTokenResult(verifier, state);
		System.out.println("Got the Access Token20!");
		System.out.println("(if your curious it looks like this: " + tokenResult + " )");
		System.out.println();

		// Now let's go and ask for a protected resource!
		System.out.println("Now we're going to access a protected resource...");
		Response response = service.getResult(HttpVerb.GET, PROTECTED_RESOURCE_URL, tokenResult);
		System.out.println("Got it! Lets see what we found...");
		System.out.println();
		System.out.println(response.getCode());
		System.out.println(response.getBody());

		System.out.println();
		System.out.println("Thats it man! Go and build something awesome with Scribe! :)");
	}
}
