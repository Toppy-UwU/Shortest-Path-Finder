package algorPk;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;




public class algorGUI {

	public static void main(String[] args) {
		mainFrame frame = new mainFrame();
		frame.setVisible(true);

	}

}


class mainFrame extends JFrame{
	
	
	public mainFrame() {
		cmdPanel cmdP = new cmdPanel();
		mainPanel mainP = new mainPanel(cmdP);
		answerPanel anP = new answerPanel(mainP);		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1690,1000);
		setLocationRelativeTo(null);
		setTitle("algorithm");
		setLayout(new BorderLayout());
		
		
		
		add(mainP, BorderLayout.CENTER);
		add(cmdP, BorderLayout.EAST);
		add(anP, BorderLayout.WEST);
	}
	
	
}

class cmdPanel extends JPanel{

	
	private boolean addStat = false;
	private boolean addFEdgeStat = false;
	private boolean startNodeStat = false;
	private boolean endNodeStat = false;
	
	JButton startBtn = new JButton("Select Start Node");
	JButton endBtn = new JButton("Select End Node");
	JButton addBtn = new JButton("Add Node");
	JButton edgeBtn = new JButton("Add Edge");
	
	
	public cmdPanel() {
		 Graph g = new Graph();
		 
		 
		setBackground(Color.white);
		setLayout(new GridLayout(10,1));
		
		addBtn.setBackground(Color.white);
		edgeBtn.setBackground(Color.white);
		startBtn.setBackground(Color.white);
		endBtn.setBackground(Color.white);
		
		add(addBtn);
		add(edgeBtn);
		add(startBtn);
		add(endBtn);
		
		addBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				addStat = !addStat;
//				System.out.println(addStat);
				if(addStat) {
					addBtn.setBackground(Color.gray);
					
					addFEdgeStat = false;
					startNodeStat = false;
					endNodeStat = false;
					
					edgeBtn.setBackground(Color.white);
					startBtn.setBackground(Color.white);
					endBtn.setBackground(Color.white);
				}else {
					addBtn.setBackground(Color.white);
				}
			}
		});
		
		edgeBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				addFEdgeStat = !addFEdgeStat;
//				System.out.println(addFEdgeStat);
				if(addFEdgeStat) {
					edgeBtn.setBackground(Color.gray);
					
					addStat = false;
					startNodeStat = false;
					endNodeStat = false;
					
					addBtn.setBackground(Color.white);
					startBtn.setBackground(Color.white);
					endBtn.setBackground(Color.white);
				}else {
					edgeBtn.setBackground(Color.white);
				}
				
			}
		});
		
		startBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				startNodeStat = !startNodeStat;
				if(startNodeStat) {
					startBtn.setBackground(Color.green);
					
					addStat = false;
					addFEdgeStat = false;
					endNodeStat = false;
					
					edgeBtn.setBackground(Color.white);
					addBtn.setBackground(Color.white);
					endBtn.setBackground(Color.white);
				}else {
					startBtn.setBackground(Color.white);
				}
			}
		});
		
		endBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				endNodeStat = !endNodeStat;
				if(endNodeStat) {
					endBtn.setBackground(Color.green);
					
					addStat = false;
					addFEdgeStat = false;
					startNodeStat = false;
					
					edgeBtn.setBackground(Color.white);
					addBtn.setBackground(Color.white);
					startBtn.setBackground(Color.white);
				}else {
					endBtn.setBackground(Color.white);
				}
			}
		});
		
		
		
	}
	 
	 
//	cmdPanel Method Section
	 
	boolean getStat() {
		return this.addStat;
	}
	
	boolean getEdgeStat() {
		return this.addFEdgeStat;
	}
	
	void resetEdgeStat() {
		this.addFEdgeStat = false;
		this.edgeBtn.setBackground(Color.white);
		
	}
	
	boolean getStartNodeStat() {
		return this.startNodeStat;
	}
	
	boolean getEndNodeStat() {
		return this.endNodeStat;
	}
	
	void resetStartEndStat() {
		startNodeStat = false;
		endNodeStat = false;
	}
	
	public void setZeroCmdPanel() {
		this.addStat = false;
		this.addFEdgeStat = false;
		this.startNodeStat = false;
		this.endNodeStat = false;
		this.startBtn.setText("Select Start Node");
		this.endBtn.setText("Select End Node");
		addBtn.setBackground(Color.white);
		edgeBtn.setBackground(Color.white);
		startBtn.setBackground(Color.white);
		endBtn.setBackground(Color.white);
	}
	 
}

class mainPanel extends JPanel{

	private cmdPanel cmdP;
	public nodePanel NP = new nodePanel();
	private ArrayList<JButton> Node = new ArrayList<JButton>();
	private ArrayList<Integer> xPosit = new ArrayList<Integer>();
	private ArrayList<Integer> yPosit = new ArrayList<Integer>();
	private ArrayList<JTextField> Dis = new ArrayList<JTextField>();
	private ArrayList<Integer> distanc = new ArrayList<Integer>();
	public ArrayList<Node> rNode = new ArrayList<Node>();
	public ArrayList<Edge> rEdge = new ArrayList<Edge>();
	private int name = 65;
	private int chk = 0;
	private int Eidx = 0;
	public int start,end;
	
	private int selectedNode[] = new int[] {-99,-99};
	private boolean selectedStat[] = new boolean[]{false, false};
	public int startEndSelect[] = new int[2];
	private boolean frChk[] = new boolean[] {false, false};
	
	private JButton resetBtn = new JButton("Reset");
	
	public mainPanel(cmdPanel cmdP) {
		
		this.cmdP = cmdP;
		
		setLayout(new BorderLayout());
		setBackground(new Color(128,152,60));
		
		add(NP, BorderLayout.CENTER);
		
		resetBtn.setBackground(Color.white);
		resetBtn.setForeground(Color.red);
		
		add(resetBtn, BorderLayout.SOUTH);
		
		addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {}
			
			@Override
			public void mousePressed(MouseEvent e) {}
			
			@Override
			public void mouseExited(MouseEvent e) {}
			
			@Override
			public void mouseEntered(MouseEvent e) {}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(cmdP.getStat()) {
					System.out.println("Work");
					addFNode(e.getX(), e.getY());
					
				}else {
					System.out.println("not");
				}
				
			}
		});
		
		resetBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cmdP.setZeroCmdPanel();
				setZeroMainPanel();
				NP.setZeroNodePanel();
			}
		});
		
	}
	
	
	
//	MainPanel Method Section
	
	void addFNode(int x, int y) {
		this.xPosit.add(x);
		this.yPosit.add(y);
		JButton btn = new JButton(""+(char)(this.name));
		btn.setBackground(Color.white);
		btn.setBounds(x-25, y-25, 50, 50);
		btn.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		
		this.rNode.add(new Node(""+(char)(name)));
		
		btn.addActionListener(nodeML);
		
		this.Node.add(btn);
		this.NP.add(this.Node.get(name-65));
		this.name++;
	}
	
	
	void addFEdge(ActionEvent e) {
		if(cmdP.getEdgeStat()) {
			int idx = (int)(e.getActionCommand().charAt(0))-65;
			System.out.println(xPosit.get(idx)+"/"+yPosit.get(idx));
			selectedNode[chk] = idx;
			if(selectedNode[0] != selectedNode[1]) {
				selectedStat[chk] = true;
				chk++;
				System.out.println("Chk:"+chk);
				System.out.println(Arrays.toString(selectedStat));
			}
			if(selectedStat[0] && selectedStat[1]) {
				this.NP.addFEdge(xPosit.get(selectedNode[0]), yPosit.get(selectedNode[0]), xPosit.get(selectedNode[1]), yPosit.get(selectedNode[1]));
				chk=0;
				this.NP.repaint();
				selectedStat[0] = false;
				selectedStat[1] = false;
				System.out.println("Node:"+selectedNode[0]+"|"+selectedNode[1]);
				
				this.rEdge.add(new Edge(this.rNode.get(selectedNode[0]), this.rNode.get(selectedNode[1])));
				
				JTextField lbl = new JTextField("1");
				lbl.setName(""+Eidx);
				this.distanc.add(1);
				Eidx++;
				lbl.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {		
						updateDistance();
					}
				});
				this.Dis.add(lbl);
				
				
				int lblX = (xPosit.get(selectedNode[0])+xPosit.get(selectedNode[1]))/2;
				int lblY = (yPosit.get(selectedNode[0])+yPosit.get(selectedNode[1]))/2;
				lbl.setBounds(lblX,lblY,35,20);
				this.NP.add(lbl);
				selectedNode[0] = -99;
				selectedNode[1] = -99;
				
			}
			
		}
	}
	
	
	public void updateDistance() {
		for(int i=0;i<Dis.size();i++) {
			distanc.set(i, Integer.parseInt(Dis.get(i).getText()));
			rEdge.get(i).setWeight(distanc.get(i));
//			System.out.print(rEdge.get(i).getWeight()+"|");
//			System.out.println(rEdge.get(i).getNodeOne()+":"+rEdge.get(i).getNodeTwo());
		}
//		System.out.println(distanc);
	}
	
	private void selectStartEnd(ActionEvent e) {
		if(cmdP.getStartNodeStat()) {
			int idx = (int)(e.getActionCommand().charAt(0))-65;
			
			if(frChk[0] && frChk[1]) {
				this.Node.get(startEndSelect[0]).setBackground(Color.white);
			}
			frChk[0] = true;
			startEndSelect[0] = idx;
			this.Node.get(idx).setBackground(new Color(80,165,255));
			cmdP.startBtn.setText("Start Node: "+((char)(idx+65)));
			cmdP.startBtn.setBackground(Color.white);
			cmdP.resetStartEndStat();
		}
		if(cmdP.getEndNodeStat()) {
			int idx = (int)(e.getActionCommand().charAt(0))-65;
			
			if(frChk[0] && frChk[1]) {
				this.Node.get(startEndSelect[1]).setBackground(Color.white);
			}
			frChk[1] = true;
			startEndSelect[1] = idx;
			this.Node.get(idx).setBackground(new Color(80,165,255));
			cmdP.endBtn.setText("End Node: "+((char)(idx+65)));
			cmdP.endBtn.setBackground(Color.white);
			cmdP.resetStartEndStat();
		}
	}
	
	ActionListener nodeML = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			addFEdge(e);
			selectStartEnd(e);
			NP.setDrawActive(cmdP.getEdgeStat());
		}
	};
	
	public void setChk() {
		this.chk = 0;
		this.selectedStat[0] = false;
		this.selectedStat[1] = false;
		this.cmdP.resetEdgeStat();
	}
	
	public void setZeroMainPanel() {
		this.Node.clear();
		this.xPosit.clear();
		this.yPosit.clear();
		this.Dis.clear();
		this.distanc.clear();
		this.rNode.clear();
		this.rEdge.clear();
		this.name = 65;
		this.chk = 0;
		this.Eidx = 0;
		
		this.selectedStat[0] = false;
		this.selectedStat[1] = false;
		this.frChk[0] = false;
		this.frChk[1] = false;
		this.NP.removeAll();
		this.NP.repaint();
		this.NP.revalidate();
		
	}
	
	public void showPathActive() {
		this.NP.setPathActive();
		this.NP.repaint();
	}
	
	public boolean getPathActive() {
		return this.NP.getPathActive();
	}
}



class nodePanel extends JPanel {
	private ArrayList<aEdge> EP = new ArrayList<aEdge>(); //Edge Position
	private ArrayList<Line2D.Double> line = new ArrayList<Line2D.Double>();
	private ArrayList<Boolean> selectedLine = new ArrayList<Boolean>();
	private int n = 0;
	private boolean fr = false;
	private boolean edgeStat;
	private boolean pathActive = false;
	
	public nodePanel() {
		setBackground(new Color(175,175,175));
		setLayout(null);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(fr && edgeStat) {
			Line2D.Double line = new Line2D.Double(EP.get(n).getX(0), EP.get(n).getY(0), EP.get(n).getX(1), EP.get(n).getY(1));
			selectedLine.add(false);
			this.line.add(line);
			n++;
		}
		for(int i=0;i<EP.size();i++) {
			Graphics2D g2 = (Graphics2D) g;
			g2.setStroke(new BasicStroke(2f));
	        if(this.selectedLine.get(i) && this.pathActive) {
	        	g2.setColor(Color.red);
	        }else {
	        	g2.setColor(Color.black);
	        }
			g2.draw(this.line.get(i));
			System.out.println(this.selectedLine);
			
		}
		
	}
	
	
	void addFEdge(int x1, int y1, int x2, int y2) {
		aEdge ep = new aEdge(x1, y1, x2, y2);
		EP.add(ep);
		this.fr = true;
	}
	
	void setDrawActive(boolean stat) {
		this.edgeStat = stat;
	}
	
	public ArrayList<Boolean> getSelectedLine() {
		return this.selectedLine;
	}
	
	public void setPathActive() {
		this.pathActive = !this.pathActive;
	}
	
	public void setZeroNodePanel() {
		this.EP.clear();
		this.line.clear();
		this.selectedLine.clear();
		this.n = 0;
		this.fr = false;
	}
	
	public boolean getPathActive() {
		return this.pathActive;
	}
	
}


class aEdge {
	int x[] = new int[2];
	int y[] = new int[2];
	int dis;
	
	public aEdge(int x1, int y1, int x2, int y2) {
		this.x[0] = x1;
		this.x[1] = x2;
		this.y[0] = y1;
		this.y[1] = y2;
		
	}
	
	int getX(int i) {
		return x[i];
	}
	
	int getY(int i) {
		return y[i];
	}
	
}

class answerPanel extends JPanel{
	private mainPanel mainP;
	private JTextArea showTxt = new JTextArea(10,20);
	
	public answerPanel(mainPanel mainP) {
		this.mainP = mainP;
		setBackground(Color.red);
		setLayout(new BorderLayout());
		
		
		JPanel sideMenu = new JPanel(new GridLayout(3, 1));
		
		JButton showBtn = new JButton("Show Path");
		JButton stcBtn = new JButton("Show Structure");
		JButton detBtn = new JButton("Show Path Color");
		
		sideMenu.add(showBtn, BorderLayout.NORTH);
		sideMenu.add(stcBtn, BorderLayout.NORTH);
		sideMenu.add(detBtn, BorderLayout.NORTH);
		add(sideMenu, BorderLayout.NORTH);
		add(showTxt, BorderLayout.CENTER);
		
		showBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				setEdgeStat();
				if(!getPathActive()) {
					showPathActive();
				}
				update();
				
				Graph g = new Graph();
				
				for(int i=0;i<mainP.rNode.size();i++) {
					g.addNode(mainP.rNode.get(i));
				}
				for(int i=0;i<mainP.rEdge.size();i++) {
					g.addEdge(mainP.rEdge.get(i));
				}
				
				DijkstraAlgorithm algor = new DijkstraAlgorithm(g, mainP.rNode.get(mainP.startEndSelect[0]));
				algor.run();
				setPathText("Path (" + (char)(mainP.startEndSelect[0]+65) + ", " + (char)(mainP.startEndSelect[1]+65) + ")\n" +
						algor.getFullPath(mainP.rNode.get(mainP.startEndSelect[1]), mainP.rEdge));
				algor.setLineColor(mainP.rNode.get(mainP.startEndSelect[1]), mainP.rEdge, mainP.NP.getSelectedLine());

				mainP.NP.repaint();
			}
		});
		
		stcBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setEdgeStat();
				
				
				update();
				
				Graph g = new Graph();
				
				for(int i=0;i<mainP.rNode.size();i++) {
					g.addNode(mainP.rNode.get(i));
				}
				for(int i=0;i<mainP.rEdge.size();i++) {
					g.addEdge(mainP.rEdge.get(i));
				}
				
				setPathText(g.toString());
				DijkstraAlgorithm algor = new DijkstraAlgorithm(g, mainP.rNode.get(0));
				algor.run();
				
				
			}
		});
		
		detBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				showPathActive();
			}
		});
		
	}
	
	private void update() {
		mainP.updateDistance();
	}
	
	private void setPathText(String str) {
		showTxt.setText(str);
	}
	
	private void setEdgeStat() {
		this.mainP.setChk();
	}
	
	private void showPathActive() {
		this.mainP.showPathActive();
	}
	
	private boolean getPathActive() {
		return mainP.getPathActive();
	}
	
}





