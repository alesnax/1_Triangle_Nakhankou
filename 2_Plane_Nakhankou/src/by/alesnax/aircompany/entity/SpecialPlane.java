package by.alesnax.aircompany.entity;

/**
 * Created by alesnax on 08.10.2016.
 */
public class SpecialPlane extends Plane {

    private double usefulWeight;
    private String type;

    public SpecialPlane() {
    }



    public SpecialPlane(String id, String model, double fuelConsumption, double fuelTank, double usefulWeight, String type) {
        super(id, model, fuelConsumption, fuelTank);
        this.usefulWeight = usefulWeight;
        this.type = type;
    }

    @Override
    public double calcMaxCarrying() {
        return getFuelTank() + getUsefulWeight();
    }

    public double getUsefulWeight() {
        return usefulWeight;
    }

    public void setUsefulWeight(double usefulWeight) {
        this.usefulWeight = usefulWeight;
    }

    public TypeUse getType() {
        type = type.replace('-', '_');
        return TypeUse.valueOf(type.toUpperCase());
    }

    public void setType(String type) {
        this.type = type;
    }

    public enum TypeUse{
        FIREFIGHTER, AGRICULTIRAL, GEODESIC, OTHER
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SpecialPlane)) return false;
        if (!super.equals(o)) return false;

        SpecialPlane that = (SpecialPlane) o;

        if (Double.compare(that.getUsefulWeight(), getUsefulWeight()) != 0) return false;
        return getType() == that.getType();

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        temp = Double.doubleToLongBits(getUsefulWeight());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (getType() != null ? getType().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return super.toString() + "SpecialPlane{" +
                "usefulWeight=" + usefulWeight +
                ", type=" + type +
                '}';
    }
}
