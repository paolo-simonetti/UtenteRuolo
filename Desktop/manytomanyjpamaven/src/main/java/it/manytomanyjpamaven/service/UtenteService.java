package it.manytomanyjpamaven.service;

import java.util.Set;

import it.manytomanyjpamaven.dao.UtenteDAO;
import it.manytomanyjpamaven.model.Ruolo;
import it.manytomanyjpamaven.model.Utente;

public interface UtenteService  {
	
	public Set<Utente> listAll() throws Exception;

	public Utente caricaSingoloElemento(Long id) throws Exception;

	public void aggiorna(Utente utenteInstance) throws Exception;

	public void inserisciNuovo(Utente utenteInstance) throws Exception;

	public void rimuovi(Utente utenteInstance) throws Exception;
	
	public void aggiungiRuolo(Utente utenteEsistente, Ruolo ruoloInstance) throws Exception;

	//per injection
	public void setUtenteDAO(UtenteDAO utenteDAO);

}
