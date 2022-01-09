package com.nick.flipper;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class ImageUploadController {

    private final ImageTransformer imageTransformer;
    private final ImageService imageService;

    @PostMapping(value = "/mirror", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getImage(@RequestParam("axis") String axis, @RequestParam("file") MultipartFile file
    ) throws IOException {
        int axisValue = Integer.parseInt(axis);
        var sourceImage = imageService.toBufferedImage(file);
        var flipLeft = imageTransformer.mirrorLeft(axisValue, sourceImage);

        var result = imageService.toByteArray(flipLeft);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/local")
    public String flipImage(@RequestParam("axis") String axis, @RequestParam("file") MultipartFile file) throws IOException {
        BufferedImage sourceImage = ImageIO.read(file.getInputStream());

        int axisValue = Integer.parseInt(axis);
        BufferedImage mirroredLeft = imageTransformer.mirrorLeft(axisValue, sourceImage);
        BufferedImage mirroredRight = imageTransformer.mirrorRight(axisValue, sourceImage);

        imageService.saveToDesktop(mirroredLeft, "mirrored-left.png");
        imageService.saveToDesktop(mirroredRight, "mirrored-right.png");
        return "IT WORKS";
    }

}
