package com.shihtzhugh.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cats/facts")
public class CatFactController {

    private static final List<String> catFacts = List.of(
            "Cats are actually not dogs!",
            "If your cat is very large, it may be a Lion. That's scary!",
            "If you are reading this, you are most likely not a cat.",
            "1 out of every 10 cats makes up a tenth of the cat population.",
            "In 2025, Loki was the 10th most common cat name in the United States.",
            "Despite common belief, cats do have the ability to bite people.",
            "I knew a cat once.",
            "The cat bit my sister.",
            "If you put every single cat in the world into a single acre of land, the cats would be very angry.",
            "Cat.",
            "I don't think cats should have the right to vote.",
            "Knock, knock.",
            "Who's there?",
            "Meow.",
            "Meow who?",
            "Meow you doing?",
            "That was good!",
            "Cats walk like camels and giraffes: They move both of their right feet first, then move both of their left feet. No other animals walk this way.",
            "\uD83D\uDC08",
            "Aww!",
            "\uD83D\uDC08 \uD83D\uDC08 \uD83D\uDC08 \uD83D\uDC08 \uD83D\uDC08 \uD83D\uDC08 \uD83D\uDC08 \uD83D\uDC08",
            "Oh no! That's too many! Go get em buddy! \uD83D\uDC15",
            "",
            "Are they gone?",
            "Phew!",
            "...",
            "I miss the cats \uD83D\uDE2D"
    );

    private int index = 0;

    @GetMapping
    public ResponseEntity<String> getCatFacts(HttpServletRequest request) {
        int factIdx = index;
        if (++index >= catFacts.size()) {
            index = 0;
        }
        return ResponseEntity.ok("{\"fact\": \"" + catFacts.get(factIdx) + "\"}");
    }
}
