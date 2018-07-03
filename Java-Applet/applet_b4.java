import java.applet.*;
import javax.swing.*;
import java.awt.*;
import java.awt.Event;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
public class applet_b4 extends Applet implements KeyListener{
	private JTextField tfnum;
	JPanel p= new JPanel();
	int num;
	int solanthu=3;
	String kq="";
	/**
	 * Create the applet.
	 */
	public applet_b4() {

}
	public void init() {
      p.setLayout(new GridLayout(3,3));
		
		JLabel lblonS = new JLabel("\u0110O\u00C1N S\u1ED0");
//		lblonS.setFont(new Font("Tahoma", Font.BOLD, 17));
//		lblonS.setForeground(Color.BLACK);
//		lblonS.setBounds(155, 20, 190, 48);
		p.add(lblonS);
		
		JLabel lblNhpS = new JLabel("Nh\u1EADp 1 s\u1ED1 m\u00E0 b\u1EA1n \u0111o\u00E1n (t\u1EEB 1-9)");
//		lblNhpS.setFont(new Font("Tahoma", Font.PLAIN, 14));
//		lblNhpS.setBounds(10, 86, 209, 27);
		p.add(lblNhpS);
		
		setRandomNum();
		
		tfnum = new JTextField();
		tfnum.addKeyListener(this);
//		tfnum.setBounds(240, 92, 158, 19);
		p.add(tfnum);
		tfnum.setColumns(10);
		this.setSize(500, 300);
		add(p,BorderLayout.PAGE_START);
	}
public void keyTyped(KeyEvent e) {
	      }

public void keyPressed(KeyEvent e) {
	        if(e.getKeyCode()==KeyEvent.VK_ENTER) {
	        	try {
	        	if(Integer.parseInt(tfnum.getText())==num) {
	        	kq="Congraturation!";
	        	setRandomNum();
	        	solanthu=3;
	        }
	        else {
	        	kq="Sai rồi, bạn còn "+String.valueOf(solanthu)+" lần thử!";
	        	solanthu--;
	        	if (solanthu==0) {
	        		kq="Đã quá 3 lần thử, Tạo lại số ngẫu nhiên";
	        		setRandomNum();
	        		solanthu=3;
	        	}
	        }
	        	
	    repaint();
	        }
	   catch(NumberFormatException e1) {
		   JOptionPane.showMessageDialog(null,"vui lòng nhập lại số");
	   }
}
}
public void keyReleased(KeyEvent e) {
	      }   
public void paint(Graphics g) {
	super.paint(g);
	g.drawString(kq, 170, 80);
}
public void setRandomNum(){
	Random rd= new Random();//khởi tạo random
	num= rd.nextInt(10);// đặt num = random 0-10;
}
}
