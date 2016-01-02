// Stephen Hung
// 1/1/16
// Program to count the number of words in a String.
import java.util.*;

public class CountWords {
	// Scanner for user input.
	private static Scanner input;
	
	public static void main(String[]args){
		input = new Scanner(System.in);
		do{
			System.out.print("Enter the phrase in which you want to count the number of words: " );
			String data = input.nextLine();
			System.out.println("The number of words in your phrase is: " + countWords(data));
		}while(repeatQuestion());
	}
	
	// Counts the number of words in a string. 
	// Words are identified if they are separated by a space on both
	// the front and the back.
	public static int countWords(String data){
		int count = 0;
		Scanner lineReader = new Scanner(data);
		while(lineReader.hasNext()){
			count++;
			lineReader.next();
		}
		return count;
	}
	
	// Asks the user whether or not he/she wants to enter another string to be reversed.
	public static boolean repeatQuestion(){
		boolean repeat = false;
		String answer = "";
		do{
			System.out.print("Would you like to reverse another string (Y/N) : ");
			answer = input.nextLine();
			if(answer.equalsIgnoreCase("Y")){
				return true;
			}else if(answer.equalsIgnoreCase("N")){
				return false;
			}else{
				repeat = true;
			}
		}while(repeat);
		return false;
	}
}
