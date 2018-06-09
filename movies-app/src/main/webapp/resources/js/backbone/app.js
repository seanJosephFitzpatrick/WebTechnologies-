var mainView;
var modalView;
var movieList;

$(document).ready(function(){
	movieList = new MovieList();
	movieList.fetch({
		success: function(data){			
			mainView = new MainView({collection : movieList});
			mainView.$el.appendTo(document.body);
		}
	});
});

var openModal = function(id){
    movieList = new MovieList();
    var res = movieList.findWhere({id:1});
	movieList.fetch({
		success: function(data){
			modalView = new ModalView({collection : movieList});
			modalView.$el.appendTo(document.body);
		}
	});
};