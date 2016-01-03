// Stephen Hung
// 1/2/16
// This program creates a TextEditor with a GUI that allows users to create
// new files and save the text written to those files.

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class TextEditor {
	
	public static void main(String[]args){
		createJFrame();		
	}
	
	// Creates the GUI
	public static void createJFrame(){
		JFrame frame = new JFrame("Text Editor");
		JPanel panel = new JPanel(new BorderLayout());
		JMenuBar bar = new JMenuBar();
		JMenu firstOption = new JMenu("File");
		JMenuItem newFile = new JMenuItem("New File");
		JTextArea text = new JTextArea("Type text here...");
		text.setVisible(false);
		JMenuItem saveFile = new JMenuItem("Save File");
		newFile.addActionListener(new MenuActionListener(text));
		saveFile.addActionListener(new MenuActionListener(text));
		bar.add(firstOption);
		firstOption.add(newFile);
		firstOption.add(saveFile);
		frame.setContentPane(panel);
		panel.add(text);
		frame.setSize(500,500);
		frame.setLocation(500,250);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setJMenuBar(bar);
		frame.setVisible(true);
	}
	
}

// ActionListener class for creating a new file and saving that file.
class MenuActionListener implements ActionListener{
	// JTextArea in order to retrieve text that the user has written.
	private JTextArea textArea;
	// PrintStream in order to print out the user's text to the specified File.
	private static PrintStream output;
	
	// Constructor to initialize the local JTextArea object.
	public MenuActionListener(JTextArea text){
		textArea = text;
	}
	
	// Method that runs when the JMenuItem "New File" & "Safe File" are clicked on.
	// When New File is clicked on, it runs createTextFile().
	// When Save File is clicked on, it prints out the user's text to the user specified File.
	public void actionPerformed(ActionEvent e){
		if(e.getActionCommand().equals("New File")){
			textArea.setVisible(true);
			createTextFile();
		}else if(e.getActionCommand().equals("Save File")){
			System.out.println(textArea.getText());
			output.print(textArea.getText());
		}
	}
	
	// Creates a text file with the user specified file name.
	public static void createTextFile(){
		String fileName = JOptionPane.showInputDialog("What would you like to name the file?");
		try {
			output = new PrintStream(new File(fileName + ".txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}



