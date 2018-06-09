var MainView = Backbone.View.extend({
	collection : MovieList,
	id : "container",
	initialize : function(){
		this.listenTo(this.collection, 'add', this.renderList);
		this.render();
	},
	render : function(){
		this.collection.each(function(movie){
			$('#tableBody').append(new MovieView({model : movie}).render());
			$('#productItem').append(new MovieCard({model : movie}).render());
		}, this);
	},
	renderList : function(){
		$('#tableBody tr').remove();
		$('#productItem item').remove();
		this.collection.each(function(movie){
			$('#tableBody').append(new MovieView({model : movie}).render());
			$('#productItem').append(new MovieCard({model : movie}).render());
		}, this);
	}
});