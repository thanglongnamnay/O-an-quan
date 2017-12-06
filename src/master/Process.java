package master;
import java.awt.Color;

import javax.swing.JOptionPane;

public class Process {
	MainGame mainGame;
	Box[] box=new Box[12];
	ScoreBox[] scBox=new ScoreBox[2];
	Stone[] stone=new Stone[70];
	
	public Process(MainGame m){
		mainGame=m;
	}
	public void init() {
		//init elements for the first time start.
		for(int i=0;i<12;i++) box[i]=new Box(mainGame,this,i);
		scBox[0]=new ScoreBox(mainGame,0);
		scBox[1]=new ScoreBox(mainGame,1);
		int count=0;
		for(int i=0;i<12;i++) {
			if(i!=5&&i!=11)
				for(int j=0;j<5;j++) {
					stone[count]=new Stone(mainGame,i);
					count++;
				}
			else 
				for(int j=0;j<10;j++) {
					stone[count]=new Stone(mainGame,i);
					count++;
				}
		}
	}
	public void reDraw() {  
		//reset game.
		mainGame.currentTeam=0;
		int count=0;
		for(int i=0;i<12;i++) {
			if(i!=5&&i!=11)
				for(int j=0;j<5;j++) {
					stone[count].quickMove(i);
					count++;
				}
			else
				for(int j=0;j<10;j++) {
					stone[count].quickMove(i);
					count++;
				}
			box[i].resetStone();
		}
		scBox[0].resetStone();
		scBox[1].resetStone();
		for(int i=0;i<12;i++) box[i].change(mainGame.getNumberInBox());
		box[5].change(mainGame.getNumberInScoreBox());
		box[11].change(mainGame.getNumberInScoreBox());
		scBox[0].change(0);
		scBox[1].change(0);
		scBox[0].setTurn(true);
	}
	public void resize() {
		for(int i=0;i<12;i++) box[i].resize();
		scBox[0].resize();
		scBox[1].resize();
		for(int i=0;i<70;i++) stone[i].resize();
	}
	public void removeAllArrow() {
		for(int i=0;i<12;i++) box[i].removeArrow();
	}
	public void move(int pos,int direction, boolean isEaten){
		//main function to move stones.
		if(pos==5||pos==11) mainGame.nextTurn();
		else if (box[pos].getNum()>0){
			if(!isEaten){
				int num=box[pos].getNum();
				box[pos].setColor(Color.BLUE);
				for(int i=1;i<=num;i++) {
					int temp=calNewPos(pos,i*direction);
					box[pos].change(box[pos].getNum()-1);
					box[temp].change(box[temp].getNum()+1);
					int j=0;
					while(box[pos].isStone[j]==false)
						j++;
					box[pos].isStone[j]=false;
					box[temp].isStone[j]=true;
					stone[j].move(temp);
				}
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
		//check if there is a win situation or out of stones in current team.
		if(box[5].getNum()==0&&box[11].getNum()==0) return -1;
		if(scBox[team].getNum()<5&&total(team)==0) 	return 0;
		if(scBox[team].getNum()>5&&total(team)==0) 	return 1;
		return 2;
	}
	int total(int team) {
		//get the total of stones on normal box.
		if(team==0)	
			return box[6].getNum()+box[7].getNum()+box[8].getNum()+box[9].getNum()+box[10].getNum();
		else 
			return box[0].getNum()+box[1].getNum()+box[2].getNum()+box[3].getNum()+box[4].getNum();
	}
	int calNewPos(int src,int step){
		return (src+1200+step)%12;
	}
	void kill(int team,int pos){
		int 
			temp=scBox[team].getNum(),
			boxTemp=box[pos].getNum();
		for(int i=0;i<boxTemp;i++) {
			int j=0;
			while(box[pos].isStone[j]==false)
				j++;
			box[pos].isStone[j]=false;
			scBox[team].isStone[j]=true;
			box[pos].change(box[pos].getNum()-1);
			stone[j].move(team-2);
			scBox[team].change(1+scBox[team].getNum());
		}
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
		scBox[mainGame.getCurTeam()].setTurn(true);
		scBox[1-mainGame.getCurTeam()].setTurn(false);
	}
	void victory(int team) {
		if(team>-1) JOptionPane.showConfirmDialog(mainGame,"Người chơi "+(team+1)+" thắng","Xong phim",JOptionPane.DEFAULT_OPTION);
		else 
			JOptionPane.showConfirmDialog(mainGame,"Hoà","Game over",1);
		mainGame.newGame();
	}
	void spread(int team) {
		//spread stones when there're no stone on table.
		if(team==0)
			for(int i=6;i<11;i++) boxSpread(team,i);
		else 
			for(int i=0;i<5;i++) boxSpread(team,i);
	}
	void boxSpread(int team, int pos) {
		//sub function spread to a box.
		int j=0;
		scBox[team].change(scBox[team].getNum()-1);
		while(!scBox[team].isStone[j])
			j++;
		
		stone[j].move(pos);
		scBox[team].isStone[j]=false;
		box[pos].change(1);
		box[pos].isStone[j]=true;
	}
}
