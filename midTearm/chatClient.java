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
import java.net.ConnectException;
import java.net.Socket; 
import java.net.UnknownHostException; 

import javax.swing.JButton; 
import javax.swing.JFrame; 
import javax.swing.JOptionPane; 
import javax.swing.JPanel; 
import javax.swing.JScrollPane; 
import javax.swing.JTextArea; 
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent; 

public class chatClient extends JFrame implements ActionListener{ 
     
    public JTextArea msgDisplay; 
    private JTextField msg; 
    private JButton send; 
     
    private DataOutputStream dos; 
    private DataInputStream dis; 
     
    public chatClient(){ 
        super("Client"); 
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
        	if(e.getKeyCode()==KeyEvent.VK_ENTER) {
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
    public static void main(String[] args) { 
        new chatClient().go(); 
    } 
    private void go() { 
        // Tao ket noi lang nghe o day 
         
        try { 
            msgDisplay.append("Client đã chạy, chưa kết nối được với Server\n"); 
            Socket client = new Socket("localhost",2000); 
            msgDisplay.append("Đã kết nối thành công.\n"); 
             
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
                else msgDisplay.append("Server : "+temp+"\n"); 
            } 
             
            msgDisplay.append("Server đã ngắt kết nối với bạn.\n"); 
            dis.close(); 
            dos.close(); 
            client.close(); 
             
        } catch (UnknownHostException e) { 
            e.printStackTrace(); 
        } catch (IOException e) { 
            e.printStackTrace(); 
        } 
         
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
                msgDisplay.append("Client : "+msg.getText()+"\n"); 
            } catch (IOException e1) { 
                JOptionPane.showMessageDialog(this, "Kết nối đã ngắt", "Cảnh báo", JOptionPane.WARNING_MESSAGE); 
            } 
        msg.setText(""); 
    } 
    }

} 