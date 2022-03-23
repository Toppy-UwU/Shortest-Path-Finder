package algorPk;

import java.awt.BasicStroke;
import java.awt.Button;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class testGUI extends JFrame{
	
	public testGUI() {
		setSize(500,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		testGUI gui = new testGUI();
		graph2 g = new graph2();
		gui.add(g);
		gui.setVisible(true);
	}

}

class graph2 extends JPanel{
	private String name = "Test Node";
	
	public graph2() {
		// TODO Auto-generated constructor stub
	}
	
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(2f));
        g2.setColor(Color.RED);
		g2.draw(new Line2D.Double(20,50,100,150));
	}
}