var MovieDetails = Backbone.View.extend({
	model : Movie,
	initialize: function(){
	},
	events : {
		"click #btnClear"  : "clearForm",
		"click #btnEdit" : "editMovie",
		"click #btnDelete" : "deleteMovie",
		"click #btnAdd" : "addMovie",
	},
	
	clearForm : function(e){
		$('#btnDelete').hide();
		$('#id').val(""),
		$('#name').val(""),
		$('#year').val(""),
		$('#rank').val(""),
		$('#picture').val("");
	},	
	editMovie : function(e){
		var movieDetails = {
				name : $('#name').val(),
				year : $('#year').val(),
				rank : $('#rank').val(),
				picture : $('#picture').val()};
		if(($('#id').val()) == ''){
			this.model = new Movie(movieDetails);
			movieList.add(this.model);
			this.model.save({
				name : $('#name').val(),
				year : $('#year').val(),
				rank : $('#rank').val(),
				picture : $('#picture').val()
			}, {
				success : function(movie){
					$('#id').val(movie.id);
					mainView.renderList();
				}
			});
		}
		else{
			this.model.save({
				id : parseInt($('#id').val()),
				name : $('#name').val(),
				year : $('#year').val(),
				rank : $('#rank').val(),
				picture : $('#picture').val()
			} , {
				success:function(wine){
					  mainView.renderList();
				  }
			});
		}
		return false;
	},
	
	deleteMovie : function(e){
		var movieId = parseInt($('#id').val());
		this.model.set({id : movieId});
		this.model.destroy({
			success:function(data){
				  mainView.renderList();
			  }
		});
		return false;
	},
	
	addMovie : function(e){
		var movieDetails = {
				name : $('#name').val(),
				year : $('#year').val(),
				rank : $('#rank').val(),
				picture : $('#picture').val() };
			this.model = new Movie(movieDetails);
			movieList.add(this.model);
			this.model.save({
				name : $('#name').val(),
				year : $('#year').val(),
				rank : $('#rank').val(),
				picture : $('#picture').val()
			}, {
				success : function(movie){
					$('#id').val(movie.id);
					mainView.renderList();
				}
			});
		return false;
	},
	
	render : function(){
		var template = _.template($('#movie_details').html(), this.model.toJSON());
		return this.$el.html(template);
	}
});