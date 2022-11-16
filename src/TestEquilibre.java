
public class TestEquilibre {
	
	public static ABRDEntiers aVide(){
		return new ABRDEntiers();
	}
	
	public static ABRDEntiers aNonEquilibre(){
		ABRDEntiers a = new ABRDEntiers(9);
		ABRDEntiers aVide = new ABRDEntiers();
		a = new ABRDEntiers(aVide, 8, a);
		a = new ABRDEntiers(aVide, 7, a);
		a = new ABRDEntiers(aVide, 6, a);
		a = new ABRDEntiers(aVide, 3, a);
		a = new ABRDEntiers(aVide, 2, a);
		return a;
	}
	
	public static ABRDEntiers aEquilibre1(){
		ABRDEntiers g = new ABRDEntiers(2);
		ABRDEntiers d = new ABRDEntiers(6);
		g = new ABRDEntiers(g, 3, d);
		ABRDEntiers g1 = new ABRDEntiers(8);
		d = new ABRDEntiers(g1,9,aVide());
		return  new ABRDEntiers(g, 7, d);	
	}
	
		
	
	public static void main(String[] args) {
			
		System.out.println("\n\ntest : equilibre()");
		System.out.println("------------------");
		ABRDEntiers aNonEquilibre = aNonEquilibre();
		ABRDEntiers aEquilibre1 = aEquilibre1();
		if(!aEquilibre1.equals(aNonEquilibre().equilibre())){
			System.out.println("test1 ko");
			System.exit(0);
		}
		
		System.out.println("Le test a reussi!");
	}
}
