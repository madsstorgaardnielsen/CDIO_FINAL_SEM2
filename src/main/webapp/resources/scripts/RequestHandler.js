/*
Klasse der håndterer at sende requests til web-server og håndtering af respons
 */

var Agent = {};

Agent.GET = function (URL, success, error) { //generates GET request
    $.ajax({
        url: URL,
        method: 'GET',
        contentType: 'application/json',
        success: success,
        error: error
        }
    )
};

Agent.POST = function (URL, data, success, error) { //generates POSt request
  $.ajax({
      url: URL,
      method: 'POST',
      data: JSON.stringify(data),
      contentType: 'application/json',
      success: success,
      error: error
  })
};

Agent.PUT = function (URL, data, success, error) { //generates PUT request
    $.ajax({
        url: URL,
        method: 'PUT',
        data: JSON.stringify(data),
        contentType: 'application/json',
        success: success,
        error: error
    })
};

Agent.DELETE = function (URL, data, success, error) { //generates DELETE request
    $.ajax({
        url: URL,
        method: 'DELETE',
        data: JSON.stringify(data),
        contentType: 'application/json',
        success: success,
        error: error
    })
};