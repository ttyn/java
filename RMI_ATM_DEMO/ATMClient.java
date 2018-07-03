

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Color;

public class ATMClient {
	ATMInterface obj;
	private JFrame frame;
	private JTextField tfsotien;
	JLabel lblSD;
	JLabel lbstt;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ATMClient window = new ATMClient();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ATMClient() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		try {
			obj= (ATMInterface)Naming.lookup("rmi://192.168.22.163/ATMRemote");
			
		} catch (MalformedURLException | RemoteException | NotBoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JButton btnKimTraTk = new JButton("KT tai khoan");
		btnKimTraTk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					lblSD.setText("So du: "+String.valueOf(obj.kttk()));
					lbstt.setText("");
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnKimTraTk.setBounds(10, 24, 85, 21);
		frame.getContentPane().add(btnKimTraTk);
		
		JButton btnNpTin = new JButton("Nap tien");
		btnNpTin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					obj.naptien(Integer.parseInt(tfsotien.getText()));
					lblSD.setText("so du: "+String.valueOf(obj.kttk()));
					lbstt.setText("Da nap "+ tfsotien.getText());
				} catch (NumberFormatException | RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNpTin.setBounds(10, 118, 85, 21);
		frame.getContentPane().add(btnNpTin);
		
		JButton btnRtTin = new JButton("Rut tien");
		btnRtTin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					obj.ruttien(Integer.parseInt(tfsotien.getText()));
					lblSD.setText("So du: "+String.valueOf(obj.kttk()));
					lbstt.setText("Da rut "+ tfsotien.getText());
				} catch (NumberFormatException | RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnRtTin.setBounds(156, 118, 85, 21);
		frame.getContentPane().add(btnRtTin);
		
		tfsotien = new JTextField();
		tfsotien.setBounds(10, 77, 231, 19);
		frame.getContentPane().add(tfsotien);
		tfsotien.setColumns(10);
		
		lblSD = new JLabel("Số dư:");
		lblSD.setBounds(125, 28, 251, 13);
		frame.getContentPane().add(lblSD);
		
		lbstt = new JLabel("");
		lbstt.setForeground(Color.RED);
		lbstt.setBounds(10, 54, 141, 13);
		frame.getContentPane().add(lbstt);
	}
}
