package it.gestionearticoli.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.gestionearticoli.model.Articolo;
import it.gestionearticoli.service.MyServiceFactory;

@WebServlet("/PrepareUpdateArticoloDaListaServlet")
public class PrepareUpdateArticoloDaListaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PrepareUpdateArticoloDaListaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String stringaIdArticolo=request.getParameter("idArticoloDaAggiornare");
		Long idArticolo =Long.parseLong(stringaIdArticolo);
		try {
			Articolo articolo=MyServiceFactory.getArticoloServiceInstance().trovaTramiteId(idArticolo);
			request.setAttribute("articoloDaAggiornare",articolo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("update.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
