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

@WebServlet("/GetArticoloDaDropdownServlet")
public class GetArticoloDaDropdownServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetArticoloDaDropdownServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idArticoloStringaInput=request.getParameter("idArticoloDaVisualizzare");
		Long idArticoloInput=(idArticoloStringaInput.isEmpty()? 0L:Long.parseLong(idArticoloStringaInput));
		if (idArticoloInput<=0L) {
			request.setAttribute("errorMessage","L'id dell'articolo � non valido, nullo o negativo!");
			request.getRequestDispatcher("deleteDaDropdownArticolo.jsp").forward(request,response);
		} else {
			try {
				Articolo articolo=MyServiceFactory.getArticoloServiceInstance().trovaTramiteId(idArticoloInput);
				if (articolo==null) {
					request.setAttribute("alertMessage","L'id immesso non ha corrispondenza tra gli articoli presenti!");
				} else {
					TreeSet<Articolo> listaMonoElemento=new TreeSet<>(); //mi serve per poter far arrivare tutto su results.jsp, dove c'� un forEach
					listaMonoElemento.add(articolo);
					request.setAttribute("listaArticoliAttribute",listaMonoElemento);
				}
				request.getRequestDispatcher("results.jsp").forward(request,response);
			} catch(Exception e) {
				e.printStackTrace();
			}
			
		}
	}

}
