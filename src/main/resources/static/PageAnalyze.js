const submit_btn = $("#submit_btn");
const inputUrl = $("#url")
const outputEle = $("#output");
const errorEle = $("#error");
const analyzeUrl = "/analyze";

function getAnalyzeRequest()
{
    return {
        url: inputUrl.val()
    };
}

submit_btn.click(function() {
    if(inputUrl.val().trim() !=="")
    {
        const analyzeRequest = getAnalyzeRequest();
        fetch(analyzeUrl, {
            method: "POST",
            headers: 
            {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(analyzeRequest)
        })
        .then(function(response)
        {
            return response.json();
        })
        .then(function(result)
        {
            outputEle.html("");
            errorEle.html("");
            if(result.error != null)
            {
                errorEle.append(
                    "<p>Error: " +
                    result.error +
                    "</p>"
                );
            }
            else
            {
                outputEle.append(
                    "<p>HTTP Status: " + result.httpStatus + "</p>" +
                    "<p>Response Time: " + result.responseTime + " ms</p>" +
                    "<p>Title: " + result.title + "</p>" +
                    "<p>Meta Description: " + result.metaDescription + "</p>" +
                    "<p>H1 Count: " + result.h1Count + "</p>" +
                    "<p>Missing Alt Images: " + result.missingAltImages + "</p>" +
                    "<p>Approximate Word Count: " + result.wordCount + "</p>"
                );
            }
        })
        .catch(function(error)
        {
            errorEle.html("<p>Error: Unable to connect to the server.</p>");
        });
    }
});