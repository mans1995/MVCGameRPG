package model;

import java.io.Serializable;

public class Item implements Serializable,ItemInterface{

	private static final long serialVersionUID = 4508903958363846405L;
	private int position, subPosition, quantity, quantityMax, valueEffect, price;
	private Sprite sprite;

	public Item(int position, int subPosition, int quantity, int quantityMax, int valueEffect, int price){
		this.quantity = quantity ;
		this.quantityMax = quantityMax ;
		this.valueEffect = valueEffect;
		this.position = position;
		this.subPosition = subPosition;
		this.price = price;
	}
	
	public void addQuantity(int c){
		this.quantity += c;
		if (this.quantity > this.quantityMax) this.quantity = this.quantityMax;
		if(this.quantity < 0) this.quantity = 0 ;
	}
	
	// GETTERS AND SETTERS
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	public int getQuantityMax() {
		return quantityMax;
	}

	public void setQuantityMax(int quantityMax) {
		this.quantityMax = quantityMax;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int getValueEffect() {
		return valueEffect;
	}

	public void setValueEffect(int valueEffect) {
		this.valueEffect = valueEffect;
	}

	public int getSubPosition() {
		return subPosition;
	}

	public void setSubPosition(int subPosition) {
		this.subPosition = subPosition;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
}
