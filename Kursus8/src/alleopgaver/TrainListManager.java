package alleopgaver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TrainListManager implements Runnable {
    private ArrayList<Train> trains;
    private Random rnd;

    public TrainListManager() {
        trains = new ArrayList<>();
        rnd = new Random();
        int AmountOfTrains = 5;
        for (int i = 0; i < AmountOfTrains; i++) {
            trains.add(new Train(rnd.nextDouble(50, 60), rnd.nextInt(0, AmountOfTrains * 10), rnd.nextFloat(50)));
            System.out.println(trains.get(i).toString());
        }
    }

    @Override
    public void run() {
        Scanner myInputScanner = new Scanner(System.in); // Create a Scanner object to read from commandline

        while (true) {
            System.out.println("\n----------SEARCH----------");
            System.out.println("Attributes are w(weight) m(manufacturerNr) v(velocity)");
            System.out.println("FORMAT: [attribute] [<,>,==,!=] [value]:");
            String inputSearch = myInputScanner.nextLine().strip(); // Read user input
            // String inputSearch = "w < 55";
            if (inputSearch.equals("exit"))
                break;

            Pattern pattern = Pattern.compile("(v|w|m) (<|>|==|!=) (.*)", Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(inputSearch);
            if (!matcher.find()) {
                System.out.println("No match, try again");
                continue;
            }
            System.out.println("Matching to " + matcher.group(1));
            Collections.sort(trains, new SortByCustom(matcher.group(1)));

            for (Iterator<Train> i = trains.iterator(); i.hasNext(); ) {
                Train tmpTrain = i.next();
                double a = 0.0;
                switch (matcher.group(1)) {    // The attribute
                    case "w":
                        a = tmpTrain.getWeight();
                        break;
                    case "m":
                        a = tmpTrain.getManufacturerNumber();
                        break;
                    case "v":
                        a = tmpTrain.getVelocity();
                        break;
                    default:
                        break;
                }
                switch (matcher.group(2)) {
                    case "<":
                        if (!(a < Integer.parseInt(matcher.group(3)))) {
                            i.remove();
                        }
                        break;
                    case ">":
                        if (!(a > Integer.parseInt(matcher.group(3)))) {
                            i.remove();
                        }
                        break;
                    case "==":
                        if (!(a == Integer.parseInt(matcher.group(3)))) {
                            i.remove();
                        }
                        break;
                    case "!=":
                        if (!(a != Integer.parseInt(matcher.group(3)))) {
                            i.remove();
                        }
                        break;
                }
            }

            for (int i = 0; i < trains.size(); i++) {
                System.out.println("RESULT = " + trains.get(i).toString());
            }
        }
        System.out.println("Program done");
    }

    public synchronized void addTrain(Train train) {
        trains.add(train);
    }

    public synchronized void removeTrain(int index) {
        if (index >= 0 && index < trains.size()) {
            trains.remove(index);
        }
    }

    public synchronized Train getTrain(int index) {
        if (index >= 0 && index < trains.size()) {
            return trains.get(index);
        }
        return null;
    }

    public synchronized int getSize() {
        return trains.size();
    }
}
