package it.gestionearticoli.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.gestionearticoli.model.Categoria;
import it.gestionearticoli.service.MyServiceFactory;

@WebServlet("/PrepareUpdateCategoriaDaDropdownServlet")
public class PrepareUpdateCategoriaDaDropdownServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PrepareUpdateCategoriaDaDropdownServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idCategoriaStringaInput=request.getParameter("idCategoriaDaAggiornare");
		Long idCategoriaInput=(idCategoriaStringaInput.isEmpty()? 0L:Long.parseLong(idCategoriaStringaInput));
		if (idCategoriaInput<=0L) {
			request.setAttribute("errorMessage","L'id della categoria è non valido, nullo o negativo!");
			request.getRequestDispatcher("updateDaDropdownCategoria.jsp").forward(request,response);
		} else {
			try {
				Categoria categoria=MyServiceFactory.getCategoriaServiceInstance().trovaTramiteId(idCategoriaInput);
				if (categoria==null) {
					request.setAttribute("errorMessage","Categoria non presente!");
					request.getRequestDispatcher("updateDaDropdownCategoria.jsp").forward(request,response);					
				} else {
					request.setAttribute("categoriaDaAggiornare",categoria);
					request.getRequestDispatcher("updateCategoria.jsp").forward(request,response);
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

}
