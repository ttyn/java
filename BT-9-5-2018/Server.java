package TCP;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.awt.event.ActionEvent;

public class Server extends JFrame {

	private JPanel contentPane;
	ServerSocket server;
	Socket socketServer;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Server frame = new Server();
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
	public Server() {
		setTitle("SERVER");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 449, 161);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("START");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					server= new ServerSocket(8069);
					JOptionPane.showMessageDialog(null, "Server đã chạy");
					socketServer= server.accept();
					JOptionPane.showMessageDialog(null, "Đã có client kết nối");
				connect();
				}
				catch (Exception e) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		});
		btnNewButton.setBounds(10, 47, 416, 31);
		contentPane.add(btnNewButton);
	}
	public void connect() {
		try {
			while (true) {
			DataInputStream inputServer= new DataInputStream(socketServer.getInputStream());
			String so1= inputServer.readLine();
			String so2= inputServer.readLine();
			int a= Integer.parseInt(so1);
			int b= Integer.parseInt(so2);
			int tong= a+b;
			DataOutputStream outputServer= new DataOutputStream(socketServer.getOutputStream());
		
			outputServer.writeBytes(String.valueOf(tong));
			inputServer.close();
			outputServer.close();
			}	
//			connect();
//			server.close();
	}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
}
