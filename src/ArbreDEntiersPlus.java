import java.util.HashSet;

public class ArbreDEntiersPlus extends ArbreDEntiers {

	public ArbreDEntiersPlus() {
		super();
	}

	public ArbreDEntiersPlus(ArbreDEntiersPlus sousArbreGauche, int entier, ArbreDEntiersPlus sousArbreDroit) {
		super(sousArbreGauche, entier, sousArbreDroit);
	}

	public ArbreDEntiersPlus(int entier) {
		super(new ArbreDEntiersPlus(), entier, new ArbreDEntiersPlus());
	}

	public ArbreDEntiersPlus filsGauche() {
		return (ArbreDEntiersPlus) super.filsGauche();
	}

	public ArbreDEntiersPlus filsDroit() {
		return (ArbreDEntiersPlus) super.filsDroit();
	}

	public boolean contientExAequos() {
		return contientExAequos(new HashSet<Integer>());
	}

	private boolean contientExAequos(HashSet<Integer> ensembleDEntiers) {
		if (estVide())
			return false;
		int element = racine().entier();
		if (ensembleDEntiers.contains(element))
			return true;
		ensembleDEntiers.add(element);
		return filsGauche().contientExAequos(ensembleDEntiers) || filsDroit().contientExAequos(ensembleDEntiers);
	}
	
	// Exercices supplementaires
	public int hauteur() {
		if (estVide())
			return -1;
		if (filsDroit().estVide() && filsGauche().estVide()) {
			return 0;
		}
		return 1 + Math.max(filsGauche().hauteur(), filsDroit().hauteur());
	}

	public boolean estCompletementRempli() {
		return nombreElements() == Math.pow(2, hauteur() + 1) - 1;
	}

	private int nombreElements() {
		if (estVide())
			return 0;
		return 1 + filsDroit().nombreElements() + filsGauche().nombreElements();
	}

	public boolean estComplet() {
		if (estVide())
			return true;
		if (filsGauche().estComplet() && filsGauche().hauteur() == this.hauteur() - 1
				&& filsDroit().estCompletementRempli() && filsDroit().hauteur() == this.hauteur() - 2)
			return true;
		if (filsGauche().estCompletementRempli() && filsGauche().hauteur() == this.hauteur() - 1
				&& filsDroit().estComplet() && filsDroit().hauteur() == this.hauteur() - 1)
			return true;
		return false;
	}
}
