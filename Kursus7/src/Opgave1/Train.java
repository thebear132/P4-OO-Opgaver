package Opgave1;
import java.util.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class SortByCustom implements Comparator<Train> {
	String type;
	public SortByCustom (String type) {
		this.type = type;
	}
	public int compare(Train a, Train b) {
		switch (type.strip()) {
		case "w":
			if (a.getWeight() < b.getWeight()) {
				return 1;
			}
			return -1;
		case "m":
			if (a.getManufacturerNumber() < b.getManufacturerNumber()) {
				return 1;
			}
			return -1;
		case "v":
			if (a.getVelocity() < b.getVelocity()) {
				return 1;
			}
			return -1;
		default:
			System.out.println("Bad comparator!");
			return 0;
		}
	}
}

public class Train implements ElectricTrain {
	private double weight;
	private int ManufacturerNumber;
	private float Velocity;
	
	public Train(double weight, int ManufacturerNumber, float Velocity) {
		this.weight = weight;
		this.ManufacturerNumber = ManufacturerNumber;
		this.Velocity = Velocity;
	}
	@Override
	public String toString() {
		return "Train " + this.ManufacturerNumber + " weighs: " + this.weight + " with speed " + this.Velocity;
	}
	
	public void charge_battery() { System.out.println("Hej"); }
	public void get_battery_status() { System.out.println("Hej"); }
	public void discharge_battery() { System.out.println("Hej"); }
	public void accelerate() { System.out.println("Hej"); }
	public void honk() { System.out.println("Hej"); }
	public double getWeight() { return this.weight; }
	public int getManufacturerNumber() { return this.ManufacturerNumber; }
	public double getVelocity() { return this.Velocity; }
	
	public static void main(String args[]) {
		Random rnd = new Random();
		ArrayList<Train> trains = new ArrayList<Train>();
		int AmountOfTrains = 5;
		for (int i = 0; i < AmountOfTrains; i++) {
			trains.add(new Train(rnd.nextDouble(50, 60), rnd.nextInt(0, AmountOfTrains*10), rnd.nextFloat(50)));
			// System.out.println(trains.get(i).toString());
			System.out.println(trains.get(i));	// toString overrider
		}
    	
		
	    Scanner myInputScanner = new Scanner(System.in);  // Create a Scanner object to read from commandline
	    
	    while (true) {
	    	System.out.println("\n----------SEARCH----------");
	    	System.out.println("Attributes are w(weigth) m(manufacturerNr) v(velocity)");
			System.out.println("FORMAT: [attribute] [<,>,==,!=] [value]:");
		    String inputSearch = myInputScanner.nextLine().strip();  // Read user input
			// String inputSearch = "w < 55";
		    if (inputSearch.equals("exit"))
		    	break;
		    
		    Pattern pattern = Pattern.compile("(v|w|m) (<|>|==|!=) (.*)", Pattern.CASE_INSENSITIVE);
		    Matcher matcher = pattern.matcher(inputSearch);
		    if (!matcher.find()) {
		    	System.out.println("No match, try again");
		    	continue;
		    }
		    // Sort using the comparator
		    System.out.println("Matching to " + matcher.group(1));
		    // Use Collections, the list and our SortByCustom comparator to sort the trains
		    Collections.sort(trains, new SortByCustom(matcher.group(1)));
		    System.out.println(trains);
		    
		    
		    for (Iterator<Train> i = trains.iterator(); i.hasNext();) {
		    	Train tmpTrain = i.next();
		    	double a = 0.0;
		    	switch (matcher.group(1)) {	// The attribute
		    	case "w":
		    		a = tmpTrain.getWeight();
		    		break;
		    	case "m":
		    		a = tmpTrain.getManufacturerNumber();
		    		break;
		    	case "v":
		    		a = tmpTrain.getVelocity();
		    		break;
		    	default: break;
		    	}
		    	
		    	switch (matcher.group(2)) {
		    	case "<":
		    		if (!(a < Integer.parseInt(matcher.group(3)))) { i.remove(); }
		    		break;
		    	case ">":
		    		if (!(a > Integer.parseInt(matcher.group(3)))) { i.remove(); }
		    		break;
		    	case "==":
		    		if (!(a == Integer.parseInt(matcher.group(3)))) { i.remove(); }
		    		break;
		    	case "!=":
		    		if (!(a != Integer.parseInt(matcher.group(3)))) { i.remove(); }
		    		break;
		    	}
		    }
		    
		    
		    
		    for (int i = 0; i < trains.size(); i++) {
				System.out.println("RESULT = " + trains.get(i).toString());
			}
	    }
	    System.out.println("Program done");
	}
}