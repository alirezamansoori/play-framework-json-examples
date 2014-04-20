// Module: Palindrome
// Dependencies:
//  - jQuery: the standard jQuery module object
var Palindrome = (function($){
    var context;

    // Attaches a click handler to the 'submit' button on the page
    var setupTestClickHandler = function(){
        $('#submit-button').on('click', testPalindrome);
    };

    // Retrieves text from the 'test string' text box on the page, and requests a palindrome test for the text
    var testPalindrome = function(){
        var text;

        text = $('#test-string').val();

        $.ajax({
            type: 'POST',
            url: context + '/api/palindrome.json',
            contentType: 'application/json; charset=UTF-8',

            // The Play framework expects JSON keys to be strings, including wrapped double quotes.  However, the
            // jQuery ajax method does not wrap the keys in its data requests, so it must be done manually.
            data: '{"testString":"' + text.replace('"', '\\"') + '"}',

            success: handlePalindromeResponse
        });
    };

    // Takes an palindrome test response object and outputs a summary to the list of results.
    var handlePalindromeResponse = function(response){
        var resultItem = $('<li>');
        resultItem.html('<strong>' + response.testString + '</strong> is ' + (response.isPalindrome === true ? '' : 'not ') + 'a palindrome.');
        resultItem.hide();
        resultItem.appendTo('#results-list');
        resultItem.fadeIn();
    };

    return {
        // Initializes the Palindrome module
        // context: the root URL of the application
        init: function(contextUrl){
            context = contextUrl;
            setupTestClickHandler();
        }
    };
})(jQuery);