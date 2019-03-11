package platform.grade;

import java.util.Scanner;

public class displaygrade {

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in); // Create in Object
		System.out.println("Enter score: "); // Display sentence
		double score = in.nextDouble(); // get input from user

		int grade = (int) Math.floor(score / 10);
		System.out.println(grade);

		// .if statement
		if (score < 0) {
			System.out.println("Out of range");
		}

		else {
			switch (grade) {
			case 9:
				System.out.println("A");
				break;
			case 8:
				System.out.println("B");
				break;
			case 7:
				System.out.println("C");
				break;
			case 6:
				System.out.println("D");
				break;
			default:
				System.out.println("F");
			}

		}
		
		in.close();
		
		 /* else if(score <= 100 && score >= 90) { System.out.println("A"); } else
		 * if(score <90 && score >= 80) {
		 * 
		 * System.out.println("B"); } else if(score <80 && score >= 70) {
		 * 
		 * System.out.println("C"); } else if(score <70 && score >= 60) {
		 * 
		 * System.out.println("D"); } else if(score <60) {
		 * 
		 * System.out.println("F"); } else { System.out.println("Score out of range"); }
		 
*/
	}
}
