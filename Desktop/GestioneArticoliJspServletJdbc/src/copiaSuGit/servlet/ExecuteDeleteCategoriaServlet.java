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

@WebServlet("/ExecuteDeleteCategoriaServlet")
public class ExecuteDeleteCategoriaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ExecuteDeleteCategoriaServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idCategoriaInputParam=request.getParameter("idCategoriaDaEliminare");
		Long idCategoria=Long.parseLong(idCategoriaInputParam);
		try {
			Categoria categoriaDaEliminare=MyServiceFactory.getCategoriaServiceInstance().trovaTramiteId(idCategoria);
			MyServiceFactory.getCategoriaServiceInstance().rimuovi(categoriaDaEliminare);
			TreeSet<Categoria> elencoCategorie=MyServiceFactory.getCategoriaServiceInstance().listAll();
			request.setAttribute("listaCategorieAttribute",elencoCategorie);
			request.setAttribute("successMessage","La categoria è stata eliminata correttamente!");
			request.getRequestDispatcher("elencoCategorie.jsp").forward(request,response);
		} catch(Exception e) {
			e.printStackTrace();
		}		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
