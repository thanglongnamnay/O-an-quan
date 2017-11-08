import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import javax.swing.*;

public class Box implements ActionListener{
	private JButton boxBtn, lArrow, rArrow;
	private JLabel numLabel=new JLabel("5");
	private int num=5;
	private static Font fontArrow=new Font("Times New Romans",Font.PLAIN,40);
	private static Font fontStone=new Font("Times New Romans",Font.BOLD,30);
	private int pos,stoneCount;
	private Process process;
	private MainGame mainGame;
	private JLabel[] stone=new JLabel[69];
	final int NUM_LABEL_SIZE=55;
	Box(MainGame m,Process p, int pos){
		mainGame=m;
		process=p;
		this.pos=pos;
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
		if(col!=0&&col!=6) boxBtn.setBounds(145+col*155,210+row*145,70,70);
			else boxBtn.setBounds(155+col*155, 295, 50, 50);
		setOutlook(boxBtn);
		boxBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				process.removeAllArrow();
				int team=mainGame.getCurTeam();
				if(team==0&&pos>5&&pos<11&&num>0) showArrow();
				if(team==1&&pos<6&&num>0) showArrow();
			}
		});
		if(col!=0&&col!=6) numLabel.setBounds(110+col*155,175+row*145,NUM_LABEL_SIZE,NUM_LABEL_SIZE);
		else numLabel.setBounds(200+col*140,200,NUM_LABEL_SIZE,NUM_LABEL_SIZE);
		numLabel.setFont(fontArrow);

		for(int i=1;i<69;i++) {
			stone[i]=new JLabel("\u2B24");
			stone[i].setFont(fontStone);
			Random r=new Random();
			stone[i].setForeground(Color.getHSBColor(r.nextFloat()*255,r.nextFloat()*255,r.nextFloat()*255));
			if(col!=0&&col!=6) stone[i].setBounds(115+col*155+r.nextInt(100),180+row*145+r.nextInt(80),80,80);
			else stone[i].setBounds(115+col*155+r.nextInt(100),245+r.nextInt(80),80,80);
		}
		mainGame.add(numLabel);
		mainGame.add(boxBtn);
	}
	void change(int n){
		removeAllStone();
		num=n;
		numLabel.setText(Integer.toString(num));
		for(int i=1; i<=num; i++) mainGame.add(stone[i]);
	}
	void removeAllStone() {
		for(int i=1; i<=num; i++) mainGame.remove(stone[i]);
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
		rArrow.setBounds(252+col*155,220+row*140,50,50);
		lArrow.setBounds(202+(col-1)*155,220+row*140,50,50);
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