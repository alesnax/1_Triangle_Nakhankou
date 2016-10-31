package by.alesnax.logisticsbase.entity;

import by.alesnax.logisticsbase.creator.StorageCreator;

import java.util.ArrayDeque;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

// static import
import static by.alesnax.logisticsbase.constant.Constant.STILLAGE_NUMBER;
import static by.alesnax.logisticsbase.constant.Constant.STORAGE_NAME;
import static by.alesnax.logisticsbase.constant.Constant.TERMINAL_NUMBER;

/**
 * Created by alesnax on 18.10.2016.
 */
public class Storage {
    private static Storage instance;
    private static ReentrantLock lock = new ReentrantLock();
    private static AtomicBoolean created = new AtomicBoolean(false);

    private String name;
    private ResourcePool<Terminal> terminalPool;
    private ResourcePool<Stillage> stillagePool;
    private WaitingQueue waitingQueue = new WaitingQueue();

    private Storage(String name, ArrayDeque<Terminal> terminalPool, ArrayDeque<Stillage> stillagePool) {
        this.name = name;
        this.terminalPool = new ResourcePool<>(terminalPool);
        this.stillagePool = new ResourcePool<>(stillagePool);
    }

    public static Storage getInstance() {
        if (created.get() == false) {
            lock.lock();
            try {
                if (instance == null) {
                    StorageCreator creator = new StorageCreator();
                    ArrayDeque<Stillage> stillages = creator.createStillages(STILLAGE_NUMBER);
                    ArrayDeque<Terminal> terminals = creator.createTerminals(TERMINAL_NUMBER, stillages);
                    instance = new Storage(STORAGE_NAME, terminals, stillages);
                    created.getAndSet(true);
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    public String getName() {
        return name;
    }

    public ResourcePool<Terminal> getTerminalPool() {
        return terminalPool;
    }

    public WaitingQueue getWaitingQueue() {
        return waitingQueue;
    }

    public ResourcePool<Stillage> getStillagePool() {
        return stillagePool;
    }
}