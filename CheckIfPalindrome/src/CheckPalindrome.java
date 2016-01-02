// Stephen Hung
// 1/1/16
// This program checks if a String is a Palindrome. 
// It also repeats the program if user wants it to.I

import java.util.*;

public class CheckPalindrome {
	// Scanner that takes in user input.
	private static Scanner input;
	
	public static void main(String[]args){
		input = new Scanner(System.in);
		do{
			System.out.println();
			System.out.print("Enter the String that you would like to check: ");
			String data = input.nextLine();
			boolean palindromeCheck = checkPalindrome(data);
			if(palindromeCheck){
				System.out.println("The String \"" + data + "\" is a palindrome");
			}else{
				System.out.println("The String \"" + data + "\" is not a palindrome");
			}
		}while(repeatQuestion());
	}
	
	// Check if the String is a palindrome.
	public static boolean checkPalindrome(String data){
		String temp = "";
		for(int i = data.length() - 1; i >= 0; i--){
			temp += data.charAt(i);
		}
		if(temp.equalsIgnoreCase(data)){
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
