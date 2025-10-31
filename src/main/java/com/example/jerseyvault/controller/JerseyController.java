package com.example.jerseyvault.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.jerseyvault.model.Jersey;
import com.example.jerseyvault.service.JerseyService;

@RestController
@RequestMapping("/api/jerseys")
public class JerseyController {

    private final JerseyService jerseyService;

    public JerseyController(JerseyService jerseyService) {
        this.jerseyService = jerseyService;
    }

    // ✅ 1. Get all jerseys
    @GetMapping
    public List<Jersey> getAllJerseys() {
        return jerseyService.getAllJerseys();
    }

    // ✅ 2. Get jerseys by league (e.g. /api/jerseys/league/Premier%20League)
    @GetMapping("/league/{league}")
    public List<Jersey> getByLeague(@PathVariable String league) {
        return jerseyService.getByLeague(league);
    }

    // ✅ 3. Get jerseys by team (e.g. /api/jerseys/team/Arsenal)
    @GetMapping("/team/{team}")
    public List<Jersey> getByTeam(@PathVariable String team) {
        return jerseyService.getByTeam(team);
    }

    // ✅ 4. Filter jerseys by max price (e.g. /api/jerseys/price?max=100)
    @GetMapping("/price")
    public List<Jersey> getByMaxPrice(@RequestParam double max) {
        return jerseyService.getByMaxPrice(max);
    }

    // ✅ 5. Add a new jersey (for testing or admin upload)
    @PostMapping
    public Jersey addJersey(@RequestBody Jersey jersey) {
        return jerseyService.saveJersey(jersey);
    }

    // ✅ 6. Optional: delete a jersey by ID
    @DeleteMapping("/{id}")
    public void deleteJersey(@PathVariable Long id) {
        jerseyService.deleteJersey(id);
    }
}
