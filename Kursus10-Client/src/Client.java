import java.util.Scanner;
import java.io.FileWriter;

public class Client {
	public static void main(String args[]) {
		Scanner myInputScanner = new Scanner(System.in);
		System.out.println("----------Query the server----------");
		
		while (true) {
			System.out.print("Query: ");
		    String inputSearch = myInputScanner.nextLine().strip();  // Read user input
			// String inputSearch = "w < 55";
		    if (inputSearch.equals("exit"))
		    	break;
		    
		    try {
	            FileWriter fw = new FileWriter("query.txt", true);
	            for (int i = 0; i < inputSearch.length(); i++)
	                fw.write(inputSearch.charAt(i));
	            System.out.println("Success");
	            fw.close();
	        } 
	        catch (Exception e) {
	        	e.getStackTrace();
	        	System.out.print("Query failed: ");
	        	System.out.println(e);
	        }
		}
		
	}
}
