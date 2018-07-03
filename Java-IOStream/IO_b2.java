
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class IO_b2 extends JFrame {

	private JPanel contentPane;
	private JTextField tfsdt;
	private JTextField tfemail;
	private JTextField tfbirth;
	private JTextField tfname;
	FileWriter fw= null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IO_b2 frame = new IO_b2();
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
	public IO_b2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblHTn = new JLabel("H\u1ECD T\u00EAn");
		lblHTn.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblHTn.setBounds(10, 10, 65, 26);
		contentPane.add(lblHTn);
		
		JLabel lblNgySinh = new JLabel("Ng\u00E0y Sinh");
		lblNgySinh.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNgySinh.setBounds(10, 56, 65, 26);
		contentPane.add(lblNgySinh);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblEmail.setBounds(10, 104, 65, 26);
		contentPane.add(lblEmail);
		
		JLabel lblSinThoi = new JLabel("S\u1ED1 \u0111i\u1EC7n tho\u1EA1i");
		lblSinThoi.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSinThoi.setBounds(10, 155, 86, 26);
		contentPane.add(lblSinThoi);
		
		tfsdt = new JTextField();
		tfsdt.setBounds(125, 160, 279, 19);
		contentPane.add(tfsdt);
		tfsdt.setColumns(10);
		
		tfemail = new JTextField();
		tfemail.setColumns(10);
		tfemail.setBounds(125, 109, 279, 19);
		contentPane.add(tfemail);
		
		tfbirth = new JTextField();
		tfbirth.setColumns(10);
		tfbirth.setBounds(125, 61, 279, 19);
		contentPane.add(tfbirth);
		
		tfname = new JTextField();
		tfname.setColumns(10);
		tfname.setBounds(125, 15, 279, 19);
		contentPane.add(tfname);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					fw= new FileWriter("F:\\testIOStreamJava\\test.txt");
					SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
					df.setLenient(false);// set false để kiểm tra tính hợp lệ của date.
					 df.parse(tfbirth.getText()); // parse dateString thành kiểu Date
					 String emailreg= "^[\\w-]+@([\\w- ]+\\.)+[\\w-]+$"; // biểu thức quy chuẩn để check địa chỉ mail
					 if (tfemail.getText().matches(emailreg) == false) throw new emailException(); // nếu mail không hợp lê, ném vào emailException

					 if(tfsdt.getText().length()<10 || tfsdt.getText().length()>11) throw new phoneLengthException();// nếu độ dài không hợp lệ, ném vào phoneLengthException
					 Double.parseDouble(tfsdt.getText());// chuyển về Double để kiểm tra có phải nhập vào là số không, nếu sai bắt lỗi ở NumberFormatException
					
					 fw.write(tfname.getText()+ "&" +tfbirth.getText()+"&"+tfemail.getText()+"&"+tfsdt.getText());
						fw.flush();
						JOptionPane.showMessageDialog(null, "Tệp F:\\\\testIOStreamJava\\\\test.txt đã được lưu lại ");
				}
				catch(phoneLengthException e) {
					 JOptionPane.showMessageDialog(null, "Độ dài số điện thoại phải từ 10-11");
				}
				catch(NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Số điện thoại phải là số");
				}
				catch (ParseException e) { // Xử lí nếu ngày sinh không hợp lệ
					JOptionPane.showMessageDialog(null, "Ngày sinh không hợp lệ, vui lòng nhập lại với định dạng dd/mm/yyyy");
				}
				catch (emailException e) {
					JOptionPane.showMessageDialog(null, "Địa chỉ mail không hợp lệ, vui lòng nhập lại");
				}
				catch(Exception e){
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			
			finally {
				try {
					fw.close();
				} catch (Exception e) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}}
		});
		btnSave.setBounds(60, 216, 85, 21);
		contentPane.add(btnSave);
		
		JButton btnCancle = new JButton("Cancle");
		btnCancle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(DISPOSE_ON_CLOSE);
				try {
					fw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnCancle.setBounds(264, 216, 85, 21);
		contentPane.add(btnCancle);
		
		//loadfile:
		loadfile();
	}
	public void loadfile(){
		FileReader fr = null;
		try {
			String s="";
			fr= new FileReader("F:\\testIOStreamJava\\test.txt");
			int data = fr.read();
			while(data!=-1) {
				s+= (char) data;
				data=fr.read();
			}
			String s1[]= s.split("&");
			tfname.setText(s1[0]);
			tfbirth.setText(s1[1]);
			tfemail.setText(s1[2]);
			tfsdt.setText(s1[3]);
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	
	finally {
		try {
			fr.close();
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}}
		
	}
class phoneLengthException extends Exception{
	public phoneLengthException() {
		super();
	}
}
class emailException extends Exception{
	public emailException() {
		super();
	}
}
