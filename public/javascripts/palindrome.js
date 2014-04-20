var PalindromeInit = function(jQuery, context){
    return (function($, context){
        var setupTestClickHandler = function(){
            $('#submit-button').on('click', testPalindrome);
        };

        var testPalindrome = function(){
            var text;

            text = $('#test-string').val();

            $.ajax({
                url: context + '/api/palindrome.json',
                data: '{"testString":"' + text.replace('"', '\\"') + '"}',
                contentType: 'application/json; charset=UTF-8',
                type: 'POST',
                success: handlePalindromeResponse
            });
        };

        var handlePalindromeResponse = function(response){
            $('#results-list').append($('<li>' + response.testString + ' is ' + (response.isPalindrome === true ? '' : 'not ') + 'a palindrome.</li>'));
        };

        return {
            init: function(context){
                setupTestClickHandler();
            }
        };
    })(jQuery, context);
};