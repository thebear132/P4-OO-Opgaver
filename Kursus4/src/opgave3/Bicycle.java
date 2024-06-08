package opgave3;

public abstract class Bicycle {
	protected String color;
	protected int stelNr;
	protected void accelerate(int n) {
		System.out.println("Accelerating : " + n);
	}
	
	
	
	public static void main(String[] args) {
		Unicycle unc = new Unicycle();
		unc.pedal();
		unc.pedal();
		unc.stabilize();
		
		TreadmillBike tmb = new TreadmillBike();
		tmb.walk();
		tmb.pressBrakes();
	}
}
