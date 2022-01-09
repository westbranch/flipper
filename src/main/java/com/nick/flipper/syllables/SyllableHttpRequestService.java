package com.nick.flipper.syllables;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.NonNull;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
public class SyllableHttpRequestService {

    private static final String BASE_URL = "https://slogi.su/word.json";


    public String getSyllables(@NonNull final String query) {
        var header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);

        var bodyParams = new LinkedMultiValueMap<String, String>();
        ;
        bodyParams.add("q", URLEncoder.encode(query, StandardCharsets.UTF_8));

        var requestEnty = new HttpEntity<>(bodyParams, header);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters()
                .add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        var result = restTemplate.exchange(BASE_URL, HttpMethod.POST, requestEnty, String.class);
        return result.getBody();
    }
}
