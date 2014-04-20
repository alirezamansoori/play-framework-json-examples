play-framework-json-examples
============================

An experimental attempt at consuming and generating JSON requests and responses in context of the Play Framework for Java.

# Points of Interest

## Route File

The route file provides two major endpoints:
* The palindrome testing page resource is given the route `/palindrome`
* The palindrome test API call resource is given the route `/api/palindrome.json`
** It can be a helpful design practice to separate main page resource routes from AJAX API calls resource routes.
** By adding the `.json` suffix to the resource, we leave the door open to permitting access via other formats, e.g. `.xml`.  This pattern is even more useful when an API is publicly exposed.

## Models

Two simple models are used:
* `PalindromeTestRequest` handles the structure of the incoming palindrome test request; no logic is bound to this object, though accessors could certainly be used if desired.
* `PalindromeTestResult` handles the structure of the outgoing palindrome test result.
** Objects of this model class include a helper method to retrieve a JSON node tree representation of themselves.  This is for convenience and consistency, delegating the format to one location.
** Note that other libraries not integrated with Play have more thoroughly integrated means of defining model structure mappings to XML and JSON.  See the [Jackson library](https://github.com/FasterXML/jackson) as one such example.

## Controllers

The `testPalindrome` resource defines our primary point of logic for the application.
* The `@BodyParser.Of` annotation is used to declare what the expected content types will be for requests and responses.
* The JSON object is retrieved from the request's body (`request().body().asJson()`).
* The object is deserialized into a `PalindromeTestRequest` using the Play Framework wrapper function `Json.fromJson`.
** This may throw other exceptions; the documentation is unclear.
* The `PalindromeTestResult` created returns a JSON tree that we can render directly as our output response.

## Javascript

The browser-based logic for the palindrome testing view is all inside the `palindrome.js` script.
* Note the module pattern being used.
** `Palindrome` module dependencies to other loaded libraries are declared in the wrapping function call.  This could be replaced with other dependency management libraries, e.g. RequireJS.  This wrapper function is called immediately, creating a closure for the module.
** Variable dependencies loaded by the page itself are loaded through the `init` function exposed in the module object `Palindrome`.
* The AJAX call is unfortunately not as clean as would be expected.
** The built-in Play Framework JSON deserializer does not accept the standard JSON formatting of the jQuery AJAX call.  It requires keys to be double-quote wrapped strings; therefore the output object is manually serialized here.
