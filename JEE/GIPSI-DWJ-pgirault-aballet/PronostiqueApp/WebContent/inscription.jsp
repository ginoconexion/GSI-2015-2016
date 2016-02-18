<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<c:import url="/views/templates/header.jsp"></c:import>
<title>PronosticApp - Inscription</title>
</head>
<body>
	<br />
		<div class="container col-sm-3 col-sm-offset-4">
		<form class="form-horizontal" action="inscription" method="post">
		  <fieldset>
		    <legend>Inscription</legend>
		    
		    <c:if test="${ not empty erreurs['login'] }">
				<jsp:include page="/views/templates/erreur.jsp">
					<jsp:param name="erreur" value="${erreurs['login'] }"/>
				</jsp:include>
			</c:if>
		    
		    <div class="form-group">
		      <label for="select" class="col-lg-2 control-label">Login</label>
		      <div class="col-lg-8">
		        <jsp:include page="/views/templates/input.jsp">
					<jsp:param name="name" value="login"/>
					<jsp:param name="value" value="${ input.idJoueur }"/>
				</jsp:include>
		      </div>
		      
		    </div>
		    <div class="form-group">
		      <div class="col-lg-10 col-lg-offset-2">
		        <button type="reset" class="btn btn-default">Annuler</button>
		        <button type="submit" class="btn btn-primary">S'inscrire</button>
		      </div>
		    </div>
		  </fieldset>
	</form>
	</div>
</body>
</html>