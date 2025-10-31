package com.example.jerseyvault.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "retailers")
public class Retailer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long retailerId;

    private String retailerName;
    private String websiteUrl;

    @OneToMany(mappedBy = "retailer", cascade = CascadeType.ALL)
    private List<JerseyPrice> jerseyPrices = new ArrayList<>();

    public Retailer() {}

    // Getters and setters
    public Long getRetailerId() { return retailerId; }
    public void setRetailerId(Long retailerId) { this.retailerId = retailerId; }

    public String getRetailerName() { return retailerName; }
    public void setRetailerName(String retailerName) { this.retailerName = retailerName; }

    public String getWebsiteUrl() { return websiteUrl; }
    public void setWebsiteUrl(String websiteUrl) { this.websiteUrl = websiteUrl; }

    public List<JerseyPrice> getJerseyPrices() { return jerseyPrices; }
    public void setJerseyPrices(List<JerseyPrice> jerseyPrices) { this.jerseyPrices = jerseyPrices; }
}
