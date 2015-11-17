package ua.nure.melnyk.mbean;

public class Config implements ConfigMBean {

    private boolean shouldLog = true;

    public boolean isShouldLog() {
        return shouldLog;
    }

    @Override
    public void setShouldLog(boolean shouldLog) {
        this.shouldLog = shouldLog;
    }
}
