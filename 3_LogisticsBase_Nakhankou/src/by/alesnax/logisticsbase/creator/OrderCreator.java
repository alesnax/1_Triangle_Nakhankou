package by.alesnax.logisticsbase.creator;

import by.alesnax.logisticsbase.entity.Product;
import by.alesnax.logisticsbase.entity.Stillage;
import by.alesnax.logisticsbase.entity.Truck;

import java.util.*;

/**
 * Created by alesnax on 19.10.2016.
 */
public class OrderCreator {
    private static Random rand = new Random();

    // List of possible Products
    private static ArrayList<Product> possibleProducts = new ArrayList<Product>() {
        {
            this.add(new Product("Sugar", 1.2, false));
            this.add(new Product("Rice", 0.6, false));
            this.add(new Product("Water", 0.8, false));
            this.add(new Product("Meal", 0.7, false));
            this.add(new Product("Corn", 0.7, false));
            this.add(new Product("Peas", 0.7, false));
            this.add(new Product("Fruits", 0.8, true));
            this.add(new Product("Vegetables", 0.3, true));
        }
    };

    public void createOrder(Truck arrivingTruck) {
        // creating and adding productlists of truck
        ArrayDeque<Product> unloadList = null;
        ArrayDeque<Product> loadList = null;

        int choice = rand.nextInt(2);
        switch (choice) {
            case 0:
                unloadList = createProductList(arrivingTruck.getCapacity());
                arrivingTruck.setProducts(unloadList);
                break;
            case 1:
                loadList = createProductList(arrivingTruck.getCapacity());
                arrivingTruck.setWishList(loadList);
                break;
        }
    }

    public void createFirstProducts(Stillage stillage) {
        for (int i = 0; i < stillage.getStillageSize(); i += 4) {
            stillage.loadProduct(possibleProducts.get(rand.nextInt(possibleProducts.size())));
        }
    }

    private ArrayDeque<Product> createProductList(double maxWeight) {
        ArrayDeque<Product> list = new ArrayDeque<>();
        double leftoverWeight = maxWeight;
        Product product = null;

        while (true) {
            product = possibleProducts.get(rand.nextInt(possibleProducts.size()));
            if (leftoverWeight - product.getWeight() > 0) {
                list.addLast(product);
                leftoverWeight -= product.getWeight();
            } else {
                break;
            }
        }
        return list;
    }
}
