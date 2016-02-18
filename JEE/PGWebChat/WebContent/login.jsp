<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<c:import url="/views/templates/header.jsp"></c:import>

<title>Login</title>

</head>
<body>

	<br />
	<br />
	<br />
	
	<div class="container col-sm-3 col-sm-offset-4" >

		<div class="row">
			<div class="col-sm-12">
				<c:if test="${ form }">
				<c:choose>
					<c:when test="${ form.resultat }">
						<div class="alert alert-success" role="alert">
							<span class="glyphicon glyphicon-exclamation-sign"
								aria-hidden="true"></span> Authentification réussie
						</div>
					</c:when>

					<c:when test="${ not form.resultat }">
						<div class="alert alert-danger" role="alert">
							<span class="glyphicon glyphicon-exclamation-sign"
								aria-hidden="true"></span> Echec de l'authentification
						</div>
					</c:when>
				</c:choose>
				</c:if>
			</div>
		</div>

		<form class="form-signin" method="post" action="login">
	      <h2 class="form-signin-heading">Connexion</h2>

			<div class="form-group">
				<label class="sr-only" for="utilisateur">Email address</label> 
				<input type="text" required="true" placeholder="Nom d'utilisateur" class="form-control" id="utilisateur" name="utilisateur" value="${ utilisateur.login }">
				<c:if test="${ not empty form.erreurs['pseudo'] }">
					<div class="col-sm-5 alert alert-danger">
						<span class="glyphicon glyphicon-exclamation-sign"
							aria-hidden="true"></span> ${ form.erreurs['pseudo'] }
					</div>
				</c:if>
			</div>



			<br />
		  <div class="form-group">
	      <label class="sr-only" for="password">Password</label>
	      <input type="password" required="true" placeholder="Password" class="form-control" id="password" name="password">
	      <c:if test="${ not empty form.erreurs['pseudo'] }">
			<div class="col-sm-5 alert alert-danger">
				<span class="glyphicon glyphicon-exclamation-sign"
					aria-hidden="true"></span> ${ form.erreurs['pseudo'] }
			</div>
		</c:if>
	      </div>
	      <div class="checkbox">
	        <label>
	          <input type="checkbox" value="remember-me"> Remember me
	        </label>
	      </div>
	      <button type="submit" class="btn btn-lg btn-primary btn-block">Sign in</button>
	    </form>
	    
	</div>
</body>
</html>