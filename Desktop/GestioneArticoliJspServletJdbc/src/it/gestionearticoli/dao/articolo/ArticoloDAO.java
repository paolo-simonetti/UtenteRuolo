package it.gestionearticoli.dao.articolo;

import java.sql.Connection;
import java.util.TreeSet;

import it.gestionearticoli.dao.IBaseDAO;
import it.gestionearticoli.model.Articolo;

public interface ArticoloDAO extends IBaseDAO<Articolo> {

	public TreeSet<Articolo> list() throws Exception;
	public Articolo get(Long id) throws Exception;
	public int update(Articolo input) throws Exception;
	public int insert(Articolo input) throws Exception;
	public int delete(Articolo input) throws Exception;
	public TreeSet<Articolo> findByExample(Articolo input) throws Exception;
	public void setConnection(Connection connection);
	
}
