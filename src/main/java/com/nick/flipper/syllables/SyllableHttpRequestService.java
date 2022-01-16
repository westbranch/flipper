package com.nick.flipper.syllables;

import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Pattern;

@Service
public class SyllableHttpRequestService {

    private static final String BASE_URL = "https://slogi.su/word.json";

    public String getSyllables(@NonNull final String query) throws IOException {
        var url = new URL(BASE_URL);
        var con = (HttpURLConnection) url.openConnection();

        con.setRequestMethod("POST");
        con.setDoOutput(true);
        con.getOutputStream().write("q=собака".getBytes());

        var content = new StringBuilder();

        try (var in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            String line;
            while ((line = in.readLine()) != null) {
                content.append(line);
            }
        }
        con.disconnect();

        var output = content.toString();

        var pattern = Pattern.compile("(?<=<span class=word-syllables>)(.*)(?=</span>)");
        var matcher = pattern.matcher(output);

        if (matcher.matches()) {
            return matcher.group(1);
        }

        return "";
    }
}
