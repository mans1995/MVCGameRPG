package view;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import control.Controller;
import model.GameData;

@SuppressWarnings("serial")
public class GameFrame extends JFrame {
  static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

  public static int width = 896;
  public static int height = 608;

  private GamePanel gamePanel;

  public GameFrame(GameData gameData) {
    this.setTitle("GameFrame");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setResizable(false);
    this.setSize(GameFrame.width + 6, GameFrame.height + 29);
    this.setLocation((int) screenSize.getWidth() - GameFrame.width - 100, 50);

    this.gamePanel = new GamePanel(gameData);
    this.setContentPane(gamePanel);

    this.setVisible(true);

    // gameData.gp = this.gamePanel ;
  }

  public void setListeners(Controller controller) {
    this.gamePanel.addKeyListener(controller);
    this.gamePanel.addMouseListener(controller);
  }

  // GETTERS ANS SETTERS

  public GamePanel getGamePanel() {
    return gamePanel;
  }

  public void setGamePanel(GamePanel gamePanel) {
    this.gamePanel = gamePanel;
  }
}
