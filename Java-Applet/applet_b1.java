import java.awt.Graphics;

import javax.swing.JApplet;
import javax.swing.JOptionPane;

public class applet_b1 extends JApplet {
int num;
String kq="";
	/**
	 * Create the applet.
	 */
	public applet_b1() {

	}
public void init(){
	try {
	String n=JOptionPane.showInputDialog("Nhập N", null);
	num=Integer.parseInt(n);
	for(int i=1;i<=num;i++) {
		if(i<num)
			kq=kq+String.valueOf(i)+", ";
		else 
			kq=kq+String.valueOf(i);
	}
	}
	catch(NumberFormatException e) {
		JOptionPane.showMessageDialog(null,"Vui lòng nhập lại 2 số thực");
		init();
	}
}
public void paint(Graphics g) {
	
	g.drawString(kq, 20, 30);
}
}
