import java.util.ArrayDeque;
import java.util.Iterator;

public class ABRNoms implements Iterable<String>{
	
	private NoeudNom racine;
	
	public ABRNoms() {
		this.racine=null ;
	}

	public NoeudNom racine(){
		return racine;
	}
	
	public boolean estVide(){
		return  this.racine == null;
	}

	public ABRNoms filsGauche(){
		return this.racine.sousArbreGauche;	
	}
	
	public ABRNoms filsDroit(){
		return this.racine.sousArbreDroit;	
	}
	
	// A NE PAS MODIFIER!!!
	// VA SERVIR POUR LES TESTS!!!
	public String toString(){
		if(this.estVide())return "";
		if(this.filsGauche().estVide()&&this.filsDroit().estVide())return ""+this.racine.element();
		if(this.filsGauche().estVide())return ""+this.racine.element()+" "+this.filsDroit().toString();
		if(this.filsDroit().estVide())return this.filsGauche().toString()+" "+this.racine.element();
		return this.filsGauche().toString()+" "+this.racine.element()+" "+this.filsDroit().toString();
	}
	
	/**
	 * insere le nom (Apres insertion l'arbre est toujours un ABR)
	 * @param nom le nom a inserer
	 */
	public void insere(String nom){
		if(this.estVide()) {
			this.racine = new NoeudNom(nom);
			return;
		}
		if(nom.compareTo(this.racine.element())<0)this.filsGauche().insere(nom);
		else this.filsDroit().insere(nom);	
	}
	
	public Iterator<String> iterator(){
		return new InIterateur(this);
	}

	
	public class NoeudNom {
		private String nom; 
		private ABRNoms sousArbreGauche;
		private ABRNoms sousArbreDroit;

		private NoeudNom(String string){
			this.nom = string;
			this.sousArbreGauche = new ABRNoms();
			this.sousArbreDroit = new ABRNoms();
		}
		
		private NoeudNom(ABRNoms g,String nom,ABRNoms d){
			this.nom = nom;
			this.sousArbreGauche = g;
			this.sousArbreDroit = d;
		}
		
		public String element() {
			return this.nom;
		}	
	}
	
	private class InIterateur implements Iterator<String> {

		private ArrayDeque<String> fileNoms;
		
		public InIterateur(ABRNoms a){
			fileNoms = new ArrayDeque<String>();
			ordre(a);
		}
		
		private void ordre(ABRNoms a){
			if(a.estVide())return;
			ordre(a.filsGauche());
			fileNoms.addLast(a.racine().nom);
			ordre(a.filsDroit());
		}

		public boolean hasNext() {
			return !fileNoms.isEmpty();
		}

		public String next() {
				return fileNoms.removeFirst();
		}
		
	}
}
