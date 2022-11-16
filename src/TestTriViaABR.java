
public class TestTriViaABR {

	public static void main(String[] args) {
		System.out.println("***************************");
		System.out.println("Test de la classe TriViaABR");
		System.out.println("***************************");
		TriViaABR tri = new TriViaABR();
		String[] t = { "antoine","victor","martin","aurelie","adrien", "adeline" ,"marie","mathieu" ,"amelie", "anouk","vanessa", "aymeric" ,"amandine"};
		String[] tTriee = tri.trier(t);
		String triToStringSol = " adeline adrien amandine amelie anouk antoine aurelie aymeric marie martin mathieu vanessa victor";

		String[] tSol = {"adeline", "adrien" ,"amandine", "amelie", "anouk", "antoine" ,"aurelie" ,"aymeric","marie","martin","mathieu","vanessa","victor"};
		if(!sontIdentiques(tTriee, tSol)){

			System.out.println("La table testee est ");
			afficher(t);
			System.out.println("La table triee est ");
			afficher(tSol);
			System.out.println("La table renvoyee par votre tri est ");
			afficher(tTriee);
		}else{
			System.out.println("Le test propose a reussi");
		}


	}	

	public static void afficher(String[] table) {
		String resultat = "";
		for (int i = 0; i < table.length; i++) {
			resultat += " "+table[i];
		}
		System.out.println(resultat);
	}

	private static boolean sontIdentiques(String[]table1,String[]table2){
		if(table1.length!=table2.length)return false;

		for (int i = 0; i < table1.length; i++) {
			if(!table1[i].equals(table2[i]))return false;
		}

		return true;
	}

}
