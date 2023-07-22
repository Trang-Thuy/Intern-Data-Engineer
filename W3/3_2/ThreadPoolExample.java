import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolExample {
    public static void main(String[] args) {
        // Tạo ThreadPool với kích thước cố định là 5 luồng
        ExecutorService executor = Executors.newFixedThreadPool(5);

        // Tạo 10 tác vụ và gửi chúng vào ThreadPool để xử lý
        for (int i = 1; i <= 10; i++) {
            Task task = new Task("Task " + i);
            executor.execute(task);
        }

        // Đóng ThreadPool sau khi hoàn thành tất cả các tác vụ
        executor.shutdown();
    }
}

class Task implements Runnable {
    private String name;

    public Task(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("Starting: " + name);
        try {
            // Giả lập thời gian thực hiện công việc
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Completed: " + name);
    }
}
