package it.manytomanyjpamaven.dao;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import it.manytomanyjpamaven.model.Ruolo;
import it.manytomanyjpamaven.model.Utente;

public class RuoloDAOImpl implements RuoloDAO {

	private EntityManager entityManager;

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public Set<Ruolo> list() throws Exception {
		return entityManager.createQuery("from Ruolo",Ruolo.class).getResultList().stream().collect(Collectors.toSet());	}

	@Override
	public Ruolo get(Long id) throws Exception {
		return entityManager.find(Ruolo.class, id);
	}

	@Override
	public void update(Ruolo ruoloInstance) throws Exception {
		if (ruoloInstance == null) {
			throw new Exception("Problema ruolo in input");
		}
		ruoloInstance = entityManager.merge(ruoloInstance);

	}

	@Override
	public void insert(Ruolo ruoloInstance) throws Exception {
		if (ruoloInstance == null) {
			throw new Exception("Problema valore in input");
		}

		entityManager.persist(ruoloInstance);

	}

	@Override
	public void delete(Ruolo ruoloInstance) throws Exception {
		if (ruoloInstance == null) {
			throw new Exception("Problema ruolo in input");
		} else {
			Set<Utente> utentiConRuoloInstance=new HashSet<>();
			UtenteDAO utenteDAO=MyDAOFactory.getUtenteDAOInstance();
			try {
				utentiConRuoloInstance=utenteDAO.findAllByRuolo(ruoloInstance);
			} catch(Exception e) {
				System.err.println("Errore nella ricerca degli utenti col ruolo in input");
				throw(e);
			}
			if (utentiConRuoloInstance.size()==0) {
				entityManager.remove(entityManager.merge(ruoloInstance));				
			} else {
				System.err.println("Non è stato possibile eliminare il ruolo, in quanto è attualmente affidato ai seguenti utenti:");
				utentiConRuoloInstance.stream().forEach((utente)->{System.err.println(utente);});
			}
		}
	}

	@Override
	public Ruolo findByDescrizioneAndCodice(String descrizione, String codice) throws Exception {
		TypedQuery<Ruolo> query = entityManager
				.createQuery("select r from Ruolo r where r.descrizione=?1 and r.codice=?2", Ruolo.class)
				.setParameter(1, descrizione)
				.setParameter(2, codice);
		
		return query.getResultStream().findFirst().orElse(null);
	}

}
