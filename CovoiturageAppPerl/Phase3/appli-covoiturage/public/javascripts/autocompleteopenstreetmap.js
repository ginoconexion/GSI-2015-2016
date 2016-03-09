function setDatePicker(){
	$( "#datepicker" ).datepicker({
		dateFormat: "dd-mm-yy",
		regional: "fr",
		minDate: 0,
		maxDate: "+12M",
	}
	);
	$( "#datepicker" ).datepicker( $.datepicker.regional[ "fr" ] );
}

function interchangerVilles(){
	inputs_autocomplete = $("input.autocomplete");
	btn = $("#interchanger").click(function(){
		interchangerInput(inputs_autocomplete);
	});
	
	function interchangerInput(inputs){
		var value1 = $($(inputs).get(0)).val();
		var value2 = $($(inputs).get(1)).val();
		$($(inputs).get(0)).val(value2);
		$($(inputs).get(1)).val(value1);
	}
}


function getCityByCoordinates(input_latitude, input_longitude, input){
	 $.ajax({
		   url: "http://nominatim.openstreetmap.org/reverse?format=json&lat="+$(input_latitude).val()+"&lon="+$(input_longitude).val()+"&zoom=10&addressdetails=1",
		   success: function(response){
			 console.log("Liste d'objet %o", response);
			 $(input).val(response.display_name);
			 console.log(response.display_name);
		   }
		});
}

function autocomplete(){		
		$(".autocomplete").blur(function(e){
			var liste = $(this).parent().parent().find("ul.liste");
			console.log(e);
			$(liste).hide(500);
		});
		$(".autocomplete").focus(function(){
			var liste = $(this).parent().parent().find("ul.liste");
			$(liste).show();
		});
		
		$(".autocomplete").keyup(function(){
			var keyword = $(this).val();
			console.log($(this).parent());
			var liste = $(this).parent().parent().find("ul.liste")
			var input = $(this);
			
			console.log(liste);
			
			if (keyword.length >= 3){
				$(liste).show();
				getPlace(keyword, liste, input);
			}
		});
}

function autocomplete1(input){
	$(input).blur(function(e){
		var liste = $(this).parent().parent().find("ul.liste");
		console.log(e);
		$(liste).hide(500);
	});
	$(input).focus(function(){
		var liste = $(this).parent().parent().find("ul.liste");
		$(liste).show();
	});
	
	$(input).keyup(function(){
		var keyword = $(this).val();
		console.log($(this).parent());
		var liste = $(this).parent().parent().find("ul.liste")
		var input = $(this);
		console.log(liste);
		
		if (keyword.length >= 3){
			$(liste).show();
			getPlace(keyword, liste, input);
		}
	});
}

function getPlace(keyword, liste, input) {
	$.ajax({
		   url: "http://nominatim.openstreetmap.org/search?q=" + keyword + "&countrycodes=fr&format=json&polygon=0&addressdetails=1",
		   success: function(response){
			 
			 $(liste).empty();
			 console.log("Liste d'objet %o", response);
			 console.log(response[0]);
			 $.each(response, function(i, item){
				 if(item.type == "city" || item.type == "administrative"){
					$(liste).append("<li class='ui-menu-item' value='[{\"nom\": \""+item.address.city+"\"}]'><a class='ui-corner-all'> "+item.display_name+"</a></li>");
				 }
			 });
			 $(liste).find("li").click(function(){
				$(liste).hide();
				console.log("ok");
				
				var jsonObj = $.parseJSON($(this).attr('value'));
				$(input).val(jsonObj[0].nom);
			 });
		   }
		});
}


function getCities(){
	inputs_latitude = $("input.latitude");
	inputs_longitude =  $("input.longitude");
	console.log(inputs_latitude);
	console.log(inputs_longitude);
	
	 $.each(inputs_latitude, function(i_lat, input_latitude){
		 $.each(inputs_longitude, function(i_long, input_longitude){
			 if (i_lat == i_long && $(input_latitude).val() != '' && $(input_longitude).valueOf() != ''){
				 console.log(i_lat);
				 console.log(i_long);
				 input = $(input_latitude).parent().find("input[type=text]");
				 getCityByCoordinates(input_latitude, input_longitude, input);
			 }
		 });
	 });
}

function addEtape1(){
	
	// On récupère la balise <div> en question qui contient l'attribut « data-prototype » qui nous intéresse.
    var $container = $('div#etapes');
    console.log($container);
    var $addLink = $('<div class="col-sm-offset-2"><a href="#" id="add_etape" class="btn btn-default">Ajouter une étape</a></div><br>');
    $container.append($addLink);
    
 // On ajoute un nouveau champ à chaque clic sur le lien d'ajout.
    $addLink.click(function(e) {
      addCategory($container);
      e.preventDefault(); // évite qu'un # apparaisse dans l'URL
      return false;
    });
    
 // On définit un compteur unique pour nommer les champs qu'on va ajouter dynamiquement
    
    var index = $container.find(':input.autocomplete').length;
   
    var etapesValides = $("div#etapes_valides").find("div.form-group");
    console.log(etapesValides);
    $.each(etapesValides, function(i, etape){
    	console.log(etape);
    	addDeleteLink($(etape));
    });
    
    // On ajoute un premier champ automatiquement s'il n'en existe pas déjà un (cas d'une nouvelle annonce par exemple).
    if (index != 0) {
      // Pour chaque catégorie déjà existante, on ajoute un lien de suppression
      $container.children('div').each(function() {
        addDeleteLink($(this));
      });
      var index0 = $('div#etapes_valides').find(':input.autocomplete').length;
      index = index + index0;
    }
    // La fonction qui ajoute un formulaire Categorie

    function addCategory($container) {
      // Dans le contenu de l'attribut « data-prototype », on remplace :
      // - le texte "__name__label__" qu'il contient par le label du champ
      // - le texte "__name__" qu'il contient par le numéro du champ
      var $prototype = $($container.attr('data-prototype').replace(/__name__label_/g, 'Etape n°' + (index+1)).replace(/__index__/, index+1));
      // On ajoute au prototype un lien pour pouvoir supprimer la catégorie
      addDeleteLink($prototype);
      console.log($($prototype).find("input.autocomplete"));
      autocomplete1($($prototype).find("input.autocomplete"));
      // On ajoute le prototype modifié à la fin de la balise <div>
      $container.append($prototype);
      // Enfin, on incrémente le compteur pour que le prochain ajout se fasse avec un autre numéro
      index++;
    }
    
    // La fonction qui ajoute un lien de suppression d'une catégorie
    function addDeleteLink($prototype) {
      // Création du lien
      $deleteLink = $('<a href="#" class="btn btn-danger">Supprimer</a>');

      // Ajout du lien
      $prototype.append($deleteLink);

      // Ajout du listener sur le clic du lien
      $deleteLink.click(function(e) {
    	index -= 1;
        $prototype.remove();
        e.preventDefault(); // évite qu'un # apparaisse dans l'URL
        return false;
      });
    }
    
}