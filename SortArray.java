//Sort Array Elements

package platform.java.assignment4.sortarray;

import java.util.Arrays;

public class SortArray {

	public static void main(String[] args) {
		int[] arr = new int[] { 2, 7, 1, 5, 0 };

		for (int i = 0; i < arr.length; i++) {
			Arrays.sort(arr);
		}
		
		System.out.println(Arrays.toString(arr));
	}

}
