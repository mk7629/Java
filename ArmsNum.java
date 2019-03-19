package platform.java.slackExercise;

import java.util.Scanner;

public class ArmsNum {

	public static void main(String[] args) {
		Integer num = 0;
		int total = 0;
		Scanner s = new Scanner(System.in);

		System.out.println("Please enter number between 1 to 500.");
		num = s.nextInt();
		// Input validation
		if (num > 500 || num < 0) {
			System.out.println("Please enter valid number");
			return;
		}

		// Finding input length
		int length = String.valueOf(num).length();
		// System.out.println(length);

		int temp = 0, r = 0;
		temp = num;
		
		while (temp != 0) {
			r = temp % 10; // Get reminder to separate number

			System.out.println(r);
			total += (int) Math.pow(r, length); // Get total with power of separate number
			System.out.println(total);

			temp = temp / 10;  //End while loop 

		}
		if (total == num) {
			System.out.println("Your number is Armstrong Number!");
		}
		else {
			System.out.println("Not a Armstrong number");
		}
		s.close();
	}
	
}
