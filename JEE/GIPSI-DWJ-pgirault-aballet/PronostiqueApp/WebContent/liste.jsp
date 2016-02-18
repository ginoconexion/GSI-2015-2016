<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<c:import url="/views/templates/header.jsp"></c:import>
<title>PronosticApp - Matchs</title>


</head>
<body>
	<c:import url="/views/templates/navbar.jsp"></c:import>
	<br />
	<br />
	<br />
	
	<div class="container col-sm-8 col-sm-offset-1">
		<div class="bs-docs-section">
	        <div class="row">
	          <div class="col-lg-12">
	            <div class="page-header">
	              <h1 id="tables">Matchs</h1>
	            </div>
	            
	            <div class="col-lg-12">
	            <c:if test="${ not empty erreurs['addResult'] }">
					<jsp:include page="/views/templates/erreur.jsp">
						<jsp:param name="erreur" value="${erreurs['addResult'] }"/>
					</jsp:include>
				</c:if>
	            </div>
	
	            <div class="bs-component">
	              <table id="liste_matchs" class="table table-striped table-hover ">
	              	<thead>
	              		<tr>
	              			<th>Date</th>
	              			<th>Equipe 1</th>
	              			<th>Equipe 2</th>
	              			<th>Poule</th>
	              			<th>Pronostiquer</th>
	              			<c:if test="${ isAdmin }">
	              			<th>Ajouter Résultat</th>
	              			</c:if>
	              		</tr>
	              	</thead>
	                <tbody>
	                	<c:forEach var="match" items="${ matchs }">
	                		<tr>
	                			<td>${ match.date }</td>
	                			<td><img src="public${ match.equipe[0].drapeau.src }"/>  ${ match.equipe[0].pays }</td>
	                			<td><img src="public${ match.equipe[1].drapeau.src }"/>  ${ match.equipe[1].pays }</td>
	                			<td>${ match.equipe[0].poule }</td>
	                			<td><a href="pronostiquer?e1=${match.equipe[0].id}&e2=${match.equipe[1].id}">Pronostiquer</a></td>
	                			<c:if test="${ isAdmin }">
	                			<td>
	                				<form action="matchs" method="post"> 
	                					<input type="hidden" name="e1" value="${ match.equipe[0].id }">
	                					<input type="hidden" name="e2" value="${ match.equipe[1].id }">
	                					<button type="submit" name="addResult" class="btn btn-sm btn-primary">Ajouter Résultat</button>
	                				</form>
	                			</td>
	                			</c:if>
	                		</tr>
						</c:forEach>
	                </tbody>
	              </table> 
	          </div>
	        </div>
	      </div>
		</div>
	</div>
	<div class="container col-sm-3">
		<br>
		<br>
		<br>
		<div class="bs-docs-section">
	        <div class="row">
	          <div class="col-lg-12">
	            <div class="page-header">
	              <h3>Classement</h3>
	            </div>
	           </div>
	           <div class="bs-component">
	              <table id="classement" class="table table-striped table-hover ">
	              	<thead>
	              		<tr>
	              			<th>Login</th>
	              			<th>Points</th>
	              		</tr>
	              	</thead>
	                <tbody>
	                	<c:forEach var="joueur" items="${ classement }">
	                		<tr>
	                			<td>${ joueur.key }</td>
	                			<td>${ joueur.value }</td>
	                		</tr>
						</c:forEach>
	                </tbody>
	              </table> 
	          </div>
	          
	         </div>
	      </div>
	</div>
</body>

<c:import url="/views/templates/footer.jsp"></c:import>

<script type="text/javascript" src="https://cdn.datatables.net/r/bs-3.3.5/jqc-1.11.3,dt-1.10.8/datatables.min.js"></script>

<script type="text/javascript">
    $(document).ready(function(){
	    $('#liste_matchs').DataTable();
	});
</script>
</html>