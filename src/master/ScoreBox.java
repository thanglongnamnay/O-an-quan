package master;
import java.awt.*;
import javax.swing.*;

public class ScoreBox{
	JLabel scBoxLabel,turnLabel;
	ImageIcon flag;
	int num,team;
	float mul=GUI.multiple/20;
	boolean[] isStone=new boolean[70];
	MainGame mainGame;
	Font fontArrow=new Font("Times New Romans",Font.PLAIN,(int)(mul*10));
	ScoreBox(MainGame m,int team){
		this.team=team;
		mainGame=m;
		scBoxLabel=new JLabel("0");
		scBoxLabel.setFocusable(false);
		scBoxLabel.setFont(fontArrow);
		mainGame.add(scBoxLabel);
		flag=new ImageIcon("src\\images\\flag"+Integer.toString(2-team)+".jpg");
		turnLabel=new JLabel(flag);
		turnLabel.setFocusable(false);
		turnLabel.setFont(fontArrow);
		turnLabel.setFocusable(false);
		resetStone();
	}
	void resize() {
		mul=GUI.multiple/20;
		fontArrow=new Font("Times New Romans",Font.PLAIN,(int)(mul*10));
		if(team==0) {
			scBoxLabel.setBounds(
				(int)(141*mul),
				(int)(11*mul),
				(int)(25*mul),
				(int)(13*mul));
			turnLabel.setBounds(
				(int)(183*mul),
				(int)(9*mul),
				flag.getIconWidth(),
				flag.getIconHeight());
		}
		else {
			scBoxLabel.setBounds(
				(int)(141*mul),
				(int)(136*mul),
				(int)(25*mul),
				(int)(13*mul));
			turnLabel.setBounds(
				(int)(183*mul),
				(int)(134*mul),
				flag.getIconWidth(),
				flag.getIconHeight());
		}
		scBoxLabel.setFont(fontArrow);
	}
	int getNum(){
		return num;
	}
	void resetStone() {
		for(int i=0;i<70;i++) isStone[i]=false;
	}
	void change(int n){
		num=n;
		scBoxLabel.setText(Integer.toString(n));
	}
	void setTurn(boolean turn) {
		if (turn==true) mainGame.add(turnLabel);
		else mainGame.remove(turnLabel);
	}
}
