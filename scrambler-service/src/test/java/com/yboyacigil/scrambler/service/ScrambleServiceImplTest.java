package com.yboyacigil.scrambler.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
class ScrambleServiceImplTest {

    private ScrambleService service;

    @BeforeEach
    void setUp() {
        service = new ScrambleServiceImpl();
    }

    @Test
    void testMaxThreeLetters() {
        String text = "a";
        String result = service.scramble(text);
        log.info("Result: `{}`", result);
        assertEquals(text, result);

        text = "an";
        result = service.scramble(text);
        log.info("Result: `{}`", result);
        assertEquals(text, result);

        text = "mom";
        result = service.scramble(text);
        log.info("Result: `{}`", result);
        assertEquals(text, result);
    }

    @Test
    void testMoreThanThreeLetters() {
        String text = "mama";
        String result = service.scramble(text);
        log.info("Result: `{}`", result);
        assertEquals(text.length(), result.length());
        assertEquals(text.charAt(0), result.charAt(0));
        assertEquals(text.charAt(text.length() - 1), result.charAt(result.length() - 1));

        text = "mamal";
        result = service.scramble(text);
        log.info("Result: `{}`", result);
        assertEquals(text.length(), result.length());
        assertEquals(text.charAt(0), result.charAt(0));
        assertEquals(text.charAt(text.length() - 1), result.charAt(result.length() - 1));

        text = "animals";
        result = service.scramble(text);
        log.info("Result: `{}`", result);
        assertEquals(text.length(), result.length());
        assertEquals(text.charAt(0), result.charAt(0));
        assertEquals(text.charAt(text.length() - 1), result.charAt(result.length() - 1));
    }

    @Test
    void testSentenceWithWhitespaceAndNumbers() {
        String text = "This is\n\t\tindented text with 8 words and 2 lines!";
        String result = service.scramble(text);
        log.info("Result: `{}`", result);
        assertEquals(text.length(), result.length());
    }

}