package it.gestionearticoli.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.gestionearticoli.service.MyServiceFactory;

@WebServlet("/ListArticoliServlet")
public class ListArticoliServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ListArticoliServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Sono arrivato a ListArticoliServlet");
		//preparo la lista di articoli
		try {
			request.setAttribute("listaArticoliAttribute", MyServiceFactory.getArticoloServiceInstance().listAll());
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("results.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
