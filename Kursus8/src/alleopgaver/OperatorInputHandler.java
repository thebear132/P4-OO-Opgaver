package alleopgaver;

import java.util.Scanner;

public class OperatorInputHandler implements Runnable {
    private TrainListManager trainListManager;

    public OperatorInputHandler(TrainListManager trainListManager) {
        this.trainListManager = trainListManager;
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter command (add/remove/search/exit): ");
            String command = scanner.nextLine();
            if (command.equals("exit")) {
                break;
            }
            switch (command) {
                case "add":
                    System.out.println("Enter weight, manufacturer number, and velocity: ");
                    double weight = scanner.nextDouble();
                    int manufacturerNumber = scanner.nextInt();
                    float velocity = scanner.nextFloat();
                    trainListManager.addTrain(new Train(weight, manufacturerNumber, velocity));
                    break;
                case "remove":
                    System.out.println("Enter train index to remove: ");
                    int index = scanner.nextInt();
                    trainListManager.removeTrain(index);
                    break;
                case "search":
                    // Implement search functionality here if needed
                    break;
                default:
                    System.out.println("Invalid command.");
            }
        }
        scanner.close();
    }
}
