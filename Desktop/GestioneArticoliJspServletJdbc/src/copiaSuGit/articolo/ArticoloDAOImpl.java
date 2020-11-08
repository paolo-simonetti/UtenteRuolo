package it.gestionearticoli.dao.articolo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.TreeSet;

import it.gestionearticoli.dao.AbstractMySQLDAO;
import it.gestionearticoli.model.Articolo;

public class ArticoloDAOImpl extends AbstractMySQLDAO implements ArticoloDAO {

	@Override
	public TreeSet<Articolo> list() throws Exception {
		if (isNotActive()) {
			return null;
		}

		TreeSet<Articolo> result = new TreeSet<Articolo>();
		Articolo articoloTemp = null;

		try (Statement ps = connection.createStatement()) {
			ResultSet rs = ps.executeQuery("select * from articolo");

			while (rs.next()) {
				articoloTemp = new Articolo();
				articoloTemp.setCodice(rs.getString("CODICE"));
				articoloTemp.setDescrizione(rs.getString("DESCRIZIONE"));
				articoloTemp.setPrezzo(rs.getInt("PREZZO"));
				articoloTemp.setId(rs.getLong("ID"));
				articoloTemp.setCategoriaFK(rs.getLong("CATEGORIA_FK"));
				result.add(articoloTemp);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public Articolo get(Long id) throws Exception {
		if (isNotActive()||id==null) {
			return null;
		} else {
			Articolo result=new Articolo();
			String query="SELECT * FROM articolo WHERE ID=?";
			try (PreparedStatement preparedStatement=connection.prepareStatement(query)) {
				preparedStatement.setLong(1,id);
				ResultSet resultSet=preparedStatement.executeQuery();
				if (resultSet.next()) {
					result.setId(resultSet.getLong("ID"));
					result.setCodice(resultSet.getString("CODICE"));
					result.setPrezzo(resultSet.getInt("PREZZO"));
					result.setDescrizione(resultSet.getString("DESCRIZIONE"));
					result.setCategoriaFK(resultSet.getLong("CATEGORIA_FK"));
				} else {
					result=null;
				}
			} catch(SQLException e) {
				e.printStackTrace();
			} catch(Exception e) {
				e.printStackTrace();
			}
			return result;			
		}
	}

	@Override
	public int update(Articolo input) throws Exception {
		if (isNotActive()||input==null) {
			return -1;
		} else {
			int result=0;
			String query=null;
			if (input.getCategoriaFK()!=null) {
				query="UPDATE articolo SET PREZZO=?,DESCRIZIONE=?,CODICE=?, CATEGORIA_FK=? WHERE ID=?";				
			} else {
				query="UPDATE articolo SET PREZZO=?,DESCRIZIONE=?,CODICE=? WHERE ID=?";
			}
			try (PreparedStatement preparedStatement=connection.prepareStatement(query)) {
				preparedStatement.setInt(1,input.getPrezzo());
				preparedStatement.setString(2,input.getDescrizione());
				preparedStatement.setString(3,input.getCodice());
				if(input.getCategoriaFK()!=null) {
					preparedStatement.setLong(4,input.getCategoriaFK());					
					preparedStatement.setLong(5,input.getId());

				} else {
					preparedStatement.setLong(4,input.getId());
				}
				result= preparedStatement.executeUpdate();
			} catch(SQLException e) {
				e.printStackTrace();
			}
			return result;
		}
	}

	@Override
	public Long insert(Articolo input) throws Exception {
		if (isNotActive() || input == null) {
			return -1L;
		}
		Long result = 0L;
		String query=null;
		if (input.getCategoriaFK()!=null) {
			query="INSERT INTO articolo (PREZZO, DESCRIZIONE, CODICE, CATEGORIA_FK) VALUES (?, ?, ?, ?)";
		} else {
			query="INSERT INTO articolo (PREZZO, DESCRIZIONE, CODICE) VALUES (?, ?, ?)";
		}
		try (PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
			ps.setInt(1, input.getPrezzo());
			ps.setString(2, input.getDescrizione());
			ps.setString(3, input.getCodice());
			if (input.getCategoriaFK()!=null) {
				ps.setLong(4,input.getCategoriaFK());				
			} 
			ps.executeUpdate();
			ResultSet resultSet=ps.getGeneratedKeys();
			if (resultSet.next()) {
				input.setId(resultSet.getLong(1));
				result=input.getId();
			} else {
				System.err.println("Generated Keys non trovate per insertArticolo");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int delete(Articolo input) throws Exception {
		if (isNotActive()||input==null) {
			return -1;
		} else {
			int result=0;
			String query="DELETE FROM articolo WHERE ID=?";
			try (PreparedStatement preparedStatement=connection.prepareStatement(query)) {
				preparedStatement.setLong(1,input.getId());
				result= preparedStatement.executeUpdate();
			} catch(SQLException e) {
				e.printStackTrace();
			}
			return result;
		}
	}
	
	public TreeSet<Articolo> listArticoliCategoria(Long idCategoriaInput) throws Exception {
		if(isNotActive()) {
			return null;
		} else if(idCategoriaInput==0L||idCategoriaInput==null) {
			TreeSet<Articolo> result=new TreeSet<Articolo>();
			String query="SELECT* FROM articolo WHERE CATEGORIA_FK IS NULL";
			try(Statement statement=connection.createStatement()) {
				ResultSet resultSet=statement.executeQuery(query); 
				Articolo articoloTemp=null;
				while (resultSet.next()) {
					articoloTemp = new Articolo();
					articoloTemp.setCodice(resultSet.getString("CODICE"));
					articoloTemp.setDescrizione(resultSet.getString("DESCRIZIONE"));
					articoloTemp.setPrezzo(resultSet.getInt("PREZZO"));
					articoloTemp.setId(resultSet.getLong("ID"));
					result.add(articoloTemp);					
				}
			}
			return result;
		} else {
			TreeSet<Articolo> result=new TreeSet<Articolo>();
			String query="SELECT* FROM articolo WHERE CATEGORIA_FK=?";
			try(PreparedStatement preparedStatement=connection.prepareStatement(query)) {
				preparedStatement.setLong(1,idCategoriaInput);
				ResultSet resultSet=preparedStatement.executeQuery(); 
				Articolo articoloTemp=null;
				while (resultSet.next()) {
					articoloTemp = new Articolo();
					articoloTemp.setCodice(resultSet.getString("CODICE"));
					articoloTemp.setDescrizione(resultSet.getString("DESCRIZIONE"));
					articoloTemp.setPrezzo(resultSet.getInt("PREZZO"));
					articoloTemp.setId(resultSet.getLong("ID"));
					articoloTemp.setCategoriaFK(resultSet.getLong("CATEGORIA_FK"));
					result.add(articoloTemp);					
				}
			}
			return result;
		}
		
	}
	
	@Override
	public TreeSet<Articolo> findByExample(Articolo input) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setConnection(Connection connection) {
		this.connection = connection;
	}

}
