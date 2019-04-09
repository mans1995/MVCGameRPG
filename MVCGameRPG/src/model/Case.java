package model;

import java.io.Serializable;

import model.GameObject;

public class Case implements Serializable{
	
	private static final long serialVersionUID = -6963571905089653303L;
	
	private int x, y;
	private boolean solid;
	
	//solid Objects
	private GameObject obj;
	
	//non solid Objects
	private GameObject ground;
	
	
	public Case(int x, int y){
		this.solid = false ; 
		this.obj = null ;
		this.ground = null ;
		this.x = x;
		this.y = y;
	}
	
	////////////////////////	Getters & Setters	////////////////////////	
	
	public GameObject getObj() {
		return obj;
	}

	public void setObj(GameObject obj) {
		this.obj = obj;
	}

	public boolean isSolid() {
		return solid;
	}

	public void setSolid(boolean solid) {
		this.solid = solid;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public GameObject getGround() {
		return ground;
	}

	public void setGround(GameObject ground) {
		this.ground = ground;
	}

}

