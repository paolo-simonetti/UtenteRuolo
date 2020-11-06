package it.gestionearticoli.dao.categoria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.TreeSet;

import it.gestionearticoli.dao.AbstractMySQLDAO;
import it.gestionearticoli.model.Categoria;

public class CategoriaDAOImpl extends AbstractMySQLDAO implements CategoriaDAO {

	@Override
	public TreeSet<Categoria> list() throws Exception {
		if(isNotActive()) {
			return null;
		}
		String query="SELECT * FROM categoria";
		TreeSet<Categoria> result=new TreeSet<>();
		try (Statement statement=connection.createStatement()) {
			ResultSet resultSet=statement.executeQuery(query);
			Categoria categoriaTemp;
			while (resultSet.next()) {
				categoriaTemp=new Categoria();
				categoriaTemp.setIdCategoria(resultSet.getLong("id_categoria"));
				categoriaTemp.setNomeCategoria(resultSet.getString("nome_categoria"));
				result.add(categoriaTemp);
			}
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;		
	}

	@Override
	public Categoria get(Long idCategoria) throws Exception {
		if (isNotActive()||idCategoria==null) {
			return null;
		} else {
			Categoria result=new Categoria();
			String query="SELECT * FROM categoria WHERE id_categoria=?";
			try (PreparedStatement preparedStatement=connection.prepareStatement(query)) {
				preparedStatement.setLong(1,idCategoria);
				ResultSet resultSet=preparedStatement.executeQuery();
				if (resultSet.next()) {
					result.setIdCategoria(resultSet.getLong("id_categoria"));
					result.setNomeCategoria(resultSet.getString("nome_categoria"));
				} else {
					result=null;
				}
			} catch(SQLException e) {
				e.printStackTrace();
				throw e;
			} catch(Exception e) {
				e.printStackTrace();
				throw e;
			}
			return result;			
		}
	}

	@Override
	public int update(Categoria input) throws Exception {
		if (isNotActive()||input==null) {
			return -1;
		} else {
			int result=0;
			String query="UPDATE categoria SET nome_categoria=? WHERE id_categoria=?";
			try (PreparedStatement preparedStatement=connection.prepareStatement(query)) {
				preparedStatement.setString(1,input.getNomeCategoria());
				result= preparedStatement.executeUpdate();
			} catch(SQLException e) {
				e.printStackTrace();
				throw e;
			}
			return result;
		}
	}

	@Override
	public int insert(Categoria input) throws Exception {
		if (isNotActive() || input == null) {
			return -1;
		}
		int result = 0;
		try (PreparedStatement ps = connection.prepareStatement("INSERT INTO categoria (nome_categoria) VALUES ?")) {
			ps.setString(1, input.getNomeCategoria());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;

	}

	@Override
	public int delete(Categoria input) throws Exception {
		if (isNotActive()||input==null) {
			return -1;
		} else {
			int result=0;
			String query="DELETE FROM categoria WHERE id_categoria=?";
			try (PreparedStatement preparedStatement=connection.prepareStatement(query)) {
				preparedStatement.setLong(1,input.getIdCategoria());
				result= preparedStatement.executeUpdate();
			} catch(SQLException e) {
				e.printStackTrace();
			}
			return result;
		}
	}

	@Override
	public TreeSet<Categoria> findByExample(Categoria input) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setConnection(Connection connection) {
		this.connection=connection;
	}

}
