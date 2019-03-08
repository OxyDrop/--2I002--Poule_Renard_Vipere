/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PouleRenardVipere;

/**
 *
 * @author Serero
 */
public class Faune {
	public static final int TAILLE = 30;
	private final Animal[] faune; //Tableau de la faune
	static int com = 0; //compteur
	
	public Faune(final int NBANIMAUX){ //Constructeur
		this.faune = new Animal[NBANIMAUX];
		for(int i=0 ; i<faune.length ; i++){
			if(i<NBANIMAUX/3){ //7 poules de position aléatoires
				faune[i]=new Poule((int)(Math.random()*(TAILLE)),(int)(Math.random()*(TAILLE)),"Poule"+i);
			}else if(i>=NBANIMAUX/3 && i<NBANIMAUX*2/3){ // 7 renards de positions aléatoires
				faune[i]=new Renard((int)(Math.random()*(TAILLE)),(int)(Math.random()*(TAILLE)),"Renard"+i);
			}else if(i>=NBANIMAUX*2/3){ // 7 vipères de positions aléatoires
				faune[i]=new Vipere((int)(Math.random()*(TAILLE)),(int)(Math.random()*(TAILLE)),"Vipere"+i);
			}
		}
	}
	public String terrain(){ //Retourne le terrain de jeu de dimension TAILLE*TAILLE avec ses animaux
		String[][] field = new String[TAILLE][TAILLE];
		String s = "";
		
		for (Animal a : faune)
		{
			if (a != null) 
				field[a.GetX()][a.GetY()] = a.toString();		
		} 
		for(int i=0 ; i<field.length ; i++)
		{
			for(int j=0 ; j<field[i].length ; j++)
			{
				if (field[i][j]==null) //Case vide
					field[i][j]=" . ";
				else //Compteur nombres d'animaux présents (positions non égales)
					com++;
				s+=field[i][j];
			}
			s+="\n";
		}
		System.out.println("Nombre d'animaux : "+com);
		com = 0;
		return s;
	}
	
	public int getIndiceAnimalLePlusProche(Animal requete){ //Retourne l'indice de l'animal le plus proche de l'animal requete
		Double dist_min = Double.POSITIVE_INFINITY; //Distance initiale
		int index = -1;
		for(int i=0 ; i<faune.length ; i++){
			if(faune[i]!=null && requete!=null && faune[i]!=requete && faune[i].getClass()!=requete.getClass() && requete.distance(faune[i])<dist_min){ //Recherche de la distance minimale
				dist_min=requete.distance(faune[i]);
				index = i;
				//Mise à jour de dist_min et indice
			}
		}return index;
	}
	public void etatDeLaFaune(){ //Retourne l'indice de l'animal le plus proche pour chaque animal du tableau
		for(Animal a : faune){
			if(a != null){
				System.out.println(getIndiceAnimalLePlusProche(a));
			}
		}
	}
	public Animal[] getTab(final int NBANIMAUX){ //Copie de tableau
		Animal[] copie = new Animal[NBANIMAUX];
		for( int i = 0 ; i < faune.length ; i++ ){
			copie[i] = faune[i];
		} //System.arraycopy(faune, 0, copie, 0, faune.length);
		return copie;
	}
	public void update(){ //Methode qui régie les etapes du jeu
		for(int i=0 ; i<faune.length;i++){
			if(faune[i] !=null){
				Animal proche = faune[getIndiceAnimalLePlusProche(faune[i])];//faune[getIndice...(faune[i]) : animal le plus proche de l'animal courant
				faune[i].move(proche);//Deplace l'animal selon l'indice de l'animal le plus proche de lui; appel à move(Animal a) = ^ (voir ci-dessus)
			}
		}
		for(int i = 0; i<faune.length;i++){ 
			for(int j=0;j<faune.length;j++){
				if(faune[j]!=null && faune[i]!=null){
					if(faune[i].distance(faune[j])==0 && faune[i].getClass()!=faune[j].getClass()){ //Positions identiques et classes differentes
						if(faune[i].proie(faune[j])){ //j est la proie
							faune[j]=null; 
						}else{//j est le prédateur
							faune[i]=null;
						}
					}
				}
			}
		}
	}
}