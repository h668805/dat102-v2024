package dat102.uke89.oppg1_mengder;

public class LinearNode<T> {
	public T data;
	public LinearNode<T> neste;

	public LinearNode() {
		data = null;
		neste = null;
	}

	public LinearNode(T data) {
		this.data = data;
		neste = null;
	}
	
	public LinearNode(T data, LinearNode<T> neste) {
		this.data = data;
		this.neste = neste;
	}
	
	public String toString() {
		if (neste == null) { //Basistilfelle
			return "[ " + data + " | null ] ";
		} else {
			//NB! Rekursivt kall til toString() her!
			return "[ " + data + " | •-]--> " + neste.toString(); 
		}
	}
}