package com.example.jerseyvault.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "jerseys")
public class Jersey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jerseyId;

    private String season;
    private String type;
    @Column(length = 1000)
    private String imageUrl;

    // A jersey belongs to one team
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    // A jersey can have multiple prices from different retailers
    @OneToMany(mappedBy = "jersey", cascade = CascadeType.ALL)
    private List<JerseyPrice> prices = new ArrayList<>();

    public Jersey() {}

    // Getters and setters
    public Long getJerseyId() { return jerseyId; }
    public void setJerseyId(Long jerseyId) { this.jerseyId = jerseyId; }

    public String getSeason() { return season; }
    public void setSeason(String season) { this.season = season; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public Team getTeam() { return team; }
    public void setTeam(Team team) { this.team = team; }

    public List<JerseyPrice> getPrices() { return prices; }
    public void setPrices(List<JerseyPrice> prices) { this.prices = prices; }
    
    public Double getLowestPrice() {
        return prices.stream()
                .mapToDouble(JerseyPrice::getPrice)
                .min()
                .orElse(0.0);
    }
}
