package dat102.uke89.oppg1_mengder;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class JavaSetToMengde<T> implements MengdeADT<T> {
	private Set<T> set;

	public JavaSetToMengde() {
		set = new HashSet<>();
	}

	@Override
	public boolean erTom() {
		return set.size() == 0;
	}

	@Override
	public boolean inneholder(T element) {
		Iterator<T> it = set.iterator();
		while (it.hasNext()) {
			if (element.equals(it.next())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean erDelmengdeAv(MengdeADT<T> annenMengde) {
		Iterator<T> it = set.iterator();
		while (it.hasNext()) {
			if (!annenMengde.inneholder(it.next())) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean erLik(MengdeADT<T> annenMengde) {
		return (set.size() != annenMengde.antallElementer()) ? false : erDelmengdeAv(annenMengde);
	}

	@Override
	public boolean erDisjunkt(MengdeADT<T> annenMengde) {
		Iterator<T> it = set.iterator();
		while (it.hasNext()) {
			if (annenMengde.inneholder(it.next())) {
				return false;
			}
		}
		return true;
	}

	@Override
	public MengdeADT<T> snitt(MengdeADT<T> annenMengde) {
		MengdeADT<T> snitt = new JavaSetToMengde<>();
		Iterator<T> it = set.iterator();
		while (it.hasNext()) {
			T e = it.next();
			if (annenMengde.inneholder(e)) {
				snitt.leggTil(e);
			}
		}
		return snitt;
	}

	@Override
	public MengdeADT<T> union(MengdeADT<T> annenMengde) {
		MengdeADT<T> union = new JavaSetToMengde<>();
		union.leggTilAlleFra(this);
		union.leggTilAlleFra(annenMengde);
		return union;
	}

	@Override
	public MengdeADT<T> minus(MengdeADT<T> annenMengde) {
		MengdeADT<T> minus = new JavaSetToMengde<>();
		minus.leggTilAlleFra(this);
		T[] tab = annenMengde.tilTabell();
		for (T e : tab) {
			minus.fjern(e);
		}
		return minus;
	}

	@Override
	public void leggTil(T element) {
		if (!inneholder(element)) {
			set.add(element);
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
		if (inneholder(element)) {
			set.remove(element);
			return element;
		}
		return null;
	}

	@Override
	public T[] tilTabell() {
		Iterator<T> it = set.iterator();
		@SuppressWarnings("unchecked")
		T[] tab = (T[]) new Object[set.size()];
		for (int i = 0; i < set.size() && it.hasNext(); i++) {
			tab[i] = it.next();
		}
		return tab;
	}

	@Override
	public int antallElementer() {
		return set.size();
	}

}