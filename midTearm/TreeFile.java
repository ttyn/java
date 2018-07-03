package TreeFile;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

public class TreeFile {
	 JFrame frame;
	JPopupMenu popupMenu;
	JMenuItem extractItem;
	JButton btnextract;
	JButton btextractto;
	String selected;
//	JTree tree;
	FileTreeModel model;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	 new TreeFile();
	}

	/**
	 * Create the application.
	 */
	public TreeFile() {
		addItem();
	}
	public void addItem()
	{
		frame= new JFrame("Tree File");
		 File root= new File("E:\\");
		
		 model = new FileTreeModel(root);
		
	    JTree tree = new JTree(model);
	
//	    tree.setModel(mode);
//	    tree.add(comp)
		
	    tree.addTreeSelectionListener(new TreeSelectionListener() {
	    	 public void valueChanged(TreeSelectionEvent event) {
	    		 try {
	    		 	selected = tree.getLastSelectedPathComponent().toString();
	    		 	if(selected.endsWith("zip")) {
	    		 		btnextract.setVisible(true);
	    		 		btextractto.setVisible(true);
	    	        
	    	      }
	    		 	else {
	    		 		btnextract.setVisible(false);
	    		 		btextractto.setVisible(false);
	    		 	}
	    	 }
	    		 catch (NullPointerException e) {
					// TODO: handle exception
//	    			 JOptionPane.showMessageDialog(null, "Có lỗi xảy ra khi chọn file");
				}
	    	 }
	    	    });
	    JScrollPane scrollpane = new JScrollPane(tree);
	    frame.getContentPane().add(scrollpane);
		
		
		
//	     frame = new JFrame("FileTreeDemo");
	    JPanel p1= new JPanel();
	   
	    btnextract= new JButton("Extract here");
	    p1.add(btnextract);
	    btnextract.setVisible(false);
	    btnextract.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String out= selected.replaceAll(".zip", "");
				extractZip(selected, out);
				tree.setModel(new FileTreeModel(root));
				
			}
		});
	    btextractto = new JButton("Extract to");
	    p1.add(btextractto);
	    btextractto.setVisible(false);
	    btextractto.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			    try {
			    	JFileChooser chooser = new JFileChooser();
				    chooser.setCurrentDirectory(new java.io.File("E:\\"));
				    chooser.setDialogTitle("Chọn thư mục giải nén");
				    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				    chooser.setAcceptAllFileFilterUsed(false);
				    chooser.showOpenDialog(null);
			    String out= chooser.getSelectedFile().toString();
			    extractZip(selected, out);
			}
			    catch (NullPointerException e1) {
					// TODO: handle exception
			    	JOptionPane.showMessageDialog(null, "Đã hủy");
				}
			}	
		});
//	    frame= new JFrame("Tree File");
//	    frame.getContentPane().add(scrollpane,"Center");
	    frame.add(p1,"South");
	    frame.setSize(400, 600);
	    frame.setVisible(true);
	    
	
	}
	public void extractZip(String file, String output) {
		File folder= new File(output);
		 if (!folder.exists()) {
	            folder.mkdirs();
	        }
		 byte[] buffer= new byte[1024];
		 ZipInputStream zip= null;
		 
		 try {
			 zip= new ZipInputStream(new FileInputStream(file));
			 ZipEntry entry = null;
			while((entry=zip.getNextEntry())!=null) {
				String entryName= entry.getName();
				String outFileName= output+File.separator+entryName;
				if (entry.isDirectory()) {
					// t?o thu m?c 
					new File(outFileName).mkdirs();
				}
				else {
					FileOutputStream fos= new FileOutputStream(outFileName);
					  int len;
	                    // Ã�?c d? li?u trÃªn Entry hi?n t?i.
	                    while ((len = zip.read(buffer)) > 0) {
	                        fos.write(buffer, 0, len);
	                    }
	                    JOptionPane.showMessageDialog(null, "File "+outFileName+" đã được giải nén");
						fos.close();
				
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		}
	}
}
	
class FileTreeModel implements TreeModel{
	protected File root;
	public FileTreeModel (File root) {
		this.root=root;
	}

public Object getRoot() {
	return root;
}
public boolean isLeaf(Object node) {
	return ((File)node).isFile();
}
public int getChildCount(Object parent) {

	String[] chidren= ((File)parent).list(); // luu danh sÃ¡ch m?c con vÃ o m?ng chidren
	if(chidren==null) return 0; // tr? v? s? lu?ng m?c con
	else return chidren.length;
}

public Object getChild(Object parent, int index) {
//Tr? v? v? trÃ­ file index,
	String[] children= ((File) parent).list();
	if(children==null ||index >= children.length )
		return null;
	else return new File((File) parent, children[index]);// tr? v? file con
}
public int getIndexOfChild(Object parent, Object child) {
	// TÃ¬m v? trÃ­ th? t? c?a m?c con trong m?c cha
	String[] children = ((File)parent).list();
    if (children == null) return -1;
    String childname = ((File)child).getName();
    for(int i = 0; i < children.length; i++) {
      if (childname.equals(children[i])) return i;
    }
    return -1;
  }

public void valueForPathChanged(TreePath path, Object newvalue) {}
public void addTreeModelListener(TreeModelListener l) {}
public void removeTreeModelListener(TreeModelListener l) {}
}
