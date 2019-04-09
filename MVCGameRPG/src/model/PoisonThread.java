package model;

public class PoisonThread implements Runnable{
	
	private DynamicObject target;
	
	public PoisonThread(DynamicObject target){
		this.target = target;
	}

	@Override
	public synchronized void run() {
		target.setPoisoned(true);
		while (target.isPoisoned() && target != null){
			target.addLife(-1);
			try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
		}
	}

}
