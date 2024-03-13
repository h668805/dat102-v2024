package dat102.uke89.oppg1_mengder;

public class LenketMengde<T> implements MengdeADT<T> {
	private LinearNode<T> start;
	private int antall;

	public LenketMengde() {
		start = new LinearNode<>();
		antall = 0;
	}

	@Override
	public boolean erTom() {
		return antall == 0;
	}

	@Override
	public boolean inneholder(T element) {
		LinearNode<T> temp = start;
		while (temp.data != null) {
			if (element.equals(temp.data)) {
				return true;
			}
			temp = temp.neste;
		}
		return false;
	}

	@Override
	public boolean erDelmengdeAv(MengdeADT<T> annenMengde) {
		LinearNode<T> temp = start;
		while (temp.data != null) {
			if (!annenMengde.inneholder(temp.data)) {
				return false;
			}
			temp = temp.neste;
		}
		return true;
	}

	@Override
	public boolean erLik(MengdeADT<T> annenMengde) {
		return (antall != annenMengde.antallElementer()) ? false : erDelmengdeAv(annenMengde);
	}

	@Override
	public boolean erDisjunkt(MengdeADT<T> annenMengde) {
		LinearNode<T> temp = start;
		while (temp.data != null) {
			if (annenMengde.inneholder(temp.data)) {
				return false;
			}
			temp = temp.neste;
		}
		return true;
	}

	@Override
	public MengdeADT<T> snitt(MengdeADT<T> annenMengde) {
		MengdeADT<T> snitt = new LenketMengde<>();
		LinearNode<T> temp = start;

		while (temp.data != null) {
			if (annenMengde.inneholder(temp.data)) {
				snitt.leggTil(temp.data);
			}
			temp = temp.neste;
		}

		return snitt;
	}

	@Override
	public MengdeADT<T> union(MengdeADT<T> annenMengde) {
		MengdeADT<T> union = new LenketMengde<>();
		union.leggTilAlleFra(annenMengde);
		union.leggTilAlleFra(this);
		return union;
	}

	@Override
	public MengdeADT<T> minus(MengdeADT<T> annenMengde) {
		MengdeADT<T> minus = new LenketMengde<>();
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
			LinearNode<T> temp = new LinearNode<>();
			temp.data = element;
			temp.neste = start;
			start = temp;
			antall++;
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

		if (element.equals(start.data)) {
			start = start.neste;
			antall--;
			return element;
		}

		LinearNode<T> temp = start;
		while (temp.neste.data != null) {
			if (element.equals(temp.neste.data)) {
				temp.neste = temp.neste.neste;
				antall--;
				return element;
			}
			temp = temp.neste;
		}

		return null;
	}

	@Override
	public T[] tilTabell() {
		@SuppressWarnings("unchecked")
		T[] tab = (T[]) new Object[antall];
		LinearNode<T> temp = start;
		for (int i = antall - 1; temp.data != null && 0 <= i; i--) {
			tab[i] = temp.data;
			temp = temp.neste;
		}
		return tab;
	}

	@Override
	public int antallElementer() {
		return antall;
	}
}
