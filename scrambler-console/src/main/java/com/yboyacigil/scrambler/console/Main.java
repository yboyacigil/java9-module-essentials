package com.yboyacigil.scrambler.console;

import com.yboyacigil.scrambler.service.ScrambleService;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.util.Scanner;
import java.util.ServiceLoader;

@Slf4j
public class Main {

    public static void main(String[] args) {
        log.info("---- Scrambler Console ----");
        val serviceLoader = ServiceLoader.load(ScrambleService.class);
        val scrambleServiceOptional = serviceLoader.findFirst();
        scrambleServiceOptional.ifPresentOrElse(
            scrambleService -> {
                try (Scanner scanner = new Scanner(System.in)) {
                    log.info("Enter text to scramble: ");
                    val text = scanner.nextLine();
                    val scrambled = scrambleService.scramble(text);
                    log.info("Plain text: `{}`; scrambled: `{}`", text, scrambled);
                }
            },
            () -> {
                throw new IllegalStateException("No scramble service found");
            }
        );

        log.info("Done!");
    }
}
