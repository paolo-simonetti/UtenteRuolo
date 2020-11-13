package it.manytomanyjpamaven.dao;

import java.util.Set;

import it.manytomanyjpamaven.model.Ruolo;
import it.manytomanyjpamaven.model.Utente;

public interface UtenteDAO extends IBaseDAO<Utente> {
	
	public Set<Utente> findAllByRuolo(Ruolo ruoloInput);

}
