package by.alesnax.logisticsbase.entity;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by alesnax on 22.10.2016.
 */
public class WaitingQueue {
    private static Logger logger = LogManager.getLogger(WaitingQueue.class);

    private LinkedList<Truck> waitingQueue = new LinkedList<Truck>();
    private int perishablePosition = -1;
    private Lock lock = new ReentrantLock();

    public LinkedList<Truck> getWaitingQueue() {
        return waitingQueue;
    }

    public void getPosition(Truck truck) {
        try {
            lock.lock();
            if (!truck.isPerishable()) {
                waitingQueue.addLast(truck);
                logger.log(Level.INFO, "Normal truck #" + truck.getTruckId() + " added into " + waitingQueue.size() + " pos");
            } else {
                ++perishablePosition;
                waitingQueue.add(perishablePosition, truck);
                logger.log(Level.INFO, "Perishable truck #" + truck.getTruckId() + " added into " + (perishablePosition + 1) + " pos");
            }
        } finally {
            lock.unlock();
        }
    }

    public void checkIfFirst(Truck truck) {
        try {
            lock.lock();
            if (waitingQueue.getFirst().equals(truck)) {
                truck.setFirstPos(true);
            }
        } finally {
            lock.unlock();
        }
    }

    public void deleteFromWaitingQueue(Truck truck) {
        try {
            lock.lock();
            if (truck.isPerishable()) {
                --perishablePosition;
            }
            waitingQueue.remove(truck);
            logger.log(Level.INFO, "truck #" + truck.getTruckId() + " was deleted from queue");
        } finally {
            lock.unlock();
        }


    }
}
