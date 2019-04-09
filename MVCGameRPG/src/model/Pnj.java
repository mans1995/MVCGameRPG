package model;

import java.util.Random;

public class Pnj extends DynamicObject {

	private static final long serialVersionUID = 6335143914748900251L;
	
	public Pnj(int ID, int frameID,int caseX, int caseY, boolean solid, GameData gameData, int speed, int lvl) {
		super(ID, frameID, caseX, caseY, solid, gameData, speed, lvl);
	}
	
	public boolean findPlayer(){
		boolean cond = false ;
		for(int i = -4 ; i <= 4 ; i++)
			for(int j = -4 ; j <= 4 ; j++){
				if(this.caseX+i == this.board.getPlayer().getCaseX() && this.caseY+j == this.board.getPlayer().getCaseY()){
					cond = true ;
					int ecartX = this.caseX - this.board.getPlayer().getCaseX() ;
					int ecartY = this.caseY - this.board.getPlayer().getCaseY() ;
						if(Math.abs(ecartX) < Math.abs(ecartY)){
							if(ecartY < 0 )
								this.direction = "DOWN" ;
							else
								this.direction = "UP" ;
						}else{
							if(ecartX < 0 )
								this.direction = "RIGHT" ;
							else
								this.direction = "LEFT" ;
						}
				}
					
						
			}
		return cond ;
	}
	
	public Boolean action() {
		System.out.println("INTERACT");
		System.out.println(this.interacts);			
		this.interacts = false ;
		return true;		
	}
	
	public void randomDirection(String[] array){
		int rand = new Random().nextInt(array.length);
		this.direction = array[rand] ;
	}
	
	////////////////////////	Getters & Setters	////////////////////////	

}
