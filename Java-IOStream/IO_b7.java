
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.awt.event.ActionEvent;
      
public class IO_b7 extends JFrame {

	private JPanel contentPane;
	JFileChooser chooser;
	JTextArea tanguon;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IO_b7 frame = new IO_b7();
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
	public IO_b7() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 848, 407);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tanguon = new JTextArea();
		tanguon.setBounds(10, 10, 812, 315);
		contentPane.add(tanguon);
		
		JButton btnSaveAs = new JButton("Save as");
		btnSaveAs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				chooser= new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File("F:\\"));
				chooser.setDialogTitle("Save file as..");
			if(chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
				String filename= chooser.getSelectedFile().getAbsolutePath();
				saveFile(filename);
			}
			}
		});
		btnSaveAs.setBounds(355, 338, 85, 21);
		contentPane.add(btnSaveAs);
	}
public void saveFile(String filename) {
	FileWriter fw= null; 
	try {
		fw =new FileWriter(filename);
		String content= tanguon.getText();
		fw.write(content);
		fw.flush();
		
	}
	catch (Exception e) {
		JOptionPane.showMessageDialog(null, e.getMessage());
	}
	finally {
		try {
			fw.close();
			JOptionPane.showMessageDialog(null, "File "+filename +" đã được lưu lại");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
}
