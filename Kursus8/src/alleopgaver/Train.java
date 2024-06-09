package alleopgaver;

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

    public void charge_battery() {
        System.out.println("Charging");
    }

    public void get_battery_status() {
        System.out.println("Hej");
    }

    public void discharge_battery() {
        System.out.println("Discharging");
    }

    public void accelerate() {
        System.out.println("Going faster!" + Velocity);
    }

    public void honk() {
        System.out.println("HONK");
    }

    public double getWeight() {
        return this.weight;
    }

    public int getManufacturerNumber() {
        return this.ManufacturerNumber;
    }

    public double getVelocity() {
        return this.Velocity;
    }
}
