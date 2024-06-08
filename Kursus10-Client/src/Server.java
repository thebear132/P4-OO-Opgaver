import java.io.FileReader;
import java.util.Scanner;

public class Server {
	
	public static void main(String args[]) {
		System.out.println("Server started");
		while (true) {
		    try {
	            FileReader fr = new FileReader("query.txt");
	            int i;
	            while ((i = fr.read()) != -1) {
	                System.out.print((char)i);
	            }
	            fr.close();
	            break;
	        } 
	        catch (Exception e) {
	        	e.getStackTrace();
	        	System.out.print("Query failed: ");
	        	System.out.println(e);
	        }
		}
	}
}
