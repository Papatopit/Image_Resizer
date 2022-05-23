import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Queue;

public class ImageResizer implements Runnable {

    private Queue<File> files;
    private int newWidth;
    private String dstFolder;
    private long start;

    public ImageResizer(Queue<File> files, int newWidth, String dstFolder, long start) {
        this.files = files;
        this.newWidth = newWidth;
        this.dstFolder = dstFolder;
        this.start = start;
    }


    @Override
    public void run() {
        try
        {

            while (true)
            {
                File file = files.poll();
                if (file == null){
                    break;
                }
                System.out.println(Thread.currentThread().getName() + " resize:" + file);


                BufferedImage image = ImageIO.read(file);
                if(image == null) {
                    break;
                }

                int newHeight = (int) Math.round(
                        image.getHeight() / (image.getWidth() / (double) newWidth)
                );
                BufferedImage newImage = new BufferedImage(
                        newWidth, newHeight, BufferedImage.TYPE_INT_RGB
                );

                int widthStep = image.getWidth() / newWidth;
                int heightStep = image.getHeight() / newHeight;

                for (int x = 0; x < newWidth; x++)
                {
                    for (int y = 0; y < newHeight; y++) {
                        int rgb = image.getRGB(x * widthStep, y * heightStep);
                        newImage.setRGB(x, y, rgb);
                    }
                }

                File newFile = new File(dstFolder + "/" + file.getName());
                ImageIO.write(newImage, "jpg", newFile);

            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
//        System.out.println(Thread.currentThread().getName() +
//                " Duration: " + (System.currentTimeMillis() - start + " ms"));
    }
}
