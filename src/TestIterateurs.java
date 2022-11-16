import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;



public class TestIterateurs {

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

	public static void main(String[] args) {
		System.out.println("**************************************");
		System.out.println("Programme Test");
		System.out.println("**************************************");
		int choix = 0;
		do{
			System.out.println();
			System.out.println("1 -> Tester l'iterateur en pre-ordre");
			System.out.println("2 -> Tester l'iterateur en post-ordre");
			System.out.println("3 -> Tester l'iterateur en in-ordre");
			System.out.println("4 -> Tester l'iterateur par niveau");
			System.out.println();
			System.out.print("Entrez votre choix : ");

			choix = scanner.nextInt();
			switch (choix) {


			case 1:
				testIterateurPreOrdre();
				break;	
			case 2:
				testIterateurPostOrdre();
				break;	
			case 3:
				testIterateurInOrdre();
				break;	
			case 4:
				testIterateurParNiveau();
				break;	
			default:
				break;
			}
		} while ((choix > 0)&&(choix<5));
	}

	private static ArbreDEntiers arbre(){
		ArbreDEntiers g = new ArbreDEntiers(6);
		ArbreDEntiers d = new ArbreDEntiers();
		d =  new ArbreDEntiers(g,7,d);
		g = new ArbreDEntiers(3);
		ArbreDEntiers g1 = new ArbreDEntiers(g,5,d);
		g = new ArbreDEntiers(2);
		d = new ArbreDEntiers();
		d = new ArbreDEntiers(g,4,d);
		g = new ArbreDEntiers();
		d = new ArbreDEntiers(g,9,d);
		return  new ArbreDEntiers(g1, 8, d);
	}

	public static void testIterateurPreOrdre(){
		
		ArbreDEntiers a = arbre();
		Iterator<Integer> it = a.preIterateur();
		int[] solution = {8,5,3,7,6,9,4,2};
		boolean aReussi = true;
		int i = 0;
		while(it.hasNext()){
			int suivant = (Integer)it.next();
			if(suivant!=solution[i]) aReussi = false;
			i++;
			System.out.print(suivant + " ");
		}
		try{
			System.out.println(it.next());
			System.out.println("Attention, il fallait une exception de type NoSuchElementException");
			aReussi = false;
		}catch (NoSuchElementException e){
			
		}catch (Exception e){
			System.out.println("Attention, il fallait une exception de type NoSuchElementException");
			aReussi = false;
		}
		System.out.println();
		if(aReussi && i == 8){
			System.out.println("Le test a reussi!");
		}
		else {
			System.out.println("Attention, le test a echoue!");
		}
	}

	private static void testIterateurParNiveau() {
		ArbreDEntiers a = arbre();
		Iterator<Integer> it = a.iterateurParNiveau();
		int[] solution = {8,5,9,3,7,4,6,2};
		boolean aReussi = true;
		int i = 0;
		while(it.hasNext()){
			int suivant = (Integer)it.next();
			if(suivant!=solution[i]) aReussi = false;
			i++;
			System.out.print(suivant + " ");
		}
		System.out.println();
		try{
			System.out.println(it.next());
			System.out.println("Attention, il fallait une exception de type NoSuchElementException");
			aReussi = false;
		}catch (NoSuchElementException e){
			
		}catch (Exception e){
			System.out.println("Attention, il fallait une exception de type NoSuchElementException");
			aReussi = false;
		}

		if(aReussi && i == 8){
			System.out.println("Le test a reussi!");
		}
		else {
			System.out.println("Attention, le test a echoue!");
		}
	}

	private static void testIterateurInOrdre() {
		ArbreDEntiers a = arbre();
		Iterator<Integer> it = a.iterator();
		int[] solution = {3,5,6,7,8,9,2,4};
		boolean aReussi = true;
		int i = 0;
		while(it.hasNext()){
			int suivant = (Integer)it.next();
			if(suivant!=solution[i]) aReussi = false;
			i++;
			System.out.print(suivant + " ");
		}
		System.out.println();
		try{
			System.out.println(it.next());
			System.out.println("Attention, il fallait une exception de type NoSuchElementException");
			aReussi = false;
		}catch (NoSuchElementException e){
			
		}catch (Exception e){
			System.out.println("Attention, il fallait une exception de type NoSuchElementException");
			aReussi = false;
		}

		if(aReussi && i == 8){
			System.out.println("Le test a reussi!");
		}
		else {
			System.out.println("Attention, le test a echoue!");
		}

	}

	private static void testIterateurPostOrdre() {
		ArbreDEntiers a = arbre();
		Iterator<Integer> it = a.postIterateur();
		int[] solution = {3,6,7,5,2,4,9,8};
		boolean aReussi = true;
		int i = 0;
		while(it.hasNext()){
			int suivant = (Integer)it.next();
			if(suivant!=solution[i]) aReussi = false;
			i++;
			System.out.print(suivant + " ");
		}
		System.out.println();
		try{
			System.out.println(it.next());
			System.out.println("Attention, il fallait une exception de type NoSuchElementException");
			aReussi = false;
		}catch (NoSuchElementException e){
			
		}catch (Exception e){
			System.out.println("Attention, il fallait une exception de type NoSuchElementException");
			aReussi = false;
		}

		if(aReussi && i == 8){
			System.out.println("Le test a reussi!");
		}
		else {
			System.out.println("Attention, le test a echoue!");
		}
	}

}
