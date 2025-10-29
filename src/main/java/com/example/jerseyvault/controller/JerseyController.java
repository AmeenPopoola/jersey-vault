package com.example.jerseyvault.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.jerseyvault.model.Jersey;
import com.example.jerseyvault.service.CsvImportService;

@RestController
@RequestMapping("/api/jerseys")
public class JerseyController {

    private final CsvImportService csvImportService;

    public JerseyController(CsvImportService csvImportService) {
        this.csvImportService = csvImportService;
    }

    // ✅ 1. Get ALL jerseys
    @GetMapping
    public List<Jersey> getAllJerseys() {
        return csvImportService.loadJerseys();
    }

    // ✅ 2. Filter by league (e.g. /api/jerseys/league/Premier%20League)
    @GetMapping("/league/{league}")
    public List<Jersey> getByLeague(@PathVariable String league) {
        return csvImportService.loadJerseys().stream()
                .filter(j -> j.getLeague().equalsIgnoreCase(league))
                .collect(Collectors.toList());
    }

    // ✅ 3. Optional: Filter by team name (e.g. /api/jerseys/team/Arsenal)
    @GetMapping("/team/{team}")
    public List<Jersey> getByTeam(@PathVariable String team) {
        return csvImportService.loadJerseys().stream()
                .filter(j -> j.getTeam().equalsIgnoreCase(team))
                .collect(Collectors.toList());
    }

    // ✅ 4. Optional: Filter by max price (e.g. /api/jerseys/price?max=100)
    @GetMapping("/price")
    public List<Jersey> getByMaxPrice(@RequestParam double max) {
        return csvImportService.loadJerseys().stream()
                .filter(j -> j.getPrice() <= max)
                .collect(Collectors.toList());
    }
}
