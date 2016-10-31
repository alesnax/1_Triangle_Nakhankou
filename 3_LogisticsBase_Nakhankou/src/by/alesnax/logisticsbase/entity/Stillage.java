package by.alesnax.logisticsbase.entity;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by alesnax on 20.10.2016.
 */
public class Stillage {
    private int stillageId;
    private final HashMap<Integer, Product> places;

    public Stillage(int stillageId, HashMap<Integer, Product> places) {
        this.stillageId = stillageId;
        this.places = places;
    }

    public int getStillageId() {
        return stillageId;
    }

    public int getStillageSize(){
        return places.size();
    }

    public Product takeProduct(Product product) {
        Product result = null;
        Iterator<Map.Entry<Integer, Product>> it = places.entrySet().iterator();
        Product current;
        while(it.hasNext()){
            Map.Entry<Integer, Product> entry = it.next();
            current = entry.getValue();
            if(product.equals(current)){
                result = current;
                entry.setValue(null);
                break;
            }
        }
        return result;
    }

    public boolean loadProduct(Product product) {
        boolean loaded = false;
        Iterator<Map.Entry<Integer, Product>> it = places.entrySet().iterator();
        Product current;
        while(it.hasNext()){
            Map.Entry<Integer, Product> entry = it.next();
            if(entry.getValue() == null){
                entry.setValue(product);
                loaded = true;
                break;
            }
        }
       return loaded;
    }
}

