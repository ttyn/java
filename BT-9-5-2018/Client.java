package TCP;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.awt.event.ActionEvent;

public class Client extends JFrame {

	private JPanel contentPane;
	private JTextField tfso1;
	private JTextField tfso2;
	private JTextField tfkq;
	Socket SocketClient;
	DataOutputStream outputClient;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client frame = new Client();
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
	public Client() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 380, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblFirstNumber = new JLabel("First Number");
		lblFirstNumber.setBounds(10, 29, 86, 13);
		contentPane.add(lblFirstNumber);
		
		tfso1 = new JTextField();
		tfso1.setBounds(103, 26, 96, 19);
		contentPane.add(tfso1);
		tfso1.setColumns(10);
		
		tfso2 = new JTextField();
		tfso2.setBounds(103, 70, 96, 19);
		contentPane.add(tfso2);
		tfso2.setColumns(10);
		
		JLabel lblSencondNumber = new JLabel("Sencond Number");
		lblSencondNumber.setBounds(10, 73, 86, 13);
		contentPane.add(lblSencondNumber);
		
		JButton btnConnect = new JButton("CONNECT");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					 SocketClient= new Socket("localhost",8069);
					JOptionPane.showMessageDialog(null, "Kết nối thành công");
				} catch (Exception e) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		});
		btnConnect.setBounds(260, 25, 85, 21);
		contentPane.add(btnConnect);
		
		JButton btnSend = new JButton("SEND");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					outputClient = new DataOutputStream(SocketClient.getOutputStream());
					outputClient.writeBytes(tfso1.getText()+"\n");
					outputClient.writeBytes(tfso2.getText()+"\n");
					getResult();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		btnSend.setBounds(260, 69, 85, 21);
		contentPane.add(btnSend);
		
		JButton btnExit = new JButton("EXIT");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					SocketClient.close();
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnExit.setBounds(260, 142, 85, 21);
		contentPane.add(btnExit);
		
		JLabel lblResultFromServer = new JLabel("Result from Server");
		lblResultFromServer.setBounds(10, 150, 86, 13);
		contentPane.add(lblResultFromServer);
		
		tfkq = new JTextField();
		tfkq.setEditable(false);
		tfkq.setBounds(103, 147, 96, 19);
		contentPane.add(tfkq);
		tfkq.setColumns(10);
	}
public void getResult() {
	DataInputStream inputClient;
	try {
		inputClient = new DataInputStream(SocketClient.getInputStream());
		tfkq.setText(inputClient.readLine());
		outputClient.close();
		inputClient.close();
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
}
