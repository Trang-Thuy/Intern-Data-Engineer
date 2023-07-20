 package Thread;


public class DaemonThread extends Thread {
    public DaemonThread(String name) {
        super(name);
    }

    public void run() {
        // Checking whether the thread is Daemon or not
        if (Thread.currentThread().isDaemon()) {
            System.out.println(getName() + " is Daemon thread");
        }

        else {
            System.out.println(getName() + " is User thread");
        }
    }

    public static void main(String[] args) {

        DaemonThread t1 = new DaemonThread("t1");
        DaemonThread t2 = new DaemonThread("t2");
        DaemonThread t3 = new DaemonThread("t3");

        // Setting user thread t1 to Daemon
        try {
            t1.setDaemon(true);
        } catch (IllegalThreadStateException i) {
            System.out.println(i);
        }
        t1.start();

        // starting first 2 threads

        t2.start();

        // Setting user thread t3 to Daemon
        t3.setDaemon(true);
        t3.start();
    }
}