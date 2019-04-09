package model;

public class AttackThread implements Runnable, SpriteInterface, ItemInterface{
	
	private DynamicObject attacker;
	private int hammerBonus;
	
	public AttackThread(DynamicObject attacker, int hammerBonus){
		this.attacker = attacker;
		this.hammerBonus = hammerBonus;
	}

	@Override
	public synchronized void run() {
		if (!this.attacker.isActing() && ((this.attacker.getClass() == Player.class && this.attacker.getDirection() == "NONE") || this.attacker.getClass() == Pnj.class)){
			this.attacker.setActing(true);	
			if (this.attacker.getClass() == Player.class){
				try {Thread.sleep(10);} catch (InterruptedException e) {e.printStackTrace();}		
				if (this.attacker.lastDirection == "LEFT") this.attacker.setFrameID(fightLeft1);
				if (this.attacker.lastDirection == "RIGHT") this.attacker.setFrameID(fightRight1);
				if (this.attacker.lastDirection == "UP") this.attacker.setFrameID(fightUp1);
				if (this.attacker.lastDirection == "DOWN") this.attacker.setFrameID(fightDown1);
				try {Thread.sleep(200);} catch (InterruptedException e) {e.printStackTrace();}
				if (this.attacker.lastDirection == "LEFT") this.attacker.setFrameID(fightLeft2);
				if (this.attacker.lastDirection == "RIGHT") this.attacker.setFrameID(fightRight2);
				if (this.attacker.lastDirection == "UP") this.attacker.setFrameID(fightUp2);
				if (this.attacker.lastDirection == "DOWN") this.attacker.setFrameID(fightDown2);
				try {Thread.sleep(50);} catch (InterruptedException e) {e.printStackTrace();}
				if (this.attacker.lastDirection == "LEFT") this.attacker.setFrameID(fightLeft3);
				if (this.attacker.lastDirection == "RIGHT") this.attacker.setFrameID(fightRight3);
				if (this.attacker.lastDirection == "UP") this.attacker.setFrameID(fightUp3);
				if (this.attacker.lastDirection == "DOWN") this.attacker.setFrameID(fightDown3);
				try {Thread.sleep(200);} catch (InterruptedException e) {e.printStackTrace();}
			}
			// ON RETIRE LA VIE
			this.attacker.acting = true;
			int atkX = this.attacker.caseX;
			int atkY = this.attacker.caseY;
			if (this.attacker.lastDirection == "DOWN") atkY++;
			else if (this.attacker.lastDirection == "UP") atkY--;
			else if (this.attacker.lastDirection == "RIGHT") atkX++;
			else if (this.attacker.lastDirection == "LEFT") atkX--;
			if (atkX < 0) atkX = 0;
			else if (atkX > this.attacker.board.getCol()-1) atkX = this.attacker.board.getCol()-1;
			if (atkY < 0) atkY = 0;
			else if (atkY > this.attacker.board.getLig()-1) atkY = this.attacker.board.getLig()-1;
			if (this.attacker.board.getGrid(atkX, atkY).getObj() != null){				
				if (this.attacker.board.getGrid(atkX, atkY).getObj().getClass() == Pnj.class
					&& this.attacker.board.getGrid(atkX, atkY).getObj() != this.attacker
					&& this.attacker.getClass() == Player.class){
					DynamicObject enemyPNJ = (DynamicObject)this.attacker.board.getGrid(atkX, atkY).getObj();
					int lifeToTake = -(this.attacker.atq+this.hammerBonus-enemyPNJ.def);
					if (lifeToTake >= 0) lifeToTake = -1;					
					enemyPNJ.addLife(lifeToTake);
					if (enemyPNJ.life <= 0) enemyPNJ.alive = false;
					if (this.attacker.getActiveItem().getPosition() == hammerPos
						&& this.attacker.getActiveItem().getSubPosition() == goldHammer){
						new Thread(new PoisonThread(enemyPNJ)).start();
					}
				}
				else if (this.attacker.board.getGrid(atkX, atkY).getObj().getClass() == Player.class
					&& this.attacker.board.getGrid(atkX, atkY).getObj() != this.attacker
					&& this.attacker.getClass() == Pnj.class){
					try {Thread.sleep(10);} catch (InterruptedException e) {e.printStackTrace();}		
					if (this.attacker.lastDirection == "LEFT") this.attacker.setFrameID(fightLeft1);
					if (this.attacker.lastDirection == "RIGHT") this.attacker.setFrameID(fightRight1);
					if (this.attacker.lastDirection == "UP") this.attacker.setFrameID(fightUp1);
					if (this.attacker.lastDirection == "DOWN") this.attacker.setFrameID(fightDown1);
					try {Thread.sleep(200);} catch (InterruptedException e) {e.printStackTrace();}
					if (this.attacker.lastDirection == "LEFT") this.attacker.setFrameID(fightLeft2);
					if (this.attacker.lastDirection == "RIGHT") this.attacker.setFrameID(fightRight2);
					if (this.attacker.lastDirection == "UP") this.attacker.setFrameID(fightUp2);
					if (this.attacker.lastDirection == "DOWN") this.attacker.setFrameID(fightDown2);
					try {Thread.sleep(50);} catch (InterruptedException e) {e.printStackTrace();}
					if (this.attacker.lastDirection == "LEFT") this.attacker.setFrameID(fightLeft3);
					if (this.attacker.lastDirection == "RIGHT") this.attacker.setFrameID(fightRight3);
					if (this.attacker.lastDirection == "UP") this.attacker.setFrameID(fightUp3);
					if (this.attacker.lastDirection == "DOWN") this.attacker.setFrameID(fightDown3);
					try {Thread.sleep(200);} catch (InterruptedException e) {e.printStackTrace();}
					DynamicObject enemyPlayer = (DynamicObject)this.attacker.board.getGrid(atkX, atkY).getObj();
					int lifeToTake = 0;
					if (enemyPlayer != null) {
						lifeToTake = -(this.attacker.atq+this.hammerBonus-enemyPlayer.def);
						if (lifeToTake >= 0) lifeToTake = -1;					
						enemyPlayer.addLife(lifeToTake);
						if (enemyPlayer.life <= 0) enemyPlayer.alive = false;
					}															
				}
			}
			// VIE RETIREE
			try {Thread.sleep(10);} catch (InterruptedException e) {e.printStackTrace();}
			this.attacker.setActing(false);
		}
	}
	
}