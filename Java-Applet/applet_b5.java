import java.awt.Graphics;

import javax.swing.JApplet;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class applet_b5 extends JApplet {
	int[][] a= new int[9][9];
	int[][] b= new int[9][9];
	int[][] c= new int[9][9];// ma trận chứa tổng và tích
	int n;
	/**
	 * Create the applet.
	 */
	public applet_b5() {
		
	}
public void init() {
	this.setSize(400, 400);
		
	n=Integer.parseInt(JOptionPane.showInputDialog("Nhập số chiều ma trận", null));
	quaylaia:
	for(int i=0;i<n;i++)
		try {
		for(int j=0;j<n;j++) 
				a[i][j]=Integer.parseInt(JOptionPane.showInputDialog("Nhập phần tử "+i+""+j+" của ma trận A", null));
		}
				catch(NumberFormatException e){
				JOptionPane.showMessageDialog(null,"Ma trận chỉ chứa số nguyên, vui lòng nhập lại");
				continue quaylaia;
			}
				
		
	quaylaib:
	for(int i=0;i<n;i++)
		try {
		for(int j=0;j<n;j++)  
				b[i][j]=Integer.parseInt(JOptionPane.showInputDialog("Nhập phần tử "+i+j+" của ma trận B", null));
			}
			catch(NumberFormatException e) {
				JOptionPane.showMessageDialog(null,"Ma trận chỉ chứa số nguyên, vui lòng nhập lại");
				continue quaylaib;
			}
		
}
public void paint(Graphics g) {
	super.paint(g);
	g.drawString("Ma trận A:", 30, 30);
	int x=30; 
	int y=30;
	for(int i=0;i<n;i++) {
		x=50;
		y=y+20;
		for(int j=0;j<n;j++) {
			g.drawString(String.valueOf(a[i][j]), x, y);
			x=x+20;
	
		}
	}
	y=30;
	int x1=x+70;// vị trí x1 vùng bên phải vùng in ma trận
	g.drawString("Ma trận chuyển vị A:", x+50, 30);
	for(int i=0;i<n;i++) {
		x=x1;
		y=y+20;
		for(int j=0;j<n;j++) {
			g.drawString(String.valueOf(a[j][i]), x, y);
			x=x+20;
	
		}
	}
	
	
	y+=20;
	int y1=y;
	g.drawString("Ma trận B:", 30, y);
	 x=30; 
	for(int i=0;i<n;i++) {
		x=50;
		y=y+20;
		for(int j=0;j<n;j++) {
			g.drawString(String.valueOf(a[i][j]), x, y);
			x=x+20;
	
		}
	}
	y=y1;// vị trí trục dọc ngang với ma trận B;
	g.drawString("Ma trận chuyển vị B:", x+50, y1);
	for(int i=0;i<n;i++) {
		x=x1;
		y=y+20;
		for(int j=0;j<n;j++) {
			g.drawString(String.valueOf(a[j][i]), x, y);
			x=x+20;
	
		}
	}
	// tính tổng 2 ma trận a,b, lưu vào mảng c
	 for (int i = 0; i < n; i++) {
         for (int j = 0; j < n; j++) {
             c[i][j] = 0;
             for (int k = 0; k < n; k++) {
                 c[i][j] = c[i][j] + a[i][k] * b[k][j];
             }
         }
     }
	 // in mảng c:
	 x=30;// đưa tọa độ x về lại đầu
	 y=y+20;// đưa tọa độ y xuống 1 đoạn
	 y1=y;// lấy tọa độ vị trí bắt đầu in mảng C
	 g.drawString("A+B:", x, y);
		for(int i=0;i<n;i++) {
			x=50;
			y=y+20;
			for(int j=0;j<n;j++) {
				g.drawString(String.valueOf(c[j][i]), x, y);
				x=x+20;
		
			}
		}
	//hàm tính ma trận tích A,B:
		for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                c[i][j] = 0;
                for (int k = 0; k < n; k++) {
                    c[i][j] = c[i][j] + a[i][k] * b[k][j];
                }
            }
        }
	g.drawString("A*B:", x+50, y1);
	x=x1;// chuyển tọa độ về vị trí bên trái vị trí in phần tử cuối cùng 50
	
	y=y1;// đưa tọa độ y về y1, là vị trí ngang với chỗ bắt đầu vẽ Tổng
	for(int i=0;i<n;i++) {
		x=x1;
		y=y+20;
		for(int j=0;j<n;j++) {
			g.drawString(String.valueOf(c[j][i]), x, y);
			x=x+20;
	
		}
	}
}
}