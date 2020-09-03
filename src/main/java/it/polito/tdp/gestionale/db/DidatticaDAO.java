package it.polito.tdp.gestionale.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.gestionale.model.Arco;
import it.polito.tdp.gestionale.model.Corso;
import it.polito.tdp.gestionale.model.Studente;

public class DidatticaDAO {

	/*
	 * Dato un codice insegnamento, ottengo il corso
	 */
//	public Corso getCorso(String codins) {
//
//		final String sql = "SELECT * FROM corso where codins=?";
//
//		try {
//			Connection conn = DBConnect.getConnection();
//			PreparedStatement st = conn.prepareStatement(sql);
//			st.setString(1, codins);
//
//			ResultSet rs = st.executeQuery();
//
//			if (rs.next()) {
//
//				Corso corso = new Corso(rs.getString("codins"), rs.getInt("crediti"), rs.getString("nome"),
//						rs.getInt("pd"));
//				return corso;
//			}
//
//			return null;
//
//		} catch (SQLException e) {
//			// e.printStackTrace();
//			throw new RuntimeException("Errore Db");
//		}
//	}
//
//	/*
//	 * Data una matricola ottengo lo studente.
//	 */
//	public Studente getStudente(int matricola) {
//
//		final String sql = "SELECT * FROM studente where matricola=?";
//
//		try {
//			Connection conn = DBConnect.getConnection();
//			PreparedStatement st = conn.prepareStatement(sql);
//			st.setInt(1, matricola);
//
//			ResultSet rs = st.executeQuery();
//
//			if (rs.next()) {
//
//				Studente studente = new Studente(rs.getInt("matricola"), rs.getString("cognome"), rs.getString("nome"),
//						rs.getString("cds"));
//				return studente;
//			}
//
//			return null;
//
//		} catch (SQLException e) {
//			// e.printStackTrace();
//			throw new RuntimeException("Errore Db");
//		}
//	}


	public List <Studente> tuttiStudenti(Map<Integer, Studente> idMapStudenti)  {
		
		String sql = "SELECT * FROM studente ";
		
		List<Studente> result = new ArrayList<Studente>();
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			
			while (res.next()) {
				Studente s = new Studente(res.getInt("matricola"), res.getString("cognome"), res.getString("nome"), res.getString("cds"));
				idMapStudenti.put(res.getInt("matricola"),s);
				result.add(s);
			}
			
			conn.close();
			return result;
			
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}

	
	public List <Corso> tuttiCorsi (Map <String, Corso> idMapCorsi) {
		
		String sql = "SELECT * FROM corso ";
		
		List <Corso> result = new ArrayList<Corso>();
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			
			while (res.next()) {
				Corso c = new Corso(res.getString("codins"), res.getInt("crediti"), res.getString("nome"), res.getInt("pd"));
				idMapCorsi.put(res.getString("codins"), c);
				result.add(c);
			}
			
			conn.close();
			return result;
			
		}  catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}
	
	public List <Arco> getArchi (Map <String, Corso> idMapCorsi, Map <Integer, Studente> idMapStudenti) {
		
		String sql = "SELECT s.matricola AS st, c.codins AS co " + 
				"FROM studente s, corso c, iscrizione i " + 
				"WHERE s.matricola = i.matricola " + 
				"AND c.codins = i.codins " + 
				"GROUP BY st, co " ;
		
		List <Arco> result = new ArrayList<Arco>();
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			
			while (res.next()) {
				Studente s = idMapStudenti.get(res.getInt("st"));
				Corso c = idMapCorsi.get(res.getString("co"));
				
				if (s != null && c != null) {
					result.add(new Arco(s, c));
				}
			}
			
			conn.close();
			return result;
			
		}  catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
		
		
	}
}
