package by.alesnax.logisticsbase.entity;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayDeque;
import java.util.concurrent.Semaphore;

// static import
import static by.alesnax.logisticsbase.constant.Constant.TERMINAL_NUMBER;

/**
 * Created by alesnax on 22.10.2016.
 */
public class ResourcePool <T> {
    private static Logger logger = LogManager.getLogger(ResourcePool.class);

    private final static int POOL_SIZE = TERMINAL_NUMBER;
    private final Semaphore semaphore = new Semaphore(POOL_SIZE, true);
    private final ArrayDeque<T> resources = new ArrayDeque<T>();

    public ResourcePool(ArrayDeque<T> source){
        resources.addAll(source);
    }

    public T getResource() {
        T t = null;
        try {
            semaphore.acquire();
            t = resources.poll();
        } catch (InterruptedException e) {
            logger.log(Level.ERROR, "InterruptedException" + e.getCause());
        }
        return t;
    }

    public void returnResource(T t) {
        resources.add(t);
        semaphore.release();
    }
}
