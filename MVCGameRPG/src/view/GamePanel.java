package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JPanel;

import model.Board;
import model.GameData;
import model.GameObject;
import model.Player;
import model.Pnj;
import model.SpriteInterface;


public class GamePanel extends JPanel implements SpriteInterface {
	
	private static final long serialVersionUID = 1L;
	
	private GameData gameData;

	private double camXi, camXf, camYi, camYf ;
	
	private Font font = FontLoader.load("fonts/pokemon_pixel_font.ttf",40);
	private Font font1 = FontLoader.load("fonts/pokemon_pixel_font.ttf",30);
	
 	public GamePanel(GameData gameData){
		this.setFocusable(true);
		this.requestFocusInWindow();	
		this.setLayout(null);

		this.gameData = gameData ;
	}
	
	public void paintComponent(Graphics g){
		if(this.gameData.getState().equals("MENU"))
			drawMenu(g) ;
		
		else if(this.gameData.getState().equals("NEWGAME"))
			drawNewGame(g);
		
		else if(this.gameData.getState().equals("LOADGAME"))
			drawLoadGame(g);
		
		else if(this.gameData.getState().equals("PAUSE"))
			drawPause(g);
		
		else if(this.gameData.getState().equals("TRANSITION"))
			drawTransition(g);
		
		else if(this.gameData.getState().equals("GAME"))
			drawObjects(g) ;
		
		}

	public void drawMenu(Graphics g){
		this.removeAll();
		g.setColor(Color.DARK_GRAY);
		g.setFont(font);
		
		g.drawImage(sprites[backGround001][0].getSprite(), 0, 0, this);
		
		g.drawImage(sprites[cadre][0].getSprite(), 50, 120, this);
		g.drawImage(sprites[cadre][0].getSprite(), 50, 220, this);
		g.drawImage(sprites[cadre][0].getSprite(), 50, 320, this);
		g.drawImage(sprites[cadre][0].getSprite(), 50, 420, this);

		g.drawString("New Game", sprites[cadre][0].getSprite().getWidth()/2, 165);
		g.drawString("Load Game", -5 + sprites[cadre][0].getSprite().getWidth()/2, 265);
		g.drawString("Controls", sprites[cadre][0].getSprite().getWidth()/2, 365);
		g.drawString("Quit", 20 + sprites[cadre][0].getSprite().getWidth()/2, 465);
	}

	public void drawNewGame(Graphics g){
		g.setColor(Color.DARK_GRAY);
		g.setFont(font1);
		
		g.drawImage(sprites[backGround001][0].getSprite(), 0, 0, this);
		
		g.drawString("Choose a name : ", 350 , 180);
		g.drawString("Play", 590 , 220);
		g.drawImage(sprites[button][0].getSprite(), 530, 190, this);
		
		if(this.getComponentCount() == 0){
			if(!this.gameData.getTextFields().isEmpty()){
				this.add(this.gameData.getTextFields().get(0));
			}
		}
	}

	public void drawLoadGame(Graphics g){
		g.setColor(Color.DARK_GRAY);
		g.setFont(font);
		
		g.drawImage(sprites[backGround001][0].getSprite(), 0, 0, this);
		
		if(this.gameData.getPlayers().size() > 0 ){
			int k = 0 ;
			int j = -1 ;
			for(int i = 0; i < this.gameData.getPlayers().size(); i++){
				if(i %7 == 0 ) {j+=1 ; k = 0;}
				g.drawImage(sprites[perso][down0].getSprite(), 50 + 300*j, 30 +80*k , 70, 70, this);
				g.drawString(this.gameData.getPlayers().get(i),130+ 300*j,  70 +80*k);
				k += 1 ;
			}
		}
		else {
			g.drawString("Aucune sauvegarde .. ", 150, 200 );
			g.drawString("Escape pour quitter ", 200, 250 );
			
		}
	}
	
	public void drawPause(Graphics g){
		g.setColor(Color.DARK_GRAY);
		g.setFont(font);
		
		g.drawImage(sprites[backGround002][0].getSprite(), GameFrame.width/4, 50, this);
		
		
		g.drawImage(sprites[cadre][0].getSprite(), GameFrame.width/2 - sprites[cadre][0].getSprite().getWidth()/2, 170, this);
		g.drawImage(sprites[cadre][0].getSprite(), GameFrame.width/2 - sprites[cadre][0].getSprite().getWidth()/2, 270, this);
		g.drawImage(sprites[cadre][0].getSprite(), GameFrame.width/2 - sprites[cadre][0].getSprite().getWidth()/2, 370, this);

		g.drawString("Resume", 80 + GameFrame.width/2 - sprites[cadre][0].getSprite().getWidth()/2, 215);
		g.drawString("Controls", 10 +GameFrame.width/2 - sprites[cadre][0].getSprite().getWidth()/2/2, 315);
		g.drawString("Quit and Save", 45 + GameFrame.width/2 - sprites[cadre][0].getSprite().getWidth()/2, 415);
	}
	
	public void drawTransition(Graphics g){
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, 1000, 1000);
		
		g.setColor(Color.lightGray);
		g.setFont(font1);
		
		g.drawString("Select lines : ", 200 , 190);
		g.drawString("Select columns : ", 350 , 190);
		
		g.drawString("Generate", 565 , 220);
		g.drawImage(sprites[button][0].getSprite(), 530, 190, this);

		g.drawString("Min : 10x10", 200 , 280);
		g.drawString("Max : 150x150", 200 , 310);

		if(this.getComponentCount() == 0){
			if(!this.gameData.getTextFields().isEmpty()){
				this.add(this.gameData.getTextFields().get(0));
				this.add(this.gameData.getTextFields().get(1));
			}
		}
	}
	
	public void drawObjects(Graphics g){
		g.setFont(font1);
		this.removeAll();
		
		Board board = this.gameData.getBoards().get(this.gameData.getBoardIndice());
			
		this.Sort(board.getObjects());
		
		if(this.gameData.getCamera()){
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(0, 0, 1000, 1000);
			for (int i = 0 ; i < board.getObjects().size() ; i++){
				this.camera();
				GameObject obj = board.getObjects().get(i);
				if(obj.getPosX() >= camXi - this.gameData.getCaseSize() - sprites[obj.getID()][obj.getFrameID()].getSprite().getWidth() && obj.getPosX() <= camXf + this.gameData.getCaseSize() && 
				obj.getPosY() >= camYi - this.gameData.getCaseSize() - sprites[obj.getID()][obj.getFrameID()].getSprite().getHeight() && obj.getPosY() <= camYf + this.gameData.getCaseSize()){
					g.drawImage(sprites[obj.getID()][obj.getFrameID()].getSprite(), (int) (obj.getPosX()+ corections[obj.getID()][obj.getFrameID()][0] - this.camXi), (int) (obj.getPosY() + corections[obj.getID()][obj.getFrameID()][1] - this.camYi), this);
					if (obj.getClass() == Pnj.class){
						Pnj objPNJ = (Pnj) obj;
						g.setColor(Color.WHITE);
						g.fillRect((int)(objPNJ.getPosX()-this.camXi), (int)(objPNJ.getPosY()-this.gameData.getCaseSize()/2-this.camYi), this.gameData.getCaseSize(), 4);
						g.setColor(Color.RED);
						g.fillRect((int)(objPNJ.getPosX()-this.camXi), (int)(objPNJ.getPosY()-this.gameData.getCaseSize()/2-this.camYi), (int)((double)objPNJ.getLife()/objPNJ.getMaxLife()*this.gameData.getCaseSize()), 4);
						g.setColor(Color.BLACK);
					}
					if (obj.getClass() == Player.class){
						Player objPNJ = (Player) obj;
						g.setColor(Color.WHITE);
						g.fillRect((int)(objPNJ.getPosX()-this.camXi), (int)(objPNJ.getPosY()-this.gameData.getCaseSize()/2-this.camYi), this.gameData.getCaseSize(), 4);
						g.setColor(Color.BLUE);
						g.fillRect((int)(objPNJ.getPosX()-this.camXi), (int)(objPNJ.getPosY()-this.gameData.getCaseSize()/2-this.camYi), (int)((double)objPNJ.getLife()/objPNJ.getMaxLife()*this.gameData.getCaseSize()), 4);
						g.setColor(Color.BLACK);
					}
				}
			}
		}
		else{
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, 1000, 1000);
			for (int i = 0 ; i < board.getObjects().size() ; i++){
				GameObject obj = board.getObjects().get(i);
				g.drawImage(sprites[obj.getID()][obj.getFrameID()].getSprite(), (int) (obj.getPosX()+ corections[obj.getID()][obj.getFrameID()][0]), (int) (obj.getPosY() + corections[obj.getID()][obj.getFrameID()][1]), this);
				if (obj.getClass() == Pnj.class){
					Pnj objPNJ = (Pnj) obj;
					g.setColor(Color.WHITE);
					g.fillRect((int)objPNJ.getPosX(), (int)objPNJ.getPosY()-this.gameData.getCaseSize()/2, this.gameData.getCaseSize(), 4);
					g.setColor(Color.RED);
					g.fillRect((int)objPNJ.getPosX(), (int)objPNJ.getPosY()-this.gameData.getCaseSize()/2, (int)((double)objPNJ.getLife()/objPNJ.getMaxLife()*this.gameData.getCaseSize()), 4);
					g.setColor(Color.BLACK);
				}
				if (obj.getClass() == Player.class){
					Player objPNJ = (Player) obj;
					g.setColor(Color.WHITE);
					g.fillRect((int)objPNJ.getPosX(), (int)objPNJ.getPosY()-this.gameData.getCaseSize()/2, this.gameData.getCaseSize(), 4);
					g.setColor(Color.BLUE);
					g.fillRect((int)objPNJ.getPosX(), (int)objPNJ.getPosY()-this.gameData.getCaseSize()/2, (int)((double)objPNJ.getLife()/objPNJ.getMaxLife()*this.gameData.getCaseSize()), 4);
					g.setColor(Color.BLACK);
				}
			}
		}
		g.drawImage(sprites[itemFrame][0].getSprite(), 750, 20, this);
		g.drawImage(itemIcons[board.getPlayer().getActiveItem().getPosition()][board.getPlayer().getActiveItem().getSubPosition()].getSprite(), 750, 20, this);
		g.setColor(Color.WHITE);
		g.drawString(String.valueOf(board.getPlayer().getActiveItem().getQuantity()), 810, 138);
		g.drawString("/", 830, 138);
		g.drawString(String.valueOf(board.getPlayer().getActiveItem().getQuantityMax()), 840, 138);
		g.setColor(Color.RED);
		g.drawString("Lvl : "+board.getPlayer().getLvl(), 540, 45);
		g.drawString(String.valueOf(board.getPlayer().getLvl()+1),720, 45);
		g.drawString("Atq : "+board.getPlayer().getAtq(), 540, 70);
		g.drawString("Def : "+board.getPlayer().getDef(), 540, 100);
		g.drawString("Cost : "+board.getPlayer().getActiveItem().getPrice(), 750, 20);
		g.drawString("Money : "+board.getPlayer().getMoney(), 750, 160);
		g.setColor(Color.WHITE);
		g.fillRect(610, 29, 100, 15);
		g.setColor(Color.DARK_GRAY);
		g.fillRect(610, 29, (int)(100*(board.getPlayer().getXp()-Math.pow(board.getPlayer().getLvl(), 2))/(Math.pow(board.getPlayer().getLvl()+1, 2)-Math.pow(board.getPlayer().getLvl(), 2))), 15);
	
			
			
	}
	
	public void Sort(ArrayList<GameObject> objects){
		Collections.sort(objects, new Comparator <GameObject>() {
			@Override
			public int compare(GameObject o1, GameObject o2) {
				if (o1 != null && o2 != null){
					int id = Boolean.compare(o1.isSolid(), o2.isSolid()) ;
					if(id != 0)
						return id ; 
					else {
						if(o1.getClass() == Player.class || o1.getClass() == Pnj.class){		
							if(o1.getNextCaseY()-o1.getCaseY() < 0){// direction = UP
								int y = Integer.compare(o1.getNextCaseY() , o2.getCaseY()) ;
								if(y == 0){
									return -1 ; 
								}
							}
							else {
								int y = Integer.compare(o1.getNextCaseY() , o2.getNextCaseY()) ;
								if(y !=0)
									return y ;
							}
						}
						if(o2.getClass() == Player.class || o2.getClass() == Pnj.class){	
							if(o2.getNextCaseY()-o2.getCaseY() > 0){// direction = DOWN
								int y = Integer.compare(o1.getCaseY() , o2.getNextCaseY()) ;
								if(y == 0){
										return 1 ; 
									}
								}
							else {
								int y = Integer.compare(o1.getNextCaseY() , o2.getNextCaseY()) ;
								if(y !=0)
									return y ;
								}
							}
						
						else { // Simple/simple && Complex/complex
							int y = Integer.compare(o1.getNextCaseY() , o2.getNextCaseY()) ;
							if(y !=0)
								return y ;
						}
					}
					
					}
				return 0;
				}
		    });

	}

	public void camera(){
		Board board = this.gameData.getBoards().get(this.gameData.getBoardIndice());
		//si on supprime le personnage pendant une boucle for
		
		if(board.getPlayer()!= null){
			this.camXi = board.getPlayer().getPosX() + sprites[perso][0].getSprite().getWidth()/2 - GameFrame.width/2 ;
			this.camXf = board.getPlayer().getPosX() + sprites[perso][0].getSprite().getWidth()/2 + GameFrame.width/2 ;
			this.camYi = board.getPlayer().getPosY() + sprites[perso][0].getSprite().getHeight()/2 - GameFrame.height/2 ;
			this.camYf = board.getPlayer().getPosY() + sprites[perso][0].getSprite().getHeight()/2 + GameFrame.height/2 ;
		}
		
		if(this.camXi < 0){
			this.camXi = 0 ;
			this.camXf = GameFrame.width ;
		}
		if(this.camXf > this.gameData.getCaseSize()*board.getCol()){
			this.camXf = this.gameData.getCaseSize()*board.getCol() ;
			this.camXi = this.gameData.getCaseSize()*board.getCol() - GameFrame.width ;
		}
		if(this.camYi < 0){
			this.camYi = 0 ;
			this.camYf = GameFrame.height ;
		}
		if(this.camYf > this.gameData.getCaseSize()*board.getLig()){
			this.camYf = this.gameData.getCaseSize()*board.getLig() ;
			this.camYi = this.gameData.getCaseSize()*board.getLig() - GameFrame.height ;
		}
	}

}
