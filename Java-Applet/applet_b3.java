import java.awt.Graphics;

import javax.swing.JApplet;
import javax.swing.JOptionPane;

public class applet_b3 extends JApplet {
	int num;
	String[] kq= {"","","",""};
		/**
		 * Create the applet.
		 */
		public applet_b3() {

		}
		public void init(){
			String n1=JOptionPane.showInputDialog("Nhập số thứ nhất", null);
			String n2=JOptionPane.showInputDialog("Nhập số thứ 2", null);
			try {
			double num1=Integer.parseInt(n1);
			double num2=Integer.parseInt(n2);
			kq[0]=n1+"+"+n2+"="+String.valueOf(num1+num2);
			kq[1]=n1+"-"+n2+"="+String.valueOf(num1-num2);
			kq[2]=n1+"*"+n2+"="+String.valueOf(num1*num2);
			kq[3]=n1+"/"+n2+"="+String.valueOf(num1/num2);
			}
			catch(NumberFormatException e) {
				JOptionPane.showMessageDialog(null,"Vui lòng nhập lại 2 số thực");
				init();
			}
		}
		public void paint(Graphics g) {
			int y = 20;
			for(int i=0;i<4;i++) {
			    y+=15;
			g.drawString(kq[i], 20, y);
		}
		}

	}
