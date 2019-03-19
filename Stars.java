package platform.java.slackExercise;

public class Stars {

	public static void main(String[] args) {
		leftStar();
		middleStar();
		number();

	}

	public static void leftStar() {
		for (int i = 0; i < 9; i++) {
			// Loop for space
			for (int j = 8; j > i; --j) {
				System.out.print(" ");
			}
			// Loop for display *
			for (int j = 0; j < i; j++) {
				System.out.print("*");
				// System.out.print(i);

			}
			// Change line
			System.out.println("");
		}
	}

	public static void middleStar() {
		for (int i = 0; i < 6; i++) {
			// Loop for space
			for (int j = 0; j < 6 - i; j++) {
				System.out.print(" ");
			}
			// Loop for display *
			for (int j = 0; j < i * 2 - 1; j++) {
				System.out.print("*");
				// System.out.print(i);

			}
			// Change line
			System.out.println("");
		}

		for (int i = 6; i >= 0; --i) {

			// Loop for starting point
			for (int j = 0; j < 6 - i; j++) {
				System.out.print(" ");
			}
			// Loop for display *
			for (int j = 0; j < i * 2 - 1; j++) {
				System.out.print("*");
				// System.out.print(i);

			}
			// Change line
			System.out.println("");
		}
	}

	public static void number() {
		for (int i = 1; i < 6; i++) {
			// Loop for starting point
			for (int j = 0; j < i - 1; j++) {
				System.out.print(" ");
			}
			// Loop for display *
			for (int j = i; j <= 5; j++) {
				System.out.print(j + " ");
				// System.out.print(i);

			}
			// Change line
			System.out.println("");
		}

		for (int i = 4; i >= 1; --i) {
			// Loop for space
			for (int j = 0; j < i - 1; j++) {
				System.out.print(" ");
			}
			// Loop for display *
			for (int j = i; j <= 5; j++) {
				System.out.print(j + " ");
				// System.out.print(i);

			}
			// Change line
			System.out.println("");
		}
	}

}
