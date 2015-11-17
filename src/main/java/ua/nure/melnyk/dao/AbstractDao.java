package ua.nure.melnyk.dao;

import org.springframework.beans.factory.annotation.Autowired;
import ua.nure.melnyk.storage.MarketStorage;

import java.util.concurrent.atomic.AtomicLong;

public abstract class AbstractDao {

    @Autowired
    private MarketStorage storage;

    private AtomicLong atomicLong = new AtomicLong();

    public MarketStorage getStorage() {
        return storage;
    }
    
    public long generateId() {
        return atomicLong.incrementAndGet();
    }

}
