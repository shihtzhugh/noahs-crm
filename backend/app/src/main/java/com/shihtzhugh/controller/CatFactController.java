package com.shihtzhugh.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cats/facts")
public class CatFactController {
    
    @GetMapping
    public ResponseEntity<String> getCatFacts(HttpServletRequest request) {
        return ResponseEntity.ok("{\"fact\": \"Cats are not dogs.\"}");
    }
}
