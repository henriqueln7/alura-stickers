package imdb;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import static java.awt.image.BufferedImage.TRANSLUCENT;

public class StickerGenerator {
    public void generateSticker(Content content) throws IOException {
        BufferedImage originalImage = ImageIO.read(new URL(content.imageUrl()).openStream());

        int width = originalImage.getWidth();
        int height = originalImage.getHeight();

        int newHeight = height + 200;

        BufferedImage newImage = new BufferedImage(width, newHeight, TRANSLUCENT);
        Graphics2D graphics = (Graphics2D) newImage.getGraphics();

        graphics.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 64));
        graphics.setColor(Color.YELLOW);
        graphics.drawImage(originalImage, 0, 0, null);
        graphics.drawString("Topzera", 100,newHeight - 100);

        ImageIO.write(newImage, "png", createFileWithDirectoryAssurance("stickers", content.title() + ".jpg"));
    }

    private File createFileWithDirectoryAssurance(String directory, String filename) {
        File dir = new File(directory);
        if (!dir.exists()) dir.mkdirs();
        return new File(directory + File.separatorChar + filename);
    }

}