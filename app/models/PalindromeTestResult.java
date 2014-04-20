package models;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import play.libs.Json;

public class PalindromeTestResult{
	public PalindromeTestResult(boolean isPalindrome, String testString){
		if(testString == null){
			throw new IllegalArgumentException("testString must be non-null.");
		}

		this.isPalindrome = isPalindrome;
		this.testString = testString;
	}

	public boolean isPalindrome(){
		return isPalindrome;
	}

	public String getTestString(){
		return testString;
	}

	public JsonNode asJson(){
		ObjectNode node = Json.newObject();
		node.put("isPalindrome", isPalindrome);
		node.put("testString", testString);
		return node;
	}

	private boolean isPalindrome;
	private String testString;
}