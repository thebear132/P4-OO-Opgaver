package opgave3;

public class Unicycle extends Bicycle {
	private boolean seat;
	private boolean wheel;
	
	public boolean stabilize() {
		System.out.println("Stabilizing!");
		return true;	// We stabilized
	}
	public void pedal() {
		System.out.println("Pedalling!");
		this.accelerate(10);
	}
}
