package game;

import control.Controller;
import model.GameData;
import view.GameFrame;

public class MainLauncher {

	public static void main(String[] args) {
		
		GameData gameData = new GameData();
		GameFrame gf = new GameFrame(gameData);
		
		Controller controller = new Controller(gameData);
		gf.setListeners(controller);
		
		while (true) {
			gf.getGamePanel().repaint();
			Thread.yield();						
		}
	}

}
