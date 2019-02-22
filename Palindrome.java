//Find Palindrome number between two number range

package platform.java.assignment3;

import java.util.Scanner;

public class Palindrome {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);

		int n1 = 0, n2 = 0;

		System.out.println("Please enter lower limit number: ");
		n1 = s.nextInt();
		System.out.println("Please enter upper limit number: ");
		n2 = s.nextInt();

		// get number range
		for (int i = n1; i <= n2; i++) {

			int r = 0, reverse = 0, temp = i;
			while (temp > 0) {
				r = temp % 10; // Get reminder to add to get reverse number
				reverse = (reverse * 10) + r; // Get reverse number
				temp = temp / 10; // End condition when temp = 0, exit loop

			}

			// check if i = reverse
			if (i == reverse) {
				System.out.println(reverse);
			}

		}

	}

}
