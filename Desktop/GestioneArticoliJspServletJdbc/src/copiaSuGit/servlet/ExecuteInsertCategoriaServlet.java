package it.gestionearticoli.web.servlet;

import java.io.IOException;
import java.util.TreeSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.gestionearticoli.model.Categoria;
import it.gestionearticoli.service.MyServiceFactory;

@WebServlet("/ExecuteInsertCategoriaServlet")
public class ExecuteInsertCategoriaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ExecuteInsertCategoriaServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nomeCategoriaInput=request.getParameter("nomeCategoria");
		try {
			TreeSet<Categoria> categoriePresenti=MyServiceFactory.getCategoriaServiceInstance().listAll();
			boolean categoriaGiaPresente=false;
			for (Categoria c:categoriePresenti) {
				if (nomeCategoriaInput.equals(c.getNomeCategoria())) {
					categoriaGiaPresente=true;
					break;
				}
			}
			if (categoriaGiaPresente) {
				request.setAttribute("errorMessage","La categoria inserita è già presente nella lista!");
				request.getRequestDispatcher("insertCategoria.jsp").forward(request,response);
			} else {
				Categoria categoriaDaInserire=new Categoria(nomeCategoriaInput);
				MyServiceFactory.getCategoriaServiceInstance().inserisciNuovo(categoriaDaInserire);
				request.setAttribute("listaCategorieAttribute",MyServiceFactory.getCategoriaServiceInstance().listAll());
				request.getRequestDispatcher("elencoCategorie.jsp").forward(request,response); 				
			}
		} catch (Exception e) {
			System.err.println("Errore nell'inserimento della nuova categoria nel db!");
			e.printStackTrace();
		}
	}

}
