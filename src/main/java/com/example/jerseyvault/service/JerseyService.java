package com.example.jerseyvault.service;

import com.example.jerseyvault.model.Jersey;
import com.example.jerseyvault.repository.JerseyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JerseyService {
    private final JerseyRepository jerseyRepository;

    @Autowired
    public JerseyService(JerseyRepository jerseyRepository) {
        this.jerseyRepository = jerseyRepository;
    }

    public List<Jersey> getAllJerseys() {
        return jerseyRepository.findAll();
    }

    public Jersey getJerseyById(Long id) {
        return jerseyRepository.findById(id).orElse(null);
    }

    public Jersey saveJersey(Jersey jersey) {
        return jerseyRepository.save(jersey);
    }

    public void deleteJersey(Long id) {
        jerseyRepository.deleteById(id);
    }

    public List<Jersey> getByTeam(String teamName) {
        return jerseyRepository.findByTeam_TeamNameIgnoreCase(teamName);
    }

    public List<Jersey> getByLeague(String leagueName) {
        return jerseyRepository.findByTeam_League_LeagueNameIgnoreCase(leagueName);
    }

    public List<Jersey> getByMaxPrice(double price) {
        return jerseyRepository.findByPriceLessThanEqual(price);
    }
}
