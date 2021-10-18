package com.yboyacigil.scrambler.service;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.util.regex.Pattern;

@Slf4j
public class ScrambleServiceImpl implements ScrambleService {

    private static final Pattern PATTERN = Pattern.compile("([a-zA-Z_]+)");

    @Override
    public String scramble(@NonNull String text) {
        log.debug("Will scramble text: `{}`", text);

        val matcher = PATTERN.matcher(text);
        val result = matcher.replaceAll(matchResult -> doScramble(matchResult.group()));

        log.debug("Returning scrambled: `{}`", result);
        return result;
    }

    private String doScramble(String s) {
        if (s.length() <= 3) {
            return s;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(s.charAt(0));

        String middle = s.substring(1, s.length() - 1);
        val k = middle.length() / 2;
        sb.append(middle.charAt(k));
        for(int i = k - 1; i >= 0; i--) {
            sb.append(middle.charAt(i));
        }
        for(int j = middle.length() - 1; j > k; j--) {
            sb.append(middle.charAt(j));
        }

        sb.append(s.charAt(s.length() - 1));
        return sb.toString();
    }
}
