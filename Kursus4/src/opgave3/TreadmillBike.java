package opgave3;

public class TreadmillBike extends Bicycle {
	private String treadmill;
	private String brake;
	public void walk() {
		System.out.println("Walking faster!");
		this.accelerate(5);
	}
	public void pressBrakes() {
		this.accelerate(-5);
	}
	

}
