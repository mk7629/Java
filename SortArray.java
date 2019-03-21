package platform.java.slackExercise;

import java.util.Arrays;

public class Java190320 {

	public static void main(String[] args) {
		//1. Separate 0 and 1
		int[] arr1 = { 0, 1, 0, 1, 1, 1, 0, 0 };
		int[] result;
		int len1 = arr1.length;
		result = arrSeparate(arr1, len1);

		System.out.println(Arrays.toString(result));

		//2. Combination of four elements 
		int[] arr2 = { 20, 2, 5, 1, 19, 8, 4, 3 };

		result = sumGivenNum(arr2);


	}

	public static int[] arrSeparate(int[] arr, int len) {

		int count = 0;

		// Count how many 0 we have
		for (int i = 0; i < len; i++) {
			if (arr[i] == 0) {
				count++;

			}
		}

		// Loop for make 0
		for (int i = 0; i < count; i++) {

			arr[i] = 0;
			// System.out.println(arr[i]);
		}
		// Loop for make 1 after 0
		for (int i = count; i < len; i++) {

			arr[i] = 1;
		}

		return arr;
	}

	public static int[] sumGivenNum(int[] arr2) {
		int len2 = arr2.length;
		int givenNum = 33;
		// Fix first element and then find other three elements
		for (int i = 0; i < len2 - 3; i++) {
			// Fix second element and then find other two elements
			for (int j = i + 1; j < len2 - 2; j++) {
				// Fix third element and then find forth element
				for (int k = j + 1; k < len2 - 1; k++) {
					// Find the fourth
					for (int l = k + 1; l < len2; l++) {
						//Then sum all 4 match to givenNumber
						if (arr2[i] + arr2[j] + arr2[k] + arr2[l] == givenNum)
							System.out.print("\n" + arr2[i] + " " + arr2[j] + " " + arr2[k] + " " + arr2[l]);
					}
				}
			}
		}

		return arr2;
	}
}
