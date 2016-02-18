<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<c:import url="/views/templates/header.jsp"></c:import>

<title>ChatWeb</title>
</head>
<body>
	<div class="col-sm-offset-3">
		<h1>Mines Albi : Messenger</h1>
		<div class="row">
			<div class="col-sm-8">
				<div class="well bs-component">
					<form class="form-horizontal" action="messages" method="post">
						<fieldset>
							<legend>Bienvenue ${ utilisateur.login }</legend>

							<div class="col-sm-offset-1">
								<c:forEach items="${ messages }" var="message">
									<p>${ message.source.login }:${ message.text }</p>
								</c:forEach>
							</div>

							<div class="form-group">
								<div class="col-sm-8">
									<input type="text" placeholder="Texte" id="inputText" class="form-control" name="message">
								</div>
								<div class="col-sm-2">
									<button class="btn btn-primary" type="submit" name="envoyer">Envoyer</button>
								</div>
							</div>
						</fieldset>
					</form>
					<div class="btn btn-primary btn-xs" id="source-button"
						style="display: none;">&lt; &gt;</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>