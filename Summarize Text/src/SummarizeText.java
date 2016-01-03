// Stephen Hung
// 1/1/16
// This program will attempt to summarize any article
// the user inputs.

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class SummarizeText {
	// Scanner to read in the article.
	private static Scanner input;
	// String that contains the entire article.
	private static String rawText;
	// PrintWriter to print out the summary to articleFinal.txt in the local directory.
	private static PrintWriter output;
	// JTextArea to print out the file name that the user has specified.
	private static JTextArea text;
	// File that the user specified.
	private static File rawFile;
	
	public static void main(String[]args){
		createJFrame();
	}
	
	// sets the Scanner variable input to whatever file the user chooses and
	// creates a PrintWriter for articleFinal.txt.
	public static void setScanner(Scanner temp){
		input = temp;
		try {
			output = new PrintWriter(new File("articleFinal.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// returns rawFile.
	public static File getFile(){
		return rawFile;
	}
	
	// Creates a basic JFrame for the user to open the file.
	public static void createJFrame(){
		JFrame f = new JFrame("Summarize Text");
		JPanel p = new JPanel(new BorderLayout());
		JButton b = new JButton("Open File");
		JButton summarize = new JButton("Summarize File");
		text = new JTextArea("No File has been chosen");
		f.setContentPane(p);
		summarize.addActionListener(new buttonListener());
		b.addActionListener(new buttonListener());
		p.add(b,BorderLayout.NORTH);
		p.add(text,BorderLayout.CENTER);
		p.add(summarize,BorderLayout.SOUTH);
		f.setSize(300,300);
		f.setVisible(true);
		f.setLocation(500,250);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	// sets the variable rawFile to the user specified File and
	// sets prints out the file name into the JTextArea.
	public static void setFile(String fileName, File f){
		text.setText(fileName);
		rawFile = f;
	}
	
	// Reads in all of the article that the user has inputted.
	// No changes are made to the article at this point.
	public static void readText(){
		String result = "";
		while(input.hasNextLine()){
			result += input.nextLine();
			result += "\n";
		}
		rawText = result;
		readRawText();
	}
	
	// Reads in every paragraph and then reads in every line of those paragraphs.
	// For each paragraph, the most important sentence is chosen from that paragraph,
	// the most important sentence is chosen by which sentence has the greatest number of
	// intersections with other sentences.
	public static void readRawText(){
		Scanner paragraphReader = new Scanner(rawText);
		String paragraph = "";
		Scanner lineReader = null;
		String line = "";
		String tempLine = "";
		ArrayList<String> sentences = new ArrayList<String>();
		while(paragraphReader.hasNextLine()){
			paragraph = paragraphReader.nextLine();
			lineReader = new Scanner(paragraph);
			while(lineReader.hasNext()){
				tempLine = lineReader.next();
				if(tempLine.contains(".")){
					line += tempLine + " ";
					sentences.add(line);
					line = "";
				}else{
					line += tempLine + " ";
				}
			}
			readParagraph(sentences);
		}
		output.close();
		
	}
	
	// Reads in one entire paragraph and then calculates which sentence
	// has the greatest number of intersections with other sentences.
	public static void readParagraph(ArrayList<String> temp){
		ArrayList<Integer> sentenceScore = new ArrayList<Integer>();
		int counter = 0;
		for(int i = 0; i < temp.size(); i++){
			for(int j = 0; j < temp.size(); j++){
				counter += intersectionCount(temp.get(i),temp.get(j));
			}
			sentenceScore.add(counter);
			counter = 0;
		}
		int max = 0;
		int indexMax = 0;
		for(int i = 0; i < sentenceScore.size(); i++){
			if(sentenceScore.get(i) > max){
				max = sentenceScore.get(i);
				indexMax = i;
			}
		}
		if(temp.size() != 0){
			output.println(temp.get(indexMax));
		}
		temp.clear();
	}
	
	// Calculates the intersection count String a (the string being evaluated)
	// and String b (the String being compared).
	public static int intersectionCount(String a, String b){
		int result = 0;
		String temp = "";
		Scanner aReader = new Scanner(a);
		while(aReader.hasNext()){
			Scanner bReader = new Scanner(b);
			temp = aReader.next();
			while(bReader.hasNext()){
				if(bReader.next().equals(temp)){
					result++;
				}
			}
		}
		return result;
	}
	
}

// Action Listener class for opening and choosing the user's file.
class buttonListener implements ActionListener{
	
	// When Open File is clicked, it retrieves the user's text.
	// When Summarize File is clicked, it summarizes the user specified file.
	public void actionPerformed(ActionEvent e){
		if(e.getActionCommand().equals("Open File")){
			JFileChooser fc = new JFileChooser();
		    int returnVal = fc.showOpenDialog(null);
		    try {
				retrieveText(returnVal,fc);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}else if(e.getActionCommand().equals("Summarize File")){
        	try {
				SummarizeText.setScanner(new Scanner(SummarizeText.getFile()));
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	SummarizeText.readText();
		}
	}
	
	// method to retrieve the text the User specifies.
	public void retrieveText(int returnVal,JFileChooser fc) throws FileNotFoundException{
		int lastIndex = 0;
	    if (returnVal == JFileChooser.APPROVE_OPTION) {
	        File file = fc.getSelectedFile();
	        lastIndex = file.toString().lastIndexOf('/');
			SummarizeText.setFile(file.toString().substring(lastIndex+1),file);
	        System.out.println("Opening: " + file.getName() + ".\n");
	    } else {
	        System.out.println("Open command cancelled by user.\n");
	    }
	}
}