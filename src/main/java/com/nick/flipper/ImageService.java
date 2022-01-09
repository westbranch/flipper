package com.nick.flipper;

import lombok.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

@Component
public class ImageService {

    private static final String USER_HOME = "user.home";
    private static final String DESKTOP = "/Desktop/";
    private static final String PNG = "png";

    public void save(@NonNull BufferedImage image, String name, String path) throws IOException {
        var outputFile = new File(path + name);
        ImageIO.write(image, PNG, outputFile);
    }

    public void saveToDesktop(BufferedImage image, String name) throws IOException {
        var desktopPath = System.getProperty(USER_HOME) + DESKTOP;
        save(image, name, desktopPath);
    }

    public BufferedImage toBufferedImage(@NonNull MultipartFile file) throws IOException {
        var inputStream = file.getInputStream();
        return ImageIO.read(inputStream);
    }

    public byte[] toByteArray(@NonNull BufferedImage image) throws IOException {
        var outputStream = new ByteArrayOutputStream();
        ImageIO.write(image, PNG, outputStream);
        return outputStream.toByteArray();
    }
}
