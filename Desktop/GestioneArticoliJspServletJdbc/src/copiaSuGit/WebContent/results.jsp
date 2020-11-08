<%@page import="it.gestionearticoli.model.Articolo"%>
<%@page import="java.util.List"%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!doctype html>
<html lang="it">
<head>
	<jsp:include page="./header.jsp" />
	<title>Pagina dei risultati</title>
	
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
		<div class="alert alert-danger alert-dismissible fade show d-none" role="alert">
		  Esempio di operazione fallita!
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
		    <span aria-hidden="true">&times;</span>
		  </button>
		</div>
		<div class="alert alert-warning alert-dismissible fade show ${alertMessage==null?'d-none': ''}" role="alert">
		  ${alertMessage}
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
		    <span aria-hidden="true">&times;</span>
		  </button>
		</div>
		
		<div class='card'>
		    <div class='card-header'>
		        <h5>Lista dei risultati</h5> 
		    </div>
		    <div class='card-body'>
		    	<a class="btn btn-primary " href="PrepareInsertArticoloServlet">Add New</a>
		    
		        <div class='table-responsive'>
		            <table class='table table-striped ' >
		                <thead>
		                    <tr>
		                        <th>Id</th>
		                        <th>Codice</th>
		                        <th>Descrizione</th>
		                        <th>Prezzo</th>
		                        <th>Categoria</th>
		                        <th>Azioni</th>
		                    </tr>
		                </thead>
		                <tbody>
		                  	<c:forEach items="${requestScope.listaArticoliAttribute}" var="item">
		                	  <tr >
		                        <td><c:out value="${item.id}"></c:out></td>
		                        <td><c:out value="${item.codice}"></c:out></td>
		                        <td><c:out value="${item.descrizione}"></c:out></td>
		                        <td><c:out value="${item.prezzo}"></c:out></td>
		                        <td><c:out value="${item.categoriaFK}"></c:out></td>
		                        <td>
									<a class="btn  btn-sm btn-outline-secondary" href="PrepareGetArticoloDaLista">Visualizza articolo</a>
									<a class="btn  btn-sm btn-outline-primary ml-2 mr-2" href="PrepareUpdateArticoloDaLista">Edit</a>
									<a class="btn btn-outline-danger btn-sm" href="PrepareDeleteArticoloServlet">Delete</a>
									<a class="btn  btn-sm btn-outline-info" href="GetCategoriaDaLista?idCategoria=${item.categoriaFK}">Visualizza categoria</a>
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