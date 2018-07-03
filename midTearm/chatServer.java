
package chat; 

import java.awt.BorderLayout; 
import java.awt.FlowLayout; 
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener; 
import java.awt.event.WindowAdapter; 
import java.awt.event.WindowEvent; 
import java.io.DataInputStream; 
import java.io.DataOutputStream; 
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket; 
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton; 
import javax.swing.JFrame; 
import javax.swing.JLabel; 
import javax.swing.JOptionPane; 
import javax.swing.JPanel; 
import javax.swing.JScrollPane; 
import javax.swing.JTextArea; 
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent; 

public class chatServer extends JFrame implements ActionListener{ 
    public JTextArea msgDisplay; 
    private JTextField msg; 
    private JButton send; 
     
    private DataOutputStream dos; 
    private DataInputStream dis; 
    InetAddress myhost;
     
    public chatServer(){ 
        super("Server"); 
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); 
        this.addWindowListener(new WindowAdapter(){ 
            public void windowClosing(WindowEvent e){ 
                System.exit(0); 
            } 
        }); 
        setSize(600, 400); 
        addItem(); 
        setVisible(true); 
    } 
    public static void main(String[] args) { 
        new chatServer().go(); 
    } 
    private void go() { 
        // tao socket o day 
    	try {
			myhost= InetAddress.getLocalHost();
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        try { 
            ServerSocket server = new ServerSocket(2000); 
            msgDisplay.append("Server bắt đầu chạy.\n");
            msgDisplay.append("Địa chỉ IP: "+myhost.getHostAddress() +"\n");
            Socket client = server.accept(); 
            msgDisplay.append("Client đã kết nối với bạn.\n"); 
             
            dos = new DataOutputStream(client.getOutputStream()); 
            dis = new DataInputStream(client.getInputStream()); 
             
            String temp =null; 
             
            while(true){ 
                temp = dis.readUTF(); 
                if(temp.toUpperCase().equals("QUIT")){ 
                    dos.writeUTF("QUIT"); 
                    dos.flush(); 
                    break; 
                } 
                else msgDisplay.append("Client : "+temp+"\n"); 
            } 
             
            msgDisplay.append("Client đã ngắt kết nối.\n"); 
            dis.close(); 
            dos.close(); 
            server.close(); 
             
        } catch (IOException e) { 
            e.printStackTrace(); 
        } 
         
    } 
    private void addItem() { 
        getContentPane().setLayout(new BorderLayout()); 
        msgDisplay = new JTextArea(); 
        msgDisplay.setEditable(false); 
        JPanel p = new JPanel(); 
        p.setLayout(new FlowLayout(FlowLayout.CENTER)); 
        msg = new JTextField(40); 
        msg.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyPressed(KeyEvent e) {
        		if(e.getKeyCode()== KeyEvent.VK_ENTER) {
        			send();
        		}
        	}
        });
        send = new JButton("Send"); 
        send.addActionListener(this); 
         
        p.add(msg); 
        p.add(send); 
         
        getContentPane().add(new JScrollPane(msgDisplay),BorderLayout.CENTER); 
        getContentPane().add(p,BorderLayout.SOUTH); 
        
    } 

    @Override 
    public void actionPerformed(ActionEvent e) { 
        send();
         
    } 
    public void send() {
    	if(msg.getText().compareTo("")!=0){ 
            try { 
                dos.writeUTF(msg.getText()); 
                dos.flush(); 
                msgDisplay.append("Server : "+msg.getText()+"\n"); 
            } catch (IOException e1) { 
                JOptionPane.showMessageDialog(this, "Kết nối đã ngắt", "Cảnh báo", JOptionPane.WARNING_MESSAGE); 
            } 
            catch(NullPointerException e1) {
            	JOptionPane.showMessageDialog(null, "Chưa có client kết nối, không thể gửi");
            }
            msg.setText(""); 
        } 
    }
} 