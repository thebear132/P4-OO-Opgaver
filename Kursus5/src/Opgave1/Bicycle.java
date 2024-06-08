package Opgave1;
import java.util.*;

public class Bicycle implements Comparable<Bicycle> {
	public int compareTo(Bicycle o) {
		if(this.stelNumber == ((Bicycle)o).stelNumber)
	        return 0;
		else if(this.stelNumber > ((Bicycle)o).stelNumber)
	        return 1;
	    else 
	        return -1;
    }
	
	private String name;
	private int stelNumber;
	private float speed;
	
	public Bicycle(String Name, int StelNumber, float Speed) {
		name = Name;
		stelNumber = StelNumber;
		speed = Speed;
	}
	public void Accelerate(float scale) {
		speed = speed * scale;
	}
	public float GetSpeed() {
		return speed;
	}
	public String Status() {
		return ("Name:" + name + " 	StelNr:" + stelNumber + "	Speed:" + speed);
	}
	
	public static void main(String[] args) {
		// Opg1
		int amount = 5;
		Bicycle[] bikeList = new Bicycle[amount];
        Random rand = new Random();
		
		for (int i = 0; i < bikeList.length; i++) {
			bikeList[i] = new Bicycle("Bike" + i, rand.nextInt(1000000), (float)rand.nextDouble(100));
		}
		
		for (int i = 0; i < bikeList.length; i++) {
			System.out.println(bikeList[i].Status());
		}
		
		// Opg2 - a
		// Add bikes to stack
		Stack<Bicycle> bikeStack = new Stack<Bicycle>();
		for (int i = 0; i < bikeList.length; i++) {
			bikeStack.push(bikeList[i]);
		}
		// Pop bikes from stack
		for (int i = 0; i < bikeList.length; i++) {
			Bicycle b = bikeStack.pop();
			b.Accelerate((float) 0.75);	// Decrease by 25%
			bikeList[i] = b;			// Add back to the list to save the speed update
			System.out.println("Popped: " + b.Status());
		}
		
		// Opg2 - b
		PriorityQueue<Bicycle> bikePriorityQueue = new PriorityQueue<Bicycle>(bikeList.length);
		for (int i = 0; i < bikeList.length; i++) {
			bikePriorityQueue.add(bikeList[i]);
		}
		for (int i = 0; i < bikeList.length; i++) {
			Bicycle b = bikePriorityQueue.poll();
			System.out.println("Priority polled " + b.Status());
		}
		
		// Opg2 - c
		LinkedList<Bicycle> bikeLinkedList = new LinkedList<Bicycle>();
		for (int i = 0; i < bikeList.length; i++) {
			bikeLinkedList.add(bikeList[i]);
		}
		for (int i = 0; i < bikeLinkedList.size(); i++) {
			if (bikeLinkedList.get(i).GetSpeed() < 10.0) {
				System.out.println("Removing slow bike: " + bikeLinkedList.get(i).Status());
				bikeLinkedList.remove(i);
			}
		}
		
		// Opg3
		HashMap<String, Bicycle> bikeHashMap = new HashMap<String, Bicycle>(); 
	    for (int i = 0; i < bikeLinkedList.size(); i++) {
			bikeHashMap.put(bikeLinkedList.get(i).name, bikeLinkedList.get(i));
		}
	    
	    System.out.println("________________________\n\n");
	    Scanner myInputScanner = new Scanner(System.in);  // Create a Scanner object to read from commandline
	    while (true) {
	    	System.out.println("Lookup bike by name:");
		    String bikeNameInput = myInputScanner.nextLine();  // Read user input
		    if (bikeNameInput.strip().equals("exit"))
		    	break;
		    
		    if (!bikeHashMap.containsKey(bikeNameInput)) {
		    	System.out.println("[!] Invalid name of bike");
		    	continue;
		    }
		    
		    Bicycle userBike = bikeHashMap.get(bikeNameInput);
		    System.out.println(userBike.Status());
	    }
	    
		System.out.println("Program done");
	}
}




