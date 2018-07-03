

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.awt.event.ActionEvent;

public class ATMServer {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ATMServer window = new ATMServer();
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
	public ATMServer() {
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
		
		JButton btnChyServer = new JButton("Ch\u1EA1y server");
		btnChyServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			try {
				ATM ATMObj = new ATM();
				LocateRegistry.createRegistry(1009);
				try {
					Naming.rebind("ATMRemote",ATMObj );
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				JOptionPane.showMessageDialog(null," ATMObj");
				}
			 catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		});
		btnChyServer.setBounds(10, 10, 416, 63);
		frame.getContentPane().add(btnChyServer);
	}
}
