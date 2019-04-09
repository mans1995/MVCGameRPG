package model;

import java.io.Serializable;

public abstract class GameObject implements SpriteInterface, Serializable, ItemInterface{

	private static final long serialVersionUID = 922178998838757629L;	
	
	protected int caseX, caseY, nextCaseX, nextCaseY;
	protected double posX, posY;
	protected int ID, frameID ;
	protected GameData gameData ;
	protected Board board;	
	protected boolean solid, interacts, alive;
	
	public GameObject(int ID, int frameID, int caseX, int caseY, boolean solid,  GameData gameData) {
		this.gameData = gameData;
		this.board = this.gameData.getBoards().get(this.gameData.getBoardIndice());
		
		this.ID = ID;		
		this.frameID = frameID;
		
		this.caseX = caseX;
		this.caseY = caseY;
		this.nextCaseX = caseX;
		this.nextCaseY = caseY;
		this.posX = caseX * this.gameData.getCaseSize();
		this.posY = caseY * this.gameData.getCaseSize();
		
		this.solid = solid;
		this.interacts = false;		
		this.alive = true;
	}
	
	public Boolean action() {return false;}

	
	
	////////////////////////	Getters & Setters	////////////////////////	
	
	
	public int getCaseX() {
		return caseX;
	}

	public void setCaseX(int caseX) {
		this.caseX = caseX;
	}

	public int getCaseY() {
		return caseY;
	}

	public void setCaseY(int caseY) {
		this.caseY = caseY;
	}

	public int getNextCaseX() {
		return nextCaseX;
	}

	public void setNextCaseX(int nextCaseX) {
		this.nextCaseX = nextCaseX;
	}

	public int getNextCaseY() {
		return nextCaseY;
	}

	public void setNextCaseY(int nextCaseY) {
		this.nextCaseY = nextCaseY;
	}

	public double getPosX() {
		return posX;
	}

	public void setPosX(double posX) {
		this.posX = posX;
	}

	public double getPosY() {
		return posY;
	}

	public void setPosY(double posY) {
		this.posY = posY;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getFrameID() {
		return frameID;
	}

	public void setFrameID(int frameID) {
		this.frameID = frameID;
	}

	public GameData getGameData() {
		return gameData;
	}

	public void setGameData(GameData gameData) {
		this.gameData = gameData;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public boolean isSolid() {
		return solid;
	}

	public void setSolid(boolean solid) {
		this.solid = solid;
	}

	public boolean isInteracts() {
		return interacts;
	}

	public void setInteracts(boolean interacts) {
		this.interacts = interacts;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}
}

