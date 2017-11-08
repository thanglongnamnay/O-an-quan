
import java.awt.*;
import java.awt.*;

import javax.swing.*;

public class ScoreBox{
	JButton scBoxBtn;
	int num;
	MainGame mainGame;
	Font fontArrow=new Font("Times New Romans",Font.PLAIN,40);
	ScoreBox(MainGame m,int team){
		mainGame=m;
		scBoxBtn=new JButton("0");
		if(team==0) scBoxBtn.setBounds(565,45,100,50);
			else scBoxBtn.setBounds(565,548,100,50);
		scBoxBtn.setFocusable(false);
		scBoxBtn.setFont(fontArrow);
		scBoxBtn.setMargin(new Insets(0,0,0,0));
		scBoxBtn.setContentAreaFilled(false);
		scBoxBtn.setBorderPainted(false);
		mainGame.add(scBoxBtn);
	}
	int getNum(){
		return num;
	}
	void change(int n){
		num=n;
		scBoxBtn.setText(Integer.toString(n));
	}
}