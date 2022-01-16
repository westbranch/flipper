package com.nick.flipper;

import com.nick.flipper.syllables.SyllableHttpRequestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class FlipperApplicationTests {

    @Autowired
    private SyllableHttpRequestService syllableHttpRequestService;

    @Test
    void contextLoads() {
    }

    @Test
    void testRequest() throws IOException {
        var result = syllableHttpRequestService.getSyllables("собака");
    }

}
