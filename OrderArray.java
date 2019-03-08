package platform.java.practice;

import java.util.Scanner;

public class OrderArray {

	public static void main(String[] args) 
	{
		Scanner scan = new Scanner(System.in);
		double[][] arr; //= new int[][] {{10,1},{15,0},{20,5},{25,2}};
//		double sumPrice =0.0, 
//				sumTip =0.0,
//				avgPrice =0.0,
//				avgTip =0.0;
			
		int size =0;
		double[] ret;
		
		System.out.println("Enter number of orders: ");
		
		
		size = scan.nextInt();
		arr = new double[size][2];
		
		fillArray(arr); // return fillArray method

			
		ret = Calc(arr); // container to receive return value

		print(ret);
		
		scan.close();
	
	}

	public static void fillArray(double[][] a) {
		try {
		Scanner scan = new Scanner(System.in);
		for (int i = 0; i < a.length+1; i++) {

			System.out.println("Enter price of order: ");
			a[i][0] = scan.nextDouble();
			System.out.println("Enter tip of order: ");
			a[i][1] = scan.nextDouble();

		}

		scan.close();
		}
		catch(Exception e) {
			System.out.println("Out of Array");
			return;
		}

			Scanner scan = new Scanner(System.in);
			for (int i = 0; i < a.length; i++) {

				System.out.println("Enter price of order: ");
				a[i][0] = scan.nextDouble();
				System.out.println("Enter tip of order: ");
				a[i][1] = scan.nextDouble();

			}

			scan.close();
		
	}

	public static double[] Calc(double[][] a) {
		double sumPrice = 0.0, sumTip = 0.0, avgPrice = 0.0, avgTip = 0.0;
		double[] ret = new double[4];

		for (int i = 0; i < a.length; i++) {
			sumPrice = a[i][0] + sumPrice;
			sumTip = a[i][1] + sumTip;

		}

		avgPrice = sumPrice / a.length;
		avgTip = sumTip / a.length;

		ret[0] = sumPrice;
		ret[1] = sumTip;
		ret[2] = avgPrice;
		ret[3] = avgTip;

		return ret;

	}

	public static void print(double[] a) {
		System.out.println("Total revenu: " + a[0]);
		System.out.println("Total tips: " + a[1]);
		System.out.println("Avg price: " + a[2]);
		System.out.println("Avg tips: " + a[3]);
	}

}
