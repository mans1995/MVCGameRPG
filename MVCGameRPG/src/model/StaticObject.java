package model;

public class StaticObject extends GameObject{
	
	private static final long serialVersionUID = 8789827499582270619L;
	
	public StaticObject(int ID, int frameID ,int caseX, int caseY, boolean solid, GameData gameData) {
		super(ID, frameID, caseX, caseY, solid, gameData);		
	}	
	
	public Boolean action(){
		Boolean action = false ;
		if(this.ID == house001 && this.frameID == 0){
			this.board.getGrid(this.board.getPlayer().getCaseX(), this.board.getPlayer().getCaseY()).setObj(null);
			this.board.getGrid(this.board.getPlayer().getCaseX(), this.board.getPlayer().getCaseY()).setSolid(false);

			this.board.getPlayer().setCaseX(14);
			this.board.getPlayer().setCaseY(13);
			this.board.getPlayer().setNextCaseX(14);
			this.board.getPlayer().setNextCaseY(13);
			this.board.getPlayer().setPosX(14*this.gameData.getCaseSize());
			this.board.getPlayer().setPosY(13*this.gameData.getCaseSize());
			
			this.board.getPlayer().setBoard(this.gameData.getBoards().get(1));
			this.gameData.getBoards().get(1).updatePlayer(this.board.getPlayer());
			
			this.gameData.setCamera(false);
			this.gameData.setBoardIndice(1);
			this.board.removeObject(this.board.getPlayer());

			action = true;
		}
		else if(this.ID == door001 && this.frameID == 0){
			this.board.getGrid(this.board.getPlayer().getCaseX(), this.board.getPlayer().getCaseY()).setObj(null);
			this.board.getGrid(this.board.getPlayer().getCaseX(), this.board.getPlayer().getCaseY()).setSolid(false);

			this.board.getPlayer().setCaseX(7);
			this.board.getPlayer().setCaseY(7);
			this.board.getPlayer().setNextCaseX(7);
			this.board.getPlayer().setNextCaseY(7);
			this.board.getPlayer().setPosX(7*this.gameData.getCaseSize());
			this.board.getPlayer().setPosY(7*this.gameData.getCaseSize());

			
			this.board.getPlayer().setBoard(this.gameData.getBoards().get(0));
			this.gameData.getBoards().get(0).updatePlayer(this.board.getPlayer());

			this.gameData.setCamera(true);
			this.gameData.setBoardIndice(0);
			this.board.removeObject(this.board.getPlayer());

			action = true;
		}
		else if(this.ID == door002 && this.frameID == 0){
			this.gameData.newTransition();
			action = true;
		}
		else if(this.ID == door003 && this.frameID == 0){
			this.board.getGrid(this.board.getPlayer().getCaseX(), this.board.getPlayer().getCaseY()).setObj(null);
			this.board.getGrid(this.board.getPlayer().getCaseX(), this.board.getPlayer().getCaseY()).setSolid(false);

			this.board.getPlayer().setCaseX(21);
			this.board.getPlayer().setCaseY(9);
			this.board.getPlayer().setNextCaseX(21);
			this.board.getPlayer().setNextCaseY(9);
			this.board.getPlayer().setPosX(21*this.gameData.getCaseSize());
			this.board.getPlayer().setPosY(9*this.gameData.getCaseSize());
			
			this.board.getPlayer().setBoard(this.gameData.getBoards().get(0));
			this.gameData.getBoards().get(0).updatePlayer(this.board.getPlayer());
			
			this.gameData.setBoardIndice(0);
			this.board.removeObject(this.board.getPlayer());
			
			for(GameObject obj : this.board.getObjects()){
				if (obj.getClass() == Player.class || obj.getClass() == Pnj.class){
					DynamicObject objDYN = (DynamicObject)obj;
					objDYN.setInPause(true);
				}
			}
			
			this.gameData.setDifficulty(this.gameData.getDifficulty()+1);

			action = true;
		}
		else if(this.ID == wall001){
			this.gameData.getBoards().get(this.gameData.getBoardIndice()).getPlayer().addItem(new Item(potionPos, healPotion, 1, 20, 15, 10));
			this.gameData.getBoards().get(this.gameData.getBoardIndice()).removeObject(this);
		}
			
		this.interacts = false ;
		
		return action;
	}
}
