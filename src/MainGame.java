
import javax.swing.*;
import java.awt.*;
  
public class MainGame extends JPanel {  
	Process process;
	int currentTeam=0,numberInBox=5,numberInScoreBox=10;
	public MainGame() {
		init();
	}
	void init() {
		setSize(GUI.WIDTH,GUI.HEIGHT);
		setLocation(0,0);
		
		setLayout(null);
		process=new Process(this);
		newGame();
	}
	void newGame() {
		process.reDraw();
	}
	public void nextTurn() {
		currentTeam=1-currentTeam;
		process.nextTurn();
		
	}
	public int getCurTeam() {
		return currentTeam;
	}
	public int getNumberInBox() {
		return numberInBox;
	}
	public int getNumberInScoreBox() {
		return numberInScoreBox;
	}
	public void setNumberInBox(int n) {
		numberInBox=n;
	}
	public void setNumberInScoreBox(int n) {
		numberInScoreBox=n;
	}

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Image i=new ImageIcon("src\\background.jpg").getImage();
        g2d.drawImage(i, 0, -30, GUI.WIDTH, GUI.HEIGHT, null);
        Image j = new ImageIcon("src\\stone.jpg").getImage();
        g2d.drawImage(j, 100, 100, 100, 100, null);
    }
} 
