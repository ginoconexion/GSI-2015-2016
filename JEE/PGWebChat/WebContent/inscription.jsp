<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<c:import url="/views/templates/header.jsp"></c:import>
<title>Messenger - Inscription</title>
</head>
<body>
	<br />
	<div class="row">
		<div class="col-sm-offset-3 col-sm-7">
			<div class="well bs-component">
				<form class="form-horizontal" action="inscription" method="post">
					<fieldset>
						<legend>Inscription</legend>
						<div class="row">
							<div class="col-sm-12">
								<c:choose>
									<c:when test="${ form.resultat }">
										<div class="alert alert-success" role="alert">
											<span class="glyphicon glyphicon-exclamation-sign"
												aria-hidden="true"></span> Inscription réussie
										</div>
									</c:when>

									<c:when test="${ not form.resultat }">
										<div class="alert alert-danger" role="alert">
											<span class="glyphicon glyphicon-exclamation-sign"
												aria-hidden="true"></span> Echec de l'inscription
										</div>
									</c:when>
								</c:choose>
							</div>
						</div>


						<div class="form-group">
							<label class="col-sm-3 control-label" for="pseudo">Pseudo</label>
							<div class="col-sm-4">
								<input type="text" placeholder="Pseudo" id="pseudo"
									class="form-control" name="pseudo"
									value="${ utilisateur.login }">
							</div>
							<c:if test="${ not empty form.erreurs['pseudo'] }">
								<div class="col-sm-5 alert alert-danger">
									<span class="glyphicon glyphicon-exclamation-sign"
										aria-hidden="true"></span> ${ form.erreurs['pseudo'] }
								</div>
							</c:if>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label" for="mdp1">Mot de
								passe :</label>
							<div class="col-sm-4">
								<input type="password" placeholder="Password" id="mdp1"
									class="form-control" name="mdp1" value="${ utilisateur.pwd }">
							</div>
							<c:if test="${ not empty form.erreurs['mdp1'] }">
								<div class="col-sm-5 alert alert-danger">
									<span class="glyphicon glyphicon-exclamation-sign"
										aria-hidden="true"></span> ${ form.erreurs['mdp1'] }
								</div>
							</c:if>
						</div>

						<div class="form-group">
							<label class="col-sm-3 control-label" for="mdp2">Confirmez</label>
							<div class="col-sm-4">
								<input type="password" placeholder="Password" id="mdp2"
									class="form-control" name="mdp2">
							</div>
						</div>

						<div class="form-group">
							<div class="col-sm-4 col-sm-offset-4">
								<button class="btn btn-default" type="reset">Annuler</button>
								<button class="btn btn-primary" type="submit">Envoyer</button>
							</div>
						</div>
					</fieldset>
				</form>
				<div class="btn btn-primary btn-xs" id="source-button"
					style="display: none;">&lt; &gt;</div>
			</div>
		</div>
	</div>
</body>
</html>