package by.alesnax.logisticsbase.creator;

import by.alesnax.logisticsbase.constant.Constant;
import by.alesnax.logisticsbase.entity.Truck;

import java.util.ArrayDeque;
import java.util.Random;

/**
 * Created by alesnax on 19.10.2016.
 */
public class TruckFactory {
    private static Random rand = new Random();

    public ArrayDeque<Truck> createTrucks(int num) {
        ArrayDeque<Truck> trucks = new ArrayDeque<>();
        for (int j = 0; j < num; j++) {
            Truck truck = createTruck(j);
            trucks.add(truck);
        }
        return trucks;
    }

    private Truck createTruck(int count) {
        return new Truck(count, Constant.DEF_TRUCK_WEIGHT + rand.nextInt(5));
    }


}
