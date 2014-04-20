package models;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import play.libs.Json;

/**
 * A model class for holding the results of a palindrome test.
 * Also contains utilities for translating the result to a JSON tree structure.
 */
public class PalindromeTestResult{

	/**
	 * Creates a new palindrome test result representing the given parameters.
	 * @param isPalindrome Whether the tested string has been evaluated to be a palindrome or not.
	 * @param testString The string that was tested to produce this result.  Must be non-null.
	 * @throws java.lang.IllegalArgumentException if testString is null.
	 */
	public PalindromeTestResult(boolean isPalindrome, String testString){
		if(testString == null){
			throw new IllegalArgumentException("testString must be non-null.");
		}

		this.isPalindrome = isPalindrome;
		this.testString = testString;
	}

	/**
	 * @return whether the tested string has been evaluated to be a palindrome or not.
	 */
	public boolean isPalindrome(){
		return isPalindrome;
	}

	/**
	 * @return the string that was tested to produce this result.
	 */
	public String getTestString(){
		return testString;
	}

	/**
	 * Creates a representation of this object as a JSON node tree.
	 * @return the root node of a JSON object modeled after this result.
	 */
	public JsonNode asJson(){
		ObjectNode node = Json.newObject();
		node.put("isPalindrome", isPalindrome);
		node.put("testString", testString);
		return node;
	}

	private boolean isPalindrome;
	private String testString;
}