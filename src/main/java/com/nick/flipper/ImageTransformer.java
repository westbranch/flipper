package com.nick.flipper;

import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.awt.image.BufferedImage;


@Component
public class ImageTransformer {

    public BufferedImage mirrorLeft(@NonNull int axis, @NonNull BufferedImage image) {
        var normal = image.getSubimage(0, 0, axis, image.getHeight());
        var mirrored = mirror(normal);
        var resultImage = new BufferedImage(axis * 2, image.getHeight(), BufferedImage.TYPE_INT_ARGB);
        var resultGraphics = resultImage.getGraphics();

        resultGraphics.drawImage(normal, 0, 0, null);
        resultGraphics.drawImage(mirrored, axis, 0, null);
        resultGraphics.dispose();

        return resultImage;
    }

    public BufferedImage mirrorRight(@NonNull int axis, @NonNull BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        int leftoverWidth = width - axis;

        var normal = image.getSubimage(axis, 0, leftoverWidth, height);
        var mirrored = mirror(normal);
        var resultImage = new BufferedImage(leftoverWidth * 2, height, BufferedImage.TYPE_INT_ARGB);
        var resultGraphics = resultImage.getGraphics();

        resultGraphics.drawImage(normal, leftoverWidth, 0, null);
        resultGraphics.drawImage(mirrored, 0, 0, null);
        resultGraphics.dispose();

        return resultImage;
    }

    public BufferedImage mirror(@NonNull BufferedImage source) {
        int width = source.getWidth();
        int height = source.getHeight();
        BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        for (int y = 0; y < height; y++) {
            for (int lx = 0, rx = width - 1; lx < width; lx++, rx--) {
                int pixel = source.getRGB(lx, y);
                result.setRGB(rx, y, pixel);
            }
        }

        return result;
    }

}
