import java.util.ArrayDeque;
import java.util.Iterator;


public class ABRDEntiers implements Iterable<Integer> {
	private NoeudEntier racine;
	private int taille;

	public ABRDEntiers() {
		this.racine = null;
		this.taille = 0;
	}

	public ABRDEntiers(int entier) {
		this.racine = new NoeudEntier(entier);
		this.taille = 1;
	}

	public ABRDEntiers(ABRDEntiers sousArbreGauche, int entier, ABRDEntiers sousArbreDroit) {
		if (sousArbreGauche == null)
			throw new IllegalArgumentException();
		if (sousArbreDroit == null)
			throw new IllegalArgumentException();
		if (!sousArbreGauche.estVide()) {
			if (sousArbreGauche.max() >= entier)
				throw new IllegalArgumentException();
		}
		if (!sousArbreDroit.estVide()) {
			if (sousArbreDroit.min() < entier)
				throw new IllegalArgumentException();
		}
		this.racine = new NoeudEntier(sousArbreGauche, entier, sousArbreDroit);
		this.taille = 1 + sousArbreGauche.taille + sousArbreDroit.taille;
	}

	public NoeudEntier racine() {
		return racine;
	}

	public boolean estVide() {
		return this.racine == null;
	}

	public int taille() {
		return taille;
	}

	public ABRDEntiers filsGauche() {
		return this.racine.sousArbreGauche;

	}

	public ABRDEntiers filsDroit() {
		return this.racine.sousArbreDroit;
	}

	public Iterator<Integer> iterator() {
		return new InIterateur(this);
	}

	// -1 si vide
	public int min() {
		if (estVide())
			return -1;
		if (filsGauche().estVide())
			return racine.entier;
		return filsGauche().min();
	}

	// -1 si vide
	public int max() {
		if (estVide())
			return -1;
		if (filsDroit().estVide())
			return racine.entier;
		return filsDroit().max();
	}

	public boolean equals(ABRDEntiers a) {
		if (this.estVide() && a.estVide())
			return true;
		if (this.estVide() || a.estVide())
			return false;
		if (this.racine().entier() != a.racine().entier())
			return false;
		return this.filsGauche().equals(a.filsGauche()) && this.filsDroit().equals(a.filsDroit());
	}

	public ABRDEntiers equilibre() {
		Iterator<Integer> it = iterator();
		return construit(taille(), it);
	}

	private static ABRDEntiers construit(int n, Iterator<Integer> it) {
		if (!it.hasNext() || n == 0)
			return new ABRDEntiers();
		return new ABRDEntiers(construit(n / 2, it), it.next(), construit(n - (n / 2) - 1, it));
	}
	
	// Defis
	public int[] toArray(){
		return new TableDEntiers(this).table;
	}
	
	//Test du defis
	public void insere(int entier) {
		taille++;
		if (estVide()) {
			this.racine = new NoeudEntier(entier);
			return;
		}
		if (racine().entier > entier) {
			filsGauche().insere(entier);
		} else { 
			filsDroit().insere(entier);
		}
	}

	public class NoeudEntier {
		private int entier;
		private ABRDEntiers sousArbreGauche;
		private ABRDEntiers sousArbreDroit;

		private NoeudEntier(int entier) {
			this.entier = entier;
			this.sousArbreGauche = new ABRDEntiers();
			this.sousArbreDroit = new ABRDEntiers();
		}

		private NoeudEntier(ABRDEntiers g, int entier, ABRDEntiers d) {
			this.entier = entier;
			this.sousArbreGauche = g;
			this.sousArbreDroit = d;
		}

		public int entier() {
			return entier;
		}
	}

	private class InIterateur implements Iterator<Integer> {

		private ArrayDeque<Integer> fileDEntiers;

		public InIterateur(ABRDEntiers a) {
			fileDEntiers = new ArrayDeque<Integer>(taille);
			ordre(a);
		}

		private void ordre(ABRDEntiers a) {
			if (a.estVide())
				return;
			ordre(a.filsGauche());
			fileDEntiers.addLast(a.racine().entier());
			ordre(a.filsDroit());
		}

		public boolean hasNext() {
			return !fileDEntiers.isEmpty();
		}

		public Integer next() {
			return fileDEntiers.removeFirst();
		}
	}
	
	// defis
	private class TableDEntiers {
		private int[] table;
		private int indice;

		public TableDEntiers(ABRDEntiers a) {
			table = new int[taille];
			indice = 0;
			remplir(a);
		}

		private void remplir(ABRDEntiers a) {
			if (a.estVide())
				return;
			remplir(a.filsGauche());
			table[indice] = a.racine.entier;
			indice++;
			remplir(a.filsDroit());
		}
	}
}
