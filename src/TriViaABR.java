public class TriViaABR {
	private ABRNoms[] tableABR;

	public TriViaABR() {
		this.tableABR = new ABRNoms[26];
		for (int i = 0; i < 26; i++) {
			this.tableABR[i] = new ABRNoms();
		}
	}

	/**
	 * tri des noms recus en parametre
	 * 
	 * @param noms table a trier
	 * @return table contenant les noms tries
	 */
	public String[] trier(String[] noms) {
		repartirDonnees(noms);
		return remplirTableTriee(noms.length);
	}

	/**
	 * 1ere etape du tri : repartition des noms dans les ABR
	 * 
	 * @param noms
	 *            table a trier
	 */
	private void repartirDonnees(String[] noms) {
		for (int i = 0; i < noms.length; i++) {
			tableABR[(int) noms[i].charAt(0) - 'a'].insere(noms[i]);
		}
	}

	/**
	 * 2eme etape du tri :
	 * 
	 * @param taille nombre de noms
	 * @return table contenant les noms tries
	 */
	private String[] remplirTableTriee(int taille) {
		String[] tableTriee = new String[taille];
		int j = 0;
		for (int i = 0; i < 26; i++) {
			for (String string : tableABR[i]) { // iterator in-ordre par defaut
				tableTriee[j] = string;
				j++;
			}
		}
		return tableTriee;
	}

	public String toString() {
		String aRenvoyer = "";
		for (int i = 0; i < 26; i++) {
			if (!tableABR[i].estVide()) {
				aRenvoyer += " " + tableABR[i].toString();
			}
		}
		return aRenvoyer;
	}
}
