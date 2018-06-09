var MovieView = Backbone.View.extend({
	model : Movie,
	tagName : 'tr',
	events : {
		"click button": "alertStatus"
	},
	alertStatus : function(e){
		var movie = this.model;
		$('#modalBody').html(new MovieDetails({model:movie}).render());
		$('#editModal').modal('show');
	},
	render : function(){
		var template = _.template($('#movie_list').html(), this.model.toJSON());
		return this.$el.html(template);
	},
});