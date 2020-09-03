package it.polito.tdp.gestionale.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.gestionale.db.DidatticaDAO;

public class Model {
	
	Graph<Nodo, DefaultEdge> grafo;
	DidatticaDAO dao;
	List<Studente> studenti;
	List <Corso> corsi ;
	List <Arco> archi;
	Map<Integer, Studente> idMapStudenti;
	Map<String,Corso > idMapCorsi ;
	
	public Model () {
		this.dao = new DidatticaDAO();
		this.studenti = new ArrayList<Studente>();
		this.corsi = new ArrayList<Corso>();
		this.archi = new ArrayList<Arco>();
		this.idMapCorsi = new HashMap<String, Corso>();
		this.idMapStudenti = new HashMap<Integer, Studente>();
		
	}
	
	public void creaGrafo() {
		
		this.grafo = new SimpleGraph<Nodo, DefaultEdge>(DefaultEdge.class);
		
		this.studenti = dao.tuttiStudenti(idMapStudenti);
		this.corsi = dao.tuttiCorsi(idMapCorsi);
		
		Graphs.addAllVertices(this.grafo, this.corsi);
		Graphs.addAllVertices(this.grafo, this.studenti);
//		Graphs.addAllVertices(this.grafo, idMapCorsi.values());
//		Graphs.addAllVertices(this.grafo, idMapStudenti.values());
		
		this.archi = dao.getArchi(idMapCorsi, idMapStudenti);
		
		for (Arco a : archi) {
			
			if (this.grafo.containsVertex(a.getC()) && this.grafo.containsVertex(a.getS())) {
				grafo.addEdge(a.getC(), a.getS());
			}
		}
		
		
	}
	
	public int nVertici () {
		return this.grafo.vertexSet().size();
	}
		
	public int nArchi() {
		return this.grafo.edgeSet().size();
	}

}
