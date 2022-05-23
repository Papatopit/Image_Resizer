import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.io.File;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Main {
    private static final int newWidth = 300;
    static Queue<File> fileQueue;

    public static void main(String[] args) {
        Logger logger = (Logger) LogManager.getLogger("log");

        String srcFolder = "C:\\Users\\Павел\\Desktop\\src";
        String dstFolder = "C:\\Users\\Павел\\Desktop\\dst";

        File srcDir = new File(srcFolder);

        long start = System.currentTimeMillis();
        logger.info("Start");


        File[] files = srcDir.listFiles();
        int processor = Runtime.getRuntime().availableProcessors();

        if (files == null) {
            System.out.println("Ошибка чтения");
            return;
        }
        fileQueue = new ConcurrentLinkedQueue<>(List.of(files));

        for (int i = 0; i < processor; i++) {
            new Thread(new ImageResizer(fileQueue,newWidth,dstFolder,start)).start();
            logger.info("vafsv");
        }







//        Queue<File[]> queue = new ConcurrentLinkedQueue<>();
//
//        File[] files1 = new File[middle];
//        System.arraycopy(files, 0, files1, 0, files1.length);
//        ImageResizer resizer1 = new ImageResizer(files1, newWidth, dstFolder, start);
//        queue.add(files1);
//        new Thread(resizer1).start();
//
//
//        File[] files2 = new File[files.length - middle];
//        System.arraycopy(files, middle, files2, 0, files2.length);
//        ImageResizer resizer2 = new ImageResizer(files2, newWidth, dstFolder, start);
//        queue.add(files2);
//        new Thread(resizer2).start();
//
//        File[] files3 = new File[middle];
//        System.arraycopy(files,middle,files3,0,files3.length);
//        ImageResizer resizer3 = new ImageResizer(files3,newWidth,dstFolder,start);
//        queue.add(files3);
//        new Thread(resizer3).start();
//
//        File[] files4 = new File[middle];
//        System.arraycopy(files,middle,files4,0,files4.length);
//        ImageResizer resizer4 = new ImageResizer(files3,newWidth,dstFolder,start);
//        queue.add(files4);
//        new Thread(resizer4).start();
//
//        for (File[] files5: queue){
//            new Thread(new ImageResizer(files5,newWidth,dstFolder,start));
//        }


//        new Thread(() -> {
//            for (int i = 0; i < 10000; i++) {
//                System.out.println(i);
//            }
//        }).start();
    }
//    public static Queue<File[]> getQueueList(File[] files, int processor){
//        Queue<File[]> queue = new ConcurrentLinkedQueue<>();
//        int size = files.length / processor;
//        for (int i = 0; i < processor; i++) {
//            if (i == processor - 1) {
//                int sizeLast = size + (files.length - (size * processor));
//                File[] files1 = new File[sizeLast];
//                System.arraycopy(files, size * i, files1, 0, sizeLast);
//                queue.add(files1);
//            }
//            File[] files1 = new File[size];
//            System.arraycopy(files, size * i, files1, 0, size);
//            queue.add(files1);
//        }
//        return queue;
//    }
}
