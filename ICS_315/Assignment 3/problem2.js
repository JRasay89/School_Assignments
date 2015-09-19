//Run function when document is ready
$().ready(function() {
  // read urls from text file
  $.get('urls.txt', function(data) {
    // each line is a url
    var urls = data.trim().split(/[\n\r]/);
    // display urls in textarea on the page
    $('#urls').val(JSON.stringify(urls, null, '  '));
    // when compute button is clicked analyze all urls with all regular expressions
    $('#compute').click(function() {
      // finds all urls that match 'regex'
      function find(regex) {
        // initialize matches
        var matches = {};
        for (var i = 0; i < urls.length; i++) {  // all urls
          var url = urls[i];
          // try to match
          var parts = regex.exec(url);
          if (parts) {  // there is a match
            // match is the 1st capturing group
            var match = parts[1];
            // we need to make an array when it's the first occurence of 'match'
            if (!matches[match]) {matches[match] = [];}
            // add url to the set of urls where this 'match' was found
            matches[match].push(url);
          }
        }
        // make an array whode items are all matched strings
        var items = [];
        for (match in matches) {
          items.push(match);
        }
        return {items: items, matches: matches};
      };
      // define regular expressions
      var searches = [
        {
          name: 'protocol',
          pattern: /^((ht|f)tps?)\:\/\/?/i     // to be filled in
        }, {
          name: 'extension',
          pattern: /\.([0-9a-z]+)(?:[\?#]|$)/i     // to be filled in
        }, {
          name: 'domain',
          pattern: /^(?:https?|ftp)\:\/\/(?:www\.)?([^\/?#]+)(?:[\/?#]|$)/i     // to be filled in
        }, {
          name: 'query',
          pattern: /(([\\?]([^=]+)\=([^&#]*))([\\&]([^=]+)\=([^&#]*))*)/i    // to be filled in
        }
      ];
      // perform all searches
      for (var i = 0; i < searches.length; i++) {
        var search = searches[i];
        // find all results for a pattern
        var found = find(search.pattern);
        // display the result of the search
		var p = $('<h1 />');
		var p2 = $('<p />');
		p.text(search.name);
		$("#results").append(p);
        p2.text(found.items);
		$("#results").append(p2);
        // ....
      }
    });
  });
});
