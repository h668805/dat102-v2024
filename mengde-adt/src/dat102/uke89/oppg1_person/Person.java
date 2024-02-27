package dat102.uke89.oppg1_person;

import dat102.uke89.oppg1_mengder.JavaSetToMengde;
import dat102.uke89.oppg1_mengder.MengdeADT;

public class Person {
	private String navn;
	private MengdeADT<String> hobbyer;

	public Person(String navn, String... hobbyer) {
		this.navn = navn;
		this.hobbyer = new JavaSetToMengde<>();
		for (String s : hobbyer) {
			this.hobbyer.leggTil(s);
		}
	}

	public String getNavn() {
		return navn;
	}

	public void setNavn(String navn) {
		this.navn = navn;
	}

	public MengdeADT<String> getHobbyer() {
		return hobbyer;
	}

	public void setHobbyer(MengdeADT<String> hobbyer) {
		this.hobbyer = hobbyer;
	}
	
	public double snitt(Person b) {
		return hobbyer.snitt(b.getHobbyer()).antallElementer();
	}
	
	public double minus(Person b) {
		return hobbyer.minus(b.getHobbyer()).antallElementer();
	}
	
	public int antallHobbyer() {
		return hobbyer.antallElementer();
	}
}
