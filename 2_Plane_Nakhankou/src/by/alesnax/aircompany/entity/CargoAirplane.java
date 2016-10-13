package by.alesnax.aircompany.entity;

/**
 * Created by alesnax on 08.10.2016.
 */
public class CargoAirplane extends Plane{

    private double cargoWeight;

    public CargoAirplane() {
    }

    @Override
    public double calcMaxCarrying() {
        return getFuelTank() + getCargoWeight();
    }

    public CargoAirplane(String id, String model, double fuelConsumption, double fuelTank, double cargoWeight) {
        super(id, model, fuelConsumption, fuelTank);
        this.cargoWeight = cargoWeight;
    }



    public double getCargoWeight() {
        return cargoWeight;
    }

    public void setCargoWeight(double cargoWeight) {
        this.cargoWeight = cargoWeight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CargoAirplane)) return false;
        if (!super.equals(o)) return false;

        CargoAirplane that = (CargoAirplane) o;

        return Double.compare(that.getCargoWeight(), getCargoWeight()) == 0;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        temp = Double.doubleToLongBits(getCargoWeight());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return super.toString() + "CargoAirplane{" +
                "cargoWeight=" + cargoWeight +
                '}';
    }
}
