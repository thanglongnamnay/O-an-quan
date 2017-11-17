import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import javax.swing.*;

public class Box implements ActionListener{
	private JButton boxBtn, lArrow, rArrow;
	private JLabel numLabel=new JLabel("5");
	private int 
		num=5,
		pos;
	boolean[] isStone=new boolean[70];
	private float mul;
	final int NUM_LABEL_SIZE=14;
	private static Font 
		fontArrow;
	private Process process;
	private MainGame mainGame;
	private JLabel[] stone=new JLabel[69];
	Box(MainGame m,Process p, int pos){
		mainGame=m;
		process=p;
		mul=mainGame.multiple/20;
		fontArrow=new Font("Times New Romans",Font.PLAIN,(int)(10*mul));
		this.pos=pos;
		
		for(int i=0;i<10;i++) isStone[i]=false;
		if(pos<5) 
			for(int i=0;i<5;i++) isStone[5*pos+i]=true;
		else if(pos==5)
			for(int i=0;i<10;i++) isStone[25+i]=true;
		else if(pos<11)
			for(int i=0;i<5;i++) isStone[5*(pos+1)+i]=true;
		else 
			for(int i=0;i<10;i++) isStone[60+i]=true;
			
		create();
		createArrow();
		mainGame.add(boxBtn);
	}
	void create() { //pos = 0~11
		boxBtn=new JButton();
		boxBtn.setFocusable(false);
		int col,row;
		if(pos<6) col=pos+1;
			else if(pos<11) col=11-pos;
				else col=0;
		if(pos<6) row=1;
			else row=0;
		if(col!=0&&col!=6) boxBtn.setBounds((int)(38*mul+col*39*mul),(int)(58*mul+row*36*mul),(int)(18*mul),(int)(18*mul));
		setOutlook(boxBtn);
		boxBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				process.removeAllArrow();
				int team=mainGame.getCurTeam();
				if(team==0&&pos>5&&pos<11&&num>0) showArrow();
				if(team==1&&pos<6&&num>0) showArrow();
			}
		});
		if(col!=0&&col!=6) numLabel.setBounds((int)(28*mul+col*39*mul),(int)(52*mul+row*36*mul),(int)(NUM_LABEL_SIZE*mul),(int)(NUM_LABEL_SIZE*mul));
		else numLabel.setBounds((int)(50*mul+col*35*mul),(int)(58*mul),(int)(NUM_LABEL_SIZE*mul),(int)(NUM_LABEL_SIZE*mul));
		numLabel.setFont(fontArrow);
		mainGame.add(numLabel);
		mainGame.add(boxBtn);
	}
	void change(int n){
		num=n;
		numLabel.setText(Integer.toString(num));
	}

	void createArrow() {
		int row,col;
		if(pos<6) col=pos+1;
			else if(pos<11) col=11-pos;
				else col=0;
		if(pos<6) row=1;
			else row=0;
		rArrow=new JButton("\u25B6");
		lArrow=new JButton("\u25C0");
		ActionListener r=new ActionListener(){
			public void actionPerformed(ActionEvent e){
				int chieu=(row==0)?-1:1;
				removeArrow();
				process.move(pos,chieu,false);
			}
		};
		ActionListener l=new ActionListener(){
			public void actionPerformed(ActionEvent e){
				int chieu=(row==0)?1:-1;
				removeArrow();
				process.move(pos,chieu,false);
			}
		};
		rArrow.setBounds((int)(66*mul+col*39*mul),(int)(63*mul+row*35*mul),(int)(14*mul),(int)(14*mul));
		lArrow.setBounds((int)(54*mul+(col-1)*39*mul),(int)(63*mul+row*35*mul),(int)(14*mul),(int)(14*mul));
		setOutlook(rArrow);
		setOutlook(lArrow);
		rArrow.addActionListener(r);
		lArrow.addActionListener(l);
	}
	void showArrow() {
		mainGame.add(rArrow);
		mainGame.add(lArrow);
		mainGame.repaint();
	}
	void removeArrow() {
		try {
			mainGame.remove(lArrow);
			mainGame.remove(rArrow);
		}catch(Exception e) {
			JOptionPane.showConfirmDialog(null, e);
		}
		mainGame.repaint();
	}
	void setOutlook(JButton b){
		b.setFocusPainted(false);
		b.setFont(fontArrow);
		b.setMargin(new Insets(0,0,0,0));
		b.setHorizontalAlignment(SwingConstants.LEFT);
		b.setVerticalAlignment(SwingConstants.TOP);
		b.setContentAreaFilled(false);
		b.setBorderPainted(false);
	}
	int getNum() {
		return num;
	}
	void setColor(Color c) {
		boxBtn.setForeground(c);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}