//Author: Haodong Wang
//this class can help to create a new pokemon. you can get pokemon HP; current catchRate; current runRate and pokemon data from this class
package model;

import java.io.Serializable;
import java.util.Random;

public abstract class Pokemon implements Serializable{
	private static final long serialVersionUID = 1L;
	protected int hp;
	protected int maxHp;
	protected int runRate;
	protected int catchRate;
	protected int round;
	protected PokemonLib pkmdata;
	protected Random rand;
	
	public abstract String getType();
	public abstract int getHp();
	public abstract int getRunRate();
	public abstract int getCatchRate();
	public abstract int getDamage();
	
	//Constructor for pokemon define
	public Pokemon(int type){
		rand=new Random();
		round=0;
		if(type==1){
			pkmdata=PokemonLib.getCommonByNum(rand.nextInt(12));
		}else if(type==2){
			pkmdata=PokemonLib.getUncommonByNum(rand.nextInt(8));
		}
		else if (type==3){
			pkmdata=PokemonLib.getRareByNum();
		}
		else
			pkmdata = PokemonLib.getSpecial();
		maxHp=getHp();
		hp=this.getHp();
		runRate=this.getRunRate();
		catchRate=this.getCatchRate();
	}
	
	//1. return "1"for not escaped
	//2. return "-1"for escaped
	//3. return "0" for caught
	//4. return "-2" for unknown input;
	public int getMessage(String str){
		if(str.compareTo("rock")==0){
			return this.useRock();
		}
		else if(str.compareTo("bait")==0){
			return this.useBait();
		}
		else if(str.compareTo("ball")==0){
			return this.useBall();
		}
		else{
			System.out.println("Message unknow!");
			return -2;
		}
	}
	
	//return "1" for fail but not escape
	//return "-1" for run away
	//return "0" for caught
	private int useBall() {
		if(round>=9){
			round++;
			return -1;
		}//reach the maxim round in one battle
		int n=rand.nextInt(100);
		if(n<this.catchRate){
			return 0;
		}
		if(rand.nextInt(100)<this.runRate){
			return -1;
		}
		round++;
		return 1;
		
	}
	
	//when trainer used a bait:
	//1. return 1 for not escape
	//2. return -1 for escaped(reach the maxim round in one battle)
	private int useBait() {
		if(round>=9){
			return -1;
		}//reach the maxim round in one battle
		this.runRate-=10;
		this.catchRate-=5;
		if(rand.nextInt(100)<runRate){
			return -1;
		}
		round++;
		return 1;
	}
	//when trainer used a rock
	//1. return 1 for not escape
	//2. return -1 for escaped(reach the maxim round in one battle)
	private int useRock() {
		if(round>=9){
			return -1;
		}//reach the maxim round in one battle
		this.runRate+=5;
		this.catchRate+=20;
		decreaseHp();
		if(rand.nextInt(100)<runRate||hp==0){
			return -1;
		}
		round++;
		return 1;
	}
	//decrease the hp when rock are used
	private void decreaseHp() {
		hp=hp-10;
	}
	public void usePotion(int n){
		hp=hp+n;
		if(hp>maxHp){
			hp=maxHp;
		}
	}
	//return the pokemon data class
	public PokemonLib getPokemonLib(){
		return pkmdata;
	}
	//return the maxHp
	public int getMaxHp(){
		return maxHp;
	}
	//return the current hp 
	public int getCurrentHp(){
		return hp;
	}
	//reutrn the current RunRate
	public int getCurrentRunRate(){
		return runRate;
	}
	//return the current catchRate
	public int getCurrentCatchRate(){
		return catchRate;
	}
	
}
