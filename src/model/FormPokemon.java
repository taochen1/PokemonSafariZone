package model;

import java.util.Random;
//Author: HaodongWang
//use this class to form a new pokemon for the trainer
public class FormPokemon {
	private Pokemon pkm;
	
	public FormPokemon(){
		this.newPokemon();
	}
	//this class is to create a pokemon randomly
	public Pokemon newPokemon(){
		Random rand=new Random();
		int type=rand.nextInt(10);
		if(type>=0 &&type<=5){
			pkm=new Common();
		}
		else if(type>=6 &&type<=8){
			pkm=new Uncommon();
		}
		else{
			pkm=new Rare();
		}
		return pkm;
	}
	//this class is to get the current pokemon
	public Pokemon getCurrentPokemon(){
		return pkm;
	}
	
}
