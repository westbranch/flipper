package com.nick.flipper;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class ImageTransformerTest {

    @Spy
    private ImageTransformer imageTransformer;

    @Test
    public void shouldMirrorWholeImage() {
        var expected = generateImage();
        var actual = imageTransformer.mirror(expected);

        int firstPixel = actual.getRGB(0, 0);
        int secondPixel = actual.getRGB(1, 0);

        assertEquals(Color.red.getRGB(), firstPixel);
        assertEquals(Color.green.getRGB(), secondPixel);
    }

    @Test
    public void shouldFlipLeft() {
        var expected = generateImage();
        var expectedWidth = 4;
        var expectedHeight = 1;

        var actual = imageTransformer.mirrorLeft(2, expected);
        int actualFirstPixel = actual.getRGB(0, 0);
        int actualSecondPixel = actual.getRGB(1, 0);
        int actualThirdPixel = actual.getRGB(2, 0);
        int actualFourthPixel = actual.getRGB(3, 0);

        assertEquals(expectedWidth, actual.getWidth());
        assertEquals(expectedHeight, actual.getHeight());

        assertEquals(Color.green.getRGB(), actualFirstPixel);
        assertEquals(Color.red.getRGB(), actualSecondPixel);
        assertEquals(Color.red.getRGB(), actualThirdPixel);
        assertEquals(Color.green.getRGB(), actualFourthPixel);
    }

    private BufferedImage generateImage() {
        var resultImage = new BufferedImage(2, 1, BufferedImage.TYPE_INT_ARGB);
        resultImage.setRGB(0, 0, Color.green.getRGB());
        resultImage.setRGB(1, 0, Color.red.getRGB());
        return resultImage;
    }

}
