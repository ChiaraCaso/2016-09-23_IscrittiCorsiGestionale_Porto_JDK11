package it.polito.tdp.gestionale.db;

import java.util.HashMap;
import java.util.Map;

import it.polito.tdp.gestionale.model.Corso;
import it.polito.tdp.gestionale.model.Studente;

public class TestDAO {

	// Test main
	public static void main(String[] args) {
		
		DidatticaDAO dd = new DidatticaDAO();
//		System.out.println(dd.getCorso("01JEFPG"));
//		System.out.println(dd.getStudente(134806));
		
		Map <String, Corso> idMapCorsi = new HashMap<String, Corso>();
		Map <Integer, Studente> idMapStudenti = new HashMap<Integer, Studente>();
		System.out.println(dd.tuttiCorsi(idMapCorsi));
		System.out.println(dd.tuttiStudenti(idMapStudenti));
		System.out.println(dd.getArchi(idMapCorsi, idMapStudenti));
		
	}
}
