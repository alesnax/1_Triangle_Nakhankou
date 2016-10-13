package by.alesnax.aircompany.entity;

/**
 * Created by alesnax on 08.10.2016.
 */
public abstract class Plane {

    private String id;
    private String model;
    private double fuelConsumption;
    private double fuelTank;

    public Plane() {
    }

    public Plane(String id, String model, double fuelConsumption, double fuelTank) {
        this.id = id;
        this.model = model;
        this.fuelConsumption = fuelConsumption;
        this.fuelTank = fuelTank;
    }

    public abstract double calcMaxCarrying();


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    public double getFuelTank() {
        return fuelTank;
    }

    public void setFuelTank(double fuelTank) {
        this.fuelTank = fuelTank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Plane)) return false;

        Plane plane = (Plane) o;

        if (Double.compare(plane.getFuelConsumption(), getFuelConsumption()) != 0) return false;
        if (Double.compare(plane.getFuelTank(), getFuelTank()) != 0) return false;
        if (!getId().equals(plane.getId())) return false;
        return getModel().equals(plane.getModel());
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getId().hashCode();
        result = 31 * result + getModel().hashCode();
        temp = Double.doubleToLongBits(getFuelConsumption());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(getFuelTank());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Plane{" +
                "id='" + id + '\'' +
                ", model='" + model + '\'' +
                ", fuelConsumption=" + fuelConsumption + " kg/km" +
                ", fuelTank=" + fuelTank + " kg" +
                "  Type: ";
    }
}
