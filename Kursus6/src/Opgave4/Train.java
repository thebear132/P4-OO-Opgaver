package Opgave4;

public class Train implements ElectricTrain {
	private double weight;
	private int ManufacturerNumber;
	private float Velocity;
	
	public void charge_battery() { System.out.println("Hej"); }
	public void get_battery_status() { System.out.println("Hej"); }
	public void discharge_battery() { System.out.println("Hej"); }
	public void accelerate() { System.out.println("acc"); }
	public void honk() { System.out.println("honk"); }
	
	public static void main(String args[]) {
		System.out.println("Hej");
		Vehicle myTrain = new Train();
		// myTrain.honk()
	}
}
