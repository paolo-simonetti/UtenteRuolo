package it.gestionearticoli.mainProva;

import it.gestionearticoli.model.Articolo;
import it.gestionearticoli.model.Categoria;
import it.gestionearticoli.service.MyServiceFactory;

public class ProvaFunzionamentoService {

	public static void main(String[] args) {
		try {
			Articolo articolo=new Articolo("wue3u","Coltellone svizzero",12,20L);
			MyServiceFactory.getArticoloServiceInstance().inserisciNuovo(articolo);
			Categoria categoria=MyServiceFactory.getCategoriaServiceInstance().trovaTramiteId(20L);
			MyServiceFactory.getCategoriaServiceInstance().sincronizzaElencoArticoli(categoria);
			for (Articolo a:categoria.getElencoArticoli()) {
				System.out.println(a.getDescrizione()+ " nella categoria " + a.getCategoriaFK());
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
