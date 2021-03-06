package it.gestionearticoli.service.articolo;

import java.util.TreeSet;

import it.gestionearticoli.dao.articolo.ArticoloDAO;
import it.gestionearticoli.model.Articolo;

public interface ArticoloService {

	// questo mi serve per injection
	public void setArticoloDao(ArticoloDAO articoloDao);
	public TreeSet<Articolo> listAll() throws Exception;
	public Articolo trovaTramiteId(Long idInput) throws Exception;
	public int aggiorna(Articolo input) throws Exception;
	public int inserisciNuovo(Articolo input) throws Exception;
	public int rimuovi(Articolo input) throws Exception;
	public TreeSet<Articolo> findByExample(Articolo input) throws Exception;

}
