// Stephen Hung
// 1/1/16
// Program that counts the number of vowels in a string and repeats if the user wants to
// run the program again.

import java.util.*;

public class CountVowels {
	// Scanner that takes in user input.
	private static Scanner input;
	
	public static void main(String[]args){
		input = new Scanner(System.in);
		do{
			System.out.print("Enter the String you want counted: ");
			String data = input.nextLine();
			System.out.println("The number of vowels in the String is: " + countVowels(data));
		}while(repeatQuestion());
	}
	
	// Counts # of vowels in a String.
	public static int countVowels(String data){
		int counter = 0;
		for(int i = 0; i < data.length(); i++){
			if(isVowel(data.charAt(i))){
				counter++;
			}
		}
		return counter;
	}
	
	// Returns if a char is a vowel.
	public static boolean isVowel(char a){
		switch(a){
		case 'a':
			return true;
		case 'e':
			return true;
		case 'i':
			return true;
		case 'o':
			return true;
		case 'u':
			return true;
		}
		return false;
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
