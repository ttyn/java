import java.awt.Graphics;

import javax.swing.JApplet;
import javax.swing.JOptionPane;

public class applet_b2 extends JApplet {
int num;
String kq="";
	/**
	 * Create the applet.
	 */
	public applet_b2() {

	}
	public void init(){
		try {
		String n=JOptionPane.showInputDialog("Nhập N", null);
		num=Integer.parseInt(n);
		}
		catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(null,"Vui lòng nhập lại số thực N");
			init();
		}
	}
	public void paint(Graphics g) {
		int y = 20;
		for(int i=1;i<=num;i++) {
			kq=kq+"*";
		    y+=10;
		g.drawString(kq, 20, y);
	}
	}

}
