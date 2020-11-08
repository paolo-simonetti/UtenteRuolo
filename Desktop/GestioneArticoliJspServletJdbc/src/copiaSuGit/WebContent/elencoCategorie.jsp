<%@page import="it.gestionearticoli.model.Categoria"%>
<%@page import="java.util.TreeSet"%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!doctype html>
<html lang="it">
<head>
	<jsp:include page="./header.jsp" />
	<title>Elenco delle categorie presenti</title>
	
	<!-- style per le pagine diverse dalla index -->
    <link href="./assets/css/global.css" rel="stylesheet">
    
</head>
<body>
	<jsp:include page="./navbar.jsp" />
	
	<main role="main" class="container">
	
		<div class="alert alert-success alert-dismissible fade show ${successMessage==null?'d-none': ''}" role="alert">
		  ${successMessage}
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
		    <span aria-hidden="true">&times;</span>
		  </button>
		</div>
		
		<div class='card'>
		    <div class='card-header'>
		        <h5>Lista delle categorie presenti</h5> 
		    </div>
		    <div class='card-body'>
		    	<a class="btn btn-primary " href="PrepareInsertCategoriaServlet">Aggiungi una nuova categoria</a> 
		    
		        <div class='table-responsive'>
		            <table class='table table-striped ' >
		                <thead>
		                    <tr>
		                        <th>Id</th>
		                        <th>Nome della categoria</th>
		                        <th>Azioni</th>
		                    </tr>
		                </thead>
		                <tbody>
		                  	<c:forEach items="${requestScope.listaCategorieAttribute}" var="item">
		                	  <tr >
		                        <td><c:out value="${item.idCategoria}"></c:out></td>
		                        <td><c:out value="${item.nomeCategoria}"></c:out></td>
		                        <td>
									<a class="btn  btn-sm btn-outline-secondary" href="GetCategoriaDaLista?idCategoria=${item.idCategoria}">Visualizza articoli afferenti</a>
									<a class="btn  btn-sm btn-outline-primary ml-2 mr-2" href="PrepareUpdateCategoriaDaLista?idCategoria=${item.idCategoria}">Modifica</a>
									<a class="btn btn-outline-danger btn-sm" href="PrepareDeleteCategoriaServlet?idCategoria=${item.idCategoria}">Elimina</a>
								</td>
		                      </tr>
		                	</c:forEach>		                   
		                </tbody>
		            </table>
		        </div>
		   
			<!-- end card-body -->			   
		    </div>
		</div>	
	
	
	
	
	
	
	<!-- end container -->	
	</main>
	<jsp:include page="./footer.jsp" />
	
</body>
</html>