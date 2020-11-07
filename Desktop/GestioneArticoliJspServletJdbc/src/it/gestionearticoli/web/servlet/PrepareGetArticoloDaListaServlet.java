package it.gestionearticoli.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.gestionearticoli.model.Articolo;
import it.gestionearticoli.service.MyServiceFactory;

/**
 * Servlet implementation class PrepareGetArticoloDaListaServlet
 */
@WebServlet("/PrepareGetArticoloDaListaServlet")
public class PrepareGetArticoloDaListaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrepareGetArticoloDaListaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String stringaIdArticolo=request.getParameter("idArticoloDaVisualizzare");
		Long idArticolo = !stringaIdArticolo.isEmpty() ? Long.parseLong(stringaIdArticolo) : 0;	
		if (idArticolo<1L) {
			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			request.getRequestDispatcher("getDaDropdown.jsp").forward(request, response);
			return;
		}
		try {
			Articolo articolo=MyServiceFactory.getArticoloServiceInstance().trovaTramiteId(idArticolo);
			if (articolo==null) {
				request.setAttribute("errorMessage", "Attenzione: articolo non esistente");
				request.getRequestDispatcher("getDaDropdown.jsp").forward(request, response);
				return;
			}
			request.setAttribute("articoloDaVisualizzare",articolo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("ExecuteGetArticoloDaListaServlet").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
