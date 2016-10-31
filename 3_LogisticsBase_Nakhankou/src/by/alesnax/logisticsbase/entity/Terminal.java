package by.alesnax.logisticsbase.entity;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by alesnax on 18.10.2016.
 */
public class Terminal {
    private static Logger logger = LogManager.getLogger(Terminal.class);
    private static Random rand = new Random();

    private int terminalId;
    private Truck parkedTruck;
    private final ResourcePool<Stillage> stillagePool;

    public Terminal(int terminalId, ArrayDeque<Stillage> stillagePool) {
        this.terminalId = terminalId;
        this.stillagePool = new ResourcePool<Stillage>(stillagePool);

    }

    public int getTerminalId() {
        return terminalId;
    }

    public Truck getParkedTruck() {
        return parkedTruck;
    }

    public void setParkedTruck(Truck parkedTruck) {
        this.parkedTruck = parkedTruck;
    }


    public void serveTruck() {
        if(parkedTruck.getProducts() != null){
            unloadTruck();
        }else{
            loadTruck();
        }
        try {
            TimeUnit.MILLISECONDS.sleep(rand.nextInt(3500));
        } catch (InterruptedException e) {
            logger.log(Level.ERROR, "InterruptedException" + e.getCause());
        }
    }

    private void unloadTruck() {
        Product product = null;
        while(!parkedTruck.getProducts().isEmpty()){
            product = parkedTruck.unloadProduct();
            boolean success = false;
            while(!success){
                Stillage stil = stillagePool.getResource();
                success = stil.loadProduct(product);
                stillagePool.returnResource(stil);
            }
        }

    }

    private void loadTruck() {
        while(!parkedTruck.getWishList().isEmpty()){
            Product wished = parkedTruck.getWishList().pollFirst();
            Product loaded = null;
            while(!wished.equals(loaded)){
                Stillage stil = stillagePool.getResource();
                loaded = stil.takeProduct(wished);
                stillagePool.returnResource(stil);
            }
            parkedTruck.loadProduct(loaded);
        }
    }

}

