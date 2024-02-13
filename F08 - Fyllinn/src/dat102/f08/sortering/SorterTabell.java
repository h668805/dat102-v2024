package dat102.f08.sortering;

import java.util.Random;

public class SorterTabell {
	/**
	 * Sorter dei første n elmenta i tabellen i ikkje stigande ordning (det betyr
	 * stigande ordning om der ikkje er like element).
	 * 
	 * @param <T> Datatype på element som skal sorterast. Må vere ein referansetype
	 * @param a   Tabell som skal sorterast
	 * @param n   Angir kor mange (frå starten) element som skal sorterast >
	 */
	public static <T extends Comparable<? super T>> void utvalgssortering(T[] a, int n) {

		for (int i = 0; i < n; i++) {

			// finne indeks for minste element i usortert del
			T min = a[i];
			int minIndeks = i;

			for (int j = i + 1; j < n; j++) {
				if (a[j].compareTo(min) < 0) {
					min = a[j];
					minIndeks = j;
				}
			}

			swap(a, i, minIndeks);
		}
	}

	/**
	 * Oftast skal heile tabellen sorterast slik vi overlastar denne metoden slik at
	 * dei som brukar den slepp skrive antal (a.length)
	 * 
	 * @param <T>
	 * @param a
	 */
	public static <T extends Comparable<? super T>> void utvalgssortering(T[] a) {
		utvalgssortering(a, a.length);
	}

	public static void swap(Object[] a, int i, int j) {
		Object temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	public static <T extends Comparable<? super T>> void sorterVedInnsetting(T[] a) {
		sorterVedInnsetting(a, 0, a.length - 1);
	}

	public static <T extends Comparable<? super T>> void sorterVedInnsetting(T[] a, int forste, int siste) {
		for (int i = forste + 1; i <= siste; i++) {

			T temp = a[i];
			int j = i - 1;

			// finn rett plass
			while (j >= forste && temp.compareTo(a[j]) < 0) {
				a[j + 1] = a[j];
				j--;
			}

			a[j + 1] = temp;
		}
	}

	public static <T extends Comparable<? super T>> void kvikkSortering(T[] a, int forste, int siste) {
		@SuppressWarnings("unchecked")
		T[] temp = (T[]) new Comparable<?>[a.length];
		kvikkSortering(a, temp, forste, siste);
	}

	public static <T extends Comparable<? super T>> void kvikkSortering(T[] a, T[] temp, int forste, int siste) {
		if (forste < siste) {
			kvikk(a, temp, forste, siste);

			kvikkSortering(a, forste, 1);
			kvikkSortering(a, 1, siste);
		}
	}

	public static <T extends Comparable<? super T>> void kvikk(T[] a, T[] temp, int forste, int siste) {
	}

	public static <T extends Comparable<? super T>> void fletteSortering(T[] a, int forste, int siste) {
		@SuppressWarnings("unchecked")
		T[] temp = (T[]) new Comparable<?>[a.length];
		int midten = (forste + siste) / 2;
		fletteSortering(a, temp, forste, midten, siste);
	}

	public static <T extends Comparable<? super T>> void fletteSortering(T[] a, T[] temp, int forste, int midten,
			int siste) {
		if (forste < siste) {
			fletteSortering(a, forste, midten);
			fletteSortering(a, midten + 1, siste);

			flette(a, temp, forste, midten, siste);
		}
	}

	private static <T extends Comparable<? super T>> void flette(T[] a, T[] temp, int forste, int midten, int siste) {
		int j = forste;
		int k = midten + 1;

		for (int i = forste; i <= siste; i++) {
			if (j >= midten) {
				temp[i] = a[k++];
			} else if (k >= siste) {
				temp[i] = a[j++];
			} else if (a[j].compareTo(a[k]) < 0) {
				temp[i] = a[j++];
			} else {
				temp[i] = a[k++];
			}
		}
	}
}