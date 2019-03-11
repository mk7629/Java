package platform.java.arrays;



public class Array {

	public static void main(String[] args) {
//		Scanner scan = new Scanner(System.in);
//		int[][] arr = new int[3][3];
//		
//		int[][] arr2 = new int[][]{{1,2},{1,2},{1,2}};
//		
//		int[][] arr3;
		
		int r=4, c=7;
		int[][] array = new int[r][c];
		
//		System.out.println(arr.length);
		//get row length
		
		int count=1;
		for(int i=0; i<r; i++) {//control row
			//get col length
			for(int j=0; j<c; j++) {//control col
				//display col value
				//System.out.println("Enter a value: ");
				array[i][j] = count++;
			}
		}
		
		//print array
		for(int[] row:array) {//row inside array
			//get col length
			for(int element:row) {
				//display col value
				System.out.println(element + " - ");
			}
			System.out.println("\n");
		}
		
	

	}

}
