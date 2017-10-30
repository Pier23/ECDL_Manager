package integration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import business.Articolo;

public class ConnessioneDB {
	
	private Connection conn;
	
	//METODI
	public List<Articolo> getAll() throws SQLException{
		
		List<Articolo> result = new ArrayList<Articolo>();
		
		String mySelect = "Select * from Articolo";
		Statement st = getConn().createStatement();
		ResultSet rs = st.executeQuery(mySelect);
		
		while (rs.next()) {
			Articolo a = new Articolo();
			a.setId(rs.getInt("id"));
			a.setNome(rs.getString("Nome"));
			a.setCodArticolo(rs.getString("CodiceArticolo"));
			a.setDescrizione(rs.getString("Descrizione"));
			a.setPrezzo(rs.getFloat("Prezzo"));
			result.add(a);
			
		}
		
		return result;
	}

	//GETTERS AND SETTERS
	public Connection getConn() throws SQLException {
		if (conn == null) {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/corsojava?user=root");
			
		}
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}
	

}
