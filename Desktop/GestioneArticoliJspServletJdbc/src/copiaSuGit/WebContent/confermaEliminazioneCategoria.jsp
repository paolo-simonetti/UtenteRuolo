<%@page import="it.gestionearticoli.model.Categoria"%>
<%@page import="it.gestionearticoli.service.MyServiceFactory"%>

<!doctype html>
<html lang="it">
  <head>
    
    <jsp:include page="./header.jsp" />
    
    <!-- Custom styles for this template -->
    <link href="./assets/css/global.css" rel="stylesheet">
    <style type="text/css">
    	body {
		  padding-top: 3.5rem;
		}	
    </style>
    
    <title>Conferma eliminazione categoria</title>
  </head>
  <body>
  
	<jsp:include page="./navbar.jsp"></jsp:include>
  
  
	<main role="main">

	  <!-- Main jumbotron for a primary marketing message or call to action -->
	  <div class="jumbotron" >
	    <div class="container">
	      <h1 class="display-3">Conferma eliminazione categoria</h1>
	      <p>Nooooooo pazzo coglione vuoi eliminare il mio categorino?</p>
	      <p><a class="btn btn-primary btn-lg" href="ExecuteDeleteCategoriaServlet?idCategoriaDaEliminare=${requestScope.idCategoriaDaEliminare}" role="button">yes_chad &raquo;</a></p>
	      <p><a class="btn btn-primary btn-lg" href="ListCategorieServlet" role="button">Baby, it's 4pm, time to tornare alla lista degli articoli &raquo;</a></p>
	    </div>
	  </div>


	</main>
	
	<jsp:include page="./footer.jsp" />
  </body>
</html>