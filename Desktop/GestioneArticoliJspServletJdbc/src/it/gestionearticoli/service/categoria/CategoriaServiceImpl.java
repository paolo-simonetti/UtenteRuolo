package it.gestionearticoli.service.categoria;

import java.util.TreeSet;

import it.gestionearticoli.dao.categoria.CategoriaDAO;
import it.gestionearticoli.model.Categoria;

public class CategoriaServiceImpl implements CategoriaService {

	public CategoriaServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setCategoriaDao(CategoriaDAO categoriaDao) {
		// TODO Auto-generated method stub

	}

	@Override
	public TreeSet<Categoria> listAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Categoria trovaTramiteId(Long idCategoria) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int aggiorna(Categoria input) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int inserisciNuovo(Categoria input) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int rimuovi(Categoria input) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public TreeSet<Categoria> findByExample(Categoria input) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
