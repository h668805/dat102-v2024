package dat102.uke89.oppg1_mengder;

import java.util.Arrays;

public class TabellMengde<T> implements MengdeADT<T> {
	private T[] tabell;
	private int antall;

	@SuppressWarnings("unchecked")
	public TabellMengde() {
		tabell = (T[]) new Object[10];
		antall = 0;
	}
	
	@Override
	public boolean erTom() {
		return antall == 0;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.deepHashCode(tabell);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		@SuppressWarnings("unchecked")
		MengdeADT<T> other = (TabellMengde<T>) obj;
		return Arrays.deepEquals(tilTabell(), other.tilTabell());
	}

	@Override
	public boolean inneholder(T element) {
		for (int i = 0; i < antall; i++) {
			if (element.equals(tabell[i])) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean erDelmengdeAv(MengdeADT<T> annenMengde) {
		for (int i = 0; i < antall; i++) {
			if (!annenMengde.inneholder(tabell[i])) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean erLik(MengdeADT<T> annenMengde) {
		return (antall != annenMengde.antallElementer()) ? false : erDelmengdeAv(annenMengde);
	}

	@Override
	public boolean erDisjunkt(MengdeADT<T> annenMengde) {
		for (int i = 0; i < antall; i++) {
			if (annenMengde.inneholder(tabell[i])) {
				return false;
			}
		}
		return true;
	}

	@Override
	public MengdeADT<T> snitt(MengdeADT<T> annenMengde) {
		MengdeADT<T> snitt = new TabellMengde<>();
		
		for (int i = 0; i < antall; i++) {
			if (annenMengde.inneholder(tabell[i])) {
				snitt.leggTil(tabell[i]);
			}
		}
		
		return snitt;
	}

	@Override
	public MengdeADT<T> union(MengdeADT<T> annenMengde) {
		MengdeADT<T> union = new TabellMengde<>();
		union.leggTilAlleFra(annenMengde);
		union.leggTilAlleFra(this);
		return union;
	}

	@Override
	public MengdeADT<T> minus(MengdeADT<T> annenMengde) {
		MengdeADT<T> minus = new TabellMengde<>();
		minus.leggTilAlleFra(this);
		T[] tab = annenMengde.tilTabell();
		for (T e : tab) {
			minus.fjern(e);
		}
		return minus;
	}

	@Override
	public void leggTil(T element) {
		if (antall >= tabell.length) {
			tabell = Arrays.copyOf(tabell, tabell.length*2);
		}
		if (!inneholder(element)) {
			tabell[antall++] = element;
		}
	}

	@Override
	public void leggTilAlleFra(MengdeADT<T> annenMengde) {
		T[] tab = annenMengde.tilTabell();
		
		for (T e : tab) {
			leggTil(e);
		}
	}

	@Override
	public T fjern(T element) {
		if (element == null || !inneholder(element)) {
			return null;
		}
		for (int i = 0; i < antall; i++) {
			if (tabell[i].equals(element)) {
				for (; i < antall-1; i++) {
					tabell[i] = tabell[i+1];
				}
				tabell[--antall] = null;
				return element;
			}
		}
		
		return null;
	}

	@Override
	public T[] tilTabell() {
		return Arrays.copyOf(tabell, antall);
	}

	@Override
	public int antallElementer() {
		return antall;
	}
}
