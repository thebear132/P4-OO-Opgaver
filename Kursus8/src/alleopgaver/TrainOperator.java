package alleopgaver;

import java.util.Random;

public class TrainOperator implements Runnable {
    private TrainListManager trainListManager;
    private Random random;

    public TrainOperator(TrainListManager trainListManager) {
        this.trainListManager = trainListManager;
        this.random = new Random();
    }

    @Override
    public void run() {
        while (true) {
            int action = random.nextInt(3);
            switch (action) {
                case 0:
                    // Add train
                    trainListManager.addTrain(new Train(random.nextDouble(50, 60), random.nextInt(100), random.nextFloat(50)));
                    break;
                case 1:
                    // Remove train
                    if (trainListManager.getSize() > 0) {
                        trainListManager.removeTrain(random.nextInt(trainListManager.getSize()));
                    }
                    break;
                case 2:
                    // Search train (implement if necessary)
                    break;
            }
            try {
                Thread.sleep(random.nextInt(1000)); // Sleep for a random time
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
