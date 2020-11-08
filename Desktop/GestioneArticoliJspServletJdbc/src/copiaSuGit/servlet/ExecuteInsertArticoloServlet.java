package it.gestionearticoli.web.servlet;

import java.io.IOException;
import java.util.TreeSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.gestionearticoli.model.Articolo;
import it.gestionearticoli.model.Categoria;
import it.gestionearticoli.service.MyServiceFactory;

@WebServlet("/ExecuteInsertArticoloServlet")
public class ExecuteInsertArticoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ExecuteInsertArticoloServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// validiamo input
		String codiceInputParam = request.getParameter("codice");
		String descrizioneInputParam = request.getParameter("descrizione");
		String prezzoInputStringParam = request.getParameter("prezzo");
		Integer prezzo = !prezzoInputStringParam.isEmpty() ? Integer.parseInt(prezzoInputStringParam) : 0;
		String categoriaInputStringParam=request.getParameter("categoria");
		// se la validazione fallisce torno in pagina
		TreeSet<Articolo> articoliPresenti=null;
		try {
			articoliPresenti=MyServiceFactory.getArticoloServiceInstance().listAll();
		} catch(Exception e) {
			System.err.println("Errore nel recupero degli articoli presenti");
			e.printStackTrace();
		}
		for (Articolo a:articoliPresenti) {
			if (descrizioneInputParam.equals(a.getDescrizione())) {
				request.setAttribute("errorMessage", "Un articolo con questa descrizione è già presente. Volevi farne l'update?");
				request.getRequestDispatcher("insert.jsp").forward(request, response);
				return;
			}
		}
		if (codiceInputParam.isEmpty() || descrizioneInputParam.isEmpty() || prezzo < 1) {
			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			request.getRequestDispatcher("insert.jsp").forward(request, response);
			return;
		}
		
		/* se la categoria non è ancora presente, l'articolo viene inserito ugualmente, ma categorizzato a null, perché 
		è così che ho impostato il model*/
		try {
			TreeSet<Categoria> categoriePresenti=MyServiceFactory.getCategoriaServiceInstance().listAll();
			boolean categoriaIsPresente=false;
			Long idCategoria=null;
			for (Categoria c:categoriePresenti) {
				if (categoriaInputStringParam.equals(c.getNomeCategoria())) {
					categoriaIsPresente=true;
					idCategoria=c.getIdCategoria();
					break;
				}
			}
			Articolo articoloDaInserire=new Articolo(codiceInputParam,descrizioneInputParam,prezzo,null);
			if (!categoriaIsPresente) {
				MyServiceFactory.getArticoloServiceInstance().inserisciNuovo(articoloDaInserire);
				if (categoriaInputStringParam.isEmpty()) {
					request.setAttribute("successMessage","Articolo aggiunto con successo!");
				} else {
					request.setAttribute("alertMessage","La categoria inserita non era presente nella lista, è stata impostata a null!");					
				}
				request.setAttribute("listaArticoliAttribute",MyServiceFactory.getArticoloServiceInstance().listAll());
				request.getRequestDispatcher("results.jsp").forward(request,response);
			} else {
				articoloDaInserire.setCategoriaFK(idCategoria);
				MyServiceFactory.getArticoloServiceInstance().inserisciNuovo(articoloDaInserire);
				request.setAttribute("listaArticoliAttribute",MyServiceFactory.getArticoloServiceInstance().listAll());
				request.setAttribute("successMessage", "Operazione effettuata con successo");
				request.getRequestDispatcher("results.jsp").forward(request,response); 				
			}
		} catch (Exception e) {
			System.err.println("Errore nell'inserimento della nuova categoria nel db!");
			e.printStackTrace();
		}

	}

}
