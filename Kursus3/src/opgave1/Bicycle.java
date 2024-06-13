package opgave1;

public class Bicycle {
	public String name;
	private String color;
	private int speed = 0;
	private int roundsPerMinute = 0;
	private int gear = 1;
	
	public Bicycle(String name, String color) {
		this.name = name;
		this.color = color;
	}
	public void PrintStatus() {
		System.out.println("Den " + this.color + "e cykel \"" + this.name + "\" har hastighed " + this.GetSpeed() + " og er i gear " + this.gear + " og rpm " + this.roundsPerMinute);
	}
	public void Accelerate() {
		this.roundsPerMinute += 10;
	}
	public void GearUp() {
		this.gear++;
	}
	public int GetSpeed() {
		this.speed = this.roundsPerMinute * this.gear;
		return this.speed;
		
	}
	
	public static void main(String args[]) {
		Bicycle bike = new Bicycle("Lynet", "blå");
		bike.PrintStatus();
		bike.Accelerate();
		bike.GearUp();
		bike.PrintStatus();
		bike.GearUp();
		bike.PrintStatus();
		bike.Accelerate();
		bike.PrintStatus();
	}
}
