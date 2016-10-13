package by.alesnax.aircompany.entity;

import by.alesnax.aircompany.constant.Constants;

/**
 * Created by alesnax on 08.10.2016.
 */
public class Airliner extends Plane {

    private int seats;
    private double maxLuggageWeight;

    public Airliner() {
    }

    @Override
    public double calcMaxCarrying() {
        return getFuelTank() + getMaxLuggageWeight() + getSeats() * Constants.AVERAGE_PASSENGER_WEIGHT;
    }

    public Airliner(String id, String model, double fuelConsumption, double fuelTank, int seats, double maxLuggageWeight) {
        super(id, model, fuelConsumption, fuelTank);
        this.seats = seats;
        this.maxLuggageWeight = maxLuggageWeight;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public double getMaxLuggageWeight() {
        return maxLuggageWeight;
    }

    public void setMaxLuggageWeight(double maxLuggageWeight) {
        this.maxLuggageWeight = maxLuggageWeight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Airliner)) return false;
        if (!super.equals(o)) return false;

        Airliner airliner = (Airliner) o;

        if (getSeats() != airliner.getSeats()) return false;
        return Double.compare(airliner.getMaxLuggageWeight(), getMaxLuggageWeight()) == 0;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        result = 31 * result + getSeats();
        temp = Double.doubleToLongBits(getMaxLuggageWeight());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return super.toString() + "Airliner{" +
                "passengers=" + seats +
                ", maxLuggageWeight=" + maxLuggageWeight +
                '}';
    }
}
