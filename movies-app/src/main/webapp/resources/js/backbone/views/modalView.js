var ModalView = Backbone.View.extend({
	model : Movie,
	events : {
		"click button": "alertStatus"
	},
	alertStatus : function(e){
		var movie = this.model;
		$('.modal-body').html(new MovieDetails({model:movie}).render());
		$('#editModal').modal('show');
	},
	render : function(){
		var template = _.template($('#movie_details').html(), this.model.toJSON());
		return this.$el.html(template);
	},
});