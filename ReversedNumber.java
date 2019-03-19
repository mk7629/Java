package platform.java.slackExercise;


import java.util.Scanner;

public class ReversedNumber {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);

		int n = 0, r = 0, reverse = 0, temp = 0;
		System.out.println("Please enter number: ");
		
		n = s.nextInt();
		temp = n;
		
		while (temp > 0) {
			r = temp % 10; // Get reminder to add to get reverse number
			System.out.println(r);
			reverse = (reverse * 10) + r; // Get reverse number
			System.out.println(reverse);
			temp = temp / 10; // End condition when temp = 0, exit loop
			System.out.println(temp);
		}
		System.out.println(reverse);

		s.close();
	}

}
