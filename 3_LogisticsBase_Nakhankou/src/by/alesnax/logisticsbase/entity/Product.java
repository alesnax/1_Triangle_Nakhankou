package by.alesnax.logisticsbase.entity;

/**
 * Created by alesnax on 18.10.2016.
 */
public class Product {

    private String name;
    private double weight;
    private boolean perishable;       //скоропортящийся

    public Product(String name, double weight, boolean perishable) {
        this.name = name;
        this.weight = weight;
        this.perishable = perishable;
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public boolean isPerishable() {
        return perishable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (Double.compare(product.weight, weight) != 0) return false;
        if (perishable != product.perishable) return false;
        return name.equals(product.name);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name.hashCode();
        temp = Double.doubleToLongBits(weight);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (perishable ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", weight='" + weight + '\'' +
                ", perishable=" + perishable +
                '}';
    }
}
