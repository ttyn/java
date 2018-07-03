package hm;

import java.util.Date;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Stringex extends JFrame {

	private JPanel contentPane;
	private JTextField tfnguon;
	private JTextField tfcheckstart;
	private JTextField tfstart;
	private JTextField tfend;
	private JTextField tfreplace;
	private JTextField tfby;
	private JTextField tfkq;
	private JLabel lblength;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Stringex frame = new Stringex();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Stringex() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 551, 466);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblChuiNgun = new JLabel("Chu\u1ED7i ngu\u1ED3n");
		lblChuiNgun.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblChuiNgun.setBounds(10, 23, 81, 27);
		contentPane.add(lblChuiNgun);
		
		tfnguon = new JTextField();
		tfnguon.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
					setlength();
			}
		});
		tfnguon.setBounds(101, 28, 402, 19);
		contentPane.add(tfnguon);
		tfnguon.setColumns(10);
		
		JLabel lblKtQu = new JLabel("K\u1EBFt qu\u1EA3");
		lblKtQu.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblKtQu.setBounds(10, 356, 81, 27);
		contentPane.add(lblKtQu);
		
		JLabel lblKimTra = new JLabel("Ki\u1EC3m tra chu\u1ED7i c\u00F3 b\u1EAFt \u0111\u1EA7u b\u1EB1ng");
		lblKimTra.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblKimTra.setBounds(10, 100, 213, 27);
		contentPane.add(lblKimTra);
		
		tfcheckstart = new JTextField();
		tfcheckstart.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					if(tfnguon.getText().startsWith(tfcheckstart.getText())) {
						recolor(true);
						tfkq.setText("Chuỗi nguồn bắt đầu bằng "+tfcheckstart.getText());
						setlength();
					}
					else {
						recolor(false);
						tfkq.setText("Chuỗi không bắt đầu bằng "+tfcheckstart.getText());
						setlength();
					}
				}
			}
		});
		tfcheckstart.setBounds(227, 105, 276, 19);
		contentPane.add(tfcheckstart);
		tfcheckstart.setColumns(10);
		
		JLabel lblVTrBt = new JLabel("V\u1ECB tr\u00ED b\u1EAFt \u0111\u1EA7u");
		lblVTrBt.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblVTrBt.setBounds(10, 137, 94, 27);
		contentPane.add(lblVTrBt);
		
		tfstart = new JTextField();
		tfstart.setBounds(101, 142, 50, 19);
		contentPane.add(tfstart);
		tfstart.setColumns(10);
		
		JLabel label = new JLabel("V\u1ECB tr\u00ED b\u1EAFt \u0111\u1EA7u");
		label.setFont(new Font("Tahoma", Font.BOLD, 13));
		label.setBounds(161, 137, 94, 27);
		contentPane.add(label);
		
		tfend = new JTextField();
		tfend.setColumns(10);
		tfend.setBounds(251, 142, 50, 19);
		contentPane.add(tfend);
		
		JButton btnLyChuiGia = new JButton("L\u1EA5y chu\u1ED7i gi\u1EEFa 2 v\u1ECB tr\u00ED");
		btnLyChuiGia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String kq=null;
				kq="";
				try {
				kq=tfnguon.getText().substring(Integer.parseInt(tfstart.getText())-1, Integer.parseInt(tfend.getText()));
				recolor(true);
				tfkq.setText(kq);
				setlength();
				}
				catch(StringIndexOutOfBoundsException ex){
					recolor(false);
					tfkq.setText("Vị trí ràng buộc không hợp lệ ");
				}
				catch(NumberFormatException ex){
					recolor(false);
					tfkq.setText("Vị trí bắt đầu và kết thúc phải là số");
				}
			}
		});
		btnLyChuiGia.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnLyChuiGia.setBounds(339, 140, 164, 21);
		contentPane.add(btnLyChuiGia);
		
		JButton btnChuynChuiThnh = new JButton("CHUY\u1EC2N CHU\u1ED6I TH\u00C0NH CH\u1EEE HOA");
		btnChuynChuiThnh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				recolor(true);
				tfkq.setText(tfnguon.getText().toUpperCase());
				tfnguon.setText(tfnguon.getText().toUpperCase());
				setlength();
			}
		});
		btnChuynChuiThnh.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnChuynChuiThnh.setBounds(10, 185, 245, 34);
		contentPane.add(btnChuynChuiThnh);
		
		JButton btnChuynChuiThnh_1 = new JButton("chuy\u1EC3n chu\u1ED7i th\u00E0nh ch\u1EEF th\u01B0\u1EDDng");
		btnChuynChuiThnh_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				recolor(true);
				tfkq.setText(tfnguon.getText().toLowerCase());
				tfnguon.setText(tfnguon.getText().toLowerCase());

				setlength();
			}
		});
		btnChuynChuiThnh_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnChuynChuiThnh_1.setBounds(281, 185, 222, 34);
		contentPane.add(btnChuynChuiThnh_1);
		
		JButton btnChuynDThnh = new JButton("Đổi");
		btnChuynDThnh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			recolor(true);
			tfkq.setText(tfnguon.getText().replace(tfreplace.getText(), tfby.getText()));
			tfnguon.setText(tfnguon.getText().replace(tfreplace.getText(), tfby.getText()));
			setlength();
			}
		});
		btnChuynDThnh.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnChuynDThnh.setBounds(281, 270, 222, 27);
		contentPane.add(btnChuynDThnh);
		
		JButton btnXaKhongTrng = new JButton("X\u00F3a kho\u1EA3ng tr\u1EAFng th\u1EEBa");
		btnXaKhongTrng.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String kq=tfnguon.getText();
				recolor(true);
				kq.trim();
				while(kq.indexOf("  ")!=-1)
					kq=kq.replaceAll("  ", " ");
				tfkq.setText(kq);
				tfnguon.setText(kq);
				setlength();
			}
		});
		btnXaKhongTrng.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnXaKhongTrng.setBounds(281, 229, 222, 31);
		contentPane.add(btnXaKhongTrng);
		
		JLabel lblThay = new JLabel("Thay");
		lblThay.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblThay.setBounds(10, 270, 94, 27);
		contentPane.add(lblThay);
		
		tfreplace = new JTextField();
		tfreplace.setColumns(10);
		tfreplace.setBounds(51, 275, 72, 19);
		contentPane.add(tfreplace);
		
		JLabel lblBng = new JLabel("B\u1EB1ng");
		lblBng.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblBng.setBounds(133, 270, 94, 27);
		contentPane.add(lblBng);
		
		tfby = new JTextField();
		tfby.setColumns(10);
		tfby.setBounds(186, 275, 72, 19);
		contentPane.add(tfby);
		
		JButton btnInNgyThng = new JButton("In ng\u00E0y th\u00E1ng hi\u1EC7n t\u1EA1i");
		btnInNgyThng.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				settime();
			}
		});
		btnInNgyThng.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnInNgyThng.setBounds(10, 229, 245, 33);
		contentPane.add(btnInNgyThng);
		
		tfkq = new JTextField();
		tfkq.setFont(new Font("Tahoma", Font.BOLD, 12));
		tfkq.setEditable(false);
		tfkq.setColumns(10);
		tfkq.setBounds(101, 361, 402, 34);
		contentPane.add(tfkq);
		
		lblength = new JLabel("Độ dài chuỗi: ");
		lblength.setForeground(Color.RED);
		lblength.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblength.setBounds(10, 319, 152, 27);
		contentPane.add(lblength);
		
	}
	public void recolor(boolean b) {
		if(b==true) {
			tfkq.setForeground(Color.BLUE);
		}
		else if (b==false) tfkq.setForeground(Color.RED);
			
	}
	public void setlength() {
		lblength.setText("Độ dài chuỗi: "+ String.valueOf(tfnguon.getText().length()));
	}
	public void settime() {
		Date today=new Date(System.currentTimeMillis());
		SimpleDateFormat timeFormat= new SimpleDateFormat("hh:mm:ss dd/MM/yyyy");
		String s=timeFormat.format(today.getTime());
		tfkq.setText(s);
	}
}
