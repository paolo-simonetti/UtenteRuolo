<!-- navbar -->
<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-primary">

	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
       <span class="navbar-toggler-icon"></span>
     </button>

  <div class="collapse navbar-collapse" id="navbarsExampleDefault">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="menu.jsp">Home <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">Link</a>
      </li>
      <li class="nav-item">
        <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Disabled</a>
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="dropdown01" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Articoli</a>
        <div class="dropdown-menu" aria-labelledby="dropdown01">
          <a class="dropdown-item" href="ListArticoliServlet">Elenco articoli</a>
          <a class="dropdown-item" href="insert.jsp">Inserisci nuovo articolo</a>
          <a class="dropdown-item" href="updateDaDropdown.jsp">Aggiorna articolo</a>
          <a class="dropdown-item" href="deleteDaDropdown.jsp">Elimina articolo</a>
          <a class="dropdown-item" href="getDaDropdown.jsp">Visualizza articolo</a>
        </div>
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="dropdown02" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Categorie</a>
        <div class="dropdown-menu" aria-labelledby="dropdown02">
          <a class="dropdown-item" href="ListCategorieServlet">Elenco categorie</a> 
          <a class="dropdown-item" href="insertCategoria.jsp">Inserisci nuova categoria</a>
          <a class="dropdown-item" href="updateDaDropdownCategoria.jsp">Aggiorna categoria</a>
          <a class="dropdown-item" href="deleteDaDropdowncategoria.jsp">Elimina categoria</a>
          <a class="dropdown-item" href="getDaDropdownCategoria.jsp">Visualizza una categoria specifica</a>
        </div>
      </li>
      
    </ul>
    <form class="form-inline my-2 my-lg-0">
      <input class="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search">
      <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
    </form>
  </div>
</nav>
