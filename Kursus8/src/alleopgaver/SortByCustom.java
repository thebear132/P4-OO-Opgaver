package alleopgaver;

import java.util.Comparator;

class SortByCustom implements Comparator<Train> {
    String type;

    public SortByCustom(String type) {
        this.type = type;
    }

    public int compare(Train a, Train b) {
        switch (type.strip()) {
            case "w":
                return Double.compare(b.getWeight(), a.getWeight());
            case "m":
                return Integer.compare(b.getManufacturerNumber(), a.getManufacturerNumber());
            case "v":
                return Double.compare(b.getVelocity(), a.getVelocity());
            default:
                System.out.println("Bad comparator!");
                return 0;
        }
    }
}
