var ModalView = Backbone.View.extend({
	collection : MovieList,
	id : "container",
	initialize : function(){
		this.listenTo(this.collection, 'add', this.renderList);
		this.render();
	},
	render : function(){
		this.collection.each(function(movie){
			$('.modal-body').append(new MovieDetails({model : movie}).render());
		}, this);
	},
	renderList : function(){
		$('.modal-body div').remove();
		this.collection.each(function(movie){
			$('.modal-body').append(new MovieDetails({model : movie}).render());
		}, this);
		$('.modal-body').append(new MovieDetails({model:movie}).render());
		$('#editModal').modal('show');
	}
});