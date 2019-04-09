package model;

public class SpellThread implements Runnable{
	
	private DynamicObject attacker;
	
	public SpellThread(DynamicObject attacker, int spellBonus) {
		this.attacker = attacker;
	}
	
	// ATTENTION AVEC LIG/COL !!!!!!!!!!!!!!
	public synchronized void run(){
		if (this.attacker.getDirection() == "NONE" && !this.attacker.isActing()){
			long startTime = System.nanoTime();
			int targetX = this.attacker.caseX;
			int targetY = this.attacker.caseY;
			this.attacker.setActing(true);		
			for (int i = targetX-1; i < targetX+2; i++){
				for (int j = targetY-1; j < targetY+2; j++){
				if (i > 0 && i < this.attacker.getBoard().getCol()
					&& j > 0 && j < this.attacker.getBoard().getLig()){
					if (this.attacker.getBoard().getGrid(i, j).getObj() != null
						&& this.attacker.getBoard().getGrid(i, j).getObj().getClass() == Pnj.class){
						DynamicObject enemyPlayer = (DynamicObject)this.attacker.board.getGrid(i, j).getObj();
						int lifeToTake = 0;
						if (enemyPlayer != null) {
							lifeToTake = -(enemyPlayer.maxLife);
							if (lifeToTake >= 0) lifeToTake = -1;					
							enemyPlayer.addLife(lifeToTake);
							if (enemyPlayer.life <= 0) enemyPlayer.alive = false;
							}
						}
					}
				}
			}
			long elapsedTime = System.nanoTime()-startTime;
			try {Thread.sleep(500-(long)(elapsedTime/1000000000d));} catch (InterruptedException e) {e.printStackTrace();}
			this.attacker.setActing(false);
		}
	}

}
