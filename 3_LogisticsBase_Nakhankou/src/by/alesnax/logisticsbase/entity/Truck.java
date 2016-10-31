package by.alesnax.logisticsbase.entity;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayDeque;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by alesnax on 18.10.2016.
 */
public class Truck extends Thread {
    private static Logger logger = LogManager.getLogger(Truck.class);
    private static Random rand = new Random();
    private final int ROAD_TIME = rand.nextInt(3500);
    private final static int CHECK_PAUSE = 100;

    private int truckId;
    private double capacity;
    private ArrayDeque<Product> products;
    private ArrayDeque<Product> wishList;
    private Storage storage;
    private boolean firstPos;

    public Truck(int truckId, double capacity) {
        this.truckId = truckId;
        this.capacity = capacity;
        this.products = new ArrayDeque<>();
        this.wishList = new ArrayDeque<>();
    }

    public int getTruckId() {
        return truckId;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setProducts(ArrayDeque<Product> products) {
        this.products = products;
    }

    public ArrayDeque<Product> getProducts() {
        return products;
    }

    public void setWishList(ArrayDeque<Product> wishList) {
        this.wishList = wishList;
    }

    public ArrayDeque<Product> getWishList() {
        return wishList;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public boolean isFirstPos() {
        return firstPos;
    }

    public void setFirstPos(boolean firstPos) {
        this.firstPos = firstPos;
    }

    public boolean isPerishable() {
        if (products != null) {
            for (Product unloadingProduct : products) {
                if (unloadingProduct.isPerishable()) {
                    return true;
                }
            }
        }
        return false;
    }


    public Product unloadProduct() {
        return !products.isEmpty() ? products.pollLast() : null;
    }

    public void loadProduct(Product product) {
        products.addLast(product);
    }


    public void run() {

        // 1. Time in road
        try {
            TimeUnit.MILLISECONDS.sleep(ROAD_TIME);
        } catch (InterruptedException e) {
            logger.log(Level.ERROR, "InterruptedException" + e.getCause());
        }

        // 2. Get position in waitingQueue
        storage.getWaitingQueue().getPosition(this);


        // 3. Waiting while truck is not first in waitingQueue
        while (!isFirstPos()) {
            try {
                TimeUnit.MILLISECONDS.sleep(CHECK_PAUSE);
                storage.getWaitingQueue().checkIfFirst(this);
            } catch (InterruptedException e) {
                logger.log(Level.ERROR, "InterruptedException" + e.getCause());
            }
        }

        // 4. Get terminal
        Terminal terminal = storage.getTerminalPool().getResource();
        logger.log(Level.INFO, "truck #" + truckId + " got Terminal #" + terminal.getTerminalId());

        // 5. Deleting from waiting waitingQueue and parking truck at terminal
        storage.getWaitingQueue().deleteFromWaitingQueue(this);
        terminal.setParkedTruck(this);

        // 6. Serving the truck by terminal
        terminal.serveTruck();
        logger.log(Level.INFO, "truck #" + truckId + " was served by Terminal #" + terminal.getTerminalId());
        terminal.setParkedTruck(null);

        // 7. Returning terminal into Pool
        storage.getTerminalPool().returnResource(terminal);
    }


    @Override
    public String toString() {
        return "Truck{" +
                "truckId='" + truckId + '\'' +
                ", capacity=" + capacity +
                ", storage=" + storage +
                '}';
    }
}
