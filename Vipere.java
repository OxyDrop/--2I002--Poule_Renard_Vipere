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
public class Vipere extends Animal{  //Methode heritée de Animal
	private static int cpt = 1;
	private final int id;
	
	public Vipere(int x, int y, String nom){
		super(x,y,nom);
		id = cpt;
		cpt++;
	}@Override
	public String toString(){
		return String.format("v%02d", id);
	}
	@Override
	public void afficher(){
		System.out.println("\t"+super.GetX()+" "+super.GetY()+" "+super.getNom());
	}@Override
	public String GetType(){
		return "Vipere";
	}
	@Override
	public void move(Animal a){
		if(a!=null){
			if("Poule".equals(a.GetType())){ //Prédateur
				this.move((int) Math.signum(-GetX() + a.GetX() + Faune.TAILLE) % Faune.TAILLE, (int) Math.signum(-GetY() + a.GetY() + Faune.TAILLE)%Faune.TAILLE);
			}else if(proie(a)){//Proie
				 this.move((int) Math.signum(GetX() - a.GetX() + Faune.TAILLE)%Faune.TAILLE, (int) Math.signum(GetY() - a.GetY()+Faune.TAILLE)%Faune.TAILLE);
			}
		}
	}
	@Override
	public boolean proie(Animal a){//Proie
		return "Renard".equals(a.GetType());
	}
}
