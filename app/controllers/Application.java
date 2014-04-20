package controllers;

import play.*;
import play.libs.Json;
import play.mvc.*;
import play.mvc.Http.*;

import com.fasterxml.jackson.databind.JsonNode;

import views.html.*;

import models.PalindromeTestRequest;
import models.PalindromeTestResult;

/**
 * The root controller for this simple application.
 * All server-side logic is represented here.
 */
public class Application extends Controller{

	/**
	 * @return a rendering of the standard Play default homepage
	 */
	public static Result index(){
		return ok(index.render("Your new application is ready."));
	}

	/**
	 * @return a rendering of the Palindrome Test page
	 */
	public static Result palindromePage(){
		return ok(palindrome.render());
	}

	/**
	 * <p>
	 *     Tests a string to determine whether it is palindromic, and returns the result of the test.
	 * </p>
	 *
	 * <p>
	 *     Expects a JSON request of the form:
	 *     <pre>
	 * {
	 *     "testString": "[Test string here]"
	 * }
	 *     </pre>
	 * </p>
	 *
	 * <p>
	 *     A malformed or uninterpretable requests returns a raw string containing an error message.
	 * </p>
	 *
	 * <p>
	 *     A successful request returns a JSON response of the form:
	 *     <pre>
	 * {
	 *     "isPalindrome": [true|false],
	 *     "testString": "[Test string here]"
	 * }
	 *     </pre>
	 * </p>
	 *
	 * @return a JSON response representing the result of a palindrome test for the requested string.
	 */
	@BodyParser.Of(BodyParser.Json.class)
	public static Result testPalindrome(){
		JsonNode requestNode = request().body().asJson();

		PalindromeTestRequest testRequest;
		try{
			testRequest = Json.fromJson(requestNode, PalindromeTestRequest.class);
		}catch(NullPointerException e){
			return badRequest("No phrase sent for testing.");
		}

		String toTest = testRequest.testString;

		if(toTest == null){
			return badRequest("No phrase sent for testing.");
		}

		String strippedString = toTest.toLowerCase().replaceAll("[^\\p{Alnum}]+", "");
		String reversedString = new StringBuilder(strippedString).reverse().toString();
		boolean isPalindrome = strippedString.equals(reversedString);

		PalindromeTestResult resultModel = new PalindromeTestResult(isPalindrome, toTest);
		return ok(resultModel.asJson());
	}
}