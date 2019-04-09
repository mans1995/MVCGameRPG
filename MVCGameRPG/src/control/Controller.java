package control;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import model.Board;
import model.GameData;


public class Controller implements KeyListener, MouseListener{
	
	private GameData gameData;
	
	private int posX,posY;
	
	public Controller(GameData gameData){
		this.gameData = gameData ;
	}

	// KeyListener
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_ESCAPE)	this.gameData.pause() ;

		if(this.gameData.getState().equals("GAME")){
			Board board = this.gameData.getBoards().get(this.gameData.getBoardIndice());

			if (key == KeyEvent.VK_UP) 		board.movePlayerUP();
			if (key == KeyEvent.VK_DOWN) 	board.movePlayerDOWN();
			if (key == KeyEvent.VK_LEFT) 	board.movePlayerLEFT();
			if (key == KeyEvent.VK_RIGHT) 	board.movePlayerRIGHT();
			if (key == KeyEvent.VK_SHIFT)	board.startRunningPlayer();
			if (key == KeyEvent.VK_ENTER)	board.interactPlayer();
			if (key == KeyEvent.VK_X)		board.playerUseActiveItem();
			if (key == KeyEvent.VK_1)		board.playerTakeHammer();
			if (key == KeyEvent.VK_2)		board.playerTakePotion();
			if (key == KeyEvent.VK_3)		board.playerTakeSpell();
			if (key == KeyEvent.VK_C)		board.playerItemSubPosPlus();
			if (key == KeyEvent.VK_E)		board.playerThrowObject();
			if (key == KeyEvent.VK_R)		board.playerBuyItem();
		}
		
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if(this.gameData.getState().equals("GAME")){
			Board board = this.gameData.getBoards().get(this.gameData.getBoardIndice());
			if (key == KeyEvent.VK_UP) 		board.stopPlayer();
			if (key == KeyEvent.VK_DOWN)	board.stopPlayer();
			if (key == KeyEvent.VK_LEFT) 	board.stopPlayer();
			if (key == KeyEvent.VK_RIGHT) 	board.stopPlayer();
			if (key == KeyEvent.VK_SHIFT)	board.stopRunningPlayer();
		}
	}

	public void keyTyped(KeyEvent e) {}
	
	//	MouseListener
	
	public void mouseClicked(MouseEvent click) {
		this.posX =   click.getX() ;	
		this.posY =   click.getY() ;
		
		if(this.gameData.getState() == "MENU")
			menu();
		
		else if(this.gameData.getState() == "NEWGAME")
			newGame();
		
		else if(this.gameData.getState() == "LOADGAME")
			loadGame();
		
		else if(this.gameData.getState() == "TRANSITION")
			transition();
		
		else if(this.gameData.getState() == "PAUSE")
			pause();
		
	}
	
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mousePressed(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}

	//	Methodes
	
	private boolean rect(int clicX, int clicY,int x,int y, int width, int height){
		boolean click = false;
		if((clicX > x && clicX < x+width) && (clicY > y && clicY < y+height))
			click = true;
		return click;
	}
	
	public void menu(){
		if(rect(posX, posY,50,120,250,66)){
			this.gameData.setState("NEWGAME") ;  
			this.gameData.newGameComponent();
		}
		else if(rect(posX, posY,50,220,250,66)){
			this.gameData.setState("LOADGAME") ;  
		}
		else if(rect(posX, posY,50,320,250,66)){
			
		}
		else if(rect(posX, posY,50,420,250,66)){
			System.exit(0);
		}
	}
	
	public void newGame(){
		if(rect(posX, posY,530,200,150,44)){
			this.gameData.play();  
		}
	}
	
	public void transition(){
		if(rect(posX, posY,565,200,150,44)){
			this.gameData.entryDungeon();  
		}
	}
	
	public void pause(){
		if(rect(posX, posY,323,170,250,66)){
			this.gameData.removePause();
		}
		if(rect(posX, posY,323,270,250,66)){
			GameData.load(this.gameData.getName());
		}
		if(rect(posX, posY,323,370,250,66)){
			GameData.save(this.gameData, this.gameData.getName());
			this.gameData.setState("MENU");
			this.gameData.setName(null);
		}

	}

	public void loadGame(){
		int j = -1;
		int k = 0;
		for(int i = 0 ; i < this.gameData.getPlayers().size() ; i++){
			if(i %7 == 0 ) {j+=1 ; k = 0;}
			if(rect(posX, posY, 50 + 300*j, 30 +80*k , 70, 70)){
				this.gameData.setName(this.gameData.getPlayers().get(i)) ;
				this.gameData.loadGame();
				this.gameData.setState("GAME") ;
			}
			k+=1;
		}
	}
	
	// GETTERS AND SETTERS
	
	public void setGameData(GameData gd){
		this.gameData = gd ;
	}

}
