package minPakke;

public class Matematik {
	private double a;
	private double b;
	private double resultat;
	
	public void givPythagorasVærdier(double a, double b) {
		this.a = a;
		this.b = b;
	}
	
	public double hentPythagorasUdregning() {
		this.resultat = Math.sqrt(this.a*this.a + this.b * this.b);
		return this.resultat;
	}
}
