package dat102.f02.tabellbag;

import java.util.Arrays;

/**
 * En implementasjon av BagADT
 * 
 * @author Lars-Petter Helland
 */
public class TabellBag<T> implements BagADT<T> {

	private static final int DEFAULT_KAPASITET = 10;

	private T[] tabell;
	private int antall;

	/************************************************************/

	public TabellBag() {
		this(DEFAULT_KAPASITET);
	}

	@SuppressWarnings("unchecked")
	public TabellBag(int kapasitet) {
		tabell = (T[]) new Object[kapasitet];
		antall = 0;
	}

	@Override
	public int getCurrentSize() {
		return antall;
	}

	@Override
	public boolean isEmpty() {
		return antall == 0;
	}

	@Override
	public boolean add(T newEntry) {
		if (antall >= tabell.length) {
			tabell = Arrays.copyOf(tabell, tabell.length * 2);
		}
		if (newEntry != null) {
			tabell[antall++] = newEntry;
			return true;
		}
		return false;
	}

	@Override
	public T remove() {
		T t = tabell[--antall];
		tabell[antall] = null;
		return t;
	}

	@Override
	public boolean remove(T anEntry) {
		for (int i = 0; i < antall; i++) {
			if (anEntry.equals(tabell[i])) {
				for (; i < antall - 1; i++) {
					tabell[i] = tabell[i + 1];
				}
				tabell[--antall] = null;
				return true;
			}
		}
		return false;
	}

	@Override
	public void clear() {
		for (int i = 0; i < antall; i++) {
			tabell[i] = null;
		}
		antall = 0;
	}

	@Override
	public int getFrequencyOf(T anEntry) {
		if (anEntry == null)
			return 0;
		int frekvens = 0;
		for (int i = 0; i < antall; i++) {
			frekvens += anEntry.equals(tabell[i]) ? 1 : 0;
		}
		return frekvens;
	}

	@Override
	public boolean contains(T anEntry) {
		for (int i = 0; i < antall; i++) {
			if (anEntry.equals(tabell[i]))
				return true;
		}
		return false;
	}

	@Override
	public T[] toArray() {
		return Arrays.copyOf(tabell, antall);
	}

	/************************************************************/

}
