
import java.awt.*;
import java.awt.*;

import javax.swing.*;

public class ScoreBox{
	JLabel scBoxBtn;
	int num;
	float mul=GUI.multiple/20;
	boolean[] isStone=new boolean[70];
	MainGame mainGame;
	Font fontArrow=new Font("Times New Romans",Font.PLAIN,40);
	ScoreBox(MainGame m,int team){
		mainGame=m;
		scBoxBtn=new JLabel("0");
		if(team==0) scBoxBtn.setBounds((int)(141*mul),(int)(11*mul),(int)(25*mul),(int)(13*mul));
			else scBoxBtn.setBounds((int)(141*mul),(int)(136*mul),(int)(25*mul),(int)(13*mul));
		
		scBoxBtn.setFocusable(false);
		scBoxBtn.setFont(fontArrow);
		for(int i=0;i<70;i++) isStone[i]=false;
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