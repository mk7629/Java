package platform.java.practice2;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;


public class Sales {

	public static void main(String[] args) throws Exception {
//        
//		File file1 = new File("C:\\Users\\Student\\Desktop\\clients.txt");
//        File file2 = new File("C:\\Users\\Student\\Desktop\\sales.txt");
//        Scanner sc1 = new Scanner(file1);
//        Scanner sc2 = new Scanner(file2);
//        
//        while(sc1.hasNextLine()){
//            //System.out.println(sc1.nextLine());
//            String client = sc1.nextLine();
//        }
//        
//        while(sc2.hasNextLine()){
//           // System.out.println(sc2.nextLine());
//            String sale = sc1.nextLine();
//        }

        List<String> list1 = Files.readAllLines(Paths.get("C:\\Users\\Student\\Desktop\\clients.txt"), StandardCharsets.UTF_8);
        String[] a = list1.toArray(new String[list1.size()]); 
        System.out.println(a);
        
		String sale = "65000-4000-54000-7000-8000";
		String client = "Walmart-Kroger-Target-Walmart-Target";
		String[][] arr;
		arr = new String[5][2];
		double sum = 0;

		// Splitting the String
		String[] sp = sale.split("-");
		String[] cp = client.split("-");

		// Add sale to array
		for (int i = 0; i < arr.length; i++) {

			arr[i][0] = sp[i];
			arr[i][1] = cp[i];
			// Print each client and sale
			System.out.println(arr[i][1] + " " + arr[i][0]);

			// Get sum with Converting String
			sum = Double.parseDouble(sp[i]) + sum;
			
		}
		
		// Print total
		System.out.println("Total of Sales is " + sum);

		// Get Duplicate client
		for (int i = 0; i < arr.length - 1; i++) {
			int count = 0;

			for (int j = i + 1; j < arr.length; j++) {
				count = count + 1;
				if ((arr[i][1].equals(arr[j][1]))) {

					// System.out.println("Duplicate Client : " + arr[j][1]);

					// System.out.println(count);
					sum = Double.parseDouble(arr[i][0]) + Double.parseDouble(arr[j][0]);
					System.out.println("Duplicate Client: " + arr[j][1] + " Total is " + sum);

				}

			}

		}



	}

}
