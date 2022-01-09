package com.nick.flipper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nick.flipper.syllables.SyllableHttpRequestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FlipperApplicationTests {

    @Autowired
    private SyllableHttpRequestService syllableHttpRequestService;

    @Test
    void contextLoads() {
    }

    @Test
    void testRequest() throws JsonProcessingException {
        var result = syllableHttpRequestService.getSyllables("собака");
    }

}
