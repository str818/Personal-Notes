public class Process {
    public static void main(String[] args) {

        double[] data = new double[100000000];

        long startTime = System.currentTimeMillis();
        process(data);
        long endTime = System.currentTimeMillis();
        System.out.println("不使用多线程耗时：" + ((endTime - startTime) / 1000) + "s");

        startTime = System.currentTimeMillis();
        processUseThread(data);
        endTime = System.currentTimeMillis();
        System.out.println("使用多线程耗时：" + ((endTime - startTime) / 1000) + "s");

    }

    public static void process(double[] data) {
        for (int i = 0; i < data.length; i++) {
            data[i] += Math.sqrt(Math.random() * Math.random() * Math.random());
        }
    }

    public static void processUseThread(double[] data) {

        new Thread(() -> {
            for (int i = 0; i < data.length / 2; i++) {
                data[i] += Math.sqrt(Math.random() * Math.random() * Math.random());
            }
        }).start();

        new Thread(() -> {
            for (int i = data.length / 2; i < data.length; i++) {
                data[i] += Math.sqrt(Math.random() * Math.random() * Math.random());
            }
        }).start();
    }
}