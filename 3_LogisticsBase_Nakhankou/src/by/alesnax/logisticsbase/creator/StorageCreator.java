package by.alesnax.logisticsbase.creator;

import by.alesnax.logisticsbase.constant.Constant;
import by.alesnax.logisticsbase.entity.Product;
import by.alesnax.logisticsbase.entity.Stillage;
import by.alesnax.logisticsbase.entity.Terminal;


import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by alesnax on 20.10.2016.
 */
public class StorageCreator {
    private static Random rand = new Random();


    public ArrayDeque<Terminal> createTerminals(int terminalsSize, ArrayDeque<Stillage> stillages) {
        ArrayDeque<Terminal> terminals = new ArrayDeque<>();
        for (int i = 0; i < terminalsSize; i++) {
            Terminal t = new Terminal(i, stillages);
            terminals.add(t);
        }
        return terminals;
    }

    public ArrayDeque<Stillage> createStillages(int stillageNumber) {
        ArrayDeque<Stillage> stillages = new ArrayDeque<>();
        Stillage stillage = null;
        OrderCreator creator = new OrderCreator();

        for (int i = 0; i < stillageNumber; i++) {
            HashMap<Integer, Product> places = new HashMap<>();
            int placesSize = Constant.STILLAGE_DEF_PLACES + rand.nextInt(40);
            for (int j = 0; j < placesSize; j++) {
                places.put(j, null);
            }
            stillage = new Stillage(stillageNumber, places);
            creator.createFirstProducts(stillage);
            stillages.add(stillage);
        }
        return stillages;
    }

}
