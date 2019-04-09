package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Board implements Serializable, SpriteInterface{	

	private static final long serialVersionUID = 30240081673527301L;

	private ArrayList<GameObject> objects ;
	
	private Case[][] grid ;
	private int lig ;
	private int col ;
	private Player player;
	
	public Board(int lig, int col) {	
		this.objects = new ArrayList<GameObject>();
		this.lig = lig ;
		this.col = col ;
		this.grid = new Case[col][lig];
		for(int i = 0 ; i < col ; i++){
			for(int j = 0 ; j < lig ; j++){
				this.grid[i][j] = new Case(i, j); 
				this.grid[i][j].setSolid(false);
				this.grid[i][j].setObj(null);
			}
		}		
	}
	
	public void addObject(GameObject object){
		// Si c'est un objet solide, il faut verifier que sur cette case il n'y en a pas deja un
		// s'il y en a deja un, on ajoute pas cet object dans notre liste d'objets.
		
		if(object.isSolid()){
			if (this.grid[object.getCaseX()][object.getCaseY()].getObj() == null){
				this.grid[object.getCaseX()][object.getCaseY()].setSolid(true);
				this.grid[object.getCaseX()][object.getCaseY()].setObj(object);
				this.objects.add(object);
			}
		}
		
		// l'objet n'est pas solide, il faut que l'ajouter dans notre liste d'objet et le mettre dans la case.
		
		else{
			this.grid[object.getCaseX()][object.getCaseY()].setGround(object);
			this.objects.add(object);
		}
		
		// Ajoute le joueur
		
		if (object.getClass().equals(Player.class)) this.player = (Player) object;
	}
	
	public void removeObject(GameObject obj){
		int i = obj.getCaseX();
		int j = obj.getCaseY();
		
		if(obj.isSolid()){
			if(!obj.getClass().equals(Player.class)){
				this.grid[i][j].setObj(null);
				this.grid[i][j].setSolid(false);
			}
		}	
		else	this.grid[i][j].setGround(null);
		
		this.getObjects().remove(obj);
		
		if (obj.getClass().equals(Player.class)) {
			this.player = null;
		}
	}
		
	public void updatePlayer(Player player){
		if(this.player != null)		this.removeObject(this.player);
		this.addObject(player);
	}
	
	public void movePlayerUP()			{this.player.moveUP();}
	public void movePlayerDOWN()		{this.player.moveDOWN();}
	public void movePlayerLEFT() 		{this.player.moveLEFT();}
	public void movePlayerRIGHT() 		{this.player.moveRIGHT();}
	public void stopPlayer() 			{this.player.stopMove();}
	public void startRunningPlayer() 	{this.player.startRunning();}
	public void stopRunningPlayer() 	{this.player.stopRunning();}
	public void interactPlayer()		{this.player.getInteraction();}
	public void playerTakeHammer()		{this.player.takeHammer();}
	public void playerTakePotion()		{this.player.takePotion();}
	public void playerTakeSpell()		{this.player.takeSpell();}
	public void playerUseActiveItem()	{this.player.useActiveItem();}
	public void playerItemSubPosPlus()	{this.player.addItemSubPos(+1);}
	public void playerItemSubPosMinus()	{this.player.addItemSubPos(-1);}
	public void playerThrowObject()		{this.player.throwObject();}
	public void playerBuyItem()			{this.player.buyItem();}


	////////////////////////	Getters & Setters	////////////////////////	
	
	public Case[][] getGrid() {
		return grid;
	}
	
	public Case getGrid(int x, int y) {
		return grid[x][y];
	}
	
	public int getLig() {
		return lig;	
	}

	public int getCol() {
		return col;
	}	
	
	public void setGrid(Case[][] grid) {
		this.grid = grid;
	}
	
	public void setGrid(Case grid,int x, int y) {
		this.grid[x][y] = grid;	
	}
	
	public void setLig(int lig) {
		this.lig = lig;	
	}
	
	public void setCol(int col) {
		this.col = col;
	}

	public ArrayList<GameObject> getObjects() {
		return objects;
	}

	public void setObjects(ArrayList<GameObject> objects) {
		this.objects = objects;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

}