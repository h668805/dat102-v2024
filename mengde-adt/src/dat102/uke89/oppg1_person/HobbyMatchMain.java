package dat102.uke89.oppg1_person;

public class HobbyMatchMain {
	public static void main(String[] args) {
		Person arne = new Person("Arne", "TikTok", "sykling", "venner", "data");
		Person ivan = new Person("Ivan", "CSGO", "sykling", "TikTok", "data");
		Person ole = new Person("Ole", "jakt", "sykling", "CSGO", "biler");
		
		System.out.println(match(arne, ivan));
		System.out.println(match(ivan, arne));
		System.out.println(match(ole, arne));
		System.out.println(match(ivan, ole));
		System.out.println(match(arne, arne));
		
	}

	static double match(Person a, Person b) {
		return a.snitt(b) - (a.minus(b) + b.minus(a)) / (double)(a.antallHobbyer() + b.antallHobbyer());
	}
}
