package control;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import model.Esame;
import model.SkillCard;
import view.EsamiView;

public class EcdlDAOSQLServer implements EcdlDAO {
	
	private Connection conn;
	
	private PreparedStatement insertEsame;
	private PreparedStatement insertSkillCard;
	private PreparedStatement updateEsame;
	private PreparedStatement updateSkillCard;
	private PreparedStatement deleteEsame;
	private PreparedStatement deleteSkillCard;
	
	
	//GETTERS
	protected Connection getConn() {
		if (conn == null) {
			try {
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/corsi_ecdl?user=root");
			} catch (SQLException e) {
				System.out.println("Errore durante la crezione della connessione al DB");
				e.printStackTrace();
			}
		}
		return conn;
	}

	public PreparedStatement getInsertEsame() throws SQLException {
		if (insertEsame == null) {
			insertEsame = getConn().prepareStatement("insert into esame values(?,?,?,?,?,?)");
			
		}
		return insertEsame;
	}

	public PreparedStatement getInsertSkillCard() throws SQLException {
		if (insertSkillCard == null) {
			insertSkillCard = getConn().prepareStatement("insert into skills_card values(?,?,?,?,?,?)");
		}
		return insertSkillCard;
	}

	public PreparedStatement getUpdateEsame() throws SQLException {
		if (updateEsame == null) {
			updateEsame = getConn().prepareStatement("update esame set id_esame=?, data=?"
					+ "modulo=?, punteggio=?, esito=? where skills_card=?");
		}
		return updateEsame;
	}

	public PreparedStatement getUpdateSkillCard(String s) throws SQLException {
		if (updateSkillCard == null) {
			updateSkillCard = getConn().prepareStatement("update skills_card set nome=?, cognome=?, email=?, pwd=?, data_di_nascita=? where skills_card='"+s+"'");	
		}
		return updateSkillCard;
	}

	public PreparedStatement getDeleteEsame() throws SQLException {
		if (deleteEsame == null) {
			deleteEsame = getConn().prepareStatement("delete from esame where id_esame=?");
		}
		return deleteEsame;
	}

	public PreparedStatement getDeleteSkillCard() throws SQLException {
		if (deleteSkillCard == null) {
			deleteSkillCard = getConn().prepareStatement("delete from skills_card where skills_card=?");
		}
		return deleteSkillCard;
	}
	
	//METODI
	@Override
	public void addEsame(Esame es) {
		try {
			getInsertEsame().clearParameters();
			
			int i = 1;
			
			getInsertEsame().setInt(i++, es.getIdEsame());
			getInsertEsame().setDate(i++, (Date) es.getDate());
			getInsertEsame().setString(i++, es.getModulo());
			getInsertEsame().setInt(i++,es.getPunteggio());
			getInsertEsame().setBoolean(i++, es.isEsito());
			getInsertEsame().setString(i++, es.getSkillsCard());
			
			getInsertEsame().execute();
		} catch (SQLException e) {
			System.out.println("addEsame" + e);
		}

	}

	@Override
	public void addSkillCard(SkillCard sk) {
		try {
			getInsertSkillCard().clearParameters();
			
			int i = 1;
			
			getInsertSkillCard().setString(i++, sk.getSkillCard());
			getInsertSkillCard().setString(i++, sk.getNome());
			getInsertSkillCard().setString(i++, sk.getCognome());
			getInsertSkillCard().setString(i++, sk.getEmail());
			getInsertSkillCard().setString(i++, sk.getPwd());
			getInsertSkillCard().setDate(i++, (Date) sk.getDataDiNascita());
			
			getInsertSkillCard().execute();
		} catch (SQLException e) {
			System.out.println("addSK");
		}

	}

	@Override
	public void delEsame(Esame es) {
		try {
			getDeleteEsame().clearParameters();
			
			getDeleteEsame().setInt(1, es.getIdEsame());
			
			getDeleteEsame().execute();
		} catch (SQLException e) {
			System.out.println("delEs");
		}

	}

	@Override
	public void delSkillCard(SkillCard sk) {
		try {
			
			getDeleteSkillCard().clearParameters();
			
			getDeleteSkillCard().setString(1, sk.getSkillCard());
			
			getDeleteSkillCard().execute();
		} catch (SQLException e) {
			System.out.println("delSK");
		}

	}

	@Override
	public void updateEsame(Esame es) {
		try {
			getUpdateEsame().clearParameters();
			
			int i = 1;
			
			getUpdateEsame().setInt(i++, es.getIdEsame());
			getUpdateEsame().setDate(i++, (Date) es.getDate());
			getUpdateEsame().setString(i++, es.getModulo());
			getUpdateEsame().setInt(i++,es.getPunteggio());
			getUpdateEsame().setBoolean(i++, es.isEsito());
			getUpdateEsame().setString(i++, es.getSkillsCard());
			
			getUpdateEsame().execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void updateSkillCard(SkillCard sk) {
		try {
			String s = sk.getSkillCard();
			getUpdateSkillCard(s).clearParameters();
			
			int i = 1;
			
//			getUpdateSkillCard(s).setString(i++, sk.getSkillsCard());
			getUpdateSkillCard(s).setString(i++, sk.getNome());
			getUpdateSkillCard(s).setString(i++, sk.getCognome());
			getUpdateSkillCard(s).setString(i++, sk.getEmail());
			getUpdateSkillCard(s).setString(i++, sk.getPwd());
			getUpdateSkillCard(s).setDate(i++, (Date) sk.getDataDiNascita());
			
			getUpdateSkillCard(s).execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}



	@Override
	public List<SkillCard> getSkillCard() {
		List<SkillCard> resultSkillCard = null;
		
		try {
			resultSkillCard = new ArrayList<SkillCard>();
			
			String selezione = "select * from skills_card";
			Statement st = getConn().createStatement();
			ResultSet rs = st.executeQuery(selezione);
			
			while(rs.next()){
				SkillCard sk = new SkillCard();
				
				sk.setSkillCard(rs.getString("skills_card"));
				sk.setNome(rs.getString("nome"));
				sk.setCognome(rs.getString("cognome"));
				sk.setEmail(rs.getString("email"));
				sk.setPwd(rs.getString("pwd"));
				sk.setDataDiNascita(rs.getDate("data_di_nascita"));
				
				resultSkillCard.add(sk);
			}
		} catch (SQLException e) {
			System.out.println("Errore getSK" + e);
		}
		return resultSkillCard;
	}

	@Override
	public List<Esame> getEsame(String skill) {
		List<Esame> resultEsame = null;
		try {
			resultEsame = new ArrayList<Esame>();
			String selezione = "select * from esame  where skills_card = '"+ skill +"'";
			Statement st = getConn().createStatement();
			ResultSet rs = st.executeQuery(selezione);
			
			while(rs.next()){
				Esame es = new Esame();
				
				es.setIdEsame(rs.getInt("idesame"));
				es.setDate(rs.getDate("data"));
				es.setModulo(rs.getString("modulo"));
				es.setPunteggio(rs.getInt("punteggio"));
				es.setEsito(rs.getBoolean("esito"));
				es.setSkillsCard(rs.getString("skills_card"));
				
				resultEsame.add(es);
			}
		} catch (SQLException e) {
			System.out.println("Errore getEsame" + e);
		}
		return resultEsame;
	}
	

}
