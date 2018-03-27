var url="http://localhost:8080/movies-app/rest/movies";

$(document).ready(function(){
	$('#productsDataTable').DataTable();
	findAll();

	addProductsGrid();

	var table = $('#productsDataTable').DataTable();

	$('#productsDataTable tbody').on( 'click', 'button', function () {
		var data = table.row( $(this).parents('tr') ).data();
		findById(data[0]);
	} );


});


var findAll=function(){
	$.ajax({
		type:'GET',
		url: url,
		dataType:"json",
		success:renderList
	});
};

var renderList=function(data){
	var t = $("#productsDataTable").DataTable();

	$.each(data, function(index,movies){
		t.row.add( [
			movies.id,
			movies.name,
			movies.year,
			movies.rank,
			movies.picture,
			'<td><button id="update" type="button" data-toggle="modal"' + 
			'data-target="#edit" data-uid="1"' + 
			'class="update btn btn-warning btn-sm" id="userTableButton">' + 
			'<i class="fa fa-pencil"></i>' + 
			'</button></td>'
			] ).draw(false);
	});


}

function fun(obj) {
    var id = obj.dataset.uid;
    console.log(id, two, three);
    moreDetails(id);
}


function reply_click()
{
	var myForm = document.getElementById('moreDetails');
	alert(myForm.ip.value);
}

var addProductsGrid=function(){
	$.ajax({
		type:'GET',
		url: url,
		dataType:"json",
		success:addProductsToCard
	});
};

var addProductsToCard=function(data){

	var div = document.getElementById('myDiv');


	$.each(data, function(index,movies){

		div.innerHTML += '<div class="col-sm-6 col-md-4 col-lg-3">'
			+ '<div class="card bg-light text-dark" id="myCard">' 
			+ '<img src="resources/images/'+movies.picture+'" height="200">'
			+ '<p>Name: ' + movies.name+ '</p>'
			+ '<p>Year: ' + movies.year+ '</p>'
			+ '<p>Rank: ' + movies.rank+ '</p>'
			+ '</div>' + '<br />'+ '</div>';

	});
}

var currentMovies;

var findById=function(id) {
	console.log('findById' + id);
	$.ajax({
		type:'GET',
		url: url + '/' + id,
		dataType:"json",
		success:function(data) {
			$('#btnDelete').show();
			console.log('findById success: ' + data.id);
			currentMovies = data;
			renderDetails(currentMovies);
		}
	});
};


var renderDetails = function(movies) {
	 $('#add').hide();
		$('#buttonUpdate').show();

	document.getElementById("idUpdate").value = movies.id;
	document.getElementById("nameUpdate").value = movies.name;
	document.getElementById("yearUpdate").value = movies.year;
	document.getElementById("rankUpdate").value = movies.rank;
	document.getElementById("pictureUpdate").value = movies.picture;
	document.getElementById("add").value = "Save";
	$('#buttonDelete').show();

}


function newMovieButtonClick() {
	$('#buttonDelete').hide();
	$('#buttonUpdate').hide();
	$('#add').show();	
}

function functionAdd() {
	$.ajax({
		type: 'POST',
		contentType: 'application/json',
		url: url,
		dataType: "json",
		data: formToJSON(),
		success: function(data, textStatus, jqXHR){
			$('#edit').modal('hide');
			var table = $('#productsDataTable').DataTable();
			table
			.clear()
			.draw();
			findAll();
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('Add Movie error: ' + textStatus);
		}
	});
};

var formToJSON=function () {
	var movieId = $('#idUpdate').val();
	return JSON.stringify({
		"id": movieId == "" ? null : movieId, 
		"name": $('#nameUpdate').val(), 
		"year": $('#yearUpdate').val(),
		"rank": $('#rankUpdate').val(),
		"picture": $('#pictureUpdate').val()
		});
};

function functionUpdate(){	
	var idUpdate = document.getElementById("idUpdate").value;
	$.ajax({
		type:'PUT',
		contentType: 'application/json',
		url: url + '/' + idUpdate,
		dataType: "json",
		data: formToJSON(),
		success: function(data, textStatus, jqXHR){
			$('#edit').modal('hide');
			var table = $('#productsDataTable').DataTable();
			
			table
			.clear()
			.draw();
			findAll();
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('Update Movie error: ' + textStatus);
		}
	});
};

function functionDelete(){
	var idDelete = document.getElementById("idUpdate").value;
	alert(idDelete);
	$.ajax({
		type:'DELETE',
		url: url + '/' + idDelete,
		dataType:"json",
		contentType: "application/json",
		success : function(data){                                  
			$('#edit').modal('hide');
			var table = $('#productsDataTable').DataTable();

			table
			.clear()
			.draw();
			findAll();
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('Delete Movie error: ' + textStatus);
		}
	});
}
 
function functionTest(){
	var div = document.getElementById('myDiv');

	div.innerHTML += '<div class="col-sm-6 col-md-4 col-lg-3">'
		+ '<div class="card" style="width: 20rem;">' + '<img class="card-img-top" src="..." alt="Card image cap">'
		+ '<div class="card-block">' + '<h4 class="card-title">Card title</h4>'
		+ '<p class="card-text">Some quick example text to build on the card title and make up the bulk of the card content.</p>'
		+ '</div>' + '</div>' + '</div>';
}
