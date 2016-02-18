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
              <h2 id="tables">Pronostiquer</h2>
            </div>
            
            
            <c:if test="${ not empty erreurs['score'] }">
				<jsp:include page="/views/templates/erreur.jsp">
					<jsp:param name="erreur" value="${erreurs['score'] }"/>
				</jsp:include>
			</c:if>

            <div class="bs-component">
              <form class="form-horizontal" action="pronostiquer" method="post">
				  <fieldset>
				    <legend>${ match.equipe[0].pays } - ${ match.equipe[1].pays } (Poule ${ match.equipe[0].poule }) le ${ match.date }</legend>
				    <br>
				    <div class="form-group">
				      <label for="score1" class="col-lg-2 control-label"><img src="public${ match.equipe[0].drapeau.src }"/>  ${ match.equipe[0].pays }</label>
				      <div class="col-lg-1">
				        <input class="form-control" id="score1" name="score1" required type="text" value="${ input.score1 }" maxlength="2" autocomplete="off">
				        <input type="hidden" name="e1" value="${ match.equipe[0].id }">
				      </div>
				      <div class="col-lg-1">
				        <input class="form-control" id="score2" name="score2" required type="text" value="${ input.score2 }" maxlength="2" autocomplete="off" >
				        <input type="hidden" name="e2" value="${ match.equipe[1].id }">
				      </div>
				      <label for="score2" class="col-lg-2 control-label"><img src="public${ match.equipe[1].drapeau.src }"/>  ${ match.equipe[1].pays }</label>
				      <button type="submit" class="btn btn-sm btn-primary col-sm-2 col-sm-offset-1">Pronostiquer</button>
				    </div>
				  </fieldset>
			</form>
          </div>
        </div>
      </div>
	</div>
	</div>
</body>
<c:import url="/views/templates/footer.jsp"></c:import>
</html>