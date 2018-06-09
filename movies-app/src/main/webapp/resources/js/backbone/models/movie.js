var rootURL = "http://localhost:8080/movies-app/rest/movies";

var Movie = Backbone.Model.extend({
	urlRoot : rootURL,
	defaults: {
		"id" : null,
		"name"  : "",
		"year" : null,
		"rank" : null,
		"picture" : ""	},
	initialize: function(){
	}
});

var MovieList = Backbone.Collection.extend({
	model : Movie,
	url : rootURL
});