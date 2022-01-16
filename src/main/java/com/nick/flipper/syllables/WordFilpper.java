package com.nick.flipper.syllables;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class WordFilpper {

    private final SyllableHttpRequestService syllableHttpRequestService;

    public WordFilpper(SyllableHttpRequestService syllableHttpRequestService) {
        this.syllableHttpRequestService = syllableHttpRequestService;
    }

    public String flipFullName(final String name, final String surname) {
        var nameSyllables = getSyllables(name);
        var surnameSyllables = getSyllables(surname);

        var buffer = nameSyllables.get(0);

        nameSyllables.set(0, surnameSyllables.get(0));
        surnameSyllables.set(0, buffer);

        return String.join("", nameSyllables) + " " + String.join("", surnameSyllables);
    }

    private List<String> getSyllables(final String word) {
        try {
            return tokenize(syllableHttpRequestService.getSyllables(word));
        } catch (IOException e) {
            throw new RuntimeException("Could not get syllables");
        }
    }

    private List<String> tokenize(final String word) {
        return Arrays.asList(word.split("-"));
    }

}
