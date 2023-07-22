import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockExample {
    private int value = 0;
    private ReadWriteLock lock = new ReentrantReadWriteLock();

    public int getValue() {
        lock.readLock().lock(); // Acquire the read lock
        try {
            return value; // Read the value
        } finally {
            lock.readLock().unlock(); // Release the read lock
        }
    }

    public void setValue(int newValue) {
        lock.writeLock().lock(); // Acquire the write lock
        try {
            value = newValue; // Update the value
        } finally {
            lock.writeLock().unlock(); // Release the write lock
        }
    }

    public static void main(String[] args) {
        ReadWriteLockExample example = new ReadWriteLockExample();

        // Create and start multiple threads to read the value
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                int val = example.getValue();
                System.out.println("Thread " + Thread.currentThread().getId() + " read value: " + val);
            }).start();
        }

        // Create and start a thread to write the value
        new Thread(() -> {
            int newValue = 100;
            example.setValue(newValue);
            System.out.println("Thread " + Thread.currentThread().getId() + " wrote value: " + newValue);
        }).start();
    }
}
