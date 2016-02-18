<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<c:import url="/views/templates/header.jsp"></c:import>
<title>PronosticApp - Pronostiquer</title>
</head>
<body>
	<c:import url="/views/templates/navbar.jsp"></c:import>
	<br />
	<br />
	<br />
	
	<div class="container col-sm-8 col-sm-offset-2">
		<div class="bs-docs-section">

        <div class="row">
          <div class="col-lg-12">
            <div class="page-header">
              <h2 id="tables">Message</h2>
            </div>

            <div class="bs-component">
            	<h3>${message}</h3>
            	<a href="matchs">Retour à l'accueil</a>
          </div>
        </div>
      </div>
	</div>
	</div>
</body>
<c:import url="/views/templates/footer.jsp"></c:import>
</html>