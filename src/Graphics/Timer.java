package Graphics;

public class Timer {

    private long startTime;

    public Timer() {
        startTime = 0;
    }

    public void start() {
        startTime = System.currentTimeMillis();
    }

    public Long getTime() {
        return System.currentTimeMillis() - startTime;
    }
}
