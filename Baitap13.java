
class ReportInventory implements Runnable {
    private String taskGetOutbound;

    public ReportInventory(String taskGetOutbound){
        this.taskGetOutbound = taskGetOutbound;
    }
    @Override
    public void run() {
        System.out.println("Task: " + taskGetOutbound);
        try{
            Thread.sleep(1000);
            System.out.println("Task: " + taskGetOutbound + " completed.");
        } catch (InterruptedException e) {
            System.out.println("Task: " + taskGetOutbound + " interrupted.");
        }
    }
}

public class Baitap13 {
    public static void main(String[] args) {
        Thread reportInventoryThread = new Thread(new ReportInventory("Get Cirro Outbound"));
        Thread reportInventoryThread2 = new Thread(new ReportInventory("Get Amass Outbound"));
        reportInventoryThread.start();
        reportInventoryThread2.start();
        System.out.println("Main thread is doing other work...");
    }
}
