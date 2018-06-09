var MovieCard = Backbone.View.extend({
	model : Movie,
	className: "col-sm-6 col-md-4 col-lg-3",

	render : function(){
		var template = _.template($('#movieCard').html(), this.model.toJSON());
		return this.$el.html(template);
	},
});