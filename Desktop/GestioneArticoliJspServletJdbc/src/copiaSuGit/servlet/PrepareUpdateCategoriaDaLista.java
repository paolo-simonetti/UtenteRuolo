package it.gestionearticoli.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.gestionearticoli.model.Categoria;
import it.gestionearticoli.service.MyServiceFactory;

@WebServlet("/PrepareUpdateCategoriaDaLista")
public class PrepareUpdateCategoriaDaLista extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PrepareUpdateCategoriaDaLista() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Categoria categoriaDaAggiornare=MyServiceFactory.getCategoriaServiceInstance().trovaTramiteId
					(Long.parseLong(request.getParameter("idCategoria")));
			request.setAttribute("categoriaDaAggiornare",categoriaDaAggiornare);
			request.getRequestDispatcher("updateCategoria.jsp");
		} catch(Exception e) {
			System.err.println("Errore nel reperire la categoria!");
			e.printStackTrace();
		} 
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
