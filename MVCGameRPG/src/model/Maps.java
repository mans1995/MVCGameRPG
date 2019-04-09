package model;

import java.util.ArrayList;
import java.util.Random;

public class Maps implements SpriteInterface, ItemInterface {
	
	private Board board ;
	private GameData gameData ;
	
	public Maps(GameData gameData){
		this.gameData = gameData ;
	}
	
	public void loadNewGame(){
		this.board = gameData.getBoards().get(gameData.getBoardIndice());
		// ground
		
		for(int i = 0 ; i< this.board.getCol() ; i++)
			for(int j = 0 ; j < this.board.getLig() ; j++)
					this.board.addObject(new StaticObject(ground005,0,i,j,false,gameData));
		
		// grass
		
		for(int i = 1 ; i <= 3; i++)
			for(int j = 12 ; j <= 17; j++)
				this.board.addObject(new StaticObject(ground006,0,i,j,true,gameData));
		
		for(int i = 1 ; i <= 25; i++)
			for(int j = 20 ; j <= 24; j++)
				this.board.addObject(new StaticObject(ground006,0,i,j,true,gameData));
		
		//tree
		
		for(int i = 7 ; i <= 13; i+=2)
			for(int j = 15 ; j >=13 ; j-=2)
				this.board.addObject(new StaticObject(tree007,0,i,j,true,gameData));
		
		for(int i = 15 ; i <= 19; i += 2)	this.board.addObject(new StaticObject(tree007,0,i,6,true,gameData));
		this.board.addObject(new StaticObject(tree009,0,23,2,true,gameData));
		this.board.addObject(new StaticObject(tree009,0,21,1,true,gameData));
			
		//road
		
		for(int i = 1 ; i <= 22; i += 1)
			for(int j = 9 ; j <= 9; j++){
				if(i > 2 && i != 7)		this.board.addObject(new StaticObject(road002,0,i,j-1,false,gameData));
				this.board.addObject(new StaticObject(road005,0,i,j,false,gameData));
				this.board.addObject(new StaticObject(road008,0,i,j+1,false,gameData));
				}
		
		for(int i = 1 ; i <= 1; i ++)
			for(int j = 0 ; j <= 9; j++){
				if(j<8)		this.board.addObject(new StaticObject(road006,0,i+1,j,false,gameData));
				this.board.addObject(new StaticObject(road005,0,i,j,false,gameData));
				this.board.addObject(new StaticObject(road004,0,i-1,j,false,gameData));
				}
		
		this.board.addObject(new StaticObject(road007,0,0,10,false,gameData));
		this.board.addObject(new StaticObject(road005,0,2,8,false,gameData));
		this.board.addObject(new StaticObject(road005,0,7,7,false,gameData));
		this.board.addObject(new StaticObject(road005,0,7,8,false,gameData));

		//house
		
		this.board.addObject(new StaticObject(house001,0,7,6,true,gameData));

		for(int i = 4 ; i <= 13; i ++)
			if(i!=7)	this.board.addObject(new StaticObject(28,0,i,7,true,gameData));
		
		for(int i = 4 ; i <= 13; i ++)
			this.board.addObject(new StaticObject(28,0,i,0,true,gameData));
		
		for(int i = 4 ; i <= 13; i +=9)
			for(int j = 1 ; j <= 6; j ++)
				this.board.addObject(new StaticObject(28,0,i,j,true,gameData));

		// dungeon
		
		this.board.addObject(new StaticObject(door002, entry, 22, 9, true, gameData));		
		this.board.addObject(new StaticObject(door002, C001, 22, 7, true, gameData));
		this.board.addObject(new StaticObject(door002, C002, 23, 7, true, gameData));
		this.board.addObject(new StaticObject(door002, C003, 24, 7, true, gameData));
		this.board.addObject(new StaticObject(door002, C004, 25, 7, true, gameData));
		this.board.addObject(new StaticObject(door002, C005, 25, 8, true, gameData));
		this.board.addObject(new StaticObject(door002, C005, 25, 9, true, gameData));
		this.board.addObject(new StaticObject(door002, C005, 25, 10, true, gameData));
		this.board.addObject(new StaticObject(door002, C006, 25, 11, true, gameData));
		this.board.addObject(new StaticObject(door002, C007, 24, 11, true, gameData));
		this.board.addObject(new StaticObject(door002, C007, 23, 11, true, gameData));
		this.board.addObject(new StaticObject(door002, C008, 22, 11, true, gameData));
		this.board.addObject(new StaticObject(door002, C009, 22, 10, true, gameData));
		this.board.addObject(new StaticObject(door002, C010, 22, 8, true, gameData));
		
		//pnj
		
		//this.board.addObject(new Pnj(perso,3,2,2,true,gameData,1,2));
		
		Player player = new Player(perso,0, 1, 0, true, this.gameData, 8, 1);
		player.addXp(0);
		player.addMoney(1000);
		player.setItem(new Item(hammerPos, ironHammer, 1, 1, 20, 100));
		player.setItem(new Item(hammerPos, goldHammer, 1, 1, 2, 1000));
		player.setItem(new Item(potionPos, healPotion, 10, 20, 15, 10));
		player.setItem(new Item(potionPos, regenPotion, 5, 10, 0, 15));
		player.setItem(new Item(spellPos, damageSpell, 100, 100, 40, 1000));
		player.setItem(new Item(spellPos, otherSpell, 0, 10, 0, 0));
		this.board.addObject(player);
	}
	
	public void loadHouse(){
		this.board = gameData.getBoards().get(gameData.getBoardIndice());

		for(int i = 11; i <= 17 ;i++)
			for(int j =5 ; j <=13 ; j++)
				board.addObject(new StaticObject(ground007,0,i,j, false, this.gameData));

		for(int i = 10; i <= 18 ;i+=8)
			for(int j =5 ; j <=13 ; j++)
				board.addObject(new StaticObject(wall002,0,i,j, true, this.gameData));
		
		for(int i = 11; i <= 17 ;i++)
			for(int j = 4 ; j <=14 ; j+=10)
				board.addObject(new StaticObject(wall002,0,i,j, true, this.gameData));
		
		board.addObject(new StaticObject(pnj001,0,14,6, true, this.gameData));
		board.addObject(new StaticObject(comptoir,middle,14,7, true, this.gameData));
		board.addObject(new StaticObject(comptoir,downLeft,13,7, true, this.gameData));
		board.addObject(new StaticObject(comptoir,downRight,15,7, true, this.gameData));
		board.addObject(new StaticObject(comptoir,upLeft,13,6, true, this.gameData));
		board.addObject(new StaticObject(comptoir,upRight,15,6, true, this.gameData));
		board.addObject(new StaticObject(comptoir,upLeft,13,5, true, this.gameData));
		board.addObject(new StaticObject(comptoir,upRight,15,5, true, this.gameData));
		
		board.removeObject(board.getGrid(14, 14).getObj());
		board.addObject(new StaticObject(door001,0,14,14, true, this.gameData));
	}

	
	public Board loadDungeon(int entryX,int entryY,int lig, int col, GameData gameData){
		
		Board board = gameData.getBoards().get(gameData.getBoardIndice()) ; 
		
		// trouvons la sortie.
		
		Random r = new Random();
		
		int exitX = col-2 ;
		
		int Low = lig/2;
		int High = lig-2;
		int exitY = r.nextInt(High-Low) + Low;
		
		board = this.findPath(board, lig, col, entryX, entryY, exitX, exitY, gameData);
		
		for (int i = 0; i <= (int)(lig*col/2500d); i++){
			this.addPath(board, lig, col, gameData);
		}


		this.addWalls(board, lig, col, gameData);

		
		int cond = 1 ;
		while(cond !=0){
			cond = 	this.removeOneThickness(board, lig, col, gameData);
		}

		this.completeBlanks(board, lig, col, exitX, exitY, gameData);

		Fabulous.makeIt(board, gameData);

		this.addMonsters(board, lig, col, entryX, entryY, gameData);
		
		//addExit
		board.removeObject(board.getGrid(exitX+1, exitY).getObj());
		board.addObject(new StaticObject(door003,0,exitX+1, exitY, true, gameData));
		
		return board ;
	}

	public Board findPath(Board board,int lig, int col, int entryX, int entryY,int exitX,int exitY, GameData gameData){
		int caseX = entryX ;
		int caseY = entryY ;
		
		int count = 0 ;
		int precedentSize = 0 ;
		
		while((caseX != exitX || caseY != exitY)){
			precedentSize = board.getObjects().size() ;
			
			board = nextObject(board, caseX, caseY, lig, col, gameData,false);
			
			//il faut mettre à jour caseX et caseY
			if(board.getObjects().size() > 0 ){
				caseX = board.getObjects().get(board.getObjects().size()-1).getCaseX();
				caseY = board.getObjects().get(board.getObjects().size()-1).getCaseY();
			}
			
			//on vérifie si un objet à été crée. Si après dix essais aucun objet n'a été rajouté,
			//on recommence tout.
			if(board.getObjects().size() == precedentSize){
				count ++ ;
				if(count == 10){
					board = new Board(lig,col);
					gameData.getBoards().set(gameData.getBoardIndice(),board) ;
					count = 0 ;
					caseX = entryX ;
					caseY = entryY ;
					}
			}else
				count = 0 ;			
		}
		return board ;
	}
	
	public void addPath(Board board,int lig, int col, GameData gameData){
		for(int k = board.getObjects().size()-1 ; k >=0 ; k--){
			GameObject obj = board.getObjects().get(k) ;
			if(obj.getID() == road012){
					int x = obj.getCaseX();
					int y = obj.getCaseY();
					board = nextObject(board, x, y, lig, col, gameData, true);
			}
		}
	}
	
	public void addWalls(Board board,int lig, int col, GameData gameData){
		for(int i = 0 ; i < col ; i++)
			for (int j = 0 ; j < lig ; j++)
				if(board.getGrid(i,j).getObj() == null && board.getGrid(i,j).getGround() == null){
					boolean cond = true;
					for(int k = board.getObjects().size()-1 ; k >=0 ; k--){
						GameObject obj = board.getObjects().get(k) ;
						
						if(obj.getID() == road012){
							for(int m = -2 ; m < 2 ; m++)
								for(int n = -2 ; n < 2 ; n++)
									if(obj.getCaseX() == i+m && obj.getCaseY() == j+n) 	cond = false ;
							}
						
						}
					if(cond)	board.addObject(new StaticObject(wall001,0,i, j, true, gameData));
					}
	}
	
	public int removeOneThickness(Board board,int lig, int col, GameData gameData){
		int count = 0 ;
		for(int k = board.getObjects().size()-1 ; k >=0 ; k--){
			GameObject obj = board.getObjects().get(k) ;
			if(obj.getID() == wall001){
				int counter = 0 ;
				if(!checkBorders(obj.getCaseX()+1,obj.getCaseY() , lig, col) && board.getGrid(obj.getCaseX()+1,obj.getCaseY()).getObj() == null)	
					counter +=1;
				if(!checkBorders(obj.getCaseX()-1,obj.getCaseY() , lig, col) && board.getGrid(obj.getCaseX()-1,obj.getCaseY()).getObj() == null)	
					counter +=1;
				if(!checkBorders(obj.getCaseX(),obj.getCaseY()+1 , lig, col) && board.getGrid(obj.getCaseX(),obj.getCaseY()+1).getObj() == null)	
					counter +=1;
				if(!checkBorders(obj.getCaseX(),obj.getCaseY()-1 , lig, col) && board.getGrid(obj.getCaseX(),obj.getCaseY()-1).getObj() == null)	
					counter +=1;
				
				if(counter > 2){
					board.removeObject(obj);
					count++ ;
				}
			}
		}
		return count ;
	}
	
	public void completeBlanks(Board board,int lig, int col,int exitX,int exitY, GameData gameData){
		for(int i = 0 ; i < col ; i++)
			for (int j = 0 ; j < lig ; j++){
				if(i == 0 || j == 0  || i == col-1 || j == lig-1)	board.addObject(new StaticObject(wall001,0,i, j, true, gameData));
				else{
					if(board.getGrid(i,j).getObj() == null)
						board.addObject(new StaticObject(road012,0,i, j, false, gameData));
					}
			}
	}
	
	public void addMonsters(Board board,int lig, int col, int entryX, int entryY, GameData gameData){
		
		int count = 0 ;
		for(int i = 0 ; i < col ; i++){
			for (int j = 0 ; j < lig ; j++){
				if(board.getGrid(i,j).getGround() != null && board.getGrid(i,j).getObj() == null){
					if(board.getGrid(i,j).getGround().getID() == road012 && Math.abs(entryX - i) > 3 && Math.abs(entryY - j) > 3 ){
						if(count%40 == 0){
							board.getGrid(i, j).setObj(null);
							board.getGrid(i,j).setSolid(false);
							if(this.gameData.getDifficulty() > 10){
								Pnj pnj = new Pnj(enemy001,3,i,j,true,gameData,4,this.gameData.getDifficulty());
								pnj.setItem(new Item(hammerPos, ironHammer, 1, 1, 20, 100));
								board.addObject(pnj);
							}
							else {
								Pnj pnj = new Pnj(enemy001,3,i,j,true,gameData,2,this.gameData.getDifficulty());
								pnj.setItem(new Item(hammerPos, ironHammer, 1, 1, 20, 100));
								board.addObject(pnj);
							}
						}
						if(count%210 == 0){
							board.addObject(new StaticObject(wall001, 0, i,j, true, this.gameData));		
						}
						
						count ++ ;

					}
				}
			}}
	}
	
	public Board nextObject(Board board,int caseX, int caseY,int lig, int col, GameData gameData, boolean allowAdjacent){
		String direction = randomDirection(board, caseX, caseY, lig, col);

		int max = Math.max(lig, col);
		int Low = max/7 ;
		int High = 2*max/7 ;
		
		Random rand = new Random() ;
		int times = rand.nextInt(High-Low) + Low;
		
//		if(times > 25)	times = 25(;
		
		for(int c = 0 ; c <= times ; c++){
			int i = 0;
			int j = 0;
			
			if(direction.equals("UP"))			{i = 0;		j = -1;}
			else if(direction.equals("DOWN"))	{i = 0;		j = 1;}
			else if(direction.equals("RIGHT"))	{i = 1;		j = 0;}
			else if(direction.equals("LEFT"))	{i = -1;	j = 0;}
			
			if(!checkBorders(caseX+i, caseY+j , lig, col)){
				if(board.getGrid(caseX+i, caseY+j).getObj() == null && board.getGrid(caseX+i, caseY+j).getGround() == null){
					// pour qu'il n'y ai pas de cases adjacentes (du moins dans la meme direction).
							if(!checkBorders(caseX+1, caseY+1 , lig, col) && !allowAdjacent){
								if(board.getGrid(caseX+1, caseY+1).getObj() == null && board.getGrid(caseX+1, caseY+1).getGround() == null){
									board.addObject(new StaticObject(road012,0,caseX+i, caseY+j, false, gameData));
									caseX += i;
									caseY += j ;
								}
					}
					else{
						board.addObject(new StaticObject(road012,0,caseX+i, caseY+j, false, gameData));
						caseX += i;
						caseY += j ;
						}
					}
				}
		}
		return board ; 
	}
	
	public String randomDirection(Board board,int caseX, int caseY,int lig, int col){
		ArrayList<String> array = new ArrayList<String>();
		String direction = findLastDirection(board, caseX, caseY, lig, col) ;
		
		if(direction != "NONE"){
			if(direction.equals("VERTICAL")){
				array.add("RIGHT") ;
				array.add("LEFT") ;
			}
			else{
				array.add("UP") ;
				array.add("DOWN") ;
			}
		}
		else{
			array.add("RIGHT") ;
			array.add("LEFT") ;
			array.add("UP") ;
			array.add("DOWN") ;
		}
			
		int rand = new Random().nextInt(array.size());
		
		return array.get(rand) ;
	}
	
	public String findLastDirection(Board board,int caseX, int caseY,int lig, int col){
		String direction = "NONE";
		int size = board.getObjects().size() ;
		
		if(size >= 2){
			if(board.getObjects().get(size-1).getCaseX() == board.getObjects().get(size-2).getCaseX())
				direction = "VERTICAL" ;
			else
				direction = "HORIZONTAL" ;
		}
		return direction ;
	}

	public boolean checkBorders(int caseX, int caseY, int lig, int col){
		boolean res = false ;
		//1 et -2 puisqu'on on met un bord.
			if(caseX < 1 ) 
				res = true ;
			else if(caseY < 1 )
				res = true ;
			else if(caseX > col -2)
				res = true ;	
			else if(caseY > lig -2)
				res = true ;
			return res ;
		}
	

}
