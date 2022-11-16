import java.util.Scanner;

public class TestContientExAequos {
	
	private static Scanner scanner = new Scanner(System.in);
	
	/**
	 * Cette methode verifie qu'un resultat attendu est bien un resultat obtenu.
	 * 
	 * @param messageErreur message a afficher en cas de probleme
	 * @param attendu la valeur qu'on s'attendait a recevoir
	 * @param recu la valeur qu'on a reçu en realite
	 */
	
	private static void assertEquals(String messageErreur, Object attendu, Object recu) {
		if (attendu==null) {
			if (recu!=null) {
				System.out.println(messageErreur+". Attendu="+attendu+" reçu="+recu);
				System.exit(0);
			}
		} else if (!attendu.equals(recu)) {
			System.out.println(messageErreur+". Attendu="+attendu+" reçu="+recu);
			System.exit(0);			
		}
	}
	
	public static ArbreDEntiersPlus arbreVide(){
		return new ArbreDEntiersPlus();
	}
	
	public static ArbreDEntiersPlus arbreEnonce(){
		ArbreDEntiersPlus ag = new ArbreDEntiersPlus(11);
		ArbreDEntiersPlus ad = new ArbreDEntiersPlus(17);
		ad = new ArbreDEntiersPlus(ag,15,ad);
		ag = new ArbreDEntiersPlus(2);	
		ArbreDEntiersPlus a = new ArbreDEntiersPlus(ag,7,ad);
		ad = new ArbreDEntiersPlus(11);
		ag = new ArbreDEntiersPlus(arbreVide(),8,ad);
		ad = new ArbreDEntiersPlus(ag,6,arbreVide());
		a = new ArbreDEntiersPlus(a,10,ad);
		return a;
	}
	
	public static ArbreDEntiersPlus arbreEnonceAvecUnNegatif(){
		ArbreDEntiersPlus ag = new ArbreDEntiersPlus(-11);
		ArbreDEntiersPlus ad = new ArbreDEntiersPlus(17);
		ad = new ArbreDEntiersPlus(ag,15,ad);
		ag = new ArbreDEntiersPlus(2);	
		ArbreDEntiersPlus a = new ArbreDEntiersPlus(ag,7,ad);
		ad = new ArbreDEntiersPlus(11);
		ag = new ArbreDEntiersPlus(arbreVide(),8,ad);
		ad = new ArbreDEntiersPlus(ag,6,arbreVide());
		a = new ArbreDEntiersPlus(a,10,ad);
		return a;
	}
	
	
	public static void main(String[] args) {
		System.out.println("***************************************");
		System.out.println("Programme Test methode contientExAEquos");
		System.out.println("***************************************");
		testContientExAequos();
	}



	private static void testContientExAequos() {
		ArbreDEntiersPlus a;
		System.out.println("test 1 : arbre vide");
		a = arbreVide();
		assertEquals("contient ex-aequos ko ",false,a.contientExAequos());
		System.out.println("test 2 : arbre propose");
		a = arbreEnonce();
		assertEquals("contient ex-aequos ko ",true,a.contientExAequos());
		System.out.println("test 3 : arbre sans ex-aequo");
		a = arbreEnonceAvecUnNegatif();
		assertEquals("contient ex-aequos ko ",false,a.contientExAequos());	
		System.out.println("Tous les tests ont reussi");
		System.out.println();	
	}

}
