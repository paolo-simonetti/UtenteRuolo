package it.gestionearticoli.service.utente;

import java.util.TreeSet;

import it.gestionearticoli.dao.articolo.ArticoloDAO;
import it.gestionearticoli.dao.utente.UtenteDAO;
import it.gestionearticoli.model.Utente;

public class UtenteServiceImpl implements UtenteService {

	private UtenteDAO utenteDao;
	
	@Override
	public void setUtenteDao(UtenteDAO utenteDao) {
		this.utenteDao = utenteDao;
	}

	@Override
	public TreeSet<Utente> listAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Utente trovaTramiteId(Long idInput) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int aggiorna(Utente input) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int inserisciNuovo(Utente input) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int rimuovi(Utente input) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public TreeSet<Utente> findByExample(Utente input) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
