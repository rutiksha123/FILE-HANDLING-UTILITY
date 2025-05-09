package abc;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.*;

import javax.swing.*;



public class FileHandling_opr implements ActionListener

{
	String str;
	JButton b1,b2,b3;
	JTextArea ta;
	JTextField ft;
	JFrame f;
	public  FileHandling_opr()
	{
		f= new JFrame("File Handling");
		JLabel filename = new JLabel("Selected File : ");
		ft = new JTextField(20);		
		ft.setEditable(false);
		ft.setFocusable(false);
		
		JPanel fp = new JPanel(new FlowLayout(FlowLayout.LEFT));
		fp.add(filename);
		fp.add(ft);
        f.add(fp, BorderLayout.NORTH);
		
        ta = new JTextArea(20, 30);
        JScrollPane sp = new JScrollPane(ta);
        f.add(sp, BorderLayout.CENTER);
		
        JPanel bp = new JPanel();
        b1 = new JButton("📂 Open/Read File");
        b1.addActionListener(this);
        b2 = new JButton("✏ Write to a File");
        b2.addActionListener(this);
        b3 = new JButton("📝 Modify file");
        b3.addActionListener(this);
        bp.add(b1);
        bp.add(b2);
        bp.add(b3);
        f.add(bp, BorderLayout.SOUTH);
        
		f.setSize(500,500);
		
		f.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		//Reading file contents
		if(e.getSource()==b1)
		{
			
			JFileChooser jf = new JFileChooser();
			jf.showOpenDialog(null);
			try
			{
				File selectedFile = jf.getSelectedFile();
				str=selectedFile.getPath();
				ft.setText(str);
				FileReader fr = new FileReader(selectedFile);
				ta.read(fr, null);
				
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(f,"Error reading file","Error",JOptionPane.ERROR_MESSAGE);
			}			
		}
		
		//Writing  to the file
		if(e.getSource()==b2)
		{
			
			String abc=JOptionPane.showInputDialog(f,"Enter the name of the file");
			
			if (abc == null || abc.trim().isEmpty())
			{
                JOptionPane.showMessageDialog(f, "Filename cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
			
			//checking if the file already exists or not
			 File file = new File(abc);
	            if (file.exists()) {
	                int choice = JOptionPane.showConfirmDialog(f, "File exists. Overwrite?", "Confirm Overwrite", JOptionPane.YES_NO_OPTION);
	                if (choice != JOptionPane.YES_OPTION) return;
	            }
	            
	            
			try
			{				
				FileWriter fw = new FileWriter(abc);
				fw.write(ta.getText());
				JOptionPane.showMessageDialog(f,"Contents written to the file","Info",JOptionPane.INFORMATION_MESSAGE);
				fw.close();
				ta.setText("");
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(f, "Error writing to file", "Error", JOptionPane.ERROR_MESSAGE);
			}			
		} 
		
		//Modifying the file contents
		if(e.getSource()==b3)
		{
			try
			{
				FileWriter fw = new FileWriter(str);
				fw.write(ta.getText());
				JOptionPane.showMessageDialog(f,"Contents of the file modified","Info",JOptionPane.INFORMATION_MESSAGE);
				fw.close();
				ta.setText("");
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(f, "Error modifying file", "Error", JOptionPane.ERROR_MESSAGE);
			}			
		} 
	}
	
	public static void main(String[] args)
	{
		new FileHandling_opr();
	}
}