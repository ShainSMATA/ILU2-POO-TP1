package histoire;

import villagegaulois.*;
import personnages.*;

public class ScenarioCasDegrade {

	public static void main(String[] args) {
		Etal etal = new Etal();
		Gaulois asterix = new Gaulois("Asterix", 10);
		etal.acheterProduit(5, asterix);
		System.out.println("Fin du test");
		}
	
}