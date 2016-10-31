package by.alesnax.logisticsbase.runner;

import by.alesnax.logisticsbase.creator.OrderCreator;
import by.alesnax.logisticsbase.creator.StorageCreator;
import by.alesnax.logisticsbase.creator.TruckFactory;
import by.alesnax.logisticsbase.entity.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayDeque;

//static import
import static by.alesnax.logisticsbase.constant.Constant.*;

/**
 * Created by alesnax on 18.10.2016.
 */
public class Runner {
    private static Logger logger = LogManager.getLogger(Runner.class);

    public static void main(String[] args) {

        // 1. Creating random trucks.
        TruckFactory factory = new TruckFactory();
        ArrayDeque<Truck> trucks = factory.createTrucks(TRUCKS_NUM);
        logger.log(Level.INFO, trucks.size() + " trucks were created ");


        // 2. Appointment orders to trucks
        OrderCreator orderCreator = new OrderCreator();
        for (Truck truck : trucks) {
            orderCreator.createOrder(truck);
        }
        logger.log(Level.INFO, "Orders for trucks were created and set");


        // 3. Creating Storage with waiting queue and terminals and stillages
        Storage storage = Storage.getInstance();
        logger.log(Level.INFO, "storage " + storage.getName() + " created \n");


        // 4. Appointment storage to all trucks
        for (Truck truck : trucks) {
            truck.setStorage(storage);
        }


        // 5. Running all trucks
        for (Truck truck : trucks) {
                truck.start();
        }
    }
}
