package minPakke;

public class MinKlasse {

	public static void main(String[] args) {
		
		double a = Double.parseDouble(args[0]);
		double b = Double.parseDouble(args[1]);
		
		Matematik matA = new Matematik();
		matA.givPythagorasVærdier(a, b);
		

		System.out.println("Hello world! " + matA.hentPythagorasUdregning());
	}
}
