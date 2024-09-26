package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;


public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	
	

	public Village(String nom, int nbVillageoisMaximum) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
	}

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les lÃ©gendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	
	
	
	
	private static class Marche{
		
		private Etal[] etales;
		private int nbe = 0;
		
		
		private Marche(int nbe) {
			super();
			this.nbe =nbe;
			etales = new Etal[nbe];
		
			for(int i = 0 ; i < nbe ; i++) {
				etales[i] = new Etal();
			}
		}
		
		public void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
			etales[indiceEtal].occuperEtal( vendeur,  produit, nbProduit);//occuper etal dans le meme pasckage donc oklm
		}
		
		
		public int trouverEtalLibre() {
			for(int i = 0 ; i < nbe ; i++) {
				if (etales[i].isEtalOccupe() == false) {
					return i;
				}
			}
			return -1;
		}
		
		
		
		
		public Etal[] trouverEtals(String produit) {
			Etal[] etalp = new Etal[nbe] ;
			int j = 0 ;
			for(int i = 0 ; i < nbe ; i++) {
				if(etales[i].contientProduit(produit)) {
					etalp[j] = etales[i];
					j=j+1;
				}		
			}
			return etalp;
		}
		
		
		
		
		public Etal trouverVendeur(Gaulois gaulois) {
			for(int i = 0 ; i < nbe ; i++) {
				if(etales[i].getVendeur()==gaulois) {
					return etales[i];
				}		
			}
			return null;
			
	
		} 
		
		
		public String afficherMarche(){
			int v = 0;
			for(int i = 0 ; i < nbe ; i++) {
				if(etales[i].isEtalOccupe()==true) {
					etales[i].afficherEtal();
				}else
					v=v+1;
			}
			if (v != 0) {
				System.out.println("Il reste " + v + " étals non utilisés dans le marché.\n");
				
			}
			return null;
		}
	}	
		
}
