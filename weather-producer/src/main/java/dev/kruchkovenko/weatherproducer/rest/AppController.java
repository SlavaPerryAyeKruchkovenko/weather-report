package dev.kruchkovenko.weatherproducer.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/api")
public class AppController {
    @GetMapping("/ready")
    public ResponseEntity<Boolean> ready() {
        return ResponseEntity.ok(true);
    }
}
