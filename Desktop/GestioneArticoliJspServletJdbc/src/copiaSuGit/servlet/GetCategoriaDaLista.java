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

@WebServlet("/GetCategoriaDaLista")
public class GetCategoriaDaLista extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetCategoriaDaLista() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String stringaIdCategoriaInput=request.getParameter("idCategoria");
		System.out.println(stringaIdCategoriaInput);
		System.out.println(Long.parseLong(stringaIdCategoriaInput));
		TreeSet<Articolo> articoliDiCategoriaInput=null;
		try {
			articoliDiCategoriaInput=MyServiceFactory.getArticoloServiceInstance().elencaArticoliCategoria(Long.parseLong(stringaIdCategoriaInput)); 
			request.setAttribute("listaArticoliAttribute",articoliDiCategoriaInput);
			request.getRequestDispatcher("results.jsp").forward(request,response);
		} catch(Exception e) {
			System.err.println("Errore nel recupero degli articoli della categoria richiesta");
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
