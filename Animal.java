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
public abstract class Animal {
	private int x,y;
	private final String nom;
	protected Animal(int x, int y, String nom){
		this.x=x;this.y=y;this.nom=nom;
	}
	public int GetX(){
		return x;
	}
	public int GetY(){
		return y;
	}
	public String getNom(){
		return nom;
	}
	public double distance(Animal a){
		return (Math.sqrt(Math.pow(this.x-a.x,2)+Math.pow(this.y-a.y,2)));
	}
	public void move(int i, int j){
		x = ( x + i + Faune.TAILLE ) % Faune.TAILLE;
		y = (y + j + Faune.TAILLE ) % Faune.TAILLE;
	}public abstract String GetType();
	public abstract void move(Animal a);
	public abstract boolean proie(Animal a);
	public abstract void afficher();
}
