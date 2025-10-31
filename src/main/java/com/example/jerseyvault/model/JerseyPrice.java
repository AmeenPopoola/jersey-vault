package com.example.jerseyvault.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "jersey_prices")
public class JerseyPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long priceId;

    private Double price;
    @Column(length = 1000)
    private String link;
    private LocalDateTime lastUpdated = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "jersey_id")
    private Jersey jersey;

    @ManyToOne
    @JoinColumn(name = "retailer_id")
    private Retailer retailer;

    public JerseyPrice() {}

    // Getters and setters
    public Long getPriceId() { return priceId; }
    public void setPriceId(Long priceId) { this.priceId = priceId; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public String getLink() { return link; }
    public void setLink(String link) { this.link = link; }

    public LocalDateTime getLastUpdated() { return lastUpdated; }
    public void setLastUpdated(LocalDateTime lastUpdated) { this.lastUpdated = lastUpdated; }

    public Jersey getJersey() { return jersey; }
    public void setJersey(Jersey jersey) { this.jersey = jersey; }

    public Retailer getRetailer() { return retailer; }
    public void setRetailer(Retailer retailer) { this.retailer = retailer; }
}
