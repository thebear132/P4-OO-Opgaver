package alleopgaver;

public class Main {
    public static void main(String[] args) {
        TrainListManager trainListManager = new TrainListManager();
        Thread trainManagerThread = new Thread(trainListManager);
        trainManagerThread.start();

        int numberOfOperators = 10;
        for (int i = 0; i < numberOfOperators; i++) {
            Thread operatorThread = new Thread(new TrainOperator(trainListManager));
            operatorThread.start();
        }
    }
}
