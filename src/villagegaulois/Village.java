package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;


public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private Marche marche;
	
	

	public class VillageSansChefException extends Exception {
	    public VillageSansChefException(String message) {
	        super(message);
	    }
	}

	
	

	public Village(String nom, int nbVillageoisMaximum,int nbEtals) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		marche = new Marche(nbEtals);
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
					+ " vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	
	
	
	
	
	public String installerVendeur(Gaulois vendeur, String produit, int nbProduit) {
	    int indice = marche.trouverEtalLibre();
	    StringBuilder chaine = new StringBuilder();
	    chaine.append(vendeur.getNom() + " cherche un endroit pour vendre " + nbProduit + " " + produit + ".\n");
	    if (indice < 0) {
	        chaine.append(vendeur.getNom() + " n'a pas trouvé d'endroit pour vendre.\n");
	    } else {
	        marche.utiliserEtal(indice, vendeur, produit, nbProduit);
	        chaine.append("Le vendeur " + vendeur.getNom() + " vend des " + produit + " à l'étal n° " + (indice + 1) + ".\n");
	    }
	    return chaine.toString();
	}
	
	
	public String rechercherVendeursProduit(String produit) {
	    StringBuilder chaine = new StringBuilder();
	    Etal[] etals = marche.trouverEtals(produit);
	    int nbVendeurs = 0;
	    for (Etal etal : etals) {
	        if (etal != null && etal.isEtalOccupe()) {
	            nbVendeurs++;
	        }
	    }
	    if (nbVendeurs == 0) {
	        chaine.append("Il n'y a pas de vendeur qui propose des " + produit + " au marché.\n");
	    } else if (nbVendeurs == 1) {
	        for (Etal etal : etals) {
	            if (etal != null && etal.isEtalOccupe()) {
	                chaine.append("Seul le vendeur " + etal.getVendeur().getNom() + " propose des " + produit + " au marché.\n");
	            }
	        }
	    } else {
	        chaine.append("Les vendeurs qui proposent des " + produit + " sont :\n");
	        for (Etal etal : etals) {
	            if (etal != null && etal.isEtalOccupe()) {
	                chaine.append("- " + etal.getVendeur().getNom() + "\n");
	            }
	        }
	    }
	    return chaine.toString();
	}


	
	
	public Etal rechercherEtal(Gaulois vendeur) {
	    return marche.trouverVendeur(vendeur);
	}
	
	
	public String partirVendeur(Gaulois vendeur) {
	    Etal etal = marche.trouverVendeur(vendeur);
	    if (etal != null) {
	        return etal.libererEtal();
	    } else {
	        return vendeur.getNom() + " n'est pas trouvé sur le marché.\n";
	    }
	}

	
	public String afficherMarche() {
	    StringBuilder chaine = new StringBuilder();
	    chaine.append("Le marché du village \"" + nom + "\" possède plusieurs étals :\n");
	    for (Etal etal : marche.etales) {
	        if (etal.isEtalOccupe()) {
	            chaine.append(etal.afficherEtal());
	        }
	    }
	    int nbEtalsLibres = 0;
	    for (Etal etal : marche.etales) {
	        if (!etal.isEtalOccupe()) {
	            nbEtalsLibres++;
	        }
	    }
	    chaine.append("Il reste " + nbEtalsLibres + " étals non utilisés dans le marché.\n");
	    return chaine.toString();
	}




	
	////////
	
	
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
				System.out.println("Il reste " + v + "  tals non utilis s dans le march .\n");
				
			}
			return null;
		}
	}	
		
}