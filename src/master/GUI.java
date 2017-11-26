package master;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
public class GUI extends JFrame implements ActionListener{
	public static final int 
		xUnit=16,
		yUnit=9;
	public static int multiple=80;
	private JMenuBar mb;
	private JMenu mGame,mHelp;  
	private JMenuItem 
		miGameMoi,
		miCaiDat,
		miThoat,
		miTroGiup, 
		miThongTin;
	MainGame mainGame;
	private Font fontMenu=new Font("SansSerif",Font.BOLD,18);
	public GUI(){
		init();
	}
	void init(){
		setTitle("Ô ăn quan 2.0");
		setResizable(false);
		addMenu();
		ImageIcon icon=new ImageIcon("\\src\\images\\stone.jpg");
		setIconImage(icon.getImage());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainGame=new MainGame();
        add(mainGame);
		resize();
	}
	void resize() {
		setSize(xUnit*multiple,yUnit*multiple+50);
		mainGame.resize();
	}
	void addMenu(){
		mb=new JMenuBar();  
		mb.setBounds(0,0,500,30);  
		
		mGame=new JMenu("Trò chơi");mGame.addActionListener(this);mGame.setFont(fontMenu);
		mHelp=new JMenu("Trợ giúp");mHelp.addActionListener(this);mHelp.setFont(fontMenu);
		
		miGameMoi=new JMenuItem("Trò chơi mới");miGameMoi.addActionListener(this);miGameMoi.setFont(fontMenu);
		miCaiDat=new JMenuItem("Cài đặt");miCaiDat.addActionListener(this);miCaiDat.setFont(fontMenu);
		miThoat=new JMenuItem("Kết thúc");miThoat.addActionListener(this);miThoat.setFont(fontMenu);
		miThongTin=new JMenuItem("Thông tin");miThongTin.addActionListener(this);miThongTin.setFont(fontMenu);
		miTroGiup=new JMenuItem("Luật chơi");miTroGiup.addActionListener(this);miTroGiup.setFont(fontMenu);
	
		mGame.add(miGameMoi);mGame.add(miCaiDat);mGame.add(miThoat);
		mHelp.add(miTroGiup);mHelp.add(miThongTin);
		
		mb.add(mGame);mb.add(mHelp);
		setJMenuBar(mb);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource()==miGameMoi) {
			mainGame.newGame();
		}
		if(e.getSource()==miCaiDat){
			caiDat();
		}
		if (e.getSource()==miThoat) {
			System.exit(0);
		}
		if (e.getSource()==miThongTin) {
			showHang();
		}
		if (e.getSource()==miTroGiup) {
			showLuatChoi();
		}
	}
	void caiDat(){
		JFrame setupFrame=new JFrame("Cài đặt");
		final int stWidth=400,stHeight=220;
		int sp=mainGame.speed;
		int xPos=getWidth()/2-stWidth/2,yPos=getHeight()/2-stHeight/2;
		setupFrame.setBounds(xPos,yPos,stWidth,stHeight);
		setupFrame.setResizable(false);
		setupFrame.setLayout(null);
		setupFrame.setVisible(true);
		
		JLabel lbSpeed=new JLabel("Tốc độ");
		lbSpeed.setBounds(20,20,80,30);
		lbSpeed.setFont(fontMenu);
		setupFrame.add(lbSpeed);
		
		JSpinner spSpeed=new JSpinner(new SpinnerNumberModel(sp,1,4,1));
		spSpeed.setBounds(100,20,50,30);
		setupFrame.add(spSpeed);

		JLabel lbSize=new JLabel("Zoooooom");
		lbSize.setBounds(20,70,80,30);
		lbSize.setFont(fontMenu);
		setupFrame.add(lbSize);
		
		JSpinner spSize=new JSpinner(new SpinnerNumberModel(multiple/20,1,4,1));
		spSize.setBounds(100,70,50,30);
		setupFrame.add(spSize);
		
		JButton dongY=new JButton("Chấp nhận");
		dongY.setBounds(55,130,130,35);
		dongY.setFont(fontMenu);
		dongY.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				mainGame.speed=(int) spSpeed.getValue();
				multiple=(int) spSize.getValue()*20;
				resize();
				setupFrame.setVisible(false);
			}
		});
		JButton huyBo=new JButton("Huỷ bỏ");
		huyBo.setBounds(200,130,100,35);
		huyBo.setFont(fontMenu);
		huyBo.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				spSpeed.setValue(sp);
				spSize.setValue(multiple/20);
				setupFrame.setVisible(false);
			}
		});
		setupFrame.add(dongY);
		setupFrame.add(huyBo);
		
	}
	void showHang() {
		String s="Trò chơi mô phỏng ô ăn quan.\nTác giả: ntl\nChúc các bạn vui vẻ.";
		JOptionPane.showConfirmDialog(this, s,"Thông tin",JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE);
	}
	void showLuatChoi() {
		String s="Trò chơi kết thúc khi bạn nợ quân hoặc 2 quan đã bị ăn hết.";
		JOptionPane.showConfirmDialog(this, s,"Luật chơi",JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE);
	}
}
/*junk:
		JLabel lbSoSoiDan=new JLabel("Số sỏi trong 1 ô dân");
		lbSoSoiDan.setBounds(50,30,200,30);
		lbSoSoiDan.setFont(fontMenu);
		JLabel lbSoSoiQuan=new JLabel("Số sỏi trong 1 ô quan");
		lbSoSoiQuan.setBounds(50,70,200,30);
		lbSoSoiQuan.setFont(fontMenu);

		SpinnerModel dan=new SpinnerNumberModel(5,1,10,1);
		SpinnerModel quan=new SpinnerNumberModel(10,1,15,1);
		JSpinner spDan=new JSpinner(dan);
		spDan.setBounds(270,30,50,30);
		spDan.setFont(fontMenu);
		JSpinner spQuan=new JSpinner(quan);
		spQuan.setBounds(270,70,50,30);
		spQuan.setFont(fontMenu);

		JButton dongY=new JButton("Chấp nhận");
		dongY.setBounds(180,130,130,35);
		dongY.setFont(fontMenu);
		dongY.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				mainGame.setNumberInBox((int) spDan.getValue());
				mainGame.setNumberInScoreBox((int) spQuan.getValue());
				mainGame.newGame();
			}
		});
		JButton huyBo=new JButton("Huỷ bỏ");
		huyBo.setBounds(55,130,100,35);
		huyBo.setFont(fontMenu);
		huyBo.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				setupFrame.setVisible(false);
			}
		});

		setupFrame.add(lbSoSoiDan);
		setupFrame.add(lbSoSoiQuan);
		setupFrame.add(spQuan);
		setupFrame.add(spDan);
		setupFrame.add(dongY);
		setupFrame.add(huyBo);
*/

