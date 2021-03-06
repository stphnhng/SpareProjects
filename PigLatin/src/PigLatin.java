// Stephen Hung
// 1/1/16
// Program to turn user inputed Strings into Pig Latin.

import java.util.*;

public class PigLatin {
	// Scanner to receive user input
	private static Scanner input;
	
	public static void main(String[]args){
		input = new Scanner(System.in);
		boolean repeat = false;
		do{
			System.out.print("Enter the String that you would like translated into Pig Latin: ");
			String dataInput = input.nextLine();
			System.out.println("Your translated String is: " + translate(dataInput));
			repeat = repeatQuestion();
		}while(repeat);
	}
	
	// Translates the String into Pig Latin.
	public static String translate(String data){
		String result = "";
		String temp = "";
		Scanner lineReader = new Scanner(data);
		while(lineReader.hasNext()){
			temp = lineReader.next();
			if(isConsonant(temp)){
				result += temp.substring(1) + temp.charAt(0) + "ay ";
			}else{
				result += temp + "yay ";
			}
		}
		return result;
	}
	
	// Return true if the String's first letter is a consonant and false if not.
	public static boolean isConsonant(String temp){
		char firstLetter = Character.toLowerCase(temp.charAt(0));
		switch(firstLetter){
		case 'a':
			return false;
		case 'e':
			return false;
		case 'i':
			return false;
		case 'o':
			return false;
		case 'u':
			return false;
		}
		return true;
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
