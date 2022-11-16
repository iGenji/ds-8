import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class ArbreDEntiers implements Iterable<Integer> {
	private NoeudEntier racine;
	private int taille;

	public ArbreDEntiers() {
		this.racine = null;
		this.taille = 0;
	}

	public ArbreDEntiers(int entier) {
		this.racine = new NoeudEntier(entier);
		this.taille = 1;
	}

	public ArbreDEntiers(ArbreDEntiers sousArbreGauche, int entier, ArbreDEntiers sousArbreDroit) {
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

	public ArbreDEntiers filsGauche() {
		return this.racine.sousArbreGauche;
	}

	public ArbreDEntiers filsDroit() {
		return this.racine.sousArbreDroit;
	}

	public Iterator<Integer> preIterateur() {
		return new PreIterateur(this);
	}

	public Iterator<Integer> postIterateur() {
		return new PostIterateur(this);
	}

	public Iterator<Integer> iterator() {
		return new InIterateur(this);
	}

	public Iterator<Integer> iterateurParNiveau() {
		return new NiveauIterateur(this);
	}

	public class NoeudEntier {
		private int entier;
		private ArbreDEntiers sousArbreGauche;
		private ArbreDEntiers sousArbreDroit;

		private NoeudEntier(int entier) {
			this.entier = entier;
			this.sousArbreGauche = new ArbreDEntiers();
			this.sousArbreDroit = new ArbreDEntiers();
		}

		private NoeudEntier(ArbreDEntiers g, int entier, ArbreDEntiers d) {
			this.entier = entier;
			this.sousArbreGauche = g;
			this.sousArbreDroit = d;
		}

		public int entier() {
			return entier;
		}
	}

	private class PreIterateur implements Iterator<Integer> {

		private ArrayDeque<Integer> fileDEntiers;

		public PreIterateur(ArbreDEntiers a) {
			fileDEntiers = new ArrayDeque<Integer>(taille);
			ordre(a);
		}

		private void ordre(ArbreDEntiers a) {
			if (a.estVide())
				return;
			fileDEntiers.addLast(a.racine.entier);
			ordre(a.filsGauche());
			ordre(a.filsDroit());
		}

		public boolean hasNext() {
			return !fileDEntiers.isEmpty();
		}

		public Integer next() {
			if (!hasNext())
				throw new NoSuchElementException();
			return fileDEntiers.removeFirst();
		}
	}

	private class PostIterateur implements Iterator<Integer> {

		private ArrayDeque<Integer> fileDEntiers;

		public PostIterateur(ArbreDEntiers a) {
			fileDEntiers = new ArrayDeque<Integer>(taille);
			ordre(a);
		}

		private void ordre(ArbreDEntiers a) {
			if (a.estVide())
				return;
			ordre(a.filsGauche());
			ordre(a.filsDroit());
			fileDEntiers.addLast(a.racine.entier);
		}

		public boolean hasNext() {
			return !fileDEntiers.isEmpty();
		}

		public Integer next() {
			if (!hasNext())
				throw new NoSuchElementException();
			return fileDEntiers.removeFirst();
		}
	}

	private class InIterateur implements Iterator<Integer> {

		private ArrayDeque<Integer> fileDEntiers;

		public InIterateur(ArbreDEntiers a) {
			fileDEntiers = new ArrayDeque<Integer>(taille);
			ordre(a);
		}

		private void ordre(ArbreDEntiers a) {
			if (a.estVide())
				return;
			ordre(a.filsGauche());
			fileDEntiers.addLast(a.racine.entier);
			ordre(a.filsDroit());
		}

		public boolean hasNext() {
			return !fileDEntiers.isEmpty();
		}

		public Integer next() {
			if (!hasNext())
				throw new NoSuchElementException();
			return fileDEntiers.removeFirst();
		}
	}

	private class NiveauIterateur implements Iterator<Integer> {

		private ArrayDeque<NoeudEntier> fileDeNoeuds;

		public NiveauIterateur(ArbreDEntiers a) {
			fileDeNoeuds = new ArrayDeque<NoeudEntier>(taille);
			ordre(a);
		}

		private void ordre(ArbreDEntiers a) {
			if (!a.estVide())
				fileDeNoeuds.addLast(a.racine);
		}

		public boolean hasNext() {
			return !fileDeNoeuds.isEmpty();
		}

		public Integer next() {
			if (!hasNext())
				throw new NoSuchElementException();
			NoeudEntier nextNode = fileDeNoeuds.getFirst();
			if (!nextNode.sousArbreGauche.estVide())
				fileDeNoeuds.addLast(nextNode.sousArbreGauche.racine);
			if (!nextNode.sousArbreDroit.estVide())
				fileDeNoeuds.addLast(nextNode.sousArbreDroit.racine);
			return fileDeNoeuds.removeFirst().entier;
		}
	}
}
