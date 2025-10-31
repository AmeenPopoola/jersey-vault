package com.example.jerseyvault.config;

import com.example.jerseyvault.model.*;
import com.example.jerseyvault.repository.*;
import com.example.jerseyvault.service.CsvImportService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.*;

@Component
public class DataLoader implements CommandLineRunner {

    private final CsvImportService csvImportService;
    private final LeagueRepository leagueRepository;
    private final TeamRepository teamRepository;
    private final RetailerRepository retailerRepository;
    private final JerseyRepository jerseyRepository;

    public DataLoader(CsvImportService csvImportService,
                      LeagueRepository leagueRepository,
                      TeamRepository teamRepository,
                      RetailerRepository retailerRepository,
                      JerseyRepository jerseyRepository) {
        this.csvImportService = csvImportService;
        this.leagueRepository = leagueRepository;
        this.teamRepository = teamRepository;
        this.retailerRepository = retailerRepository;
        this.jerseyRepository = jerseyRepository;
    }

    @Override
    public void run(String... args) {
        if (jerseyRepository.count() > 0) {
            System.out.println("✅ Jerseys already exist, skipping CSV import.");
            return;
        }

        System.out.println("🌍 Importing jerseys from CSV...");

        // Explicitly specify the generic type to help with type inference
        @SuppressWarnings("unchecked")
        List<JerseyCsv> csvJerseys = (List<JerseyCsv>) (List<?>) csvImportService.loadJerseys();

        for (JerseyCsv csv : csvJerseys) {
            League league = leagueRepository.findByLeagueNameIgnoreCase(csv.getLeague())
                    .orElseGet(() -> leagueRepository.save(new League(csv.getLeague(), "England")));

            Team team = teamRepository.findByTeamNameIgnoreCase(csv.getTeam())
                    .orElseGet(() -> {
                        Team newTeam = new Team();
                        newTeam.setTeamName(csv.getTeam());
                        newTeam.setLeague(league);
                        return teamRepository.save(newTeam);
                    });

            Retailer retailer = retailerRepository.findByRetailerNameIgnoreCase(csv.getRetailer())
                    .orElseGet(() -> {
                        Retailer newRetailer = new Retailer();
                        newRetailer.setRetailerName(csv.getRetailer());
                        newRetailer.setWebsiteUrl("https://" + csv.getRetailer().toLowerCase() + ".com");
                        return retailerRepository.save(newRetailer);
                    });

            Jersey jersey = new Jersey();
            jersey.setSeason(csv.getSeason());
            jersey.setType(csv.getType());
            jersey.setImageUrl(csv.getImageUrl());
            jersey.setTeam(team);

            JerseyPrice price = new JerseyPrice();
            price.setPrice(csv.getPrice());
            price.setLink(csv.getLink());
            price.setRetailer(retailer);
            price.setJersey(jersey);

            jersey.getPrices().add(price);
            jerseyRepository.save(jersey);
        }

        System.out.println("✅ CSV import complete!");
    }
}
