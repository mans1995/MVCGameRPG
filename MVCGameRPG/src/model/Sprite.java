package model;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class Sprite {
	
	private BufferedImage sprite;
	
	public Sprite(String path){
		this.loadSprite(path);
	}
	
	public Sprite(String path, int xSize, int ySize){
		this.loadSprite(path);
	}
	
	private void loadSprite(String path){
		BufferedImage sprite = null;
		try{
			InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
			sprite = ImageIO.read(is);
		} catch(IOException e){
			e.printStackTrace();
		}
		this.sprite = sprite;
	}
	

	// GETTERS AND SETTERS //

	public BufferedImage getSprite(){
		return this.sprite;
	}

	public void setSprite(BufferedImage sprite) {
		this.sprite = sprite;
	}
	
}
