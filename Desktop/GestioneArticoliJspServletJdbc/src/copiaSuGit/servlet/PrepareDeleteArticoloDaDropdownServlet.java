package it.gestionearticoli.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.gestionearticoli.model.Articolo;
import it.gestionearticoli.service.MyServiceFactory;

@WebServlet("/PrepareDeleteArticoloDaDropdownServlet")
public class PrepareDeleteArticoloDaDropdownServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PrepareDeleteArticoloDaDropdownServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idArticoloStringaInput=(String) request.getParameter("idArticoloDaEliminare");
		Long idArticoloInput=(idArticoloStringaInput.isEmpty()? 0L:Long.parseLong(idArticoloStringaInput));
		if (idArticoloInput<=0L) {
			request.setAttribute("errorMessage","L'id dell'articolo è non valido, nullo o negativo!");
			request.getRequestDispatcher("deleteDaDropdownArticolo.jsp").forward(request,response);
		} else {
			try {
				Articolo articolo=MyServiceFactory.getArticoloServiceInstance().trovaTramiteId(idArticoloInput);
				if (articolo==null) {
					request.setAttribute("errorMessage","Articolo non presente!");
					request.getRequestDispatcher("deleteDaDropdownArticolo.jsp").forward(request,response);					
				} else {
					request.setAttribute("idArticoloDaEliminare",articolo.getId());
					request.getRequestDispatcher("confermaEliminazione.jsp").forward(request,response);
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

}
