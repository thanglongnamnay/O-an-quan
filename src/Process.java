import java.awt.Color;

import javax.swing.JOptionPane;

public class Process {
	MainGame mainGame;
	Box[] box=new Box[12];
	ScoreBox[] scBox=new ScoreBox[12];
	public Process(MainGame m){
		mainGame=m;
	}
	public void reDraw() {
	    for(int i=0;i<12;i++) box[i]=new Box(mainGame,this,i);
		scBox[0]=new ScoreBox(mainGame,0);
		scBox[1]=new ScoreBox(mainGame,1);
		for(int i=0;i<12;i++) box[i].change(mainGame.getNumberInBox());
		box[5].change(mainGame.getNumberInScoreBox());
		box[11].change(mainGame.getNumberInScoreBox());
		scBox[0].change(0);
		scBox[0].change(0);
	}
	public void removeAllArrow() {
		for(int i=0;i<12;i++) box[i].removeArrow();
	}
	public void move(int pos,int direction, boolean isEaten){
		if(pos==5||pos==11) mainGame.nextTurn();
		else if (box[pos].getNum()>0){
			if(!isEaten){
				int num=box[pos].getNum();
				box[pos].setColor(Color.BLUE);
				for(int i=1;i<=num;i++) {
					int temp=calNewPos(pos,i*direction);
					box[pos].change(box[pos].getNum()-1);
					box[temp].change(box[temp].getNum()+1);
					try {
						mainGame.paintImmediately(mainGame.getVisibleRect());
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				box[pos].setColor(Color.BLACK);
				int vtSau=calNewPos(pos,(num+1)*direction);
				move(vtSau,direction,false);
			}else mainGame.nextTurn();
		}else{
			if(box[calNewPos(pos,direction)].getNum()>0){
				kill(mainGame.getCurTeam(),calNewPos(pos,direction));
				move(calNewPos(pos,direction*2),direction,true);
			}else mainGame.nextTurn();
		}
	}
	public int check(int team) {
		if(box[5].getNum()==0&&box[11].getNum()==0) return -1;
		if(scBox[team].getNum()<5&&total(team)==0) return 0;
		if(scBox[team].getNum()>5&&total(team)==0) return 1;
		return 2;
	}
	int total(int team) {
		if(team==0)	return box[6].getNum()+box[7].getNum()+box[8].getNum()+box[9].getNum()+box[10].getNum();
		else return box[0].getNum()+box[1].getNum()+box[2].getNum()+box[3].getNum()+box[4].getNum();
	}
	int calNewPos(int src,int step){
		return (src+1200+step)%12;
	}
	void kill(int team,int pos){
		int temp=scBox[team].getNum();
		scBox[team].change(temp+box[pos].getNum());
		box[pos].change(0);
	}
	void nextTurn() {
		int result=check(mainGame.getCurTeam());
		if(result==-1) {
			int winTeam;
			if(scBox[0].getNum()>scBox[1].getNum()) winTeam=0;
			else if(scBox[0].getNum()<scBox[1].getNum()) winTeam=1;
			else winTeam=-1;
			victory(winTeam);
		}
		if(result==0) victory(1-mainGame.getCurTeam());
		if(result==1) spread(mainGame.getCurTeam());
	}
	void victory(int team) {
		if(team>-1) JOptionPane.showConfirmDialog(mainGame,"Người chơi "+(team+1)+" thắng","Xong phim",JOptionPane.DEFAULT_OPTION);
		else 
			JOptionPane.showConfirmDialog(mainGame,"Hoà","Game over",1);
		mainGame.newGame();
	}
	void spread(int team) {
		if(team==0){
			for(int i=6;i<11;i++) box[i].change(1);
			int temp=scBox[team].getNum()-5;
			scBox[team].change(temp);
		}else {
			for(int i=0;i<5;i++) box[i].change(1);
			int temp=scBox[team].getNum()-5;
			scBox[team].change(temp);
		}
	}
}
