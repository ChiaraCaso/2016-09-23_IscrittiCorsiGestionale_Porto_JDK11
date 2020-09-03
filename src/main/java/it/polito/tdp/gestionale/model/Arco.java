package it.polito.tdp.gestionale.model;

public class Arco {
	
	Studente s ;
	Corso c ;
	
	
	public Arco(Studente s, Corso c) {
		super();
		this.s = s;
		this.c = c;
	}


	public Studente getS() {
		return s;
	}


	public void setS(Studente s) {
		this.s = s;
	}


	public Corso getC() {
		return c;
	}


	public void setC(Corso c) {
		this.c = c;
	}


	@Override
	public String toString() {
		return  s + ", " + c + "\n";
	}
	
	

	
	

}
