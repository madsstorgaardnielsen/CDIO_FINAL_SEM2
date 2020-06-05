/*
Klasse der håndterer at sende requests til web-server og håndtering af respons
 */

var Agent = {};

Agent.GET = function (URL, success, error) {
    $.ajax({
        url: URL,
        method: 'GET',
        contentType: 'application/json',
        success: success,
        error: error
        }
    )
};

Agent.POST = function (URL, data, success, error) {
  $.ajax({
      url: URL,
      method: 'POST',
      data: JSON.stringify(data),
      contentType: 'application/json',
      success: success,
      error: error
  })
};

Agent.PUT = function (URL, data, success, error) {
    $.ajax({
        url: URL,
        method: 'PUT',
        data: JSON.stringify(data),
        contentType: 'application/json',
        success: success,
        error: error
    })
};

Agent.GETByID = function (URL, data, success, error) {
    $.ajax({
        url: URL,
        method: 'GET',
        data: JSON.stringify(data),
        contentType: 'application/json',
        success: success,
        error: error
    })
};

Agent.POSTByID = function (URL, data, success, error) {
    $.ajax({
        url: URL,
        method: 'POST',
        data: JSON.stringify(data),
        contentType: 'application/json',
        success: success,
        error: error
    })
};