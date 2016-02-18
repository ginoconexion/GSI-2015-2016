<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="fr.emac.gipsi.gsi.model.Ascenseur" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>


<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<c:import url="/views/templates/header.jsp"></c:import>
<title>Ascenseur</title>
</head>
<body>

<div class="bs-docs-section">
        <div class="row">
          <div class="col-lg-12">
            <div class="page-header">
              <h1 id="tables">Ascenseurs</h1>
            </div>
			
            <div class="bs-component">
              <table class="table table-striped table-hover ">
                <thead>
                  <tr>
                    <th>Etage</th>
					<c:forEach var="i" begin="1" end="${ manager.nbAscenseurs }">
					   <th>Ascenseur <c:out value="${i}"/></th>
					</c:forEach>
                  </tr>
                </thead>
                <tbody>
                	<c:forEach var="i" begin="1" end="7">
                		<tr>
                			<c:set var="etage" value="${7-i+1}"/>
                			<td>${ etage }</td>
                			<c:forEach var="ascenseur" items="${ manager.listeAscenseur }">
	                				<c:choose> 
										<c:when test="${ ascenseur.posY ==  etage }">
									   		<td>Ascenseur</td>
										</c:when>
									<c:otherwise>
										<td>-</td>
									</c:otherwise>
								</c:choose>
                			</c:forEach>
                		</tr>
                	</c:forEach>
                </tbody>
              </table> 
            <div class="btn btn-primary btn-xs" id="source-button" style="display: none;">&lt; &gt;</div></div><!-- /example -->
          </div>
        </div>
      </div>
      
      <div class="row">
          <div class="col-lg-6">
            <div class="well bs-component">
              <form class="form-horizontal" action="ascenseur" method="post">
                <fieldset>
                  <div class="form-group">
                    <label class="col-lg-2 control-label" for="select">Numéro étage</label>
                    <div class="col-lg-10">
                      <select id="select" class="form-control" name="numero">
                      <c:forEach var="i" begin="1" end="7">
                      	<option value="${i}">${i}</option>
                      </c:forEach>
                      </select>
                      <br>
                    </div>
                  </div>
                    <div class="form-group">
	                    <div class="col-lg-10 col-lg-offset-2">
	                      <button class="btn btn-primary" type="submit" name="envoyer">Submit</button>
	                    </div>
                  </div>
                </fieldset>
              </form>
            <div class="btn btn-primary btn-xs" id="source-button" style="display: none;">&lt; &gt;</div></div>
          </div>
        </div>
</body>
</html>