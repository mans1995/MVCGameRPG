package model;

public class Fabulous implements SpriteInterface{
	
	public static void makeIt(Board board, GameData gameData){
		for(int i = 0; i < board.getCol(); i++){
			for (int j = 0; j < board.getLig(); j++){
				if (board.getGrid(i, j).getObj() != null){
					computeStyle(board, gameData, i, j);
				}
			}
		}
	}
	
	private static void computeStyle(Board board, GameData gameData, int x, int y){
		
		board.getGrid(x, y).getObj().setID(wall);
		
		boolean upLeft = false;
		boolean up = false;
		boolean upRight = false;
		boolean right = false;
		boolean downRight = false;
		boolean down = false;
		boolean downLeft = false;
		boolean left = false;		
		
		// Ligne d'en haut
		if (y-1 == -1 && x+1 < board.getCol() && x-1 > -1){
			board.addObject(new StaticObject(road012,0, x, y, false, gameData));
			if (board.getGrid(x, y+1).getObj() == null) board.getGrid(x, y).getObj().setFrameID(wallDown);
			else if (board.getGrid(x, y+1).getObj() != null && board.getGrid(x, y+1).getObj().isSolid()){
				if (board.getGrid(x-1, y+1).getObj() != null && board.getGrid(x-1, y+1).getObj().isSolid() && board.getGrid(x+1, y+1).getObj() == null) board.getGrid(x, y).getObj().setFrameID(wallInternDownRight);
				if (board.getGrid(x-1, y+1).getObj() == null && board.getGrid(x+1, y+1).getObj() != null && board.getGrid(x+1, y+1).getObj().isSolid()) board.getGrid(x, y).getObj().setFrameID(wallInternDownLeft);
				if (board.getGrid(x-1, y+1).getObj() != null && board.getGrid(x-1, y+1).getObj().isSolid() && board.getGrid(x+1, y+1).getObj() != null && board.getGrid(x+1, y+1).getObj().isSolid()) board.getGrid(x, y).getObj().setFrameID(wallCenter);
			}
		}
		
		// Ligne d'en bas
		if (y+1 == board.getLig() && x+1 < board.getCol() && x-1 > -1){
			board.addObject(new StaticObject(road012,0, x, y, false, gameData));
			if (board.getGrid(x, y-1).getObj() == null) board.getGrid(x, y).getObj().setFrameID(wallUp);
			else if (board.getGrid(x, y-1).getObj() != null && board.getGrid(x, y-1).getObj().isSolid()){
				if (board.getGrid(x-1, y-1).getObj() != null && board.getGrid(x-1, y-1).getObj().isSolid() && board.getGrid(x+1, y-1).getObj() == null) board.getGrid(x, y).getObj().setFrameID(wallInternUpRight);
				if (board.getGrid(x-1, y-1).getObj() == null && board.getGrid(x+1, y-1).getObj() != null && board.getGrid(x+1, y-1).getObj().isSolid()) board.getGrid(x, y).getObj().setFrameID(wallInternUpLeft);
				if (board.getGrid(x-1, y-1).getObj() != null && board.getGrid(x-1, y-1).getObj().isSolid() && board.getGrid(x+1, y-1).getObj() != null && board.getGrid(x+1, y-1).getObj().isSolid()) board.getGrid(x, y).getObj().setFrameID(wallCenter);
			}
		}
		
		// Ligne de gauche
		if (x-1 == -1 && y+1 < board.getLig() && y-1 > -1){
			board.addObject(new StaticObject(road012,0, x, y, false, gameData));
			if (board.getGrid(x+1, y).getObj() == null) board.getGrid(x, y).getObj().setFrameID(wallRight);
			else if (board.getGrid(x+1, y).getObj() != null && board.getGrid(x+1, y).getObj().isSolid()){
				if (board.getGrid(x+1, y-1).getObj() != null && board.getGrid(x+1, y-1).getObj().isSolid() && board.getGrid(x+1, y+1).getObj() == null) board.getGrid(x, y).getObj().setFrameID(wallInternDownRight);
				if (board.getGrid(x+1, y-1).getObj() == null && board.getGrid(x+1, y+1).getObj() != null && board.getGrid(x+1, y+1).getObj().isSolid()) board.getGrid(x, y).getObj().setFrameID(wallInternUpRight);
				if (board.getGrid(x+1, y-1).getObj() != null && board.getGrid(x+1, y-1).getObj().isSolid() && board.getGrid(x+1, y+1).getObj() != null && board.getGrid(x+1, y+1).getObj().isSolid()) board.getGrid(x, y).getObj().setFrameID(wallCenter);
			}
		}
		
		// Ligne de droite
		if (x+1 == board.getCol() && y+1 < board.getLig() && y-1 > -1){
			board.addObject(new StaticObject(road012,0, x, y, false, gameData));
			if (board.getGrid(x-1, y).getObj() == null) board.getGrid(x, y).getObj().setFrameID(wallLeft);
			else if (board.getGrid(x-1, y).getObj() != null && board.getGrid(x-1, y).getObj().isSolid()){
				if (board.getGrid(x-1, y-1).getObj() != null && board.getGrid(x-1, y-1).getObj().isSolid() && board.getGrid(x-1, y+1).getObj() == null) board.getGrid(x, y).getObj().setFrameID(wallInternDownLeft);
				if (board.getGrid(x-1, y-1).getObj() == null && board.getGrid(x-1, y+1).getObj() != null && board.getGrid(x-1, y+1).getObj().isSolid()) board.getGrid(x, y).getObj().setFrameID(wallInternUpLeft);
				if (board.getGrid(x-1, y-1).getObj() != null && board.getGrid(x-1, y-1).getObj().isSolid() && board.getGrid(x-1, y+1).getObj() != null && board.getGrid(x-1, y+1).getObj().isSolid()) board.getGrid(x, y).getObj().setFrameID(wallCenter);
			}
		}
		
		// Coin UL
		if (x-1 == -1 && y-1 == -1){
			board.addObject(new StaticObject(road012,0, x, y, false, gameData));
			if (board.getGrid(x+1, y+1).getObj() != null && board.getGrid(x+1, y+1).getObj().isSolid()) board.getGrid(x, y).getObj().setFrameID(wallCenter);
			else if (board.getGrid(x+1, y+1).getObj() == null) board.getGrid(x, y).getObj().setFrameID(wallInternDownRight);
		}
		
		// Coin UR
		if (x+1 == board.getCol() && y-1 == -1){
			board.addObject(new StaticObject(road012,0, x, y, false, gameData));
			if (board.getGrid(x-1, y+1).getObj() != null && board.getGrid(x-1, y+1).getObj().isSolid()) board.getGrid(x, y).getObj().setFrameID(wallCenter);
			else if (board.getGrid(x-1, y+1).getObj() == null) board.getGrid(x, y).getObj().setFrameID(wallInternDownLeft);
		}
		
		// Coin DR
		if (x+1 == board.getCol() && y+1 == board.getLig()){
			board.addObject(new StaticObject(road012,0, x, y, false, gameData));
			if (board.getGrid(x-1, y-1).getObj() != null && board.getGrid(x-1, y-1).getObj().isSolid()) board.getGrid(x, y).getObj().setFrameID(wallCenter);
			else if (board.getGrid(x-1, y-1).getObj() == null) board.getGrid(x, y).getObj().setFrameID(wallInternUpLeft);
		}
		
		// Coin DL
		if (x-1 == -1 && y+1 == board.getLig()){
			board.addObject(new StaticObject(road012,0, x, y, false, gameData));
			if (board.getGrid(x+1, y-1).getObj() != null && board.getGrid(x+1, y-1).getObj().isSolid()) board.getGrid(x, y).getObj().setFrameID(wallCenter);
			else if (board.getGrid(x+1, y-1).getObj() == null) board.getGrid(x, y).getObj().setFrameID(wallInternUpRight);
		}
		
		// Intérieur
		if (x-1 > -1 && y-1 > -1 && x+1 < board.getCol() && y+1 < board.getLig()){
			if (board.getGrid(x-1, y-1).getObj() != null && board.getGrid(x-1, y-1).getObj().isSolid()) upLeft = true;
			if (board.getGrid(x, y-1).getObj() != null && board.getGrid(x, y-1).getObj().isSolid()) up = true;
			if (board.getGrid(x+1, y-1).getObj() != null && board.getGrid(x+1, y-1).getObj().isSolid()) upRight = true;
			if (board.getGrid(x+1, y).getObj() != null && board.getGrid(x+1, y).getObj().isSolid()) right = true;
			if (board.getGrid(x+1, y+1).getObj() != null && board.getGrid(x+1, y+1).getObj().isSolid()) downRight = true;
			if (board.getGrid(x, y+1).getObj() != null && board.getGrid(x, y+1).getObj().isSolid()) down = true;
			if (board.getGrid(x-1, y+1).getObj() != null && board.getGrid(x-1, y+1).getObj().isSolid()) downLeft = true;
			if (board.getGrid(x-1, y).getObj() != null && board.getGrid(x-1, y).getObj().isSolid()) left = true;			
			if (left && right && !down && upLeft && up && upRight) board.getGrid(x, y).getObj().setFrameID(wallDown);//D		
			if (!right && up && down && upLeft && downLeft && left ) board.getGrid(x, y).getObj().setFrameID(wallRight);//R		
			if (!up && left && right && downLeft && downRight && down) board.getGrid(x, y).getObj().setFrameID(wallUp);//U		
			if (!left && right && up && down && upRight && downRight) board.getGrid(x, y).getObj().setFrameID(wallLeft);//L
			if (!downLeft && !left && !down && up && right && upRight) board.getGrid(x, y).getObj().setFrameID(wallDownLeft);//DL
			if (!upLeft && !up && !left && down && right && downRight) board.getGrid(x, y).getObj().setFrameID(wallUpLeft);//UL
			if (!right && !up && !upRight && left && down && downLeft) board.getGrid(x, y).getObj().setFrameID(wallUpRight);//UR
			if (!right && !downRight && !down && left && up && upLeft) board.getGrid(x, y).getObj().setFrameID(wallDownRight);//DR
			if (left && down && right && up && upLeft && upRight && downRight && !downLeft) board.getGrid(x, y).getObj().setFrameID(wallInternDownLeft);//IDL
			if (left && down && right && up && upLeft && upRight && !downRight && downLeft) board.getGrid(x, y).getObj().setFrameID(wallInternDownRight);//IDR
			if (left && down && right && up && !upLeft && upRight && downRight && downLeft) board.getGrid(x, y).getObj().setFrameID(wallInternUpLeft);//IUL
			if (left && down && right && up && upLeft && !upRight && downRight && downLeft) board.getGrid(x, y).getObj().setFrameID(wallInternUpRight);//IUR
			if (up && right && down && left && upLeft && upRight && downRight && downLeft) board.getGrid(x, y).getObj().setFrameID(wallCenter);//C
			else{board.addObject(new StaticObject(road012, 0, x, y, false, gameData));}
		}
	}

}
