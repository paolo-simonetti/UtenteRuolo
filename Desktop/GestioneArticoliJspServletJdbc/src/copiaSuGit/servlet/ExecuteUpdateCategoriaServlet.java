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

@WebServlet("/ExecuteUpdateCategoriaServlet")
public class ExecuteUpdateCategoriaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ExecuteUpdateCategoriaServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idCategoriaInputParam=request.getParameter("idCategoria");
		String nomeCategoriaInputParam=request.getParameter("nomeCategoria");
		Long idCategoria=Long.parseLong(idCategoriaInputParam);
		//controllo se la categoria inserita � tra quelle presenti (e quindi non � aggiornabile)
	try {
		TreeSet<Categoria> categoriePresenti=MyServiceFactory.getCategoriaServiceInstance().listAll();
		boolean categoriaGiaPresente=false;
		for (Categoria c:categoriePresenti) {
			if (nomeCategoriaInputParam.equals(c.getNomeCategoria())) {
				categoriaGiaPresente=true;
				break;
			}
		}
		if (categoriaGiaPresente) {
			request.setAttribute("errorMessage","La categoria inserita � gi� presente nella lista!");
			request.setAttribute("categoriaDaAggiornare",MyServiceFactory.getCategoriaServiceInstance().trovaTramiteId(idCategoria));
			request.getRequestDispatcher("updateCategoria.jsp").forward(request,response);
		} else {
			Categoria categoriaDaInserire=MyServiceFactory.getCategoriaServiceInstance().trovaTramiteId(idCategoria);
			categoriaDaInserire.setNomeCategoria(nomeCategoriaInputParam);
			MyServiceFactory.getCategoriaServiceInstance().aggiorna(categoriaDaInserire);
			request.setAttribute("listaCategorieAttribute",MyServiceFactory.getCategoriaServiceInstance().listAll());
			request.getRequestDispatcher("elencoCategorie.jsp").forward(request,response); 				
		}
	} catch (Exception e) {
		System.err.println("Errore nell'aggiornamento della categoria!");
		e.printStackTrace();
	}

	}

}
