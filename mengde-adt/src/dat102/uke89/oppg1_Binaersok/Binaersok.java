package dat102.uke89.oppg1_Binaersok;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Binaersok {
	public static void main(String[] args) {
		int antElement = 100000;
		int antSok = 10000;
		int antall = 0;

		Integer[] tab = new Integer[antElement];
		Set<Integer> set = new HashSet<>();
		int[] tabsok = new int[antSok];

		int tall = 376; // Her kan vi bruke eit vilkårleg tal

		for (int i = 0; i < antElement; i++) {
			// legg tall til i HashSet og tabell
			tab[i] = tall;
			set.add(tall);
			tall = (tall + 45713) % 1000000; // Sjå nedanfor om 45713
		}

		Random rand = new Random();
		for (int i = 0; i < tabsok.length; i++) {
			tabsok[i] = rand.nextInt(1000000);
		}

		long start = System.currentTimeMillis();
		Arrays.sort(tab);
		for (int i : tabsok) {
			int index = Arrays.binarySearch(tab, i);
			if (index >= 0) {
				antall++;
			}
		}
		System.out.println("Binært søk: [" + antall + "] " + (System.currentTimeMillis() - start) + "ms.");

		antall = 0;
		start = System.currentTimeMillis();

		for (int i : tabsok) {
			for (Integer e : set) {
				if (e == i) {
					antall++;
					break;
				}
			}
		}
		System.out.println("Linjært søk: [" + antall + "] " + (System.currentTimeMillis() - start) + "ms.");

		tall = 4;
		Integer[] test = new Integer[10];
		for (int i = 0; i < test.length; i++) {
			test[i] = tall;
			tall = (tall + 45713) % 10; // Sjå nedanfor om 45713
		}

		System.out.println(Arrays.toString(test));
	}
}