package com.connector;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public abstract class AbstractHttpConnector {

    String name;
    private long lastPolledTimeStamp;

    private static final Logger LOG = LoggerFactory.getLogger(AbstractHttpConnector.class);

    public AbstractHttpConnector(String x) {
        name =x;
    }

    public long getLastPolledTimeStamp() {
        return lastPolledTimeStamp;
    }

    public void setLastPolledTimeStamp(long lastPolledTimeStamp) {
        this.lastPolledTimeStamp = lastPolledTimeStamp;
    }
}
