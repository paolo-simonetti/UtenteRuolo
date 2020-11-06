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
 * Servlet implementation class ExecuteUpdateArticoloDaListaServlet
 */
@WebServlet("/ExecuteUpdateArticoloDaListaServlet")
public class ExecuteUpdateArticoloDaListaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExecuteUpdateArticoloDaListaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStringaInput=request.getParameter("idArticolo");
		Long idInput=Long.parseLong(idStringaInput);
		String codiceInput=request.getParameter("codice");
		String descrizioneInput=request.getParameter("descrizione");
		String prezzoStringaInput=request.getParameter("prezzo");
		int prezzo=Integer.parseInt(prezzoStringaInput);
		if (codiceInput.isEmpty()) {
			request.setAttribute("errorMessage", "Attenzione: sono presenti errori di validazione");
			Articolo articolo=new Articolo();
			try {
				articolo=MyServiceFactory.getArticoloServiceInstance().trovaTramiteId(idInput);				
			} catch(Exception e) {
				e.printStackTrace();
			}
			articolo.setCodice(codiceInput);
			request.setAttribute("articoloDaAggiornare",articolo);
			request.getRequestDispatcher("update.jsp").forward(request, response);
			return;
		} else if (descrizioneInput.isEmpty()) {
			request.setAttribute("errorMessage", "Attenzione: sono presenti errori di validazione");
			Articolo articolo=new Articolo();
			try {
				articolo=MyServiceFactory.getArticoloServiceInstance().trovaTramiteId(idInput);				
			} catch(Exception e) {
				e.printStackTrace();
			}
			articolo.setDescrizione(descrizioneInput);
			request.setAttribute("articoloDaAggiornare",articolo);
			request.getRequestDispatcher("update.jsp").forward(request, response);
			return;
		} else if (prezzo<1) {
			request.setAttribute("errorMessage", "Attenzione: sono presenti errori di validazione");
			Articolo articolo=new Articolo();
			try {
				articolo=MyServiceFactory.getArticoloServiceInstance().trovaTramiteId(idInput);				
			} catch(Exception e) {
				e.printStackTrace();
			}
			articolo.setPrezzo(prezzo);
			request.setAttribute("articoloDaAggiornare",articolo);
			request.getRequestDispatcher("update.jsp").forward(request, response);
			return;
		}

		Articolo articolo=new Articolo(codiceInput,descrizioneInput,prezzo);
		articolo.setId(idInput);
		try{
			MyServiceFactory.getArticoloServiceInstance().aggiorna(articolo);
			request.setAttribute("listaArticoliAttribute", MyServiceFactory.getArticoloServiceInstance().listAll());
			request.setAttribute("successMessage", "Operazione effettuata con successo");
		} catch(Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("InsertResult.jsp").forward(request, response);
	}

}
