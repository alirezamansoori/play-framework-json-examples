package controllers;

import play.*;
import play.libs.Json;
import play.mvc.*;
import play.mvc.Http.*;

import com.fasterxml.jackson.databind.JsonNode;

import views.html.*;

import models.PalindromeTestRequest;
import models.PalindromeTestResult;

public class Application extends Controller{
	public static Result index(){
		return ok(index.render("Your new application is ready."));
	}

	public static Result palindromePage(){
		return ok(palindrome.render());
	}

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