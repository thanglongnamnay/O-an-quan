package master;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class GUI extends JFrame implements ActionListener{
	public static final int 
		xUnit=16,
		yUnit=9;
	public static int multiple=80;
	private JMenuBar mb;
	private JMenu 
		mGame=new JMenu("Trò chơi"),
		mHelp=new JMenu("Trợ giúp");  
	private JMenuItem 
		miGameMoi=new JMenuItem("Trò chơi mới"),
		miCaiDat=new JMenuItem("Cài đặt"),
		miThoat=new JMenuItem("Kết thúc"),
		miTroGiup=new JMenuItem("Luật chơi"), 
		miThongTin=new JMenuItem("Thông tin");
	MainGame mainGame;
	private Font fontMenu=new Font("SansSerif",Font.BOLD,18);
	public GUI(){
		init();
	}
	void init(){
		setTitle("Ô ăn quan 3.0");
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
		setJMenuBar(mb);
		
		createMI(mGame);
		createMI(mHelp);
		createMI(miGameMoi);
		createMI(miCaiDat);
		createMI(miThoat);
		createMI(miThongTin);
		createMI(miTroGiup);
	
		mGame.add(miGameMoi);mGame.add(miCaiDat);mGame.add(miThoat);
		mHelp.add(miTroGiup);mHelp.add(miThongTin);
		
		mb.add(mGame);mb.add(mHelp);
	}
	void createMI(Object mi) {
		((AbstractButton) mi).addActionListener(this);
		 ((JComponent) mi).setFont(fontMenu);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==miGameMoi) 	mainGame.newGame();
		if (e.getSource()==miCaiDat)	caiDat();
		if (e.getSource()==miThoat) 	System.exit(0);
		if (e.getSource()==miThongTin) 	showHang();
		if (e.getSource()==miTroGiup) 	showLuatChoi();
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
		
		JLabel lbSpeed=new JLabel("Tốc độ di chuyển");
		lbSpeed.setBounds(20,20,200,30);
		lbSpeed.setFont(fontMenu);
		setupFrame.add(lbSpeed);
		
		JSpinner spSpeed=new JSpinner(new SpinnerNumberModel(sp,1,4,1));
		spSpeed.setBounds(300,20,50,30);
		setupFrame.add(spSpeed);

		JLabel lbSize=new JLabel("Độ thu phóng");
		lbSize.setBounds(20,70,200,30);
		lbSize.setFont(fontMenu);
		setupFrame.add(lbSize);
		
		JSpinner spSize=new JSpinner(new SpinnerNumberModel(multiple/20,1,4,1));
		spSize.setBounds(300,70,50,30);
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
		JOptionPane.showConfirmDialog(this, s,"Ô ăn quan 2.0",JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE);
	}
	void showLuatChoi() {
		String s="Trò chơi kết thúc khi bạn nợ quân hoặc 2 quan đã bị ăn hết.";
		JOptionPane.showConfirmDialog(this, s,"Luật chơi",JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE);
	}
}

