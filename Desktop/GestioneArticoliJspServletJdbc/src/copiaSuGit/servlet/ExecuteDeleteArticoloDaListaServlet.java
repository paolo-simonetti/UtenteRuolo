package it.gestionearticoli.web.servlet;

import java.io.IOException;
import java.util.TreeSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.gestionearticoli.model.Articolo;
import it.gestionearticoli.service.MyServiceFactory;

/**
 * Servlet implementation class ExecuteDeleteArticoloDaListaServlet
 */
@WebServlet("/ExecuteDeleteArticoloDaListaServlet")
public class ExecuteDeleteArticoloDaListaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExecuteDeleteArticoloDaListaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		try {
			Articolo articolo=MyServiceFactory.getArticoloServiceInstance().trovaTramiteId(Long.parseLong(request.getParameter("idArticoloDaEliminare")));
			MyServiceFactory.getArticoloServiceInstance().rimuovi(articolo);
			TreeSet<Articolo> articoliPresenti=MyServiceFactory.getArticoloServiceInstance().listAll();
			request.setAttribute("listaArticoliAttribute",articoliPresenti);
			request.setAttribute("successMessage","Articolo eliminato con successo");
		} catch(Exception e) {
			e.printStackTrace();
		}
 		request.getRequestDispatcher("results.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
