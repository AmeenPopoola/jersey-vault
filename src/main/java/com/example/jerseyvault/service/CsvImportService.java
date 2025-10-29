package com.example.jerseyvault.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.example.jerseyvault.model.Jersey;

@Service
public class CsvImportService {
    private static final Logger logger = LoggerFactory.getLogger(CsvImportService.class);

    private static final String CSV_CLASSPATH = "data/normalized_jersey_vault_premier_league_25_26.csv";

    public List<Jersey> loadJerseys() {
        List<Jersey> jerseys = new ArrayList<>();
        try {
            ClassPathResource resource = new ClassPathResource(CSV_CLASSPATH);
            if (!resource.exists()) {
                throw new IllegalStateException("CSV not found on classpath at: " + CSV_CLASSPATH);
            }

            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8))) {

                String line;
                boolean firstLine = true;

                while ((line = reader.readLine()) != null) {
                    if (firstLine) { firstLine = false; continue; }

                    // Split by commas but keep commas inside quotes intact
                    String[] parts = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                    if (parts.length < 8) continue;

                    Jersey j = new Jersey(
                            parts[0].trim(),               // League
                            parts[1].trim(),               // Team
                            parts[2].trim(),               // Season
                            parts[3].trim(),               // Type
                            stripQuotes(parts[4]).trim(),  // Image URL
                            stripQuotes(parts[5]).trim(),  // Retailer
                            parsePrice(parts[6]),          // Price
                            stripQuotes(parts[7]).trim()   // Link
                    );
                    jerseys.add(j);
                }
            }
        } catch (java.io.IOException e) {
            logger.error("I/O error while reading CSV file: {}", e.getMessage(), e);
        } catch (IllegalStateException e) {
            logger.error("Resource error: {}", e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Unexpected error while processing CSV: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to process CSV file", e);
        }
        return jerseys;
    }

    private static String stripQuotes(String s) {
        return s == null ? "" : s.replaceAll("^\"|\"$", "");
    }

    private static double parsePrice(String price) {
        try { 
            return Double.parseDouble(price.replaceAll("[^0-9.]", "")); 
        } catch (NumberFormatException | NullPointerException e) { 
            return 0.0; 
        }
    }
}
