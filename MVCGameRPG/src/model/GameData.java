package model;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JTextField;

import view.GamePanel;


public class GameData implements Serializable,SpriteInterface {
	
	private static final long serialVersionUID = -7202242587077987288L;
	
	private ArrayList<JTextField> textFields ;

	private ArrayList<String> players ;
	
	private ArrayList<Board> boards ;
	private int boardIndice ; 
	
	private int caseSize = 32;
	
	private String state, lastState ;
	private String name ;
	
	private int difficulty ;
	
	private boolean camera = true ;
	

	public GameData (){
		this.textFields = new ArrayList<JTextField>() ;
		
		this.players = loadPlayers();
		this.boards = new ArrayList<Board>();
		this.state = "TRANSITION";
		this.state = "MENU";
		this.boardIndice = 0;
		}
	
	public void newGame(){
		//On copie le nom et on ecrase l'ancien gameData (qui peut contenir des objets 
		//si l'on a deja lancé plus d'une nouvelle partie sans etteindre le jeu)
		String name = this.name ;
		this.setGameData(new GameData());
		this.name = name ;
		
		this.players.add(this.name);
		this.getBoards().add(new Board(40,30));
		new Maps(this).loadNewGame();
		
		house();
		
		this.difficulty = 1 ;
		this.boardIndice = 0 ;
		
	}
	
	public void loadGame(){
		this.setGameData(load(this.getName()));

		this.players = loadPlayers() ;

		 // il faut relancer les threads
		for(int i = 0 ; i <  this.boards.size() ; i++){
			for(GameObject obj : this.boards.get(i).getObjects()){
				obj.setGameData(this);
				obj.setBoard(this.boards.get(i));
				if (obj.getClass() == Player.class || obj.getClass() == Pnj.class){
					DynamicObject objDYN = (DynamicObject)obj;
					new Thread(objDYN).start();
					objDYN.setInPause(false);
				}
			}
		}

	}
	
	public void house(){
		this.boards.add(new Board(30,30));
		this.boardIndice = 1 ;
		new Maps(this).loadHouse();
	}

	public void dungeon(int lig,int col){
		if(this.boards.size() == 3)
			this.boards.set(2,new Board(lig,col));
		else this.boards.add(new Board(lig,col));
		this.boardIndice = 2;
		new Maps(this).loadDungeon(1, 1,lig,col, this);
	}
	
	public void pause(){
		if(this.state.equals("GAME")){
			for(GameObject obj : this.boards.get(this.boardIndice).getObjects()){
				if (obj.getClass() == Player.class || obj.getClass() == Pnj.class){
					DynamicObject objDYN = (DynamicObject)obj;
					objDYN.setInPause(true);
				}
			}
			this.lastState = this.state;
			this.state = "PAUSE";
			}
		else if(this.state.equals("NEWGAME") || this.state.equals("LOADGAME"))	{
			this.name = null ;
			this.state = "MENU" ;
		}
		else if(this.state.equals("PAUSE")){
			this.state = this.lastState ;
			removePause();
		}
	}
	
	public void removePause(){
		for(GameObject obj : this.boards.get(this.boardIndice).getObjects()){
			if (obj.getClass() == Player.class || obj.getClass() == Pnj.class){
				DynamicObject objDYN = (DynamicObject)obj;
				objDYN.setInPause(false);
			}
		}
		this.state = "GAME" ;
	}

	public void newGameComponent(){
		this.textFields = new ArrayList<JTextField>() ;
		textFields.add(this.newJTextField(350,200,150,30));
	}

	public void newTransition(){
		this.state = "TRANSITION" ;
		this.textFields = new ArrayList<JTextField>() ;
		this.textFields.add(this.newJTextField(200,200,100,30));
		this.textFields.add(this.newJTextField(350,200,100,30));
	}

	public JTextField newJTextField(int x, int y, int width, int height){
		JTextField field = new JTextField() ;
 		field.setBackground(Color.GRAY);
 		field.setLocation(x, y);
 		field.setSize(width, height);
 		return field ;
	}

	public void play(){
		if(!this.textFields.isEmpty()){
			this.name =  this.textFields.get(0).getText();
			if(this.name != null && !this.name.isEmpty()){
			this.players.add(name);
			this.newGame();
			this.state = "GAME" ;
			}
		}
	}

	public void entryDungeon(){
		int lig = 0 ;
		int col = 0;
		String text ;
		if(!this.textFields.isEmpty()){
			if(this.textFields.get(0) != null){
				text =  this.textFields.get(0).getText();
	            try {
	                lig = Integer.parseInt(text);
	            }catch (NumberFormatException nfe) {}
			}
			if(this.textFields.get(1) != null){
				text =  this.textFields.get(1).getText();
	            try {
	                col = Integer.parseInt(text);
	            }catch (NumberFormatException nfe) {}
			}
		}
		
		if(lig >= 10 && col >=10 && lig <= 150 && col <=150){
			dungeon(lig,col);
			this.state = "GAME" ;
			this.boards.get(0).getGrid(21,9).setObj(null);
			this.boards.get(0).getGrid(21,9).setSolid(false);

			this.boards.get(0).getPlayer().setCaseX(1);
			this.boards.get(0).getPlayer().setCaseY(1);
			this.boards.get(0).getPlayer().setNextCaseX(1);
			this.boards.get(0).getPlayer().setNextCaseY(1);
			this.boards.get(0).getPlayer().setPosX(1*this.caseSize);
			this.boards.get(0).getPlayer().setPosY(1*this.caseSize);
			
			this.boards.get(0).getPlayer().setBoard(this.boards.get(2));
			this.boards.get(2).updatePlayer(this.boards.get(0).getPlayer());
			
			this.boardIndice = 2 ;
			this.boards.get(0).removeObject(this.boards.get(0).getPlayer());
		}
	}
	
	
	public static void save(GameData gameData, String path){
		GameData gd = new GameData();
		
		gd.setGameData(gameData) ;
		
		try{
		FileOutputStream fout = new FileOutputStream("src/saves/" + path+".ser");
		ObjectOutputStream oos = new ObjectOutputStream(fout);  
		oos.writeObject(gd);
		oos.close();
		System.out.println("Saved   : " + path);
		
	   }catch(Exception ex){
		   ex.printStackTrace();
	   }
   }
	
	public static GameData load(String path){
		GameData gd ;
		 
		try{
			FileInputStream fin = new FileInputStream("src/saves/" + path+".ser");
			ObjectInputStream ois = new ObjectInputStream(fin);
			gd = (GameData) ois.readObject();
			ois.close();
			System.out.println("Loaded   : " + path);
			
			   return gd;
			   
		   }catch(Exception ex){
			   ex.printStackTrace();
		   }
		return null; 
	   }

	public static ArrayList<String> loadPlayers(){
		ArrayList<String> filesNames = new ArrayList<String>();
		
		File repertoire = new File("src/saves/");
		File[] files = repertoire.listFiles();
		if (files != null) {
			 for (int i = 0; i < files.length; i++) {
				 filesNames.add(files[i].getName().substring(0,files[i].getName().length()-4));
			 }
		}
		return filesNames;
	}
	
	public void setGameData(GameData gd){
		this.players = gd.getPlayers() ;
		this.boards = gd.getBoards() ;
		this.boardIndice = gd.getBoardIndice();
		this.state = gd.getState() ;
		this.name = gd.getName() ;
		this.camera = gd.getCamera(); 
		this.difficulty = gd.difficulty;
	}
	
	
	// GETTERS AND SETTERS
	
	public ArrayList<String> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<String> players) {
		this.players = players;
	}
	public ArrayList<Board> getBoards() {
		return boards;
	}

	public void setBoards(ArrayList<Board> boards) {
		this.boards = boards;
	}

	public int getBoardIndice() {
		return boardIndice;
	}

	public void setBoardIndice(int boardIndice) {
		this.boardIndice = boardIndice;
	}

	public boolean getCamera() {
		return camera;
	}
	
	public void setCamera(boolean camera) {
		this.camera = camera;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getLastState() {
		return lastState;
	}

	public void setLastState(String lastState) {
		this.lastState = lastState;
	}

	public int getCaseSize() {
		return caseSize;
	}

	public void setCaseSize(int caseSize) {
		this.caseSize = caseSize;
	}

	public ArrayList<JTextField> getTextFields() {
		return textFields;
	}

	public void setTextFields(ArrayList<JTextField> textFields) {
		this.textFields = textFields;
	}

	public int getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}

	
	
}
