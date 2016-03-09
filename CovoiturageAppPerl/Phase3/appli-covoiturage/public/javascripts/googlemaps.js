function initMap(waypts, villes, start, end) {
  var directionsDisplay = new google.maps.DirectionsRenderer;
  var directionsService = new google.maps.DirectionsService;
  var map = new google.maps.Map(document.getElementById('map'), {
    zoom: 7,
  });
  directionsDisplay.setMap(map);
  directionsDisplay.setPanel(document.getElementById('right-panel'));


  calculateAndDisplayRoute(directionsService, directionsDisplay, waypts, villes, start, end);
}

function calculateAndDisplayRoute(directionsService, directionsDisplay,waypts, villes, start, end) {
  
  directionsService.route({
    origin: start,
    destination: end,
    waypoints: waypts,
    travelMode: google.maps.TravelMode.DRIVING
  }, function(response, status) {
    if (status === google.maps.DirectionsStatus.OK) {
      directionsDisplay.setDirections(response);
      console.log(response.routes[0].legs);
      var legs = response.routes[0].legs;
      var duration1 = legs[0].duration[0];
      
      var informations = [];
      $.each(legs, function(k, v) {
  	  	var information = {ville: villes[k], distance: legs[k].distance.text, duration: legs[k].duration.text};
  	  	informations.push(information);
	});
	console.log(informations);
	$.each(informations, function(k,v){
		$("#itineraire").append("<p><strong>"+informations[k].ville + " ("+ informations[k].distance +") : </strong> + "+informations[k].duration+"</p>");
	});
	
	
    } else {
      window.alert('Directions request failed due to ' + status);
    }
  });
}