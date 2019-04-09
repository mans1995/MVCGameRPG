package model;

public abstract class DynamicObject extends GameObject implements Runnable{ 
	
	private static final long serialVersionUID = -529536900940430648L;
	
	protected int speed;
	protected int lvl, life, atq, def, maxLife, bagSize;
	protected String direction, lastDirection;
	protected boolean acting;
	protected Item[][] items  ;
	protected int itemPos, itemSubPos;
	protected boolean poisoned;
	private int regenerationTic;
	protected boolean inPause;
	
	
	public DynamicObject(int ID, int frameID, int caseX, int caseY, boolean solid, GameData gameData, int speed,int lvl) {
		super(ID, frameID, caseX, caseY, solid, gameData);
		this.direction = "NONE";
		this.lastDirection = "DOWN";
		this.speed = speed;
		this.lvl = lvl;
		this.acting = false;
		this.poisoned = false;
		this.setRegenerationTic(0);
		this.itemPos = 0; this.itemSubPos = 0; // { {epee0, epee1...} {protection0...} {...} {...} } -> epee0
		// Stats
		this.initStats();
		// Items
		this.bagSize = 6;
		this.items = new Item[this.bagSize][itemsPerCategory];		
		
		new Thread(this).start();
	}
	
	public void initStats(){
		this.maxLife = 30*this.lvl;
		this.life = 30*this.lvl;
		this.atq = 2*lvl;
		this.def = 4*lvl;
	}
	
	public void computeStats(){
		int lostHp = this.maxLife-this.life;
		this.maxLife = 30*this.lvl;		
		this.life = this.maxLife-lostHp;
		this.atq = 2*lvl;
		this.def = 4*lvl;
	}
	
	public void takeHammer(){this.itemPos = hammerPos;}
	public void takePotion(){this.itemPos = potionPos;}
	public void takeSpell(){this.itemPos = spellPos;}
	
	public void addItemSubPos(int c){
		this.itemSubPos += c;
		if (this.itemSubPos < 0) this.itemSubPos = 0;
		else if (this.itemSubPos > itemsPerCategory-1) this.itemSubPos %= itemsPerCategory;
	}
	
	public void setItem(Item item){
		this.items[item.getPosition()][item.getSubPosition()] = item ;
	}
	
	public void addItem(Item item){
		this.items[item.getPosition()][item.getSubPosition()].addQuantity(item.getQuantity());
	}
	
	public Item getActiveItem(){
		return this.items[this.itemPos][this.itemSubPos];
	}
	
	public void useActiveItem(){
		if (this.getActiveItem().getQuantity() > 0){
			if (this.getActiveItem().getPosition() == hammerPos){
				new Thread(new AttackThread(this, this.getActiveItem().getValueEffect())).start();
			}
			else if (this.getActiveItem().getPosition() == potionPos && this.getLife() < this.getMaxLife()){
				if (this.getActiveItem().getSubPosition() == healPotion){
					this.addLife(this.getActiveItem().getValueEffect());
					this.getActiveItem().addQuantity(-1);
				}
				else if (this.getActiveItem().getSubPosition() == regenPotion && this.regenerationTic == 0){
					new Thread(new RegenerationThread(this)).start();
					this.getActiveItem().addQuantity(-1);
				}
			}
			else if (this.getActiveItem().getPosition() == spellPos){
				if (this.getActiveItem().getSubPosition() == damageSpell){
					new Thread(new SpellThread(this, this.getActiveItem().getValueEffect())).start();
					this.getActiveItem().addQuantity(-1);
				}
			}
		}
		
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
				try {
					Thread.sleep(10);// MOI AUSSI IL M'A FAIT LE COUP
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				long startTime = System.nanoTime();
				
				if(this.getClass().equals(Pnj.class)){
					Pnj thisPNJ = (Pnj)this;
					if (!thisPNJ.findPlayer()) thisPNJ.randomDirection(new String[]{"LEFT", "RIGHT", "UP", "DOWN"});
				}
				this.setNextCase();
				new Thread(new AttackThread(this, this.getActiveItem().getValueEffect())).start();
				int deltaX = (this.nextCaseX - this.caseX)*this.speed;
				int deltaY = (this.nextCaseY - this.caseY)*this.speed;
				if (!this.direction.equals("NONE")) this.lastDirection = this.direction;
				if (this.caseX != this.nextCaseX || this.caseY != this.nextCaseY){
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
					this.board.getGrid(this.caseX, this.caseY).setSolid(false);
					this.board.getGrid(this.caseX, this.caseY).setObj(null);
					this.direction = "NONE";
					this.caseX = this.nextCaseX;
					this.caseY = this.nextCaseY;
				}	
				else{
					this.spriteUpdate();			
				}
				new Thread(new AttackThread(this, this.getActiveItem().getValueEffect())).start();
			}
		}
		this.board.getPlayer().addXp(this.lvl);
		this.board.getPlayer().addMoney(20);
		// si le thread n'est plus actif on supprime l'objet ?
		int i = this.caseX;
		int j = this.caseY;
		this.getBoard().removeObject(this);	
		this.board.addObject(new StaticObject(wall001, 0, i,j, true, this.gameData));

		
	}
	
	
	public void setNextCase() {
		if (this.posX % this.gameData.getCaseSize() == 0 && this.posY % this.gameData.getCaseSize() == 0 && this.direction != "NONE"){
			Integer[] nextPosition = this.findNextCasePosition();
			if (this.checkBorders(nextPosition) && !this.board.getGrid(nextPosition[0], nextPosition[1]).isSolid()){
				this.nextCaseX = nextPosition[0];
				this.nextCaseY = nextPosition[1];
			}
		}	
	 }

	public boolean checkBorders(Integer[] caseToReach){
		return (caseToReach[0] >= 0 && caseToReach[1] >= 0 && caseToReach[0] <= this.board.getCol()-1 && caseToReach[1] <= this.board.getLig()-1);
	}

	public Integer[] findNextCasePosition() {
		int caseToReachX = this.caseX;
		int caseToReachY = this.caseY;
		if(this.direction == "UP") 			caseToReachY = this.caseY-1;
		else if(this.direction == "DOWN")	caseToReachY = this.caseY+1;
		else if(this.direction == "LEFT") 	caseToReachX = this.caseX-1;
		else if(this.direction == "RIGHT") 	caseToReachX = this.caseX+1;		
		return new Integer[]{caseToReachX, caseToReachY};
	}

	public void spriteUpdate() {
		if (this.lastDirection == "DOWN"){
			if (8 < this.posY % 64 && this.posY % 64 < 24) this.frameID = down1;
			else if (40 < this.posY % 64 && this.posY % 64 < 56) this.frameID = down2;
			else this.frameID = down0;
		}
		if (this.lastDirection == "UP"){
			if (8 < this.posY % 64 && this.posY % 64 < 24) this.frameID = up1;
			else if (40 < this.posY % 64 && this.posY % 64 < 56) this.frameID = up2;
			else this.frameID = up0;
		}
		else if (this.lastDirection == "RIGHT"){
			if (8 < this.posX % 64 && this.posX % 64 < 24) this.frameID = right1;
			else if (40 < this.posX % 64 && this.posX % 64 < 56) this.frameID = right2;
			else this.frameID = right0;
		}
		else if (this.lastDirection == "LEFT"){
			if (8 < this.posX % 64 && this.posX % 64 < 24) this.frameID = left1;
			else if (40 < this.posX % 64 && this.posX % 64 < 56) this.frameID = left2;
			else this.frameID = left0;
		}
	}
	
	public void addLife(int value){
		// value can be negative
		this.life += value;
		if (this.life > this.maxLife) this.life = this.maxLife;
		else if (this.life < 0) {
			this.life = 0;
			this.alive = false;
		}
	}
	
	public void addRegenerationTic(int c){
		this.regenerationTic += c;
	}
	
		////////////////////////Getters & Setters	////////////////////////
	
	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public int getLvl() {
		return lvl;
	}

	public void setLvl(int lvl) {
		this.lvl = lvl;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public int getAtq() {
		return atq;
	}

	public void setAtq(int atq) {
		this.atq = atq;
	}

	public int getDef() {
		return def;
	}

	public void setDef(int def) {
		this.def = def;
	}

	public int getMaxLife() {
		return maxLife;
	}

	public void setMaxLife(int maxLife) {
		this.maxLife = maxLife;
	}

	public boolean isActing() {
		return acting;
	}

	public void setActing(boolean acting) {
		this.acting = acting;
	}

	public String getLastDirection() {
		return lastDirection;
	}

	public void setLastDirection(String lastDirection) {
		this.lastDirection = lastDirection;
	}
	
	public Item[][] getItems() {
		return items;
	}

	public void setItems(Item[][] items) {
		this.items = items;
	}

	public boolean isPoisoned() {
		return poisoned;
	}

	public void setPoisoned(boolean poisoned) {
		this.poisoned = poisoned;
	}

	public int getRegenerationTic() {
		return regenerationTic;
	}

	public void setRegenerationTic(int regenerationTic) {
		this.regenerationTic = regenerationTic;
	}
	
	public boolean isInPause() {
		return inPause;
	}

	public void setInPause(boolean inPause) {
		this.inPause = inPause;
	}	
}
