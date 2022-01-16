package com.nick.flipper;

import com.nick.flipper.syllables.SyllableHttpRequestService;
import com.nick.flipper.syllables.WordFilpper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class FlipperApplicationTests {

    public static final String QUERY = "чупров";
    public static final String EXPECTED = "чуп-ров";

    @Autowired
    private SyllableHttpRequestService syllableHttpRequestService;

    @Autowired
    private WordFilpper wordFilpper;

    @Test
    void testRequest() throws IOException {
        var result = syllableHttpRequestService.getSyllables(QUERY);
        Assertions.assertThat(result).isEqualTo(EXPECTED);
    }

    @Test
    void testNameFlip() {
        var flipped = wordFilpper.flipFullName("Валерий", "Чупров");
    }

}
