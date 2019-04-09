package model;

public class Player extends DynamicObject {

	private static final long serialVersionUID = 5550896006410769035L;

	private int xp, money;
	private boolean isRunning ;
	private boolean startRunning ; 
	private boolean stopRunning ;	
	
	public Player(int ID, int frameID,int caseX, int caseY, boolean solid, GameData gameData, int speed, int lvl) {
		super(ID, frameID, caseX, caseY, solid, gameData, speed, lvl);		
		
		this.xp = 0;
		this.money = 0;	
		
		this.isRunning = false ;
	}
	
	@Override
	public synchronized void run(){		
		while (this.alive){
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (!this.acting && !this.inPause){
				long startTime = System.nanoTime();
				this.setNextCase();
				this.speedUpdate();
				int deltaX = (this.nextCaseX - this.caseX)*this.speed;
				int deltaY = (this.nextCaseY - this.caseY)*this.speed;
				if (!this.direction.equals("NONE")) this.lastDirection = this.direction;
				////// sinon ça ne marche pas !!!!! -> PEUT ETRE A RETIRER
				try {
					Thread.sleep(10);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}			
				if (this.caseX!= this.nextCaseX || this.caseY != this.nextCaseY){
					this.board.getGrid(this.nextCaseX, this.nextCaseY).setSolid(true);
					this.board.getGrid(this.nextCaseX, this.nextCaseY).setObj(this);
					for (int i = 0; i < this.gameData.getCaseSize(); i += this.speed){
						if (this.alive){
							this.posX += deltaX;
							this.posY += deltaY;
							this.spriteUpdate();
							long elapsedTime = System.nanoTime()-startTime;
							startTime = System.nanoTime();
							try {
								Thread.sleep(33-(long)(elapsedTime/1000000000d));
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
					if (this.caseX!= this.nextCaseX || this.caseY != this.nextCaseY){
						this.board.getGrid(this.caseX, this.caseY).setSolid(false);
						this.board.getGrid(this.caseX, this.caseY).setObj(null);
						this.caseX = this.nextCaseX;
						this.caseY = this.nextCaseY;
					}
					
					if(this.posX % 32 != 0 )
						this.posX = this.caseX * this.gameData.getCaseSize();
					if(this.posY % 32 != 0 )
						this.posY = this.caseY * this.gameData.getCaseSize();
				}
				else{
					this.spriteUpdate();
				}
			}
		}
		this.gameData.setState("MENU");
	}
	
	public void moveUP() 			{this.direction = "UP";}
	public void moveDOWN()			{this.direction = "DOWN";}
	public void moveLEFT()			{this.direction = "LEFT";}
	public void moveRIGHT()			{this.direction = "RIGHT";}
	public void stopMove() 			{this.direction = "NONE";}
	
	public void buyItem(){
		if (this.money > this.getActiveItem().getPrice() && this.gameData.getBoardIndice() == 1){
			if(this.getActiveItem().getQuantity() < this.getActiveItem().getQuantityMax()){
			this.getActiveItem().addQuantity(+1);
			this.money -= this.getActiveItem().getPrice();
			}
		}
	}
	
	public void throwObject(){
		this.getActiveItem().addQuantity(-1);
	}
	
	public void startRunning() {
		if(!this.isRunning) {
			this.startRunning = true ; 
			this.isRunning = true;
			}
		}
	public void stopRunning() {
		if(this.isRunning) {
			this.isRunning = false ; 
			this.stopRunning = true ; 
		}
	}
	
	public void speedUpdate(){
		if(this.startRunning){
			this.speed *= 2;
			this.startRunning = false ; 
		}
		if(this.stopRunning){
			this.speed /= 2;
			this.stopRunning = false ; 
		}
	}
	public void getInteraction() {
		boolean interact = false ;
		int i = -1 ;
		while(i <= 1 && !interact) {
			if(!checkBorders(this.caseX+i,this.caseY,this.board.getLig(),this.board.getCol()))
				if(this.board.getGrid(this.caseX+i, this.caseY).getObj() != null){
					interact = this.board.getGrid(this.caseX+i, this.caseY).getObj().action();
				}
			if(!checkBorders(this.caseX,this.caseY+i,this.board.getLig(),this.board.getCol()))
				if(this.board.getGrid(this.caseX, this.caseY+i).getObj() != null){
					interact = this.board.getGrid(this.caseX, this.caseY+i).getObj().action();
				}
			i+= 2 ;
			
		}
		int j = -2 ;
		while(j <= 2 && !interact) {
			if(!checkBorders(this.caseX+i,this.caseY,this.board.getLig(),this.board.getCol()))
				if(this.board.getGrid(this.caseX+i, this.caseY).getObj() != null){
					interact = this.board.getGrid(this.caseX+i, this.caseY).getObj().action();
				}
			if(!checkBorders(this.caseX,this.caseY+i,this.board.getLig(),this.board.getCol()))
				if(this.board.getGrid(this.caseX, this.caseY+i).getObj() != null){
					interact = this.board.getGrid(this.caseX, this.caseY+i).getObj().action();
				}
			j+=4 ;
		}
		this.interacts = false ;
	}
	
	public boolean checkBorders(int caseX, int caseY, int lig, int col){
		boolean res = false ;
		if(caseX < 0 ) 
			res = true ;
		else if(caseY < 0 )
			res = true ;
		//-2 puisqu'on on entoure la carte avec qcch
		else if(caseX > col -1)
			res = true ;	
		else if(caseY > lig -1)
			res = true ;
		return res ;
	}
	
	public void addMoney(int value){
		this.money += value;
	}
	
	public void addXp(int value){
		this.xp += value;
		if (this.lvl <= 0) this.lvl = 1;
		this.lvl = Math.max(1, (int)(Math.pow(this.xp, (1/2d))));
		if (this.lvl <= 0) this.lvl = 1;
		this.computeStats();		
	}

	////////////////////////	Getters & Setters	////////////////////////	

	public int getBagSize() {
		return bagSize;
	}

	public void setBagSize(int bagSize) {
		this.bagSize = bagSize;
	}

	public int getMaxLife() {
		return maxLife;
	}

	public void setMaxLife(int maxLife) {
		this.maxLife = maxLife;
	}

	public int getXp() {
		return xp;
	}

	public void setXp(int xp) {
		this.xp = xp;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	
}

