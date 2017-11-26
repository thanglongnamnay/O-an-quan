package master;
import javax.swing.*;
import java.awt.*;
  
public class MainGame extends JPanel {  
	Process process;
	int 
		currentTeam=0,
		numberInBox=5,
		numberInScoreBox=10,
		multiple=GUI.multiple;
	final int 	
		xUnit=GUI.xUnit,
		yUnit=GUI.yUnit;
	public int speed=4;
	public MainGame() {
		init();
	}
	void init() {
		setLocation(0,0);
		setLayout(null);
		process=new Process(this);
		process.init();
		process.reDraw();
		resize();
	}
	void resize() {
		multiple=GUI.multiple;
		setSize(GUI.WIDTH,GUI.HEIGHT);
		process.resize();
	}
	void newGame() {
		int option = JOptionPane.showConfirmDialog(this, "Bạn có mún chơi lại ko?","Retard alert",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE);
		if(option==JOptionPane.YES_OPTION)	process.reDraw();
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
        Image i=new ImageIcon("src\\images\\background.jpg").getImage();
        g2d.drawImage(i, 0, 0, xUnit*multiple, yUnit*multiple, null);
        System.out.println(GUI.class.getResource("../"));
    }
} 
