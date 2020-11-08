package it.gestionearticoli.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/PrepareDeleteArticoloDaListaServlet")
public class PrepareDeleteArticoloDaListaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PrepareDeleteArticoloDaListaServlet() {
        super();
     }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String stringaIdArticolo=request.getParameter("idArticoloDaEliminare");
		request.setAttribute("idArticoloDaEliminare",stringaIdArticolo);
		request.getRequestDispatcher("confermaEliminazione.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
