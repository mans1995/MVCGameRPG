package model;

public class RegenerationThread implements Runnable{
	
	private DynamicObject target;
	
	public RegenerationThread(DynamicObject target){
		this.target = target;
	}

	@Override
	public synchronized void run() {
		while (target != null && target.getRegenerationTic() < 100){
			if (target.getRegenerationTic()%10==0)target.addLife(5);
			target.addRegenerationTic(1);
			try {Thread.sleep(100);} catch (InterruptedException e) {e.printStackTrace();}
		}
		target.setRegenerationTic(0);
	}

}
